package testCases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.HelloUserMenuPages.EditUserProfilePageFacory;
import utilities.ExcelDataConfig;

/**
 * This test
 * 
 * @author jesse.childress
 *
 */
public class EditUserProfile extends BaseTestScriptConfig {
	
	// These variables are used to store the original datasheet values so that
	// we can return the record to the state in which we found it.
	String originalUserId; 
	String originalDisplayName;
	String originalSite;
	String originalSecurityQuestion1;
	String originalSecurityAnswer1;
	String originalSecurityQuestion2;
	String originalSecurityAnswer2;
	String originalSecurityQuestion3;
	String originalSecurityAnswer3;
	String originalTermsAndConditions;
	
	// These variables are used to modify the record and assert that the change
	// took place.
	String modifiedDisplayName = "TEMP Display Name";
	String modifiedSite = "TATRC";
	String modifiedSecurityQuestion1 = "Where were you when you had your first kiss?";
	String modifiedSecurityAnswer1 = "I don't kiss and tell";
	String modifiedSecurityQuestion2 = "Who was your childhood hero?";
	String modifiedSecurityAnswer2 = "Frankenstien";
	String modifiedSecurityQuestion3 = "What was the first film you saw in the theater?";
	String modifiedSecurityAnswer3 = "Goonies";
//	String modifiedTermsAndConditions = "False";
	


	/**
	 * This test method ensures that the Edit User Profile feature accepts new
	 * values and saves. It performs the following functions:
	 * <ul>
	 * <li>Logs into the application with one of two data providers in this
	 * class. Can be used with userLoginList() or userLoginSingle();
	 * <li>Checks the user profile worksheet to see of the user has been used
	 * before. If not, adds that user to the worksheet.
	 * <li>Navigates to the Edit User Profile page.
	 * <li>Ensure that the user has values in the datasheet. If not, take the
	 * values that are currently in the application under the Edit User Profile
	 * page and write them to the datasheet.
	 * <li> Captures the original data values so that we can change the record back to its original state.
	 * <li> Performs an assertion that exist in the application match the dataset.
	 * <li> Sets the application fields to new, temporary values.
	 * <li> Click save. This action re-directs user to the Home Page. Then Navigate back to Edit User Profile Page.
	 * <li> Performs an assertion that the new fields values are in the application, and are checked against the new fields values that were written to the dataset.
	 * <li> Sets the original values back in the application
	 * <li> Clicks save. This action re-directs user to the Home Page. Then navigate back to the Edit User Profile Page.
	 * <li> Write the original values back to the dataset.
	 * <li> Assert that the original values are re-written to the dataset.
	 * </ul>
	 * 
	 * @param userName
	 */
	@Test(dataProvider = "userLoginSingle")
	public void editUserProfileAndSave(String userName) {

		// Perform Login function
		LoginMod login = new LoginMod();
		login.execute(userName);
		Reporter.log("Logged in with user: " + userName, true);

		// Create a new Datafile of ONLY the user profile data
		ExcelDataConfig userProfile = getExcelFile("UsersData.xlsx", "UserProfile");
		
		//If the user doesn't exist in the UserProfile worksheet inside of UsersData.xlsx, then add them.
		writeNewUserProfileRowIfNotExist(userName, userProfile);
		
		//Navigate to the Edit User Profile page.
		HomePageFactory home = new HomePageFactory();
		home.clickProfileInHelloUser();
		
		// In the event that we have a user that does not have data in the
		// datasheet, take the values that are currently in the application and
		// write them to the datasheet.
		populateDataFileWhereEmptyColumnValuesExist(userName);
		
		//Capture the original values;
		captureCurrentDataValues();

		//Assert values in datasheet are in application
		assertFieldValues(userName);
		
		//Set new values
		setNewFieldValues();
		
		//Save page values
		EditUserProfilePageFacory ep = new EditUserProfilePageFacory();
		ep.clickSaveButton();
		//Clicking save takes user to home page. Instantiate it again as to call for wait for page to load. Not waiting can cause timing issues.
		home = new HomePageFactory();
		home.clickProfileInHelloUser();
		
		//Write new values to datasheet
		writeCurrentValuesToDatasheet(userName);
		
		//Assert values in datasheet are in application (after edit)
		assertFieldValues(userName);
		
		//Set the original field values back to what they were
		setOriginalFieldValues();
		
		//Save page values
		ep.clickSaveButton();
		//Clicking save takes user to home page
		home = new HomePageFactory();
		home.clickProfileInHelloUser();
		
		//Write the original values back to the datasheet
		writeCurrentValuesToDatasheet(userName);
		
		//Assert values in datasheet are in application (after setting back to original)
		assertFieldValues(userName);
	}
	
