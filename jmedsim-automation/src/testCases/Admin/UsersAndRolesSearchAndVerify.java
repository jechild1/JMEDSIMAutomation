package testCases.Admin;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.UsersAndRoles.EditAssignedRolePageFactory;
import pageFactories.Admin.UsersAndRoles.UserRoleDetailsPageFactory;
import pageFactories.Admin.UsersAndRoles.UsersAndRolesPageFactory;
import testCases.BaseTestScriptConfig;
import utilities.AutomationHelper;
import utilities.ExcelDataConfig;

/**
 * This test script navigates Admin > Users & Roles page with a System Admin or
 * DHA Super User role. It then searches for a random user and asserts that the
 * user is in the table. It then Looks at the Edit and Delete pages to assert
 * that they load with the correct data.
 * 
 * @author jesse.childress
 *
 */
public class UsersAndRolesSearchAndVerify extends BaseTestScriptConfig {
	
	String user;
	String role;
	String site;
	protected static final String PRIMARY_COLUMN = "User";

	@Test(dataProvider = "loginAccounts")
	public void execute(String userName) {

		// Login as a DHA System Admin or DHA Super User, which has access to the Admin
		// menu
		LoginMod login = new LoginMod();
		login.execute(userName);

		// Select a random user from the UsersData.xlsx file which starts with either
		// "jesse" or "scott", which prevents us from using some of the users in this
		// datafile that would otherwise be used for error checking and the like.
		selectRandomUserFromDatasheet();

		// Navigate to the Users & Roles page
		HomePageFactory homePage = new HomePageFactory();
		homePage.clickUsersAndRolesInAdmin();
		

		// Search for the user and validate it is in the table
		UsersAndRolesPageFactory usersAndRoles = new UsersAndRolesPageFactory();
		usersAndRoles.setSearchBy(user);
		usersAndRoles.clickSearch();
		
		//Navigate to the page with the value (should be on visible page)
		usersAndRoles.gotoTablePageWithRow(usersAndRoles.getUsersAndRolesTable(), "User", user);
		
		assertEquals(usersAndRoles.getUsersAndRolesTable().readTableRowValue(PRIMARY_COLUMN, user, "User", true), user, "Users and Roles Table - User Exist");
		assertEquals(usersAndRoles.getUsersAndRolesTable().readTableRowValue(PRIMARY_COLUMN, user, "Role", true), role, "Users and Roles Table - Role Exist");
		assertEquals(usersAndRoles.getUsersAndRolesTable().readTableRowValue(PRIMARY_COLUMN, user, "Site", true), site, "Users and Roles Table - Site Exist");

		

		// Click "Edit" to view the Edit Assigned Role page. Validate that the User ID
		// and the Role match the record.
		usersAndRoles.getUsersAndRolesTable().clickLinkInRow(user, "Edit");
		
		EditAssignedRolePageFactory editRole = new EditAssignedRolePageFactory();
		assertEquals(editRole.readUserIdSelected(), user, "Edit Assigned Role - User ID");
		assertEquals(editRole.readRoleSelected(), role, "Edit Assigned Role - Role");
		
		// Navigate back to the User & Role page.
//		editRole.clickBackToList();
		editRole.clickUsersAndRolesInAdmin();
		
		//Navigate to the details page
		usersAndRoles = new UsersAndRolesPageFactory();
		usersAndRoles.getUsersAndRolesTable().clickLinkInRow(user, "Details");
		
		//Read the data on the User Role Details page
		UserRoleDetailsPageFactory userRoleDetails = new UserRoleDetailsPageFactory();
		assertEquals(userRoleDetails.readUser(), user, "User Role Details - User");
		assertEquals(userRoleDetails.readRole(), role, "User Role Details - Role");
		
		userRoleDetails.clickBackToList();		
		
	}
	
	/**
	 * Utility method to select a random user, starting with jesse_ or scott_ in
	 * order to NOT use accounts meant for negative testing.
	 */
	private void selectRandomUserFromDatasheet() {
		
		ExcelDataConfig usersFile = getExcelFile("UsersData.xlsx", "JMEDUserData");
		
		//Create a list of users
		int rowCount = usersFile.getRowCount();
		
		boolean successfulMatch = false;
		do {
			//Generate a random row between 1 and the row count in the datasheet.
			//Starting on row 2 as not to be header row. Row count +1 as not to be 0 indexed.
			int randomRow = AutomationHelper.generateRandomInteger(2, rowCount+1);
			
			user = usersFile.getData(randomRow, usersFile.getColumnIndex("UserName"));
			
			if((user.startsWith("jesse_site"))||(user.startsWith("scott_site"))) {
				successfulMatch = true;
				role = usersFile.getData(randomRow, "Type");
				site = usersFile.getData(randomRow, "Site");
			}
			
		}while(!successfulMatch);
		
	}

	/**
	 * Returns a random user of type DHA System Admin or DHA Super User from the
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
		String[] adminTypes = { "DHA System Admin", "DHA Super User" };

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
		// DHA System Admin or DHA Super User
		return new String[] { returnArray[AutomationHelper.generateRandomInteger(0, arrayCount - 1)] };
	}

}
