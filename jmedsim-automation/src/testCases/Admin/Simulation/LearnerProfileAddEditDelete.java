package testCases.Admin.Simulation;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.AdminSimulationPages.CreateNewLearnerPofilePageFactory;
import pageFactories.Admin.AdminSimulationPages.DeleteLearnerProfilePageFactory;
import pageFactories.Admin.AdminSimulationPages.EditLearnerProfilePageFactory;
import pageFactories.Admin.AdminSimulationPages.LearnerProfileDetailsPageFactory;
import pageFactories.Admin.AdminSimulationPages.LearnerProfilePageFactory;
import testCases.BaseTestScriptConfig;
import utilities.AutomationHelper;
import utilities.ExcelDataConfig;

/**
 * Test Script to exercise Admin > Simulation > Learner Profile features. This
 * script performs the following functions:
 * <ul>
 * <li>Navigates to the Admin > Simulation > Learner Profile page
 * <li>Exercises search functionality
 * <li>Views details of existing records
 * <li>Views and asserts the Edit page for an existing record
 * <li>Creates a new Learner Profile record
 * <li>Asserts error messages fire with incorrect data
 * <li>Asserts record was added to the table
 * <li>Exercises Edit functionality and asserts
 * <li>Deletes record.
 * 
 * @author jesse.childress
 *
 */
public class LearnerProfileAddEditDelete extends BaseTestScriptConfig {

	// Class level variables to hold existing data to perform basic checks
	String learnerProfile;
	boolean active;

	String tempLearnerProfile;
	boolean tempActive;

	static String PRIMARY_COLUMN = "Learner Profile";

	@Test(dataProvider = "loginAccounts")
	public void execute(String userName) {

		// Login as either a DHA Super User or DHA System Admin (randomly)
		LoginMod lm = new LoginMod();
		lm.execute(userName);

		// Navigate to the Training Type page under ADMIN > SIMULATION > Learner Profile
		HomePageFactory home = new HomePageFactory();
		home.clickLearnerProfileInAdmin();

		// Search for an existing record
		LearnerProfilePageFactory learnerProfileIndex = new LearnerProfilePageFactory();
		retrieveExistingRecord();
		learnerProfileIndex.setSearchBy(learnerProfile);
		learnerProfileIndex.clickSearch();
		
		//Assert that there is only ONE item in the search results
		assertEquals(learnerProfileIndex.getLearnerProfileTable().getTableRowCount(),  1, "Table Search Results: Only one result returned");
		assertEquals(learnerProfileIndex.getLearnerProfileTable().readTableRowValue(PRIMARY_COLUMN, learnerProfile, PRIMARY_COLUMN, true), learnerProfile,	"Existing Learner Profile Record Present");
		
		//Clear the search
		learnerProfileIndex.clearSearchBy();
		learnerProfileIndex.clickSearch();
		
		//Pick a random record from the datasheet and assert details.
		retrieveExistingRecord();
		learnerProfileIndex.getLearnerProfileTable().clickLinkInRow(learnerProfile, "Details");
		
		//Details Page
		//Assert values are present
		LearnerProfileDetailsPageFactory lpDetails = new LearnerProfileDetailsPageFactory();
		assertEquals(lpDetails.readLearnerProfile(), learnerProfile, "Learner Profile Details - Learner Profile");
		assertEquals(lpDetails.isActiveChecked(), active, "Learner Profile Details - Active");
		
		// TODO - BUG 986 has this blocked. Uncomment it later, and delete the work
		// around.
		// LearnerProfileDetails.clickBackToList();
		// TODO - Work around - Delete Me
		lpDetails.clickLearnerProfileInAdmin();
		
		//Assert that errors fire.
		performErrorValidation();
		
		//Create a new Learner Profile and validate values
		createNewLearnerProfile();
		
		//Edit the newly created Learner Profile record to a different value
		editNewLearnerProfileRecord();
		
		//Delete the newly created/edited record
		deleteNewlyCreatedRecord();
		
		// log out
		learnerProfileIndex = new LearnerProfilePageFactory();
		learnerProfileIndex.clickLogOffInHelloUser();
	}
	
