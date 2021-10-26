package testCases;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.JMEDSimTables;
import pageFactories.Admin.SitesPages.SitesPageFactory;
import utilities.AutomationHelper;
import utilities.ExcelDataConfig;

public class TableCleanupUtilitiy extends BaseTestScriptConfig{
	
	@Test (dataProvider = "loginAccounts")
	public void execute(String userName) {

		// Login to the application using an admin type account
		LoginMod lm= new LoginMod();
		lm.execute(userName);
		
		HomePageFactory homePage = new HomePageFactory();

		JMEDSimTables table ;

		// SITES
		homePage.clickSitesInAdmin();
		SitesPageFactory sitesPage = new SitesPageFactory();
		table = sitesPage.getSitesTable();
		searchTableForTemps(table);
		

//		// EQUIPMENT CATEGORY
//		sitesPage.clickEquipmentCategoryInAdmin();
//		EquipmentCategoryPageFactory equipmentCategoryPage = new EquipmentCategoryPageFactory();
//		softAsserter.assertEquals(equipmentCategoryPage.readPageTitle(),
//				"Equipment Category");
//
//
//		// SIMULATION > SERVICE ROLE
//		equipmentCategoryPage.clickServiceRoleInAdmin();
//		ServiceRolePageFactory serviceRolePage = new ServiceRolePageFactory();
//		softAsserter.assertEquals(serviceRolePage.readPageTitle(),
//				"Service Role");
//
//		// SIMULATION > TRAINING TYPE
//		serviceRolePage.clickTrainingTypeInAdmin();
//		TrainingTypePageFactory trainingTypePage = new TrainingTypePageFactory();
//		softAsserter.assertEquals(trainingTypePage.readPageTitle(),
//				"Training Type");
//
//		// SIMULATION > LEARNER PROFILE
//		trainingTypePage.clickLearnerProfileInAdmin();
//		LearnerProfilePageFactory learnerProfilePage = new LearnerProfilePageFactory();
//		softAsserter.assertEquals(learnerProfilePage.readPageTitle(),
//				"Learner Profile");
//
//		// SIMNUALTION > Personnel
//		learnerProfilePage.clickPersonnelInAdmin();
//		PersonnelPageFactory personnelPage = new PersonnelPageFactory();
//		softAsserter.assertEquals(personnelPage.readPageTitle(), "Personnel");
//		
//		//SIMULATION > SIMULATION PLATFORM
//		personnelPage.clickSimulatorProgrammingPlatformInAdmin();
//		SimulatorProgrammingPlatformPageFactory simulatorPlatform = new SimulatorProgrammingPlatformPageFactory();
//		softAsserter.assertEquals(simulatorPlatform.readPageTitle(), "Simulator Programming Platform");
//		
//		//SIMULATION > SIMULATION SOFTWARE VERSION/OS
//		simulatorPlatform.clickSimulatorSoftwareVersionsOSInAdmin();
//		SimulatorSoftwareVersionOSPageFactory simulatorSoftwareVerOS = new SimulatorSoftwareVersionOSPageFactory();
//		softAsserter.assertEquals(simulatorSoftwareVerOS.readPageTitle(), "Simulator Software Version/OS");
//		
//		//SIMULATION > INITIAL PHYSIOLOGIC PARAMETERS TEMPLATE
//		simulatorSoftwareVerOS.clickInitialPhysiologicParametersTemplateInAdmin();
//		InitialPhysiologicParametersTemplatePageFactory initialPhysiologic = new InitialPhysiologicParametersTemplatePageFactory();
//		softAsserter.assertEquals(initialPhysiologic.readPageTitle(), "Initial Physiologic Parameters Template");
//		
//		//SIMULATION > NODE TYPE
//		initialPhysiologic.clickNodeTypeInAdmin();
//		NodeTypePageFactory nodeType = new NodeTypePageFactory();
//		softAsserter.assertEquals(nodeType.readPageTitle(), "Node Type");
//		
//		//SIMULATION > NODE CONNECTOR
//		nodeType.clickNodeConnectorInAdmin();
//		NodeConnectorPageFactory nodeConnector = new NodeConnectorPageFactory();
//		softAsserter.assertEquals(nodeConnector.readPageTitle(),  "Node Connector");
//		
//
//		
//		// CONTENT > Content
//		nodeConnector.clickContentInAdmin();
//		ContentPageFactory contentPage = new ContentPageFactory();
//		softAsserter.assertEquals(contentPage.readPageTitle(), "Content");
//
//		// CONTENT > Content Type
//		contentPage.clickContentTypeInAdmin();
//		ContentTypePageFactory contentTypePage = new ContentTypePageFactory();
//		softAsserter.assertEquals(contentTypePage.readPageTitle(),
//				"Content Type");
//
//		// METADATA > Metadata
//		contentTypePage.clickMetadataInAdmin();
//		MetadataPageFactory metadataPage = new MetadataPageFactory();
//		softAsserter.assertEquals(metadataPage.readPageTitle(), "Metadata");
//
//		// METADATA > Metadata Category
//		metadataPage.clickMetadataCategoryInAdmin();
//		MetadataCategoryPageFactory metadataCategoryPage = new MetadataCategoryPageFactory();
//		softAsserter.assertEquals(metadataCategoryPage.readPageTitle(),
//				"Metadata Category");
//
//		// SIMULATOR > Simulator
//		metadataCategoryPage.clickSimulatorInAdmin();
//		SimulatorPageFactory simulatorPage = new SimulatorPageFactory();
//		softAsserter.assertEquals(simulatorPage.readPageTitle(), "Simulator");
//
//		// SIMULATOR > Simulator Category
//		simulatorPage.clickSimulatorCategoryInAdmin();
//		SimulatorCategoryPageFactory simulatorCategoryPage = new SimulatorCategoryPageFactory();
//		softAsserter.assertEquals(simulatorCategoryPage.readPageTitle(),
//				"Simulator Category");
//		
//		//NOTIFICATIONS > Notification Recipient
//		simulatorCategoryPage.clickNotificationRecipientsInAdmin();
//		NotificationRecipientsPageFactory notificationRecipientPage = new NotificationRecipientsPageFactory();
//		softAsserter.assertEquals(notificationRecipientPage.readPageTitle(), "Notification Recipients");
//				
//		//NOTIFICATIONS > Notifications Recipient Roles
//		notificationRecipientPage.clickNotificationRecipientRolesInAdmin();
//		NotificationRecipientRolesPageFactory notificationRecipientRolesPage = new NotificationRecipientRolesPageFactory();
//		softAsserter.assertEquals(notificationRecipientRolesPage.readPageTitle(), "Notification Recipient Roles");
//		
//		
//		//LOG VERBOSITY
//		notificationRecipientRolesPage.clickLogVerbosityInAdmin();
//		LogVerbosityPageFactory logVerbosityPage = new LogVerbosityPageFactory();
//		softAsserter.assertEquals(logVerbosityPage.readPageTitle(), "Log Verbosity");
//
//
//		// AUDIT LOG
//		logVerbosityPage.clickAuditLogInAdmin();
//		AuditLogPageFactory auditLogPage = new AuditLogPageFactory();
//		softAsserter.assertEquals(auditLogPage.readPageTitle(), "Index");
//
//		// Leave user at home page to proceed with next menus.
//		auditLogPage.clickHomeLogo();
//		
//		softAsserter.assertAll();
	}
	
