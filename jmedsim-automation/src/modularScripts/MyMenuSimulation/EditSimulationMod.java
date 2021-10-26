package modularScripts.MyMenuSimulation;

import java.util.HashMap;

import configuration.JMEDConfig;
import pageFactories.JMEDSimTables;
import pageFactories.MyMenuPages.EditSimulationPageFactory;
import pageFactories.MyMenuPages.EditSimulationPageFactory.AdministrativeDetailsTab;
import pageFactories.MyMenuPages.EditSimulationPageFactory.AdministrativeDetailsTab.AddServiceRolesModal;
import pageFactories.MyMenuPages.EditSimulationPageFactory.EnvironmentAndEquipmentSetupTab;
import pageFactories.MyMenuPages.EditSimulationPageFactory.EnvironmentAndEquipmentSetupTab.EnvironmentAndEquipmentSetupModal;
import pageFactories.MyMenuPages.EditSimulationPageFactory.EquipmentTab;
import pageFactories.MyMenuPages.EditSimulationPageFactory.EquipmentTab.EquipmentModal;
import pageFactories.MyMenuPages.EditSimulationPageFactory.LearnersTab;
import pageFactories.MyMenuPages.EditSimulationPageFactory.LearnersTab.AdditionalLearnerModal;
import pageFactories.MyMenuPages.EditSimulationPageFactory.LearnersTab.EditLearnerModal;
import pageFactories.MyMenuPages.EditSimulationPageFactory.MedicalRecordsPatientDocumentationTab;
import pageFactories.MyMenuPages.EditSimulationPageFactory.MedicalRecordsPatientDocumentationTab.MedicalRecordsPatientDocumentationModal;
import pageFactories.MyMenuPages.EditSimulationPageFactory.PersonnelTab;
import pageFactories.MyMenuPages.EditSimulationPageFactory.PersonnelTab.PersonnelModal;
import pageFactories.MyMenuPages.EditSimulationPageFactory.PrerequisitesTab;
import pageFactories.MyMenuPages.EditSimulationPageFactory.PrerequisitesTab.PrerequisiteModal;
import pageFactories.MyMenuPages.EditSimulationPageFactory.PrerequisitesTab.TermsAndConditionsModal;
import pageFactories.MyMenuPages.EditSimulationPageFactory.SimulationDescriptionTab;
import pageFactories.MyMenuPages.EditSimulationPageFactory.SimulationEnvironmentTab;
import pageFactories.MyMenuPages.EditSimulationPageFactory.SimulationEnvironmentTab.EnvironmentFurniturePropModal;
import pageFactories.MyMenuPages.EditSimulationPageFactory.SimulationOOPITab;
import pageFactories.MyMenuPages.EditSimulationPageFactory.SimulationOOPITab.ObjectiveModal;
import pageFactories.MyMenuPages.EditSimulationPageFactory.SimulationOOPITab.PerformanceIndicatorModal;
import pageFactories.MyMenuPages.EditSimulationPageFactory.StandardizedPatientScriptsTab;
import pageFactories.MyMenuPages.EditSimulationPageFactory.StandardizedPatientScriptsTab.StandardizedPatientScriptModal;
import pageFactories.MyMenuPages.SimulationsPageFactory;
import utilities.AutomationHelper;
import utilities.ExcelDataConfig;

/**
 * Module Test Script
 * 
 * Extends JMEDConfig
 * 
 * @author scott.brazelton
 *
 */
public class EditSimulationMod extends JMEDConfig {

	private boolean isValidateFieldErrors;
	private boolean isInitialCreation;
	private String pre;
	private ExcelDataConfig addm;
	private EditSimulationPageFactory es;
	// Modify possibilities
	private enum ModifyType {
		DO_NOTHING, ADD, EDIT, DELETE
	}

	// Hashmap to store data map ID's
	private HashMap<String, String> dmIDs = new HashMap<String, String>();

	/**
	 * Sets a reference to a data map ID
	 * 
	 * @param hashmapKey
	 * @param dmID
	 */
	private void setDMID(String hashmapKey, String dmID) {
		dmIDs.put(hashmapKey, dmID);
	}

	/**
	 * Returns a generic object reference of a data map ID based on the key
	 * supplied
	 * 
	 * @param hashmapKey
	 * @return String
	 */
	private String getDMID(String hashmapKey) {
		return dmIDs.get(hashmapKey);
	}

	private ModifyType getModifyType(String changeType) {

		ModifyType modifyType = ModifyType.DO_NOTHING;

		if (isInitialCreation && !changeType.equals("Add")) {
			modifyType = ModifyType.ADD;
		} else if (!isInitialCreation && changeType.equals("Add")) {
			modifyType = ModifyType.ADD;
		} else if (!isInitialCreation && changeType.equals("Edit")) {
			modifyType = ModifyType.EDIT;
		} else if (!isInitialCreation && changeType.equals("Delete")) {
			modifyType = ModifyType.DELETE;
		}

		return modifyType;

	}

	/**
	 * Performs on-demand validations against specific row columns of a table
	 * 
	 * NOTE: Excel mapping must provide GroupTestDataID & ChangeType
	 * 
	 * @param dm
	 * @param dmGroupId
	 * @param table
	 * @param colKey
	 * @param colsToTest
	 * @param isColsEditable
	 */
	private void validateTableColumns(ExcelDataConfig dm, String dmGroupId,
			JMEDSimTables table, String colKey, String[] colsToTest,
			boolean isColsEditable) {

		for (int i = 1; i <= dm.getRowCount(); i++) {
			String groupID = dm.getData(i, "GroupTestDataID");
			String changeType = dm.getData(i, "ChangeType");

			if (groupID.equals(dmGroupId)) {
				ModifyType modType = getModifyType(changeType);

				String editLine = modType == ModifyType.EDIT && isColsEditable
						? pre
						: "";
				String eColKeyValue = dm.getData(i, editLine + colKey);

				for (String col : colsToTest) {
					String eColValue = dm.getData(i, editLine + col);

					// Verify row is present if not expected delete
					if (modType != ModifyType.DELETE) {

						// Verify expected column value equals actual column
						// value

						String aColValue = table.readTableRowValue(colKey,
								eColKeyValue, col, false);
						softAsserter.assertEquals(aColValue, eColValue,
								String.format(
										"Validate '%s' %s added to table for %s %s in Edit",
										eColValue, col, eColKeyValue, colKey));

					} // end row present

					// Verify row deleted if expected
					if (modType == ModifyType.DELETE) {

						boolean isRowInTable = table.isRowInTableByValue(colKey,
								eColKeyValue);
						softAsserter.assertEquals(isRowInTable, false,
								String.format(
										"Validate '%s' %s deleted from table in Edit",
										eColKeyValue, colKey));
					} // end row deleted

				} // end for colsToTest
			} // end if group equals expected loop
		} // end for

	}

