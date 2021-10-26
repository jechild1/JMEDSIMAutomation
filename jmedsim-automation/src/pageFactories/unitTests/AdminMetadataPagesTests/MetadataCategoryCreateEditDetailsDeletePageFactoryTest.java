package pageFactories.unitTests.AdminMetadataPagesTests;

import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.AdminMetadataPages.CreateMetadataCategoryPageFactory;
import pageFactories.Admin.AdminMetadataPages.DeleteMetadataCategoryPageFactory;
import pageFactories.Admin.AdminMetadataPages.EditMetadataCategoryPageFactory;
import pageFactories.Admin.AdminMetadataPages.MetadataCategoryDetailsPageFactory;
import pageFactories.Admin.AdminMetadataPages.MetadataCategoryPageFactory;
import pageFactories.unitTests.TestBase;
import utilities.AutomationHelper;

public class MetadataCategoryCreateEditDetailsDeletePageFactoryTest
		extends
			TestBase {
	@Test
	public void execute() {
		LoginMod lm = new LoginMod();
		lm.execute("scott_DHA_system_admin@mail.mil");

		HomePageFactory hpf = new HomePageFactory();

		hpf.clickMetadataCategoryInAdmin();

		MetadataCategoryPageFactory mc = new MetadataCategoryPageFactory();
		mc.gotoTablePageWithRow(mc.getMetadataCategoryTable(),
				"Metadata Category", "CT Scan");

		mc.getMetadataCategoryTable().clickLinkInRow("Metadata Category",
				"CT Scan", "", "Edit", false);

		EditMetadataCategoryPageFactory emc = new EditMetadataCategoryPageFactory();

		System.out.println(emc.readMetadataCategory());
		System.out.println(emc.isActiveChecked());

		emc.setMetadataCategory("");
		emc.clickSave();

		System.out.println(emc.readMetadataCategoryErrorMessage());

		// TODO: revert back to "clickBackToList" once fixed
		// emc.clickBackToList();
		emc.clickMetadataCategoryInAdmin();

		mc.gotoTablePageWithRow(mc.getMetadataCategoryTable(),
				"Metadata Category", "CT Scan");

		mc.getMetadataCategoryTable().clickLinkInRow("Metadata Category",
				"CT Scan", "", "Delete", false);

		DeleteMetadataCategoryPageFactory dmc = new DeleteMetadataCategoryPageFactory();

		System.out.println(dmc.isAlertMessageOnPage());

		if (dmc.isAlertMessageOnPage()) {
			System.out.println(dmc.readAlertMessage());
		}
		dmc.clickBackToList();

		mc.gotoTablePageWithRow(mc.getMetadataCategoryTable(),
				"Metadata Category", "CT Scan");

		mc.getMetadataCategoryTable().clickLinkInRow("Metadata Category",
				"CT Scan", "", "Details", false);

		MetadataCategoryDetailsPageFactory mcd = new MetadataCategoryDetailsPageFactory();

		System.out.println(mcd.readMetadataCategory());
		System.out.println(mcd.isActiveChecked());

		mcd.clickBackToList();

		mc.clickCreateMetadataCategory();

		CreateMetadataCategoryPageFactory cnct = new CreateMetadataCategoryPageFactory();

		cnct.setMetadataCategory("NEW METADATA CATEGORY");
		cnct.setActiveCheckbox(false);
		cnct.clickCreate();

		mc.gotoTablePageWithRow(mc.getMetadataCategoryTable(),
				"Metadata Category", "NEW METADATA CATEGORY");

		mc.getMetadataCategoryTable().clickLinkInRow("Metadata Category",
				"NEW METADATA CATEGORY", "", "Delete", false);

		dmc = new DeleteMetadataCategoryPageFactory();

		System.out.println(dmc.readMetadataCategory());
		System.out.println(dmc.isActiveChecked());

		dmc.clickDelete();

		AutomationHelper.wait(10);
	}
}
