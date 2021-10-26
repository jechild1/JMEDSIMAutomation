package testCases.Admin.Content;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.AdminContentPages.ContentTypeDetailsPageFactory;
import pageFactories.Admin.AdminContentPages.ContentTypePageFactory;
import pageFactories.Admin.AdminContentPages.CreateContentTypePageFactory;
import pageFactories.Admin.AdminContentPages.DeleteContentTypePageFactory;
import pageFactories.Admin.AdminContentPages.EditContentTypePageFactory;
import testCases.BaseTestScriptConfig;
import utilities.AutomationHelper;
import utilities.ExcelDataConfig;

/**
 * Test Script to exercise Admin > Simulation > Content Type features. This
 * script performs the following functions:
 * <ul>
 * <li>Navigates to the Admin > Simulation > Content Type page
 * <li>Exercises search functionality
 * <li>Views details of existing records
 * <li>Views and asserts the Edit page for an existing record
 * <li>Creates a new Content Type record
 * <li>Asserts error messages fire with incorrect data
 * <li>Asserts record was added to the table
 * <li>Exercises Edit functionality and asserts
 * <li>Deletes record.
 * 
 * @author jesse.childress
 *
 */
public class ContentTypeAddEditDelete extends BaseTestScriptConfig {
	
	//Class level variables to hold existing data to perform basic checks
		String contentType;
		boolean active;
		
		String tempContentType;
		boolean tempActive;
		
		static String PRIMARY_COLUMN = "Content Type";
		
		@Test (dataProvider = "loginAccounts")
		public void execute(String userName) {

			// Login to the application using an admin type account
			LoginMod lm= new LoginMod();
			lm.execute(userName);
			
			//Navigate to the Admin > Simulation > Content Type page
			HomePageFactory hp= new HomePageFactory();
			hp.clickContentTypeInAdmin();
			
			//Search for an existing (Dynamic) Content Type record
			ContentTypePageFactory contentTypeIndex = new ContentTypePageFactory();
			retrieveExistingRecord();
			contentTypeIndex.setSearchBy(contentType);
			contentTypeIndex.clickSearch();
			
			//Assert that there is only ONE item in the search results
			assertEquals(contentTypeIndex.getContentTypeTable().getTableRowCount(), 1, "Existing Record Search - Single Value Expected"); 
			assertEquals(contentTypeIndex.getContentTypeTable().readTableRowValue(PRIMARY_COLUMN, contentType, PRIMARY_COLUMN, true), contentType,	"Existing Content Type Record Present");
			
			//Clear the search
			contentTypeIndex.clearSearchBy();
			contentTypeIndex.clickSearch();
			
			//Pick a random record from the datasheet and assert details
			retrieveExistingRecord();
			contentTypeIndex.getContentTypeTable().clickLinkInRow(contentType, "Details");
			
			//Details Page
			//Assert values are present
			ContentTypeDetailsPageFactory contentTypeDetails = new ContentTypeDetailsPageFactory();
			assertEquals(contentTypeDetails.readContentType(), contentType, "Content Type Details - Content Type");
			assertEquals(contentTypeDetails.isActiveChecked(), active, "Content Type Details - Active");
			
			// TODO - BUG 986 has this blocked. Uncomment it later, and delete the work
			// around.
			// contentTypeDetails.clickBackToList();
			// TODO - Work around - Delete Me
			contentTypeDetails.clickContentTypeInAdmin();
			
			//Assert that errors fire.
			performErrorValidation();
			
			//Create a new Content Type record and validate values
			createNewContentType();
			
			//Edit the newly created Content Type record to a different value
			editNewContentTypeRecord();
			
			// Delete the newly created/edited record
			deleteNewlyCreatedRecord();
			
			// log out
			contentTypeIndex = new ContentTypePageFactory();
			contentTypeIndex.clickLogOffInHelloUser();
		}
		
