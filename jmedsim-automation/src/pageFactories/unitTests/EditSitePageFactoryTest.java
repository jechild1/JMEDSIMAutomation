package pageFactories.unitTests;

import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.SitesPages.EditSitePageFactory;
import pageFactories.Admin.SitesPages.SitesPageFactory;
import utilities.AutomationHelper;

public class EditSitePageFactoryTest extends TestBase {
	@Test
	public void execute() {
		LoginMod lm = new LoginMod();
		lm.execute("scott_DHA_system_admin@mail.mil");

		HomePageFactory hpf = new HomePageFactory();

		hpf.clickSitesInAdmin();

		SitesPageFactory spf = new SitesPageFactory();

		spf.getSitesTable().clickLinkInRow("Site", "TATRC", "", "Edit", false);

		EditSitePageFactory espf = new EditSitePageFactory();
		// espf.setSite("");
		// espf.setAddress1("");
		// espf.setAddress2("");
		// espf.setCity("");
		// espf.selectState("Select State");
		// espf.setZipcode("");
		// espf.clickSave();
		//
		// System.out.println(espf.readSiteErrorMessage());
		// System.out.println(espf.readAddress1ErrorMessage());
		// System.out.println(espf.readAddress2ErrorMessage());
		// System.out.println(espf.readCityErrorMessage());
		// System.out.println(espf.readStateErrorMessage());
		// System.out.println(espf.readZipcodeErrorMessage());
		// System.out.println(espf.readActiveSiteErrorMessage());
		System.out.println(espf.readSite());
		System.out.println(espf.readAddress1());
		System.out.println(espf.readAddress2());
		System.out.println(espf.readCity());
		System.out.println(espf.readStateSelected());
		System.out.println(espf.readZipcode());
		System.out.println(espf.isActiveSiteChecked());

		AutomationHelper.wait(10);
	}

}
