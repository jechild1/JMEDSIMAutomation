package pageFactories.unitTests.LockedAccountsPagesTests;

import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.LockedAccountsPages.LockAccountPageFactory;
import pageFactories.Admin.LockedAccountsPages.LockedAccountsPageFactory;
import pageFactories.Admin.LockedAccountsPages.UnlockAccountPageFactory;
import pageFactories.unitTests.TestBase;
import utilities.AutomationHelper;

public class LockedAccountsLockUnlockPageFactoryTest extends TestBase {
	@Test
	public void execute() {
		LoginMod lm = new LoginMod();
		lm.execute("scott_DHA_system_admin@mail.mil");

		HomePageFactory hpf = new HomePageFactory();

		hpf.clickLockedAccountsInAdmin();

		LockedAccountsPageFactory la = new LockedAccountsPageFactory();
		la.gotoTablePageWithRow(la.getLockedAccountsTable(), "User",
				"test@mail.mil");

		la.getLockedAccountsTable().clickLinkInRow("User", "test@mail.mil", "",
				"Unlock", false);

		UnlockAccountPageFactory ua = new UnlockAccountPageFactory();

		System.out.println(ua.readUser());
		System.out.println(ua.readReason());
		System.out.println(ua.readLockedBy());
		System.out.println(ua.readLockedOn());

		ua.clickBackToList();

		la = new LockedAccountsPageFactory();
		la.clickLockAnAccount();

		LockAccountPageFactory lapf = new LockAccountPageFactory();

		System.out.println(lapf.readUserSelected());
		System.out.println(lapf.readLockReasonSelected());

		lapf.selectUser("ScottUser TATRC (scott_site_TATRC_user@mail.mil)");
		lapf.selectLockReason("No longer needed");
		lapf.clickLock();

		la.gotoTablePageWithRow(la.getLockedAccountsTable(), "User",
				"scott_site_TATRC_user@mail.mil");

		la.getLockedAccountsTable().clickLinkInRow("User",
				"scott_site_TATRC_user@mail.mil", "", "Unlock", false);

		ua = new UnlockAccountPageFactory();

		System.out.println(ua.readUser());
		System.out.println(ua.readReason());
		System.out.println(ua.readLockedBy());
		System.out.println(ua.readLockedOn());

		ua.clickUnlock();

		AutomationHelper.wait(10);
	}
}