		/**
		 * Utility method to delete the newly created record and assert that it can no
		 * longer be found.
		 */
		private void deleteNewlyCreatedRecord() {
			ContentTypePageFactory contentTypeIndex = new ContentTypePageFactory();
			
			contentTypeIndex.getContentTypeTable().clickLinkInRow(tempContentType, "Delete");
			
			DeleteContentTypePageFactory deleteContentType = new DeleteContentTypePageFactory();
			
			assertEquals(deleteContentType.readContentType(), tempContentType, "Delete Content Type - Content Type");
			assertEquals(deleteContentType.isActiveChecked(), tempActive, "Delete Content Type - Active");
			
			deleteContentType.clickDelete();
			
			//Back on Index page
			 contentTypeIndex = new ContentTypePageFactory();
			 assertEquals(contentTypeIndex.getContentTypeTable().isRowInTable(tempContentType), false, "Content Type Table - Temporary Value Deleted");
		}
		
		/**
		 * Utility method to edit a new <i>Content Type</i> to a different value and assert
		 * that the edit does in fact commit successfully.
		 */
		private void editNewContentTypeRecord() {
			ContentTypePageFactory contentTypeIndex = new ContentTypePageFactory();
			
			//Navigate to newly created record
			contentTypeIndex.getContentTypeTable().clickLinkInRow(tempContentType, "Edit");
			
			//Now on the edit page
			EditContentTypePageFactory editContentType = new EditContentTypePageFactory();
			
			//Update the value of the temporary Content Type record, to a new temporary Content Type record (random)
			retrieveTemporaryRecord();
			
			editContentType.setContentType(tempContentType);
			editContentType.setActiveCheckbox(tempActive);
			
			//Save the record
			editContentType.clickSave();
			
			//Ensure that the new record shows up on in the table
			contentTypeIndex = new ContentTypePageFactory();
			assertEquals(contentTypeIndex.getContentTypeTable().isRowInTable(tempContentType), true, "Edited Temporary Content Type Record");
		}
		
		/**
		 * Utility Method that searches for an temporary record (dynamically gathered
		 * from data sheet) and assigns global variables for a task.
		 */
		private void retrieveTemporaryRecord() {

			ExcelDataConfig contentTypeFile = getExcelFile("JMEDAdminDataMap.xlsx", "ContentTypeDataMap");

			List<Integer> rowIndexs = new ArrayList<Integer>();
			for (int i = 1; i <= contentTypeFile.getRowCount(); i++) {

				// If the row is a Temporary row, then add it to the list.
				if (contentTypeFile.getData(i, contentTypeFile.getColumnIndex("Type")).equalsIgnoreCase("Temporary")) {
					rowIndexs.add(i);
				}
			}

			// Generate a random number that is between 1 and the upper limit of the
			// temporary records.
			Random rand = new Random();
			int rowIndex = rowIndexs.get(rand.nextInt(rowIndexs.size()));

			tempContentType = contentTypeFile.getData(rowIndex, contentTypeFile.getColumnIndex("ContentType"));
			tempActive = Boolean.valueOf(contentTypeFile.getData(rowIndex, contentTypeFile.getColumnIndex("Active")));
		}
		
		/**
		 * Utility method to create a new <i>Content Type</i> and validate that it in fact
		 * saves and is committed.
		 */
		private void createNewContentType() {

			// First, navigate to the Create page
			ContentTypePageFactory contentTypeIndex = new ContentTypePageFactory();
			contentTypeIndex.clickCreateNewContentType();

			CreateContentTypePageFactory createContentType = new CreateContentTypePageFactory();
			
			//Obtain a random record that is TEMPORARY from the datasheet
			retrieveTemporaryRecord();
			
			//Set the new fields with the temporary data
			createContentType.setContentType(tempContentType);
			createContentType.setActiveCheckbox(tempActive);
			
			//Click Create
			createContentType.clickCreate();
			
			//Back on the index page
			contentTypeIndex = new ContentTypePageFactory();
			
			//Assert that the new field is in the table
			assertEquals(contentTypeIndex.getContentTypeTable().isRowInTable(tempContentType), true, "New Temporary Record added to table");
			contentTypeIndex.getContentTypeTable().clickLinkInRow(tempContentType, "Edit");
			
			//Ensure data is present after a save
			EditContentTypePageFactory editContentType = new EditContentTypePageFactory();
			assertEquals(editContentType.readContentType(), tempContentType, "Edit Content Type Page - Content Type field");
			assertEquals(editContentType.isActiveChecked(), tempActive, "Edit Content Type Page - Active field");
			editContentType.clickBackToList();	
		}
		
