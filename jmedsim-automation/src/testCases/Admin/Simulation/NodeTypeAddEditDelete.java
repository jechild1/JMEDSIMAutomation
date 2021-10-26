package testCases.Admin.Simulation;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.AdminSimulationPages.CreateNodeTypePageFactory;
import pageFactories.Admin.AdminSimulationPages.DeleteNodeTypePageFactory;
import pageFactories.Admin.AdminSimulationPages.EditNodeTypePageFactory;
import pageFactories.Admin.AdminSimulationPages.NodeTypeDetailsPageFactory;
import pageFactories.Admin.AdminSimulationPages.NodeTypePageFactory;
import testCases.BaseTestScriptConfig;
import utilities.AutomationHelper;
import utilities.ExcelDataConfig;

/**
 * Test Script to exercise Admin > Simulation > Node Type features. This
 * script performs the following functions:
 * <ul>
 * <li>Navigates to the Admin > Simulation > Node Type page
 * <li>Exercises search functionality
 * <li>Views details of existing records
 * <li>Views and asserts the Edit page for an existing record
 * <li>Creates a new Node Type record
 * <li>Asserts error messages fire with incorrect data
 * <li>Asserts record was added to the table
 * <li>Exercises Edit functionality and asserts
 * <li>Deletes record.
 * 
 * @author jesse.childress
 *
 */
public class NodeTypeAddEditDelete extends BaseTestScriptConfig{
	
	//Class level variables to hold existing data to perform basic checks
	String nodeType;
	boolean active;
	
	String tempNodeType;
	boolean tempActive;
	
	static String PRIMARY_COLUMN = "Node Type";
	
	@Test (dataProvider = "loginAccounts")
	public void execute(String userName) {

		// Login to the application using an admin type account
		LoginMod lm= new LoginMod();
		lm.execute(userName);
		
		//Navigate to the Admin > Simulation > Node Type page
		HomePageFactory hp= new HomePageFactory();
		hp.clickNodeTypeInAdmin();
		
		
		//Search for an existing (Dynamic) Node Type record
		NodeTypePageFactory nodeTypeIndex = new NodeTypePageFactory();
		retrieveExistingRecord();
		nodeTypeIndex.setSearchBy(nodeType);
		nodeTypeIndex.clickSearch();
		
		//Assert that there is only ONE item in the search results
		assertEquals(nodeTypeIndex.getNodeTypeTable().getTableRowCount(), 1, "Existing Record Search - Single Value Expected"); 
		assertEquals(nodeTypeIndex.getNodeTypeTable().readTableRowValue(PRIMARY_COLUMN, nodeType, PRIMARY_COLUMN, true), nodeType,	"Existing Node Type Record Present");
		
		//Clear the search
		nodeTypeIndex.clearSearchBy();
		nodeTypeIndex.clickSearch();
		
		//Pick a random record from the datasheet and assert details
		retrieveExistingRecord();
		nodeTypeIndex.getNodeTypeTable().clickLinkInRow(nodeType, "Details");
		
		//Details Page
		//Assert values are present
		NodeTypeDetailsPageFactory nodeTypeDetails = new NodeTypeDetailsPageFactory();
		assertEquals(nodeTypeDetails.readNodeType(), nodeType, "Node Type Details - Node Type");
		assertEquals(nodeTypeDetails.isActiveChecked(), active, "Node Type Details - Active");
		
		// TODO - BUG 986 has this blocked. Uncomment it later, and delete the work
		// around.
		// nodeTypeDetails.clickBackToList();
		// TODO - Work around - Delete Me
		nodeTypeDetails.clickNodeTypeInAdmin();
		
		//Assert that errors fire.
		performErrorValidation();
		
		//Create a new Node Type record and validate values
		createNewNodeType();
		
		//Edit the newly created Node Type record to a different value
		editNewNodeTypeRecord();
		
		// Delete the newly created/edited record
		deleteNewlyCreatedRecord();
		
		// log out
		nodeTypeIndex = new NodeTypePageFactory();
		nodeTypeIndex.clickLogOffInHelloUser();
	}
	
