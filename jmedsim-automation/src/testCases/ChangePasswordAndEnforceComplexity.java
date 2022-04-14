package testCases;

import static org.testng.Assert.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import javax.print.MultiDocPrintJob;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.LoginPageFactory;
import pageFactories.HelloUserMenuPages.ChangePasswordPageFactory;
import pageFactories.HelloUserMenuPages.ManageAccountPageFactory;
import utilities.AutomationHelper;
import utilities.ExcelDataConfig;
import utilities.PasswordUtils;

/**
 * This test will validate password rules are applied and that a password can be
 * successfully changed for a user. It will perform the following functions:
 * <ul>
 * <li>Attempt NO data entered at all
 * <li>Attempt NO current password
 * <li>Attempt No new password
 * <li>Attempt both no new and no current password
 * <li>Attempt bad password confirmation
 * <li>Attempt a password that's too short
 * <li>Attempt a password with no lower case
 * <li>Attempt a password with no upper case
 * <li>Attempt a password with no numbers
 * <li>Attempt a password with no special characters
 * <li>Change the password to a valid password
 * <li>Login with the new password
 * <li>Change password to the original
 * <li>Login with the original to ensure it works as before.
 * 
 * @author jesse.childress
 *
 */
public class ChangePasswordAndEnforceComplexity extends BaseTestScriptConfig   {
	
	public static String newPassword = PasswordUtils.generateRandomPassword(15);
	public static String confirmNewPassword = newPassword;
	
