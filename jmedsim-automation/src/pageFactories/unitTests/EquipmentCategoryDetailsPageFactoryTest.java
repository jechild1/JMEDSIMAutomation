package pageFactories.unitTests;

import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.EquipmentCategoryPages.EquipmentCategoryDetailsPageFactory;
import pageFactories.Admin.EquipmentCategoryPages.EquipmentCategoryPageFactory;
import utilities.AutomationHelper;

public class EquipmentCategoryDetailsPageFactoryTest extends TestBase {
	@Test
	public void execute() {
		LoginMod lm = new LoginMod();
		lm.execute("scott_DHA_system_admin@mail.mil");

		HomePageFactory hpf = new HomePageFactory();

		hpf.clickEquipmentCategoryInAdmin();

		EquipmentCategoryPageFactory ec = new EquipmentCategoryPageFactory();

		ec.getEquipmentCategoryTable().clickLinkInRow("Equipment Category",
				"E Category #1", "", "Details", false);

		EquipmentCategoryDetailsPageFactory ecd = new EquipmentCategoryDetailsPageFactory();
		//
		System.out.println(ecd.readEquipmentCategory());
		System.out.println(ecd.readDescription());
		System.out.println(ecd.isActiveChecked());
		//
		// ecd.clickEdit();
		// ecd.clickBackToList();

		AutomationHelper.waitSeconds(5);
	}
}
