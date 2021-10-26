package testCases.Admin.Simulation;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.AdminSimulationPages.CreatePersonnelPageFactory;
import pageFactories.Admin.AdminSimulationPages.DeletePersonnelPageFactory;
import pageFactories.Admin.AdminSimulationPages.EditPersonnelPageFactory;
import pageFactories.Admin.AdminSimulationPages.PersonnelDetailsPageFactory;
import pageFactories.Admin.AdminSimulationPages.PersonnelPageFactory;
import testCases.BaseTestScriptConfig;
import utilities.AutomationHelper;
import utilities.ExcelDataConfig;

/**
 * Test Script to exercise Admin > Simulation > Personnel features. This script
 * performs the following functions:
 * <ul>
 * <li>Navigates to the Admin > Simulation > Personnel page
 * <li>Exercises search functionality
 * <li>Views details of existing records
 * <li>Views and asserts the Edit page for an existing record
 * <li>Creates a new Personnel record
 * <li>Asserts error messages fire with incorrect data
 * <li>Asserts record was added to the table
 * <li>Exercises Edit functionality and asserts
 * <li>Deletes record.
 * 
 * @author jesse.childress
 *
 */
public class PersonnelAddEditDelete extends BaseTestScriptConfig {
	static final String PRIMARY_COLUMN = "Personnel";
	
	String personnelRecord;
	boolean personnelRecordActive;
	
	String tempPersonnelRecord;
	boolean tempPersonnelRecordActive;
	
	@Test(dataProvider = "loginAccounts")
	public void execute(String userName) {

		// Login to the application using an admin type account
		LoginMod lm = new LoginMod();
		lm.execute(userName);

		// Navigate to the Admin > Simulation > Personnel page
		HomePageFactory home = new HomePageFactory();
		home.clickPersonnelInAdmin();

		// Search for an existing (dynamic) Personnel record
		PersonnelPageFactory personnelIndex = new PersonnelPageFactory();
		retrieveExistingRecord();
		personnelIndex.setSearchBy(personnelRecord);
		personnelIndex.clickSearch();

		// Assert that there is only ONE item in the search results
		assertEquals(personnelIndex.getPersonnelTable().getTableRowCount(), 1, "Existing Record Search - Single Value Expected"); 
		assertEquals(personnelIndex.getPersonnelTable().readTableRowValue(PRIMARY_COLUMN, personnelRecord, "Personnel", true), personnelRecord,
				"Existing Personnel Record Present");

		// Clear the search
		personnelIndex.clearSearchBy();
		personnelIndex.clickSearch();

		// Pick a random record from datasheet and assert details
		retrieveExistingRecord();
		personnelIndex.getPersonnelTable().clickLinkInRow(personnelRecord, "Details");

		PersonnelDetailsPageFactory personnelDetails = new PersonnelDetailsPageFactory();
		assertEquals(personnelDetails.readPersonnel(), personnelRecord, "Personnel Details - Existing Personnel");
		assertEquals(personnelDetails.isActiveChecked(), personnelRecordActive, "Personnel Details - Existing Active");

		// TODO - BUG 986 has this blocked. Uncomment it later, and delete the work
		// around.
		// personnelDetails.clickBackToList();
		// TODO - Work around - Delete Me
		personnelDetails.clickPersonnelInAdmin();
		personnelIndex = new PersonnelPageFactory();

		// Assert errors fire
		performErrorValidation(personnelIndex);

		// Create a new personnel record and validate values
		createNewPersonnelRecord();

		// Edit the new personnel record to a different value
		editNewPersonnelRecord();

		// Delete the newly created/edited record
		deleteNewlyCreatedRecord();

		// log out
		personnelIndex = new PersonnelPageFactory();
		personnelIndex.clickLogOffInHelloUser();

	}

