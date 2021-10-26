package pageFactories.unitTests;

import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.UsersAndRoles.UserRoleDetailsPageFactory;
import pageFactories.Admin.UsersAndRoles.UsersAndRolesPageFactory;

public class UserRoleDetailsPageFactoryTest extends TestBase {
	@Test
	public void execute() {

		LoginMod lm = new LoginMod();
		lm.execute("scott_DHA_system_admin@mail.mil");

		HomePageFactory hpf = new HomePageFactory();

		hpf.clickUsersAndRolesInAdmin();

		UsersAndRolesPageFactory urpf = new UsersAndRolesPageFactory();

		urpf.gotoTablePageWithRow(urpf.getUsersAndRolesTable(), "User",
				"scott_site_TSunshine_super_user@mail.mil");

		urpf.getUsersAndRolesTable().clickLinkInRow("User",
				"scott_site_TSunshine_super_user@mail.mil", "", "Details",
				false);

		UserRoleDetailsPageFactory urd = new UserRoleDetailsPageFactory();

		System.out.println(urd.readUser());
		System.out.println(urd.readRole());

	}
}
