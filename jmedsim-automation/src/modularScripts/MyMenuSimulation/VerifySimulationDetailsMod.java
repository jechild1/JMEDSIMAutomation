package modularScripts.MyMenuSimulation;

import pageFactories.JMEDSimTables;
import pageFactories.MyMenuPages.SimulationDetailsPageFactory;
import pageFactories.MyMenuPages.SimulationDetailsPageFactory.AdministrativeDetailsTab;
import pageFactories.MyMenuPages.SimulationDetailsPageFactory.EnvironmentAndEquipmentSetupTab;
import pageFactories.MyMenuPages.SimulationDetailsPageFactory.EquipmentTab;
import pageFactories.MyMenuPages.SimulationDetailsPageFactory.LearnersTab;
import pageFactories.MyMenuPages.SimulationDetailsPageFactory.MedicalRecordsPatientDocumentationTab;
import pageFactories.MyMenuPages.SimulationDetailsPageFactory.PersonnelTab;
import pageFactories.MyMenuPages.SimulationDetailsPageFactory.PrerequisitesTab;
import pageFactories.MyMenuPages.SimulationDetailsPageFactory.SimulationDescriptionTab;
import pageFactories.MyMenuPages.SimulationDetailsPageFactory.SimulationEnvironmentTab;
import pageFactories.MyMenuPages.SimulationDetailsPageFactory.SimulationOOPITab;
import pageFactories.MyMenuPages.SimulationDetailsPageFactory.StandardizedPatientScriptsTab;
import pageFactories.MyMenuPages.SimulationsPageFactory;
import utilities.AutomationHelper;
import utilities.ExcelDataConfig;

/**
 * Module Test Script
 * 
 * Extends SimulationDetailValidationsBase
 * 
 * @author scott.brazelton
 *
 */
