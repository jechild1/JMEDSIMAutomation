package testCases;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import utilities.ExcelDataConfig;

public class LoginUsersWhoAreAboutToExpire extends BaseTestScriptConfig {

	/**
	 * This test script opens the JMEDSIM login page logs in users that are
	 * about to expire (within 10 days)
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

		HomePageFactory h = new HomePageFactory();
		h.clickLogOffInHelloUser();

		softAsserter.assertAll();
	}

	/**
	 * Date provider method that will return user id's to the execute method
	 * 
	 * @return String[]
	 */
	@DataProvider
	public String[] dp() {
		return getUsersAboutToExpire();
	}

	/**
	 * Get users that need a login
	 * 
	 * @return String[]
	 */
	private String[] getUsersAboutToExpire() {

		// First get 20 days in the past
		LocalDateTime dtCutoff = LocalDateTime.now().minusDays(20);

		ExcelDataConfig ud = getExcelFile("UsersData.xlsx", "JMEDUserData");

		List<String> userNamesList = new ArrayList<String>();

		int udRowCount = ud.getRowCount();
		for (int i = 1; i <= udRowCount; i++) {

			// Get last time a user logged in
			String lastLoginString = ud.getData(i, "LastLoginDate");
			String group = ud.getData(i, "Group").toLowerCase();

			// Try to parse the date - if no date present, use MIN date
			LocalDateTime lastLoginDate = lastLoginString == ""
					? LocalDateTime.MIN
					: LocalDateTime.parse(lastLoginString,
							DateTimeFormatter.ofPattern("yyyy/MM/dd HH.mm.ss"));

			// Check if the last login date is older than 20 days & is not part
			// of the error test group (since these won't login ever)
			if (lastLoginDate.isBefore(dtCutoff)
					&& !group.equals("errortest")) {

				// if older than 20 days add to user list that needs to login
				userNamesList.add(ud.getData(i, "UserName"));
			}
		}

		String[] userNamesArr = userNamesList
				.toArray(new String[userNamesList.size()]);

		return userNamesArr;
	}
}
