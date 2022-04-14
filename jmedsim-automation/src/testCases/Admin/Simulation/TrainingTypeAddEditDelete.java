package testCases.Admin.Simulation;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.AdminSimulationPages.CreateNewTrainingTypePageFactory;
import pageFactories.Admin.AdminSimulationPages.DeleteTrainingTypePageFactory;
import pageFactories.Admin.AdminSimulationPages.EditTrainingTypePageFactory;
import pageFactories.Admin.AdminSimulationPages.TrainingTypeDetailsPageFactory;
import pageFactories.Admin.AdminSimulationPages.TrainingTypePageFactory;
import testCases.BaseTestScriptConfig;
import utilities.AutomationHelper;
import utilities.ExcelDataConfig;

/**
 * Test Script to exercise Admin > Simulation > Training type features. This
 * script performs the following functions:
 * <ul>
 * <li>Navigates to the Admin > Simulation > Training Type page
 * <li>Exercises search functionality
 * <li>Views details of existing records
 * <li>Views and asserts the Edit page for an existing record
 * <li>Creates a new Training Type record
 * <li>Asserts error messages fire with incorrect data
 * <li>Asserts record was added to the table
 * <li>Exercises Edit functionality and asserts
 * <li>Deletes record.
 * 
 * @author jesse.childress
 *
 */
public class TrainingTypeAddEditDelete extends BaseTestScriptConfig {
	
	//Class level variables to hold existing data to perform basic checks
	static String TRAINING_TYPE = "Task Training";
	static boolean ACTIVE = true;
	
	static String NEW_TRAINING_TYPE = "New Training Type";
	static String NEW_TRAINING_TYPE_EDITED = "Edited Training Type";
	
	static boolean NEW_ACTIVE_FLAG = true;
	static boolean NEW_ACTIVE_FLAG_EDITED = false;

	
	static String PRIMARY_COLUMN = "Training Type";
	