	private void searchTableForTemps(JMEDSimTables table) {
		
		table.deleteRowInTable("TEMP_");
		
		
	}
	
	/**
	 * Returns a random user of type DHA Super User or DHA System Admin from the
	 * UsersData.xlsx
	 * 
	 * @return String[]
	 */
	@DataProvider
	private String[] loginAccounts() {

		ExcelDataConfig usersFile = getExcelFile("UsersData.xlsx", "JMEDUserData");

		List<String> adminUserNames = new ArrayList<String>();

		// Sting[] to store the different types of admin accounts. We will want
		// to test for each of these.
		String[] adminTypes = { "DHA Super User", "DHA System Admin" };

		// Get the row count for the data file
		int rowCount = usersFile.getRowCount();

		// Loop through each different admin role outlined above.
		for (String currentAdminType : adminTypes) {

			// For each user type, loop through the data set until we find the
			// accounts in which the TYPE matches the admin type.
			for (int i = 1; i <= rowCount; i++) {

				String currentUserNameInDatasheet = usersFile.getData(i, "Type");

				if (currentAdminType.equals(currentUserNameInDatasheet)) {
					adminUserNames.add(usersFile.getData(i, "UserName"));
				}
			}
		}

		String[] returnArray = adminUserNames.stream().toArray(String[]::new);
		int arrayCount = returnArray.length;

		// Return a String [] that only contains a random user with a login of
		// DHA Super User
		return new String[] { returnArray[AutomationHelper.generateRandomInteger(0, arrayCount - 1)] };
	}
		
		

}
