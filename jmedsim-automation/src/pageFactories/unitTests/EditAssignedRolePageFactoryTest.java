package pageFactories.unitTests;

import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.UsersAndRoles.EditAssignedRolePageFactory;
import pageFactories.Admin.UsersAndRoles.UsersAndRolesPageFactory;
import utilities.AutomationHelper;

public class EditAssignedRolePageFactoryTest extends TestBase {
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
				"scott_site_TSunshine_super_user@mail.mil", "", "Edit", false);

		EditAssignedRolePageFactory ear = new EditAssignedRolePageFactory();

		System.out.println(ear.readUserIdSelected());
		System.out.println(ear.readUserIdErrorMessage());
		System.out.println(ear.readRoleSelected());
		System.out.println(ear.readRoleErrorMessage());

		// ear.selectRole("Site Admin");
		//
		// ear.selectRole("Site Super User");
		// ear.clickSave();

		AutomationHelper.wait(10);
	}

}
