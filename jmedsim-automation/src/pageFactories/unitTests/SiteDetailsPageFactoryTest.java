package pageFactories.unitTests;

import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.SitesPages.SiteDetailsPageFactory;
import pageFactories.Admin.SitesPages.SitesPageFactory;
import utilities.AutomationHelper;

public class SiteDetailsPageFactoryTest extends TestBase {
	@Test
	public void execute() {
		LoginMod lm = new LoginMod();
		lm.execute("scott_DHA_system_admin@mail.mil");

		HomePageFactory hpf = new HomePageFactory();

		hpf.clickSitesInAdmin();

		SitesPageFactory spf = new SitesPageFactory();

		spf.getSitesTable().clickLinkInRow("Site", "TATRC", "", "Details",
				false);

		SiteDetailsPageFactory sdpf = new SiteDetailsPageFactory();
		//
		System.out.println(sdpf.readSite());
		System.out.println(sdpf.readAddress());
		System.out.println(sdpf.isActiveSiteChecked());
		//
		sdpf.clickEdit();
		// sdpf.clickBackToList();

		AutomationHelper.waitSeconds(5);
	}

}
