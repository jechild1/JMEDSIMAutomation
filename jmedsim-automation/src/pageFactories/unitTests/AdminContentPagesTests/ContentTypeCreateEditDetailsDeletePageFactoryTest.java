package pageFactories.unitTests.AdminContentPagesTests;

import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.AdminContentPages.ContentTypeDetailsPageFactory;
import pageFactories.Admin.AdminContentPages.ContentTypePageFactory;
import pageFactories.Admin.AdminContentPages.CreateContentTypePageFactory;
import pageFactories.Admin.AdminContentPages.DeleteContentTypePageFactory;
import pageFactories.Admin.AdminContentPages.EditContentTypePageFactory;
import pageFactories.unitTests.TestBase;
import utilities.AutomationHelper;

public class ContentTypeCreateEditDetailsDeletePageFactoryTest
		extends
			TestBase {
	@Test
	public void execute() {
		LoginMod lm = new LoginMod();
		lm.execute("scott_DHA_system_admin@mail.mil");

		HomePageFactory hpf = new HomePageFactory();

		hpf.clickContentTypeInAdmin();

		ContentTypePageFactory ct = new ContentTypePageFactory();
		ct.gotoTablePageWithRow(ct.getContentTypeTable(), "Content Type",
				"Image");

		ct.getContentTypeTable().clickLinkInRow("Content Type", "Image", "",
				"Edit", false);

		EditContentTypePageFactory ect = new EditContentTypePageFactory();

		System.out.println(ect.readContentType());
		System.out.println(ect.isActiveChecked());

		ect.setContentType("");
		ect.clickSave();

		System.out.println(ect.readContentTypeErrorMessage());

		ect.clickBackToList();

		ct.gotoTablePageWithRow(ct.getContentTypeTable(), "Content Type",
				"Image");

		ct.getContentTypeTable().clickLinkInRow("Content Type", "Image", "",
				"Delete", false);

		DeleteContentTypePageFactory dct = new DeleteContentTypePageFactory();

		System.out.println(dct.isAlertMessageOnPage());

		if (dct.isAlertMessageOnPage()) {
			System.out.println(dct.readAlertMessage());
		}
		dct.clickBackToList();

		ct.gotoTablePageWithRow(ct.getContentTypeTable(), "Content Type",
				"Image");

		ct.getContentTypeTable().clickLinkInRow("Content Type", "Image", "",
				"Details", false);

		ContentTypeDetailsPageFactory ctd = new ContentTypeDetailsPageFactory();

		System.out.println(ctd.readContentType());
		System.out.println(ctd.isActiveChecked());

		ctd.clickBackToList();

		ct.clickCreateNewContentType();

		CreateContentTypePageFactory cnct = new CreateContentTypePageFactory();

		cnct.setContentType("NEW TYPE");
		cnct.setActiveCheckbox(false);
		cnct.clickCreate();

		ct.gotoTablePageWithRow(ct.getContentTypeTable(), "Content Type",
				"NEW TYPE");

		ct.getContentTypeTable().clickLinkInRow("Content Type", "NEW TYPE", "",
				"Delete", false);

		dct = new DeleteContentTypePageFactory();

		System.out.println(dct.readContentType());
		System.out.println(dct.isActiveChecked());

		dct.clickDelete();

		AutomationHelper.waitSeconds(10);
	}
}
