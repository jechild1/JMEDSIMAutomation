package pageFactories.unitTests;

import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.UsersAndRoles.UsersAndRolesPageFactory;
import utilities.AutomationHelper;

public class UsersAndRolesPageFactoryTest extends TestBase {
	@Test
	public void execute() {
		LoginMod lm = new LoginMod();
		lm.execute("scott_DHA_system_admin@mail.mil");

		HomePageFactory hpf = new HomePageFactory();

		hpf.clickUsersAndRolesInAdmin();

		UsersAndRolesPageFactory urpf = new UsersAndRolesPageFactory();

		urpf.setSearchBy("scott_site_TSunshine_user@mail.mil");
		urpf.clickSearch();
		//
		urpf.gotoTablePageWithRow(urpf.getUsersAndRolesTable(), "User",
				"scott_site_TSunshine_user@mail.mil");

		System.out.println(urpf.getUsersAndRolesTable().readTableRowValue(
				"User", "scott_site_TSunshine_user@mail.mil", "Role", false));

		AutomationHelper.waitSeconds(10);
	}

}
