package testCases.Admin.Simulation;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.AdminSimulationPages.CreateSoftwareVersionOSPageFactory;
import pageFactories.Admin.AdminSimulationPages.DeleteSoftwareVersionOSPageFactory;
import pageFactories.Admin.AdminSimulationPages.EditSoftwareVersionOSPageFactory;
import pageFactories.Admin.AdminSimulationPages.SimulatorSoftwareVersionOSDetailsPageFactory;
import pageFactories.Admin.AdminSimulationPages.SimulatorSoftwareVersionOSPageFactory;
import testCases.BaseTestScriptConfig;
import utilities.AutomationHelper;
import utilities.ExcelDataConfig;

/**
 * Test Script to exercise Admin > Simulation > Simulator Software Version/OS.
 * This script performs the following functions:
 * <ul>
 * <li>Navigates to the Admin > Simulation > Simulator Software Version/OS page
 * <li>Exercises search functionality
 * <li>Views details of existing records
 * <li>Views and asserts the Edit page for an existing record
 * <li>Creates a new Simulator Software Version/OS record
 * <li>Asserts error messages fire with incorrect data
 * <li>Asserts record was added to the table
 * <li>Exercises Edit functionality and asserts
 * <li>Deletes record.
 * 
 * @author jesse.childress
 *
 */
public class SimulatorSoftwareVersionOSAddEditDelete extends BaseTestScriptConfig {

	// Primary Column Value
	static final String PRIMARY_COLUMN = "Software Version/OS";
	static final String SECONDARY_COLUMN = "Description";

	String softwareVersionOS;
	String description;
	boolean active;

	String tempSoftwareVersionOS;
	String tempDescription;
	boolean tempActive;

	@Test(dataProvider = "loginAccounts")
	public void execute(String userName) {

		// Login to the application using an admin type account
		LoginMod lm = new LoginMod();
		lm.execute(userName);

		// Navigate to the Admin > Simulation > Simulator Software Version/OS page
		HomePageFactory home = new HomePageFactory();
		home.clickSimulatorSoftwareVersionsOSInAdmin();
		
		//Now on the Index page for Simulator Software Version/OS
		SimulatorSoftwareVersionOSPageFactory simSoftwareOSIndex = new SimulatorSoftwareVersionOSPageFactory();
		
		//Search for an existing (dynamic) Simulator Software Version/OS record
		retrieveExistingRecord();
		simSoftwareOSIndex.setSearchBy(softwareVersionOS);
		simSoftwareOSIndex.clickSearch();
		
		//Assert that the search function only returns ONE row, and that the two values are in teh table
		assertEquals(simSoftwareOSIndex.getSimulatorSoftwareVersionOSTable().getTableRowCount(), 1, "Existing Record Search - Single Value Expected");
		assertEquals(simSoftwareOSIndex.getSimulatorSoftwareVersionOSTable().readTableRowValue(PRIMARY_COLUMN, softwareVersionOS, PRIMARY_COLUMN, true), softwareVersionOS, "Simulator Software Version/OS Record Found in table - Software Version/OS");
		assertEquals(simSoftwareOSIndex.getSimulatorSoftwareVersionOSTable().readTableRowValue(PRIMARY_COLUMN, softwareVersionOS, SECONDARY_COLUMN, true ), description, "Description Record Found in table - Description");

		//Clear the search
		simSoftwareOSIndex.clearSearchBy();
		simSoftwareOSIndex.clickSearch();
		
		//Pick a random record from teh datasheet and assert the details
		retrieveExistingRecord();
		simSoftwareOSIndex.getSimulatorSoftwareVersionOSTable().clickLinkInRow(softwareVersionOS, "Details");
		SimulatorSoftwareVersionOSDetailsPageFactory simSoftwareOSDetails = new SimulatorSoftwareVersionOSDetailsPageFactory();
		assertEquals(simSoftwareOSDetails.readSoftwareVersionOS(), softwareVersionOS, "Simulator Software Version/OS - Software Version/OS");
		assertEquals(simSoftwareOSDetails.readDescription(), description, "Simulator Software Version/OS - Description");
		assertEquals(simSoftwareOSDetails.isActiveChecked(), active, "Simulator Software Version/OS - Existing Active");
		
		// TODO - BUG 986 has this blocked. Uncomment it later, and delete the work
		// around.
		// simSoftwareOSDetails.clickBackToList();
		simSoftwareOSDetails.clickSimulatorSoftwareVersionsOSInAdmin();
		
		//Assert that the errors fire
		performErrorValidation();
		
		//Create a new Software Version/OS Record
		createSoftwareVersionOS();
		
		//Edit new Software Version/OS Record
		editSoftwareVersionOS();
		
		//Delete the newly created / edited record
		deleteNewlyCreatedRecord();
		

	}
	
