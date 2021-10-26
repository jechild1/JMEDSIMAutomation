package pageFactories.unitTests.AdminSimulationPagesTests;

import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.AdminSimulationPages.CreatePersonnelPageFactory;
import pageFactories.Admin.AdminSimulationPages.DeletePersonnelPageFactory;
import pageFactories.Admin.AdminSimulationPages.EditPersonnelPageFactory;
import pageFactories.Admin.AdminSimulationPages.PersonnelDetailsPageFactory;
import pageFactories.Admin.AdminSimulationPages.PersonnelPageFactory;
import pageFactories.unitTests.TestBase;
import utilities.AutomationHelper;

public class PersonnelCreateEditDetailsDeletePageFactoryTest extends TestBase {
	@Test
	public void execute() {
		LoginMod lm = new LoginMod();
		lm.execute("scott_DHA_system_admin@mail.mil");

		HomePageFactory hpf = new HomePageFactory();

		hpf.clickPersonnelInAdmin();

		PersonnelPageFactory p = new PersonnelPageFactory();
		p.gotoTablePageWithRow(p.getPersonnelTable(), "Personnel",
				"Facilitator");

		p.getPersonnelTable().clickLinkInRow("Personnel", "Facilitator", "",
				"Edit", false);

		EditPersonnelPageFactory ep = new EditPersonnelPageFactory();

		System.out.println(ep.readPersonnel());
		System.out.println(ep.isActiveChecked());

		ep.setPersonnel("");
		ep.clickSave();

		System.out.println(ep.readPersonnelErrorMessage());

		ep.clickBackToList();

		p.gotoTablePageWithRow(p.getPersonnelTable(), "Personnel",
				"Facilitator");

		p.getPersonnelTable().clickLinkInRow("Personnel", "Facilitator", "",
				"Delete", false);

		DeletePersonnelPageFactory dp = new DeletePersonnelPageFactory();

		System.out.println(dp.isAlertMessageOnPage());

		if (dp.isAlertMessageOnPage()) {
			System.out.println(dp.readAlertMessage());
		}
		dp.clickBackToList();

		p.gotoTablePageWithRow(p.getPersonnelTable(), "Personnel",
				"Facilitator");

		p.getPersonnelTable().clickLinkInRow("Personnel", "Facilitator", "",
				"Details", false);

		PersonnelDetailsPageFactory pd = new PersonnelDetailsPageFactory();

		System.out.println(pd.readPersonnel());
		System.out.println(pd.isActiveChecked());

		pd.clickBackToList();

		p.clickCreateNewPersonnel();

		CreatePersonnelPageFactory cnp = new CreatePersonnelPageFactory();

		cnp.setPersonnel("NEW PERSONNEL");
		cnp.setActiveCheckbox(false);
		cnp.clickCreate();

		p.gotoTablePageWithRow(p.getPersonnelTable(), "Personnel",
				"NEW PERSONNEL");

		p.getPersonnelTable().clickLinkInRow("Personnel", "NEW PERSONNEL", "",
				"Delete", false);

		dp = new DeletePersonnelPageFactory();

		System.out.println(dp.readPersonnel());
		System.out.println(dp.isActiveChecked());

		dp.clickDelete();

		AutomationHelper.wait(10);
	}
}
