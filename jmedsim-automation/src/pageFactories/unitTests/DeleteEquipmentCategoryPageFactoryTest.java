package pageFactories.unitTests;

import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.EquipmentCategoryPages.CreateNewEquipmentCategoryPageFactory;
import pageFactories.Admin.EquipmentCategoryPages.DeleteEquipmentCategoryPageFactory;
import pageFactories.Admin.EquipmentCategoryPages.EquipmentCategoryPageFactory;

public class DeleteEquipmentCategoryPageFactoryTest extends TestBase {
	@Test
	public void execute() {
		LoginMod lm = new LoginMod();
		lm.execute("scott_DHA_system_admin@mail.mil");

		HomePageFactory hpf = new HomePageFactory();

		hpf.clickEquipmentCategoryInAdmin();

		EquipmentCategoryPageFactory ec = new EquipmentCategoryPageFactory();
		ec.clickCreateNewEquipmentCategory();

		CreateNewEquipmentCategoryPageFactory cnec = new CreateNewEquipmentCategoryPageFactory();

		cnec.setEquipmentCategory("test equipment");
		cnec.setDescription("123 test site");
		cnec.setActiveCheckbox(false);

		cnec.clickCreate();

		ec.gotoTablePageWithRow(ec.getEquipmentCategoryTable(),
				"Equipment Category", "test equipment");

		ec.getEquipmentCategoryTable().clickLinkInRow("Equipment Category",
				"test equipment", "", "Delete", false);
		DeleteEquipmentCategoryPageFactory dec = new DeleteEquipmentCategoryPageFactory();
		
		System.out.println(dec.readEquipmentCategory());
		System.out.println(dec.readDescription());
		System.out.println(dec.isActiveChecked());

		dec.clickDelete();

	}
}
