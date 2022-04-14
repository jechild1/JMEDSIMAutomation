package pageFactories.unitTests.AdminContentPagesTests;

import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.AdminContentPages.AddMetadataToContentPageFactory;
import pageFactories.Admin.AdminContentPages.ContentDetailsPageFactory;
import pageFactories.Admin.AdminContentPages.ContentPageFactory;
import pageFactories.Admin.AdminContentPages.CreateContentPageFactory;
import pageFactories.Admin.AdminContentPages.DeleteContentPageFactory;
import pageFactories.Admin.AdminContentPages.EditContentPageFactory;
import pageFactories.unitTests.TestBase;
import utilities.AutomationHelper;

public class ContentCreateEditDetailsDeletePageFactoryTest extends TestBase {
	@Test
	public void execute() {
		LoginMod lm = new LoginMod();
		lm.execute("scott_DHA_system_admin@mail.mil");

		HomePageFactory hpf = new HomePageFactory();

		hpf.clickContentInAdmin();

		ContentPageFactory c = new ContentPageFactory();
		c.gotoTablePageWithRow(c.getContentTable(), "Content Name", "sfsdfsf");

		c.getContentTable().clickLinkInRow("Content Name", "sfsdfsf", "",
				"Edit", false);

		EditContentPageFactory ec = new EditContentPageFactory();

		System.out.println(ec.readContentTypeSelected());
		System.out.println(ec.readContentName());
		System.out.println(ec.readContentDescription());
		System.out.println(ec.readContentPath());
		System.out.println(ec.readMetadata());
		System.out.println(ec.isActiveChecked());
		System.out.println(ec.readUpdatedBy());
		System.out.println(ec.readUpdatedOn());

		ec.setContentName("");
		ec.setContentDescription("");
		ec.setContentPath("");
		ec.clickSave();

		System.out.println(ec.readContentNameErrorMessage());
		System.out.println(ec.readContentDescriptionErrorMessage());
		System.out.println(ec.readContentPathErrorMessage());

		// TODO: back to list broken on edit here
		// ec.clickBackToList();
		ec.clickContentInAdmin();

		c.gotoTablePageWithRow(c.getContentTable(), "Content Name", "sfsdfsf");

		c.getContentTable().clickLinkInRow("Content Name", "sfsdfsf", "",
				"Delete", false);

		DeleteContentPageFactory dc = new DeleteContentPageFactory();

		System.out.println(dc.isAlertMessageOnPage());

		if (dc.isAlertMessageOnPage()) {
			System.out.println(dc.readAlertMessage());
		}
		dc.clickBackToList();

		c.gotoTablePageWithRow(c.getContentTable(), "Content Name", "sfsdfsf");

		c.getContentTable().clickLinkInRow("Content Name", "sfsdfsf", "",
				"Details", false);

		ContentDetailsPageFactory cd = new ContentDetailsPageFactory();

		System.out.println(cd.readContentName());
		System.out.println(cd.readDescription());
		System.out.println(cd.readContentPath());
		System.out.println(cd.readContentType());
		System.out.println(cd.isActiveChecked());

		cd.clickAddMetadata();

		AddMetadataToContentPageFactory amtc = new AddMetadataToContentPageFactory();

		amtc.getAddMetadataToContentTable().clickLinkInRow("Metadata",
				"does not add, but don't error check either.", "",
				"Add Metadata", false);

		cd.getContentMetadataTable().clickLinkInRow("Metadata",
				"does not add, but don't error check either.", "", "Delete",
				false);

		cd.clickOKOnAlert();

		// TODO: clickBackToList doesn't work currently
		// cd.clickBackToList();
		cd = new ContentDetailsPageFactory();
		cd.clickContentInAdmin();

		c = new ContentPageFactory();
		c.clickCreateNewContent();

		CreateContentPageFactory cc = new CreateContentPageFactory();

		cc.setContentName("NEW CONTENT");
		cc.setContentDescription("Test");
		cc.setSelectFile("C:\\temp\\test.docx");
		cc.setMetadata("test");
		cc.setActiveCheckbox(false);
		cc.clickCreate();

		c.gotoTablePageWithRow(c.getContentTable(), "Content Name",
				"NEW CONTENT");

		c.getContentTable().clickLinkInRow("Content Name", "NEW CONTENT", "",
				"Delete", false);

		dc = new DeleteContentPageFactory();

		System.out.println(dc.readContentName());
		System.out.println(dc.readContentDescription());
		System.out.println(dc.readContentPath());
		System.out.println(dc.readMetadata());
		System.out.println(dc.readContentType());
		System.out.println(dc.isActiveChecked());
		System.out.println(dc.readUpdatedBy());
		System.out.println(dc.readUpdatedOn());

		dc.clickDelete();

		AutomationHelper.waitSeconds(10);
	}
}
