package testCases;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.AdminAuditLogPages.AuditLogPageFactory;
import pageFactories.Admin.AdminContentPages.ContentPageFactory;
import pageFactories.Admin.AdminContentPages.ContentTypePageFactory;
import pageFactories.Admin.AdminMetadataPages.MetadataCategoryPageFactory;
import pageFactories.Admin.AdminMetadataPages.MetadataDetailsPageFactory;
import pageFactories.Admin.AdminMetadataPages.MetadataPageFactory;
import pageFactories.Admin.AdminNotifications.NotificationRecipientRolesPageFactory;
import pageFactories.Admin.AdminNotifications.NotificationRecipientsPageFactory;
import pageFactories.Admin.AdminSimulationPages.InitialPhysiologicParametersTemplatePageFactory;
import pageFactories.Admin.AdminSimulationPages.LearnerProfilePageFactory;
import pageFactories.Admin.AdminSimulationPages.NodeConnectorPageFactory;
import pageFactories.Admin.AdminSimulationPages.NodeTypePageFactory;
import pageFactories.Admin.AdminSimulationPages.PersonnelPageFactory;
import pageFactories.Admin.AdminSimulationPages.ServiceRolePageFactory;
import pageFactories.Admin.AdminSimulationPages.SimulatorProgrammingPlatformPageFactory;
import pageFactories.Admin.AdminSimulationPages.SimulatorSoftwareVersionOSPageFactory;
import pageFactories.Admin.AdminSimulationPages.TrainingTypePageFactory;
import pageFactories.Admin.AdminSimulatorPages.SimulatorCategoryPageFactory;
import pageFactories.Admin.AdminSimulatorPages.SimulatorPageFactory;
import pageFactories.Admin.EquipmentCategoryPages.EquipmentCategoryPageFactory;
import pageFactories.Admin.LockedAccountsPages.LockedAccountsPageFactory;
import pageFactories.Admin.LogVerbosityPages.LogVerbosityPageFactory;
import pageFactories.Admin.SitePOCPages.SitePOCPageFactory;
import pageFactories.Admin.SitesPages.SitesPageFactory;
import pageFactories.Admin.UsersAndRoles.UsersAndRolesPageFactory;
import pageFactories.HelloUserMenuPages.EditUserProfilePageFacory;
import pageFactories.HelloUserMenuPages.ManageAccountPageFactory;
import pageFactories.MyMenuPages.FeedbackPageFactory;
import pageFactories.MyMenuPages.SimulationsPageFactory;
import utilities.AutomationHelper;
import utilities.ExcelDataConfig;

/**
 * This test executes all of the menu options available for DHA System Admin and
 * DHA Super User roles, and will click on all active pages, asserting that the
 * page loads with the expected page title.
 * 
 * Additionally, it will assert that the expected amount of menu options are
 * present.
 * 
 * @author jesse.childress
 *
 */
public class ValidateMenusForDHAUsers extends BaseTestScriptConfig {

	@Test(dataProvider = "dhaUserRoles")
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
		Assert.assertEquals(mySubMenus.size(), 4, "My Menu does not have correct amount of sub-links");

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

