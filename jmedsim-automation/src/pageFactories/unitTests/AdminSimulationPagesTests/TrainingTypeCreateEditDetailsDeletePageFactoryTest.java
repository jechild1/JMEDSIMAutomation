package pageFactories.unitTests.AdminSimulationPagesTests;

import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.AdminSimulationPages.CreateNewTrainingTypePageFactory;
import pageFactories.Admin.AdminSimulationPages.DeleteTrainingTypePageFactory;
import pageFactories.Admin.AdminSimulationPages.EditTrainingTypePageFactory;
import pageFactories.Admin.AdminSimulationPages.TrainingTypeDetailsPageFactory;
import pageFactories.Admin.AdminSimulationPages.TrainingTypePageFactory;
import pageFactories.unitTests.TestBase;
import utilities.AutomationHelper;

public class TrainingTypeCreateEditDetailsDeletePageFactoryTest
		extends
			TestBase {
	@Test
	public void execute() {
		LoginMod lm = new LoginMod();
		lm.execute("scott_DHA_system_admin@mail.mil");

		HomePageFactory hpf = new HomePageFactory();

		hpf.clickTrainingTypeInAdmin();

		TrainingTypePageFactory tt = new TrainingTypePageFactory();
		tt.gotoTablePageWithRow(tt.getTrainingTypeTable(), "Training Type",
				"Head trauma");

		tt.getTrainingTypeTable().clickLinkInRow("Training Type", "Head trauma",
				"", "Edit", false);

		EditTrainingTypePageFactory ett = new EditTrainingTypePageFactory();

		System.out.println(ett.readTrainingType());
		System.out.println(ett.isActiveChecked());

		ett.setTrainingType("");
		ett.clickSave();

		System.out.println(ett.readTrainingTypeErrorMessage());

		ett.clickBackToList();

		tt.gotoTablePageWithRow(tt.getTrainingTypeTable(), "Training Type",
				"Head trauma");

		tt.getTrainingTypeTable().clickLinkInRow("Training Type", "Head trauma",
				"", "Delete", false);

		DeleteTrainingTypePageFactory dtt = new DeleteTrainingTypePageFactory();

		System.out.println(dtt.isAlertMessageOnPage());

		if (dtt.isAlertMessageOnPage()) {
			System.out.println(dtt.readAlertMessage());
		}

		dtt.clickBackToList();

		tt.gotoTablePageWithRow(tt.getTrainingTypeTable(), "Training Type",
				"Head trauma");

		tt.getTrainingTypeTable().clickLinkInRow("Training Type", "Head trauma",
				"", "Details", false);

		TrainingTypeDetailsPageFactory ttd = new TrainingTypeDetailsPageFactory();

		System.out.println(ttd.readTrainingType());
		System.out.println(ttd.isActiveChecked());

		ttd.clickBackToList();

		tt.clickCreateNewTrainingType();

		CreateNewTrainingTypePageFactory cntt = new CreateNewTrainingTypePageFactory();

		cntt.setTrainingType("test equipment");
		cntt.setActiveCheckbox(false);
		cntt.clickCreate();

		tt.gotoTablePageWithRow(tt.getTrainingTypeTable(), "Training Type",
				"test equipment");

		tt.getTrainingTypeTable().clickLinkInRow("Training Type",
				"test equipment", "", "Delete", false);

		dtt = new DeleteTrainingTypePageFactory();

		System.out.println(dtt.readTrainingType());
		System.out.println(dtt.isActiveChecked());

		dtt.clickDelete();

		AutomationHelper.wait(10);
	}
}
