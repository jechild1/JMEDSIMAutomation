package pageFactories.unitTests;

import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.EquipmentCategoryPages.CreateNewEquipmentCategoryPageFactory;
import pageFactories.Admin.EquipmentCategoryPages.EquipmentCategoryPageFactory;
import utilities.AutomationHelper;

public class CreateNewEquipmentCategoryPageFactoryTest extends TestBase {
	@Test
	public void execute() {
		LoginMod lm = new LoginMod();
		lm.execute("scott_DHA_system_admin@mail.mil");

		HomePageFactory hpf = new HomePageFactory();

		hpf.clickEquipmentCategoryInAdmin();

		EquipmentCategoryPageFactory ec = new EquipmentCategoryPageFactory();
		ec.clickCreateNewEquipmentCategory();

		CreateNewEquipmentCategoryPageFactory cnec = new CreateNewEquipmentCategoryPageFactory();

		cnec.clickCreate();
		//
		System.out.println(cnec.readEquipmentCategoryErrorMessage());
		System.out.println(cnec.readDescriptionErrorMessage());

		cnec.setEquipmentCategory("test equipment");
		cnec.setDescription("123 test site");
		cnec.setActiveCheckbox(false);

		AutomationHelper.wait(10);
	}
}