	/**
	 * This method looks at a row for a specific user and loops through each
	 * column header looking for a value. If there is NOT a value in one of the
	 * columns, it takes the value that is currently set in the application and
	 * writes it to the datasheet as a baseline.
	 * 
	 * @param userName
	 */
	private void populateDataFileWhereEmptyColumnValuesExist(String userName) {
		
		ExcelDataConfig userProfile = getExcelFile("UsersData.xlsx", "UserProfile");
		EditUserProfilePageFacory ep = new EditUserProfilePageFacory();
		
		//Get the row index ahead of time.
		int rowIndex = userProfile.getRowIndex("UserName", userName);
		
		List<String> columnHeadersList = new ArrayList<>(Arrays.asList("UserID", "DisplayName", "Site", "SecurityQuestion1", "SecurityAnswer1", "SecurityQuestion2", "SecurityAnswer2", "SecurityQuestion3", "SecurityAnswer3", "TermsAndConditions"));
	
		//Loop through each column header, as outlined in the list above.
		for (String currentColHeader : columnHeadersList) {
			// If there is no value in the column header, e.g. empty string,
			// then write the value that is currently in the application into
			// the datasheet.
			if (userProfile.getData(rowIndex, currentColHeader).equals("")) {
				switch (currentColHeader) {
					case "UserID":
						userProfile.writeToWorkSheet(rowIndex,	userProfile.getColumnIndex(currentColHeader), ep.readUserID());
						break;

					case "DisplayName":
						userProfile.writeToWorkSheet(rowIndex,	userProfile.getColumnIndex(currentColHeader), ep.readDisplayName());
						break;

					case "Site":
						userProfile.writeToWorkSheet(rowIndex,	userProfile.getColumnIndex(currentColHeader), ep.readSite());
						break;

					case "SecurityQuestion1":
						userProfile.writeToWorkSheet(rowIndex,	userProfile.getColumnIndex(currentColHeader), ep.readSecurityQuestion1());
						break;

					case "SecurityAnswer1":
						userProfile.writeToWorkSheet(rowIndex,	userProfile.getColumnIndex(currentColHeader), ep.readSecurityAnswer1());
						break;

					case "SecurityQuestion2":
						userProfile.writeToWorkSheet(rowIndex,	userProfile.getColumnIndex(currentColHeader), ep.readSecurityQuestion2());
						break;

					case "SecurityAnswer2":
						userProfile.writeToWorkSheet(rowIndex,	userProfile.getColumnIndex(currentColHeader), ep.readSecurityAnswer2());
						break;

					case "SecurityQuestion3":
						userProfile.writeToWorkSheet(rowIndex,	userProfile.getColumnIndex(currentColHeader), ep.readSecurityQuestion3());
						break;

					case "SecurityAnswer3":
						userProfile.writeToWorkSheet(rowIndex,	userProfile.getColumnIndex(currentColHeader), ep.readSecurityAnswer3());
						break;

					case "TermsAndConditions":
						userProfile.writeToWorkSheet(rowIndex,	userProfile.getColumnIndex(currentColHeader), Boolean.valueOf(ep.readTermsAndConditions()).toString());
						break;

				}
			}
		}
	}

	/**
	 * Creates a new user profile tab row if the user name can not be found.
	 * 
	 * @param userName
	 * @param userProfile
	 */
	private void writeNewUserProfileRowIfNotExist(String userName,
			ExcelDataConfig userProfile) {
		// Not the most efficient, but keeps user from having to add to the
		// UserProfile tab themselves
		int rowCount = userProfile.getRowCount();
		boolean found = false;

		for (int i = 1; i <= rowCount; i++) {
			if (userProfile.getData(i, "UserName").equals(userName)) {
				found = true;
			}
		}

		if (!found) {
			userProfile.writeToWorkSheet(rowCount + 1,
					userProfile.getColumnIndex("UserName"), userName);
		}
	}
	