	/**
	 * Utility Method that searches for an existing record (dynamically gathered
	 * from data sheet) and assigns global variables for a task.
	 */
	private void retrieveExistingRecord() {

		ExcelDataConfig personnelFile = getExcelFile("JMEDAdminDataMap.xlsx", "PersonnelDataMap");

		List<Integer> rowIndexs = new ArrayList<Integer>();
		for (int i = 1; i <= personnelFile.getRowCount(); i++) {

			// If the row is a Temporary row, then add it to the list.
			if (personnelFile.getData(i, personnelFile.getColumnIndex("Type")).equalsIgnoreCase("Permanent")) {
				rowIndexs.add(i);
			}
		}

		// Generate a random number that is between 1 and the upper limit of the
		// temporary records.
		Random rand = new Random();
		int rowIndex = rowIndexs.get(rand.nextInt(rowIndexs.size()));

		personnelRecord = personnelFile.getData(rowIndex, personnelFile.getColumnIndex("Personnel"));
		personnelRecordActive = Boolean.valueOf(personnelFile.getData(rowIndex, personnelFile.getColumnIndex("Active")));
	}
	
	/**
	 * Utility Method that searches for an temporary record (dynamically gathered
	 * from data sheet) and assigns global variables for a task.
	 */
	private void retrieveTemporaryRecord() {

		ExcelDataConfig personnelFile = getExcelFile("JMEDAdminDataMap.xlsx", "PersonnelDataMap");

		List<Integer> rowIndexs = new ArrayList<Integer>();
		for (int i = 1; i <= personnelFile.getRowCount(); i++) {

			// If the row is a Temporary row, then add it to the list.
			if (personnelFile.getData(i, personnelFile.getColumnIndex("Type")).equalsIgnoreCase("Temporary")) {
				rowIndexs.add(i);
			}
		}

		// Generate a random number that is between 1 and the upper limit of the
		// temporary records.
		Random rand = new Random();
		int rowIndex = rowIndexs.get(rand.nextInt(rowIndexs.size()));

		tempPersonnelRecord = personnelFile.getData(rowIndex, personnelFile.getColumnIndex("Personnel"));
		tempPersonnelRecordActive = Boolean.valueOf(personnelFile.getData(rowIndex, personnelFile.getColumnIndex("Active")));
	}

	/**
	 * Utility method to handle checking for errors when incorrect data is entered
	 * 
	 * @param personnelIndex
	 */
	private void performErrorValidation(PersonnelPageFactory personnelIndex) {
		
		// First, navigate to the Create page
		personnelIndex.clickCreateNewPersonnel();

		CreatePersonnelPageFactory createPersonnel = new CreatePersonnelPageFactory();

		/*
		 * Error Validation
		 */
		// Set the datasheet up in one place to prevent instantiating it multiple places
		ExcelDataConfig errorMessagesDatasheet = getExcelFile("JMEDContentMatrix.xlsx", "StaticTextValidations");
		String personnelRequired = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATENEWPERSONNEL_personnel_required"), "Content");

		//Too many characters
		String fiftyOneChars = "abc def ghi jkl mno pqrs tuv wxyz ABC DEF GHI JKL M";
		createPersonnel.clearPersonnel();
		createPersonnel.setPersonnel(fiftyOneChars);
		createPersonnel.clickCreate();
		
		String tooManyChars = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID",  "CREATENEWPERSONNEL_max_chars"), "Content");
		assertEquals(createPersonnel.readPersonnelErrorMessage(), tooManyChars, "Errors - Too Many Characters");
		
		
		// Try to create an empty record
		createPersonnel.clearPersonnel();
		createPersonnel.clickCreate();

		assertEquals(createPersonnel.readPersonnelErrorMessage(), personnelRequired, "Errors - Personnel Required");

		// Attempt to create a duplicate
		// Pull a random record from the datasheet
		retrieveExistingRecord();
		createPersonnel.clearPersonnel();
		createPersonnel.setPersonnel(personnelRecord);
		createPersonnel.clickCreate();
		String personnelAlreadyExists = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATENEWPERSONNEL_personnel_already_exists"), "Content");
		assertEquals(createPersonnel.readPersonnelErrorMessage(), personnelAlreadyExists, "Errors - Personnel Already Exists");
		
		
		//Navigate back to the Index page after error validation
		createPersonnel.clickBackToList();
	}

