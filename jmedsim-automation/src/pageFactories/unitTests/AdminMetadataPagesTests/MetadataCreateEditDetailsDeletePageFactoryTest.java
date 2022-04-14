package pageFactories.unitTests.AdminMetadataPagesTests;

import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.AdminMetadataPages.CreateMetadataPageFactory;
import pageFactories.Admin.AdminMetadataPages.DeleteMetadataPageFactory;
import pageFactories.Admin.AdminMetadataPages.EditMetadataPageFactory;
import pageFactories.Admin.AdminMetadataPages.MetadataDetailsPageFactory;
import pageFactories.Admin.AdminMetadataPages.MetadataPageFactory;
import pageFactories.unitTests.TestBase;
import utilities.AutomationHelper;

public class MetadataCreateEditDetailsDeletePageFactoryTest extends TestBase {
	@Test
	public void execute() {
		LoginMod lm = new LoginMod();
		lm.execute("scott_DHA_system_admin@mail.mil");

		HomePageFactory hpf = new HomePageFactory();

		hpf.clickMetadataDictionaryInAdmin();

		MetadataPageFactory m = new MetadataPageFactory();
		m.gotoTablePageWithRow(m.getMetadataTable(), "Metadata",
				"Chest CT Scan");

		m.getMetadataTable().clickLinkInRow("Metadata", "Chest CT Scan", "",
				"Edit", false);

		EditMetadataPageFactory em = new EditMetadataPageFactory();

		System.out.println(em.readMetadataCategorySelected());
		System.out.println(em.readMetadata());
		System.out.println(em.readDescription());
		System.out.println(em.isActiveChecked());

		em.setMetadata("");
		em.setDescription("");
		em.clickSave();

		System.out.println(em.readMetadataErrorMessage());

		// TODO: go back to "clickBackToList" once defect resolved
		// em.clickBackToList();
		em.clickMetadataDictionaryInAdmin();

		m.gotoTablePageWithRow(m.getMetadataTable(), "Metadata",
				"Chest CT Scan");

		m.getMetadataTable().clickLinkInRow("Metadata", "Chest CT Scan", "",
				"Delete", false);

		DeleteMetadataPageFactory dm = new DeleteMetadataPageFactory();

		System.out.println(dm.isAlertMessageOnPage());

		if (dm.isAlertMessageOnPage()) {
			System.out.println(dm.readAlertMessage());
		}
		dm.clickBackToList();

		m.gotoTablePageWithRow(m.getMetadataTable(), "Metadata",
				"Chest CT Scan");

		m.getMetadataTable().clickLinkInRow("Metadata", "Chest CT Scan", "",
				"Details", false);

		MetadataDetailsPageFactory md = new MetadataDetailsPageFactory();

		System.out.println(md.readMetadata());
		System.out.println(md.readDescription());
		System.out.println(md.isActiveChecked());
		System.out.println(md.readMetadataCategory());

		md.clickBackToList();

		m.clickCreateNewMetadata();

		CreateMetadataPageFactory cnm = new CreateMetadataPageFactory();

		cnm.selectMetadataCategory("CT Scan");
		cnm.setMetadata("NEW METADATA");
		cnm.setDescription("TEST METADATA DESCRIPTION");
		cnm.setActiveCheckbox(false);
		cnm.clickCreate();

		m.gotoTablePageWithRow(m.getMetadataTable(), "Metadata",
				"NEW METADATA");

		m.getMetadataTable().clickLinkInRow("Metadata", "NEW METADATA", "",
				"Delete", false);

		dm = new DeleteMetadataPageFactory();

		System.out.println(dm.readMetadata());
		System.out.println(dm.readDescription());
		System.out.println(dm.isActiveChecked());
		System.out.println(dm.readMetadataCategory());

		dm.clickDelete();

		AutomationHelper.waitSeconds(10);
	}
}
