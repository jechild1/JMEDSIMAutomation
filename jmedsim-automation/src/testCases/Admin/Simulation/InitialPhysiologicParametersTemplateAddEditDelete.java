package testCases.Admin.Simulation;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.AdminSimulationPages.CreateNewInitialPhysiologicParametersTemplatePageFactory;
import pageFactories.Admin.AdminSimulationPages.DeleteInitialPhysiologicParametersTemplatePageFactory;
import pageFactories.Admin.AdminSimulationPages.EditInitialPhysiologicParametersTemplatePageFactory;
import pageFactories.Admin.AdminSimulationPages.InitialPhysiologicParametersTemplateDetailsPageFactory;
import pageFactories.Admin.AdminSimulationPages.InitialPhysiologicParametersTemplatePageFactory;
import testCases.BaseTestScriptConfig;
import utilities.AutomationHelper;
import utilities.ExcelDataConfig;

/**
 * Test Script to exercise Admin > Simulation > Simulator Software Version/OS.
 * This script performs the following functions:
 * <ul>
 * <li>Navigates to the Admin > Simulation > Initial Physiologic Parameters Template page
 * <li>Views details of existing record
 * <li>Views and asserts the Edit page for an existing record
 * <li>Views and asserts the details page for an existing record
 * <li>
 * <li>Asserts error messages fire with incorrect data
 * <li>Asserts record was added to the table
 * <li>Exercises Edit functionality and asserts
 * <li>Deletes record.
 * 
 * @author jesse.childress
 *
 */
public class InitialPhysiologicParametersTemplateAddEditDelete extends BaseTestScriptConfig {
	
	ExcelDataConfig dataFile = getExcelFile("JMEDAdminDataMap.xlsx", "InitialPhysiologicParamTemplate");
	String templateName = dataFile.getData(dataFile.getRowIndex("TestDataID", "InitialPhyParTemplate1"), dataFile.getColumnIndex("TemplateName"));
	String actualFileName = dataFile.getData(dataFile.getRowIndex("TestDataID", "InitialPhyParTemplate1"), dataFile.getColumnIndex("ActualFileName"));
	String description = dataFile.getData(dataFile.getRowIndex("TestDataID", "InitialPhyParTemplate1"), dataFile.getColumnIndex("Description"));
	boolean active = Boolean.valueOf(dataFile.getData(dataFile.getRowIndex("TestDataID", "InitialPhyParTemplate1"), dataFile.getColumnIndex("Active")));
	String contentType = dataFile.getData(dataFile.getRowIndex("TestDataID", "InitialPhyParTemplate1"), dataFile.getColumnIndex("ContentType"));
	
	//Temporary values for editing
	String tempTestDataID = "InitialPhyParTemplate2";
	String tempTemplateName = dataFile.getData(dataFile.getRowIndex("TestDataID", tempTestDataID), dataFile.getColumnIndex("TemplateName"));
	String tempDescription = dataFile.getData(dataFile.getRowIndex("TestDataID", tempTestDataID), dataFile.getColumnIndex("Description"));
	boolean tempActive = Boolean.valueOf(dataFile.getData(dataFile.getRowIndex("TestDataID", tempTestDataID), dataFile.getColumnIndex("Active")));
	