	/**
	 * This test script module modifies Simulation data based on dynamic data
	 * <br>
	 * Pre-Condition: On My Menu > Simulation Page <br>
	 * Post-Condition: On My Menu > Simulation Page > Edit Page
	 * 
	 * @param simulationDataMapID
	 *            - TestDataID from JMEDSimulationDataMap.xlsx
	 * @param validateFieldErrors
	 *            - true = validate all required field errors | false = skip
	 *            required field errors
	 * @param isInitialCreation
	 *            - true = use original data to populate fields on edit page |
	 *            false = use edit data to populate fields on edit page
	 */
	public void execute(String simulationDataMapID, boolean validateFieldErrors,
			boolean isInitialCreation) {
		AutomationHelper.printClassName();
		AutomationHelper.printMethodName();

		this.isValidateFieldErrors = validateFieldErrors;
		this.isInitialCreation = isInitialCreation;

		// prefix to use edit column if editing
		this.pre = isInitialCreation ? "" : "Edit";

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

		SimulationsPageFactory spf = new SimulationsPageFactory();

		spf.gotoTablePageWithRow(spf.getSimulationsTable(), "Title",
				addm.getData(getDMID("adminDetailsDataMapID"), "Title"));

		spf.getSimulationsTable().clickLinkInRow("Title",
				addm.getData(getDMID("adminDetailsDataMapID"), "Title"), "",
				"Edit", false);

		this.es = new EditSimulationPageFactory();

		validateStaticTitle("MMSP_ES_Title", es.readPageTitle(),
				"Edit Simulation Page", "Page title");

		// Administrative Detail
		modifyAdministrativeDetailsTab();

		// Simulation Description
		modifySimulationDescriptionTab();

		// Learners
		modifyLearnersInLearnersTab();
		modifyAdditionalLearnerInLearnersTab();

		// Simulation OOPI
		modifySimulationOOPITab();

		// Personnel
		modifyPersonnelTab();

		// Prerequisites
		modifyPrerequisitesTab();

		// Equipment
		modifyEquipmentTab();

		// Medical Record(s)/ Patient Documentation
		modifyMedicalRecordsPatientDocumentationTab();

		// Simulation Environment
		modifySimulationEnvironmentTab();

		// Environment and Equipment Setup
		modifyEnvironmentAndEquipmentSetupTab();

		// Standardized Patient Script(s)
		modifyStandardizedPatientScriptTab();

		// TODO: add methods for each tab area

	}

	/**
	 * Modify Administrative Details within the Administrative Details tab
	 * 
	 * NOTE: if validateFieldErrors is true - required field error messages will
	 * be validated
	 */
	private void modifyAdministrativeDetailsTab() {
		AutomationHelper.printMethodName();

		String adminDetailsDataMapID = getDMID("adminDetailsDataMapID");

		AdministrativeDetailsTab adt = es.getAdministrativeDetailsTab();

		if (isValidateFieldErrors) {

			adt.setTitle("");
			adt.setDurationHours("");
			adt.setDurationMinutes("");
			adt.selectAuthor("Select Author");
			adt.setContentKeywords("");
			adt.clickSave();

			validateStaticContent("MMSP_ES_AD_TitleError",
					adt.readTitleErrorMessage(), "Admin Details tab",
					"Title error");

			validateStaticContent("MMSP_ES_AD_DurationError",
					adt.readDurationErrorMessage(), "Admin Details tab",
					"Duration error");

			validateStaticContent("MMSP_ES_AD_AuthorError",
					adt.readAuthorErrorMessage(), "Admin Details tab",
					"Author error");

			validateStaticContent("MMSP_ES_AD_ContentKeywordsError",
					adt.readContentKeywordsErrorMessage(), "Admin Details tab",
					"Content Keywords error");
		}

		adt.setTitle(addm.getData(adminDetailsDataMapID, pre + "Title"));
		adt.setDurationHours(
				addm.getData(adminDetailsDataMapID, pre + "DurationHours"));
		adt.setDurationMinutes(
				addm.getData(adminDetailsDataMapID, pre + "DurationMinutes"));
		adt.selectAuthor(addm.getData(adminDetailsDataMapID, pre + "Author"));
		adt.selectSite(addm.getData(adminDetailsDataMapID, pre + "Site"));
		adt.setContentKeywords(
				addm.getData(adminDetailsDataMapID, pre + "ContentKeywords"));
		adt.selectTrainingType(
				addm.getData(adminDetailsDataMapID, pre + "TrainingType"));
		adt.setActiveCheckbox(
				addm.getData(adminDetailsDataMapID, pre + "Active").equals("")
						? false
						: true);

		adt.clickSave();

		modifyServiceRoleInAdminDetailsTab(adt);

	}
	/**
	 * Modify the Service Role table within the Administrative Details tab
	 * 
	 * @param adt
	 */
	private void modifyServiceRoleInAdminDetailsTab(
			AdministrativeDetailsTab adt) {
		AutomationHelper.printMethodName();

		String serviceRoleDMGroupId = getDMID("serviceRoleDMGroupId");

		ExcelDataConfig srdm = getExcelFile("JMEDSimulationDataMap.xlsx",
				"ServiceRoleDataMap");

		for (int i = 1; i <= srdm.getRowCount(); i++) {
			String groupID = srdm.getData(i, "GroupTestDataID");
			String changeType = srdm.getData(i, "ChangeType");

			if (groupID.equals(serviceRoleDMGroupId)) {
				ModifyType modType = getModifyType(changeType);

				String eServiceRole = srdm.getData(i, "Service Role");

				// Add row if initial creation or specify add on edit
				if (modType == ModifyType.ADD) {

					AddServiceRolesModal asr = adt.clickAddServiceRole();

					// find by searching service role
					asr.setSearchBy(eServiceRole);
					asr.clickSearch();

					asr.getAddServiceRolesTable().clickLinkInRow("Service Role",
							eServiceRole, "", "Add", false);

					asr.clickClose();

					// reset Administrative Details Tab
					es.getAdministrativeDetailsTab();

				} // end add logic

				// Delete row if not initial creation and specified delete
				if (modType == ModifyType.DELETE) {
					adt.getServiceRoleTable().clickLinkInRow("Service Role",
							eServiceRole, "", "Delete", false);

					adt.clickOKOnAlert();

					// reset Administrative Details Tab
					es.getAdministrativeDetailsTab();
				}

			} // end if group equals expected loop
		} // end for

		validateTableColumns(srdm, serviceRoleDMGroupId,
				adt.getServiceRoleTable(), "Service Role",
				new String[]{"Service Code", "Service Role"}, false);
	}

	/**
	 * Modify Description within the Simulation Description tab
	 * 
	 * NOTE: if validateFieldErrors is true - required field error messages will
	 * be validated
	 */
	private void modifySimulationDescriptionTab() {
		AutomationHelper.printMethodName();

		String simulationDescriptionDMId = getDMID("simulationDescriptionDMId");

		ExcelDataConfig srdm = getExcelFile("JMEDSimulationDataMap.xlsx",
				"SimulationDescDataMap");

		SimulationDescriptionTab sdt = es.getSimulationDescriptionTab();

		if (isValidateFieldErrors) {
			sdt.setSimulationDescription("");

			sdt.clickSave();

			validateStaticContent("MMSP_ES_SD_DescriptionError",
					sdt.readSimulationDescriptionErrorMessage(),
					"Simulation Description tab", "Description error");
		}

		int paragraphSize = Integer
				.parseInt(srdm.getData(simulationDescriptionDMId, "Size"));

		String desc = getRandomText(paragraphSize, paragraphSize);

		// write generated description to excel
		srdm.writeToWorkSheet(simulationDescriptionDMId, pre + "Description",
				desc);

		// write generated description to simulation description tab
		sdt.setSimulationDescription(desc);

		sdt.clickSave();

	}

	/**
	 * Modify Learners table in Learners Tab
	 * 
	 * NOTE: if validateFieldErrors is true - required field error messages will
	 * be validated
	 */
	private void modifyLearnersInLearnersTab() {
		AutomationHelper.printMethodName();

		String serviceRoleDMGroupId = getDMID("serviceRoleDMGroupId");

		ExcelDataConfig srdm = getExcelFile("JMEDSimulationDataMap.xlsx",
				"ServiceRoleDataMap");

		for (int i = 1; i <= srdm.getRowCount(); i++) {
			String groupID = srdm.getData(i, "GroupTestDataID");
			String changeType = srdm.getData(i, "ChangeType");

			if (groupID.equals(serviceRoleDMGroupId)) {
				ModifyType modType = getModifyType(changeType);

				String editLine = modType == ModifyType.EDIT ? pre : "";
				String eServiceRole = srdm.getData(i, "Service Role");
				String eLearnerProfile = srdm.getData(i,
						editLine + "Learner Profile");
				String eQty = srdm.getData(i, editLine + "Qty");

				// Edit the line as long as it is not deleted already
				if (modType == ModifyType.ADD || modType == ModifyType.EDIT) {

					EditLearnerModal elm = es.getLearnersTab()
							.clickEditInLearnersTableByServiceRole(
									eServiceRole);

					if (modType == ModifyType.ADD && isValidateFieldErrors) {
						elm.clickSave();

						validateStaticContent("MMSP_ES_L_EL_QtyError",
								elm.readQtyErrorMessage(), "Learners tab",
								"Qty error");
					}

					elm.selectLearnerProfile(eLearnerProfile);
					elm.setQty(eQty);

					elm.clickSave();
				} // end add logic
			} // end if group equals expected loop
		} // end for

		validateTableColumns(srdm, serviceRoleDMGroupId,
				es.getLearnersTab().getLearnersTable(), "Service Role",
				new String[]{"Learner Profile", "Qty"}, true);
	}

