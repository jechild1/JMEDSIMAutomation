package testCases.Admin;

import static org.testng.Assert.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.LockedAccountsPages.LockAccountPageFactory;
import pageFactories.Admin.LockedAccountsPages.LockedAccountsPageFactory;
import pageFactories.Admin.LockedAccountsPages.UnlockAccountPageFactory;
import testCases.BaseTestScriptConfig;
import utilities.AutomationHelper;

/**
 * Test script to perform the lock out procedure for a given user. More
 * specifically, we user an administrator account to lock a specific account
 * out, and assert that the user exist in the "lock out" table. After locked
 * out, we attempt to log in as the user who was locked to assert that the error
 * message appears. Finally, we unlock the account and assert that we can log in
 * successfully.
 * 
 * @author jesse.childress
 *
 */
public class LockedAccountsLockAndUnlock extends BaseTestScriptConfig {
	
	static String userNameForLock = "lockedAccount@mail.mil";
	static String displayNameForLock = "Locked Account - User";
	
	static String adminUserName = "jesse_DHA_super_user@mail.mil";
	static String adminDisplayName = "Jesse DHA Super User";
	
	static String reasonForLock = "No longer needed";
	
	@Test
	public void execute() {
		
		// (1) Login as user
		LoginMod lm = new LoginMod();
		lm.execute(adminUserName);

		// (2) Navigate to locked accounts
		HomePageFactory homePage = new HomePageFactory();
		homePage.clickLockedAccountsInAdmin();
		
		// (3) Click the "Lock an Account" link
		LockedAccountsPageFactory lockedAcctIndex = new LockedAccountsPageFactory();
		lockedAcctIndex.clickLockAnAccount();
		
		// (4) Select a user from the drop down
		//String to build the selection. e.g. Jesse User (jesse_site_TATRC_user@mail.mil);
		LockAccountPageFactory lockAccount = new LockAccountPageFactory();
		String userText = displayNameForLock + " (" + userNameForLock + ")";
		lockAccount.selectUser(userText);
		
		// (5) Select a lock Reason
		lockAccount.selectLockReason(reasonForLock);
		
		// (6) Click the Lock button
		lockAccount.clickLock();
		
		// Assert that the user was indeed locked
		lockedAcctIndex = new LockedAccountsPageFactory();
		
		final String PRIMARY_COL = "User";
		lockedAcctIndex.gotoTablePageWithRow(lockedAcctIndex.getLockedAccountsTable(), PRIMARY_COL, userNameForLock);
		
		assertEquals(lockedAcctIndex.getLockedAccountsTable().readTableRowValue(PRIMARY_COL, userNameForLock, "Display Name", true), displayNameForLock, "Locked Accounts Table - Display Name");
		assertEquals(lockedAcctIndex.getLockedAccountsTable().readTableRowValue(PRIMARY_COL, userNameForLock, "User", true), userNameForLock, "Locked Accounts Table - User");
		assertEquals(lockedAcctIndex.getLockedAccountsTable().readTableRowValue(PRIMARY_COL, userNameForLock, "Reason", true), reasonForLock, "Locked Accounts Table - Reason");
		assertEquals(lockedAcctIndex.getLockedAccountsTable().readTableRowValue(PRIMARY_COL, userNameForLock, "Locked By", true), adminDisplayName, "Locked Accounts Table - Locked By");
		// We can't check the exact time due to the time being different between the
		// server and our PCs. However, we can check the date. Pull the current
		// date, and then get the date from the table, but strip off the minutes.
		String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("M/d/yyyy"));
		String tableDate = lockedAcctIndex.getLockedAccountsTable().readTableRowValue(PRIMARY_COL, userNameForLock, "Locked On", true);
		tableDate = tableDate.substring(0, tableDate.indexOf(" "));
		assertEquals(tableDate, currentDate , "Locked Accounts Table - Locked On");

		// Log out of the admin account
		lockedAcctIndex.clickLogOffInHelloUser();
		AutomationHelper.finishTest();
		
		// Attempt to login as the user
		lm = new LoginMod();
		lm.execute(userNameForLock);
		
		// Assert that the message "Locked out. This account has been locked out, please contact an admin for assistance" message appears.
		// (close browser due to defect)
		homePage = new HomePageFactory();
		assertEquals(homePage.readPageHeader(), "Locked out.", "Error message Header - Locked Out");
		assertEquals(homePage.readPageTitle(), "This account has been locked out, please contact an admin for assistance.", "Error Message Sub-heading - Account has been locked out");
		AutomationHelper.finishTest();
		
		//Log back in as the original administrator
		lm = new LoginMod();
		lm.execute(adminUserName);
		
		
		//Navigate to the Locked Accounts page.
		homePage = new HomePageFactory();
		homePage.clickLockedAccountsInAdmin();
		
		// Exercise the Search feature to zone in on the account
		lockedAcctIndex = new LockedAccountsPageFactory();
		lockedAcctIndex.setSearchBy(userNameForLock);
		lockedAcctIndex.clickSearch();
		
		// Click the Unlock link 
		lockedAcctIndex.getLockedAccountsTable().clickLinkInRow(userNameForLock, "Unlock");
		
		// On the Unlock Account page, read the user / Reason / Locked By / Locked on data
		UnlockAccountPageFactory unlockAccount = new UnlockAccountPageFactory();
		assertEquals(unlockAccount.readUser(), userText, "Unlock Account - User");
		assertEquals(unlockAccount.readReason(), reasonForLock, "Unlock Account - Reason");
		assertEquals(unlockAccount.readLockedBy(), adminDisplayName, "Unlock Account - Locked By");
		String lockedOn = unlockAccount.readLockedOn();
		lockedOn = lockedOn.substring(0, lockedOn.indexOf(" "));
		assertEquals(lockedOn, currentDate, "Unlock Account - Locked On");
		

		//Click Unlock
		unlockAccount.clickUnlock();
		
		//Assert that the user is no longer in the table.
		lockedAcctIndex = new LockedAccountsPageFactory();
		assertEquals(lockedAcctIndex.getLockedAccountsTable().isRowInTable(displayNameForLock, userNameForLock, reasonForLock, adminDisplayName), false, "Locked Accounts Table - User removed");
		
		//Log out of the Admin
		lockedAcctIndex.clickLogOffInHelloUser();
		AutomationHelper.finishTest();
		
		//Log in successfully after account was unlocked
		lm = new LoginMod();
		lm.execute(userNameForLock);
		
		homePage = new HomePageFactory();
		assertEquals(homePage.readPageHeader(), "JMedSIM", "Home Page after successfull login");
		homePage.clickLogOffInHelloUser();		
		
	}
	
	

}
