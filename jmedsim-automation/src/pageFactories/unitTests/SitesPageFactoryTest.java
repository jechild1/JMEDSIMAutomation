package pageFactories.unitTests;

import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.SitesPages.SitesPageFactory;

public class SitesPageFactoryTest extends TestBase {
	@Test
	public void execute() {
		LoginMod lm = new LoginMod();
		lm.execute("scott_DHA_system_admin@mail.mil");

		HomePageFactory hpf = new HomePageFactory();

		hpf.clickSitesInAdmin();

		SitesPageFactory spf = new SitesPageFactory();

		// spf.clickCreateNewSite();
		//
		// spf.setSearchBy("test 4");
		// spf.clickSearch();
		//
		spf.gotoTablePageWithRow(spf.getSitesTable(), "Site", "test 4");

		System.out.println(spf.getSitesTable().readTableRowValue("Site",
				"test 4", "Address", false));

		spf.gotoTablePageWithRow(spf.getSitesTable(), "Site", "blah");
		//
		// spf.getSitesTable().clickLinkInRow("Site", "TATRC", "", "Details",
		// false);
		//
		// AutomationHelper.wait(10);
	}
}