	public static String originalUserName;
	public static String originalPassword;
	
	
	@Test(priority=0, dataProvider="loginCredentials")
	public void loginAndNavigateToChangePassword(String userName) {
		
		//Prior to logging in successfully, capture the last login date so that it is not overwritten again.
		ExcelDataConfig usersFile = getExcelFile("UsersData.xlsx", "JMEDUserData");

		String lastLoginDateString = usersFile.getData(usersFile.getRowIndex("UserName", userName), "LastLoginDate");

		
		
		//Logs in with the specified user and lands on the home page.
		LoginMod lm = new LoginMod();
		lm.execute(userName);
		
		
		//Print user name and password in console for record
		originalUserName = userName;
		originalPassword = usersFile.getData(usersFile.getRowIndex("UserName", userName), usersFile.getColumnIndex("Password"));
		Reporter.log("Current Username: " + originalUserName, true);
		Reporter.log("Current Password: " + originalPassword, true);
		Reporter.log("New Proposed Password: " + newPassword, true);
		
		//Navigate to the manage account page
		HomePageFactory home = new HomePageFactory();
		home.clickManageAccountInHelloUser();
		
		//Navigate to the Change Password page
		ManageAccountPageFactory manageAcct = new ManageAccountPageFactory();
		manageAcct.clickChangeYourPassword();
		
		//Create a reference to the Error Messages datasheet
		ExcelDataConfig errorFile = getExcelFile("JMEDContentMatrix.xlsx", "StaticTextValidations");

		//Click the Change Password button to produce the errors.
		ChangePasswordPageFactory changePassword = new ChangePasswordPageFactory();
		
		//Store the Column Index for Content column so that code can look cleaner.
		int contentColIndex = errorFile.getColumnIndex("Content");
		
		//***Attempt NO data entered at all***
		changePassword.clickChangePassword();
		Reporter.log("Checking dual messages:", true );
		Reporter.log("The current password field is required.", true);
		Reporter.log("The New password field is required.", true);
		//Pause a moment to ensure error has time to fire
		AutomationHelper.waitSeconds(1);
		assertEquals(changePassword.readSummaryErrors(), errorFile.getData(errorFile.getRowIndex("TestDataID", "HMMACP_BothFieldsRequired"), contentColIndex), "Current password field is required & New password field is required");
		
		//***Attempt NO current password***
		changePassword.setNewPassword(newPassword);
		changePassword.setConfirmNewPassword(confirmNewPassword);
		changePassword.clickChangePassword();
		Reporter.log("Checking Current password field is required.", true);
		//Pause a moment to ensure error has time to fire
		AutomationHelper.waitSeconds(1);
		assertEquals(changePassword.readSummaryErrors(), errorFile.getData(errorFile.getRowIndex("TestDataID", "HMMACP_CurrentPasswordRequired"), contentColIndex), "Current password field is required");

		//***Attempt No new password***
		changePassword.clearNewPassword();
		changePassword.clearConfirmNewPassword();
		changePassword.setCurrentPassword(originalPassword);
		changePassword.clickChangePassword();
		Reporter.log("Checking New password field is required.", true);
		//Pause a moment to ensure error has time to fire
		AutomationHelper.waitSeconds(1);
		assertEquals(changePassword.readSummaryErrors(), errorFile.getData(errorFile.getRowIndex("TestDataID", "HMMACP_NewPasswordRequired"), contentColIndex), "New password field is required");

		//***Attempt both no new and no current password***
		changePassword.clearCurrentPassword();
		changePassword.setConfirmNewPassword(confirmNewPassword);
		changePassword.clickChangePassword();
		Reporter.log("Checking both no new and no current password.", true);
		//Pause a moment to ensure error has time to fire
		AutomationHelper.waitSeconds(1);
		assertEquals(changePassword.readSummaryErrors(), errorFile.getData(errorFile.getRowIndex("TestDataID", "HMMACP_CurrentAndNewPasswordRequired"), contentColIndex), "Current Password & New Password required, New and confirmation passwords don't match");

		//***Attempt bad password confirmation***
		changePassword.clearConfirmNewPassword();
		changePassword.setCurrentPassword(originalPassword);
		changePassword.setNewPassword(newPassword);
		changePassword.setConfirmNewPassword("ThisPasswordWillNeverMatch!@12");
		changePassword.clickChangePassword();
		Reporter.log("Checking incorrect password confirmation", true);
		//Pause a moment to ensure error has time to fire
		AutomationHelper.waitSeconds(1);
		assertEquals(changePassword.readSummaryErrors(), errorFile.getData(errorFile.getRowIndex("TestDataID", "HMMACP_NewAndConfirmationPasswordsNotMatch"), contentColIndex), "New Password and Confirmation passwords don't match");

		
		//***Attempt a password that's too short***
		changePassword.clearCurrentPassword();
		changePassword.clearNewPassword();
		changePassword.setCurrentPassword(originalPassword);
		changePassword.setNewPassword("!TooShort01");
		changePassword.setConfirmNewPassword("!TooShort01");
		changePassword.clickChangePassword();
		Reporter.log("Checking password length too short", true);
		//Pause a moment to ensure error has time to fire
		AutomationHelper.waitSeconds(1);
		assertEquals(changePassword.readSummaryErrors(), errorFile.getData(errorFile.getRowIndex("TestDataID", "HMMACP_PasswordTooShort"), contentColIndex), "New Password too short.");

		
		//TODO
		/*
		 * Because a password cannot be attempted more than one time every 24 hours, we
		 * must check the last time we attempted the password with this user name. If we
		 * tried a password change within 24 hours, we must skip over the remainder of
		 * the test.
		 */
		
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy/MM/dd HH.mm.ss", Locale.US);
		
		LocalDateTime lastLoginDate = LocalDateTime.parse(lastLoginDateString, df);
		
		//Add 24 hours to the last login date
		lastLoginDate = lastLoginDate.plusHours(24);
		
		//If right now is after the last login date + 24 hours, we can proceed with the remainder of the test.
		if(LocalDateTime.now().isAfter(lastLoginDate)) {
			
				
		//***Attempt a password with no lower case***
		changePassword.clearCurrentPassword();
		changePassword.clearNewPassword();
		changePassword.clearConfirmNewPassword();
		changePassword.setCurrentPassword(originalPassword);
		changePassword.setNewPassword("!LONGENOUGHBUTNOLOWERCASE77");
		changePassword.setConfirmNewPassword("!LONGENOUGHBUTNOLOWERCASE77");
		changePassword.clickChangePassword();
		Reporter.log("Checking password with no lower case.", true);
		//Pause a moment to ensure error has time to fire
		AutomationHelper.waitSeconds(1);
		assertEquals(changePassword.readSummaryErrors(), errorFile.getData(errorFile.getRowIndex("TestDataID", "HMMACP_PasswordNoLowercase"), contentColIndex), "New password needs lower case.");

		//***Attempt a password with no upper case***
		changePassword.clearCurrentPassword();
		changePassword.clearNewPassword();
		changePassword.clearConfirmNewPassword();
		changePassword.setCurrentPassword(originalPassword);
		changePassword.setNewPassword("!longenoughbutnouppercase77");
		changePassword.setConfirmNewPassword("!longenoughbutnouppercase77");
		changePassword.clickChangePassword();
		Reporter.log("Checking password with no upper case.", true);
		//Pause a moment to ensure error has time to fire
		AutomationHelper.waitSeconds(1);
		assertEquals(changePassword.readSummaryErrors(), errorFile.getData(errorFile.getRowIndex("TestDataID", "HMMACP_PasswordNoUppercase"), contentColIndex), "New Password needs upper case.");
		
		//***Attempt a password with no numbers***
		changePassword.clearCurrentPassword();
		changePassword.clearNewPassword();
		changePassword.clearConfirmNewPassword();
		changePassword.setCurrentPassword(originalPassword);
		changePassword.setNewPassword("!LongEnoughButNoNumbers");
		changePassword.setConfirmNewPassword("!LongEnoughButNoNumbers");
		changePassword.clickChangePassword();
		Reporter.log("Checking password with no numbers.", true);
		//Pause a moment to ensure error has time to fire
		AutomationHelper.waitSeconds(1);
		assertEquals(changePassword.readSummaryErrors(), errorFile.getData(errorFile.getRowIndex("TestDataID", "HMMACP_PasswordNoNumbers"), contentColIndex), "New Password no numbers.");
		
		//***Attempt a password with no special characters***
		changePassword.clearCurrentPassword();
		changePassword.clearNewPassword();
		changePassword.clearConfirmNewPassword();
		changePassword.setCurrentPassword(originalPassword);
		changePassword.setNewPassword("LongEnoughNoSpecialChars55");
		changePassword.setConfirmNewPassword("LongEnoughNoSpecialChars55");
		changePassword.clickChangePassword();
		Reporter.log("Checking password with no special characters.", true);
		//Pause a moment to ensure error has time to fire
		AutomationHelper.waitSeconds(1);
		assertEquals(changePassword.readSummaryErrors(), errorFile.getData(errorFile.getRowIndex("TestDataID", "HMMACP_PasswordNoSpecialCharacters"), contentColIndex), "New Password no special characters.");
		
	
		//Set the new password
		changePassword.clearCurrentPassword();
		changePassword.clearNewPassword();
		changePassword.clearConfirmNewPassword();
		
		changePassword.setCurrentPassword(originalPassword);
		changePassword.setNewPassword(newPassword);
		changePassword.setConfirmNewPassword(confirmNewPassword);
		
		changePassword.clickChangePassword();
		
		//Validate Success
		ManageAccountPageFactory manageAccount = new ManageAccountPageFactory();
		assertEquals(manageAccount.readSuccessLabel(), errorFile.getData(errorFile.getRowIndex("TestDataID", "HMMACP_PasswordChangeSuccess"), contentColIndex), "Password changed successfully.");
		
		//Write new values to datasheet
		String previousPassword1 = usersFile.getData(usersFile.getRowIndex("UserName", userName), usersFile.getColumnIndex("PreviousPassword1"));
	
		//Place Previous Password into PreviousPassword2
		usersFile.writeToWorkSheet(usersFile.getRowIndex("UserName", userName), usersFile.getColumnIndex("PreviousPassword2"), previousPassword1);

		//Place the current password into PreviousPassword1
		usersFile.writeToWorkSheet(usersFile.getRowIndex("UserName", userName), usersFile.getColumnIndex("PreviousPassword1"), originalPassword);

		//Place the new password into the Password field.
		usersFile.writeToWorkSheet(usersFile.getRowIndex("UserName", userName), usersFile.getColumnIndex("Password"), newPassword);

		
		//Log off the application
		manageAcct.clickLogOffInHelloUser();
		
		//***Login with the new password***
		//Wait for the log off message (Redirect after 5 seconds)
		loginWithUpdatedPassword(usersFile, userName, newPassword);
		
	}
	else {
		
		displayPopupNoTestRun();
		
		home = new HomePageFactory();
		home.clickLogOffInHelloUser();
		
	}
		
	}
	
