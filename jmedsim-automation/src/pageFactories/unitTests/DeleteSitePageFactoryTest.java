package pageFactories.unitTests;

import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.SitesPages.CreateSitePageFactory;
import pageFactories.Admin.SitesPages.DeleteSitePageFactory;
import pageFactories.Admin.SitesPages.SitesPageFactory;

public class DeleteSitePageFactoryTest extends TestBase {
	@Test
	public void execute() {
		LoginMod lm = new LoginMod();
		lm.execute("scott_DHA_system_admin@mail.mil");

		HomePageFactory hpf = new HomePageFactory();

		hpf.clickSitesInAdmin();

		SitesPageFactory spf = new SitesPageFactory();
		spf.clickCreateNewSite();

		CreateSitePageFactory cspf = new CreateSitePageFactory();
		cspf.setSite("fake site");
		cspf.setAddress1("123 test site");
		cspf.setAddress2("APT 2");
		cspf.setCity("TestCity");
		cspf.selectState("Virginia");
		cspf.setZipcode("24210");
		cspf.setActiveSiteCheckbox(false);
		cspf.clickCreate();

		spf.gotoTablePageWithRow(spf.getSitesTable(), "Site", "fake site");

		spf.getSitesTable().clickLinkInRow("Site", "fake site", "", "Delete",
				false);

		DeleteSitePageFactory dspf = new DeleteSitePageFactory();

		System.out.println(dspf.readSite());
		System.out.println(dspf.readAddress());
		System.out.println(dspf.isActiveSiteChecked());

		dspf.clickDelete();
	}

}