	/**
	 * Modify Additional Learner table in Learners Tab
	 * 
	 * NOTE: if validateFieldErrors is true - required field error messages will
	 * be validated
	 */
	private void modifyAdditionalLearnerInLearnersTab() {
		AutomationHelper.printMethodName();

		String aldmGroupId = getDMID("additionalLearnerDMGroupId");

		ExcelDataConfig aldm = getExcelFile("JMEDSimulationDataMap.xlsx",
				"AdditionalLearnerDataMap");

		for (int i = 1; i <= aldm.getRowCount(); i++) {
			String groupID = aldm.getData(i, "GroupTestDataID");
			String changeType = aldm.getData(i, "ChangeType");

			if (groupID.equals(aldmGroupId)) {
				ModifyType modType = getModifyType(changeType);

				String editLine = modType == ModifyType.EDIT ? pre : "";
				String eAdditionalLearner = aldm.getData(i,
						editLine + "Additional Learner");
				String eLearnerProfile = aldm.getData(i,
						editLine + "Learner Profile");
				String otherLearnerProfile = aldm.getData(i,
						editLine + "Other Learner Profile");
				String eQty = aldm.getData(i, editLine + "Qty");

				LearnersTab lt = es.getLearnersTab();

				switch (modType) {
					case ADD :
						AdditionalLearnerModal aalm = lt
								.clickAddAdditionalLearner();
						if (isValidateFieldErrors) {
							aalm.clickAdd();

							validateStaticContent(
									"MMSP_ES_L_AL_AdditionalLearnerError",
									aalm.readAdditionalLearnerErrorMessage(),
									"Additional Learners modal",
									"Additional Learner error");

							validateStaticContent("MMSP_ES_L_AL_QtyError",
									aalm.readQtyErrorMessage(),
									"Additional Learners modal", "Qty error");

							validateStaticContent(
									"MMSP_ES_L_AL_OtherLearnerError",
									aalm.readOtherLearnerProfileErrorMessage(),
									"Additional Learners modal",
									"Other Learner error");
						}

						aalm.setAdditionalLearner(eAdditionalLearner);

						if (!eLearnerProfile.equals("")) {
							aalm.selectLearnerProfile(eLearnerProfile);
						} else {
							aalm.setOtherLearnerProfile(otherLearnerProfile);
						}
						aalm.setQty(eQty);
						aalm.clickAdd();

						break;

					case EDIT :
						AdditionalLearnerModal ealm = lt
								.clickEditInAdditionalLearnersTableByAdditionalLearner(
										aldm.getData(i, "Additional Learner"));

						ealm.setAdditionalLearner(eAdditionalLearner);
						if (!eLearnerProfile.equals("")) {
							ealm.selectLearnerProfile(eLearnerProfile);
						} else {
							ealm.setOtherLearnerProfile(otherLearnerProfile);
						}
						ealm.setQty(eQty);
						ealm.clickSave();

						break;

					case DELETE :
						lt.clickDeleteInAdditionalLearnersTableByAdditionalLearner(
								eAdditionalLearner);

						lt.clickOKOnAlert();

						break;

					default :
						break;
				}
			} // end if group equals expected loop
		} // end for

		validateTableColumns(aldm, aldmGroupId,
				es.getLearnersTab().getAdditionalLearnersTable(),
				"Additional Learner",
				new String[]{"Learner Profile", "Other Learner Profile", "Qty"},
				true);
	}

	/**
	 * Modify Simulation OOPI Tab
	 * 
	 * NOTE: if validateFieldErrors is true - required field error messages will
	 * be validated
	 */
	private void modifySimulationOOPITab() {
		AutomationHelper.printMethodName();

		String simOOPIDataMapID = getDMID("simOOPIDataMapID");

		ExcelDataConfig sodm = getExcelFile("JMEDSimulationDataMap.xlsx",
				"SimOOPIDataMap");

		setDMID("objectiveGroupID",
				sodm.getData(simOOPIDataMapID, "ObjectiveGroupID"));

		setDMID("performanceIndicatorGroupID",
				sodm.getData(simOOPIDataMapID, "PerformanceIndicatorGroupID"));

		SimulationOOPITab sot = es.getSimulationOOPITab();

		sot.selectoOpiType(sodm.getData(simOOPIDataMapID, pre + "OOPI Type"));

		if (isValidateFieldErrors) {
			sot.setOutcome("");
			sot.clickSave();

			validateStaticContent("MMSP_ES_SimOOPI_OutcomeError",
					sot.readOutcomeErrorMessage(), "Simulation OOPI tab",
					"Outcome error");
		}

		int paragraphSize = Integer
				.parseInt(sodm.getData(simOOPIDataMapID, "Size"));

		String outcome = getRandomText(paragraphSize, paragraphSize);

		// write generated description to excel
		sodm.writeToWorkSheet(simOOPIDataMapID, pre + "Outcome", outcome);

		// write generated description to Outcome field
		sot.setOutcome(outcome);
		sot.clickSave();

		modifyObjectiveInSimulationOOPITab();

		modifyPerformanceIndicatorInSimulationOOPITab();

	}

	/**
	 * Modify Objective table in Simulation OOPI Tab
	 * 
	 * NOTE: if validateFieldErrors is true - required field error messages will
	 * be validated
	 */
	private void modifyObjectiveInSimulationOOPITab() {
		AutomationHelper.printMethodName();

		String soodmGroupID = getDMID("objectiveGroupID");

		ExcelDataConfig soodm = getExcelFile("JMEDSimulationDataMap.xlsx",
				"SimOOPIObjectiveDataMap");

		for (int i = 1; i <= soodm.getRowCount(); i++) {
			String groupID = soodm.getData(i, "GroupTestDataID");
			String changeType = soodm.getData(i, "ChangeType");

			if (groupID.equals(soodmGroupID)) {
				ModifyType modType = getModifyType(changeType);

				int paragraphSize = Integer.parseInt(soodm.getData(i, "Size"));
				// generate an objective if needed
				String objective = getRandomText(paragraphSize, paragraphSize);

				SimulationOOPITab sot = es.getSimulationOOPITab();

				switch (modType) {
					case ADD :
						ObjectiveModal com = sot.clickAddObjective();
						if (isValidateFieldErrors) {
							com.clickAdd();

							validateStaticContent(
									"MMSP_ES_SimOOPI_ObjectiveError",
									com.readObjectiveErrorMessage(),
									"Simulation OOPI Objective modal",
									"Objective error");
						}

						// write generated description to excel
						soodm.writeToWorkSheet(i, "Objective", objective);

						com.setObjective(objective);
						com.clickAdd();

						break;

					case EDIT :
						ObjectiveModal eom = sot
								.clickEditInObjectiveTableByObjective(
										soodm.getData(i, "Objective"));

						// write generated description to excel
						soodm.writeToWorkSheet(i, pre + "Objective", objective);

						eom.setObjective(objective);
						eom.clickSave();

						break;

					case DELETE :
						sot.clickDeleteInObjectiveTableByObjective(
								soodm.getData(i, "Objective"));

						sot.clickOKOnAlert();

						break;

					default :
						break;
				}
			} // end if group equals expected loop
		} // end for

		validateTableColumns(soodm, soodmGroupID,
				es.getSimulationOOPITab().getObjectiveTable(), "Objective",
				new String[]{"Objective"}, true);

	}

