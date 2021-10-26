package testCases.Admin.Simulation;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.AdminSimulationPages.CreateNewNodeConnectorPageFactory;
import pageFactories.Admin.AdminSimulationPages.DeleteNodeConnectorPageFactory;
import pageFactories.Admin.AdminSimulationPages.EditNodeConnectorPageFactory;
import pageFactories.Admin.AdminSimulationPages.NodeConnectorDetailsPageFactory;
import pageFactories.Admin.AdminSimulationPages.NodeConnectorPageFactory;
import testCases.BaseTestScriptConfig;
import utilities.AutomationHelper;
import utilities.ExcelDataConfig;

/**
 * Test Script to exercise Admin > Simulation > Node Connector features. This
 * script performs the following functions:
 * <ul>
 * <li>Navigates to the Admin > Simulation > Node Connector page
 * <li>Exercises search functionality
 * <li>Views details of existing records
 * <li>Views and asserts the Edit page for an existing record
 * <li>Creates a new Node Connector record
 * <li>Asserts error messages fire with incorrect data
 * <li>Asserts record was added to the table
 * <li>Exercises Edit functionality and asserts
 * <li>Deletes record.
 * 
 * @author jesse.childress
 *
 */
public class NodeConnectorAddEditDelete extends BaseTestScriptConfig{
	
	//Class level variables to hold existing data to perform basic checks
	String nodeConnector;
	boolean active;
	
	String tempNodeConnector;
	boolean tempActive;
	
	static String PRIMARY_COLUMN = "Node Connector";
	
	@Test (dataProvider = "loginAccounts")
	public void execute(String userName) {

		// Login to the application using an admin type account
		LoginMod lm= new LoginMod();
		lm.execute(userName);
		
		//Navigate to the Admin > Simulation > Node Connector page
		HomePageFactory hp= new HomePageFactory();
		hp.clickNodeConnectorInAdmin();
		
		//Search for an existing (Dynamic) Node Connector record
		NodeConnectorPageFactory nodeConnectorIndex = new NodeConnectorPageFactory();
		retrieveExistingRecord();
		nodeConnectorIndex.setSearchBy(nodeConnector);
		nodeConnectorIndex.clickSearch();
		
		//Assert that there is only ONE item in the search results
		assertEquals(nodeConnectorIndex.getNodeConnectorTable().getTableRowCount(), 1, "Existing Record Search - Single Value Expected"); 
		assertEquals(nodeConnectorIndex.getNodeConnectorTable().readTableRowValue(PRIMARY_COLUMN, nodeConnector, PRIMARY_COLUMN, true), nodeConnector,	"Existing Node Connector Record Present");
		
		//Clear the search
		nodeConnectorIndex.clearSearchBy();
		nodeConnectorIndex.clickSearch();
		
		//Pick a random record from the datasheet and assert details
		retrieveExistingRecord();
		nodeConnectorIndex.getNodeConnectorTable().clickLinkInRow(nodeConnector, "Details");
		
		//Details Page
		//Assert values are present
		NodeConnectorDetailsPageFactory nodeConnectorDetails = new NodeConnectorDetailsPageFactory();
		assertEquals(nodeConnectorDetails.readNodeConnector(), nodeConnector, "Node Connector Details - Node Connector");
		assertEquals(nodeConnectorDetails.isActiveChecked(), active, "Node Connector Details - Active");
		
		// TODO - BUG 986 has this blocked. Uncomment it later, and delete the work
		// around.
		// nodeConnectorDetails.clickBackToList();
		// TODO - Work around - Delete Me
		nodeConnectorDetails.clickNodeConnectorInAdmin();
		
		//Assert that errors fire.
		performErrorValidation();
		
		//Create a new Node Connector record and validate values
		createNewNodeConnector();
		
		//Edit the newly created Node Connector record to a different value
		editNewNodeConnectorRecord();
		
		// Delete the newly created/edited record
		deleteNewlyCreatedRecord();
		
		// log out
		nodeConnectorIndex = new NodeConnectorPageFactory();
		nodeConnectorIndex.clickLogOffInHelloUser();
	}
	
