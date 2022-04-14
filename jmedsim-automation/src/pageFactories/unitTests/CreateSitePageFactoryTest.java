package pageFactories.unitTests;

import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.SitesPages.CreateSitePageFactory;
import pageFactories.Admin.SitesPages.SitesPageFactory;
import utilities.AutomationHelper;

public class CreateSitePageFactoryTest extends TestBase {
	@Test
	public void execute() {
		LoginMod lm = new LoginMod();
		lm.execute("scott_DHA_system_admin@mail.mil");

		HomePageFactory hpf = new HomePageFactory();

		hpf.clickSitesInAdmin();

		SitesPageFactory spf = new SitesPageFactory();
		spf.clickCreateNewSite();

		CreateSitePageFactory cspf = new CreateSitePageFactory();
		// cspf.setSite("test");
		// cspf.setAddress1("123 test site");
		// cspf.setAddress2("APT 2");
		// cspf.setCity("TestCity");
		// cspf.selectState("Virginia");
		// cspf.setZipcode("24210");
		// cspf.setActiveSiteCheckbox(false);
		// cspf.clickBackToList();
		cspf.clickCreate();
		//
		System.out.println(cspf.readSiteErrorMessage());
		System.out.println(cspf.readAddress1ErrorMessage());
		System.out.println(cspf.readAddress2ErrorMessage());
		System.out.println(cspf.readCityErrorMessage());
		System.out.println(cspf.readStateErrorMessage());
		System.out.println(cspf.readZipcodeErrorMessage());
		System.out.println(cspf.readActiveSiteErrorMessage());

		AutomationHelper.waitSeconds(10);
	}

}