	/**
	 * Modify Performance Indicator table in Simulation OOPI Tab
	 * 
	 * NOTE: if validateFieldErrors is true - required field error messages will
	 * be validated
	 */
	private void modifyPerformanceIndicatorInSimulationOOPITab() {
		AutomationHelper.printMethodName();

		String sopidmGroupID = getDMID("performanceIndicatorGroupID");

		ExcelDataConfig sopidm = getExcelFile("JMEDSimulationDataMap.xlsx",
				"SimOOPIPerIndDataMap");

		for (int i = 1; i <= sopidm.getRowCount(); i++) {
			String groupID = sopidm.getData(i, "GroupTestDataID");
			String changeType = sopidm.getData(i, "ChangeType");

			if (groupID.equals(sopidmGroupID)) {
				ModifyType modType = getModifyType(changeType);

				int paragraphSize = Integer.parseInt(sopidm.getData(i, "Size"));
				// generate a performance indicator if needed
				String performanceIndicator = getRandomText(paragraphSize,
						paragraphSize);

				SimulationOOPITab sot = es.getSimulationOOPITab();

				switch (modType) {
					case ADD :
						PerformanceIndicatorModal cpim = sot
								.clickAddPerformanceIndicator();
						if (isValidateFieldErrors) {
							cpim.clickAdd();

							validateStaticContent(
									"MMSP_ES_SimOOPI_PerformIndError",
									cpim.readPerformanceIndicatorErrorMessage(),
									"Simulation OOPI Performance Indicator modal",
									"Performance Indicator error");
						}

						// write generated description to excel
						sopidm.writeToWorkSheet(i, "Performance Indicator",
								performanceIndicator);

						cpim.setPerformanceIndicator(performanceIndicator);
						cpim.clickAdd();

						break;

					case EDIT :
						PerformanceIndicatorModal epim = sot
								.clickEditInPerformanceIndicatorTableByPerformanceIndicator(
										sopidm.getData(i,
												"Performance Indicator"));

						// write generated description to excel
						sopidm.writeToWorkSheet(i,
								pre + "Performance Indicator",
								performanceIndicator);

						epim.setPerformanceIndicator(performanceIndicator);
						epim.clickSave();

						break;

					case DELETE :
						sot.clickDeleteInPerformanceIndicatorTableByPerformanceIndicator(
								sopidm.getData(i, "Performance Indicator"));

						sot.clickOKOnAlert();

						break;

					default :
						break;
				}
			} // end if group equals expected loop
		} // end for

		validateTableColumns(sopidm, sopidmGroupID,
				es.getSimulationOOPITab().getPerformanceIndicatorTable(),
				"Performance Indicator", new String[]{"Performance Indicator"},
				true);
	}

	/**
	 * Modify Personnel Tab
	 * 
	 * NOTE: if validateFieldErrors is true - required field error messages will
	 * be validated
	 */
	private void modifyPersonnelTab() {
		AutomationHelper.printMethodName();

		String pdmGroupID = getDMID("personnelDataMapGroupID");

		ExcelDataConfig pdm = getExcelFile("JMEDSimulationDataMap.xlsx",
				"PersonnelDataMap");

		for (int i = 1; i <= pdm.getRowCount(); i++) {
			String groupID = pdm.getData(i, "GroupTestDataID");
			String changeType = pdm.getData(i, "ChangeType");

			if (groupID.equals(pdmGroupID)) {
				ModifyType modType = getModifyType(changeType);

				String editLine = modType == ModifyType.EDIT ? pre : "";
				String ePersonnel = pdm.getData(i, editLine + "Personnel");
				String eQty = pdm.getData(i, editLine + "Qty");

				int paragraphSize = Integer.parseInt(pdm.getData(i, "Size"));
				// generate an objective if needed
				String roleResponsibility = getRandomText(paragraphSize,
						paragraphSize);

				PersonnelTab pt = es.getPersonnelTab();

				switch (modType) {
					case ADD :
						PersonnelModal cpm = pt.clickAddPersonnel();
						if (isValidateFieldErrors) {
							cpm.clickAdd();

							validateStaticContent(
									"MMSP_ES_Personnel_PersonnelError",
									cpm.readPersonnelErrorMessage(),
									"Personnel modal", "Personnel error");

							validateStaticContent("MMSP_ES_Personnel_QtyError",
									cpm.readQtyErrorMessage(),
									"Personnel modal", "Qty error");

							validateStaticContent(
									"MMSP_ES_Personnel_RoleRespError",
									cpm.readRoleResponsibilityErrorMessage(),
									"Personnel modal",
									"Role/Responsibility error");
						}

						cpm.selectPersonnel(ePersonnel);

						cpm.setQty(eQty);

						// write generated description to excel
						pdm.writeToWorkSheet(i, "Role/Responsibility",
								roleResponsibility);

						cpm.setRoleResponsibility(roleResponsibility);
						cpm.clickAdd();

						break;

					case EDIT :
						PersonnelModal eom = pt
								.clickEditInPersonnelTableByPersonnel(
										pdm.getData(i, "Personnel"));

						eom.selectPersonnel(ePersonnel);

						eom.setQty(eQty);

						// write generated description to excel
						pdm.writeToWorkSheet(i, pre + "Role/Responsibility",
								roleResponsibility);

						eom.setRoleResponsibility(roleResponsibility);
						eom.clickSave();

						break;

					case DELETE :
						pt.clickDeleteInPersonnelTableByPersonnel(ePersonnel);

						pt.clickOKOnAlert();

						break;

					default :
						break;
				}
			} // end if group equals expected loop
		} // end for

		validateTableColumns(pdm, pdmGroupID,
				es.getPersonnelTab().getPersonnelTable(), "Personnel",
				new String[]{"Personnel", "Qty", "Role/Responsibility"}, true);
	}

	/**
	 * Modify Prerequisites Tab
	 * 
	 * NOTE: if validateFieldErrors is true - required field error messages will
	 * be validated
	 */
	private void modifyPrerequisitesTab() {
		AutomationHelper.printMethodName();

		String pdmGroupID = getDMID("prerequisitesDataMapGroupID");

		ExcelDataConfig pdm = getExcelFile("JMEDSimulationDataMap.xlsx",
				"PrerequisitesDataMap");

		PrerequisitesTab pt = es.getPrerequsitiesTab();

		if (isInitialCreation) {
			TermsAndConditionsModal tacm = pt.clickTermsAndConditions();
			validateStaticContent("MMSP_ES_PRE_TermsAndConditions",
					tacm.readTermsAndConditions(),
					"Prerequisites Terms and Conditions modal",
					"Terms and Conditions text");

			tacm.clickClose();
		}

		for (int i = 1; i <= pdm.getRowCount(); i++) {
			String groupID = pdm.getData(i, "GroupTestDataID");
			String changeType = pdm.getData(i, "ChangeType");

			if (groupID.equals(pdmGroupID)) {
				ModifyType modType = getModifyType(changeType);

				int nameSize = Integer.parseInt(pdm.getData(i, "NameSize"));
				String name = getRandomText(nameSize, nameSize);

				String eFile = pdm.getData(i, "File"); // not editable

				int descriptionSize = Integer
						.parseInt(pdm.getData(i, "DescriptionSize"));
				String description = getRandomText(descriptionSize,
						descriptionSize);

				int courseSize = Integer.parseInt(pdm.getData(i, "CourseSize"));
				String course = getRandomText(courseSize, courseSize);

				switch (modType) {
					case ADD :
						PrerequisiteModal cpm = pt.clickAddPrerequisite();
						if (isValidateFieldErrors) {
							cpm.clickAdd();

							validateStaticContent("MMSP_ES_PRE_NameError",
									cpm.readNameErrorMessage(),
									"Prerequisites modal", "Name error");

							validateStaticContent("MMSP_ES_PRE_SelectFileError",
									cpm.readSelectFileErrorMessage(),
									"Prerequisites modal", "Select File error");

							validateStaticContent("MMSP_ES_PRE_CourseError",
									cpm.readCourseErrorMessage(),
									"Prerequisites modal", "Course error");
						}

						pdm.writeToWorkSheet(i, "Name", name);

						cpm.setName(name);

						cpm.selectFile(generateFullFileNameAndPath(
								"testFiles\\" + eFile));

						pdm.writeToWorkSheet(i, "Description", description);

						cpm.setDescription(description);

						pdm.writeToWorkSheet(i, "Course", course);

						cpm.setCourse(course);
						cpm.clickAdd();

						break;

					case EDIT :
						PrerequisiteModal eom = pt
								.clickEditOnPrerequisiteTableByName(
										pdm.getData(i, "Name"));

						pdm.writeToWorkSheet(i, pre + "Name", name);

						eom.setName(name);

						pdm.writeToWorkSheet(i, pre + "Description",
								description);

						eom.setDescription(description);

						pdm.writeToWorkSheet(i, pre + "Course", course);

						eom.setCourse(course);
						eom.clickSave();

						break;

					case DELETE :
						pt.clickDeleteInPrerequisitesTableByName(
								pdm.getData(i, "Name"));

						pt.clickOKOnAlert();

						break;

					default :
						break;
				}
			} // end if group equals expected loop
		} // end for

		validateTableColumns(pdm, pdmGroupID,
				es.getPrerequsitiesTab().getPrerequisiteTable(), "Name",
				new String[]{"Name", "Description", "Course"}, true);
	}

