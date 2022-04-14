package pageFactories.unitTests.AdminSimulationPagesTests;

import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.AdminSimulationPages.CreateNewServiceRolePageFactory;
import pageFactories.Admin.AdminSimulationPages.DeleteServiceRolePageFactory;
import pageFactories.Admin.AdminSimulationPages.EditServiceRolePageFactory;
import pageFactories.Admin.AdminSimulationPages.ServiceRoleDetailsPageFactory;
import pageFactories.Admin.AdminSimulationPages.ServiceRolePageFactory;
import pageFactories.unitTests.TestBase;
import utilities.AutomationHelper;

public class ServiceRoleCreateEditDetailsDeletePageFactoryTest
		extends
			TestBase {
	@Test
	public void execute() {
		LoginMod lm = new LoginMod();
		lm.execute("scott_DHA_system_admin@mail.mil");

		HomePageFactory hpf = new HomePageFactory();

		hpf.clickServiceRoleInAdmin();

		ServiceRolePageFactory sr = new ServiceRolePageFactory();
		sr.gotoTablePageWithRow(sr.getServiceRoleTable(), "Service Code",
				"3.2");

		sr.getServiceRoleTable().clickLinkInRow("Service Code", "3.2", "",
				"Edit", false);

		EditServiceRolePageFactory esr = new EditServiceRolePageFactory();

		System.out.println(esr.readServiceCode());
		System.out.println(esr.readServiceRole());
		System.out.println(esr.isActiveChecked());

		esr.setServiceCode("");
		esr.setServiceRole("");
		esr.clickSave();

		System.out.println(esr.readServiceCodeErrorMessage());
		System.out.println(esr.readServiceRoleErrorMessage());

		esr.clickBackToList();

		sr.gotoTablePageWithRow(sr.getServiceRoleTable(), "Service Code",
				"3.2");

		sr.getServiceRoleTable().clickLinkInRow("Service Code", "3.2", "",
				"Delete", false);

		DeleteServiceRolePageFactory dsr = new DeleteServiceRolePageFactory();

		System.out.println(dsr.isAlertMessageOnPage());

		if (dsr.isAlertMessageOnPage()) {
			System.out.println(dsr.readAlertMessage());
		}
		dsr.clickBackToList();

		sr.gotoTablePageWithRow(sr.getServiceRoleTable(), "Service Code",
				"3.2");

		sr.getServiceRoleTable().clickLinkInRow("Service Code", "3.2", "",
				"Details", false);

		ServiceRoleDetailsPageFactory srd = new ServiceRoleDetailsPageFactory();

		System.out.println(srd.readServiceCode());
		System.out.println(srd.readServiceRole());
		System.out.println(srd.isActiveChecked());

		srd.clickBackToList();

		sr.clickCreateNewServiceRole();

		CreateNewServiceRolePageFactory cnsr = new CreateNewServiceRolePageFactory();

		cnsr.setServiceCode("00000");
		cnsr.setServiceRole("test role");
		cnsr.setActiveCheckbox(false);
		cnsr.clickCreate();

		sr.gotoTablePageWithRow(sr.getServiceRoleTable(), "Service Code",
				"00000");

		sr.getServiceRoleTable().clickLinkInRow("Service Code", "00000", "",
				"Delete", false);

		dsr = new DeleteServiceRolePageFactory();

		System.out.println(dsr.readServiceCode());
		System.out.println(dsr.readServiceRole());

		dsr.clickDelete();

		AutomationHelper.waitSeconds(10);
	}
}
