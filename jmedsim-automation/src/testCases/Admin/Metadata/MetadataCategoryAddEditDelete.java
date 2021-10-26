package testCases.Admin.Metadata;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.AdminMetadataPages.CreateMetadataCategoryPageFactory;
import pageFactories.Admin.AdminMetadataPages.DeleteMetadataCategoryPageFactory;
import pageFactories.Admin.AdminMetadataPages.EditMetadataCategoryPageFactory;
import pageFactories.Admin.AdminMetadataPages.MetadataCategoryDetailsPageFactory;
import pageFactories.Admin.AdminMetadataPages.MetadataCategoryPageFactory;
import testCases.BaseTestScriptConfig;
import utilities.AutomationHelper;
import utilities.ExcelDataConfig;

/**
 * Test Script to exercise Admin > Metadata > Metadata Category features. This
 * script performs the following functions:
 * <ul>
 * <li>Navigates to the Admin > Metadata > Metadata Category
 * <li>Exercises search functionality
 * <li>Views details of existing records
 * <li>Views and asserts the Edit page for an existing record
 * <li>Creates a new Metadata Category record
 * <li>Asserts error messages fire with incorrect data
 * <li>Asserts record was added to the table
 * <li>Exercises Edit functionality and asserts
 * <li>Deletes record.
 * 
 * @author jesse.childress
 *
 */
public class MetadataCategoryAddEditDelete extends BaseTestScriptConfig {
	
	String metadataCategory;
	boolean active;
	
	String tempMetadataCategory;
	boolean tempActive;
	
	static String PRIMARY_COLUMN = "Metadata Category";
	
	@Test (dataProvider = "loginAccounts")
	public void execute(String userName) {
		
		//Login to the application using an admin type account
		LoginMod login = new LoginMod();
		login.execute(userName);
		
		//Navigate to the Admin > Metadata > Metadata Category page
		HomePageFactory homePage = new HomePageFactory();
		homePage.clickMetadataCategoryInAdmin();
		
		//Search for an existing (Dynamic) Metadata Category type record
		MetadataCategoryPageFactory metadataCatIndex = new MetadataCategoryPageFactory();
		retrieveExistingRecord();
		metadataCatIndex.setSearchBy(metadataCategory);
		metadataCatIndex.clickSearch();
		
		//Assert that there is only ONE item in the search results
		assertEquals(metadataCatIndex.getMetadataCategoryTable().getTableRowCount(), 1, "Existing Record Search - Single Value Expected");
		assertEquals(metadataCatIndex.getMetadataCategoryTable().readTableRowValue(PRIMARY_COLUMN, metadataCategory, PRIMARY_COLUMN, true), metadataCategory, "Existing Metadata Category Record Present");
		
		//Clear the search
		metadataCatIndex.clearSearchBy();
		metadataCatIndex.clickSearch();
		
		//Pick a random record from the datasheet and assert details
		retrieveExistingRecord();
		metadataCatIndex.getMetadataCategoryTable().clickLinkInRow(metadataCategory, "Details");
		
		//DETAILS PAGE
		//Assert values are present
		MetadataCategoryDetailsPageFactory metadataCatDetails = new MetadataCategoryDetailsPageFactory();
		assertEquals(metadataCatDetails.readMetadataCategory(), metadataCategory, "Metadata Category Details - Metadata Category");
		assertEquals(metadataCatDetails.isActiveChecked(), active, "Metadata Category Details - Active");
		
		// TODO - BUG 986 has this blocked. Uncomment it later, and delete the work
		// around.
		// metadataCatDetails.clickBackToList();
		// TODO - Work around - Delete Me
		metadataCatDetails.clickMetadataCategoryInAdmin();
		
		//Assert that errors fire
		performErrorValidation();
		
		//Create a new Metadata Category record and validate values
		createNewMetadataCategory();
		
		//Edit he newly created Metadata Category record to a different value
		editNewMetadataCategoryRecord();
		
		// Delete the newly created/edited record
		deleteNewlyCreatedRecord();
		
		//Log out
		metadataCatIndex = new MetadataCategoryPageFactory();
		metadataCatDetails.clickLogOffInHelloUser();
		
		
		
	}
	
	/**
	 * Utility method to delete the newly created record and assert that it can no
	 * longer be found.
	 */
	private void deleteNewlyCreatedRecord() {
		MetadataCategoryPageFactory metadataCatIndex = new MetadataCategoryPageFactory();
		
		metadataCatIndex.getMetadataCategoryTable().clickLinkInRow(tempMetadataCategory, "Delete");
		
		DeleteMetadataCategoryPageFactory deleteMetadataCategory = new DeleteMetadataCategoryPageFactory();
		
		assertEquals(deleteMetadataCategory.readMetadataCategory(), tempMetadataCategory, "Delete Metadata Category - Metadata Category");
		assertEquals(deleteMetadataCategory.isActiveChecked(), tempActive, "Delete Metadata Category - Active");
		
		deleteMetadataCategory.clickDelete();
		
		//Back on Index page
		 metadataCatIndex = new MetadataCategoryPageFactory();
		 assertEquals(metadataCatIndex.getMetadataCategoryTable().isRowInTable(tempMetadataCategory), false, "Metadata Category Table - Temporary Value Deleted");
	}
	