	/**
	 * Modify Equipment Tab
	 * 
	 * NOTE: if validateFieldErrors is true - required field error messages will
	 * be validated
	 */
	private void modifyEquipmentTab() {
		AutomationHelper.printMethodName();

		String edmID = getDMID("equipmentDataMapID");

		ExcelDataConfig edm = getExcelFile("JMEDSimulationDataMap.xlsx",
				"EquipmentDataMap");

		setDMID("simulatorGroupID", edm.getData(edmID, "SimulatorGroupID"));
		setDMID("consumablesGroupID", edm.getData(edmID, "ConsumablesGroupID"));
		setDMID("nonConsumablesGroupID",
				edm.getData(edmID, "NonConsumablesGroupID"));
		setDMID("avITCommunicationsGroupID",
				edm.getData(edmID, "AVITCommunicationsGroupID"));

		modifySimulatorInEquipmentTab();
		modifyConsumablesInEquipmentTab();
		modifyNonConsumablesInEquipmentTab();
		modifyAVITCommunicationsInEquipmentTab();

	}

	/**
	 * Modify Simulator table in Equipment Tab
	 * 
	 * NOTE: if validateFieldErrors is true - required field error messages will
	 * be validated
	 */
	private void modifySimulatorInEquipmentTab() {
		AutomationHelper.printMethodName();

		String sdmGroupID = getDMID("simulatorGroupID");

		ExcelDataConfig sdm = getExcelFile("JMEDSimulationDataMap.xlsx",
				"EquipSimulatorDataMap");

		EquipmentTab et = es.getEquipmentTab();

		et.setSimulatorNoneCheckbox(false);

		for (int i = 1; i <= sdm.getRowCount(); i++) {
			String groupID = sdm.getData(i, "GroupTestDataID");
			String changeType = sdm.getData(i, "ChangeType");

			if (groupID.equals(sdmGroupID)) {
				ModifyType modType = getModifyType(changeType);

				String editLine = modType == ModifyType.EDIT ? pre : "";
				String eSimulator = sdm.getData(i, editLine + "Simulator Name");
				String eQty = sdm.getData(i, editLine + "Qty");

				int noteSize = Integer.parseInt(sdm.getData(i, "NoteSize"));
				String note = getRandomText(noteSize, noteSize);

				switch (modType) {
					case ADD :
						EquipmentModal cem = et.clickAddSimulator();
						if (isValidateFieldErrors) {
							cem.clickAdd();

							validateStaticContent(
									"MMSP_ES_Equip_SimulatorError",
									cem.readSimulatorErrorMessage(),
									"Equipment modal", "Simulator error");

							validateStaticContent("MMSP_ES_Equip_QtyError",
									cem.readQtyErrorMessage(),
									"Equipment modal", "Qty error");

							validateStaticContent("MMSP_ES_Equip_NoteError",
									cem.readNoteErrorMessage(),
									"Equipment modal", "Note error");
						}

						cem.selectSimulator(eSimulator);
						cem.setQty(eQty);
						sdm.writeToWorkSheet(i, "Note", note);
						cem.setNote(note);

						cem.clickAdd();

						break;

					case EDIT :
						EquipmentModal eem = et
								.clickEditInSimulatorTableBySimulatorName(
										sdm.getData(i, "Simulator Name"));

						eem.selectSimulator(eSimulator);
						eem.setQty(eQty);
						sdm.writeToWorkSheet(i, pre + "Note", note);
						eem.setNote(note);

						eem.clickSave();

						break;

					case DELETE :
						et.clickDeleteInSimulatorTableBySimulatorName(
								sdm.getData(i, "Simulator Name"));

						et.clickOKOnAlert();

						break;

					default :
						break;
				}
			} // end if group equals expected loop
		} // end for

		validateTableColumns(sdm, sdmGroupID,
				es.getEquipmentTab().getSimulatorTable(), "Simulator Name",
				new String[]{"Simulator Name", "Qty", "Note"}, true);
	}

	/**
	 * Modify Consumables table in Equipment Tab
	 * 
	 * NOTE: if validateFieldErrors is true - required field error messages will
	 * be validated
	 */
	private void modifyConsumablesInEquipmentTab() {
		AutomationHelper.printMethodName();

		String cdmGroupID = getDMID("consumablesGroupID");

		ExcelDataConfig cdm = getExcelFile("JMEDSimulationDataMap.xlsx",
				"EquipConsumablesDataMap");

		EquipmentTab et = es.getEquipmentTab();

		et.setConsumablesNoneCheckbox(false);

		for (int i = 1; i <= cdm.getRowCount(); i++) {
			String groupID = cdm.getData(i, "GroupTestDataID");
			String changeType = cdm.getData(i, "ChangeType");

			if (groupID.equals(cdmGroupID)) {
				ModifyType modType = getModifyType(changeType);

				String editLine = modType == ModifyType.EDIT ? pre : "";
				int itemSize = Integer.parseInt(cdm.getData(i, "ItemSize"));
				String item = getRandomText(itemSize, itemSize);

				String eQty = cdm.getData(i, editLine + "Qty");

				int noteSize = Integer.parseInt(cdm.getData(i, "NoteSize"));
				String note = getRandomText(noteSize, noteSize);

				switch (modType) {
					case ADD :
						EquipmentModal cem = et.clickAddConsumables();
						if (isValidateFieldErrors) {
							cem.clickAdd();

							validateStaticContent("MMSP_ES_Equip_ItemError",
									cem.readItemErrorMessage(),
									"Equipment modal", "Item error");

							validateStaticContent("MMSP_ES_Equip_QtyError",
									cem.readQtyErrorMessage(),
									"Equipment modal", "Qty error");

							validateStaticContent("MMSP_ES_Equip_NoteError",
									cem.readNoteErrorMessage(),
									"Equipment modal", "Note error");
						}

						cdm.writeToWorkSheet(i, "Item", item);
						cem.setItem(item);
						cem.setQty(eQty);
						cdm.writeToWorkSheet(i, "Note", note);
						cem.setNote(note);

						cem.clickAdd();

						break;

					case EDIT :
						EquipmentModal eem = et
								.clickEditInConsumablesTableByItem(
										cdm.getData(i, "Item"));

						cdm.writeToWorkSheet(i, pre + "Item", item);
						eem.setItem(item);
						eem.setQty(eQty);
						cdm.writeToWorkSheet(i, pre + "Note", note);
						eem.setNote(note);

						eem.clickSave();

						break;

					case DELETE :
						et.clickDeleteInConsumablesTableByItem(
								cdm.getData(i, "Item"));

						et.clickOKOnAlert();

						break;

					default :
						break;
				}
			} // end if group equals expected loop
		} // end for

		validateTableColumns(cdm, cdmGroupID,
				es.getEquipmentTab().getConsumablesTable(), "Item",
				new String[]{"Item", "Qty", "Note"}, true);
	}