	private void displayPopupNoTestRun() {
		
		JFrame myJframe = new JFrame("JMED Testing");
		myJframe.setSize(400,300);
		JLabel label = new JLabel("The password was reset less than 24 hours ago. Skipping test.");
		myJframe.add(label);
		myJframe.setVisible(true);
		myJframe.setAlwaysOnTop(true);
		
		int secondsToWait = 5;
		while(secondsToWait > 0) {
			
			myJframe.setTitle("Closing in " + secondsToWait + " seconds.");
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			secondsToWait--;
		}
		
		myJframe.setDefaultCloseOperation(myJframe.DISPOSE_ON_CLOSE);
		myJframe.dispose();
		
		
	}
	
	/**
	 * Utility method to login again after password is changed and write last
	 * login date to sheet.
	 * 
	 * @param usersFile
	 * @param userName
	 * @param password
	 */
	private void loginWithUpdatedPassword(ExcelDataConfig usersFile, String userName, String password) {
		
		AutomationHelper.printMethodName();

		LoginPageFactory lpf = new LoginPageFactory();

		lpf.clickOkInDODBanner();

		lpf.setEmail(userName);

		lpf.setPassword(password);

		lpf.clickLogin();

		// if no longer on login page, write date/time logged in and return
		if (!lpf.readPageTitle().toLowerCase().equals("log in")) {
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH.mm.ss");
			Date date = new Date();
			
			int rowIndex = usersFile.getRowIndex("UserName", userName);
			int colIndex = usersFile.getColumnIndex("LastLoginDate");
			usersFile.writeToWorkSheet(rowIndex, colIndex, df.format(date));

			return;
		}
	}
	

	
	/**
	 * Data provider - hard coded user name to handle this script.
	 * @return String[]
	 */
	@DataProvider
	private String[] loginCredentials() {
		return new String[] {"jesse_site_TATRC_readonly_user1@mail.mil"};
	}
}
