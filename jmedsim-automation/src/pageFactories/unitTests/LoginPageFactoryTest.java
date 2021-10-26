package pageFactories.unitTests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import modularScripts.LoginMod;
import utilities.AutomationHelper;

class LoginPageFactoryTest extends TestBase {

	/**
	 * This method gets the login data for JLV
	 * 
	 * @return String[][]
	 */
	@DataProvider(name = "loginInfo")
	private String[] getAccounts() {

		String userName = "test@mail.mil";

		return new String[]{userName};
	}

	@Test(dataProvider = "loginInfo")
	public void execute(String userName) {
		AutomationHelper.printMethodName();

		// LoginPageFactory lpf = new LoginPageFactory();
		// lpf.loadPage();
		//
		// System.out.println(lpf.readPageTitle());
		//
		// lpf.setEmail("test@mail.mil");
		//
		// lpf.setPassword("test2");
		//
		// System.out.println(lpf.readEmailError());
		// System.out.println(lpf.readPasswordError());
		//
		// lpf.setRememberMeCheckbox(true);
		//
		// lpf.clickLogin();
		//
		// System.out.println(lpf.getCurrentUrl());
		//
		// System.out.println(lpf.readSummaryErrors());
		//
		// AutomationHelper.wait(3);
		//
		//
		// lpf.clickRegisterAsANewUser();
		//
		// AutomationHelper.wait(3);
		//
		LoginMod lm = new LoginMod();

		lm.execute(userName);

	}

}