	/**
	 * Modify Non-Consumables table in Equipment Tab
	 * 
	 * NOTE: if validateFieldErrors is true - required field error messages will
	 * be validated
	 */
	private void modifyNonConsumablesInEquipmentTab() {
		AutomationHelper.printMethodName();

		String ncdmGroupID = getDMID("nonConsumablesGroupID");

		ExcelDataConfig ncdm = getExcelFile("JMEDSimulationDataMap.xlsx",
				"EquipNonConsumablesDataMap");

		EquipmentTab et = es.getEquipmentTab();

		et.setNonConsumablesNoneCheckbox(false);

		for (int i = 1; i <= ncdm.getRowCount(); i++) {
			String groupID = ncdm.getData(i, "GroupTestDataID");
			String changeType = ncdm.getData(i, "ChangeType");

			if (groupID.equals(ncdmGroupID)) {
				ModifyType modType = getModifyType(changeType);

				String editLine = modType == ModifyType.EDIT ? pre : "";
				int itemSize = Integer.parseInt(ncdm.getData(i, "ItemSize"));
				String item = getRandomText(itemSize, itemSize);

				String eQty = ncdm.getData(i, editLine + "Qty");

				int noteSize = Integer.parseInt(ncdm.getData(i, "NoteSize"));
				String note = getRandomText(noteSize, noteSize);

				switch (modType) {
					case ADD :
						EquipmentModal cem = et.clickAddNonConsumables();
						if (isValidateFieldErrors) {
							cem.clickAdd();

							validateStaticContent("MMSP_ES_Equip_ItemError",
									cem.readItemErrorMessage(),
									"Equipment modal", "Item error");

							validateStaticContent("MMSP_ES_Equip_QtyError",
									cem.readQtyErrorMessage(),
									"Equipment modal", "Qty error");

							validateStaticContent("MMSP_ES_Equip_NoteError",
									cem.readNoteErrorMessage(),
									"Equipment modal", "Note error");
						}

						ncdm.writeToWorkSheet(i, "Item", item);
						cem.setItem(item);
						cem.setQty(eQty);
						ncdm.writeToWorkSheet(i, "Note", note);
						cem.setNote(note);

						cem.clickAdd();

						break;

					case EDIT :
						EquipmentModal eem = et
								.clickEditInNonConsumablesTableByItem(
										ncdm.getData(i, "Item"));

						ncdm.writeToWorkSheet(i, pre + "Item", item);
						eem.setItem(item);
						eem.setQty(eQty);
						ncdm.writeToWorkSheet(i, pre + "Note", note);
						eem.setNote(note);

						eem.clickSave();

						break;

					case DELETE :
						et.clickDeleteInNonConsumablesTableByItem(
								ncdm.getData(i, "Item"));

						et.clickOKOnAlert();

						break;

					default :
						break;
				}
			} // end if group equals expected loop
		} // end for

		validateTableColumns(ncdm, ncdmGroupID,
				es.getEquipmentTab().getNonConsumablesTable(), "Item",
				new String[]{"Item", "Qty", "Note"}, true);
	}

	/**
	 * Modify AV/IT/Communications table in Equipment Tab
	 * 
	 * NOTE: if validateFieldErrors is true - required field error messages will
	 * be validated
	 */
	private void modifyAVITCommunicationsInEquipmentTab() {
		AutomationHelper.printMethodName();

		String avitcdmGroupID = getDMID("avITCommunicationsGroupID");

		ExcelDataConfig avitcdm = getExcelFile("JMEDSimulationDataMap.xlsx",
				"EquipAVITCommDataMap");

		EquipmentTab et = es.getEquipmentTab();

		et.setAVITCommunicationsNoneCheckbox(false);

		for (int i = 1; i <= avitcdm.getRowCount(); i++) {
			String groupID = avitcdm.getData(i, "GroupTestDataID");
			String changeType = avitcdm.getData(i, "ChangeType");

			if (groupID.equals(avitcdmGroupID)) {
				ModifyType modType = getModifyType(changeType);

				String editLine = modType == ModifyType.EDIT ? pre : "";
				int itemSize = Integer.parseInt(avitcdm.getData(i, "ItemSize"));
				String item = getRandomText(itemSize, itemSize);

				String eQty = avitcdm.getData(i, editLine + "Qty");

				int noteSize = Integer.parseInt(avitcdm.getData(i, "NoteSize"));
				String note = getRandomText(noteSize, noteSize);

				switch (modType) {
					case ADD :
						EquipmentModal cem = et.clickAddAVITCommunications();
						if (isValidateFieldErrors) {
							cem.clickAdd();

							validateStaticContent("MMSP_ES_Equip_ItemError",
									cem.readItemErrorMessage(),
									"Equipment modal", "Item error");

							validateStaticContent("MMSP_ES_Equip_QtyError",
									cem.readQtyErrorMessage(),
									"Equipment modal", "Qty error");

							validateStaticContent("MMSP_ES_Equip_NoteError",
									cem.readNoteErrorMessage(),
									"Equipment modal", "Note error");
						}

						avitcdm.writeToWorkSheet(i, "Item", item);
						cem.setItem(item);
						cem.setQty(eQty);
						avitcdm.writeToWorkSheet(i, "Note", note);
						cem.setNote(note);

						cem.clickAdd();

						break;

					case EDIT :
						EquipmentModal eem = et
								.clickEditInAVITCommunicationsTableByItem(
										avitcdm.getData(i, "Item"));

						avitcdm.writeToWorkSheet(i, pre + "Item", item);
						eem.setItem(item);
						eem.setQty(eQty);
						avitcdm.writeToWorkSheet(i, pre + "Note", note);
						eem.setNote(note);

						eem.clickSave();

						break;

					case DELETE :
						et.clickDeleteInAVITCommunicationsTableByItem(
								avitcdm.getData(i, "Item"));

						et.clickOKOnAlert();

						break;

					default :
						break;
				}
			} // end if group equals expected loop
		} // end for

		validateTableColumns(avitcdm, avitcdmGroupID,
				es.getEquipmentTab().getAVITCommunicationsTable(), "Item",
				new String[]{"Item", "Qty", "Note"}, true);
	}

