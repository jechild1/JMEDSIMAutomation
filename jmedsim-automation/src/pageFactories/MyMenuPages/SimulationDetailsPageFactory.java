package pageFactories.MyMenuPages;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import pageFactories.JMEDSimTables;
import utilities.AutomationHelper;

/**
 * Page factory for the My Menu > Simulations > Simulation Details page for
 * JMEDSIM.<br>
 * (1) Finds a reference to objects on the page<br>
 * (2) Defines methods to interact with objects on the page<br>
 * 
 * Extends ModifySimulationsBase
 * 
 * @author jesse.childress
 *
 */
public class SimulationDetailsPageFactory extends SimulationDetailsBase {

	private static String regexURL = BASE_URL + "Simulations/Details.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public SimulationDetailsPageFactory() {
		super(regexURL);
	}

	// Hashmap to store tabs
	private HashMap<String, Object> tabs = new HashMap<String, Object>();

	/**
	 * Sets a reference to a tab in the tabs hashmap
	 * 
	 * @param hashmapKey
	 * @param widget
	 */
	private void setSimulationTab(String hashmapKey, Object widget) {
		tabs.put(hashmapKey, widget);
	}

	/**
	 * Returns a generic object reference of a tab based on key supplied
	 * 
	 * @param hashmapKey
	 * @return Object
	 */
	private Object getSimulationTab(String hashmapKey) {
		return tabs.get(hashmapKey);
	}

	/**
	 * Clicks a tab element based on whether user wants to expand or collapse
	 * the tab and then waits for tab to fully open or close
	 * 
	 * @param tabElement
	 * @param action
	 *            - true = expand | false = collapse
	 */
	private void clickTab(WebElement tabElement, boolean action) {
		String className = tabElement
				.findElement(By.xpath(".//../../following-sibling::*"))
				.getAttribute("class");

		AutomationHelper.scrollIntoView(tabElement);

		if ((className.equals("panel-collapse in")) && !action) {
			tabElement.click();

			new WebDriverWait(driver, 15).until(ExpectedConditions
					.attributeToBe(tabElement, "class", "collapsed"));

		} else if (className.equals("panel-collapse collapse") && action) {
			try {
				tabElement.click();
			} catch (ElementClickInterceptedException e) {
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].click()", tabElement);
				Reporter.log(
						"Method threw ElementClickInterceptionException. Error caught and link clicked",
						true);

			}

			new WebDriverWait(driver, 15).until(
					ExpectedConditions.attributeToBe(tabElement, "class", ""));
		}
	}

	@FindBy(linkText = "Administrative Details")
	WebElement administrativeDetailsTab;

	/**
	 * Clicks the Administrative Details Tab
	 * 
	 * @param action
	 *            - true = expand | false = collapse
	 */
	private void clickAdministrativeDetailsTab(boolean action) {
		AutomationHelper.printMethodName();
		clickTab(administrativeDetailsTab, action);
	}

	/**
	 * Returns a reference to the Administrative Details tab within Simulation
	 * Details
	 * 
	 * @return AdministrativeDetailsTab
	 */
	public AdministrativeDetailsTab getAdministrativeDetailsTab() {

		AdministrativeDetailsTab simulationTab = (AdministrativeDetailsTab) getSimulationTab(
				"adminDetailsTab");

		if (simulationTab == null) {
			simulationTab = new AdministrativeDetailsTab();
			setSimulationTab("adminDetailsTab", simulationTab);
		}

		// Ensures that the tab is clicked
		clickAdministrativeDetailsTab(true);
		return simulationTab;
	}

	@FindBy(linkText = "Simulation Description")
	WebElement simulationDescriptionTab;

	/**
	 * Clicks the Simulation Description Tab
	 * 
	 * @param action
	 *            - true = expand | false = collapse
	 */
	private void clickSimulationDescriptionTab(boolean action) {
		AutomationHelper.printMethodName();
		clickTab(simulationDescriptionTab, action);
	}

	/**
	 * Returns a reference to the Simulation Description tab within Simulation
	 * Details
	 * 
	 * @return SimulationDescriptionTab
	 */
	public SimulationDescriptionTab getSimulationDescriptionTab() {

		SimulationDescriptionTab simulationTab = (SimulationDescriptionTab) getSimulationTab(
				"simulationDescTab");

		if (simulationTab == null) {
			simulationTab = new SimulationDescriptionTab();
			setSimulationTab("simulationDescTab", simulationTab);
		}

		// Ensures that the tab is clicked
		clickSimulationDescriptionTab(true);
		return simulationTab;
	}

	@FindBy(linkText = "Learners")
	WebElement learnersTab;

	/**
	 * Clicks the Learners Tab
	 * 
	 * @param action
	 *            - true = expand | false = collapse
	 */
	private void clickLearnersTab(boolean action) {
		AutomationHelper.printMethodName();
		clickTab(learnersTab, action);
	}

	/**
	 * Returns a reference to the Learners tab within Simulation Details
	 * 
	 * @return LearnersTab
	 */
	public LearnersTab getLearnersTab() {

		LearnersTab simulationTab = (LearnersTab) getSimulationTab(
				"learnersTab");

		if (simulationTab == null) {
			simulationTab = new LearnersTab();
			setSimulationTab("learnersTab", simulationTab);
		}

		// Ensures that the tab is clicked
		clickLearnersTab(true);
		return simulationTab;
	}

	@FindBy(linkText = "Simulation Objective / Outcome / Performance Indicators (Required)")
	WebElement simulationOOPITab;

	/**
	 * Clicks the Simulation OOPI Tab
	 * 
	 * @param action
	 *            - true = expand | false = collapse
	 */
	private void clickSimulationOOPITab(boolean action) {
		AutomationHelper.printMethodName();
		clickTab(simulationOOPITab, action);
	}

	/**
	 * Returns a reference to the Simulation OOPI tab within Simulation Details
	 * 
	 * @return SimulationOOPITab
	 */
	public SimulationOOPITab getSimulationOOPITab() {

		SimulationOOPITab simulationTab = (SimulationOOPITab) getSimulationTab(
				"simulationOOPITab");

		if (simulationTab == null) {
			simulationTab = new SimulationOOPITab();
			setSimulationTab("simulationOOPITab", simulationTab);
		}

		// Ensures that the tab is clicked
		clickSimulationOOPITab(true);
		return simulationTab;
	}

	@FindBy(linkText = "Personnel (Required)")
	WebElement personnelTab;

	/**
	 * Clicks the Personnel Tab
	 * 
	 * @param action
	 *            - true = expand | false = collapse
	 */
	private void clickPersonnelTab(boolean action) {
		AutomationHelper.printMethodName();
		clickTab(personnelTab, action);
	}

	/**
	 * Returns a reference to the Personnel tab within Simulation Details
	 * 
	 * @return PersonnelTab
	 */
	public PersonnelTab getPersonnelTab() {

		PersonnelTab simulationTab = (PersonnelTab) getSimulationTab(
				"personnelTab");

		if (simulationTab == null) {
			simulationTab = new PersonnelTab();
			setSimulationTab("personnelTab", simulationTab);
		}

		// Ensures that the tab is clicked
		clickPersonnelTab(true);
		return simulationTab;
	}

	@FindBy(linkText = "Prerequisites")
	WebElement prerequisitesTab;

	/**
	 * Clicks the Prerequisites Tab
	 * 
	 * @param action
	 *            - true = expand | false = collapse
	 */
	private void clickPrerequisitesTab(boolean action) {
		AutomationHelper.printMethodName();
		clickTab(prerequisitesTab, action);
	}

	/**
	 * Returns a reference to the Prerequisites tab within Simulation Details
	 * 
	 * @return PrerequisitesTab
	 */
	public PrerequisitesTab getPrerequisitesTab() {

		PrerequisitesTab simulationTab = (PrerequisitesTab) getSimulationTab(
				"prerequisitesTab");

		if (simulationTab == null) {
			simulationTab = new PrerequisitesTab();
			setSimulationTab("prerequisitesTab", simulationTab);
		}

		// Ensures that the tab is clicked
		clickPrerequisitesTab(true);
		return simulationTab;
	}

	@FindBy(linkText = "Equipments (Required)")
	WebElement equipmentTab;

	/**
	 * Clicks the Equipment Tab
	 * 
	 * @param action
	 *            - true = expand | false = collapse
	 */
	private void clickEquipmentTab(boolean action) {
		AutomationHelper.printMethodName();
		clickTab(equipmentTab, action);
	}

	/**
	 * Returns a reference to the Equipment tab within Simulation Details
	 * 
	 * @return EquipmentsTab
	 */
	public EquipmentTab getEquipmentTab() {

		EquipmentTab simulationTab = (EquipmentTab) getSimulationTab(
				"equipmentTab");

		if (simulationTab == null) {
			simulationTab = new EquipmentTab();
			setSimulationTab("equipmentTab", simulationTab);
		}

		// Ensures that the tab is clicked
		clickEquipmentTab(true);
		return simulationTab;
	}

	@FindBy(linkText = "Medical Record(s)/ Patient Documentation")
	WebElement medRecPatientDocTab;

	/**
	 * Clicks the Medical Record(s)/ Patient Documentation Tab
	 * 
	 * @param action
	 *            - true = expand | false = collapse
	 */
	private void clickMedicalRecordsPatientDocumentationTab(boolean action) {
		AutomationHelper.printMethodName();
		clickTab(medRecPatientDocTab, action);
	}

	/**
	 * Returns a reference to the Medical Record(s)/ Patient Documentation tab
	 * within Simulation Details
	 * 
	 * @return MedicalRecordsPatientDocumentationTab
	 */
	public MedicalRecordsPatientDocumentationTab getMedicalRecordsPatientDocumentationTab() {

		MedicalRecordsPatientDocumentationTab simulationTab = (MedicalRecordsPatientDocumentationTab) getSimulationTab(
				"medRecPatientDocTab");

		if (simulationTab == null) {
			simulationTab = new MedicalRecordsPatientDocumentationTab();
			setSimulationTab("medRecPatientDocTab", simulationTab);
		}

		// Ensures that the tab is clicked
		clickMedicalRecordsPatientDocumentationTab(true);
		return simulationTab;
	}

	@FindBy(linkText = "Simulation Environment")
	WebElement simulationEnvironmentTab;

	/**
	 * Clicks the Simulation Environment Tab
	 * 
	 * @param action
	 *            - true = expand | false = collapse
	 */
	private void clickSimulationEnvironmentTab(boolean action) {
		AutomationHelper.printMethodName();
		clickTab(simulationEnvironmentTab, action);
	}

	/**
	 * Returns a reference to the Simulation Environment tab within Simulation
	 * Details
	 * 
	 * @return SimulationEnvironmentTab
	 */
	public SimulationEnvironmentTab getSimulationEnvironmentTab() {

		SimulationEnvironmentTab simulationTab = (SimulationEnvironmentTab) getSimulationTab(
				"simulationEnvironmentTab");

		if (simulationTab == null) {
			simulationTab = new SimulationEnvironmentTab();
			setSimulationTab("simulationEnvironmentTab", simulationTab);
		}

		// Ensures that the tab is clicked
		clickSimulationEnvironmentTab(true);
		return simulationTab;
	}

	@FindBy(linkText = "Environment and Equipment Setup")
	WebElement environmentAndEquipmentSetupTab;

	/**
	 * Clicks the Environment and Equipment Setup Tab
	 * 
	 * @param action
	 *            - true = expand | false = collapse
	 */
	private void clickEnvironmentAndEquipmentSetupTab(boolean action) {
		AutomationHelper.printMethodName();
		clickTab(environmentAndEquipmentSetupTab, action);
	}

	/**
	 * Returns a reference to the Environment and Equipment Setup tab within
	 * Simulation Details
	 * 
	 * @return EnvironmentAndEquipmentSetupTab
	 */
	public EnvironmentAndEquipmentSetupTab getEnvironmentAndEquipmentSetupTab() {

		EnvironmentAndEquipmentSetupTab simulationTab = (EnvironmentAndEquipmentSetupTab) getSimulationTab(
				"environmentAndEquipmentSetupTab");

		if (simulationTab == null) {
			simulationTab = new EnvironmentAndEquipmentSetupTab();
			setSimulationTab("environmentAndEquipmentSetupTab", simulationTab);
		}

		// Ensures that the tab is clicked
		clickEnvironmentAndEquipmentSetupTab(true);
		return simulationTab;
	}

	@FindBy(linkText = "Standardized Patient Script(s)")
	WebElement standardizedPatientScriptsTab;

	/**
	 * Clicks the Standardized Patient Script(s) Tab
	 * 
	 * @param action
	 *            - true = expand | false = collapse
	 */
	private void clickStandardizedPatientScriptsTab(boolean action) {
		AutomationHelper.printMethodName();
		clickTab(standardizedPatientScriptsTab, action);
	}

	/**
	 * Returns a reference to the Standardized Patient Script(s) tab within
	 * Simulation Details
	 * 
	 * @return StandardizedPatientScriptsTab
	 */
	public StandardizedPatientScriptsTab getStandardizedPatientScriptsTab() {

		StandardizedPatientScriptsTab simulationTab = (StandardizedPatientScriptsTab) getSimulationTab(
				"standardizedPatientScriptsTab");

		if (simulationTab == null) {
			simulationTab = new StandardizedPatientScriptsTab();
			setSimulationTab("standardizedPatientScriptsTab", simulationTab);
		}

		// Ensures that the tab is clicked
		clickStandardizedPatientScriptsTab(true);
		return simulationTab;
	}

	// START TABBED CLASSES
	// ------//
	// ADMINISTRATIVE DETAILS
	/**
	 * Page factory for the My Menu > Simulations > Details > Administrative
	 * Details tab for JMEDSIM.<br>
	 * (1) Finds a reference to objects within the scope of the tab<br>
	 * (2) Defines methods to interact with objects on the tab<br>
	 * 
	 * EXTENDS: SimulationDetailsBase
	 * 
	 * <br>
	 * <i>NOTE: this extends SimulationDetailsBase abstract class because the
	 * main details of this tab match that of the delete page
	 * 
	 * @author jesse.childress
	 *
	 */
	public class AdministrativeDetailsTab extends SimulationDetailsBase {

		public AdministrativeDetailsTab() {
			super(regexURL);
		}

		// TODO: handle when no service code table exists (no service code data
		// entered)

		@FindBy(xpath = "//div[@id='initialSimulationServiceRole']//table")
		WebElement serviceRoleTable;

		/**
		 * Returns a reference to the Add Service Role table
		 * 
		 * @return JMEDSimTables
		 */
		public JMEDSimTables getServiceRoleTable() {
			return new JMEDSimTables(serviceRoleTable);
		}
	}

	// Simulation Description
	/**
	 * Page factory for the My Menu > Simulations > Details > Simulation
	 * Description tab for JMEDSIM.<br>
	 * (1) Finds a reference to objects within the scope of the tab<br>
	 * (2) Defines methods to interact with objects on the tab<br>
	 * 
	 * Extends SimulationDetailsPageFactory
	 * 
	 * @author scott.brazeltion
	 *
	 */
	public class SimulationDescriptionTab extends SimulationDetailsPageFactory {

		@FindBy(id = "simulationDescription")
		WebElement simulationDescriptionTextarea;

		/**
		 * Reads the value of the Simulation Description text area
		 * 
		 * @return String
		 */
		public String readSimulationDescription() {
			AutomationHelper.printMethodName();
			return simulationDescriptionTextarea.getAttribute("value");
		}
	}

	// Learners Description
	/**
	 * Page factory for the My Menu > Simulations > Details > Learners tab for
	 * JMEDSIM.<br>
	 * (1) Finds a reference to objects within the scope of the tab<br>
	 * (2) Defines methods to interact with objects on the tab<br>
	 * 
	 * Extends SimulationDetailsPageFactory
	 * 
	 * @author scott.brazeltion
	 *
	 */
	public class LearnersTab extends SimulationDetailsPageFactory {

		// TODO: handle when no Learners table exists (no service code data
		// entered)

		@FindBy(xpath = "//div[@id='collapseLearners']//table[descendant::th[normalize-space()='Service Role']]")
		WebElement learnersTable;

		/**
		 * Returns a reference to the Learners Table
		 * 
		 * @return JMEDSimTables
		 */
		public JMEDSimTables getLearnersTable() {
			return new JMEDSimTables(learnersTable);
		}

		// TODO: handle when no Additional Learners table exists (no service
		// code data
		// entered)

		@FindBy(xpath = "//div[@id='collapseLearners']//table[descendant::th[normalize-space()='Additional Learner']]")
		WebElement additionalLearnersTable;

		/**
		 * Returns a reference to the AdditionalLearners Table
		 * 
		 * @return JMEDSimTables
		 */
		public JMEDSimTables getAdditionalLearnerTable() {
			return new JMEDSimTables(additionalLearnersTable);
		}
	}

	// Simulation OOPI Description
	/**
	 * Page factory for the My Menu > Simulations > Details > Simulation OOPI
	 * tab for JMEDSIM.<br>
	 * (1) Finds a reference to objects within the scope of the tab<br>
	 * (2) Defines methods to interact with objects on the tab<br>
	 * 
	 * Extends SimulationDetailsPageFactory
	 * 
	 * @author scott.brazeltion
	 *
	 */
	public class SimulationOOPITab extends SimulationDetailsPageFactory {

		@FindBy(id = "oOPITypeId")
		WebElement oOPITypeDropdown;

		/**
		 * Reads the currently selected value of the OOPI Type drop down.
		 * 
		 * @return String
		 */
		public String readOOPITypeSelected() {
			AutomationHelper.printMethodName();
			return AutomationHelper.readSelectedSubItem(oOPITypeDropdown);
		}

		@FindBy(id = "simulationOutcome")
		WebElement outcomeTextarea;

		/**
		 * Reads the value of the Outcome text area.
		 * 
		 * @return String
		 */
		public String readOutcome() {
			AutomationHelper.printMethodName();
			return outcomeTextarea.getAttribute("value");
		}

		@FindBy(xpath = "//div[@id='collapseSimulationObjective']//table[descendant::th[normalize-space()='Objective']]")
		WebElement objectiveTable;

		/**
		 * Returns a reference to the Objective Table.
		 * 
		 * @return JMEDSimTables - Objective Table
		 */
		public JMEDSimTables getObjectiveTable() {
			return new JMEDSimTables(objectiveTable);
		}

		@FindBy(xpath = "//div[@id='collapseSimulationObjective']//table[descendant::th[normalize-space()='Performance Indicator']]")
		WebElement performanceIndicatorsTable;

		/**
		 * Returns a reference to the Performance Indicator table.
		 * 
		 * @return JMEDSimTables - Performance Indicator Table
		 */
		public JMEDSimTables getPerformanceIndicatorTable() {
			return new JMEDSimTables(performanceIndicatorsTable);
		}

	}

	// Personnel Description
	/**
	 * Page factory for the My Menu > Simulations > Details > Personnel tab for
	 * JMEDSIM.<br>
	 * (1) Finds a reference to objects within the scope of the tab<br>
	 * (2) Defines methods to interact with objects on the tab<br>
	 * 
	 * Extends SimulationDetailsPageFactory
	 * 
	 * @author scott.brazeltion
	 *
	 */
	public class PersonnelTab extends SimulationDetailsPageFactory {

		@FindBy(xpath = "//div[@id='collapseSimulationPersonnel']//table[descendant::th[normalize-space()='Personnel']]")
		WebElement personnelTable;

		/**
		 * Returns a reference to the Personnel table.
		 * 
		 * @return JMEDSimTables - Personnel Table
		 */
		public JMEDSimTables getPersonnelTable() {
			return new JMEDSimTables(personnelTable);
		}
	}

	// Prerequisites Description
	/**
	 * Page factory for the My Menu > Simulations > Details > Prerequisites tab
	 * for JMEDSIM.<br>
	 * (1) Finds a reference to objects within the scope of the tab<br>
	 * (2) Defines methods to interact with objects on the tab<br>
	 * 
	 * Extends SimulationDetailsPageFactory
	 * 
	 * @author scott.brazeltion
	 *
	 */
	public class PrerequisitesTab extends SimulationDetailsPageFactory {

		@FindBy(xpath = "//div[@id='collapseSimulationPrerequisites']//table[descendant::th[normalize-space()='Name']]")
		WebElement prerequisitesTable;

		/**
		 * Returns a reference to the Prerequisites table.
		 * 
		 * @return JMEDSimTables - Prerequisites Table
		 */
		public JMEDSimTables getPrerequisitesTable() {
			return new JMEDSimTables(prerequisitesTable);
		}
	}

	// Equipments Description
	/**
	 * Page factory for the My Menu > Simulations > Details > Equipments tab for
	 * JMEDSIM.<br>
	 * (1) Finds a reference to objects within the scope of the tab<br>
	 * (2) Defines methods to interact with objects on the tab<br>
	 * 
	 * Extends SimulationDetailsPageFactory
	 * 
	 * @author scott.brazeltion
	 *
	 */
	public class EquipmentTab extends SimulationDetailsPageFactory {

		@FindBy(xpath = ".//div[contains(@id,'Simulator')][not(@style)]/table[@class='table table-striped table-hover table-bordered']")
		WebElement simulatorTable;

		/**
		 * Returns a reference to the Simulator table.
		 * 
		 * @return JMEDSimTables - Simulator Table
		 */
		public JMEDSimTables getSimulatorTable() {
			return new JMEDSimTables(simulatorTable);
		}

		@FindBy(xpath = ".//div[contains(@id,'divInitialConsumables')][not(@style)]/table[@class='table table-striped table-hover table-bordered']")
		WebElement consumablesTable;

		/**
		 * Returns a reference to the Consumables table.
		 * 
		 * @return JMEDSimTables - Consumables Table
		 */
		public JMEDSimTables getConsumablesTable() {
			return new JMEDSimTables(consumablesTable);
		}

		@FindBy(xpath = ".//div[contains(@id,'Non-Consumables')][not(@style)]/table[@class='table table-striped table-hover table-bordered']")
		WebElement nonConsumablesTable;

		/**
		 * Returns a reference to the Non-Consumables table.
		 * 
		 * @return JMEDSimTables - Non-Consumables Table
		 */
		public JMEDSimTables getNonConsumablesTable() {
			return new JMEDSimTables(nonConsumablesTable);
		}

		@FindBy(xpath = ".//div[contains(@id,'AVITCommunications')][not(@style)]/table[@class='table table-striped table-hover table-bordered']")
		WebElement avITCommunicationsTable;

		/**
		 * Returns a reference to the AV/IT/Communications table.
		 * 
		 * @return JMEDSimTables - AV/IT/Communications Table
		 */
		public JMEDSimTables getAVITCommunicationsTable() {
			return new JMEDSimTables(avITCommunicationsTable);
		}
	}

	// Medical Record(s)/ Patient Documentation Description
	/**
	 * Page factory for the My Menu > Simulations > Details > Medical Record(s)/
	 * Patient Documentation tab for JMEDSIM.<br>
	 * (1) Finds a reference to objects within the scope of the tab<br>
	 * (2) Defines methods to interact with objects on the tab<br>
	 * 
	 * Extends SimulationDetailsPageFactory
	 * 
	 * @author scott.brazeltion
	 *
	 */
	public class MedicalRecordsPatientDocumentationTab
			extends
				SimulationDetailsPageFactory {

		@FindBy(xpath = ".//div[contains(@id,'MedRecordsPatientDocs')][not(@style)]/table[@class='table table-striped table-hover table-bordered']")
		WebElement medRecordsPatientDocsTable;

		/**
		 * Returns a reference to the Medical Record(s)/ Patient Documentation
		 * table.
		 * 
		 * @return JMEDSimTables - Medical Record(s)/ Patient Documentation
		 *         Table
		 */
		public JMEDSimTables getMedicalRecordsPatientDocumentationTable() {
			return new JMEDSimTables(medRecordsPatientDocsTable);
		}
	}

	// Simulation Environment Description
	/**
	 * Page factory for the My Menu > Simulations > Details > Simulation
	 * Environment tab for JMEDSIM.<br>
	 * (1) Finds a reference to objects within the scope of the tab<br>
	 * (2) Defines methods to interact with objects on the tab<br>
	 * 
	 * Extends SimulationDetailsPageFactory
	 * 
	 * @author scott.brazeltion
	 *
	 */
	public class SimulationEnvironmentTab extends SimulationDetailsPageFactory {

		@FindBy(id = "spaceRequirementLocation")
		WebElement spaceRequirementLocationTextarea;

		/**
		 * Reads the value of the Space Requirement/Location text area.
		 * 
		 * @return String
		 */
		public String readSpaceRequirementLocation() {
			AutomationHelper.printMethodName();
			return spaceRequirementLocationTextarea.getAttribute("value");
		}

		@FindBy(id = "clinicalOperationalEnvType")
		WebElement clinicalOperationalEnvTypeTextarea;

		/**
		 * Reads the value of the Clinical/Operational Environment Type text
		 * area.
		 * 
		 * @return String
		 */
		public String readClinicalOperationalEnvironmentType() {
			AutomationHelper.printMethodName();
			return clinicalOperationalEnvTypeTextarea.getAttribute("value");
		}

		@FindBy(xpath = "//div[@id='collapseEnvironment']//table[descendant::th[normalize-space()='Item']]")
		WebElement furniturePropTable;

		/**
		 * Returns a reference to the Furniture / Prop Table.
		 * 
		 * @return JMEDSimTables - Furniture / Prop Table
		 */
		public JMEDSimTables getFurniturePropTable() {
			return new JMEDSimTables(furniturePropTable);
		}

	}

	// EnvironmentAndEquipmentSetupTab
	/**
	 * Page factory for the My Menu > Simulations > Details > Environment and
	 * Equipment Setup tab for JMEDSIM.<br>
	 * (1) Finds a reference to objects within the scope of the tab<br>
	 * (2) Defines methods to interact with objects on the tab<br>
	 * 
	 * Extends SimulationDetailsPageFactory
	 * 
	 * @author scott.brazeltion
	 *
	 */
	public class EnvironmentAndEquipmentSetupTab
			extends
				SimulationDetailsPageFactory {

		@FindBy(xpath = ".//div[contains(@id,'EnvEquipSetup')][not(@style)]/table[@class='table table-striped table-hover table-bordered']")
		WebElement envAndEquipSetupTable;

		/**
		 * Returns a reference to the Medical Record(s)/ Patient Documentation
		 * table.
		 * 
		 * @return JMEDSimTables - Medical Record(s)/ Patient Documentation
		 *         Table
		 */
		public JMEDSimTables getEnvironmentAndEquipmentSetupTable() {
			return new JMEDSimTables(envAndEquipSetupTable);
		}
	}

	// Standardized Patient Script(s)
	/**
	 * Page factory for the My Menu > Simulations > Details > Standardized
	 * Patient Script(s) tab for JMEDSIM.<br>
	 * (1) Finds a reference to objects within the scope of the tab<br>
	 * (2) Defines methods to interact with objects on the tab<br>
	 * 
	 * Extends SimulationDetailsPageFactory
	 * 
	 * @author scott.brazeltion
	 *
	 */
	public class StandardizedPatientScriptsTab
			extends
				SimulationDetailsPageFactory {

		@FindBy(xpath = ".//div[contains(@id,'SPScript')][not(@style)]/table[@class='table table-striped table-hover table-bordered']")
		WebElement envAndEquipSetupTable;

		/**
		 * Returns a reference to the Standardized Patient Script(s) table.
		 * 
		 * @return JMEDSimTables - Standardized Patient Script(s)
		 */
		public JMEDSimTables getStandardizedPatientScriptsTable() {
			return new JMEDSimTables(envAndEquipSetupTable);
		}
	}

}