	/**
	 * Utility method to delete the newly created record and assert that it can no
	 * longer be found.
	 */
	private void deleteNewlyCreatedRecord() {
		LearnerProfilePageFactory lpIndex = new LearnerProfilePageFactory();
		
		lpIndex.getLearnerProfileTable().clickLinkInRow(tempLearnerProfile, "Delete");
		
		DeleteLearnerProfilePageFactory deleteLearnerProfile = new DeleteLearnerProfilePageFactory();
		
		assertEquals(deleteLearnerProfile.readLearnerProfile(), tempLearnerProfile, "Delete Learner Profile - Learner Profile");
		assertEquals(deleteLearnerProfile.isActiveChecked(), tempActive, "Delete Learner Profile - Active");
		
		deleteLearnerProfile.clickDelete();
		
		//Back on Index page
		 lpIndex = new LearnerProfilePageFactory();
		 assertEquals(lpIndex.getLearnerProfileTable().isRowInTable(tempLearnerProfile), false, "Learner Profile Table - Temporary Value Deleted");
	}
	
	/**
	 * Utility method to edit a new <i>Learner Profile</i> to a different value and assert
	 * that the edit does in fact commit successfully.
	 */
	private void editNewLearnerProfileRecord() {
		LearnerProfilePageFactory lpIndex = new LearnerProfilePageFactory();
		
		//Navigate to newly created record
		lpIndex.getLearnerProfileTable().clickLinkInRow(tempLearnerProfile, "Edit");
		
		//Now on the edit page
		EditLearnerProfilePageFactory editLPPage = new EditLearnerProfilePageFactory();
		
		//Update the value of the temporary Learner Profile record, to a new temporary Learner Profile record (random)
		retrieveTemporaryRecord();
		
		editLPPage.setLearnerProfile(tempLearnerProfile);
		editLPPage.setActiveCheckbox(tempActive);
		
		//Save the record
		editLPPage.clickSave();
		
		//Ensure that the new record shows up on in the table
		lpIndex = new LearnerProfilePageFactory();
		assertEquals(lpIndex.getLearnerProfileTable().isRowInTable(tempLearnerProfile), true, "Edited Temporary Learner Profile Record");
	}
	
	/**
	 * Utility Method that searches for an temporary record (dynamically gathered
	 * from data sheet) and assigns global variables for a task.
	 */
	private void retrieveTemporaryRecord() {

		ExcelDataConfig learnerProfileFile = getExcelFile("JMEDAdminDataMap.xlsx", "LearnerProfileDataMap");

		List<Integer> rowIndexs = new ArrayList<Integer>();
		for (int i = 1; i <= learnerProfileFile.getRowCount(); i++) {

			// If the row is a Temporary row, then add it to the list.
			if (learnerProfileFile.getData(i, learnerProfileFile.getColumnIndex("Type")).equalsIgnoreCase("Temporary")) {
				rowIndexs.add(i);
			}
		}

		// Generate a random number that is between 1 and the upper limit of the
		// temporary records.
		Random rand = new Random();
		int rowIndex = rowIndexs.get(rand.nextInt(rowIndexs.size()));

		tempLearnerProfile = learnerProfileFile.getData(rowIndex, learnerProfileFile.getColumnIndex("LearnerProfile"));
		tempActive = Boolean.valueOf(learnerProfileFile.getData(rowIndex, learnerProfileFile.getColumnIndex("Active")));
	}
	