	/**
	 * Modify Medical Record(s)/ Patient Documentation Tab
	 * 
	 * NOTE: if validateFieldErrors is true - required field error messages will
	 * be validated
	 */
	private void modifyMedicalRecordsPatientDocumentationTab() {
		AutomationHelper.printMethodName();

		String mrpddmGroupID = getDMID("medRecordsPatientDocDataMapGroupID");

		ExcelDataConfig mrpddm = getExcelFile("JMEDSimulationDataMap.xlsx",
				"MedRecordsPatientDocDataMap");

		MedicalRecordsPatientDocumentationTab mrpdt = es
				.getMedicalRecordsPatientDocumentationTab();

		if (isInitialCreation) {
			MedicalRecordsPatientDocumentationTab.TermsAndConditionsModal tacm = mrpdt
					.clickTermsAndConditions();
			validateStaticContent("MMSP_ES_MedRec_TermsAndConditions",
					tacm.readTermsAndConditions(),
					"Medical Records Patient Documentation Terms and Conditions modal",
					"Terms and Conditions text");

			tacm.clickClose();
		}

		for (int i = 1; i <= mrpddm.getRowCount(); i++) {
			String groupID = mrpddm.getData(i, "GroupTestDataID");
			String changeType = mrpddm.getData(i, "ChangeType");

			if (groupID.equals(mrpddmGroupID)) {
				ModifyType modType = getModifyType(changeType);

				int fileNameSize = Integer
						.parseInt(mrpddm.getData(i, "FileNameSize"));
				String fileName = getRandomText(fileNameSize, fileNameSize);

				String eFile = mrpddm.getData(i, "File"); // not editable

				int descriptionSize = Integer
						.parseInt(mrpddm.getData(i, "DescriptionSize"));
				String description = getRandomText(descriptionSize,
						descriptionSize);

				switch (modType) {
					case ADD :
						MedicalRecordsPatientDocumentationModal cmrpdm = mrpdt
								.clickAddMedicalRecordsPatientDocumentation();
						if (isValidateFieldErrors) {
							cmrpdm.clickAdd();

							validateStaticContent(
									"MMSP_ES_MedRec_FileNameError",
									cmrpdm.readFileNameErrorMessage(),
									"Medical Records and Patient Documentation modal",
									"File Name error");

							validateStaticContent(
									"MMSP_ES_MedRec_SelectFileError",
									cmrpdm.readSelectFileErrorMessage(),
									"Medical Records and Patient Documentation modal",
									"Select File error");

							validateStaticContent(
									"MMSP_ES_MedRec_FileDescError",
									cmrpdm.readFileDescriptionErrorMessage(),
									"Medical Records and Patient Documentation modal",
									"File Description error");
						}

						mrpddm.writeToWorkSheet(i, "File Name", fileName);

						cmrpdm.setFileName(fileName);

						cmrpdm.selectFile(generateFullFileNameAndPath(
								"testFiles\\" + eFile));

						mrpddm.writeToWorkSheet(i, "Description", description);

						cmrpdm.setFileDescription(description);
						cmrpdm.clickAdd();

						break;

					case EDIT :
						MedicalRecordsPatientDocumentationModal emrpdm = mrpdt
								.clickEditOnMedicalRecordsPatientDocTableByFileName(
										mrpddm.getData(i, "File Name"));

						mrpddm.writeToWorkSheet(i, pre + "File Name",
								description);

						emrpdm.setFileDescription(description);
						emrpdm.clickSave();

						break;

					case DELETE :
						mrpdt.clickDeleteInMedicalRecordsPatientDocumentationTableByFileName(
								mrpddm.getData(i, "File Name"));

						mrpdt.clickOKOnAlert();

						break;

					default :
						break;
				}
			} // end if group equals expected loop
		} // end for

		validateTableColumns(mrpddm, mrpddmGroupID,
				es.getMedicalRecordsPatientDocumentationTab()
						.getMedicalRecordsPatientDocumentationTable(),
				"File Name", new String[]{"File Name", "Description"}, true);
	}

	/**
	 * Modify Simulation Environment Tab
	 * 
	 * NOTE: if validateFieldErrors is true - required field error messages will
	 * be validated
	 */
	private void modifySimulationEnvironmentTab() {
		AutomationHelper.printMethodName();

		String sedmID = getDMID("simEnvironmentDataMapID");

		ExcelDataConfig sedm = getExcelFile("JMEDSimulationDataMap.xlsx",
				"SimEnvironmentDataMap");

		setDMID("simEnvironmentFurnPropDataMapGroupID",
				sedm.getData(sedmID, "SimEnvironmentFurnPropDataMapGroupID"));

		SimulationEnvironmentTab set = es.getSimulationEnvironmentTab();

		if (isValidateFieldErrors) {
			set.setSpaceRequirementLocation("");
			set.setClinicalOperationalEnvironmentType("");
			set.clickSave();

			validateStaticContent("MMSP_ES_SimEnv_SpaceReqLocError",
					set.readSpaceRequirementLocationErrorMessage(),
					"Simulation Environment tab",
					"Space Requirement/Location error");

			validateStaticContent("MMSP_ES_SimEnv_ClinicalOpEnvTypeError",
					set.readClinicalOperationalEnvironmentTypeErrorMessage(),
					"Simulation Environment tab",
					"Clinical/Operational Environment Type error");
		}

		int spaceReqLocSize = Integer
				.parseInt(sedm.getData(sedmID, "SpaceSize"));

		String spaceReq = getRandomText(spaceReqLocSize, spaceReqLocSize);

		// write generated description to excel
		sedm.writeToWorkSheet(sedmID, pre + "Space Requirement/Location",
				spaceReq);

		// write generated description to Space Requirement/Location field
		set.setSpaceRequirementLocation(spaceReq);

		int clinicalOperationalTypeSize = Integer
				.parseInt(sedm.getData(sedmID, "ClinicalSize"));

		String clincalOperationalType = getRandomText(
				clinicalOperationalTypeSize, clinicalOperationalTypeSize);

		// write generated description to excel
		sedm.writeToWorkSheet(sedmID,
				pre + "Clinical/Operational Environment Type",
				clincalOperationalType);

		// write generated description to Space Requirement/Location field
		set.setClinicalOperationalEnvironmentType(clincalOperationalType);

		set.clickSave();

		modifyFurniturePropInSimulationEnvironmentTab();

	}

	/**
	 * Modify Furniture / Prop table in Simulation Environment Tab
	 * 
	 * NOTE: if validateFieldErrors is true - required field error messages will
	 * be validated
	 */
	private void modifyFurniturePropInSimulationEnvironmentTab() {
		AutomationHelper.printMethodName();

		String sedmGroupID = getDMID("simEnvironmentFurnPropDataMapGroupID");

		ExcelDataConfig sedm = getExcelFile("JMEDSimulationDataMap.xlsx",
				"SimEnvironmentFurnPropDataMap");

		for (int i = 1; i <= sedm.getRowCount(); i++) {
			String groupID = sedm.getData(i, "GroupTestDataID");
			String changeType = sedm.getData(i, "ChangeType");

			if (groupID.equals(sedmGroupID)) {
				ModifyType modType = getModifyType(changeType);

				int itemSize = Integer.parseInt(sedm.getData(i, "ItemSize"));
				String item = getRandomText(itemSize, itemSize);

				String qty = sedm.getData(i, "Qty");

				int noteUseSize = Integer
						.parseInt(sedm.getData(i, "NoteUseSize"));
				String noteUse = getRandomText(noteUseSize, noteUseSize);

				SimulationEnvironmentTab set = es.getSimulationEnvironmentTab();

				switch (modType) {
					case ADD :
						EnvironmentFurniturePropModal cefpm = set
								.clickAddFurnitureProp();
						if (isValidateFieldErrors) {
							cefpm.clickAdd();

							validateStaticContent(
									"MMSP_ES_SE_EnvFurnProp_ItemError",
									cefpm.readItemErrorMessage(),
									"Simulation Environment Furniture/Prop modal",
									"Item error");

							validateStaticContent(
									"MMSP_ES_SE_EnvFurnProp_QtyError",
									cefpm.readQtyErrorMessage(),
									"Simulation Environment Furniture/Prop modal",
									"Qty error");

							validateStaticContent(
									"MMSP_ES_SE_EnvFurnProp_NotesUseError",
									cefpm.readNotesUseErrorMessage(),
									"Simulation Environment Furniture/Prop modal",
									"Note/Use error");
						}

						sedm.writeToWorkSheet(i, "Item", item);
						cefpm.setItem(item);

						cefpm.setQty(qty);

						sedm.writeToWorkSheet(i, "Notes/Use", noteUse);
						cefpm.setNotesUse(noteUse);

						cefpm.clickAdd();

						break;

					case EDIT :
						EnvironmentFurniturePropModal eefpm = set
								.clickEditInFurniturePropTableByItem(
										sedm.getData(i, "Item"));

						// write generated description to excel
						sedm.writeToWorkSheet(i, pre + "Item", item);
						eefpm.setItem(item);

						eefpm.setQty(qty);

						sedm.writeToWorkSheet(i, pre + "Notes/Use", noteUse);
						eefpm.setNotesUse(noteUse);

						eefpm.clickSave();

						break;

					case DELETE :
						set.clickDeleteInFurniturePropTableByItem(
								sedm.getData(i, "Item"));

						set.clickOKOnAlert();

						break;

					default :
						break;
				}
			} // end if group equals expected loop
		} // end for

		validateTableColumns(sedm, sedmGroupID,
				es.getSimulationEnvironmentTab().getFurniturePropTable(),
				"Item", new String[]{"Item", "Qty", "Notes/Use"}, true);

	}