	/**
	 * Utility method to delete the newly created record and assert that it can no
	 * longer be found.
	 */
	private void deleteNewlyCreatedRecord() {
		NodeConnectorPageFactory nodeConnectorIndex = new NodeConnectorPageFactory();
		
		nodeConnectorIndex.getNodeConnectorTable().clickLinkInRow(tempNodeConnector, "Delete");
		
		DeleteNodeConnectorPageFactory deleteNodeConnector = new DeleteNodeConnectorPageFactory();
		
		assertEquals(deleteNodeConnector.readNodeConnector(), tempNodeConnector, "Delete Node Connector - Node Connector");
		assertEquals(deleteNodeConnector.isActiveChecked(), tempActive, "Delete Node Connector - Active");
		
		deleteNodeConnector.clickDelete();
		
		//Back on Index page
		 nodeConnectorIndex = new NodeConnectorPageFactory();
		 assertEquals(nodeConnectorIndex.getNodeConnectorTable().isRowInTable(tempNodeConnector), false, "Node Connector Table - Temporary Value Deleted");
	}
	
	/**
	 * Utility method to edit a new <i>Node Connector</i> to a different value and assert
	 * that the edit does in fact commit successfully.
	 */
	private void editNewNodeConnectorRecord() {
		NodeConnectorPageFactory nodeConnectorIndex = new NodeConnectorPageFactory();
		
		//Navigate to newly created record
		nodeConnectorIndex.getNodeConnectorTable().clickLinkInRow(tempNodeConnector, "Edit");
		
		//Now on the edit page
		EditNodeConnectorPageFactory editNodeConnector = new EditNodeConnectorPageFactory();
		
		//Update the value of the temporary Node Connector record, to a new temporary Node Connector record (random)
		retrieveTemporaryRecord();
		
		editNodeConnector.setNodeConnector(tempNodeConnector);
		editNodeConnector.setActiveCheckbox(tempActive);
		
		//Save the record
		editNodeConnector.clickSave();
		
		//Ensure that the new record shows up on in the table
		nodeConnectorIndex = new NodeConnectorPageFactory();
		assertEquals(nodeConnectorIndex.getNodeConnectorTable().isRowInTable(tempNodeConnector), true, "Edited Temporary Node Connector Record");
	}
	
	/**
	 * Utility Method that searches for an temporary record (dynamically gathered
	 * from data sheet) and assigns global variables for a task.
	 */
	private void retrieveTemporaryRecord() {

		ExcelDataConfig nodeConnectorFile = getExcelFile("JMEDAdminDataMap.xlsx", "NodeConnectorDataMap");

		List<Integer> rowIndexs = new ArrayList<Integer>();
		for (int i = 1; i <= nodeConnectorFile.getRowCount(); i++) {

			// If the row is a Temporary row, then add it to the list.
			if (nodeConnectorFile.getData(i, nodeConnectorFile.getColumnIndex("Type")).equalsIgnoreCase("Temporary")) {
				rowIndexs.add(i);
			}
		}

		// Generate a random number that is between 1 and the upper limit of the
		// temporary records.
		Random rand = new Random();
		int rowIndex = rowIndexs.get(rand.nextInt(rowIndexs.size()));

		tempNodeConnector = nodeConnectorFile.getData(rowIndex, nodeConnectorFile.getColumnIndex("NodeConnector"));
		tempActive = Boolean.valueOf(nodeConnectorFile.getData(rowIndex, nodeConnectorFile.getColumnIndex("Active")));
	}
	
	/**
	 * Utility method to create a new <i>Node Connector</i> and validate that it in fact
	 * saves and is committed.
	 */
	private void createNewNodeConnector() {

		// First, navigate to the Create page
		NodeConnectorPageFactory nodeConnectorIndex = new NodeConnectorPageFactory();
		nodeConnectorIndex.clickCreateNewNodeConnector();

		CreateNewNodeConnectorPageFactory createNodeConnector = new CreateNewNodeConnectorPageFactory();
		
		//Obtain a random record that is TEMPORARY from the datasheet
		retrieveTemporaryRecord();
		
		//Set the new fields with the temporary data
		createNodeConnector.setNodeConnector(tempNodeConnector);
		createNodeConnector.setActiveCheckbox(tempActive);
		
		//Click Create
		createNodeConnector.clickCreate();
		
		//Back on the index page
		nodeConnectorIndex = new NodeConnectorPageFactory();
		
		//Assert that the new field is in the table
		assertEquals(nodeConnectorIndex.getNodeConnectorTable().isRowInTable(tempNodeConnector), true, "New Temporary Record added to table");
		nodeConnectorIndex.getNodeConnectorTable().clickLinkInRow(tempNodeConnector, "Edit");
		
		//Ensure data is present after a save
		EditNodeConnectorPageFactory editNodeConnector = new EditNodeConnectorPageFactory();
		assertEquals(editNodeConnector.readNodeConnector(), tempNodeConnector, "Edit Node Connector Page - Node Connector field");
		assertEquals(editNodeConnector.isActiveChecked(), tempActive, "Edit Node Connector Page - Active field");
		editNodeConnector.clickBackToList();	
	}
	