	/**
	 * Utility method to delete the newly created record and assert that it can no
	 * longer be found.
	 */
	private void deleteNewlyCreatedRecord() {
		SimulatorSoftwareVersionOSPageFactory simSWVerOsIndex = new SimulatorSoftwareVersionOSPageFactory();
		
		simSWVerOsIndex.getSimulatorSoftwareVersionOSTable().clickLinkInRow(tempSoftwareVersionOS, "Delete");
		
		DeleteSoftwareVersionOSPageFactory deleteSWVerOs = new DeleteSoftwareVersionOSPageFactory();
		
		assertEquals(deleteSWVerOs.readSoftwareVersionOS(), tempSoftwareVersionOS, "Delete Software Version/OS- Software Version/OS");
		assertEquals(deleteSWVerOs.readDescription(), tempDescription, "Delete Software Version/OS - Description");
		assertEquals(deleteSWVerOs.isActiveChecked(), tempActive, "Delete Software Version/OS - Active");
		
		deleteSWVerOs.clickDelete();
		
		//Back on Index page
		simSWVerOsIndex = new SimulatorSoftwareVersionOSPageFactory();
		 assertEquals(simSWVerOsIndex.getSimulatorSoftwareVersionOSTable().isRowInTable(tempSoftwareVersionOS, tempDescription), false, "Simulator Programming Table - Temporary Value Deleted");
	}
	
	/**
	 * Utility method to edit a new Software Version/OS record to a different value and assert
	 * that the edit does in fact commit successfully.
	 */
	private void editSoftwareVersionOS() {
		SimulatorSoftwareVersionOSPageFactory softwareVerOsIndex = new SimulatorSoftwareVersionOSPageFactory();
		
		//Navigate to newly created record
		softwareVerOsIndex.getSimulatorSoftwareVersionOSTable().clickLinkInRow(tempSoftwareVersionOS, "Edit");
		
		//Now on the edit page
		EditSoftwareVersionOSPageFactory editSWVerOs = new EditSoftwareVersionOSPageFactory();
		
		//Update the value of the temporary personnel record, to a new temporary personnel record (random)
		retrieveTemporaryRecord();
		
		editSWVerOs.setSoftwareVersionOS(tempSoftwareVersionOS);
		editSWVerOs.setDescription(tempDescription);
		editSWVerOs.setActiveCheckbox(tempActive);
		
		//Save the record
		editSWVerOs.clickSave();
		
		//Ensure that the new record shows up on in the table
		softwareVerOsIndex = new SimulatorSoftwareVersionOSPageFactory();
		assertEquals(softwareVerOsIndex.getSimulatorSoftwareVersionOSTable().isRowInTable(tempSoftwareVersionOS, tempDescription), true, "Edited Temporary Software Version/OS Record");
	}
	
	/**
	 * Utility method to create a new Software Version/OS record.
	 */
	private void createSoftwareVersionOS() {
		
		//From the Index page, click Create New Simulator Software Version OS
		SimulatorSoftwareVersionOSPageFactory simSWVerOsIndex = new SimulatorSoftwareVersionOSPageFactory();
		simSWVerOsIndex.clickCreateNewSimulatorSoftwareVersionOS();
		
		CreateSoftwareVersionOSPageFactory createSoftwareVerOS = new CreateSoftwareVersionOSPageFactory();
		
		//Obtain a random record that is TEMPORARY from the datasheet
		retrieveTemporaryRecord();
		
		//Set the fields with the temporary data
		createSoftwareVerOS.setSoftwareVersionOS(tempSoftwareVersionOS);
		createSoftwareVerOS.setDescription(tempDescription);
		createSoftwareVerOS.setActiveCheckbox(tempActive);
		
		//Click Create
		createSoftwareVerOS.clickCreate();
		
		//Back on the index page
		AutomationHelper.waitForPageToLoad(10);
		simSWVerOsIndex = new SimulatorSoftwareVersionOSPageFactory();
		
		//Assert that the new field is in the table
		assertEquals(simSWVerOsIndex.getSimulatorSoftwareVersionOSTable().isRowInTable(tempSoftwareVersionOS, tempDescription), true, "New Temporary Record added to the table");
		simSWVerOsIndex.getSimulatorSoftwareVersionOSTable().clickLinkInRow(tempSoftwareVersionOS, "Edit");
		
		//Ensure data is present after a save
		EditSoftwareVersionOSPageFactory editSWVerOS = new EditSoftwareVersionOSPageFactory();
		assertEquals(editSWVerOS.readSoftwareVersionOS(), tempSoftwareVersionOS, "Edit Software Version/OS - Software Version/OS");
		assertEquals(editSWVerOS.readDescription(), tempDescription, "Edit Software Version/OS - Description");
		assertEquals(editSWVerOS.isActiveChecked(), tempActive, "Edit Software Version/OS - Active");

		editSWVerOS.clickBackToList();		
	}
	