public class VerifySimulationDetailsMod
		extends
			SimulationDetailValidationsBase {

	/**
	 * This test script module verifies the details of a specified simulation
	 * <br>
	 * Pre-Condition: On My Menu > Simulation Page <br>
	 * Post-Condition: On My Menu > Simulation Page
	 * 
	 * @param simulationDataMapID
	 *            - TestDataID from JMEDSimulationDataMap.xlsx
	 * @param isOriginal
	 *            - true = verifying original data against details | false =
	 *            verifying edited data against original
	 */
	public void execute(String simulationDataMapID, boolean isOriginal) {
		AutomationHelper.printClassName();
		AutomationHelper.printMethodName();

		this.isOriginal = isOriginal;

		// prefix to use edit column if editing
		this.pre = isOriginal ? "" : "Edit";

		ExcelDataConfig mdm = getExcelFile("JMEDSimulationDataMap.xlsx",
				"MainDataMap");

		this.setDMID("adminDetailsDataMapID",
				mdm.getData(simulationDataMapID, "AdminDetailsDataMapID"));

		this.setDMID("serviceRoleDMGroupId",
				mdm.getData(simulationDataMapID, "ServiceRoleDataMapGroupID"));

		this.setDMID("simulationDescriptionDMId",
				mdm.getData(simulationDataMapID, "SimulationDescDataMapID"));

		this.setDMID("additionalLearnerDMGroupId", mdm.getData(
				simulationDataMapID, "AdditionalLearnerDataMapGroupID"));

		this.setDMID("simOOPIDataMapID",
				mdm.getData(simulationDataMapID, "SimOOPIDataMapID"));

		this.setDMID("personnelDataMapGroupID",
				mdm.getData(simulationDataMapID, "PersonnelDataMapGroupID"));

		this.setDMID("prerequisitesDataMapGroupID", mdm
				.getData(simulationDataMapID, "PrerequisitesDataMapGroupID"));

		this.setDMID("equipmentDataMapID",
				mdm.getData(simulationDataMapID, "EquipmentDataMapID"));

		this.setDMID("medRecordsPatientDocDataMapGroupID", mdm.getData(
				simulationDataMapID, "MedRecordsPatientDocDataMapGroupID"));

		this.setDMID("simEnvironmentDataMapID",
				mdm.getData(simulationDataMapID, "SimEnvironmentDataMapID"));

		this.setDMID("envEquipSetupDataMapGroupID", mdm
				.getData(simulationDataMapID, "EnvEquipSetupDataMapGroupID"));

		this.setDMID("standardPatientScriptsDataMapGroupID", mdm.getData(
				simulationDataMapID, "StandardPatientScriptsDataMapGroupID"));

		this.addm = getExcelFile("JMEDSimulationDataMap.xlsx",
				"AdminDetailsDataMap");

		ExcelDataConfig stv = getExcelFile("JMEDContentMatrix.xlsx",
				"StaticTextValidations");

		SimulationsPageFactory spf = new SimulationsPageFactory();

		spf.gotoTablePageWithRow(spf.getSimulationsTable(), "Title",
				addm.getData(getDMID("adminDetailsDataMapID"), pre + "Title"));

		// Validates the index table before going to details
		validateSimulationsIndexTableDetails(spf);

		spf.getSimulationsTable().clickLinkInRow("Title",
				addm.getData(getDMID("adminDetailsDataMapID"), pre + "Title"),
				"", "Details", false);

		this.sd = new SimulationDetailsPageFactory();

		softAsserter.assertEquals(sd.readPageTitle(),
				stv.getData("MMSP_SD_Title", "Title"),
				"Validating page title when reviewing details of a simulation");

		// Validate common Administrative Details items
		validateAdministrativeDetails(sd.getAdministrativeDetailsTab());

		// Validates Administrative Details not also on delete page
		validateAdditionalAdministrativeDetailsTab();

		validateSimulationDescriptionTab();

		validateLearnersTab();

		validateSimulationOOPITab();

		validatePersonnelTab();

		validatePrerequisitesTab();

		validateEquipmentTab();

		validateMedicalRecordsPatientDocumentationTab();

		validateSimulationEnvironmentTab();

		validateEnvironmentAndEquipmentSetupTab();

		validateStandardizedPatientScripts();

		// TODO: Validate other tabs as page factory functionality become
		// available

		sd.clickBackToList();

	}

	/**
	 * Performs validation against specified row on My Menu > Simulations index
	 * page
	 * 
	 * @param spf
	 */
	private void validateSimulationsIndexTableDetails(
			SimulationsPageFactory spf) {
		AutomationHelper.printMethodName();

		String addmID = getDMID("adminDetailsDataMapID");

		JMEDSimTables st = spf.getSimulationsTable();

		String title = addm.getData(addmID, "Title");

		softAsserter.assertEquals(
				st.readTableRowValue("Title", title, "Duration (hh:mm)", false),
				addm.getData(addmID, pre + "DurationHours") + ":"
						+ addm.getData(addmID, pre + "DurationMinutes"),
				String.format("Validating 'Duration' in %s Table row", title));

		softAsserter.assertEquals(
				st.readTableRowValue("Title", title, "Author", false),
				addm.getData(addmID, pre + "Author"),
				String.format("Validating 'Author' in %s Table row", title));

		softAsserter.assertEquals(
				st.readTableRowValue("Title", title, "Created On", false),
				addm.getData(addmID, "CreatedOn"), String.format(
						"Validating 'Created On' in %s Table row", title));

		softAsserter.assertEquals(
				st.readTableRowValue("Title", title, "Updated On", false),
				addm.getData(addmID, "UpdatedOn"), String.format(
						"Validating 'Updated On' in %s Table row", title));

		softAsserter.assertEquals(
				st.isTableRowValueSelected("Title", title, "Published", false),
				addm.getData(addmID, "Published").equals("") ? false : true,
				String.format("Validating 'Published' in %s Table row", title));

		softAsserter.assertEquals(
				st.isTableRowValueSelected("Title", title, "Active", false),
				addm.getData(addmID, pre + "Active").equals("") ? false : true,
				String.format("Validating 'Duration' in %s Table row", title));

	}

	/**
	 * Performs additional administrative details validations that are not in
	 * both details and delete pages
	 */
	private void validateAdditionalAdministrativeDetailsTab() {
		AutomationHelper.printMethodName();

		String addmID = getDMID("adminDetailsDataMapID");
		String srdmGroupId = getDMID("serviceRoleDMGroupId");

		ExcelDataConfig srdm = getExcelFile("JMEDSimulationDataMap.xlsx",
				"ServiceRoleDataMap");

		// get instance of Admin details tab so don't have to keep
		// re-referencing the whole method chain
		AdministrativeDetailsTab adt = sd.getAdministrativeDetailsTab();

		softAsserter.assertEquals(adt.readDuration(),
				addm.getData(addmID, pre + "DurationHours") + ":"
						+ addm.getData(addmID, pre + "DurationMinutes"),
				"Validating 'Duration (minutes)' in Administrative Details");

		softAsserter.assertEquals(adt.readContactInformation(),
				addm.getData(addmID, pre + "ContactInformation"),
				"Validating 'Contact Information' in Administrative Details");

		softAsserter.assertEquals(adt.readCreatedBy(),
				addm.getData(addmID, "CreatedBy"),
				"Validating 'Created By' in Administrative Details");

		softAsserter.assertEquals(adt.readContentKeywords(),
				addm.getData(addmID, pre + "ContentKeywords"),
				"Validating 'Content Keywords' in Administrative Details");

		// Validate columns Service Code & Service Role - NOT EDITABLE
		validateTableColumns(srdm, srdmGroupId, adt.getServiceRoleTable(),
				"Service Role", new String[]{"Service Code", "Service Role"},
				false);
	}

	/**
	 * Validate fields on Simulation Description tab
	 */
	private void validateSimulationDescriptionTab() {
		AutomationHelper.printMethodName();

		String sddmId = getDMID("simulationDescriptionDMId");

		SimulationDescriptionTab sdt = sd.getSimulationDescriptionTab();

		ExcelDataConfig srdm = getExcelFile("JMEDSimulationDataMap.xlsx",
				"SimulationDescDataMap");

		softAsserter.assertEquals(sdt.readSimulationDescription(),
				srdm.getData(sddmId, pre + "Description"),
				"Validating 'Description' in Simulation Description details");
	}

	/**
	 * Performs validation on Learners Tab of Simulation Details
	 */
	private void validateLearnersTab() {
		AutomationHelper.printMethodName();

		String srdmGroupId = getDMID("serviceRoleDMGroupId");

		ExcelDataConfig srdm = getExcelFile("JMEDSimulationDataMap.xlsx",
				"ServiceRoleDataMap");

		String aldmGroupId = getDMID("additionalLearnerDMGroupId");

		ExcelDataConfig aldm = getExcelFile("JMEDSimulationDataMap.xlsx",
				"AdditionalLearnerDataMap");

		// get instance of Admin details tab so don't have to keep
		// re-referencing the whole method chain
		LearnersTab lt = sd.getLearnersTab();

		// Validate columns Learner Profile & Qty - EDITABLE
		validateTableColumns(srdm, srdmGroupId, lt.getLearnersTable(),
				"Service Role", new String[]{"Learner Profile", "Qty"}, true);

		// Validate columns for Additional Learners - EDITABLE
		validateTableColumns(aldm, aldmGroupId, lt.getAdditionalLearnerTable(),
				"Additional Learner",
				new String[]{"Learner Profile", "Other Learner Profile", "Qty"},
				true);

	}

	/**
	 * Performs validation on Simulation OOPI tab of Simulation Details
	 */
	private void validateSimulationOOPITab() {
		AutomationHelper.printMethodName();

		String sodmId = getDMID("simOOPIDataMapID");

		ExcelDataConfig sodm = getExcelFile("JMEDSimulationDataMap.xlsx",
				"SimOOPIDataMap");

		String soodmGroupID = sodm.getData(sodmId, "ObjectiveGroupID");
		String sopidmGroupID = sodm.getData(sodmId,
				"PerformanceIndicatorGroupID");

		ExcelDataConfig soodm = getExcelFile("JMEDSimulationDataMap.xlsx",
				"SimOOPIObjectiveDataMap");

		ExcelDataConfig sopidm = getExcelFile("JMEDSimulationDataMap.xlsx",
				"SimOOPIPerIndDataMap");

		SimulationOOPITab sot = sd.getSimulationOOPITab();

		softAsserter.assertEquals(sot.readOOPITypeSelected(),
				sodm.getData(sodmId, pre + "OOPI Type"),
				"Validating 'OOPI Type' in Simulation OOPI details");

		softAsserter.assertEquals(sot.readOOPITypeSelected(),
				sodm.getData(sodmId, pre + "OOPI Type"),
				"Validating 'OOPI Type' in Simulation OOPI details");

		// Validate columns Objective - EDITABLE
		validateTableColumns(soodm, soodmGroupID, sot.getObjectiveTable(),
				"Objective", new String[]{"Objective"}, true);

		// Validate columns Performance Indicators - EDITABLE
		validateTableColumns(sopidm, sopidmGroupID,
				sot.getPerformanceIndicatorTable(), "Performance Indicator",
				new String[]{"Performance Indicator"}, true);
	}

	/**
	 * Performs validation on Personnel tab of Simulation Details
	 */
	private void validatePersonnelTab() {
		AutomationHelper.printMethodName();

		String pdmGroupId = getDMID("personnelDataMapGroupID");

		ExcelDataConfig pdm = getExcelFile("JMEDSimulationDataMap.xlsx",
				"PersonnelDataMap");

		PersonnelTab pt = sd.getPersonnelTab();

		// Validate columns Personnel - EDITABLE
		validateTableColumns(pdm, pdmGroupId, pt.getPersonnelTable(),
				"Personnel",
				new String[]{"Personnel", "Qty", "Role/Responsibility"}, true);
	}

	/**
	 * Performs validation on Prerequisites tab of Simulation Details
	 */
	private void validatePrerequisitesTab() {
		AutomationHelper.printMethodName();

		String pdmGroupId = getDMID("prerequisitesDataMapGroupID");

		ExcelDataConfig pdm = getExcelFile("JMEDSimulationDataMap.xlsx",
				"PrerequisitesDataMap");

		PrerequisitesTab pt = sd.getPrerequisitesTab();

		// Validate columns Prerequisites - EDITABLE
		validateTableColumns(pdm, pdmGroupId, pt.getPrerequisitesTable(),
				"Name", new String[]{"Name", "Description", "Course"}, true);
	}

	/**
	 * Performs validation on Equipment tab of Simulation Details
	 */
	private void validateEquipmentTab() {
		AutomationHelper.printMethodName();

		String edmId = getDMID("equipmentDataMapID");

		ExcelDataConfig edm = getExcelFile("JMEDSimulationDataMap.xlsx",
				"EquipmentDataMap");

		String sdmGroupId = edm.getData(edmId, "SimulatorGroupID");
		ExcelDataConfig sdm = getExcelFile("JMEDSimulationDataMap.xlsx",
				"EquipSimulatorDataMap");

		String cdmGroupId = edm.getData(edmId, "ConsumablesGroupID");
		ExcelDataConfig cdm = getExcelFile("JMEDSimulationDataMap.xlsx",
				"EquipConsumablesDataMap");

		String ncdmGroupId = edm.getData(edmId, "NonConsumablesGroupID");
		ExcelDataConfig ncdm = getExcelFile("JMEDSimulationDataMap.xlsx",
				"EquipNonConsumablesDataMap");

		String avitcdmGroupId = edm.getData(edmId, "AVITCommunicationsGroupID");
		ExcelDataConfig avitcdm = getExcelFile("JMEDSimulationDataMap.xlsx",
				"EquipAVITCommDataMap");

		EquipmentTab et = sd.getEquipmentTab();

		// Validate columns Simulator - EDITABLE
		validateTableColumns(sdm, sdmGroupId, et.getSimulatorTable(),
				"Simulator Name", new String[]{"Simulator Name", "Qty", "Note"},
				true);

		// Validate columns Consumables - EDITABLE
		validateTableColumns(cdm, cdmGroupId, et.getConsumablesTable(), "Item",
				new String[]{"Item", "Qty", "Note"}, true);

		// Validate columns Consumables - EDITABLE
		validateTableColumns(ncdm, ncdmGroupId, et.getNonConsumablesTable(),
				"Item", new String[]{"Item", "Qty", "Note"}, true);

		// Validate columns Consumables - EDITABLE
		validateTableColumns(avitcdm, avitcdmGroupId,
				et.getAVITCommunicationsTable(), "Item",
				new String[]{"Item", "Qty", "Note"}, true);

	}

	/**
	 * Performs validation on Medical Record(s)/ Patient Documentation tab of
	 * Simulation Details
	 */
	private void validateMedicalRecordsPatientDocumentationTab() {
		AutomationHelper.printMethodName();

		String mrpddmGroupId = getDMID("medRecordsPatientDocDataMapGroupID");

		ExcelDataConfig mrpddm = getExcelFile("JMEDSimulationDataMap.xlsx",
				"MedRecordsPatientDocDataMap");

		MedicalRecordsPatientDocumentationTab mrpdt = sd
				.getMedicalRecordsPatientDocumentationTab();

		// Validate columns Medical Records Patient Documentation - EDITABLE
		validateTableColumns(mrpddm, mrpddmGroupId,
				mrpdt.getMedicalRecordsPatientDocumentationTable(), "File Name",
				new String[]{"File Name", "Description"}, true);
	}

	/**
	 * Performs validation on Simulation Environment tab of Simulation Details
	 */
	private void validateSimulationEnvironmentTab() {
		AutomationHelper.printMethodName();

		String sedmId = getDMID("simEnvironmentDataMapID");

		ExcelDataConfig sedm = getExcelFile("JMEDSimulationDataMap.xlsx",
				"SimEnvironmentDataMap");

		String sefpdmGroupID = sedm.getData(sedmId,
				"SimEnvironmentFurnPropDataMapGroupID");

		ExcelDataConfig sefpdm = getExcelFile("JMEDSimulationDataMap.xlsx",
				"SimEnvironmentFurnPropDataMap");

		SimulationEnvironmentTab set = sd.getSimulationEnvironmentTab();

		softAsserter.assertEquals(set.readSpaceRequirementLocation(),
				sedm.getData(sedmId, pre + "Space Requirement/Location"),
				"Validating 'Space Requirement/Location' in Simulation Environment details");

		softAsserter.assertEquals(set.readClinicalOperationalEnvironmentType(),
				sedm.getData(sedmId,
						pre + "Clinical/Operational Environment Type"),
				"Validating 'Clinical/Operational Environment Type' in Simulation Environment details");

		// Validate columns Furniture / Prop - EDITABLE
		validateTableColumns(sefpdm, sefpdmGroupID, set.getFurniturePropTable(),
				"Item", new String[]{"Item", "Qty", "Notes/Use"}, true);
	}

	/**
	 * Performs validation on Environment and Equipment Setup tab of Simulation
	 * Details
	 */
	private void validateEnvironmentAndEquipmentSetupTab() {
		AutomationHelper.printMethodName();

		String eesdmGroupId = getDMID("envEquipSetupDataMapGroupID");

		ExcelDataConfig eesdm = getExcelFile("JMEDSimulationDataMap.xlsx",
				"EnvEquipSetupDataMap");

		EnvironmentAndEquipmentSetupTab eest = sd
				.getEnvironmentAndEquipmentSetupTab();

		// Validate columns Environment and Equipment Setup - EDITABLE
		validateTableColumns(eesdm, eesdmGroupId,
				eest.getEnvironmentAndEquipmentSetupTable(), "Name",
				new String[]{"Name", "Description"}, true);
	}

	/**
	 * Performs validation on Standardized Patient Scripts tab of Simulation
	 * Details
	 */
	private void validateStandardizedPatientScripts() {
		AutomationHelper.printMethodName();

		String spsdmGroupId = getDMID("standardPatientScriptsDataMapGroupID");

		ExcelDataConfig spsdm = getExcelFile("JMEDSimulationDataMap.xlsx",
				"StandardPatientScriptsDataMap");

		StandardizedPatientScriptsTab spst = sd
				.getStandardizedPatientScriptsTab();

		// Validate columns Environment and Equipment Setup - EDITABLE
		validateTableColumns(spsdm, spsdmGroupId,
				spst.getStandardizedPatientScriptsTable(), "Script Name",
				new String[]{"Script Name", "Description"}, true);
	}
}
