package pageFactories.unitTests;

import org.testng.annotations.Test;

import ArchivedClasses.EditUserSitePageFactory;
import ArchivedClasses.SitesAndUsersPageFactory;
import ArchivedClasses.UserSiteDetailsPageFactory;
import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import utilities.AutomationHelper;

public class UserSitesCreateEditDetailsDeletePageFactoryTest extends TestBase {
	@Test
	public void execute() {
		LoginMod lm = new LoginMod();
		lm.execute("scott_site_TATRC_site_admin@mail.mil");

		HomePageFactory hpf = new HomePageFactory();

		hpf.clickUserSitesInAdmin();

		SitesAndUsersPageFactory sau = new SitesAndUsersPageFactory();
		sau.gotoTablePageWithRow(sau.getSitesAndUsersTable(), "User",
				"scott_site_TSunshine_super_user@mail.mil");

		sau.getSitesAndUsersTable().clickLinkInRow("User",
				"scott_site_TSunshine_super_user@mail.mil", "", "Edit", false);

		EditUserSitePageFactory eus = new EditUserSitePageFactory();

		System.out.println(eus.readUserIdSelected());
		System.out.println(eus.readSiteIdSelected());

		eus.clickBackToList();

		sau.gotoTablePageWithRow(sau.getSitesAndUsersTable(), "User",
				"scott_site_TSunshine_super_user@mail.mil");

		sau.getSitesAndUsersTable().clickLinkInRow("User",
				"scott_site_TSunshine_super_user@mail.mil", "", "Details",
				false);

		UserSiteDetailsPageFactory usd = new UserSiteDetailsPageFactory();

		System.out.println(usd.readUser());
		System.out.println(usd.readSite());

		usd.clickBackToList();

		AutomationHelper.waitSeconds(10);
	}
}
