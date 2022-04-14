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
		AutomationHelper.waitSeconds(5);

		// ADMIN
		hpf.clickUsersAndRolesInAdmin();
		AutomationHelper.waitSeconds(5);

		hpf.clickSitesInAdmin();
		AutomationHelper.waitSeconds(5);

		hpf.clickEquipmentCategoryInAdmin();
		AutomationHelper.waitSeconds(5);

		hpf.clickTargetedLearnerInAdmin();
		AutomationHelper.waitSeconds(5);

		hpf.clickServiceRoleInAdmin();
		AutomationHelper.waitSeconds(5);
		hpf.clickTrainingTypeInAdmin();
		AutomationHelper.waitSeconds(5);
		hpf.clickLearnerProfileInAdmin();
		AutomationHelper.waitSeconds(5);

		hpf.clickContentInAdmin();
		AutomationHelper.waitSeconds(5);
		hpf.clickContentTypeInAdmin();
		AutomationHelper.waitSeconds(5);

		hpf.clickMetadataDictionaryInAdmin();
		AutomationHelper.waitSeconds(5);
		hpf.clickMetadataCategoryInAdmin();
		AutomationHelper.waitSeconds(5);

		hpf.clickSimulatorInAdmin();
		AutomationHelper.waitSeconds(5);
		hpf.clickSimulatorCategoryInAdmin();
		AutomationHelper.waitSeconds(5);

		hpf.clickProfileInHelloUser();
		AutomationHelper.waitSeconds(5);

		hpf.clickManageAccountInHelloUser();
		AutomationHelper.waitSeconds(5);

		hpf.clickLogOffInHelloUser();
		AutomationHelper.waitSeconds(5);
	}

}
