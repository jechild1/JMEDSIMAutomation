package modularScripts.MyMenuSimulation;

import pageFactories.MyMenuPages.DeleteSimulationPageFactory;
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
public class DeleteSimulationMod extends SimulationDetailValidationsBase {

	/**
	 * This test script module deletes a specified simulation <br>
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

		// prefix to use edit column if editing
		this.pre = isOriginal ? "" : "Edit";

		ExcelDataConfig mdm = getExcelFile("JMEDSimulationDataMap.xlsx",
				"MainDataMap");

		setDMID("adminDetailsDataMapID",
				mdm.getData(simulationDataMapID, "AdminDetailsDataMapID"));

		this.addm = getExcelFile("JMEDSimulationDataMap.xlsx",
				"AdminDetailsDataMap");

		ExcelDataConfig stv = getExcelFile("JMEDContentMatrix.xlsx",
				"StaticTextValidations");

		SimulationsPageFactory spf = new SimulationsPageFactory();

		spf.gotoTablePageWithRow(spf.getSimulationsTable(), "Title",
				addm.getData(getDMID("adminDetailsDataMapID"), pre + "Title"));

		spf.getSimulationsTable().clickLinkInRow("Title",
				addm.getData(getDMID("adminDetailsDataMapID"), pre + "Title"),
				"", "Delete", false);

		DeleteSimulationPageFactory ds = new DeleteSimulationPageFactory();

		softAsserter.assertEquals(ds.readPageTitle(),
				stv.getData("MMSP_DS_Title", "Title"),
				"Validating page title when deleting a simulation");

		validateAdministrativeDetails(ds);

		softAsserter.assertEquals(ds.readDurationHours(),
				addm.getData(getDMID("adminDetailsDataMapID"),
						pre + "DurationHours"),
				"Validating 'Duration (Hours)' in Administrative Details");

		softAsserter.assertEquals(ds.readDurationMinutes(),
				addm.getData(getDMID("adminDetailsDataMapID"),
						pre + "DurationMinutes"),
				"Validating 'Duration (Minutes)' in Administrative Details");

		ds.clickDelete();

	}
}