	/**
	 * Utility Method that searches for an temporary record (dynamically gathered
	 * from data sheet) and assigns global variables for a task.
	 */
	private void retrieveTemporaryRecord() {

		ExcelDataConfig swVerOSDataFile = getExcelFile("JMEDAdminDataMap.xlsx", "SimulatorSoftwareVerOS");

		List<Integer> rowIndexs = new ArrayList<Integer>();
		for (int i = 1; i <= swVerOSDataFile.getRowCount(); i++) {

			// If the row is a Temporary row, then add it to the list.
			if (swVerOSDataFile.getData(i, swVerOSDataFile.getColumnIndex("Type")).equalsIgnoreCase("Temporary")) {
				rowIndexs.add(i);
			}
		}

		// Generate a random number that is between 1 and the upper limit of the
		// temporary records.
		Random rand = new Random();
		int rowIndex = rowIndexs.get(rand.nextInt(rowIndexs.size()));

		tempSoftwareVersionOS = swVerOSDataFile.getData(rowIndex, swVerOSDataFile.getColumnIndex("SoftwareVersionOS"));
		tempDescription = swVerOSDataFile.getData(rowIndex, swVerOSDataFile.getColumnIndex("Description"));
		tempActive = Boolean.valueOf(swVerOSDataFile.getData(rowIndex, swVerOSDataFile.getColumnIndex("Active")));
	}
	
	/**
	 * Utility method to handle checking for errors when incorrect data is entered
	 * 
	 */
	private void performErrorValidation() {
		
		// First, navigate to the Create page
		SimulatorSoftwareVersionOSPageFactory simSoftwareVerOSIndex = new SimulatorSoftwareVersionOSPageFactory();
		simSoftwareVerOSIndex.clickCreateNewSimulatorSoftwareVersionOS();

		CreateSoftwareVersionOSPageFactory createSWVersionOS = new CreateSoftwareVersionOSPageFactory();
		
		/*
		 * Error Validation
		 */
		
		// Set the datasheet up in one place to prevent instantiating it multiple places
		ExcelDataConfig errorMessagesDatasheet = getExcelFile("JMEDContentMatrix.xlsx", "StaticTextValidations");

		//Too many characters - SIMULATOR SOFTWARE VERERSION/OS & DESCRIPTION
		String fiftyOneChars = "abc def ghi jkl mno pqrs tuv wxyz ABC DEF GHI JKL M";
		String oneHundredOneChars = "The quick, brown fox jumps over a lazy dog. DJs flock by when MTV ax quiz prog. Junk MTV quiz graced.";
		
		createSWVersionOS.setSoftwareVersionOS(fiftyOneChars);
		createSWVersionOS.setDescription(oneHundredOneChars);
		createSWVersionOS.clickCreate();
		
		
		//Too many characters - Datasheet errors
		String tooManyCharsSwVerOs = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID",  "CREATESOFTWAREVEROS_swVerOs_max_chars"), "Content");
		String tooManyCharsDescription = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID",  "CREATESOFTWAREVEROS_description_max_chars"), "Content");

		//Check the page for the error		
		assertEquals(createSWVersionOS.readSoftwareVersionOSErrorMessage(), tooManyCharsSwVerOs, "Software Version/OS Errors - Too Many Characters");
		assertEquals(createSWVersionOS.readDescriptionErrorMessage(), tooManyCharsDescription, "Description Errors - Too Many Characters");


		// Try to create an empty record
		createSWVersionOS.clearSoftwareVersionOS();
		createSWVersionOS.clearDescription();
		createSWVersionOS.clickCreate();

		String swVerOsRequiredError = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATESOFTWAREVEROS_swVerOs_required"), "Content");
		String descriptionRequiredError = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATESOFTWAREVEROS_description_required"), "Content");
		
		assertEquals(createSWVersionOS.readSoftwareVersionOSErrorMessage(), swVerOsRequiredError, "Software Version/OS Errors - Software Version/OS Required");
		assertEquals(createSWVersionOS.readDescriptionErrorMessage(), descriptionRequiredError, "Description Errors - Description Required");

		// Attempt to create a duplicate
		// Pull a random record from the datasheet
		retrieveExistingRecord();
		
		createSWVersionOS.clearSoftwareVersionOS();
		createSWVersionOS.clearDescription();
		
		createSWVersionOS.setSoftwareVersionOS(softwareVersionOS);
		createSWVersionOS.setDescription(description);
		
		createSWVersionOS.clickCreate();
		
		String swVerOsAlreadyExists = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATESOFTWAREVEROS_swVerOs_already_exists"), "Content");
		assertEquals(createSWVersionOS.readSoftwareVersionOSErrorMessage(), swVerOsAlreadyExists, "Errors - Software Version/OS Already Exists");
				
		//Navigate back to the Index page after error validation
		createSWVersionOS.clickBackToList();
	}
	
	/**
	 * Utility Method that searches for an existing record (dynamically gathered
	 * from data sheet) and assigns global variables for a task.
	 */
	private void retrieveExistingRecord() {

		ExcelDataConfig personnelFile = getExcelFile("JMEDAdminDataMap.xlsx", "SimulatorSoftwareVerOS");

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

		softwareVersionOS = personnelFile.getData(rowIndex, personnelFile.getColumnIndex("SoftwareVersionOS"));
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