	/**
	 * Utility method to handle checking for errors when incorrect data is entered
	 * 
	 */
	private void performErrorValidation() {
		
		// First, navigate to the Create page
		NodeConnectorPageFactory nodeConnectorIndex = new NodeConnectorPageFactory();
		nodeConnectorIndex.clickCreateNewNodeConnector();

		CreateNewNodeConnectorPageFactory createNodeConnector = new CreateNewNodeConnectorPageFactory();

		/*
		 * Error Validation
		 */
		
		// Set the datasheet up in one place to prevent instantiating it multiple places
		ExcelDataConfig errorMessagesDatasheet = getExcelFile("JMEDContentMatrix.xlsx", "StaticTextValidations");

		//MAX CHARACTER TEST
		String fiftyOneChars = "abc def ghi jkl mno pqrs tuv wxyz ABC DEF GHI JKL M";
		createNodeConnector.clearNodeConnector();
		createNodeConnector.setNodeConnector(fiftyOneChars);
		createNodeConnector.clickCreate();
		
		String tooManyChars = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATENODECONNECTOR_node_connector_max_chars"), "Content");
		assertEquals(createNodeConnector.readNodeConnectorErrorMessage(), tooManyChars, "Errors - Node Connector - Too Many Characters");
		
		// EMPTY RECORD TEST
		createNodeConnector.clearNodeConnector();
		createNodeConnector.clickCreate();

		String nodeConnectorRequired = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATENODECONNECTOR_node_connector_required"), "Content");
		assertEquals(createNodeConnector.readNodeConnectorErrorMessage(), nodeConnectorRequired, "Errors - Node Connector Required");

		// DUPLICATE RECORD TEST
		// Pull a random record from the datasheet
		retrieveExistingRecord();
		createNodeConnector.clearNodeConnector();
		createNodeConnector.setNodeConnector(nodeConnector);
		createNodeConnector.clickCreate();
		String nodeConnectorAlreadyExists = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATENODECONNECTOR_node_connector_already_exists"), "Content");
		assertEquals(createNodeConnector.readNodeConnectorErrorMessage(), nodeConnectorAlreadyExists, "Errors - Node Connector Already Exists");
		
		//Navigate back to the Index page after error validation
		createNodeConnector.clickBackToList();
	}
	
	
	
	/**
	 * Utility Method that searches for an existing record (dynamically gathered
	 * from data sheet) and assigns global variables for a task.
	 */
	private void retrieveExistingRecord() {

		ExcelDataConfig nodeConnectorFile = getExcelFile("JMEDAdminDataMap.xlsx", "NodeConnectorDataMap");

		List<Integer> rowIndexs = new ArrayList<Integer>();
		for (int i = 1; i <= nodeConnectorFile.getRowCount(); i++) {

			// If the row is a Temporary row, then add it to the list.
			if (nodeConnectorFile.getData(i, nodeConnectorFile.getColumnIndex("Type")).equalsIgnoreCase("Permanent")) {
				rowIndexs.add(i);
			}
		}

		// Generate a random number that is between 1 and the upper limit of the
		// temporary records.
		Random rand = new Random();
		int rowIndex = rowIndexs.get(rand.nextInt(rowIndexs.size()));

		nodeConnector = nodeConnectorFile.getData(rowIndex, nodeConnectorFile.getColumnIndex("NodeConnector"));
		active = Boolean.valueOf(nodeConnectorFile.getData(rowIndex, nodeConnectorFile.getColumnIndex("Active")));
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