		// The current Admin Menu size for a DHA user is 24
		Reporter.log("Asserting that the My Menu size is = 24", true);
		Assert.assertEquals(mySubMenus.size(), 24,
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
		
		// SITE POC
		lockedAcctPage.clickSitePOCInAdmin();
		SitePOCPageFactory sitePOCPage = new SitePOCPageFactory();
		softAsserter.assertEquals(sitePOCPage.readPageTitle(), "Site POC");	

		// SITES
		sitePOCPage.clickSitesInAdmin();
		SitesPageFactory sitesPage = new SitesPageFactory();
		softAsserter.assertEquals(sitesPage.readPageTitle(), "Sites");

		// EQUIPMENT CATEGORY
		sitesPage.clickEquipmentCategoryInAdmin();
		EquipmentCategoryPageFactory equipmentCategoryPage = new EquipmentCategoryPageFactory();
		softAsserter.assertEquals(equipmentCategoryPage.readPageTitle(),
				"Equipment Category");


		// SIMULATION > SERVICE ROLE
		equipmentCategoryPage.clickServiceRoleInAdmin();
		ServiceRolePageFactory serviceRolePage = new ServiceRolePageFactory();
		softAsserter.assertEquals(serviceRolePage.readPageTitle(),
				"Service Role");

		// SIMULATION > TRAINING TYPE
		serviceRolePage.clickTrainingTypeInAdmin();
		TrainingTypePageFactory trainingTypePage = new TrainingTypePageFactory();
		softAsserter.assertEquals(trainingTypePage.readPageTitle(),
				"Training Type");

		// SIMULATION > LEARNER PROFILE
		trainingTypePage.clickLearnerProfileInAdmin();
		LearnerProfilePageFactory learnerProfilePage = new LearnerProfilePageFactory();
		softAsserter.assertEquals(learnerProfilePage.readPageTitle(),
				"Learner Profile");

		// SIMULATION > PERSONNEL
		learnerProfilePage.clickPersonnelInAdmin();
		PersonnelPageFactory personnelPage = new PersonnelPageFactory();
		softAsserter.assertEquals(personnelPage.readPageTitle(), "Personnel");
		
		//SIMULATION > SIMULATION PROGRAMMING PLATFORM
		personnelPage.clickSimulatorProgrammingPlatformInAdmin();
		SimulatorProgrammingPlatformPageFactory simulatorPlatform = new SimulatorProgrammingPlatformPageFactory();
		softAsserter.assertEquals(simulatorPlatform.readPageTitle(), "Simulator Programming Platform");
		
		//SIMULATION > SIMULATION SOFTWARE VERSION/OS
		simulatorPlatform.clickSimulatorSoftwareVersionsOSInAdmin();
		SimulatorSoftwareVersionOSPageFactory simulatorSoftwareVerOS = new SimulatorSoftwareVersionOSPageFactory();
		softAsserter.assertEquals(simulatorSoftwareVerOS.readPageTitle(), "Simulator Software Version/OS");
		
		//SIMULATION > INITIAL PHYSIOLOGIC PARAMETERS TEMPLATE
		simulatorSoftwareVerOS.clickInitialPhysiologicParametersTemplateInAdmin();
		InitialPhysiologicParametersTemplatePageFactory initialPhysiologic = new InitialPhysiologicParametersTemplatePageFactory();
		softAsserter.assertEquals(initialPhysiologic.readPageTitle(), "Initial Physiologic Parameters Template");
		
		//SIMULATION > NODE TYPE
		initialPhysiologic.clickNodeTypeInAdmin();
		NodeTypePageFactory nodeType = new NodeTypePageFactory();
		softAsserter.assertEquals(nodeType.readPageTitle(), "Node Type");
		
		//SIMULATION > NODE CONNECTOR
		nodeType.clickNodeConnectorInAdmin();
		NodeConnectorPageFactory nodeConnector = new NodeConnectorPageFactory();
		softAsserter.assertEquals(nodeConnector.readPageTitle(),  "Node Connector");
		

		
		// CONTENT > Content
		nodeConnector.clickContentInAdmin();
		ContentPageFactory contentPage = new ContentPageFactory();
		softAsserter.assertEquals(contentPage.readPageTitle(), "Content");

		// CONTENT > Content Type
		contentPage.clickContentTypeInAdmin();
		ContentTypePageFactory contentTypePage = new ContentTypePageFactory();
		softAsserter.assertEquals(contentTypePage.readPageTitle(),
				"Content Type");
		
		

		// METADATA > Metadata Dictionary
		contentTypePage.clickMetadataDictionaryInAdmin();
		MetadataPageFactory metadataPage = new MetadataPageFactory();
		softAsserter.assertEquals(metadataPage.readPageTitle(), "Metadata");

		// METADATA > Metadata Category
		metadataPage.clickMetadataCategoryInAdmin();
		MetadataCategoryPageFactory metadataCategoryPage = new MetadataCategoryPageFactory();
		softAsserter.assertEquals(metadataCategoryPage.readPageTitle(),
				"Metadata Category");

		// SIMULATOR > Simulator
		metadataCategoryPage.clickSimulatorInAdmin();
		SimulatorPageFactory simulatorPage = new SimulatorPageFactory();
		softAsserter.assertEquals(simulatorPage.readPageTitle(), "Simulator");

		// SIMULATOR > Simulator Category
		simulatorPage.clickSimulatorCategoryInAdmin();
		SimulatorCategoryPageFactory simulatorCategoryPage = new SimulatorCategoryPageFactory();
		softAsserter.assertEquals(simulatorCategoryPage.readPageTitle(),
				"Simulator Category");
		
		
		//NOTIFICATIONS > Notification Recipient
		simulatorCategoryPage.clickNotificationRecipientsInAdmin();
		NotificationRecipientsPageFactory notificationRecipientPage = new NotificationRecipientsPageFactory();
		softAsserter.assertEquals(notificationRecipientPage.readPageTitle(), "Notification Recipients");
				
		//NOTIFICATIONS > Notifications Recipient Roles
		notificationRecipientPage.clickNotificationRecipientRolesInAdmin();
		NotificationRecipientRolesPageFactory notificationRecipientRolesPage = new NotificationRecipientRolesPageFactory();
		softAsserter.assertEquals(notificationRecipientRolesPage.readPageTitle(), "Notification Recipient Roles");
		
		
		//LOG VERBOSITY
		notificationRecipientRolesPage.clickLogVerbosityInAdmin();
		LogVerbosityPageFactory logVerbosityPage = new LogVerbosityPageFactory();
		softAsserter.assertEquals(logVerbosityPage.readPageTitle(), "Log Verbosity");


		// AUDIT LOG
		logVerbosityPage.clickAuditLogInAdmin();
		AuditLogPageFactory auditLogPage = new AuditLogPageFactory();
		softAsserter.assertEquals(auditLogPage.readPageTitle(), "Index");

		// Leave user at home page to proceed with next menus.
		auditLogPage.clickHomeLogo();
		
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
	 * Private utility method to obtain the first DHA type users from the
	 * datasheet.
	 * 
	 * @return String[]
	 */
	@DataProvider
	private String[] dhaUserRoles() {

		ExcelDataConfig usersFile = getExcelFile("UsersData.xlsx",
				"JMEDUserData");

		List<String> adminUserNames = new ArrayList<String>();

		// Sting[] to store the different types of admin accounts. We will want
		// to test for each of these.
		String[] adminTypes = {"DHA System Admin", "DHA Super User"};

		// String[] adminTypes = {"DHA System Admin"};

		// Get the row count for the data file
		int rowCount = usersFile.getRowCount();

		// Loop through each different admin role outlined above.
		for (String currentAdminType : adminTypes) {

			// For each user type, loop through the data set until we find the
			// FIRST account in which the TYPE matches the admin type.
			for (int i = 1; i <= rowCount; i++) {

				String currentUserNameInDatasheet = usersFile.getData(i,
						"Type");

				if (currentAdminType.equals(currentUserNameInDatasheet)) {
					adminUserNames.add(usersFile.getData(i, "UserName"));
					break;
				}
			}
		}

		String[] returnArray = adminUserNames.stream().toArray(String[]::new);
		return returnArray;
	}

}