	/**
	 * Utility method to edit a new <i>Metadata Category</i> to a different value and assert
	 * that the edit does in fact commit successfully.
	 */
	private void editNewMetadataCategoryRecord() {
		MetadataCategoryPageFactory metadataCatIndex = new MetadataCategoryPageFactory();
		
		//Navigate to newly created record
		metadataCatIndex.getMetadataCategoryTable().clickLinkInRow(tempMetadataCategory, "Edit");
		
		//Now on the edit page
		EditMetadataCategoryPageFactory editMetadataCatPage = new EditMetadataCategoryPageFactory();
		
		//Update the value of the temporary record, to a new temporary record (random)
		retrieveTemporaryRecord();
		
		editMetadataCatPage.setMetadataCategory(tempMetadataCategory);
		editMetadataCatPage.setActiveCheckbox(tempActive);
		
		//Save the record
		editMetadataCatPage.clickSave();
		
		//Ensure that the new record shows up on in the table
		metadataCatIndex = new MetadataCategoryPageFactory();
		assertEquals(metadataCatIndex.getMetadataCategoryTable().isRowInTable(tempMetadataCategory), true, "Edited Temporary Metadata Category Record");
	}
	
	/**
	 * Utility Method that searches for an temporary record (dynamically gathered
	 * from data sheet) and assigns global variables for a task.
	 */
	private void retrieveTemporaryRecord() {

		ExcelDataConfig contentTypeFile = getExcelFile("JMEDAdminDataMap.xlsx", "MetadataCategoryDataMap");

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

		tempMetadataCategory = contentTypeFile.getData(rowIndex, contentTypeFile.getColumnIndex("Metadata Category"));
		tempActive = Boolean.valueOf(contentTypeFile.getData(rowIndex, contentTypeFile.getColumnIndex("Active")));
	}
	
	/**
	 * Utility method to create a new <i>Metadata Category</i> and validate that it in fact
	 * saves and is committed.
	 */
	private void createNewMetadataCategory() {

		// First, navigate to the Create page
		MetadataCategoryPageFactory metadataCatIndex = new MetadataCategoryPageFactory();
		metadataCatIndex.clickCreateMetadataCategory();

		CreateMetadataCategoryPageFactory createMetadataCat = new CreateMetadataCategoryPageFactory();
		
		//Obtain a random record that is TEMPORARY from the datasheet
		retrieveTemporaryRecord();
		
		//Set the new fields with the temporary data
		createMetadataCat.setMetadataCategory(tempMetadataCategory);
		createMetadataCat.setActiveCheckbox(tempActive);
		
		//Click Create
		createMetadataCat.clickCreate();
		
		//Back on the index page
		metadataCatIndex = new MetadataCategoryPageFactory();
		
		//Assert that the new field is in the table
		assertEquals(metadataCatIndex.getMetadataCategoryTable().isRowInTable(tempMetadataCategory), true, "New Temporary Record added to table");
		metadataCatIndex.getMetadataCategoryTable().clickLinkInRow(tempMetadataCategory, "Edit");
		
		//Ensure data is present after a save
		EditMetadataCategoryPageFactory editMetadataCategory = new EditMetadataCategoryPageFactory();
		assertEquals(editMetadataCategory.readMetadataCategory(), tempMetadataCategory, "Edit Metadata Category Page - Metadata Category field");
		assertEquals(editMetadataCategory.isActiveChecked(), tempActive, "Edit Metadata Category Page - Active field");
		editMetadataCategory.clickBackToList();	
	}
	
	/**
	 * Utility method to handle checking for errors when incorrect data is entered
	 * 
	 */
	private void performErrorValidation() {
		
		// First, navigate to the Create page
		MetadataCategoryPageFactory metadataCatIndex = new MetadataCategoryPageFactory();
		metadataCatIndex.clickCreateMetadataCategory();
		
		CreateMetadataCategoryPageFactory createMetadata = new CreateMetadataCategoryPageFactory();

		/*
		 * Error Validation
		 */
		
		// Set the datasheet up in one place to prevent instantiating it multiple places
		ExcelDataConfig errorMessagesDatasheet = getExcelFile("JMEDContentMatrix.xlsx", "StaticTextValidations");

		//MAX CHARACTER TEST
		String fiftyOneChars = "abc def ghi jkl mno pqrs tuv wxyz ABC DEF GHI JKL M";
		createMetadata.clearMetadataCategory();
		createMetadata.setMetadataCategory(fiftyOneChars);
		createMetadata.clickCreate();
		
		String tooManyChars = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATEMETADATACAT_max_chars"), "Content");
		assertEquals(createMetadata.readMetadataCategoryErrorMessage(), tooManyChars, "Errors - Metadata Category - Too Many Characters");
		
		// EMPTY RECORD TEST
		createMetadata.clearMetadataCategory();
		createMetadata.clickCreate();

		String metadataCatRequired = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATEMETADATACAT_metadata_category_required"), "Content");
		assertEquals(createMetadata.readMetadataCategoryErrorMessage(), metadataCatRequired, "Errors - Metadata Category Required");

		// DUPLICATE RECORD TEST
		// Pull a random record from the datasheet
		retrieveExistingRecord();
		createMetadata.clearMetadataCategory();
		createMetadata.setMetadataCategory(metadataCategory);
		createMetadata.clickCreate();
		String metadataCategoryAlreadyExists = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATEMETADATACAT_metadata_category_already_exists"), "Content");
		assertEquals(createMetadata.readMetadataCategoryErrorMessage(), metadataCategoryAlreadyExists, "Errors - Metadata Category Already Exists");
		
		//Navigate back to the Index page after error validation
		createMetadata.clickBackToList();
	}
	
	
	/**
	 * Utility Method that searches for an existing record (dynamically gathered
	 * from data sheet) and assigns global variables for a task.
	 */
	private void retrieveExistingRecord() {

		ExcelDataConfig contentTypeFile = getExcelFile("JMEDAdminDataMap.xlsx", "MetadataCategoryDataMap");

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

		metadataCategory = contentTypeFile.getData(rowIndex, contentTypeFile.getColumnIndex("Metadata Category"));
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
