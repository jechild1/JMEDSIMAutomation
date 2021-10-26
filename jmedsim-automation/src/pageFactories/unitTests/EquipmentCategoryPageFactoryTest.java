package pageFactories.unitTests;

import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.EquipmentCategoryPages.EquipmentCategoryPageFactory;

public class EquipmentCategoryPageFactoryTest extends TestBase {
	@Test
	public void execute() {
		LoginMod lm = new LoginMod();
		lm.execute("scott_DHA_system_admin@mail.mil");

		HomePageFactory hpf = new HomePageFactory();

		hpf.clickEquipmentCategoryInAdmin();

		EquipmentCategoryPageFactory ec = new EquipmentCategoryPageFactory();

		// ec.clickCreateNewSite();
		//
		// ec.setSearchBy("test 4");
		// ec.clickSearch();
		//
		ec.gotoTablePageWithRow(ec.getEquipmentCategoryTable(),
				"Equipment Category", "E Category #1");

		System.out.println(ec.getEquipmentCategoryTable().readTableRowValue(
				"Equipment Category", "E Category #1", "Description", false));

	}
}