	/**
	 * Modify Environment & Equipment Setup Tab
	 * 
	 * NOTE: if validateFieldErrors is true - required field error messages will
	 * be validated
	 */
	private void modifyEnvironmentAndEquipmentSetupTab() {
		AutomationHelper.printMethodName();

		String eesdmGroupID = getDMID("envEquipSetupDataMapGroupID");

		ExcelDataConfig eesdm = getExcelFile("JMEDSimulationDataMap.xlsx",
				"EnvEquipSetupDataMap");

		EnvironmentAndEquipmentSetupTab eest = es
				.getEnvironmentAndEquipmentSetupTab();

		if (isInitialCreation) {
			EnvironmentAndEquipmentSetupTab.TermsAndConditionsModal tacm = eest
					.clickTermsAndConditions();
			validateStaticContent("MMSP_ES_EnvEquip_TermsAndConditions",
					tacm.readTermsAndConditions(),
					"Environment and Equipment Setup Terms and Conditions modal",
					"Terms and Conditions text");

			tacm.clickClose();
		}

		for (int i = 1; i <= eesdm.getRowCount(); i++) {
			String groupID = eesdm.getData(i, "GroupTestDataID");
			String changeType = eesdm.getData(i, "ChangeType");

			if (groupID.equals(eesdmGroupID)) {
				ModifyType modType = getModifyType(changeType);

				int nameSize = Integer.parseInt(eesdm.getData(i, "NameSize"));
				String name = getRandomText(nameSize, nameSize);

				String eFile = eesdm.getData(i, "File"); // not editable

				int descriptionSize = Integer
						.parseInt(eesdm.getData(i, "DescriptionSize"));
				String description = getRandomText(descriptionSize,
						descriptionSize);

				switch (modType) {
					case ADD :
						EnvironmentAndEquipmentSetupModal ceesm = eest
								.clickAddEnvironmentAndEquipmentSetup();
						if (isValidateFieldErrors) {
							ceesm.clickAdd();

							validateStaticContent("MMSP_ES_EnvEquip_NameError",
									ceesm.readNameErrorMessage(),
									"Environment & Equipment Setup modal",
									"Name error");

							validateStaticContent(
									"MMSP_ES_EnvEquip_SelectFileError",
									ceesm.readSelectFileErrorMessage(),
									"Environment & Equipment Setup modal",
									"Select File error");

							validateStaticContent("MMSP_ES_EnvEquip_DescError",
									ceesm.readDescriptionErrorMessage(),
									"Environment & Equipment Setup modal",
									"Description error");
						}

						eesdm.writeToWorkSheet(i, "Name", name);

						ceesm.setName(name);

						ceesm.selectFile(generateFullFileNameAndPath(
								"testFiles\\" + eFile));

						eesdm.writeToWorkSheet(i, "Description", description);

						ceesm.setDescription(description);
						ceesm.clickAdd();

						break;

					case EDIT :
						EnvironmentAndEquipmentSetupModal eeesm = eest
								.clickEditOnEnvironmentAndEquipmentSetupByName(
										eesdm.getData(i, "Name"));

						eesdm.writeToWorkSheet(i, pre + "Name", name);

						eeesm.setName(name);

						eesdm.writeToWorkSheet(i, pre + "Description",
								description);

						eeesm.setDescription(description);
						eeesm.clickSave();

						break;

					case DELETE :
						eest.clickDeleteInEnvironmentAndEquipmentSetupTableByFileDescription(
								eesdm.getData(i, "Name"));

						eest.clickOKOnAlert();

						break;

					default :
						break;
				}
			} // end if group equals expected loop
		} // end for

		validateTableColumns(eesdm, eesdmGroupID,
				es.getEnvironmentAndEquipmentSetupTab()
						.getEnvironmentAndEquipmentSetupTable(),
				"Name", new String[]{"Name", "Description"}, true);
	}

	/**
	 * Modify Standardized Patient Script Tab
	 * 
	 * NOTE: if validateFieldErrors is true - required field error messages will
	 * be validated
	 */
	private void modifyStandardizedPatientScriptTab() {
		AutomationHelper.printMethodName();

		String spsdmGroupID = getDMID("standardPatientScriptsDataMapGroupID");

		ExcelDataConfig spsdm = getExcelFile("JMEDSimulationDataMap.xlsx",
				"StandardPatientScriptsDataMap");

		StandardizedPatientScriptsTab spst = es
				.getStandardizedPatientScriptsTab();

		if (isInitialCreation) {
			spst.setScriptsNoneCheckbox(false);
			spst.setSPCaseCheckbox(true);
		}

		for (int i = 1; i <= spsdm.getRowCount(); i++) {
			String groupID = spsdm.getData(i, "GroupTestDataID");
			String changeType = spsdm.getData(i, "ChangeType");

			if (groupID.equals(spsdmGroupID)) {
				ModifyType modType = getModifyType(changeType);

				int scriptNameSize = Integer
						.parseInt(spsdm.getData(i, "ScriptNameSize"));
				String scriptName = getRandomText(scriptNameSize,
						scriptNameSize);

				String eFile = spsdm.getData(i, "File"); // not editable

				int descriptionSize = Integer
						.parseInt(spsdm.getData(i, "DescriptionSize"));
				String description = getRandomText(descriptionSize,
						descriptionSize);

				switch (modType) {
					case ADD :
						StandardizedPatientScriptModal cspsm = spst
								.clickAddStandardizedPatientScript();
						if (isValidateFieldErrors) {
							cspsm.clickAdd();

							validateStaticContent(
									"MMSP_ES_StandPatScript_ScriptNameError",
									cspsm.readScriptNameErrorMessage(),
									"Standardized Patient Script modal",
									"Script Name error");

							validateStaticContent(
									"MMSP_ES_StandPatScript_SelectFileError",
									cspsm.readSelectFileErrorMessage(),
									"Standardized Patient Script modal",
									"Select File error");

							validateStaticContent(
									"MMSP_ES_StandPatScript_DescError",
									cspsm.readDescriptionErrorMessage(),
									"Standardized Patient Script modal",
									"Description error");
						}

						spsdm.writeToWorkSheet(i, "Script Name", scriptName);

						cspsm.setScriptName(scriptName);

						cspsm.selectFile(generateFullFileNameAndPath(
								"testFiles\\" + eFile));

						spsdm.writeToWorkSheet(i, "Description", description);

						cspsm.setDescription(description);
						cspsm.clickAdd();

						break;

					case EDIT :
						StandardizedPatientScriptModal espsm = spst
								.clickEditOnStandardizedPatientScriptsByScriptName(
										spsdm.getData(i, "Script Name"));

						spsdm.writeToWorkSheet(i, pre + "Script Name",
								scriptName);

						espsm.setScriptName(scriptName);

						spsdm.writeToWorkSheet(i, pre + "Description",
								description);

						espsm.setDescription(description);
						espsm.clickSave();

						break;

					case DELETE :
						spst.clickDeleteInStandardizedPatientScriptsTableByName(
								spsdm.getData(i, "Script Name"));

						spst.clickOKOnAlert();

						break;

					default :
						break;
				}
			} // end if group equals expected loop
		} // end for

		validateTableColumns(spsdm, spsdmGroupID,
				es.getStandardizedPatientScriptsTab()
						.getStandardizedPatientScriptsTable(),
				"Script Name", new String[]{"Script Name", "Description"},
				true);
	}
}
