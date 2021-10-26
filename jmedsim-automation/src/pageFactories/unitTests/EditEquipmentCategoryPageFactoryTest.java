package pageFactories.unitTests;

import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.EquipmentCategoryPages.EditEquipmentCategoryPageFactory;
import pageFactories.Admin.EquipmentCategoryPages.EquipmentCategoryPageFactory;

public class EditEquipmentCategoryPageFactoryTest extends TestBase {
	@Test
	public void execute() {
		LoginMod lm = new LoginMod();
		lm.execute("scott_DHA_system_admin@mail.mil");

		HomePageFactory hpf = new HomePageFactory();

		hpf.clickEquipmentCategoryInAdmin();

		EquipmentCategoryPageFactory ec = new EquipmentCategoryPageFactory();

		ec.getEquipmentCategoryTable().clickLinkInRow("Equipment Category",
				"E Category #1", "", "Edit", false);

		EditEquipmentCategoryPageFactory eec = new EditEquipmentCategoryPageFactory();

		System.out.println(eec.readEquipmentCategory());
		System.out.println(eec.readDescription());

		eec.setEquipmentCategory("");
		eec.setDescription("");
		eec.clickSave();

		System.out.println(eec.readEquipmentCategoryErrorMessage());
		System.out.println(eec.readDescriptionErrorMessage());

	}
}