	/**
	 * Utility method to create a new Personnel Record and validate that it in fact
	 * saves and is committed.
	 */
	private void createNewPersonnelRecord() {

		//From the Index page, click Create New Personnel
		PersonnelPageFactory personnelIndex = new PersonnelPageFactory();
		personnelIndex.clickCreateNewPersonnel();
		
		CreatePersonnelPageFactory createPersonnel = new CreatePersonnelPageFactory();
		
		//Obtain a random record that is TEMPORARY from the datasheet
		retrieveTemporaryRecord();
		
		//Set the new fields with the temporary data
		createPersonnel.setPersonnel(tempPersonnelRecord);
		createPersonnel.setActiveCheckbox(tempPersonnelRecordActive);
		
		//Click Create
		createPersonnel.clickCreate();
		
		//Back on the index page
		personnelIndex = new PersonnelPageFactory();
		
		//Assert that the new field is in the table
		assertEquals(personnelIndex.getPersonnelTable().isRowInTable(tempPersonnelRecord), true, "New Temporary Record added to table");
		personnelIndex.getPersonnelTable().clickLinkInRow(tempPersonnelRecord, "Edit");
		
		//Ensure data is present after a save
		EditPersonnelPageFactory editPersonnel = new EditPersonnelPageFactory();
		assertEquals(editPersonnel.readPersonnel(), tempPersonnelRecord, "Edit Personnel - Personnel field");
		assertEquals(editPersonnel.isActiveChecked(), tempPersonnelRecordActive, "Edit Personnel - Active field");
		editPersonnel.clickBackToList();	
	}
	
	/**
	 * Utility method to edit a new personnel record to a different value and assert
	 * that the edit does in fact commit successfully.
	 */
	private void editNewPersonnelRecord() {
		PersonnelPageFactory personnelIndex = new PersonnelPageFactory();
		
		//Navigate to newly created record
		personnelIndex.getPersonnelTable().clickLinkInRow(tempPersonnelRecord, "Edit");
		
		//Now on the edit page
		EditPersonnelPageFactory editPersonnel = new EditPersonnelPageFactory();
		
		//Update the value of the temporary personnel record, to a new temporary personnel record (random)
		retrieveTemporaryRecord();
		
		editPersonnel.setPersonnel(tempPersonnelRecord);
		editPersonnel.setActiveCheckbox(tempPersonnelRecordActive);
		
		//Save the record
		editPersonnel.clickSave();
		
		//Ensure that the new record shows up on in the table
		personnelIndex = new PersonnelPageFactory();
		assertEquals(personnelIndex.getPersonnelTable().isRowInTable(tempPersonnelRecord), true, "Edited Temporary Personnel Record");
	}
	
	/**
	 * Utility method to delete the newly created record and assert that it can no
	 * longer be found.
	 */
	private void deleteNewlyCreatedRecord() {
		PersonnelPageFactory personnelIndex = new PersonnelPageFactory();
		
		personnelIndex.getPersonnelTable().clickLinkInRow(tempPersonnelRecord, "Delete");
		
		DeletePersonnelPageFactory deletePersonnel = new DeletePersonnelPageFactory();
		
		assertEquals(deletePersonnel.readPersonnel(), tempPersonnelRecord, "Delete Personnel - Personnel");
		assertEquals(deletePersonnel.isActiveChecked(), tempPersonnelRecordActive, "Delete Personnel - Active");
		
		deletePersonnel.clickDelete();
		
		//Back on Index page
		 personnelIndex = new PersonnelPageFactory();
		 assertEquals(personnelIndex.getPersonnelTable().isRowInTable(tempPersonnelRecord), false, "Personnel Table - Temporary Value Deleted");
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