	/**
	 * Utility method to delete the newly created record and assert that it can no
	 * longer be found.
	 */
	private void deleteNewlyCreatedRecord() {
		NodeTypePageFactory nodeTypeIndex = new NodeTypePageFactory();
		
		nodeTypeIndex.getNodeTypeTable().clickLinkInRow(tempNodeType, "Delete");
		
		DeleteNodeTypePageFactory deleteNodeType = new DeleteNodeTypePageFactory();
		
		assertEquals(deleteNodeType.readNodeType(), tempNodeType, "Delete Node Type - Node Type");
		assertEquals(deleteNodeType.isActiveChecked(), tempActive, "Delete Node Type - Active");
		
		deleteNodeType.clickDelete();
		
		//Back on Index page
		 nodeTypeIndex = new NodeTypePageFactory();
		 assertEquals(nodeTypeIndex.getNodeTypeTable().isRowInTable(tempNodeType), false, "Node Type Table - Temporary Value Deleted");
	}
	
	/**
	 * Utility method to edit a new <i>Node Type</i> to a different value and assert
	 * that the edit does in fact commit successfully.
	 */
	private void editNewNodeTypeRecord() {
		NodeTypePageFactory nodeTypeIndex = new NodeTypePageFactory();
		
		//Navigate to newly created record
		nodeTypeIndex.getNodeTypeTable().clickLinkInRow(tempNodeType, "Edit");
		
		//Now on the edit page
		EditNodeTypePageFactory editNodeType = new EditNodeTypePageFactory();
		
		//Update the value of the temporary Node Type record, to a new temporary Node Type record (random)
		retrieveTemporaryRecord();
		
		editNodeType.setNodeType(tempNodeType);
		editNodeType.setActiveCheckbox(tempActive);
		
		//Save the record
		editNodeType.clickSave();
		
		//Ensure that the new record shows up on in the table
		nodeTypeIndex = new NodeTypePageFactory();
		assertEquals(nodeTypeIndex.getNodeTypeTable().isRowInTable(tempNodeType), true, "Edited Temporary Node Type Record");
	}
	
	/**
	 * Utility Method that searches for an temporary record (dynamically gathered
	 * from data sheet) and assigns global variables for a task.
	 */
	private void retrieveTemporaryRecord() {

		ExcelDataConfig nodeTypeFile = getExcelFile("JMEDAdminDataMap.xlsx", "NodeTypeDataMap");

		List<Integer> rowIndexs = new ArrayList<Integer>();
		for (int i = 1; i <= nodeTypeFile.getRowCount(); i++) {

			// If the row is a Temporary row, then add it to the list.
			if (nodeTypeFile.getData(i, nodeTypeFile.getColumnIndex("Type")).equalsIgnoreCase("Temporary")) {
				rowIndexs.add(i);
			}
		}

		// Generate a random number that is between 1 and the upper limit of the
		// temporary records.
		Random rand = new Random();
		int rowIndex = rowIndexs.get(rand.nextInt(rowIndexs.size()));

		tempNodeType = nodeTypeFile.getData(rowIndex, nodeTypeFile.getColumnIndex("NodeType"));
		tempActive = Boolean.valueOf(nodeTypeFile.getData(rowIndex, nodeTypeFile.getColumnIndex("Active")));
	}
	
