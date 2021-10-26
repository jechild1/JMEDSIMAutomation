package pageFactories.unitTests.AdminSimulationPagesTests;

import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.AdminSimulationPages.CreateNewLearnerPofilePageFactory;
import pageFactories.Admin.AdminSimulationPages.DeleteLearnerProfilePageFactory;
import pageFactories.Admin.AdminSimulationPages.EditLearnerProfilePageFactory;
import pageFactories.Admin.AdminSimulationPages.LearnerProfileDetailsPageFactory;
import pageFactories.Admin.AdminSimulationPages.LearnerProfilePageFactory;
import pageFactories.unitTests.TestBase;
import utilities.AutomationHelper;

public class LearnerProfileCreateEditDetailsDeletePageFactoryTest
		extends
			TestBase {
	@Test
	public void execute() {
		LoginMod lm = new LoginMod();
		lm.execute("scott_DHA_system_admin@mail.mil");

		HomePageFactory hpf = new HomePageFactory();

		hpf.clickLearnerProfileInAdmin();

		LearnerProfilePageFactory lp = new LearnerProfilePageFactory();
		lp.gotoTablePageWithRow(lp.getLearnerProfileTable(), "Learner Profile",
				"Doctor");

		lp.getLearnerProfileTable().clickLinkInRow("Learner Profile", "Doctor",
				"", "Edit", false);

		EditLearnerProfilePageFactory elp = new EditLearnerProfilePageFactory();

		System.out.println(elp.readLearnerProfile());
		System.out.println(elp.isActiveChecked());

		elp.setLearnerProfile("");
		elp.clickSave();

		System.out.println(elp.readLearnerProfileErrorMessage());

		elp.clickBackToList();

		lp.gotoTablePageWithRow(lp.getLearnerProfileTable(), "Learner Profile",
				"Doctor");

		lp.getLearnerProfileTable().clickLinkInRow("Learner Profile", "Doctor",
				"", "Delete", false);

		DeleteLearnerProfilePageFactory dlp = new DeleteLearnerProfilePageFactory();

		System.out.println(dlp.isAlertMessageOnPage());

		if (dlp.isAlertMessageOnPage()) {
			System.out.println(dlp.readAlertMessage());
		}
		dlp.clickBackToList();

		lp.gotoTablePageWithRow(lp.getLearnerProfileTable(), "Learner Profile",
				"Doctor");

		lp.getLearnerProfileTable().clickLinkInRow("Learner Profile", "Doctor",
				"", "Details", false);

		LearnerProfileDetailsPageFactory lpd = new LearnerProfileDetailsPageFactory();

		System.out.println(lpd.readLearnerProfile());
		System.out.println(lpd.isActiveChecked());

		lpd.clickBackToList();

		lp.clickCreateNewLearnerProfile();

		CreateNewLearnerPofilePageFactory cnlp = new CreateNewLearnerPofilePageFactory();

		cnlp.setLearnerProfile("NEW PROFILE");
		cnlp.setActiveCheckbox(false);
		cnlp.clickCreate();

		lp.gotoTablePageWithRow(lp.getLearnerProfileTable(), "Learner Profile",
				"NEW PROFILE");

		lp.getLearnerProfileTable().clickLinkInRow("Learner Profile",
				"NEW PROFILE", "", "Delete", false);

		dlp = new DeleteLearnerProfilePageFactory();

		System.out.println(dlp.readLearnerProfile());
		System.out.println(dlp.isActiveChecked());

		dlp.clickDelete();

		AutomationHelper.wait(10);
	}
}