	@Test (dataProvider = "loginAccounts")
	public void execute(String userName)  {
		
		//Login as either a DHA Super User or DHA System Admin (randomly)
		LoginMod login = new LoginMod();
		login.execute(userName);
		
		//Navigate to the Training  Type page under ADMIN > SIMULATION > TRAINING TYPE
		HomePageFactory home = new HomePageFactory();
		home.clickTrainingTypeInAdmin();
		
		//Search for an existing record
		TrainingTypePageFactory trainingTypeIndex = new TrainingTypePageFactory();
		trainingTypeIndex.setSearchBy(TRAINING_TYPE);
		trainingTypeIndex.clickSearch();
		
		assertEquals(trainingTypeIndex.getTrainingTypeTable().getTableRowCount(), 1, "Table Search Results: Only one result returned");
		
		//Validate details of existing page can be viewed
		trainingTypeIndex.getTrainingTypeTable().clickLinkInRow(TRAINING_TYPE, "Details");
		
		TrainingTypeDetailsPageFactory trainingTypeDetails = new TrainingTypeDetailsPageFactory();
		assertEquals(trainingTypeDetails.readTrainingType(), TRAINING_TYPE, "Training Type Details - Training Type");
		assertEquals(trainingTypeDetails.isActiveChecked(), ACTIVE, "Training Type Details - Active");
		trainingTypeDetails.clickBackToList();
		
		//View Edit page for an existing record
		trainingTypeIndex = new TrainingTypePageFactory();
		trainingTypeIndex.getTrainingTypeTable().clickLinkInRow(TRAINING_TYPE, "Edit");
		
		EditTrainingTypePageFactory editTrainingType = new EditTrainingTypePageFactory();
		assertEquals(editTrainingType.readTrainingType(), TRAINING_TYPE, "Edit Training Type - Task Training");
		assertEquals(editTrainingType.isActiveChecked(), ACTIVE, "Edit Training Type - ACTIVE");
		editTrainingType.clickBackToList();		
		
		//Create a new Training Type record
		trainingTypeIndex = new TrainingTypePageFactory();
		trainingTypeIndex.clickCreateNewTrainingType();
				
		//Error validation first
		CreateNewTrainingTypePageFactory createNewTrainingType = new CreateNewTrainingTypePageFactory();
		createNewTrainingType.clickCreate();
		
		//No data entered
		ExcelDataConfig errorFile = getExcelFile("JMEDContentMatrix.xlsx", "StaticTextValidations");
		String requiredErrorText = errorFile.getData("CREATENEWTRAININGTYPE_type_required", "Content");
		
		assertEquals(createNewTrainingType.readTrainingTypeErrorMessage(), requiredErrorText, "Create New Training Type - Training Type Field Required");
		
		//Training Type Already exists
//		createNewTrainingType.setTrainingType(TRAINING_TYPE);
//		createNewTrainingType.clickCreate();
//		String typeAlreadyExistsError = errorFile.getData("CREATENEWTRAININGTYPE_type_already_exists", "Content");
//		
//		assertEquals(createNewTrainingType.readTrainingTypeErrorMessage(), typeAlreadyExistsError, "Create New Training Type - Training Type already exists.");

		//Too many chars
		String fiftyOneChars = "Li Europan lingues es membres del sam familie. Lor.";
		createNewTrainingType.setTrainingType(fiftyOneChars);

		String maxCharsError = errorFile.getData("CREATENEWTRAININGTYPE_max_chars", "Content");
		AutomationHelper.waitSeconds(2);
		createNewTrainingType.clickCreate();
		assertEquals(createNewTrainingType.readTrainingTypeErrorMessage(), maxCharsError, "Create New Training Type - 50 Chars Max");
		
		//Creating legitimate new record
		createNewTrainingType.setTrainingType(NEW_TRAINING_TYPE);
		createNewTrainingType.setActiveCheckbox(NEW_ACTIVE_FLAG);
		
		WebElement createButton = driver.findElement(By.xpath("//input[@value='Create']"));
		Actions hover = new Actions(driver);
		hover.moveToElement(createButton);
		hover.build();
		hover.perform();
		AutomationHelper.waitSeconds(5);
		
		createNewTrainingType.clickCreate();
		
		//Validate new record exist on the index page
		trainingTypeIndex = new TrainingTypePageFactory();
		assertEquals(trainingTypeIndex.getTrainingTypeTable().readTableRowValue(PRIMARY_COLUMN, NEW_TRAINING_TYPE, PRIMARY_COLUMN, true), NEW_TRAINING_TYPE, "Training Type Table - New Record Present");
		
		//Edit newly created training type
		trainingTypeIndex.getTrainingTypeTable().clickLinkInRow(NEW_TRAINING_TYPE, "Edit");
		editTrainingType = new EditTrainingTypePageFactory();
		editTrainingType.setTrainingType(NEW_TRAINING_TYPE_EDITED);
		editTrainingType.setActiveCheckbox(NEW_ACTIVE_FLAG_EDITED);
		
		editTrainingType.clickSave();
		
		//Assert that record was edited back on the index page
		trainingTypeIndex = new TrainingTypePageFactory();
		assertEquals(trainingTypeIndex.getTrainingTypeTable().readTableRowValue(PRIMARY_COLUMN, NEW_TRAINING_TYPE_EDITED, PRIMARY_COLUMN, true), NEW_TRAINING_TYPE_EDITED, "Training Type Table - Newly Edited Training Type Present");
		
		
		//Perform the delete
		trainingTypeIndex.getTrainingTypeTable().clickLinkInRow(NEW_TRAINING_TYPE_EDITED, "Delete");
		
		DeleteTrainingTypePageFactory deleteTrainingType = new DeleteTrainingTypePageFactory();
		assertEquals(deleteTrainingType.readTrainingType(), NEW_TRAINING_TYPE_EDITED, "Delete Training Type - Training Type");
		assertEquals(deleteTrainingType.isActiveChecked(), NEW_ACTIVE_FLAG_EDITED, "Delete Training Type - Active");
		
		deleteTrainingType.clickDelete();
		
		//Assert that the row is removed on the index page
		trainingTypeIndex = new TrainingTypePageFactory();
		assertEquals(trainingTypeIndex.getTrainingTypeTable().isRowInTable(NEW_TRAINING_TYPE_EDITED), false, "Training Type Table - Record Removed");
		
		trainingTypeIndex.clickLogOffInHelloUser();
	}
	
	
	
	/**
	 * Returns a random user of type DHA Super User or DHA System Admin from the UsersData.xlsx
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