		/**
		 * Utility method to handle checking for errors when incorrect data is entered
		 * 
		 */
		private void performErrorValidation() {
			
			// First, navigate to the Create page
			ContentTypePageFactory contentTypeIndex = new ContentTypePageFactory();
			contentTypeIndex.clickCreateNewContentType();

			CreateContentTypePageFactory createContentType = new CreateContentTypePageFactory();

			/*
			 * Error Validation
			 */
			
			// Set the datasheet up in one place to prevent instantiating it multiple places
			ExcelDataConfig errorMessagesDatasheet = getExcelFile("JMEDContentMatrix.xlsx", "StaticTextValidations");

			//MAX CHARACTER TEST
			String fiftyOneChars = "abc def ghi jkl mno pqrs tuv wxyz ABC DEF GHI JKL M";
			createContentType.clearContentType();
			createContentType.setContentType(fiftyOneChars);
			createContentType.clickCreate();
			
			String tooManyChars = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATECONTENTTYPE_content_type_max_chars"), "Content");
			assertEquals(createContentType.readContentTypeErrorMessage(), tooManyChars, "Errors - Content Type - Too Many Characters");
			
			// EMPTY RECORD TEST
			createContentType.clearContentType();
			createContentType.clickCreate();

			String contentTypeRequired = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATECONTENTTYPE_content_type_required"), "Content");
			assertEquals(createContentType.readContentTypeErrorMessage(), contentTypeRequired, "Errors - Content Type Required");

			// DUPLICATE RECORD TEST
			// Pull a random record from the datasheet
			retrieveExistingRecord();
			createContentType.clearContentType();
			createContentType.setContentType(contentType);
			createContentType.clickCreate();
			String contentTypeAlreadyExists = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATECONTENTTYPE_content_type_already_exists"), "Content");
			assertEquals(createContentType.readContentTypeErrorMessage(), contentTypeAlreadyExists, "Errors - Content Type Already Exists");
			
			//Navigate back to the Index page after error validation
			createContentType.clickBackToList();
		}
		
		
		
		/**
		 * Utility Method that searches for an existing record (dynamically gathered
		 * from data sheet) and assigns global variables for a task.
		 */
		private void retrieveExistingRecord() {

			ExcelDataConfig contentTypeFile = getExcelFile("JMEDAdminDataMap.xlsx", "ContentTypeDataMap");

			List<Integer> rowIndexs = new ArrayList<Integer>();
			for (int i = 1; i <= contentTypeFile.getRowCount(); i++) {

				// If the row is a Temporary row, then add it to the list.
				if (contentTypeFile.getData(i, contentTypeFile.getColumnIndex("Type")).equalsIgnoreCase("Permanent")) {
					rowIndexs.add(i);
				}
			}

			// Generate a random number that is between 1 and the upper limit of the
			// temporary records.
			Random rand = new Random();
			int rowIndex = rowIndexs.get(rand.nextInt(rowIndexs.size()));

			contentType = contentTypeFile.getData(rowIndex, contentTypeFile.getColumnIndex("ContentType"));
			active = Boolean.valueOf(contentTypeFile.getData(rowIndex, contentTypeFile.getColumnIndex("Active")));
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
