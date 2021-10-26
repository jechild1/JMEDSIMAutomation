package testCases.Admin.Simulation;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.AdminSimulationPages.CreateNewServiceRolePageFactory;
import pageFactories.Admin.AdminSimulationPages.DeleteServiceRolePageFactory;
import pageFactories.Admin.AdminSimulationPages.ServiceRolePageFactory;
import testCases.BaseTestScriptConfig;
import utilities.AutomationHelper;
import utilities.ExcelDataConfig;

/**
 * This test script navigates to the Service Role menu and creates a new service
 * role, asserts proper validations occur, and then deletes the newly created
 * record.
 * 
 * @author jesse.childress
 *
 */
public class ServiceRoleAddEditDelete extends BaseTestScriptConfig {
	private static final String PRIMARY_COLUMN = "Service Role";	
	private static final String EXISTING_SERVICE_ROLE = "Medical Commander";
	
	//Set the datasheet up in one place to prevent instantiating it multiple places
	ExcelDataConfig errorMessagesDatasheet = getExcelFile("JMEDContentMatrix.xlsx", "StaticTextValidations");


	@Test(dataProvider = "loginAccounts")
	public void execute(String userName) {
		
				
		// (1)Log in to the system as an admin type role
		LoginMod login = new LoginMod();
		login.execute(userName);

		// (2)Navigate to the Simulation > Service Role menu option
		// User currently at home page.
		HomePageFactory homePage = new HomePageFactory();
		homePage.clickServiceRoleInAdmin();

		// (3)Perform search to ensure search actually works.
		// User Landed on Service Role Page
		ServiceRolePageFactory serviceRole = new ServiceRolePageFactory();
		serviceRole.setSearchBy(EXISTING_SERVICE_ROLE);
		serviceRole.clickSearch();
		assertEquals(serviceRole.getServiceRoleTable().readTableRowValue(PRIMARY_COLUMN, EXISTING_SERVICE_ROLE, "Service Role", true), EXISTING_SERVICE_ROLE, "Service Role Table: Search Value Present");

		// (4)Click Create a new Service Role
		serviceRole.clickCreateNewServiceRole();
		CreateNewServiceRolePageFactory newServiceRole = new CreateNewServiceRolePageFactory();

		// (5)Attempt to create without data - assert errors
		validateErrorsOnCreateNewServiceRole(newServiceRole);
		
		//(6)Validate errors when invalid data exists
		validateErrorsWhenInvalidDataEntered(newServiceRole);

		//(7)Attempt to save duplicate service code & service role
		// Note: This record already exists. Attempting a duplicate.
		validateDuplicateCannotExist(newServiceRole);
		
		//(8)Add a new Service Role from the datasheet (a temporary one), validate, then delete.
		addValidateDeleteNewServiceRole(newServiceRole);

	}// End execute

