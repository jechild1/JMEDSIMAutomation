package testCases.Admin.Content;




import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.AdminContentPages.ContentDetailsPageFactory;
import pageFactories.Admin.AdminContentPages.ContentPageFactory;
import pageFactories.Admin.AdminContentPages.CreateContentPageFactory;
import pageFactories.Admin.AdminContentPages.DeleteContentPageFactory;
import pageFactories.Admin.AdminContentPages.EditContentPageFactory;
import testCases.BaseTestScriptConfig;
import utilities.AutomationHelper;
import utilities.ExcelDataConfig;

/**
 * Test Script to exercise Admin > Simulation > Content features. This
 * script performs the following functions:
 * <ul>
 * <li>Navigates to the Admin > Simulation > Content page
 * <li>Exercises search functionality
 * <li>Views details of existing records
 * <li>Views and asserts the Edit page for an existing record
 * <li>Creates a new Content record
 * <li>Asserts error messages fire with incorrect data
 * <li>Asserts record was added to the table
 * <li>Exercises Edit functionality and asserts
 * <li>Deletes record.
 * 
 * @author jesse.childress
 *
 */
public class ContentAddEditDelete extends BaseTestScriptConfig {
	
	//Class level variables to hold existing data to perform basic checks
	String contentName;
	String contentDescription;
	String metadata;
	boolean active;
	
	String tempContentName;
	String tempContentDescription;
	String tempMedatdata;
	String tempFilePath;
	boolean tempActive;
	
	static String PRIMARY_COLUMN = "Content Name";
	
	@Test (dataProvider = "loginAccounts")
	public void execute(String userName) {

		// Login to the application using an admin type account
		LoginMod lm= new LoginMod();
		lm.execute(userName);
		
		//Navigate to the Admin > Simulation > Content page
		HomePageFactory hp= new HomePageFactory();
		hp.clickContentInAdmin();
		
		//TODO - Blocked by JMED-1084
		
//		//Search for an existing (Dynamic) Content record
		ContentPageFactory contentIndex = new ContentPageFactory();
//		retrieveExistingRecord();
//		contentIndex.setSearchBy(contentName);
//		contentIndex.clickSearch();
//		
//		//Assert that there is only ONE item in the search results
//		assertEquals(contentIndex.getContentTable().getTableRowCount(), 1, "Existing Record Search - Single Value Expected"); 
//		assertEquals(contentIndex.getContentTable().readTableRowValue(PRIMARY_COLUMN, contentName, PRIMARY_COLUMN, true), contentName,	"Existing Content Record Present");
//
//		//Clear the search
//		contentIndex.clearSearchBy();
//		contentIndex.clickSearch();
		
		//Pick a random record from the datasheet and assert details
		retrieveExistingRecord();
		contentIndex.getContentTable().clickLinkInRow(contentName, "Details");
		
		//Details Page
		//Assert values are present
		ContentDetailsPageFactory contentDetails = new ContentDetailsPageFactory();
		assertEquals(contentDetails.readContentName(), contentName, "Content Details - Content Name");
		assertEquals(contentDetails.readDescription(), contentDescription, "Content Details - Content Description");
		assertEquals(contentDetails.readMetadata(), metadata, "Content Details - Metadata");
		assertEquals(contentDetails.isActiveChecked(), active, "Content Details - Active");
		
		// TODO - BUG 986 has this blocked. Uncomment it later, and delete the work
		// around.
		// contentDetails.clickBackToList();
		contentDetails.clickContentInAdmin();
		
		//Assert that errors fire.
		performErrorValidation();
		
		//Create a new Content record and validate values
		createNewcontent();
		
		//Edit the newly created Content record to a different value
		editNewcontentRecord();
		
		// Delete the newly created/edited record
		deleteNewlyCreatedRecord();
		
		// log out
		contentIndex = new ContentPageFactory();
		contentIndex.clickLogOffInHelloUser();
	}
	
	/**
	 * Utility method to delete the newly created record and assert that it can no
	 * longer be found.
	 */
	private void deleteNewlyCreatedRecord() {
		ContentPageFactory contentIndex = new ContentPageFactory();
		
		contentIndex.getContentTable().clickLinkInRow(tempContentName, "Delete");
		
		DeleteContentPageFactory deleteContent = new DeleteContentPageFactory();
		
		assertEquals(deleteContent.readContentName(), tempContentName, "Delete Content - Content Name");
		assertEquals(deleteContent.readContentDescription(), tempContentDescription, "Delete Content - Content Description");
		assertEquals(deleteContent.readMetadata(), tempMedatdata, "Delete Content - Content Metadata");
		//TODO - Add assert for TYPE after functionality stabilizes
		assertEquals(deleteContent.isActiveChecked(), tempActive, "Delete Content - Active");
		
		deleteContent.clickDelete();
		
		//Back on Index page
		 contentIndex = new ContentPageFactory();
		 assertEquals(contentIndex.getContentTable().isRowInTable(tempContentName), false, "Content Table - Temporary Value Deleted");
	}
	
