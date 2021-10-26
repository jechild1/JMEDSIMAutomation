package testCases.Admin.Simulation;
/**
 * Test Script to exercise Admin > Simulation > Simulator Programming Platform. This script
 * performs the following functions:
 * <ul>
 * <li>Navigates to the Admin > Simulation > Simulator Programming Platform
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

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.AdminSimulationPages.CreateSimulatorProgrammingPlatformPageFactory;
import pageFactories.Admin.AdminSimulationPages.DeleteSimulatorProgrammingPlatformPageFactory;
import pageFactories.Admin.AdminSimulationPages.EditSimulatorProgrammingPlatformPageFactory;
import pageFactories.Admin.AdminSimulationPages.SimulatorProgrammingPlatformDetailsPageFactory;
import pageFactories.Admin.AdminSimulationPages.SimulatorProgrammingPlatformPageFactory;
import testCases.BaseTestScriptConfig;
import utilities.AutomationHelper;
import utilities.ExcelDataConfig;

public class SimulatorProgrammingPlatformAddEditDelete extends BaseTestScriptConfig {

	// Primary Column Value
	static final String PRIMARY_COLUMN = "Platform Name";
	static final String SECONDARY_COLUMN = "Description";

	String platformRecord;
	String description;
	boolean active;

	String tempPlatformRecord;
	String tempDescription;
	boolean tempActive;

	@Test(dataProvider = "loginAccounts")
	public void execute(String userName) 
	{
		//Login to the application using an admin type account
		LoginMod lm = new LoginMod();
		lm.execute(userName);
		
		//Navigate to the Admin > Simulation > Simulator Programming Platform page
		HomePageFactory home = new HomePageFactory();
		home.clickSimulatorProgrammingPlatformInAdmin();
		
		//Search for an existing (dynamic) Simulator Programming Platform record
		SimulatorProgrammingPlatformPageFactory simIndex = new SimulatorProgrammingPlatformPageFactory();
		retrieveExistingRecord();
		simIndex.setSearchBy(platformRecord);
		simIndex.clickSearch();
		//Assert that there is only ONE item in the search results
//		assertEquals(simIndex.getSimulatorPlatformTable().getTableRowCount(), 1, "Existing Record Search - Single value expected.");
		assertEquals(simIndex.getSimulatorPlatformTable().readTableRowValue(PRIMARY_COLUMN, platformRecord, PRIMARY_COLUMN, true), platformRecord, "Simulator Record Found in Table - Platform Name");
		assertEquals(simIndex.getSimulatorPlatformTable().readTableRowValue(PRIMARY_COLUMN, platformRecord, SECONDARY_COLUMN, true), description, "Simulator Record Found in Table - Platform Name");
	
		//Clear the search
		simIndex.clearSearchBy();
		simIndex.clickSearch();
		
		//Pick a random record from the datasheet and assert details
		retrieveExistingRecord();
		simIndex.getSimulatorPlatformTable().clickLinkInRow(platformRecord, "Details");
		SimulatorProgrammingPlatformDetailsPageFactory simDetails = new SimulatorProgrammingPlatformDetailsPageFactory();
		assertEquals(simDetails.readPlatformName(), platformRecord, "Simulator Programming Platform Details - Platform Name");
		assertEquals(simDetails.readDescription(), description, "Simulator Programming Platform Details - Description");
		assertEquals(simDetails.isActiveChecked(), active, "Simulator Programming Platform Details - Existing Active");

		// TODO - BUG 986 has this blocked. Uncomment it later, and delete the work
		// around.
		// simDetails.clickBackToList();
		simDetails.clickSimulatorProgrammingPlatformInAdmin();
		
		//Assert that the errors fire
		performErrorValidation();
		
		//Create a new Simulator Programming Platform
		createNewSimulatorProgrammingPlatform();
		
		//Edit new Simulator Programming Platform
		editNewSimulatorProgrammingPlatform();
		
		//Delete the newly created / edited record
		deleteNewlyCreatedRecord();
		
		
		//Log out
		SimulatorProgrammingPlatformPageFactory simProgPlatIndex = new SimulatorProgrammingPlatformPageFactory();
		simProgPlatIndex.clickLogOffInHelloUser();
	
	}
	
	/**
	 * Utility method to handle checking for errors when incorrect data is entered
	 * 
	 */
	private void performErrorValidation() {
		
		// First, navigate to the Create page
		SimulatorProgrammingPlatformPageFactory simProgPlatformIndex = new SimulatorProgrammingPlatformPageFactory();
		simProgPlatformIndex.clickCreateNewSimulatorPlatform();

		CreateSimulatorProgrammingPlatformPageFactory createSimProgPlatform = new CreateSimulatorProgrammingPlatformPageFactory();
		
		/*
		 * Error Validation
		 */
		
		// Set the datasheet up in one place to prevent instantiating it multiple places
		ExcelDataConfig errorMessagesDatasheet = getExcelFile("JMEDContentMatrix.xlsx", "StaticTextValidations");

		//Too many characters - PLATFORM NAME & DESCRIPTION
		String fiftyOneChars = "abc def ghi jkl mno pqrs tuv wxyz ABC DEF GHI JKL M";
		String oneHundredOneChars = "The quick, brown fox jumps over a lazy dog. DJs flock by when MTV ax quiz prog. Junk MTV quiz graced.";
		
		createSimProgPlatform.setPlatformName(fiftyOneChars);
		createSimProgPlatform.setDescription(oneHundredOneChars);
		createSimProgPlatform.clickCreate();
		
		
		//Too many characters - Datasheet errors
		String tooManyCharsPlatformName = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID",  "CREATENEWSIMPROGPLAT_platform_max_chars"), "Content");
		String tooManyCharsDescription = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID",  "CREATENEWSIMPROGPLAT_description_max_chars"), "Content");

		//Check the page for the error		
		assertEquals(createSimProgPlatform.readPlatformNameErrorMessage(), tooManyCharsPlatformName, "Platform Name Errors - Too Many Characters");
		assertEquals(createSimProgPlatform.readDescriptionErrorMessage(), tooManyCharsDescription, "Description Errors - Too Many Characters");


		// Try to create an empty record
		createSimProgPlatform.clearPlatformName();
		createSimProgPlatform.clearDescription();
		createSimProgPlatform.clickCreate();

		String platformNameRequiredError = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATENEWSIMPROGPLAT_platform_required"), "Content");
		String descriptionRequiredError = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATENEWSIMPROGPLAT_description_required"), "Content");
		
		assertEquals(createSimProgPlatform.readPlatformNameErrorMessage(), platformNameRequiredError, "Platform Name Errors - Platform Name Required");
		assertEquals(createSimProgPlatform.readDescriptionErrorMessage(), descriptionRequiredError, "Description Errors - Description Required");

		// Attempt to create a duplicate
		// Pull a random record from the datasheet
		retrieveExistingRecord();
		
		createSimProgPlatform.clearPlatformName();
		createSimProgPlatform.clearDescription();
		
		createSimProgPlatform.setPlatformName(platformRecord);
		createSimProgPlatform.setDescription(description);
		
		createSimProgPlatform.clickCreate();
		
		String programmingPlatAlreadyExists = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATENEWSIMPROGPLAT_platform_already_exists"), "Content");
		assertEquals(createSimProgPlatform.readPlatformNameErrorMessage(), programmingPlatAlreadyExists, "Errors - Programming Platform Already Exists");
				
		//Navigate back to the Index page after error validation
		createSimProgPlatform.clickBackToList();
	}
	
	/**
	 * Utility method to delete the newly created record and assert that it can no
	 * longer be found.
	 */
	private void deleteNewlyCreatedRecord() {
		SimulatorProgrammingPlatformPageFactory simProgPlatIndex = new SimulatorProgrammingPlatformPageFactory();
		
		simProgPlatIndex.getSimulatorPlatformTable().clickLinkInRow(tempPlatformRecord, "Delete");
		
		DeleteSimulatorProgrammingPlatformPageFactory deleteSimProgPlatform = new DeleteSimulatorProgrammingPlatformPageFactory();
		
		assertEquals(deleteSimProgPlatform.readPlatformName(), tempPlatformRecord, "Delete Simulator Programming Platform - Platform Name");
		assertEquals(deleteSimProgPlatform.readDescription(), tempDescription, "Delete Simulator Programming Platform - Description");
		assertEquals(deleteSimProgPlatform.isActiveChecked(), tempActive, "Delete Simulator Programming Platform - Active");
		
		deleteSimProgPlatform.clickDelete();
		
		//Back on Index page
		simProgPlatIndex = new SimulatorProgrammingPlatformPageFactory();
		 assertEquals(simProgPlatIndex.getSimulatorPlatformTable().isRowInTable(tempPlatformRecord, tempDescription), false, "Simulator Programming Table - Temporary Value Deleted");
	}
	
	/**
	 * Utility method to edit a new personnel record to a different value and assert
	 * that the edit does in fact commit successfully.
	 */
	private void editNewSimulatorProgrammingPlatform() {
		SimulatorProgrammingPlatformPageFactory simulatorProgPlatIndex = new SimulatorProgrammingPlatformPageFactory();
		
		//Navigate to newly created record
		simulatorProgPlatIndex.getSimulatorPlatformTable().clickLinkInRow(tempPlatformRecord, "Edit");
		
		//Now on the edit page
		EditSimulatorProgrammingPlatformPageFactory editSimPlatform = new EditSimulatorProgrammingPlatformPageFactory();
		
		//Update the value of the temporary personnel record, to a new temporary personnel record (random)
		retrieveTemporaryRecord();
		
		editSimPlatform.setPlatformName(tempPlatformRecord);
		editSimPlatform.setDescription(tempDescription);
		editSimPlatform.setActiveCheckbox(tempActive);
		
		//Save the record
		editSimPlatform.clickSave();
		
		//Ensure that the new record shows up on in the table
		simulatorProgPlatIndex = new SimulatorProgrammingPlatformPageFactory();
		assertEquals(simulatorProgPlatIndex.getSimulatorPlatformTable().isRowInTable(tempPlatformRecord, tempDescription), true, "Edited Temporary Simulator Programming Platform Record");
	}
	
	/**
	 * Utility method to create a new Simulator Programming Platform record.
	 */
	private void createNewSimulatorProgrammingPlatform() {
		
		//From the Index page, click Create New Simulator Programming Platform
		SimulatorProgrammingPlatformPageFactory simIndex = new SimulatorProgrammingPlatformPageFactory();
		simIndex.clickCreateNewSimulatorPlatform();
		
		CreateSimulatorProgrammingPlatformPageFactory createNewSimProgPlatform = new CreateSimulatorProgrammingPlatformPageFactory();
		
		//Obtain a random record that is TEMPORARY from the datasheet
		retrieveTemporaryRecord();
		
		//Set the fields with the temporary data
		createNewSimProgPlatform.setPlatformName(tempPlatformRecord);
		createNewSimProgPlatform.setDescription(tempDescription);
		createNewSimProgPlatform.setActiveCheckbox(tempActive);
		
		//Click Create
		createNewSimProgPlatform.clickCreate();
		
		//Back on the index page
		simIndex = new SimulatorProgrammingPlatformPageFactory();
		
		//Assert that the new field is in the table
		assertEquals(simIndex.getSimulatorPlatformTable().isRowInTable(tempPlatformRecord, tempDescription), true, "New Temporary Record added to the table");
		simIndex.getSimulatorPlatformTable().clickLinkInRow(tempPlatformRecord, "Edit");
		
		//Ensure data is present after a save
		EditSimulatorProgrammingPlatformPageFactory editSimProgPlatform = new EditSimulatorProgrammingPlatformPageFactory();
		assertEquals(editSimProgPlatform.readPlatformName(), tempPlatformRecord, "Edit Simulator Programming Platform - Platform Name");
		assertEquals(editSimProgPlatform.readDescription(), tempDescription, "Edit Simulator Programming Platform - Description");
		assertEquals(editSimProgPlatform.isActiveChecked(), tempActive, "Edit Simulator Programming Platform - Active");

		editSimProgPlatform.clickBackToList();		
	}
	
	/**
	 * Utility Method that searches for an temporary record (dynamically gathered
	 * from data sheet) and assigns global variables for a task.
	 */
	private void retrieveTemporaryRecord() {

		ExcelDataConfig simProgPlatformDataFile = getExcelFile("JMEDAdminDataMap.xlsx", "SimulatorProgPlatformDataMap");

		List<Integer> rowIndexs = new ArrayList<Integer>();
		for (int i = 1; i <= simProgPlatformDataFile.getRowCount(); i++) {

			// If the row is a Temporary row, then add it to the list.
			if (simProgPlatformDataFile.getData(i, simProgPlatformDataFile.getColumnIndex("Type")).equalsIgnoreCase("Temporary")) {
				rowIndexs.add(i);
			}
		}

		// Generate a random number that is between 1 and the upper limit of the
		// temporary records.
		Random rand = new Random();
		int rowIndex = rowIndexs.get(rand.nextInt(rowIndexs.size()));

		tempPlatformRecord = simProgPlatformDataFile.getData(rowIndex, simProgPlatformDataFile.getColumnIndex("PlatformName"));
		tempDescription = simProgPlatformDataFile.getData(rowIndex, simProgPlatformDataFile.getColumnIndex("Description"));
		tempActive = Boolean.valueOf(simProgPlatformDataFile.getData(rowIndex, simProgPlatformDataFile.getColumnIndex("Active")));
	}

	
	
	
	
	/**
	 * Utility Method that searches for an existing record (dynamically gathered
	 * from data sheet) and assigns global variables for a task.
	 */
	private void retrieveExistingRecord() {

		ExcelDataConfig personnelFile = getExcelFile("JMEDAdminDataMap.xlsx", "SimulatorProgPlatformDataMap");

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

		platformRecord = personnelFile.getData(rowIndex, personnelFile.getColumnIndex("PlatformName"));
		description = personnelFile.getData(rowIndex, personnelFile.getColumnIndex("Description"));
		active = Boolean.valueOf(personnelFile.getData(rowIndex, personnelFile.getColumnIndex("Active")));
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