	public static final String PRIMARY_COLUMN = "Template Name";

	
	@Test(dataProvider = "loginAccounts")
	public void execute(String userName) {

		// Login to the application using an admin type account
		LoginMod login = new LoginMod();
		login.execute(userName);
		
		//Navigate to the Admin > Simulation > Initial Physiologic Parameters Template
		HomePageFactory home = new HomePageFactory();
		home.clickInitialPhysiologicParametersTemplateInAdmin();
		
		//Now on the Initial Physiologic Parameters Template page
		InitialPhysiologicParametersTemplatePageFactory ipptIndex = new InitialPhysiologicParametersTemplatePageFactory();
		
		//If there is NOT a record, perhaps due to a failed prior test, then add it here.
		if(ipptIndex.getInitialPhysiologicParametersTemplateTable().getTableRowsForVisibleTable().size() == 0) {
			ipptIndex.clickCreateInitialPhysiologicParametersTemplate();
			addIPPTRecord();
		}
		
		//Refresh reference to Index
		ipptIndex = new InitialPhysiologicParametersTemplatePageFactory();
		
		
		//Assert that there is only ONE record in the table. Only one record should be allowed.
		assertEquals(ipptIndex.getInitialPhysiologicParametersTemplateTable().getTableRowCount(), 1, "Initial Physiologic Parameters Template - Single Row Count");
		
		//Assert the record exists on the page
		ipptIndex.getInitialPhysiologicParametersTemplateTable().clickLinkInRow(templateName, "Details");
		
		
		
		
		//Now on the Initial Physiologic Parameters Template Details page
		InitialPhysiologicParametersTemplateDetailsPageFactory ipptDetails = new InitialPhysiologicParametersTemplateDetailsPageFactory();
		assertEquals(ipptDetails.readTemplateName(), templateName, "Initial Physiologic Parameters Template - Details Page - Template Name");
		assertEquals(ipptDetails.readActualFileName(), actualFileName, "Initial Physiologic Parameters Template - Details Page - Actual File Name");
		assertEquals(ipptDetails.readDescription(), description, "Initial Physiologic Parameters Template - Details Page - Description");
		assertEquals(ipptDetails.isActiveChecked(), active, "Initial Physiologic Parameters Template - Details Page - Active");
		assertEquals(ipptDetails.readContentType(), contentType, "Initial Physiologic Parameters Template - Details Page - Content Type");
		
		//Click Edit on the Initial Physiologic Parameters Template Details
		ipptDetails.clickEdit();
		EditInitialPhysiologicParametersTemplatePageFactory ipptEdit = new EditInitialPhysiologicParametersTemplatePageFactory();
		//Ensure the values are present on the Edit page
		assertEquals(ipptEdit.readTemplateFileName(), templateName, "Initial physiologic Parameters - Edit Page - Template File Name");
		assertEquals(ipptEdit.readActualFileName(), actualFileName, "Initial physiologic Parameters - Edit Page - Actual File Name");
		assertEquals(ipptEdit.readDescription(), description, "Initial physiologic Parameters - Edit Page - Description");
		assertEquals(ipptEdit.isActiveChecked(), active, "Initial physiologic Parameters - Edit Page - Active");
		
		//Perform an edit of the fields
		ipptEdit.setTemplateFileName(tempTemplateName);
		ipptEdit.setDescription(tempDescription);
		ipptEdit.setActiveCheckbox(tempActive);
		
		ipptEdit.clickSave();
		
		//Assert that the new verbiage is back on the index page
		ipptIndex = new InitialPhysiologicParametersTemplatePageFactory();
		assertEquals(ipptIndex.getInitialPhysiologicParametersTemplateTable().readTableRowValue(PRIMARY_COLUMN, tempTemplateName, PRIMARY_COLUMN, true), tempTemplateName, "Initial Physiologic Parameters - Index Page - Table Value");
		
		//Click Delete on the new template
		ipptIndex.getInitialPhysiologicParametersTemplateTable().clickLinkInRow(tempTemplateName, "Delete");
		
		//On the Delete Page now
		DeleteInitialPhysiologicParametersTemplatePageFactory deleteippt = new DeleteInitialPhysiologicParametersTemplatePageFactory();
		assertEquals(deleteippt.readTemplateName(), tempTemplateName, "Initial Physiologic Parameters Template - Delete Page - Template Name");
		assertEquals(deleteippt.readActualFileName(), actualFileName, "Initial Physiologic Parameters Template - Delete Page - Actual File Name");
		assertEquals(deleteippt.readDescription(), tempDescription, "Initial Physiologic Parameters Template - Delete Page - Description");
		assertEquals(deleteippt.isActiveChecked(), tempActive, "Initial Physiologic Parameters Template - Delete Page - Active");
		assertEquals(deleteippt.readContentType(), contentType, "Initial Physiologic Parameters Template - Delete Page - Content Type");
		
		//Delete the record
		deleteippt.clickDelete();
		
		//Back on the index page
		ipptIndex = new InitialPhysiologicParametersTemplatePageFactory();

		//Create a new record (with the same data)
		ipptIndex.clickCreateInitialPhysiologicParametersTemplate();
		
		CreateNewInitialPhysiologicParametersTemplatePageFactory createIppt = new CreateNewInitialPhysiologicParametersTemplatePageFactory();
		
		//Perform Error checking prior to creating a new record
		String oneHundredOneChars = "The quick, brown fox jumps over a lazy dog. DJs flock by when MTV ax quiz prog. Junk MTV quiz graced.";
		String twoHundredFiftyOneChars = "The quick, brown fox jumps over a lazy dog. DJs flock by when MTV ax quiz prog. Junk MTV quiz graced by fox whelps. Bawds jog, flick quartz, vex nymphs. Waltz, bad nymph, for quick jigs vex! Fox nymphs grab quick-jived waltz. Brick quiz whangs jumpy v";
		
		ExcelDataConfig errorMessagesDatasheet = getExcelFile("JMEDContentMatrix.xlsx", "StaticTextValidations");
		String tooManyCharsTempName = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATEINITIALPHYSIOLOGICPARAM_template_name_max_chars"), errorMessagesDatasheet.getColumnIndex("Content"));
		String tooMayCharsDescription = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATEINITIALPHYSIOLOGICPARAM_description_max_chars"), errorMessagesDatasheet.getColumnIndex("Content"));
		String templateNameRequired = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATEINITIALPHYSIOLOGICPARAM_template_name_required"), errorMessagesDatasheet.getColumnIndex("Content"));
		String descriptionRequired = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATEINITIALPHYSIOLOGICPARAM_description_required"), errorMessagesDatasheet.getColumnIndex("Content"));
		String fileRequired = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATEINITIALPHYSIOLOGICPARAM_file_required"), errorMessagesDatasheet.getColumnIndex("Content"));

		
		//Set too many characters
		createIppt.setTemplateName(oneHundredOneChars);
		createIppt.setDescription("Temp");
		AutomationHelper.waitSeconds(3);
		createIppt.clickCreate();
		
		//Check for the errors
		assertEquals(createIppt.readTemplateFileNameErrorMessage(), tooManyCharsTempName, "Initial Physiologic Parameters Templage - Create Page - Template Name - Too Many Chars");
		
		//Set too many characters
		createIppt.setTemplateName("Temp");
		createIppt.setDescription(twoHundredFiftyOneChars);
		createIppt.clickCreate();
				
		//Check for the errors
		assertEquals(createIppt.readDescriptionErrorMessage(), tooMayCharsDescription, "Initial Physiologic Parameters Templage - Create Page - Description - Too Many Chars");
		
		//Clear the text
		createIppt.clearTemplateFileName();
		createIppt.clearDescription();
		
		//Attempt to create an empty record
		createIppt.clickCreate();

		//Assert the errors
		assertEquals(createIppt.readTemplateFileNameErrorMessage(), templateNameRequired, "Initial Physiologic Parameters Templage - Create Page - Template Name - Required");
		assertEquals(createIppt.readDescriptionErrorMessage(), descriptionRequired, "Initial Physiologic Parameters Templage - Create Page - Description - Required");
	
		//Clear the text
		createIppt.clearTemplateFileName();
		createIppt.clearDescription();
		
		//Ensure that the file is required
		createIppt.setTemplateName(templateName);
		createIppt.setDescription(description);
		createIppt.setActiveCheckbox(active);
		createIppt.clickCreate();
		assertEquals(createIppt.readSelectedFileErrorMessage(), fileRequired, "Initial Physiologic Parameters Templage - Create Page - File Name - Required");

		//Clear the text
		createIppt.clearTemplateFileName();
		createIppt.clearDescription();
		
		//Set the fields with legitimate values.
		addIPPTRecord();
//		createIppt.setTemplateName(templateName);
//		String filePath =  System.getProperty("user.dir") + "\\dataSets\\localDataSets\\testFiles\\Initial_Phy_Parameters.pdf";
//		createIppt.selectFile(filePath);
//		createIppt.setDescription(description);
//		createIppt.setActiveCheckbox(active);
//		createIppt.clickCreate();
		
		//Back on the Initial Physiologic Parameters Template Page
		//Ensure that the new file is indeed added to the table
		ipptIndex = new InitialPhysiologicParametersTemplatePageFactory();
		assertEquals(ipptIndex.getInitialPhysiologicParametersTemplateTable().isRowInTable(templateName, description), true, "Initial Physiologic Parameters Template - Index Page - New record added");
				
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
	
	/**
	 * Utility method to add a new IPPT record.
	 */
	private void addIPPTRecord() {
		
		CreateNewInitialPhysiologicParametersTemplatePageFactory createIppt = new CreateNewInitialPhysiologicParametersTemplatePageFactory();
		//Set the fields with legitimate values.
		createIppt.setTemplateName(templateName);
		String filePath =  System.getProperty("user.dir") + "\\dataSets\\localDataSets\\testFiles\\Initial_Phy_Parameters.pdf";
		createIppt.selectFile(filePath);
		createIppt.setDescription(description);
		createIppt.setActiveCheckbox(active);
		createIppt.clickCreate();
	}
}