	/**
	 * Utility method to create a new <i>Learner Profile</i> and validate that it in fact
	 * saves and is committed.
	 */
	private void createNewLearnerProfile() {

		// First, navigate to the Create page
		LearnerProfilePageFactory lpIndex = new LearnerProfilePageFactory();
		lpIndex.clickCreateNewLearnerProfile();

		CreateNewLearnerPofilePageFactory createLP = new CreateNewLearnerPofilePageFactory();
		
		//Obtain a random record that is TEMPORARY from the datasheet
		retrieveTemporaryRecord();
		
		//Set the new fields with the temporary data
		createLP.setLearnerProfile(tempLearnerProfile);
		createLP.setActiveCheckbox(tempActive);
		
		//Click Create
		createLP.clickCreate();
		
		//Back on the index page
		lpIndex = new LearnerProfilePageFactory();
		
		//Assert that the new field is in the table
		assertEquals(lpIndex.getLearnerProfileTable().isRowInTable(tempLearnerProfile), true, "New Temporary Record added to table");
		lpIndex.getLearnerProfileTable().clickLinkInRow(tempLearnerProfile, "Edit");
		
		//Ensure data is present after a save
		EditLearnerProfilePageFactory editLPPage = new EditLearnerProfilePageFactory();
		assertEquals(editLPPage.readLearnerProfile(), tempLearnerProfile, "Edit Learner Profile Page - Learner Profile field");
		assertEquals(editLPPage.isActiveChecked(), tempActive, "Edit Learner Profile Page - Active field");
		editLPPage.clickBackToList();	
	}
	
	/**
	 * Utility method to handle checking for errors when incorrect data is entered
	 * 
	 */
	private void performErrorValidation() {
		
		// First, navigate to the Create page
		LearnerProfilePageFactory lpIndex = new LearnerProfilePageFactory();
		lpIndex.clickCreateNewLearnerProfile();
		CreateNewLearnerPofilePageFactory createLP = new CreateNewLearnerPofilePageFactory();

		/*
		 * Error Validation
		 */
		
		// Set the datasheet up in one place to prevent instantiating it multiple places
		ExcelDataConfig errorMessagesDatasheet = getExcelFile("JMEDContentMatrix.xlsx", "StaticTextValidations");

		//MAX CHARACTER TEST
		String fiftyOneChars = "abc def ghi jkl mno pqrs tuv wxyz ABC DEF GHI JKL M";
		createLP.clearLearnerProfile();
		createLP.setLearnerProfile(fiftyOneChars);
		createLP.clickCreate();
		
		String tooManyChars = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATENEWLEARNERPROFILE_max_chars"), "Content");
		assertEquals(createLP.readLearnerProfileErrorMessage(), tooManyChars, "Errors - Learner Profile - Too Many Characters");
		
		// EMPTY RECORD TEST
		createLP.clearLearnerProfile();
		createLP.clickCreate();

		String LearnerProfileRequired = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATENEWLEARNERPROFILE_profile_required"), "Content");
		assertEquals(createLP.readLearnerProfileErrorMessage(), LearnerProfileRequired, "Errors - Learner Profile Required");

		// DUPLICATE RECORD TEST
		// Pull a random record from the datasheet
		retrieveExistingRecord();
		createLP.clearLearnerProfile();
		createLP.setLearnerProfile(learnerProfile);
		createLP.clickCreate();
		String LearnerProfileAlreadyExists = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATENEWLEARNERPROFILE_profile_already_exists"), "Content");
		assertEquals(createLP.readLearnerProfileErrorMessage(), LearnerProfileAlreadyExists, "Errors - Learner Profile Already Exists");
		
		//Navigate back to the Index page after error validation
		createLP.clickBackToList();
	}
	
	
	/**
	 * Utility Method that searches for an existing record (dynamically gathered
	 * from data sheet) and assigns global variables for a task.
	 */
	private void retrieveExistingRecord() {

		ExcelDataConfig learnerProfileFile = getExcelFile("JMEDAdminDataMap.xlsx", "LearnerProfileDataMap");

		List<Integer> rowIndexs = new ArrayList<Integer>();
		for (int i = 1; i <= learnerProfileFile.getRowCount(); i++) {

			// If the row is a Temporary row, then add it to the list.
			if (learnerProfileFile.getData(i, learnerProfileFile.getColumnIndex("Type")).equalsIgnoreCase("Permanent")) {
				rowIndexs.add(i);
			}
		}

		// Generate a random number that is between 1 and the upper limit of the
		// temporary records.
		Random rand = new Random();
		int rowIndex = rowIndexs.get(rand.nextInt(rowIndexs.size()));

		learnerProfile = learnerProfileFile.getData(rowIndex, learnerProfileFile.getColumnIndex("LearnerProfile"));
		active = Boolean.valueOf(learnerProfileFile.getData(rowIndex, learnerProfileFile.getColumnIndex("Active")));
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
