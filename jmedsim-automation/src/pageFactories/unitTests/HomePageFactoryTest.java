package pageFactories.unitTests;

import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import utilities.AutomationHelper;

public class HomePageFactoryTest extends TestBase {

	@Test
	public void execute() {
		LoginMod lm = new LoginMod();
		lm.execute("scott_DHA_system_admin@mail.mil");

		HomePageFactory hpf = new HomePageFactory();

		// My Menu
		hpf.clickSimulationInMyMenu();
		AutomationHelper.wait(5);

		// ADMIN
		hpf.clickUsersAndRolesInAdmin();
		AutomationHelper.wait(5);

		hpf.clickSitesInAdmin();
		AutomationHelper.wait(5);

		hpf.clickEquipmentCategoryInAdmin();
		AutomationHelper.wait(5);

		hpf.clickTargetedLearnerInAdmin();
		AutomationHelper.wait(5);

		hpf.clickServiceRoleInAdmin();
		AutomationHelper.wait(5);
		hpf.clickTrainingTypeInAdmin();
		AutomationHelper.wait(5);
		hpf.clickLearnerProfileInAdmin();
		AutomationHelper.wait(5);

		hpf.clickContentInAdmin();
		AutomationHelper.wait(5);
		hpf.clickContentTypeInAdmin();
		AutomationHelper.wait(5);

		hpf.clickMetadataDictionaryInAdmin();
		AutomationHelper.wait(5);
		hpf.clickMetadataCategoryInAdmin();
		AutomationHelper.wait(5);

		hpf.clickSimulatorInAdmin();
		AutomationHelper.wait(5);
		hpf.clickSimulatorCategoryInAdmin();
		AutomationHelper.wait(5);

		hpf.clickProfileInHelloUser();
		AutomationHelper.wait(5);

		hpf.clickManageAccountInHelloUser();
		AutomationHelper.wait(5);

		hpf.clickLogOffInHelloUser();
		AutomationHelper.wait(5);
	}

}
