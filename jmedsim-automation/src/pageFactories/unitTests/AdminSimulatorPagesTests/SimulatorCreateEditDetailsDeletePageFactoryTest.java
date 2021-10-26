package pageFactories.unitTests.AdminSimulatorPagesTests;

import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.AdminSimulatorPages.CreateSimulatorPageFactory;
import pageFactories.Admin.AdminSimulatorPages.DeleteSimulatorPageFactory;
import pageFactories.Admin.AdminSimulatorPages.EditSimulatorPageFactory;
import pageFactories.Admin.AdminSimulatorPages.SimulatorDetailsPageFactory;
import pageFactories.Admin.AdminSimulatorPages.SimulatorPageFactory;
import pageFactories.unitTests.TestBase;
import utilities.AutomationHelper;

public class SimulatorCreateEditDetailsDeletePageFactoryTest extends TestBase {
	@Test
	public void execute() {
		LoginMod lm = new LoginMod();
		lm.execute("scott_DHA_system_admin@mail.mil");

		HomePageFactory hpf = new HomePageFactory();

		hpf.clickSimulatorInAdmin();

		SimulatorPageFactory s = new SimulatorPageFactory();
		s.gotoTablePageWithRow(s.getSimulatorTable(), "Name", "Simulator 2");

		s.getSimulatorTable().clickLinkInRow("Name", "Simulator 2", "", "Edit",
				false);

		EditSimulatorPageFactory es = new EditSimulatorPageFactory();

		System.out.println(es.readName());
		System.out.println(es.readDescription());
		System.out.println(es.readSimulatorCategorySelected());
		System.out.println(es.isActiveChecked());

		es.setName("");
		es.setDescription("");
		es.clickSave();

		System.out.println(es.readNameErrorMessage());
		System.out.println(es.readDescriptionErrorMessage());

		es.clickBackToList();

		s.gotoTablePageWithRow(s.getSimulatorTable(), "Name", "Simulator 2");

		s.getSimulatorTable().clickLinkInRow("Name", "Simulator 2", "",
				"Delete", false);

		DeleteSimulatorPageFactory ds = new DeleteSimulatorPageFactory();

		System.out.println(ds.isAlertMessageOnPage());

		if (ds.isAlertMessageOnPage()) {
			System.out.println(ds.readAlertMessage());
		}
		ds.clickBackToList();

		s.gotoTablePageWithRow(s.getSimulatorTable(), "Name", "Simulator 2");

		s.getSimulatorTable().clickLinkInRow("Name", "Simulator 2", "",
				"Details", false);

		SimulatorDetailsPageFactory sd = new SimulatorDetailsPageFactory();

		System.out.println(sd.readName());
		System.out.println(sd.readCategory());
		System.out.println(sd.readDescription());
		System.out.println(sd.isActiveChecked());

		sd.clickBackToList();

		s.clickCreateNewSimulator();

		CreateSimulatorPageFactory cns = new CreateSimulatorPageFactory();

		cns.setName("NEW SIMULATOR");
		cns.setDescription("NEW SIMULATOR DESC");
		cns.selectSimulatorCategory("Simulator Category 1");
		cns.setActiveCheckbox(false);
		cns.clickCreate();

		s.gotoTablePageWithRow(s.getSimulatorTable(), "Name", "NEW SIMULATOR");

		s.getSimulatorTable().clickLinkInRow("Name", "NEW SIMULATOR", "",
				"Delete", false);

		ds = new DeleteSimulatorPageFactory();

		System.out.println(ds.readName());
		System.out.println(ds.readCategory());
		System.out.println(ds.readDescription());
		System.out.println(ds.isActiveChecked());

		ds.clickDelete();

		AutomationHelper.wait(10);

	}
}
