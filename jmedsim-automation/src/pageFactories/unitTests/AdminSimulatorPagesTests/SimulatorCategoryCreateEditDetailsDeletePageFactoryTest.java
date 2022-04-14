package pageFactories.unitTests.AdminSimulatorPagesTests;

import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.AdminSimulatorPages.CreateNewSimulatorCategoryPageFactory;
import pageFactories.Admin.AdminSimulatorPages.DeleteSimulatorCategoryPageFactory;
import pageFactories.Admin.AdminSimulatorPages.EditSimulatorCategoryPageFactory;
import pageFactories.Admin.AdminSimulatorPages.SimulatorCategoryDetailsPageFactory;
import pageFactories.Admin.AdminSimulatorPages.SimulatorCategoryPageFactory;
import pageFactories.unitTests.TestBase;
import utilities.AutomationHelper;

public class SimulatorCategoryCreateEditDetailsDeletePageFactoryTest
		extends
			TestBase {
	@Test
	public void execute() {
		LoginMod lm = new LoginMod();
		lm.execute("scott_DHA_system_admin@mail.mil");

		HomePageFactory hpf = new HomePageFactory();

		hpf.clickSimulatorCategoryInAdmin();

		SimulatorCategoryPageFactory sc = new SimulatorCategoryPageFactory();
		sc.gotoTablePageWithRow(sc.getSimulatorCategoryTable(), "Category",
				"Simulator Category 2");

		sc.getSimulatorCategoryTable().clickLinkInRow("Category",
				"Simulator Category 2", "", "Edit", false);

		EditSimulatorCategoryPageFactory esc = new EditSimulatorCategoryPageFactory();

		System.out.println(esc.readCategory());
		System.out.println(esc.readDescription());
		System.out.println(esc.isActiveChecked());

		esc.setCategory("");
		esc.setDescription("");
		esc.clickSave();

		System.out.println(esc.readCategoryErrorMessage());
		System.out.println(esc.readDescriptionErrorMessage());

		esc.clickBackToList();

		sc.gotoTablePageWithRow(sc.getSimulatorCategoryTable(), "Category",
				"Simulator Category 2");

		sc.getSimulatorCategoryTable().clickLinkInRow("Category",
				"Simulator Category 2", "", "Delete", false);

		DeleteSimulatorCategoryPageFactory dsc = new DeleteSimulatorCategoryPageFactory();

		System.out.println(dsc.isAlertMessageOnPage());

		if (dsc.isAlertMessageOnPage()) {
			System.out.println(dsc.readAlertMessage());
		}
		dsc.clickBackToList();

		sc.gotoTablePageWithRow(sc.getSimulatorCategoryTable(), "Category",
				"Simulator Category 2");

		sc.getSimulatorCategoryTable().clickLinkInRow("Category",
				"Simulator Category 2", "", "Details", false);

		SimulatorCategoryDetailsPageFactory scd = new SimulatorCategoryDetailsPageFactory();

		System.out.println(scd.readCategory());
		System.out.println(scd.readDescription());
		System.out.println(scd.isActiveChecked());

		scd.clickBackToList();

		sc.clickCreateNewSimulatorCategory();

		CreateNewSimulatorCategoryPageFactory cnsc = new CreateNewSimulatorCategoryPageFactory();

		cnsc.setCategory("NEW CATEGORY");
		cnsc.setDescription("NEW CATEGORY DESC");
		cnsc.setActiveCheckbox(false);
		cnsc.clickCreate();

		sc.gotoTablePageWithRow(sc.getSimulatorCategoryTable(), "Category",
				"NEW CATEGORY");

		sc.getSimulatorCategoryTable().clickLinkInRow("Category",
				"NEW CATEGORY", "", "Delete", false);

		dsc = new DeleteSimulatorCategoryPageFactory();

		System.out.println(dsc.readCategory());
		System.out.println(dsc.readDescription());
		System.out.println(dsc.isActiveChecked());

		dsc.clickDelete();

		AutomationHelper.waitSeconds(10);
	}
}
