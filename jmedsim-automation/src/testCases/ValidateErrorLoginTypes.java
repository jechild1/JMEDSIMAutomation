package testCases;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import modularScripts.LoginMod;
import utilities.ExcelDataConfig;

/**
 * Test Script
 * 
 * @author scott.brazelton
 *
 */
public class ValidateErrorLoginTypes extends BaseTestScriptConfig {

	/**
	 * This test script opens the JMEDSIM login page and validates possible
	 * error messages
	 * 
	 * Pre-Condition: All browsers closed
	 * 
	 * Post-Condition: All browsers closed. All modules validated
	 * 
	 * @param userName
	 */
	@Test(dataProvider = "dp", alwaysRun = true)
	public void execute(String userName) {

		LoginMod lm = new LoginMod();

		lm.execute(userName);
	}

	/**
	 * Date provider method that will return user id's to the execute method
	 * 
	 * @return String[]
	 */
	@DataProvider
	public String[] dp() {
		return getErrorGroupUsers();
	}

	/**
	 * Get users that are expected to fail at login
	 * 
	 * @return String[]
	 */
	private String[] getErrorGroupUsers() {

		ExcelDataConfig ud = getExcelFile("UsersData.xlsx", "JMEDUserData");

		List<String> userNamesList = new ArrayList<String>();

		int udRowCount = ud.getRowCount();
		for (int i = 1; i <= udRowCount; i++) {
			if (ud.getData(i, "Group").equals("ErrorTest")) {
				userNamesList.add(ud.getData(i, "UserName"));
			}
		}

		String[] userNamesArr = userNamesList
				.toArray(new String[userNamesList.size()]);

		return userNamesArr;
	}
}