	/**
	 * Capture the original data values on the Edit User Profile Page so that we
	 * can change them back to the originals later.
	 */
	private void captureCurrentDataValues() {
		EditUserProfilePageFacory ep = new EditUserProfilePageFacory();
		
		 originalUserId = ep.readUserID();
		 originalDisplayName = ep.readDisplayName();
		 originalSite = ep.readSite();
		 originalSecurityQuestion1 = ep.readSecurityQuestion1();
		 originalSecurityAnswer1 = ep.readSecurityAnswer1();
		 originalSecurityQuestion2 = ep.readSecurityQuestion2();
		 originalSecurityAnswer2 = ep.readSecurityAnswer2();
		 originalSecurityQuestion3 = ep.readSecurityQuestion3();
		 originalSecurityAnswer3 = ep.readSecurityAnswer3();
		 originalTermsAndConditions = String.valueOf(ep.readTermsAndConditions());
	}
	
	/**
	 * This method asserts that values in the application match the values in
	 * the dataset for the passed in user name. <br>
	 * Note: This method performs the assert AFTER ALL fields are validated and
	 * will kill the test if they do not match.
	 * 
	 * @param userName
	 */
	private void assertFieldValues(String userName) {

		EditUserProfilePageFacory ep = new EditUserProfilePageFacory();
		ExcelDataConfig userProfile = getExcelFile("UsersData.xlsx", "UserProfile");
		
		//Get row index once instead of multiple times
		int rowIndex = userProfile.getRowIndex("UserName", userName);
		
		softAsserter.assertEquals(ep.readUserID(), userProfile.getData(rowIndex, "UserID"), "User Id Validation:");
		softAsserter.assertEquals(ep.readDisplayName(), userProfile.getData(rowIndex, "DisplayName"), "Display Name Validation:");
		softAsserter.assertEquals(ep.readSite(), userProfile.getData(rowIndex, "Site"), "Original Site Validation:");
		softAsserter.assertEquals(ep.readSecurityQuestion1(), userProfile.getData(rowIndex, "SecurityQuestion1"), "Security Question 1 Validation:");
		softAsserter.assertEquals(ep.readSecurityAnswer1(), userProfile.getData(rowIndex, "SecurityAnswer1"), "Security Answer 1 Validation:");
		softAsserter.assertEquals(ep.readSecurityQuestion2(), userProfile.getData(rowIndex, "SecurityQuestion2"), "Security Question 2 Validation:");
		softAsserter.assertEquals(ep.readSecurityAnswer2(), userProfile.getData(rowIndex, "SecurityAnswer2"), "Security Answer 2 Validation:");
		softAsserter.assertEquals(ep.readSecurityQuestion3(), userProfile.getData(rowIndex, "SecurityQuestion3"), "Security Question 3 Validation:");
		softAsserter.assertEquals(ep.readSecurityAnswer3(), userProfile.getData(rowIndex, "SecurityAnswer3"), "Security Answer 3 Validation:");
		softAsserter.assertEquals(ep.readTermsAndConditions(), Boolean.valueOf(userProfile.getData(rowIndex, "TermsAndConditions")), "Terms And Conditions Validation:");
				
		softAsserter.assertAll();
	}
	
	/**
	 * Sets the fields on the Edit User Profile Page to new, temporary values.
	 */
	private void setNewFieldValues() {
		//NOTE: User ID cannot be modified
		EditUserProfilePageFacory ep = new EditUserProfilePageFacory();
		
		ep.setDisplayName(modifiedDisplayName);
		ep.selectSite(modifiedSite);
		ep.selectSecurityQuestion1(modifiedSecurityQuestion1);
		ep.setSecurityAnswer1(modifiedSecurityAnswer1);
		ep.selectSecurityQuestion2(modifiedSecurityQuestion2);
		ep.setSecurityAnswer2(modifiedSecurityAnswer2);
		ep.selectSecurityQuestion3(modifiedSecurityQuestion3);
		ep.setSecurityAnswer3(modifiedSecurityAnswer3);
//		ep.setAgreeToTermsAndConditionsCheckbox(Boolean.valueOf(modifiedTermsAndConditions));
	}
	
