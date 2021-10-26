package pageFactories.unitTests.MyMenuPagesTests;

import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.MyMenuPages.EditSimulationPageFactory;
import pageFactories.MyMenuPages.EditSimulationPageFactory.EquipmentTab.EquipmentModal;
import pageFactories.MyMenuPages.SimulationsPageFactory;

//public class SimulationsCreateEditDetailsDeletePageFactoryTest extends TestBase {
//TODO - Extend TestBase again
public class SimulationsCreateEditDetailsDeletePageFactoryTest {

	@Test
	public void execute() {
		LoginMod lm = new LoginMod();
		lm.execute("jesse_DHA_super_user@mail.mil");

		HomePageFactory hpf = new HomePageFactory();
		hpf.clickSimulationInMyMenu();

		SimulationsPageFactory spf = new SimulationsPageFactory();

		// EDIT SIMULATION
		spf.gotoTablePageWithRow(spf.getSimulationsTable(), "Title", "Temp Simulation");
		spf.getSimulationsTable().clickLinkInRow("Temp Simulation", "Edit");

		EditSimulationPageFactory espf = new EditSimulationPageFactory();

		// CYCLE THROUGH TABS
		espf.getAdministrativeDetailsTab();
		espf.getSimulationDescriptionTab();
		espf.getLearnersTab();
		espf.getSimulationOOPITab();
		espf.getPersonnelTab();
		espf.getSimulationOOPITab();
		espf.getAdministrativeDetailsTab();
		espf.getSimulationDescriptionTab();
		espf.getLearnersTab();
		espf.getSimulationOOPITab();
		espf.getPersonnelTab();
		espf.getSimulationOOPITab();
		espf.getPrerequsitiesTab();
		
		

		// ADMINISTRATIVE DETAILS
		// espf.getAdministrativeDetailsTab().setTitle("Title Text");
		// System.out.println(
		// "Title: " + espf.getAdministrativeDetailsTab().readTitle());
		//
		// espf.getAdministrativeDetailsTab().setDuration("Duration");
		// System.out.println("Duration: "
		// + espf.getAdministrativeDetailsTab().readDuration());
		//
		// espf.getAdministrativeDetailsTab().setAuthor("Author Person");
		// System.out.println(
		// "Author: " + espf.getAdministrativeDetailsTab().readAuthor());
		//
		// espf.getAdministrativeDetailsTab().selectSite("TATRC Sunshine");
		// System.out.println(
		// "Site: " + espf.getAdministrativeDetailsTab().readSite());
		//
		// espf.getAdministrativeDetailsTab()
		// .setContentKeywords("Content Keywords");
		// System.out.println("Content Keywords: "
		// + espf.getAdministrativeDetailsTab().readContentKeywords());
		//
		// espf.getAdministrativeDetailsTab().selectTrainingType("Scenario
		// Based");
		// System.out.println("Training Type: "
		// + espf.getAdministrativeDetailsTab().readTrainingType());
		//
		// System.out.println("Active Before: "
		// + espf.getAdministrativeDetailsTab().isActiveChecked());
		// espf.getAdministrativeDetailsTab().setActiveCheckbox(false);
		// System.out.println("Active After: "
		// + espf.getAdministrativeDetailsTab().isActiveChecked());
		// espf.getAdministrativeDetailsTab().setActiveCheckbox(true);
		// espf.getAdministrativeDetails().clickSave();

		// ADD SERVICE ROLE MODAL
		// AddServiceRolesModal asrm = espf.getAdministrativeDetailsTab()
		// .clickAddServiceRole();
		// asrm.setSearchBy("Test");
		// asrm.clickSearch();
		// // asrm.getAddServiceRolesTable().clickLinkInRow("test", "Add");
		// //AJAX
		// // ERROR
		// asrm.clickClose();

		// SIMULATION DESCRIPTION (Required)
		// espf.getSimulationDescriptionTab().setSimulationDescription(
		// "Simulation Description Text Goes Here");
		// System.out.println("Simulation Description Text: " + espf
		// .getSimulationDescriptionTab().readSimulationDescription());
		// espf.getSimulationDescriptionTab().clickSave();

		// LEARNERS
		// EditLearnerModal elm = espf.getLearnersTab()
		// .clickEditInLearnersTableByServiceRole("Operational Medicine");

		// Edit Learner Modal
		// System.out.println(
		// "Edit Learner - Service Role: " + elm.readServiceRole());
		// elm.selectLearnerProfile("Dentist");
		// System.out.println(
		// "Edit Learner - Learner Profile: " + elm.readLearnerProfile());
		// elm.setQty("3");
		// System.out.println("Edit Learner - Qty: " + elm.readQty());
		// elm.clickClose();
		//
		// // Additional Learner Modal (EDIT EXISTING)
		// AdditionalLearnerModal alm = espf.getLearnersTab()
		// .clickEditInAdditionalLearnersTableByAdditionalLearner("Test");
		// // Perform edits on existing
		// alm.setAdditionalLearner("Additional Learner Test");
		// System.out.println("Additional Learner - Additinoal Learner: "
		// + alm.readAdditionalLearner());
		//
		// alm.setQty("15");
		// System.out.println("Additional Learner - Qty: " + alm.readQty());
		//
		// alm.selectLearnerProfile("Dentist");
		// System.out.println("Additional Learner - Learner Profile: "
		// + alm.readLearnerProfileSelected());
		//
		// alm.setOtherLearnerProfile("Other Learner Profile");
		// System.out.println("Additional Learner - Other Learner Profile: "
		// + alm.readOtherLearnerProfile());
		//
		// alm.clickClose();

		// Perform add Additional Learner
		// alm = espf.getLearnersTab().clickAddAdditionalLearner();
		//
		// alm.setAdditionalLearner("Additional Learner Test");
		// System.out.println("Additional Learner - Additinoal Learner: "
		// + alm.readAdditionalLearner());
		//
		// alm.setQty("3");
		// System.out.println("Additional Learner - Qty: " + alm.readQty());
		//
		// alm.selectLearnerProfile("Doctor");
		// System.out.println("Additional Learner - Learner Profile: "
		// + alm.readLearnerProfileSelected());
		//
		// alm.setOtherLearnerProfile("Other Learner Profile Test");
		// System.out.println("Additional Learner - Other Learner Profile: "
		// + alm.readOtherLearnerProfile());
		//
		// alm.clickAdd();
		//
		// espf.getLearnersTab()
		// .clickDeleteInAdditionalLearnersTableByAdditionalLearner(
		// "Additional Learner Test");
		// espf.clickOKOnAlert();

		// SIMULATION OOPI
		// System.out.println("***SIMULATION OOPI SECTION***");
		// espf.getSimulationOOPITab().selectoOpiType("Formative");
		// System.out.println("Simulation OOPI Type: " +
		// espf.getSimulationOOPITab().readOOPIType());
		//
		// espf.getSimulationOOPITab().setOutcome("Outcome text for Simulation
		// OOPI");
		// System.out.println("Simulation OOPI Outcome: "+
		// espf.getSimulationOOPITab().readOutcome());
		// espf.getSimulationOOPITab().clickSave();
		//
		// //Edit Objective
		// ObjectiveModal om
		// =espf.getSimulationOOPITab().clickEditInObjectiveTableByObjective("Objective
		// 1");
		// //Objective Modal
		// System.out.println("Objective Modal Text: " + om.readObjective());
		// om.setObjective("Text to set objective with");
		// System.out.println("Objective Modal Text: " + om.readObjective());
		//
		// om.clickClose();

		// Add New Objective - BUG EXIST - 607
		// om = espf.getSimulationOOPITab().clickAddObjective();
		// om.setObjective("Objective x text");
		// om.clickAdd();
		//
		// //Delete newly created objective
		// espf.getSimulationOOPITab().clickDeleteInObjectiveTableByObjective("Objective
		// x text");
		// espf.clickOKOnAlert();

		// ADD PERFORMANCE INDICATORS
		// PerformanceIndicatorModal pim =
		// espf.getSimulationOOPITab().clickAddPerformanceIndicator();
		// pim.setPerformanceIndicator("Performance Indicator Text");
		// System.out.println("Performance Indicator Text: " +
		// pim.readPerformanceIndicator());
		// pim.clickAdd();
		//
		// //Delete created performance indicator
		// espf.getSimulationOOPITab().getPerformanceIndicatorTable().clickLinkInRow("Performance
		// Indicator Text", "Delete");
		// espf.clickOKOnAlert();

		// PERSONNEL
		// Edit existing personnel
		// PersonnelModal pm =
		// espf.getPersonnelTab().clickEditInPersonnelTable("Board Member",
		// "2");
		// System.out.println("Personnel: " + pm.readPersonnel());
		// System.out.println("Qty: " + pm.readQty());
		// System.out.println("Role/Responsibility: " +
		// pm.readRoleResponsibility());
		//
		// pm.selectPersonnel("Chairman");
		// pm.setQty("40");
		// pm.setRoleResponsibility("Role and such");
		//
		// System.out.println("Personnel: " + pm.readPersonnel());
		// System.out.println("Qty: " + pm.readQty());
		// System.out.println("Role/Responsibility: " +
		// pm.readRoleResponsibility());
		//
		// pm.clickClose();
		//
		// //Add New Personnel
		// pm = espf.getPersonnelTab().clickAddPersonnelLink();
		// pm.selectPersonnel("Standardized Patient");
		// pm.setQty("5");
		// pm.setRoleResponsibility("Role Responsibility Text");
		// pm.clickAdd();
		//
		// //Delete Newly Added Personnel from table.
		// espf.getPersonnelTab().clickDeleteInPersonnelTable("Standardized
		// Patient", "5");
		// espf.clickOKOnAlert();

		// PREREQUISITE
//		espf.getPrerequsitiesTab().setTermsAndConditionsCheckbox(true);
//		System.out.println("Terms and Conditions: "
//				+ espf.getPrerequsitiesTab().isTermsAndConditionsChecked());

		// Prerequisite Modal
		// PrerequisiteModal prereq =
		// espf.getPrerequsitiesTab().clickAddPrerequisite();
		// prereq.setFileName("Test File Name");
		// prereq.selectFile("C:\\Users\\jesse.childress\\Pictures\\Saved
		// Pictures\\Sample Picture - Koala.jpg");
		// prereq.setDescription("This is a description");
		// prereq.setCourse("This is a course");
		// prereq.clickAdd();

//		PrerequisiteModal prereq = espf.getPrerequsitiesTab()
//				.clickEditOnPrerequisiteTableByName("Test Prerequisite");
//		System.out.println("File Name: " + prereq.readName());
//		System.out.println("Selected File: " + prereq.readSelectedFile());
//		System.out.println("Description: " + prereq.readDescription());
//		System.out.println("Course: " + prereq.readCourse());
//		prereq.clickClose();
//
//		espf.getPrerequsitiesTab()
//				.clickDeleteInPrerequisitesTableByName("Test File Name 2");
//		espf.clickOKOnAlert();
		
		
		
		//EQUIPMENT
		//Jesse Was Here
		String equipmentCategoryName = "Test Category";
		String item = "Item 1";
		espf.getEquipmentTab().setCheckbox(equipmentCategoryName, true);
		espf.getEquipmentTab().setCheckbox(equipmentCategoryName, false);

		EquipmentModal equip = espf.getEquipmentTab().clickLink(equipmentCategoryName);
		equip.setItem(item);
		equip.setQty("5");
		equip.setNote("Note goes here");
		equip.clickAdd();
		
		//TEMP BUG
		espf.clickHomeLogo();
		HomePageFactory home = new HomePageFactory();
		home.clickSimulationInMyMenu();
		spf.gotoTablePageWithRow(spf.getSimulationsTable(), "Title", "Temp Simulation");
		spf.getSimulationsTable().clickLinkInRow("Temp Simulation", "Edit");
		//New reference to page
		espf = new EditSimulationPageFactory();
		
		System.out.println("Item: " + espf.getEquipmentTab().getTable(equipmentCategoryName).readTableRowValue("Item", item, "Item", true));
		System.out.println("Qty: " + espf.getEquipmentTab().getTable(equipmentCategoryName).readTableRowValue("Item", item, "Qty", true));
		System.out.println("Note: " + espf.getEquipmentTab().getTable(equipmentCategoryName).readTableRowValue("Item", item, "Note", true));
		
		espf.getEquipmentTab().getTable(equipmentCategoryName).clickLinkInRow(item, "Delete");

	}

}