	/**
	 * Utility method to edit a new <i>Content</i> to a different value and assert
	 * that the edit does in fact commit successfully.
	 */
	private void editNewcontentRecord() {
		ContentPageFactory contentIndex = new ContentPageFactory();
		
		//Navigate to newly created record
		contentIndex.getContentTable().clickLinkInRow(tempContentName, "Edit");
		
		//Now on the edit page
		EditContentPageFactory editContent = new EditContentPageFactory();
		
		//Update the value of the temporary Content record, to a new temporary Content record (random)
		retrieveTemporaryRecord();
		
		editContent.setContentName(tempContentName);
		editContent.setContentDescription(tempContentDescription);
		editContent.setMetadata(tempMedatdata);
		editContent.setActiveCheckbox(tempActive);
		
		//Save the record
		editContent.clickSave();
		
		//Ensure that the new record shows up on in the table
		contentIndex = new ContentPageFactory();
		assertEquals(contentIndex.getContentTable().isRowInTable(tempContentName), true, "Edited Temporary Content Record");
	}
	
	/**
	 * Utility Method that searches for an temporary record (dynamically gathered
	 * from data sheet) and assigns global variables for a task.
	 */
	private void retrieveTemporaryRecord() {

		ExcelDataConfig contentFile = getExcelFile("JMEDAdminDataMap.xlsx", "ContentDataMap");

		List<Integer> rowIndexs = new ArrayList<Integer>();
		for (int i = 1; i <= contentFile.getRowCount(); i++) {

			// If the row is a Temporary row, then add it to the list.
			if (contentFile.getData(i, contentFile.getColumnIndex("Type")).equalsIgnoreCase("Temporary")) {
				rowIndexs.add(i);
			}
		}

		// Generate a random number that is between 1 and the upper limit of the
		// temporary records.
		Random rand = new Random();
		int rowIndex = rowIndexs.get(rand.nextInt(rowIndexs.size()));

		tempContentName = contentFile.getData(rowIndex, contentFile.getColumnIndex("ContentName"));
		tempContentDescription = contentFile.getData(rowIndex, contentFile.getColumnIndex("ContentDescription"));
		tempFilePath = System.getProperty("user.dir") + contentFile.getData(rowIndex, contentFile.getColumnIndex("FilePath"));
		tempMedatdata = contentFile.getData(rowIndex, contentFile.getColumnIndex("Metadata"));
		tempActive = Boolean.valueOf(contentFile.getData(rowIndex, contentFile.getColumnIndex("Active")));
	}
	
	/**
	 * Utility method to create a new <i>Content</i> and validate that it in fact
	 * saves and is committed.
	 */
	private void createNewcontent() {

		// First, navigate to the Create page
		ContentPageFactory contentIndex = new ContentPageFactory();
		contentIndex.clickCreateNewContent();

		CreateContentPageFactory createContent = new CreateContentPageFactory();
		
		//Obtain a random record that is TEMPORARY from the datasheet
		retrieveTemporaryRecord();
		
		//Set the new fields with the temporary data
		createContent.setContentName(tempContentName);
		createContent.setContentDescription(tempContentDescription);
		createContent.setSelectFile(tempFilePath);
		createContent.setMetadata(tempMedatdata);
		createContent.setActiveCheckbox(tempActive);
		
		//Click Create
		createContent.clickCreate();
		//Click Save
		createContent.clickSave();
		
		//Back on the index page
		contentIndex = new ContentPageFactory();
		
		//Assert that the new field is in the table
		assertEquals(contentIndex.getContentTable().isRowInTable(tempContentName), true, "New Temporary Record added to table");
		contentIndex.getContentTable().clickLinkInRow(tempContentName, "Edit");
		
		//Ensure data is present after a save
		EditContentPageFactory editcontent = new EditContentPageFactory();
		assertEquals(editcontent.readContentName(), tempContentName, "Edit Content Page - Content Name field");
		assertEquals(editcontent.readContentDescription(), tempContentDescription, "Edit Content Page - Content Description field");
		assertEquals(editcontent.readMetadata(), tempMedatdata, "Edit Content Page - Metadata field");
		assertEquals(editcontent.isActiveChecked(), tempActive, "Edit Content Page - Active field");
		
		editcontent.clickBackToList();	
	}
	