	/**
	 * Sets the fields on the Edit User Profile Page to the original field values.
	 */
	private void setOriginalFieldValues() {
		//NOTE: User ID cannot be modified
		EditUserProfilePageFacory ep = new EditUserProfilePageFacory();
		
		ep.setDisplayName(originalDisplayName);
		ep.selectSite(originalSite);
		ep.selectSecurityQuestion1(originalSecurityQuestion1);
		ep.setSecurityAnswer1(originalSecurityAnswer1);
		ep.selectSecurityQuestion2(originalSecurityQuestion2);
		ep.setSecurityAnswer2(originalSecurityAnswer2);
		ep.selectSecurityQuestion3(originalSecurityQuestion3);
		ep.setSecurityAnswer3(originalSecurityAnswer3);
		ep.setAgreeToTermsAndConditionsCheckbox(Boolean.valueOf(originalTermsAndConditions));
	}
	
	/**
	 * Writes the current values of the Edit User Profile page to the datasheet.
	 * 
	 * @param userName
	 */
	private void writeCurrentValuesToDatasheet(String userName) {
		EditUserProfilePageFacory ep = new EditUserProfilePageFacory();

		ExcelDataConfig userProfile = getExcelFile("UsersData.xlsx", "UserProfile");
		
		//Get row index once instead of multiple times
		int rowIndex = userProfile.getRowIndex("UserName", userName);
		
		userProfile.writeToWorkSheet(rowIndex, userProfile.getColumnIndex("UserID"), ep.readUserID());
		userProfile.writeToWorkSheet(rowIndex, userProfile.getColumnIndex("DisplayName"), ep.readDisplayName());
		userProfile.writeToWorkSheet(rowIndex, userProfile.getColumnIndex("Site"), ep.readSite());
		userProfile.writeToWorkSheet(rowIndex, userProfile.getColumnIndex("SecurityQuestion1"), ep.readSecurityQuestion1());
		userProfile.writeToWorkSheet(rowIndex, userProfile.getColumnIndex("SecurityAnswer1"), ep.readSecurityAnswer1());
		userProfile.writeToWorkSheet(rowIndex, userProfile.getColumnIndex("SecurityQuestion2"), ep.readSecurityQuestion2());
		userProfile.writeToWorkSheet(rowIndex, userProfile.getColumnIndex("SecurityAnswer2"), ep.readSecurityAnswer2());
		userProfile.writeToWorkSheet(rowIndex, userProfile.getColumnIndex("SecurityQuestion3"), ep.readSecurityQuestion3());
		userProfile.writeToWorkSheet(rowIndex, userProfile.getColumnIndex("SecurityAnswer3"), ep.readSecurityAnswer3());
		userProfile.writeToWorkSheet(rowIndex, userProfile.getColumnIndex("TermsAndConditions"), String.valueOf(ep.readTermsAndConditions()));	
		
	}

	/**
	 * Data provider to retrieve a String[] of Login accounts. <br>
	 * We will only retrieve accounts that have the name Jesse in it, and that
	 * are not part of error logging.
	 * 
	 * @return String[]
	 */
	@DataProvider
	private String[] userLoginList() {
	
		// Create new datafile object
		ExcelDataConfig usersFile = getExcelFile("UsersData.xlsx",
				"JMEDUserData");
	
		// Create a new list that will be used to store relevant user names.
		List<String> usersList = new ArrayList<String>();
	
		// Obtain the row count so we will not how many times we need to loop
		int rowCount = usersFile.getRowCount();
	
		// Loop through each row in the datasheet and add each row that is not
		// an Error Checking row, and that belongs to Jesse, to the list.
		for (int i = 1; i <= rowCount; i++) {
	
			// If the users file is not part of any error checking and it
			// belongs to Jesse then use it. Don't want to mess ID's up for
			// other team members.
			if (usersFile.getData(i, "Group").equals("")
					&& usersFile.getData(i, "UserName").contains("jesse")) {
	
				usersList.add(usersFile.getData(i, "UserName"));
			}
		}
		return usersList.stream().toArray(String[]::new);
	}
	
	/**
	 * Data provider to return a single hard-coded login account.
	 * @return String[]
	 */
	@DataProvider
	private String[] userLoginSingle() {
		return new String[] {"jesse_site_TGoldenState_super_user@mail.mil"};
	}

}
