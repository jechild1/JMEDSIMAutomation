package pageFactories.unitTests;

import org.testng.annotations.AfterClass;

import utilities.AutomationHelper;

public class TestBase {

	@AfterClass
	public void afterClass() {
		AutomationHelper.finishTest();
	}

}