	/**
	 * Utility method to create a new <i>Node Type</i> and validate that it in fact
	 * saves and is committed.
	 */
	private void createNewNodeType() {

		// First, navigate to the Create page
		NodeTypePageFactory nodeTypeIndex = new NodeTypePageFactory();
		nodeTypeIndex.clickCreateNewNodeType();

		CreateNodeTypePageFactory createNodeType = new CreateNodeTypePageFactory();
		
		//Obtain a random record that is TEMPORARY from the datasheet
		retrieveTemporaryRecord();
		
		//Set the new fields with the temporary data
		createNodeType.setNodeType(tempNodeType);
		createNodeType.setActiveCheckbox(tempActive);
		
		//Click Create
		createNodeType.clickCreate();
		
		//Back on the index page
		nodeTypeIndex = new NodeTypePageFactory();
		
		//Assert that the new field is in the table
		assertEquals(nodeTypeIndex.getNodeTypeTable().isRowInTable(tempNodeType), true, "New Temporary Record added to table");
		nodeTypeIndex.getNodeTypeTable().clickLinkInRow(tempNodeType, "Edit");
		
		//Ensure data is present after a save
		EditNodeTypePageFactory editNodeType = new EditNodeTypePageFactory();
		assertEquals(editNodeType.readNodeType(), tempNodeType, "Edit Node Type Page - Node Type field");
		assertEquals(editNodeType.isActiveChecked(), tempActive, "Edit Node Type Page - Active field");
		editNodeType.clickBackToList();	
	}
	
	/**
	 * Utility method to handle checking for errors when incorrect data is entered
	 * 
	 */
	private void performErrorValidation() {
		
		// First, navigate to the Create page
		NodeTypePageFactory nodeTypeIndex = new NodeTypePageFactory();
		nodeTypeIndex.clickCreateNewNodeType();

		CreateNodeTypePageFactory createNodeType = new CreateNodeTypePageFactory();

		/*
		 * Error Validation
		 */
		
		// Set the datasheet up in one place to prevent instantiating it multiple places
		ExcelDataConfig errorMessagesDatasheet = getExcelFile("JMEDContentMatrix.xlsx", "StaticTextValidations");

		//MAX CHARACTER TEST
		String fiftyOneChars = "abc def ghi jkl mno pqrs tuv wxyz ABC DEF GHI JKL M";
		createNodeType.clearNodeType();
		createNodeType.setNodeType(fiftyOneChars);
		createNodeType.clickCreate();
		
		String tooManyChars = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATENODETYPE_node_type_max_chars"), "Content");
		assertEquals(createNodeType.readNodeTypeErrorMessage(), tooManyChars, "Errors - Node Type - Too Many Characters");
		
		// EMPTY RECORD TEST
		createNodeType.clearNodeType();
		createNodeType.clickCreate();

		String nodeTypeRequired = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATENODETYPE_node_type_required"), "Content");
		assertEquals(createNodeType.readNodeTypeErrorMessage(), nodeTypeRequired, "Errors - Node Type Required");

		// DUPLICATE RECORD TEST
		// Pull a random record from the datasheet
		retrieveExistingRecord();
		createNodeType.clearNodeType();
		createNodeType.setNodeType(nodeType);
		createNodeType.clickCreate();
		String nodeTypeAlreadyExists = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATENODETYPE_node_type_already_exists"), "Content");
		assertEquals(createNodeType.readNodeTypeErrorMessage(), nodeTypeAlreadyExists, "Errors - Node Type Already Exists");
		
		//Navigate back to the Index page after error validation
		createNodeType.clickBackToList();
	}
	
	
	
	/**
	 * Utility Method that searches for an existing record (dynamically gathered
	 * from data sheet) and assigns global variables for a task.
	 */
	private void retrieveExistingRecord() {

		ExcelDataConfig nodeTypeFile = getExcelFile("JMEDAdminDataMap.xlsx", "NodeTypeDataMap");

		List<Integer> rowIndexs = new ArrayList<Integer>();
		for (int i = 1; i <= nodeTypeFile.getRowCount(); i++) {

			// If the row is a Temporary row, then add it to the list.
			if (nodeTypeFile.getData(i, nodeTypeFile.getColumnIndex("Type")).equalsIgnoreCase("Permanent")) {
				rowIndexs.add(i);
			}
		}

		// Generate a random number that is between 1 and the upper limit of the
		// temporary records.
		Random rand = new Random();
		int rowIndex = rowIndexs.get(rand.nextInt(rowIndexs.size()));

		nodeType = nodeTypeFile.getData(rowIndex, nodeTypeFile.getColumnIndex("NodeType"));
		active = Boolean.valueOf(nodeTypeFile.getData(rowIndex, nodeTypeFile.getColumnIndex("Active")));
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










