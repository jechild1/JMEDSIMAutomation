package modularScripts.MyMenuSimulation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import configuration.JMEDConfig;
import pageFactories.MyMenuPages.CreateNewSimulationPageFactory;
import pageFactories.MyMenuPages.SimulationsPageFactory;
import utilities.AutomationHelper;
import utilities.ExcelDataConfig;

/**
 * Module Test Script
 * 
 * @author scott.brazelton
 *
 */
public class CreateNewSimulationMod extends JMEDConfig {

	private boolean validateFieldErrors;
	private String userName;

	/**
	 * This test script module creates a new Simulation based on dynamic data
	 * <br>
	 * Pre-Condition: On My Menu > Simulation Page <br>
	 * Post-Condition: On My Menu > Simulation > Edit Page
	 * 
	 * @param simulationDataMapID
	 *            - TestDataID from JMEDSimulationDataMap.xlsx
	 * @param validateFieldErrors
	 *            - true = validate all required field errors | false = skip
	 *            required field errors
	 */
	public void execute(String userName, String simulationDataMapID,
			boolean validateFieldErrors) {
		AutomationHelper.printClassName();
		AutomationHelper.printMethodName();

		this.userName = userName;
		this.validateFieldErrors = validateFieldErrors;

		ExcelDataConfig mdm = getExcelFile("JMEDSimulationDataMap.xlsx",
				"MainDataMap");

		new SimulationsPageFactory().clickCreateNewSimulation();

		validateStaticTitle("MMSP_CNS_Title",
				new CreateNewSimulationPageFactory().readPageTitle(),
				"Create New Simulation Page", "Page title");

		// ADMINISTRATIVE DETAILS
		addAdministrativeDetails(
				mdm.getData(simulationDataMapID, "AdminDetailsDataMapID"));

	}

	/**
	 * Add Administrative Details for new simulation & create
	 * 
	 * NOTE: if validateFieldErrors is true - required field error messages will
	 * be validated
	 * 
	 * @param adminDetailsDataMapID
	 */
	private void addAdministrativeDetails(String adminDetailsDataMapID) {
		AutomationHelper.printMethodName();

		ExcelDataConfig addm = getExcelFile("JMEDSimulationDataMap.xlsx",
				"AdminDetailsDataMap");

		CreateNewSimulationPageFactory cns = new CreateNewSimulationPageFactory();
		if (this.validateFieldErrors) {
			cns.clickCreate();

			validateStaticContent("MMSP_CNS_TitleError",
					cns.readTitleErrorMessage(), "Admin Details tab",
					"Title error");

			validateStaticContent("MMSP_CNS_DurationError",
					cns.readDurationErrorMessage(), "Admin Details tab",
					"Duration error");

			validateStaticContent("MMSP_CNS_AuthorError",
					cns.readAuthorErrorMessage(), "Admin Details tab",
					"Author error");

			validateStaticContent("MMSP_CNS_SiteError",
					cns.readSiteErrorMessage(), "Admin Details tab",
					"Site error");

			validateStaticContent("MMSP_CNS_ContentKeywordsError",
					cns.readContentKeywordsErrorMessage(), "Admin Details tab",
					"Content Keywords error");

			validateStaticContent("MMSP_CNS_TrainingTypeError",
					cns.readTrainingTypeErrorMessage(), "Admin Details tab",
					"Training Type error");

		}

		cns.setTitle(addm.getData(adminDetailsDataMapID, "Title"));
		cns.setDurationHours(addm.getData(adminDetailsDataMapID, "DurationHours"));
		cns.setDurationMinutes(addm.getData(adminDetailsDataMapID, "DurationMinutes"));
		cns.selectAuthor(addm.getData(adminDetailsDataMapID, "Author"));
		cns.selectSite(addm.getData(adminDetailsDataMapID, "Site"));
		cns.setContentKeywords(
				addm.getData(adminDetailsDataMapID, "ContentKeywords"));
		cns.selectTrainingType(
				addm.getData(adminDetailsDataMapID, "TrainingType"));
		cns.setActiveCheckbox(
				addm.getData(adminDetailsDataMapID, "Active").equals("")
						? false
						: true);

		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();

		addm.writeToWorkSheet(adminDetailsDataMapID, "CreatedBy",
				this.userName);
		addm.writeToWorkSheet(adminDetailsDataMapID, "CreatedOn",
				df.format(date));
		addm.writeToWorkSheet(adminDetailsDataMapID, "UpdatedBy",
				this.userName);
		addm.writeToWorkSheet(adminDetailsDataMapID, "UpdatedOn",
				df.format(date));

		cns.clickCreate();
	}
}
