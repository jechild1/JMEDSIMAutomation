package testCases;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ArchivedClasses.SitesAndUsersPageFactory;
import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.AdminAuditLogPages.AuditLogPageFactory;
import pageFactories.Admin.AdminMetadataPages.MetadataPageFactory;
import pageFactories.Admin.LockedAccountsPages.LockedAccountsPageFactory;
import pageFactories.Admin.UsersAndRoles.UsersAndRolesPageFactory;
import pageFactories.HelloUserMenuPages.EditUserProfilePageFacory;
import pageFactories.HelloUserMenuPages.ManageAccountPageFactory;
import pageFactories.MyMenuPages.FeedbackPageFactory;
import pageFactories.MyMenuPages.SimulationsPageFactory;
import utilities.AutomationHelper;
import utilities.ExcelDataConfig;
/**
 * This test executes all of the menu options available for Site Admin and Super
 * User roles, and will click on all active pages, asserting that the page loads
 * with the expected page title.
 * 
 * Additionally, it will assert that the expected amount of menu options are
 * present.
 * 
 * @author jesse.childress
 *
 */
public class ValidateMenusForSiteAdminAndSuperUser
		extends
			BaseTestScriptConfig {

	@Test(dataProvider = "siteAdminSiteSuperUserRoles")
	public void test(String userName) {

		Reporter.log("Logged in as user: " + userName, true);

		LoginMod login = new LoginMod();
		login.execute(userName);

		validateMyMenuOptionsSize();
		exerciseMyMenu();

		validateAdminMenuOptionsSize();
		exerciseAdminMenu();

		validateHelloUserMenuOptionsSize();
		exerciseHelloMenu();
	}

	/**
	 * Method to validate that there are no new or missing menu options to show
	 * up for the My Menu section. If the size is not consistent, either high or
	 * low, this method will not assert.
	 */
	private void validateMyMenuOptionsSize() {
		AutomationHelper.printMethodName();

		// The following xpath performs these functions:
		// (1) Finds an href that has a "#" in it that contains My Menu (these
		// are non URL links)
		// (2) Gets the parent
		// (3) Gets all the links that do NOT have an "#", which means they go
		// to a page and are not navigation menus
		List<WebElement> mySubMenus = driver.findElements(By.xpath(
				"//a[@href='#' and contains(text(),'My Menu')]/..//a[not(@href='#')]"));

		// The current My Menu size for a normal user is 4
		Reporter.log("Asserting that the My Menu size is = 4", true);
		Assert.assertEquals(mySubMenus.size(), 4,
				"My Menu does not have correct amount of sub-links");

		Reporter.log("My Menu - Sub Menu Size: " + mySubMenus.size(), true);

		// Print the Menu URL's for reference
		for (int i = 0; i < mySubMenus.size(); i++) {
			Reporter.log("My Menu URL # " + (i + 1) + ":"
					+ mySubMenus.get(i).getAttribute("href"), true);
		}
	}

	/**
	 * Looks through the expected menu options on the <i>My Menu</i> menu.
	 * Selects each menu option and then asserts that the page loads.
	 */
	private void exerciseMyMenu() {
		// HOME PAGE
		HomePageFactory homePage = new HomePageFactory();
		softAsserter.assertEquals(homePage.readPageHeader(), "JMedSIM");

		// SIMULATION
		homePage.clickSimulationInMyMenu();
		SimulationsPageFactory simulationsPage = new SimulationsPageFactory();
		softAsserter.assertEquals(simulationsPage.readPageTitle(),	"Simulations");
		
		//CONTENT (BUG OR NOT?)

		
		//METADATA DICTIONARY
		simulationsPage.clickMetadataDictionaryInMyMenu();
		MetadataPageFactory metadataIndex = new MetadataPageFactory();
		softAsserter.assertEquals(metadataIndex.readPageTitle(), "Metadata Dictionary");
		

		//FEEDBACK
		metadataIndex.clickFeedbackInMyMenu();
		FeedbackPageFactory feedbackPage = new FeedbackPageFactory();
		softAsserter.assertEquals(feedbackPage.readPageTitle(), "Feedback");

		// Leave user at home page to proceed with next menus.
		simulationsPage.clickHomeLogo();
		
		softAsserter.assertAll();
	}

	/**
	 * Method to validate that there are no new or missing menu options to show
	 * up for the Admin Menu section. If the size is not consistent, either high
	 * or low, this method will not assert.
	 */
	private void validateAdminMenuOptionsSize() {
		AutomationHelper.printMethodName();

		// The following xpath performs these functions:
		// (1) Finds an href that has a "#" in it that contains My Menu (these
		// are non URL links)
		// (2) Gets the parent
		// (3) Gets all the links that do NOT have an "#", which means they go
		// to a page and are not navigation menus
		List<WebElement> mySubMenus = driver.findElements(By.xpath(
				"//a[@href='#' and text()='Admin']/..//a[not(@href='#')]"));

		// The current Admin Menu size for a DHA user is 3
		Reporter.log("Asserting that the My Menu size is = 3", true);
		Assert.assertEquals(mySubMenus.size(), 3,
				"Admin Menu does not have correct amount of sub-links");

		Reporter.log("Admin Menu - Sub Menu Size: " + mySubMenus.size(), true);

		// Print the Menu URL's for reference
		for (int i = 0; i < mySubMenus.size(); i++) {
			Reporter.log("My Menu URL # " + (i + 1) + ":"
					+ mySubMenus.get(i).getAttribute("href"), true);
		}
	}

	/**
	 * Looks through the expected menu options on the <i>Admin</i> menu. Selects
	 * each menu option and then asserts that the page loads.
	 */
	private void exerciseAdminMenu() {
		// USERS & ROLES
		HomePageFactory homePage = new HomePageFactory();
		homePage.clickUsersAndRolesInAdmin();
		UsersAndRolesPageFactory userAndRolesPage = new UsersAndRolesPageFactory();
		softAsserter.assertEquals(userAndRolesPage.readPageTitle(),
				"Users & Roles");

		// LOCKED ACCOUNTS
		userAndRolesPage.clickLockedAccountsInAdmin();
		LockedAccountsPageFactory lockedAcctPage = new LockedAccountsPageFactory();
		softAsserter.assertEquals(lockedAcctPage.readPageTitle(),
				"Locked Accounts");

		// USER SITES
		lockedAcctPage.clickUserSitesInAdmin();
		SitesAndUsersPageFactory userSitesPage = new SitesAndUsersPageFactory();
		softAsserter.assertEquals(userSitesPage.readPageTitle(), "User Sites");

		// Leave user at home page to proceed with next menus.
		userSitesPage.clickHomeLogo();
		
		softAsserter.assertAll();

	}

	/**
	 * Method to validate that there are no new or missing menu options to show
	 * up for the Hello User menu section. If the size is not consistent, either
	 * high or low, this method will not assert.
	 */
	private void validateHelloUserMenuOptionsSize() {
		AutomationHelper.printMethodName();

		// The following xpath performs these functions:
		// (1) Finds an href that has a "#" in it that contains Hello (these are
		// non URL links)
		// (2) Gets the parent
		// (3) Gets all the links that do NOT have an "#", which means they go
		// to a page and are not navigation menus
		List<WebElement> mySubMenus = driver.findElements(By.xpath(
				"//a[@href='#' and contains(text(),'Hello')]/..//a[not(@href='#')]"));

		// The current My Menu size for a normal user is 2
		Reporter.log("Asserting that the Hello User Menu size is = 3", true);
		Assert.assertEquals(mySubMenus.size(), 3,
				"Hello User does not have correct amount of sub-links.");

		Reporter.log("Hello User - Sub Menu Size: " + mySubMenus.size(), true);

		// Print the Menu URL's for reference
		for (int i = 0; i < mySubMenus.size(); i++) {
			Reporter.log("Hello User Menu URL # " + (i + 1) + ":"
					+ mySubMenus.get(i).getAttribute("href"), true);
		}
	}

	/**
	 * Looks through the expected menu options on the <i>Hello User</i> menu.
	 * Selects each menu option and then asserts that the page loads.
	 */
	private void exerciseHelloMenu() {
		HomePageFactory homePage = new HomePageFactory();

		// PROFILE
		homePage.clickProfileInHelloUser();
		EditUserProfilePageFacory editProfile = new EditUserProfilePageFacory();
		softAsserter.assertEquals(editProfile.readPageTitle(),
				"Edit Your User Profile");

		// MANAGE ACCOUNT
		editProfile.clickManageAccountInHelloUser();
		ManageAccountPageFactory manageAcct = new ManageAccountPageFactory();
		softAsserter.assertEquals(manageAcct.readPageTitle(), "Manage Account");
		
		softAsserter.assertAll();

	}

	/**
	 * Method to obtain the first Site Admin and Super User role records the
	 * datasheet.
	 * 
	 * @return String[]
	 */
	@DataProvider
	private String[] siteAdminSiteSuperUserRoles() {

		ExcelDataConfig usersFile = getExcelFile("UsersData.xlsx",
				"JMEDUserData");

		List<String> siteAdminSiteSuperUserNames = new ArrayList<String>();

		// Sting[] to store the different types of Site Admin / Site Super User
		// accounts. We will want
		// to test for each of these.
		String[] userRoleTypes = {"Site Admin", "Site Super User"};

		// Get the row count for the data file
		int rowCount = usersFile.getRowCount();

		// Loop through each different roles outlined above.
		for (String currentRoleType : userRoleTypes) {

			// For each user type, loop through the data set until we find the
			// FIRST account in which the TYPE matches the Site Admin / Site
			// Super User type.
			for (int i = 1; i <= rowCount; i++) {

				String currentUserNameInDatasheet = usersFile.getData(i,
						"Type");

				if (currentRoleType.equals(currentUserNameInDatasheet)) {
					siteAdminSiteSuperUserNames.add(usersFile.getData(i, "UserName"));
					break;
				}
			}
		}

		String[] returnArray = siteAdminSiteSuperUserNames.stream().toArray(String[]::new);
		return returnArray;
	}
}