	/**
	 * Utility method to handle checking for errors when incorrect data is entered
	 * 
	 */
	private void performErrorValidation() {
		
		// First, navigate to the Create page
		ContentPageFactory contentIndex = new ContentPageFactory();
		contentIndex.clickCreateNewContent();

		CreateContentPageFactory createContent = new CreateContentPageFactory();

		/*
		 * Error Validation
		 */
		
		// Set the datasheet up in one place to prevent instantiating it multiple places
		ExcelDataConfig errorMessagesDatasheet = getExcelFile("JMEDContentMatrix.xlsx", "StaticTextValidations");

		//MAX CHARACTER TEST
		String fiftyOneChars = "abc def ghi jkl mno pqrs tuv wxyz ABC DEF GHI JKL M";
		String twoHundredFiftyOneChars = "abc def ghi jkl mno pqrs tuv wxyz ABC DEF GHI JKL MNO PQRS TUV WXYZ !\"§ $%& /() =?* '<> #|; ²³~ @`´ ©«» ¤¼× {} abc def ghi jkl mno pqrs tuv wxyz ABC DEF GHI JKL MNO PQRS TUV WXYZ !\"§ $%& /() =?* '<> #|; ²³~ @`´ ©«» ¤¼× {} abc def ghi jkl mno pqrs tuv.";
		createContent.clearContentName();
		createContent.clearContentDescription();
		
		createContent.setContentName(fiftyOneChars);
		createContent.setContentDescription(twoHundredFiftyOneChars);
		
		createContent.clickCreate();
		
		String tooManyChars_Content_Name = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATECONTENT_content_name_max_chars"), "Content");
		assertEquals(createContent.readContentNameErrorMessage(), tooManyChars_Content_Name, "Errors - Content Name - Too Many Characters");
		
		String tooManyChars_Content_Description = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATECONTENT_content_description_max_chars"), "Content");
		assertEquals(createContent.readContentDescriptionErrorMessage(), tooManyChars_Content_Description, "Errors - Content Description - Too Many Characters");
		
		//TODO - Add too many chars for Metadata when JMED-537 is resolved

		
		// EMPTY RECORD TEST
		createContent.clearContentName();
		createContent.clearContentDescription();
		createContent.clickCreate();

		String contentNameRequired = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATECONTENT_content_name_required"), "Content");
		assertEquals(createContent.readContentNameErrorMessage(), contentNameRequired, "Errors - Content Name Required");
		
		String contentDescriptionRequired = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATECONTENT_content_description_required"), "Content");
		assertEquals(createContent.readContentDescriptionErrorMessage(), contentDescriptionRequired, "Errors - Content Description Required");
		
		//To get the file missing error to display, we have to add some text to Content Name and Content Description
		createContent.setContentName("Temporary Content Name");
		createContent.setContentDescription("Temporary Content Description");
		createContent.clickCreate();
		
		String fileRequired = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATECONTENT_file_required"), "Content");
		assertEquals(createContent.readSelectFileErrorMessage(), fileRequired, "Errors - File Required");	
		
	
		
		
		

		// DUPLICATE RECORD TEST
		// Pull a random record from the datasheet
		retrieveExistingRecord();
		createContent.clearContentName();
		createContent.clearContentDescription();
		createContent.setContentName(contentName);
		createContent.clickCreate();
		String contentAlreadyExists = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATECONTENT_content_name_already_exists"), "Content");
		assertEquals(createContent.readContentNameErrorMessage(), contentAlreadyExists, "Errors - Content Name Already Exists");
		
		//Navigate back to the Index page after error validation
		createContent.clickBackToList();
		//TODO - Because of JMED-1092, we have to click back two times
		createContent.clickBackToList();
	}
	
	
	
	/**
	 * Utility Method that searches for an existing record (dynamically gathered
	 * from data sheet) and assigns global variables for a task.
	 */
	private void retrieveExistingRecord() {

		ExcelDataConfig contentFile = getExcelFile("JMEDAdminDataMap.xlsx", "ContentDataMap");

		List<Integer> rowIndexs = new ArrayList<Integer>();
		for (int i = 1; i <= contentFile.getRowCount(); i++) {

			// If the row is a Temporary row, then add it to the list.
			if (contentFile.getData(i, contentFile.getColumnIndex("Type")).equalsIgnoreCase("Permanent")) {
				rowIndexs.add(i);
			}
		}

		// Generate a random number that is between 1 and the upper limit of the
		// temporary records.
		Random rand = new Random();
		int rowIndex = rowIndexs.get(rand.nextInt(rowIndexs.size()));

		contentName = contentFile.getData(rowIndex, contentFile.getColumnIndex("ContentName"));
		contentDescription = contentFile.getData(rowIndex, contentFile.getColumnIndex("ContentDescription"));
		metadata = contentFile.getData(rowIndex, contentFile.getColumnIndex("Metadata"));
		active = Boolean.valueOf(contentFile.getData(rowIndex, contentFile.getColumnIndex("Active")));
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