	/**
	 * Asserts that the error error messages happen when the user clicks create and
	 * enters no data
	 * 
	 * @param newServiceRole
	 */
	private void validateErrorsOnCreateNewServiceRole(CreateNewServiceRolePageFactory newServiceRole) {

		newServiceRole.clickCreate();

		// Validate messages with no data
		assertEquals(newServiceRole.readServiceCodeErrorMessage(),
				errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATENEWSERVICEROLE_code"), "Content"), "Service Code Error");
		assertEquals(newServiceRole.readServiceRoleErrorMessage(),
				errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATENEWSERVICEROLE_role"), "Content"), "Service Role Error");

	}
	
	/**
	 * Validates that character limit bounds are enforced.
	 * 
	 * @param newServiceRole
	 */
	private void validateErrorsWhenInvalidDataEntered (CreateNewServiceRolePageFactory newServiceRole) {
		
		// Validate messages with invalid data
		// Too Many Characters (Code - 5 char max)
		String serviceCodeText = "abc de";
		newServiceRole.setServiceCode(serviceCodeText);
		AutomationHelper.wait(1);

		// Too Many Characters (Role - 50 char max)
		String serviceRoleText = "abc def ghi jkl mno pqrs tuv wxyz ABC DEF GHI JKL M";
		newServiceRole.setServiceRole(serviceRoleText);
		AutomationHelper.wait(1);

		newServiceRole.clickCreate();

		assertEquals(newServiceRole.readServiceCodeErrorMessage(),
				errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATENEWSERVICEROLE_code_charmax"), "Content"), "Service Code Error - Char Max");
		assertEquals(newServiceRole.readServiceRoleErrorMessage(),
				errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATENEWSERVICEROLE_role_charmax"), "Content"), "Service Role Error - Char Max");
	
	}

	/**
	 * Asserts that duplicate values cannot exist for both the Service Code and the
	 * Service Role.
	 * 
	 * @param newServiceRole
	 */
	private void validateDuplicateCannotExist(CreateNewServiceRolePageFactory newServiceRole) {

		newServiceRole.setServiceCode("test");
		newServiceRole.setServiceRole("testCodeChars");

		newServiceRole.clickCreate();

		assertEquals(newServiceRole.readServiceCodeErrorMessage(),
				errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATENEWSERVICEROLE_code_duplicate"), "Content"),
				"Service Code Error - Duplicate");
		assertEquals(newServiceRole.readServiceRoleErrorMessage(),
				errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATENEWSERVICEROLE_role_duplicate"), "Content"),
				"Service Role Error - Duplicate");

	}
	
	/**
	 * Adds a new service code and role record from a randomly selected row in the
	 * data sheet. Then asserts that the values are added to the data sheet before
	 * deleting it.
	 * 
	 * @param newServiceRole
	 */
	private void addValidateDeleteNewServiceRole(CreateNewServiceRolePageFactory newServiceRole) {
		
		//Clear current values
		newServiceRole.clearServiceCode();
		newServiceRole.clearServiceRole();
		
		//Go to the datasheet and pick a random TEMPORARY record
		String serviceCode;
		String serviceRole;
		
		ExcelDataConfig serviceRolesFile = getExcelFile("JMEDAdminDataMap.xlsx", "ServiceRoleDataMap");
		
		List <Integer> rowIndexs = new ArrayList<Integer>();
		for(int i = 1; i <= serviceRolesFile.getRowCount(); i++) {
			
			//If the row is a Temporary row, then add it to the list.
			if(serviceRolesFile.getData(i, serviceRolesFile.getColumnIndex("Type")).equalsIgnoreCase("Temporary")) {
				rowIndexs.add(i);				
			}
		}
		
		//Generate a random number that is between 1 and the upper limit of the temporary records.
		Random rand = new Random();
		int rowIndex = rowIndexs.get(rand.nextInt(rowIndexs.size()));
		
		serviceCode = serviceRolesFile.getData(rowIndex, serviceRolesFile.getColumnIndex("ServiceCode"));
		serviceRole = serviceRolesFile.getData(rowIndex, serviceRolesFile.getColumnIndex("ServiceRole"));
		
		newServiceRole.setServiceCode(serviceCode);
		newServiceRole.setServiceRole(serviceRole);
		
		//Wait necessary to allow javascript errors time to go away.
		AutomationHelper.wait(1);
		
		newServiceRole.clickCreate();
		
		ServiceRolePageFactory serviceRoleIndex= new ServiceRolePageFactory();
		
		//Navigate to page with the value.
		serviceRoleIndex.gotoTablePageWithRow(serviceRoleIndex.getServiceRoleTable(), "Service Code", serviceCode);
		
		//Assert the values are present
		assertEquals(serviceRoleIndex.getServiceRoleTable().readTableRowValue("Service Code", serviceCode, "Service Code", true), serviceCode, "Service Role Table - New Service Code Validation");
		assertEquals(serviceRoleIndex.getServiceRoleTable().readTableRowValue("Service Code", serviceCode, "Service Role", true), serviceRole, "Service Role Table - New Service Role Validation");
		
		
		//Deletes the newly created record.
		serviceRoleIndex.getServiceRoleTable().clickLinkInRow(serviceCode, "Delete");
		
		DeleteServiceRolePageFactory deleteServiceRole = new DeleteServiceRolePageFactory();
		assertEquals(deleteServiceRole.readServiceCode(), serviceCode, "Delete Service Role - Service Code");
		assertEquals(deleteServiceRole.readServiceRole(), serviceRole, "Delete Service Role - Service Role");
		deleteServiceRole.clickDelete();	
	}
	


	/**
	 * Returns a random user of type DHA Super User from the UsersData.xlsx
	 * 
	 * @return String[]
	 */
	@DataProvider
	private String[] loginAccounts() {

		ExcelDataConfig usersFile = getExcelFile("UsersData.xlsx", "JMEDUserData");

		List<String> adminUserNames = new ArrayList<String>();

		// Sting[] to store the different types of admin accounts. We will want
		// to test for each of these.
		String[] adminTypes = { "DHA Super User" };

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
