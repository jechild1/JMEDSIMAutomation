package pageFactories.MyMenuPages;

import java.util.HashMap;

import javax.ws.rs.NotAllowedException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import pageFactories.JMEDSIMBase;
import pageFactories.JMEDSimTables;
import pageFactories.ModifyBase;
import utilities.AutomationHelper;

/**
 * Page factory for the My Menu > Simulations > Edit Simulation page for
 * JMEDSIM.<br>
 * (1) Finds a reference to objects on the page<br>
 * (2) Defines methods to interact with objects on the page<br>
 * 
 * Extends ModifySimulationsBase
 * 
 * @author jesse.childress
 *
 */
public class EditSimulationPageFactory extends ModifyBase {
	private static String regexURL = BASE_URL + "Simulations/Edit.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public EditSimulationPageFactory() {
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
	 * {@inheritDoc}
	 */
	@Override
	@Deprecated
	protected String readErrorMessageForField(String field) {
		throw new NotAllowedException(
				"Use readErrorMessageInEditSimulation(WebElement field) instead");
	}

	/**
	 * Generic protected method to read an error message for a field
	 * 
	 * NOTE: returns an empty string if span not found
	 * 
	 * @param field
	 * @return String
	 */
	protected String readErrorMessageInEditSimulation(WebElement field) {
		return field.getAttribute("style").contains("display: inline-block;")
				? field.getText()
				: "";
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
	 * Returns a reference to the Administrative Details tab within Edit
	 * Simulation
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

	@FindBy(linkText = "Simulation Description (Required)")
	WebElement simulationDescriptionTab;

	/**
	 * Clicks the Simulation Description tab.
	 * 
	 * @param action
	 *            - true = expand | false = collapse
	 */
	private void clickSimulationDescriptionTab(boolean action) {
		AutomationHelper.printMethodName();
		clickTab(simulationDescriptionTab, action);
	}

	/**
	 * Returns a reference to the Simulation Description tab within Edit
	 * Simulation
	 * 
	 * @return SimulationDescriptionTab
	 */
	public SimulationDescriptionTab getSimulationDescriptionTab() {
		SimulationDescriptionTab simulationTab = (SimulationDescriptionTab) getSimulationTab(
				"simulationDescriptionTab");

		if (simulationTab == null) {
			simulationTab = new SimulationDescriptionTab();
			setSimulationTab("simulationDescriptionTab", simulationTab);
		}

		// Ensures that the tab is clicked
		clickSimulationDescriptionTab(true);
		return simulationTab;
	}

	@FindBy(linkText = "Learners (Required)")
	WebElement learnersTab;

	/**
	 * Clicks the Learners Tab.
	 * 
	 * @param action
	 *            - true = expand | false = collapse
	 * 
	 */
	private void clickLearnersTab(boolean action) {
		AutomationHelper.printMethodName();
		clickTab(learnersTab, action);
	}

	/**
	 * Returns a reference to the Learners tab within Edit Simulation
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
	 * Clicks the Simulation Objective / Outcome / Performance Indicators
	 * (Required) tab.
	 * 
	 * @param action
	 *            - true = expand | false = collapse
	 */
	private void clickSimulationOOPITab(boolean action) {
		AutomationHelper.printMethodName();
		clickTab(simulationOOPITab, action);
	}

	/**
	 * Returns a reference to the Simulation Objective / Outcome / Performance
	 * Indicators (Required) tab within Edit Simulation
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
	 * Clicks the Personnel tab.
	 * 
	 * @param action
	 *            - true = expand | false = collapse
	 */
	private void clickPersonnelTab(boolean action) {
		AutomationHelper.printMethodName();
		clickTab(personnelTab, action);
	}

	/**
	 * Returns a reference to the Personnel (Required) tab within Edit
	 * Simulation
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
	WebElement prerequsitiesTab;

	/**
	 * Clicks the Prerequisites tab.
	 * 
	 * @param action
	 *            - true = expand | false = collapse
	 */
	private void clickPrerequsitiesTab(boolean action) {
		AutomationHelper.printMethodName();
		clickTab(prerequsitiesTab, true);
	}

	/**
	 * Returns a reference to the Prerequisites tab within Edit Simulation
	 * 
	 * @return PrerequisitesTab
	 */
	public PrerequisitesTab getPrerequsitiesTab() {
		PrerequisitesTab simulationTab = (PrerequisitesTab) getSimulationTab(
				"prerequisitesTab");

		if (simulationTab == null) {
			simulationTab = new PrerequisitesTab();
			setSimulationTab("prerequisitesTab", simulationTab);
		}

		// Ensures that the tab is clicked
		clickPrerequsitiesTab(true);
		return simulationTab;
	}

	@FindBy(linkText = "Equipments (Required)")
	WebElement equipmentTab;

	/**
	 * Clicks the Equipment tab.
	 * 
	 * @param action
	 *            - true = expand | false = collapse
	 */
	private void clickEquipmentTab(boolean action) {
		AutomationHelper.printMethodName();
		clickTab(equipmentTab, true);
	}

	/**
	 * Returns a reference to the Equipment tab within Edit Simulation
	 * 
	 * @return EquipmentTab
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
	WebElement medRecordsPatientDocTab;

	/**
	 * Clicks the Medical Record(s)/ Patient Documentation tab.
	 * 
	 * @param action
	 *            - true = expand | false = collapse
	 */
	private void clickMedicalRecordsPatientDocumentationTab(boolean action) {
		AutomationHelper.printMethodName();
		clickTab(medRecordsPatientDocTab, true);
	}

	/**
	 * Returns a reference to the Medical Record(s)/ Patient Documentation tab
	 * within Edit Simulation
	 * 
	 * @return MedicalRecordsPatientDocumentationTab
	 */
	public MedicalRecordsPatientDocumentationTab getMedicalRecordsPatientDocumentationTab() {
		MedicalRecordsPatientDocumentationTab simulationTab = (MedicalRecordsPatientDocumentationTab) getSimulationTab(
				"medRecordsPatientDocTab");

		if (simulationTab == null) {
			simulationTab = new MedicalRecordsPatientDocumentationTab();
			setSimulationTab("medRecordsPatientDocTab", simulationTab);
		}

		// Ensures that the tab is clicked
		clickMedicalRecordsPatientDocumentationTab(true);
		return simulationTab;
	}

	@FindBy(linkText = "Simulation Environment")
	WebElement simulationEnvironmentTab;

	/**
	 * Clicks the Simulation Environment tab.
	 * 
	 * @param action
	 *            - true = expand | false = collapse
	 */
	private void clickSimulationEnvironmentTab(boolean action) {
		AutomationHelper.printMethodName();
		clickTab(simulationEnvironmentTab, true);
	}

	/**
	 * Returns a reference to the Simulation Environment tab within Edit
	 * Simulation
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
	WebElement environmentAndEqupmentSetupTab;

	/**
	 * Clicks the Environment and Equipment Setup tab.
	 * 
	 * @param action
	 *            - true = expand | false = collapse
	 */
	private void clickEnvironmentAndEquipmentSetupTab(boolean action) {
		AutomationHelper.printMethodName();
		clickTab(environmentAndEqupmentSetupTab, true);
	}

	/**
	 * Returns a reference to the Environment and Equipment Setup tab within
	 * Edit Simulation
	 * 
	 * @return EnvironmentAndEquipmentTab
	 */
	public EnvironmentAndEquipmentSetupTab getEnvironmentAndEquipmentSetupTab() {
		EnvironmentAndEquipmentSetupTab simulationTab = (EnvironmentAndEquipmentSetupTab) getSimulationTab(
				"environmentAndEqupmentSetupTab");

		if (simulationTab == null) {
			simulationTab = new EnvironmentAndEquipmentSetupTab();
			setSimulationTab("environmentAndEqupmentSetupTab", simulationTab);
		}

		// Ensures that the tab is clicked
		clickEnvironmentAndEquipmentSetupTab(true);
		return simulationTab;
	}

	@FindBy(linkText = "Standardized Patient Script(s)")
	WebElement standardizedPatientScriptsTab;

	/**
	 * Clicks the Standardized Patient Script(s) tab.
	 * 
	 * @param action
	 *            - true = expand | false = collapse
	 */
	private void clickStandardizedPatientScriptsTab(boolean action) {
		AutomationHelper.printMethodName();
		clickTab(standardizedPatientScriptsTab, true);
	}

	/**
	 * Returns a reference to the Standardized Patient Script(s) tab within Edit
	 * Simulation
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

	/**
	 * Abstract Modal Page factory for modals that can be found on the Edit
	 * Simulation page.<br>
	 * (1) Finds a reference to objects on the page <br>
	 * (2) Defines methods to interact with objects on the page.<br>
	 * 
	 * Extends JMEDSIMBase
	 * 
	 * @author scott.brazelton
	 *
	 */
	public abstract class SimulationModalBase extends JMEDSIMBase {
		private final WebElement currentModal;

		/**
		 * Abstract Modal Constructor: Accepts the modal from the calling class,
		 * provides base methods common to modals.
		 * 
		 * @param modal
		 * 
		 *            Extends JMEDSIMBase
		 */
		public SimulationModalBase(WebElement modal) {
			String modalID = modal.getAttribute("id");
			DefaultElementLocatorFactory parentContext = new DefaultElementLocatorFactory(
					modal);
			PageFactory.initElements(parentContext, this);

			// get modal post-pagefactory initilization
			currentModal = driver.findElement(By.id(modalID));

			waitForModalToLoad();
		}

		/**
		 * Is the current modal displayed
		 * 
		 * @return boolean - true = yes | false = no
		 */
		private boolean isModalPresent() {
			while (currentModal.getAttribute("aria-hidden") == null) {
				AutomationHelper.wait(1);
			}

			return currentModal.getAttribute("aria-hidden").equals("false")
					? true
					: false;
		}

		/**
		 * Waits for current modal to load
		 */
		private void waitForModalToLoad() {

			waitForPageToLoad();

			while (!isModalPresent()) {
				AutomationHelper.wait(1);
			}
		}

		@FindBy(xpath = "//div[@id='modalSavedSuccessfully']")
		WebElement modalSavedSuccessfullyModal;

		/**
		 * Is the modal loader icon displayed
		 * 
		 * @param id
		 * @return boolean - true = yes | false = no
		 */
		private boolean isModalLoaderDisplayed(String id) {
			return currentModal.findElement(By.id(id)).isDisplayed();
		}

		/**
		 * Waits for modal specific loader to complete <br>
		 * NOTE: not all modals have this
		 * 
		 * @param id
		 */
		protected void waitForModalLoaderToCompleteByID(String id) {

			waitForPageToLoad();
			AutomationHelper.wait(1);
			while (isModalLoaderDisplayed(id)) {
				AutomationHelper.wait(1);
			}
		}

		@FindBy(xpath = ".//button[text()='Add']")
		WebElement addButton;

		/**
		 * Clicks "Add" button in modal
		 */
		public void clickAdd() {
			AutomationHelper.printMethodName();
			WebDriverWait wait = new WebDriverWait(driver, NORMAL_TIMEOUT);
			wait.until(ExpectedConditions.visibilityOf(addButton));
			addButton.click();
			waitForSavedSuccessfullyModal();
		}

		@FindBy(xpath = ".//button[text()='Close']")
		WebElement closeButton;

		/**
		 * Clicks "Close" button in modal
		 */
		public void clickClose() {
			AutomationHelper.printMethodName();
			WebDriverWait wait = new WebDriverWait(driver, NORMAL_TIMEOUT);
			wait.until(ExpectedConditions.visibilityOf(closeButton));
			closeButton.click();
			wait.until(ExpectedConditions.invisibilityOf(closeButton));
			waitForBackdropToBeRemoved();
		}

		@FindBy(xpath = ".//button[text()='Save']")
		WebElement saveButton;

		/**
		 * Clicks "Save" button in modal
		 */
		public void clickSave() {
			AutomationHelper.printMethodName();
			WebDriverWait wait = new WebDriverWait(driver, NORMAL_TIMEOUT);
			wait.until(ExpectedConditions.visibilityOf(saveButton));
			saveButton.click();
			waitForSavedSuccessfullyModal();
		}

		@FindBy(id = "searchString")
		WebElement searchTextfield;

		/**
		 * Sets text for "Search by"
		 * 
		 * @param searchText
		 */
		public void setSearchBy(String searchText) {
			AutomationHelper.printMethodName();

			AutomationHelper.setTextField(searchTextfield, searchText);

		}

		@FindBy(id = "searchButton")
		WebElement searchButton;

		/**
		 * Clicks "Search"
		 */
		public void clickSearch() {
			AutomationHelper.printMethodName();

			searchButton.click();
			waitForPageToLoad();

		}
	}

	/**
	 * Page factory for the My Menu > Simulations > Edit Simulation >
	 * Administrative Details tab for JMEDSIM.<br>
	 * (1) Finds a reference to objects within the scope of the tab<br>
	 * (2) Defines methods to interact with objects on the tab<br>
	 * <br>
	 * <br>
	 * <i>Note: This extends the ModifySimulationsBase class because the fields
	 * on this Administrative Details page are applicable both when creating a
	 * New Simulation and when exiting an existing simulation. <br>
	 * Extends ModifySimulationsBase
	 * 
	 * @author jesse.childress
	 *
	 */
	public class AdministrativeDetailsTab extends ModifySimulationBase {

		public AdministrativeDetailsTab() {
			super(regexURL);
		}

		@FindBy(id = "saveSimulation")
		WebElement saveButton;

		/**
		 * Clicks the Save button on the Simulation Description (Required) tab.
		 */
		public void clickSave() {
			AutomationHelper.printMethodName();
			saveButton.click();
			// This is necessary to ensure the "Saved Successfully" modal
			// disappears before proceeding.
			waitForSavedSuccessfullyModal();
		}

		@FindBy(linkText = "Add Service Role")
		WebElement addServiceRoleLink;

		/**
		 * Clicks the Add Service Role link.
		 * 
		 * @return AddServiceRolesModal
		 */
		public AddServiceRolesModal clickAddServiceRole() {
			AutomationHelper.printMethodName();
			// Ensure the Administrative Details link is open and clicked
			clickAdministrativeDetailsTab(true);
			addServiceRoleLink.click();

			return new AddServiceRolesModal();
		}

		@FindBy(xpath = ".//div[@id='initialSimulationServiceRole']/table|.//div[@id='simulationServiceRole']/table")
		WebElement serviceRoleTable;

		/**
		 * Returns a reference to the Add Service Role table
		 * 
		 * @return JMEDSimTables
		 */
		public JMEDSimTables getServiceRoleTable() {
			return new JMEDSimTables(serviceRoleTable);

		}

		@FindBy(id = "serviceRoleListModal")
		WebElement serviceRoleListModal;

		/**
		 * Modal Page factory sub class for the Add Service Roles modal on My
		 * Menu > Simulations > Edit Simulation page for JMEDSIM after clicking
		 * on "Add Service Role" in Administrative Details tab.<br>
		 * (1) Finds a reference to objects on the page <br>
		 * (2) Defines methods to interact with objects on the page.<br>
		 * 
		 * Extends SimulationModalBase
		 * 
		 * @author scott.brazelton
		 *
		 */
		public class AddServiceRolesModal extends SimulationModalBase {

			/**
			 * Modal Constructor: Instantiates super class
			 */
			public AddServiceRolesModal() {
				super(serviceRoleListModal);
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			@Deprecated
			public void clickAdd() {
				throw new NotAllowedException(
						"Use clickAdd() via the getAddServiceRolesTable() instead");
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			@Deprecated
			public void clickSave() {
				throw new NotAllowedException(
						"Use clickAdd() via the getAddServiceRolesTable() instead");
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void clickClose() {
				waitForServiceRoleSavedSuccessfullyModal();
				super.clickClose();
			}

			@FindBy(xpath = "//div[@id='serviceRoleListModal']//table [@class = 'table table-striped table-hover table-bordered']")
			WebElement addServiceRolesTable;

			/**
			 * Returns a reference to the Add Service Roles table.
			 * 
			 * @return WebElement
			 */
			public JMEDSimTables getAddServiceRolesTable() {
				return new JMEDSimTables(addServiceRolesTable);
			}

			@FindBy(xpath = "//div[@id='loaderDiv']")
			WebElement servieRoleLoader;

			/**
			 * Is the Service Role Loader present on page
			 * 
			 * @return boolean - true = yes | false = no
			 */
			private boolean isServiceRoleLoaderPresent() {
				return servieRoleLoader.isDisplayed() ? true : false;
			}

			/**
			 * Waits for Service Role Loader to complete
			 */
			private void waitForServiceRoleSavedSuccessfullyModal() {

				AutomationHelper.wait(1);
				while (isServiceRoleLoaderPresent()) {
					AutomationHelper.wait(1);
				}
			}
		}

	}

	/**
	 * Page factory for the My Menu > Simulations > Edit Simulation > Simulation
	 * Description (Required) tab for JMEDSIM.<br>
	 * (1) Finds a reference to objects within the scope of the tab<br>
	 * (2) Defines methods to interact with objects on the tab<br>
	 * 
	 * Extends EditSimulationPageFactory
	 * 
	 * @author jesse.childress
	 *
	 */
	public class SimulationDescriptionTab extends EditSimulationPageFactory {

		@FindBy(id = "simulationDescription")
		WebElement simulationDescriptionTextarea;

		/**
		 * Sets the Simulation Description text area
		 * 
		 * @param text
		 */
		public void setSimulationDescription(String text) {
			AutomationHelper.printMethodName();
			AutomationHelper.setTextField(simulationDescriptionTextarea, text);
		}

		/**
		 * Reads the value of the Simulation Description text area
		 * 
		 * @return String
		 */
		public String readSimulationDescription() {
			AutomationHelper.printMethodName();
			return simulationDescriptionTextarea.getAttribute("value");
		}

		@FindBy(id = "descriptionRequired")
		WebElement simulationDescriptionErrorMessage;

		/**
		 * Reads Simulation Description error message (if any)
		 * 
		 * NOTE: returns empty string if no message
		 * 
		 * @return String
		 */
		public String readSimulationDescriptionErrorMessage() {
			AutomationHelper.printMethodName();

			return readErrorMessageInEditSimulation(
					simulationDescriptionErrorMessage);
		}

		@FindBy(id = "saveSimulationDescription")
		WebElement saveButton;

		/**
		 * Clicks the Save button on the Simulation Description (Required) tab.
		 */
		public void clickSave() {
			AutomationHelper.printMethodName();
			saveButton.click();
			// This is necessary to ensure the "Saved Successfully" modal
			// disappears before proceeding.
			waitForSavedSuccessfullyModal();
		}
	}

	/**
	 * Page factory for the My Menu > Simulations > Edit Simulation > Learners
	 * (Required) tab for JMEDSIM.<br>
	 * (1) Finds a reference to objects within the scope of the tab<br>
	 * (2) Defines methods to interact with objects on the tab<br>
	 * 
	 * Extends EditSimulationPageFactory
	 * 
	 * @author jesse.childress
	 *
	 */
	public class LearnersTab extends EditSimulationPageFactory {

		@FindBy(linkText = "Add Additional Learner")
		WebElement addAdditionalLearnerLink;

		/**
		 * Clicks the Add Additional Learner link.
		 * 
		 * @return AdditionalLearnerModal
		 */
		public AdditionalLearnerModal clickAddAdditionalLearner() {
			AutomationHelper.printMethodName();
			addAdditionalLearnerLink.click();
			return new AdditionalLearnerModal();
		}

		/**
		 * Clicks "Edit" in the Learners Table by service role
		 * 
		 * @param serviceRole
		 * @return EditLearnerModal
		 */
		public EditLearnerModal clickEditInLearnersTableByServiceRole(
				String serviceRole) {
			getLearnersTable().clickLinkInRow("Service Role", serviceRole, "",
					"Edit", false);

			return new EditLearnerModal();
		}

		/**
		 * Clicks "Edit" in the Additional Learners Table by Additional Learner
		 * 
		 * @param additionalLearner
		 * @return AdditionalLearnerModal
		 */
		public AdditionalLearnerModal clickEditInAdditionalLearnersTableByAdditionalLearner(
				String additionalLearner) {
			getAdditionalLearnersTable().clickLinkInRow("Additional Learner",
					additionalLearner, "", "Edit", false);

			return new AdditionalLearnerModal();
		}

		/**
		 * Clicks "Delete" in the Additional Learners Table by Additional
		 * Learner
		 * 
		 * @param additionalLearner
		 */
		public void clickDeleteInAdditionalLearnersTableByAdditionalLearner(
				String additionalLearner) {

			getAdditionalLearnersTable().clickLinkInRow("Additional Learner",
					additionalLearner, "", "Delete", false);
		}

		@FindBy(xpath = ".//div[contains(@id,'SimulationLearner')][not(@style)]/table[@class='table table-striped table-hover table-bordered']")
		WebElement learnersTable;

		/**
		 * Returns a reference to the Learners Table.
		 * 
		 * @return JMEDSimTables
		 */
		public JMEDSimTables getLearnersTable() {
			return new JMEDSimTables(learnersTable);
		}

		@FindBy(xpath = ".//div[contains(@id,'AdditionalLearners')][not(@style)]/table[@class='table table-striped table-hover table-bordered']")
		WebElement additionalLearnersTable;

		/**
		 * Returns a reference to the Additional Learners Table.
		 * 
		 * @return JMEDSimTables
		 */
		public JMEDSimTables getAdditionalLearnersTable() {
			return new JMEDSimTables(additionalLearnersTable);
		}

		@FindBy(id = "editLearnerModal")
		WebElement editLearnerModal;

		/**
		 * Modal Page factory sub class for the Edit Learner modal on My Menu >
		 * Simulations > Edit Simulation > Learners Tab for JMEDSIM.<br>
		 * (1) Finds a reference to objects on the modal <br>
		 * (2) Defines methods to interact with objects on the page.<br>
		 * 
		 * Extends SimulationModalBase
		 * 
		 * @author jesse.childress
		 *
		 */
		public class EditLearnerModal extends SimulationModalBase {

			/**
			 * Modal Constructor: Instantiates super class
			 */
			public EditLearnerModal() {
				super(editLearnerModal);
			}

			@FindBy(id = "simulationTitle")
			WebElement serviceRoleTextfield;

			/**
			 * Returns the value of the Service Role field.
			 * 
			 * @return String
			 */
			public String readServiceRole() {
				AutomationHelper.printMethodName();
				return serviceRoleTextfield.getAttribute("value");
			}

			@FindBy(id = "learnerProfileId")
			WebElement learnerProfileDropdown;

			/**
			 * Select a value in the Learner Profile drop down.
			 * 
			 * @param text
			 */
			public void selectLearnerProfile(String text) {
				AutomationHelper.printMethodName();
				AutomationHelper.selectDropdownItem(learnerProfileDropdown,
						text);
			}

			/**
			 * Reads the currently selected value of the Learner Profile drop
			 * down.
			 * 
			 * @return String
			 */
			public String readLearnerProfileSelected() {
				AutomationHelper.printMethodName();
				return AutomationHelper
						.readSelectedSubItem(learnerProfileDropdown);
			}

			@FindBy(id = "simulationQty")
			WebElement qtyTextField;

			/**
			 * Sets a value in the Qty text field.
			 * 
			 * @param text
			 */
			public void setQty(String text) {
				AutomationHelper.printMethodName();
				AutomationHelper.setTextField(qtyTextField, text);
			}

			/**
			 * Reads the value of the Quantity text field.
			 * 
			 * @return String
			 */
			public String readQty() {
				AutomationHelper.printMethodName();
				return qtyTextField.getAttribute("value");
			}

			@FindBy(id = "learnerQtyRequired")
			WebElement qtyRequired;

			/**
			 * Reads Qty error message (if any)
			 * 
			 * NOTE: returns empty string if no message
			 * 
			 * @return String
			 */
			public String readQtyErrorMessage() {
				AutomationHelper.printMethodName();

				return readErrorMessageInEditSimulation(qtyRequired);
			}

		}

		@FindBy(id = "additionalLearnerModal")
		WebElement additionalLearnerModal;

		/**
		 * Modal Page factory sub class for the Additional Learners modal on My
		 * Menu > Simulations > Edit Simulation page for JMEDSIM after clicking
		 * on "Add Additional Learner" or Edit in Learners tab.<br>
		 * (1) Finds a reference to objects on the page <br>
		 * (2) Defines methods to interact with objects on the page.<br>
		 * 
		 * Extends SimulationModal
		 * 
		 * @author scott.brazelton
		 *
		 */
		public class AdditionalLearnerModal extends SimulationModalBase {

			/**
			 * Modal Constructor: Instantiates super class
			 */
			public AdditionalLearnerModal() {
				super(additionalLearnerModal);
			}

			@FindBy(id = "additionalLearner")
			WebElement additionalLearnerTextfield;

			/**
			 * Set text in field "Additional Learner".
			 * 
			 * @param text
			 */
			public void setAdditionalLearner(String text) {
				AutomationHelper.printMethodName();
				AutomationHelper.setTextField(additionalLearnerTextfield, text);
			}

			/**
			 * Reads Additional Learner
			 * 
			 * @return String
			 */
			public String readAdditionalLearner() {
				AutomationHelper.printMethodName();
				return additionalLearnerTextfield.getAttribute("value");
			}

			@FindBy(id = "additionalLearnerRequired")
			WebElement additionalLearnerRequiredLabel;

			/**
			 * Reads Additional Learner error message (if any)
			 * 
			 * NOTE: returns empty string if no message
			 * 
			 * @return String
			 */
			public String readAdditionalLearnerErrorMessage() {
				AutomationHelper.printMethodName();
				return readErrorMessageInEditSimulation(
						additionalLearnerRequiredLabel);
			}

			@FindBy(id = "qty")
			WebElement qtyTextfield;

			/**
			 * Set text in field "Qty".
			 * 
			 * @param text
			 */
			public void setQty(String text) {
				AutomationHelper.printMethodName();
				AutomationHelper.setTextField(qtyTextfield, text);
			}

			/**
			 * Reads Qty text
			 * 
			 * @return String
			 */
			public String readQty() {
				AutomationHelper.printMethodName();
				return qtyTextfield.getAttribute("value");
			}

			@FindBy(id = "additionalLearnerQtyRequired")
			WebElement qtyRequiredLabel;

			/**
			 * Reads Qty error message (if any)
			 * 
			 * NOTE: returns empty string if no message
			 * 
			 * @return String
			 */
			public String readQtyErrorMessage() {
				AutomationHelper.printMethodName();
				return readErrorMessageInEditSimulation(qtyRequiredLabel);
			}

			@FindBy(id = "addLearnerProfileId")
			WebElement learnerProfileDropdown;

			/**
			 * Select text in "Learner Profile" drop down.
			 * 
			 * @param text
			 */
			public void selectLearnerProfile(String text) {
				AutomationHelper.printMethodName();
				AutomationHelper.selectDropdownItem(learnerProfileDropdown,
						text);
			}

			/**
			 * Reads Learner Profile selected text
			 * 
			 * @return String
			 */
			public String readLearnerProfileSelected() {
				AutomationHelper.printMethodName();
				return AutomationHelper
						.readSelectedSubItem(learnerProfileDropdown);
			}

			@FindBy(id = "otherLearnerProfile")
			WebElement otherLearnerProfileTextfield;

			/**
			 * Set text in field "Other Learner Profile".
			 * 
			 * @param text
			 */
			public void setOtherLearnerProfile(String text) {
				AutomationHelper.printMethodName();
				AutomationHelper.setTextField(otherLearnerProfileTextfield,
						text);
			}

			/**
			 * Reads Other Learner Profile text
			 * 
			 * @return String
			 */
			public String readOtherLearnerProfile() {
				AutomationHelper.printMethodName();
				return otherLearnerProfileTextfield.getAttribute("value");
			}

			@FindBy(id = "otherLearnerProfileRequired")
			WebElement otherLearnerProfileRequiredLabel;

			/**
			 * Reads Other Learner Profile error message (if any)
			 * 
			 * NOTE: returns empty string if no message
			 * 
			 * @return String
			 */
			public String readOtherLearnerProfileErrorMessage() {
				AutomationHelper.printMethodName();
				return readErrorMessageInEditSimulation(
						otherLearnerProfileRequiredLabel);
			}
		}
	}

	/**
	 * Page factory for the My Menu > Simulations > Edit Simulation > Simulation
	 * Objective / Outcome / Performance Indicators (Required) tab for
	 * JMEDSIM.<br>
	 * (1) Finds a reference to objects within the scope of the tab<br>
	 * (2) Defines methods to interact with objects on the tab<br>
	 * 
	 * @author jesse.childress
	 *
	 */
	public class SimulationOOPITab extends EditSimulationPageFactory {
		@FindBy(xpath = "//div[@id='divOOPIType']")
		WebElement simulationOOPITabElements;

		/**
		 * Is the Simulation OOPI elements displayed on the Simulation OOPI tab
		 * 
		 * @return boolean - true = yes | false = no
		 */
		private boolean isSimulationOOPITabElementsPresent() {
			AutomationHelper.adjustWaitTimeToShort();
			boolean isPresent = simulationOOPITabElements.isDisplayed();
			AutomationHelper.adjustWaitTimeToNormal();
			return isPresent;
		}

		/**
		 * Waits for Simulation OOPI tab elements to become available
		 */
		// Changed from private to protected
		private void waitForSimulationOOPITabElementsModal() {

			waitForPageToLoad();
			AutomationHelper.wait(1);
			while (!isSimulationOOPITabElementsPresent()) {
				AutomationHelper.wait(1);
			}
		}

		@FindBy(id = "oOPITypeId")
		WebElement oOPITypeDropdown;

		/**
		 * Sets the OOPI Type drop down field with the passed in text.
		 * 
		 * @param text
		 */
		public void selectoOpiType(String text) {
			AutomationHelper.printMethodName();
			AutomationHelper.selectDropdownItem(oOPITypeDropdown, text);
			// There is a delay between selection and the elements becoming
			// available
			waitForSimulationOOPITabElementsModal();
		}

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
		 * Sets the Outcome text area with the passed in text.
		 * 
		 * @param text
		 */
		public void setOutcome(String text) {
			AutomationHelper.printMethodName();
			AutomationHelper.setTextField(outcomeTextarea, text);
		}

		/**
		 * Reads the value of the Outcome text area.
		 * 
		 * @return String
		 */
		public String readOutcome() {
			AutomationHelper.printMethodName();
			return outcomeTextarea.getAttribute("value");
		}

		@FindBy(id = "outcomeRequired")
		WebElement outcomeRequiredLabel;

		/**
		 * Reads Outcome error message (if any)
		 * 
		 * NOTE: returns empty string if no message
		 * 
		 * @return String
		 */
		public String readOutcomeErrorMessage() {
			AutomationHelper.printMethodName();
			return readErrorMessageInEditSimulation(outcomeRequiredLabel);
		}

		@FindBy(id = "saveSimulationOutcome")
		WebElement saveButton;

		/**
		 * Clicks the save button in the Simulation OOPI section (below Outcome
		 * section)
		 */
		public void clickSave() {
			AutomationHelper.printMethodName();
			saveButton.click();
			waitForSavedSuccessfullyModal();
		}

		@FindBy(linkText = "Add Objective")
		WebElement addObjectiveLink;

		/**
		 * Clicks the Add Objective link.
		 */
		public ObjectiveModal clickAddObjective() {
			AutomationHelper.printMethodName();
			// Ensure OOPI tab to ensure it's open
			clickSimulationOOPITab(true);
			addObjectiveLink.click();
			return new ObjectiveModal();
		}

		@FindBy(xpath = ".//div[contains(@id,'divObjectives')][not(@style)]/table[@class='table table-striped table-hover table-bordered']")
		WebElement objectiveTable;

		/**
		 * Returns a reference to the Objective Table.
		 * 
		 * @return JMEDSimTables - Objective Table
		 */
		public JMEDSimTables getObjectiveTable() {
			return new JMEDSimTables(objectiveTable);
		}

		/**
		 * Clicks "Edit" in the Objective Table by Objective text
		 * 
		 * @param objective
		 * @return ObjectiveModal
		 */
		public ObjectiveModal clickEditInObjectiveTableByObjective(
				String objective) {

			getObjectiveTable().clickLinkInRow("Objective", objective, "",
					"Edit", false);

			return new ObjectiveModal();
		}

		/**
		 * Clicks "Delete" in the Objective Table by Objective text
		 * 
		 * @param objective
		 */
		public void clickDeleteInObjectiveTableByObjective(String objective) {

			getObjectiveTable().clickLinkInRow("Objective", objective, "",
					"Delete", false);

		}

		@FindBy(linkText = "Add Performance Indicator")
		WebElement addPerformanceIndicatorLink;

		/**
		 * Clicks the Add Performance Indicator link.
		 */
		public PerformanceIndicatorModal clickAddPerformanceIndicator() {
			AutomationHelper.printMethodName();
			// Ensure OOPI tab to ensure it's open
			clickSimulationOOPITab(true);
			addPerformanceIndicatorLink.click();
			return new PerformanceIndicatorModal();
		}

		@FindBy(xpath = ".//div[contains(@id,'PerformanceIndicators')][not(@style)]/table[@class='table table-striped table-hover table-bordered']")
		WebElement performanceIndicatorsTable;

		/**
		 * Returns a reference to the Performance Indicator table.
		 * 
		 * @return JMEDSimTables - Performance Indicator Table
		 */
		public JMEDSimTables getPerformanceIndicatorTable() {
			return new JMEDSimTables(performanceIndicatorsTable);
		}

		/**
		 * Clicks "Edit" in the Performance Indicator Table by Performance
		 * Indicator text
		 * 
		 * @param performanceIndicator
		 * @return PerformanceIndicatorModal
		 */
		public PerformanceIndicatorModal clickEditInPerformanceIndicatorTableByPerformanceIndicator(
				String performanceIndicator) {

			getObjectiveTable().clickLinkInRow("Performance Indicator",
					performanceIndicator, "", "Edit", false);

			return new PerformanceIndicatorModal();
		}

		/**
		 * Clicks "Delete" in the Performance Indicator Table by Performance
		 * Indicator text
		 * 
		 * @param objective
		 */
		public void clickDeleteInPerformanceIndicatorTableByPerformanceIndicator(
				String performanceIndicator) {

			getObjectiveTable().clickLinkInRow("Performance Indicator",
					performanceIndicator, "", "Delete", false);
		}

		@FindBy(id = "performanceIndicatorModal")
		WebElement performanceIndicatorModal;

		/**
		 * Modal Page factory sub class for the Performance Indicator modal on
		 * My Menu > Simulations > Edit Simulation > Simulation OOPI Tab for
		 * JMEDSIM after clicking on "Add Performance Indicator" or Edit in
		 * Simulation OOPI tab.<br>
		 * (1) Finds a reference to objects on the page <br>
		 * (2) Defines methods to interact with objects on the page.<br>
		 * 
		 * Extends SimulationModalBase
		 * 
		 * @author scott.brazelton
		 *
		 */
		public class PerformanceIndicatorModal extends SimulationModalBase {

			/**
			 * Modal Constructor: Instantiates super class
			 */
			public PerformanceIndicatorModal() {
				super(performanceIndicatorModal);
			}

			@FindBy(id = "simulationPerformanceIndicator")
			WebElement performanceIndicatorTextarea;

			/**
			 * Sets the Performance Indicator field with the passed in value
			 * 
			 * @param text
			 */
			public void setPerformanceIndicator(String text) {
				AutomationHelper.printMethodName();
				AutomationHelper.setTextField(performanceIndicatorTextarea,
						text);
			}

			/**
			 * Reads the value currently in the Performance Indicator text
			 * field.
			 * 
			 * @return String - Performance Indicator
			 */
			public String readPerformanceIndicator() {
				AutomationHelper.printMethodName();
				return performanceIndicatorTextarea.getAttribute("value");

			}

			@FindBy(id = "performanceIndicatorRequired")
			WebElement piRequiredLabel;

			/**
			 * Reads Performance Indicator error message (if any)
			 * 
			 * NOTE: returns empty string if no message
			 * 
			 * @return String
			 */
			public String readPerformanceIndicatorErrorMessage() {
				AutomationHelper.printMethodName();
				return readErrorMessageInEditSimulation(piRequiredLabel);
			}
		}

		@FindBy(id = "objectiveModal")
		WebElement objectiveModal;

		/**
		 * Modal Page factory sub class for the Objective modal on My Menu >
		 * Simulations > Edit Simulation > Simulation OOPI page for JMEDSIM
		 * after clicking on "Add Objective" or Edit in Simulation OOPI tab.<br>
		 * (1) Finds a reference to objects on the page <br>
		 * (2) Defines methods to interact with objects on the page.<br>
		 * 
		 * Extends SimulationModalBase
		 * 
		 * @author scott.brazelton
		 *
		 */
		public class ObjectiveModal extends SimulationModalBase {

			/**
			 * Modal Constructor: Instantiates super class
			 */
			public ObjectiveModal() {
				super(objectiveModal);
			}

			@FindBy(id = "simulationObjective")
			WebElement objectiveTextArea;

			/**
			 * Sets the value of the Objective field with the passed in text.
			 * 
			 * @param text
			 */
			public void setObjective(String text) {
				AutomationHelper.printMethodName();
				AutomationHelper.setTextField(objectiveTextArea, text);
			}

			/**
			 * Returns the value currently in the Objective field.
			 * 
			 * @return String - Objective
			 */
			public String readObjective() {
				AutomationHelper.printMethodName();
				return objectiveTextArea.getAttribute("value");
			}

			@FindBy(id = "objectiveRequired")
			WebElement objectiveRequiredLabel;

			/**
			 * Reads Objective error message (if any)
			 * 
			 * NOTE: returns empty string if no message
			 * 
			 * @return String
			 */
			public String readObjectiveErrorMessage() {
				AutomationHelper.printMethodName();
				return readErrorMessageInEditSimulation(objectiveRequiredLabel);
			}
		}

	}

	/**
	 * Page factory for the My Menu > Simulations > Edit Simulation > Personnel
	 * (Required) tab for JMEDSIM.<br>
	 * (1) Finds a reference to objects within the scope of the tab<br>
	 * (2) Defines methods to interact with objects on the tab<br>
	 * 
	 * @author jesse.childress
	 *
	 */
	public class PersonnelTab extends EditSimulationPageFactory {

		@FindBy(linkText = "Add Personnel")
		WebElement addPersonnelLink;

		/**
		 * Clicks the Add Personnel link.
		 */
		public PersonnelModal clickAddPersonnel() {
			AutomationHelper.printMethodName();
			clickPersonnelTab(true);
			addPersonnelLink.click();
			return new PersonnelModal();
		}

		@FindBy(xpath = ".//div[contains(@id,'Personnels')][not(@style)]/table[@class='table table-striped table-hover table-bordered']")
		WebElement personnelsTable;

		/**
		 * Returns a reference to the Personnels table.
		 * 
		 * @return JMEDSimTables - personnelsTable
		 */
		public JMEDSimTables getPersonnelTable() {
			return new JMEDSimTables(personnelsTable);
		}

		/**
		 * Clicks "Edit" in the Personnel Table by Personnel text
		 * 
		 * @param personnel
		 * @return PersonnelModal
		 */
		public PersonnelModal clickEditInPersonnelTableByPersonnel(
				String personnel) {
			getPersonnelTable().clickLinkInRow("Personnel", personnel, "",
					"Edit", false);
			return new PersonnelModal();
		}

		/**
		 * Clicks "Delete" in the Personnel Table by Personnel
		 * 
		 * @param personnel
		 */
		public void clickDeleteInPersonnelTableByPersonnel(String personnel) {
			getPersonnelTable().clickLinkInRow("Personnel", personnel, "",
					"Delete", false);
		}

		@FindBy(id = "personnelModal")
		WebElement personnelModal;

		/**
		 * Modal Page factory sub class for the Personnel modal on My Menu >
		 * Simulations > Edit Simulation page for JMEDSIM after clicking on "Add
		 * Personnel" or Edit in Personnel tab.<br>
		 * (1) Finds a reference to objects on the page <br>
		 * (2) Defines methods to interact with objects on the page.<br>
		 * 
		 * Extends SimulationModalBase
		 * 
		 * @author scott.brazelton
		 *
		 */
		public class PersonnelModal extends SimulationModalBase {

			/**
			 * Modal Constructor: Instantiates super class
			 */
			public PersonnelModal() {
				super(personnelModal);
			}

			@FindBy(id = "personnelId")
			WebElement personnelDropdown;

			/**
			 * Selects a value in the Personnel drop down
			 * 
			 * @param text
			 */
			public void selectPersonnel(String text) {
				AutomationHelper.printMethodName();
				AutomationHelper.selectDropdownItem(personnelDropdown, text);
			}

			/**
			 * Returns the currently selected value in teh Personnel drop down.
			 * 
			 * @return String
			 */
			public String readPersonnelSelected() {
				AutomationHelper.printMethodName();
				return AutomationHelper.readSelectedSubItem(personnelDropdown);
			}

			@FindBy(id = "personnelRequired")
			WebElement personnelRequiredLabel;

			/**
			 * Reads Personnel error message (if any)
			 * 
			 * NOTE: returns empty string if no message
			 * 
			 * @return String
			 */
			public String readPersonnelErrorMessage() {
				AutomationHelper.printMethodName();
				return readErrorMessageInEditSimulation(personnelRequiredLabel);
			}

			@FindBy(id = "personnelQty")
			WebElement qtyTextfield;

			/**
			 * Sets the Qty text field with the passed in text.
			 * 
			 * @param text
			 */
			public void setQty(String text) {
				AutomationHelper.printMethodName();
				AutomationHelper.setTextField(qtyTextfield, text);
			}

			/**
			 * Reads the value of the Qty text field.
			 * 
			 * @return String
			 */
			public String readQty() {
				AutomationHelper.printMethodName();
				return qtyTextfield.getAttribute("value");
			}

			@FindBy(id = "personnelQtyRequired")
			WebElement qtyRequiredLabel;

			/**
			 * Reads Qty error message (if any)
			 * 
			 * NOTE: returns empty string if no message
			 * 
			 * @return String
			 */
			public String readQtyErrorMessage() {
				AutomationHelper.printMethodName();
				return readErrorMessageInEditSimulation(qtyRequiredLabel);
			}

			@FindBy(id = "roleResponsibility")
			WebElement roleResponsibilityTextfield;

			/**
			 * Sets the Role/Responsibility text field with the passed in text.
			 * 
			 * @param text
			 */
			public void setRoleResponsibility(String text) {
				AutomationHelper.printMethodName();
				AutomationHelper.setTextField(roleResponsibilityTextfield,
						text);
			}

			/**
			 * Reads the value in the Role/Responsibility text field.
			 * 
			 * @return String
			 */
			public String readRoleResponsibility() {
				AutomationHelper.printMethodName();
				return roleResponsibilityTextfield.getAttribute("value");
			}

			@FindBy(id = "roleResponsibilityRequired")
			WebElement roleResponsibilityRequiredLabel;

			/**
			 * Reads Role/Responsibility error message (if any)
			 * 
			 * NOTE: returns empty string if no message
			 * 
			 * @return String
			 */
			public String readRoleResponsibilityErrorMessage() {
				AutomationHelper.printMethodName();
				return readErrorMessageInEditSimulation(
						roleResponsibilityRequiredLabel);
			}

		}

	}

	/**
	 * Page factory for the My Menu > Simulations > Edit Simulation >
	 * Prerequsites tab for JMEDSIM.<br>
	 * (1) Finds a reference to objects within the scope of the tab<br>
	 * (2) Defines methods to interact with objects on the tab<br>
	 * 
	 * @author jesse.childress
	 *
	 */
	public class PrerequisitesTab extends EditSimulationPageFactory {

		@FindBy(xpath = "//div[@id='divSimulationPrerequisite']")
		WebElement simulationPrerequisiteTabElements;

		/**
		 * Is the Prerequisites tab elements displayed on the Prerequisites tab
		 * 
		 * @return boolean - true = yes | false = no
		 */
		private boolean isPrerequisitesTabElementsPresent() {
			AutomationHelper.adjustWaitTimeToShort();
			boolean isPresent = simulationPrerequisiteTabElements.isDisplayed();
			AutomationHelper.adjustWaitTimeToNormal();

			return isPresent;
		}

		/**
		 * Waits for Prerequisites tab elements to become available
		 */
		private void waitForPrerequisitesTabElements() {

			waitForPageToLoad();
			AutomationHelper.wait(1);
			while (!isPrerequisitesTabElementsPresent()) {
				AutomationHelper.wait(1);
			}
		}

		@FindBy(linkText = "Terms and Conditions")
		WebElement termsAndConditionsLink;

		/**
		 * Clicks the Terms and Conditions link
		 * 
		 * @return TermsAndConditionsModal
		 */
		public TermsAndConditionsModal clickTermsAndConditions() {
			AutomationHelper.printMethodName();
			termsAndConditionsLink.click();
			return new TermsAndConditionsModal();
		}

		@FindBy(linkText = "Add Prerequisite")
		WebElement addPrerequisiteLink;

		/**
		 * Clicks the Add Prerequisite link.
		 * 
		 * @return PrerequisiteModal
		 */
		public PrerequisiteModal clickAddPrerequisite() {
			AutomationHelper.printMethodName();
			addPrerequisiteLink.click();
			return new PrerequisiteModal();
		}

		@FindBy(xpath = ".//div[contains(@id,'Prerequisite')][not(@style)]/table[@class='table table-striped table-hover table-bordered']")
		WebElement prerequisiteTable;

		/**
		 * Returns a reference to the Prerequisite table.
		 * 
		 * @return JMEDSimTables - Prerequisite table
		 */
		public JMEDSimTables getPrerequisiteTable() {
			return new JMEDSimTables(prerequisiteTable);
		}

		/**
		 * Clicks "Edit" in the Prerequisites Table by Name text
		 * 
		 * @param name
		 * @return PrerequisiteModal
		 */
		public PrerequisiteModal clickEditOnPrerequisiteTableByName(
				String name) {
			AutomationHelper.printMethodName();
			getPrerequisiteTable().clickLinkInRow(name, "Edit");
			return new PrerequisiteModal();
		}

		/**
		 * Clicks "Delete" in the Prerequisites Table by Name text
		 * 
		 * @param name
		 */
		public void clickDeleteInPrerequisitesTableByName(String name) {
			AutomationHelper.printMethodName();
			getPrerequisiteTable().clickLinkInRow(name, "Delete");
		}

		@FindBy(id = "uploadDisclaimerModal")
		WebElement prerequisiteTermsAndConditionModal;

		/**
		 * Modal Page factory sub class for the Terms and Conditions modal on My
		 * Menu > Simulations > Edit Simulation page for JMEDSIM after clicking
		 * on "Terms and Conditions" in Prerequisite tab.<br>
		 * (1) Finds a reference to objects on the page <br>
		 * (2) Defines methods to interact with objects on the page.<br>
		 * 
		 * Extends SimulationModalBase
		 * 
		 * @author scott.brazelton
		 *
		 */
		public class TermsAndConditionsModal extends SimulationModalBase {

			/**
			 * Modal Constructor: Instantiates super class
			 */
			public TermsAndConditionsModal() {
				super(prerequisiteTermsAndConditionModal);
			}

			@FindBy(xpath = ".//div[@class='modal-body text-info']")
			WebElement textBody;

			/**
			 * Reads the terms and conditions in the terms and conditions modal.
			 * <br>
			 * 
			 * @return String
			 */
			public String readTermsAndConditions() {
				AutomationHelper.printMethodName();
				return textBody.getAttribute("innerText");
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void clickClose() {
				AutomationHelper.scrollIntoView(closeButton);
				super.clickClose();
			}

		}

		@FindBy(id = "prerequisiteModal")
		WebElement prerequisiteModal;

		/**
		 * Modal Page factory sub class for the Prerequisite modal on My Menu >
		 * Simulations > Edit Simulation page for JMEDSIM after clicking on "Add
		 * Prerequisite" in Prerequisite tab.<br>
		 * (1) Finds a reference to objects on the page <br>
		 * (2) Defines methods to interact with objects on the page.<br>
		 * 
		 * Extends SimulationModalBase
		 * 
		 * @author jesse.childress
		 *
		 */
		public class PrerequisiteModal extends SimulationModalBase {

			/**
			 * Modal Constructor: Instantiates super class
			 */
			public PrerequisiteModal() {
				super(prerequisiteModal);
			}

			@FindBy(id = "displayFileName")
			WebElement fileNameTextfield;

			/**
			 * Sets the File Name text field with the passed in text.
			 * 
			 * @param text
			 */
			public void setName(String text) {
				AutomationHelper.printMethodName();
				AutomationHelper.setTextField(fileNameTextfield, text);
			}

			/**
			 * Reads the value in the File Name text field.
			 * 
			 * @return String
			 */
			public String readName() {
				AutomationHelper.printMethodName();
				return fileNameTextfield.getAttribute("value");
			}

			@FindBy(id = "displayFileNameRequired")
			WebElement displayFileNameRequiredLabel;

			/**
			 * Reads Name error message (if any)
			 * 
			 * NOTE: returns empty string if no message
			 * 
			 * @return String
			 */
			public String readNameErrorMessage() {
				AutomationHelper.printMethodName();
				return readErrorMessageInEditSimulation(
						displayFileNameRequiredLabel);
			}

			// Instead of clicking browse, we will push the file path to the
			// element directly.
			// @FindBy(id = "fileName")
			// WebElement browseButton;
			//
			// /**
			// * Clicks the browse button.
			// */
			// public void clickBrowse() {
			// AutomationHelper.printMethodName();
			// browseButton.click();
			// }

			@FindBy(id = "fileName")
			WebElement selectFileField;

			/**
			 * Sets the Select File field with the passed in file path. <br>
			 * <i>E.g. C:\Users\jesse.childress\Pictures\Saved Pictures\Sample
			 * Picture - Koala.jpg </i>
			 * 
			 * @param filePath
			 */
			public void selectFile(String filePath) {
				// System.out.println("Filepath: " + filePath);
				// String formattedFilepath =
				// AutomationHelper.escapeStringForRegEx(filePath);
				// System.out.println("Formatted Filepath: " +
				// formattedFilepath);

				selectFileField.sendKeys(filePath);
			}

			@FindBy(id = "actualFileName")
			WebElement selectedFileTextfield;

			/**
			 * Reads the Selected File text field. <br>
			 * <b><i>Note: This only displays when editing a previously saved
			 * prerequisite </i></b>
			 * 
			 * @return String
			 */
			public String readSelectedFile() {
				AutomationHelper.printMethodName();
				return selectedFileTextfield.getAttribute("value");
			}

			@FindBy(id = "fileRequired")
			WebElement fileRequiredLabel;

			/**
			 * Reads Select File error message (if any)
			 * 
			 * NOTE: returns empty string if no message
			 * 
			 * @return String
			 */
			public String readSelectFileErrorMessage() {
				AutomationHelper.printMethodName();
				return readErrorMessageInEditSimulation(fileRequiredLabel);
			}

			@FindBy(id = "fileDescription")
			WebElement descriptionTextarea;

			/**
			 * Sets the Description text area with the passed in text.
			 * 
			 * @param text
			 */
			public void setDescription(String text) {
				AutomationHelper.printMethodName();
				AutomationHelper.setTextField(descriptionTextarea, text);
			}

			/**
			 * Reads the value of the Description text area.
			 * 
			 * @return String
			 */
			public String readDescription() {
				AutomationHelper.printMethodName();
				return descriptionTextarea.getAttribute("value");
			}

			@FindBy(id = "courseName")
			WebElement courseNameTextarea;

			/**
			 * Sets the Course text area with the passed in text.
			 * 
			 * @param text
			 */
			public void setCourse(String text) {
				AutomationHelper.printMethodName();
				AutomationHelper.setTextField(courseNameTextarea, text);
			}

			/**
			 * Reads the value of the Course text area.
			 * 
			 * @return String
			 */
			public String readCourse() {
				AutomationHelper.printMethodName();
				return courseNameTextarea.getAttribute("value");
			}

			@FindBy(id = "courseNameRequired")
			WebElement courseNameRequiredLabel;

			/**
			 * Reads Course error message (if any)
			 * 
			 * NOTE: returns empty string if no message
			 * 
			 * @return String
			 */
			public String readCourseErrorMessage() {
				AutomationHelper.printMethodName();
				return readErrorMessageInEditSimulation(
						courseNameRequiredLabel);
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void clickAdd() {
				AutomationHelper.printMethodName();
				WebDriverWait wait = new WebDriverWait(driver, NORMAL_TIMEOUT);
				wait.until(ExpectedConditions.visibilityOf(addButton));
				addButton.click();
				waitForModalLoaderToCompleteByID("loaderPrerequisiteDiv");
				waitForSavedSuccessfullyModal();
			}

			/**
			 * {@inheritDoc}
			 */
			public void clickSave() {
				AutomationHelper.printMethodName();
				WebDriverWait wait = new WebDriverWait(driver, NORMAL_TIMEOUT);
				wait.until(ExpectedConditions.visibilityOf(saveButton));
				saveButton.click();
				waitForModalLoaderToCompleteByID("loaderPrerequisiteDiv");
				waitForSavedSuccessfullyModal();
			}
		}

	}

	/**
	 * Page factory for the My Menu > Simulations > Edit Simulation > Equipment
	 * (Required) tab for JMEDSIM.<br>
	 * (1) Finds a reference to objects within the scope of the tab<br>
	 * (2) Defines methods to interact with objects on the tab<br>
	 * 
	 * @author scott.brazelton
	 *
	 */
	/**
	 * @author jesse.childress
	 *
	 */
	public class EquipmentTab extends EditSimulationPageFactory {

		/**
		 * Is the specified tab section elements present
		 * 
		 * @param tabSection
		 * @return boolean - true = yes | false = no
		 */
		private boolean isTabSectionElementsPresent(WebElement tabSection) {
			return tabSection.isDisplayed();
		}

		/**
		 * Waits for tab section elements to become available
		 * 
		 * @param tabSection
		 * @param desiredStatus
		 */
		private void waitForTabSectionElements(WebElement tabSection,
				boolean desiredStatus) {

			waitForPageToLoad();
			AutomationHelper.wait(1);
			if (desiredStatus) {
				while (isTabSectionElementsPresent(tabSection)) {
					AutomationHelper.wait(1);
				}
			} else {
				while (!isTabSectionElementsPresent(tabSection)) {
					AutomationHelper.wait(1);
				}
			}
		}

		@FindBy(id = "cbxSimulator")
		WebElement simulatorNoneCheckbox;

		/**
		 * Sets the Simulator "None" check box with the desired status.
		 * 
		 * @param desiredStatus
		 */
		public void setSimulatorNoneCheckbox(boolean desiredStatus) {
			AutomationHelper.printMethodName();
			AutomationHelper.setCheckbox(simulatorNoneCheckbox, desiredStatus);

			waitForTabSectionElements(addSimulatorLink, desiredStatus);

		}

		@FindBy(linkText = "Add Simulator")
		WebElement addSimulatorLink;

		/**
		 * Clicks the Add Simulator link.
		 */
		public EquipmentModal clickAddSimulator() {
			AutomationHelper.printMethodName();
			clickEquipmentTab(true);
			addSimulatorLink.click();
			return new EquipmentModal();
		}

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

		/**
		 * Clicks "Edit" in the Simulator Table by Simulator Name text
		 * 
		 * @param simulatorName
		 * @return EquipmentModal
		 */
		public EquipmentModal clickEditInSimulatorTableBySimulatorName(
				String simulatorName) {
			getSimulatorTable().clickLinkInRow("Simulator Name", simulatorName,
					"", "Edit", false);
			return new EquipmentModal();
		}

		/**
		 * Clicks "Delete" in the Simulator Table by Simulator Name
		 * 
		 * @param simulatorName
		 */
		public void clickDeleteInSimulatorTableBySimulatorName(
				String simulatorName) {
			getSimulatorTable().clickLinkInRow("Simulator Name", simulatorName,
					"", "Delete", false);
		}

		@FindBy(id = "cbxConsumables")
		WebElement consumablesNoneCheckbox;

		/**
		 * Sets the Consumables "None" check box with the desired status.
		 * 
		 * @param desiredStatus
		 */
		public void setConsumablesNoneCheckbox(boolean desiredStatus) {
			AutomationHelper.printMethodName();
			AutomationHelper.setCheckbox(consumablesNoneCheckbox,
					desiredStatus);

			waitForTabSectionElements(addSimulatorLink, desiredStatus);

		}

		@FindBy(linkText = "Add Consumables")
		WebElement addConsumablesLink;

		/**
		 * Clicks the Add Consumables link.
		 */
		public EquipmentModal clickAddConsumables() {
			AutomationHelper.printMethodName();
			clickEquipmentTab(true);
			addConsumablesLink.click();
			return new EquipmentModal();
		}

		@FindBy(xpath = ".//div[contains(@id,'Consumables')][not(@style)]/table[@class='table table-striped table-hover table-bordered']")
		WebElement consumablesTable;

		/**
		 * Returns a reference to the Consumables table.
		 * 
		 * @return JMEDSimTables - Consumables Table
		 */
		public JMEDSimTables getConsumablesTable() {
			return new JMEDSimTables(consumablesTable);
		}

		/**
		 * Clicks "Edit" in the Consumables Table by Item text
		 * 
		 * @param item
		 * @return EquipmentModal
		 */
		public EquipmentModal clickEditInConsumablesTableByItem(String item) {
			getConsumablesTable().clickLinkInRow("Item", item, "", "Edit",
					false);
			return new EquipmentModal();
		}

		/**
		 * Clicks "Delete" in the Consumables Table by Item
		 * 
		 * @param item
		 */
		public void clickDeleteInConsumablesTableByItem(String item) {
			getConsumablesTable().clickLinkInRow("Item", item, "", "Delete",
					false);
		}

		@FindBy(id = "cbxNon-Consumables")
		WebElement nonConsumablesNoneCheckbox;

		/**
		 * Sets the Non-Consumables "None" check box with the desired status.
		 * 
		 * @param desiredStatus
		 */
		public void setNonConsumablesNoneCheckbox(boolean desiredStatus) {
			AutomationHelper.printMethodName();
			AutomationHelper.setCheckbox(nonConsumablesNoneCheckbox,
					desiredStatus);

			waitForTabSectionElements(addNonConsumablesLink, desiredStatus);

		}

		@FindBy(linkText = "Add Non-Consumables")
		WebElement addNonConsumablesLink;

		/**
		 * Clicks the Add Non-Consumables link.
		 */
		public EquipmentModal clickAddNonConsumables() {
			AutomationHelper.printMethodName();
			clickEquipmentTab(true);
			addNonConsumablesLink.click();
			return new EquipmentModal();
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

		/**
		 * Clicks "Edit" in the Non-Consumables Table by Item text
		 * 
		 * @param item
		 * @return EquipmentModal
		 */
		public EquipmentModal clickEditInNonConsumablesTableByItem(
				String item) {
			getSimulatorTable().clickLinkInRow("Item", item, "", "Edit", false);
			return new EquipmentModal();
		}

		/**
		 * Clicks "Delete" in the Non-Consumables Table by Item
		 * 
		 * @param simulatorName
		 */
		public void clickDeleteInNonConsumablesTableByItem(
				String simulatorName) {
			getSimulatorTable().clickLinkInRow("Item", simulatorName, "",
					"Delete", false);
		}

		@FindBy(id = "cbxAV/IT/Communications")
		WebElement avITCommunicationsNoneCheckbox;

		/**
		 * Sets the AV/IT/Communications "None" check box with the desired
		 * status.
		 * 
		 * @param desiredStatus
		 */
		public void setAVITCommunicationsNoneCheckbox(boolean desiredStatus) {
			AutomationHelper.printMethodName();
			AutomationHelper.setCheckbox(avITCommunicationsNoneCheckbox,
					desiredStatus);

			waitForTabSectionElements(addAVITCommunicationsLink, desiredStatus);

		}

		@FindBy(linkText = "Add AVITCommunications")
		WebElement addAVITCommunicationsLink;

		/**
		 * Clicks the Add AV/IT/Communications link.
		 */
		public EquipmentModal clickAddAVITCommunications() {
			AutomationHelper.printMethodName();
			clickEquipmentTab(true);
			addAVITCommunicationsLink.click();
			return new EquipmentModal();
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

		/**
		 * Clicks "Edit" in the AV/IT/Communications Table by Item text
		 * 
		 * @param item
		 * @return EquipmentModal
		 */
		public EquipmentModal clickEditInAVITCommunicationsTableByItem(
				String item) {
			getSimulatorTable().clickLinkInRow("Item", item, "", "Edit", false);
			return new EquipmentModal();
		}

		/**
		 * Clicks "Delete" in the AV/IT/Communications Table by Item
		 * 
		 * @param item
		 */
		public void clickDeleteInAVITCommunicationsTableByItem(String item) {
			getSimulatorTable().clickLinkInRow("Item", item, "", "Delete",
					false);
		}

		/**
		 * Sets a checkbox for the passed in Equipment Category name. <br>
		 * <i> Note: This is a useful method when we add a new Equipment
		 * Category and there are no methods that are currently built out to
		 * support it. The Equipment Category name must be an exact match.</i>
		 * 
		 * @param equipmentCategoryName
		 * @param desiredStatus
		 */
		public void setCheckbox(String equipmentCategoryName,
				boolean desiredStatus) {
			AutomationHelper.printMethodName();
			WebElement checkBox = driver
					.findElement(By.id("cbx" + equipmentCategoryName));
			AutomationHelper.setCheckbox(checkBox, desiredStatus);
			waitForTabSectionElements(checkBox, desiredStatus);
		}

		/**
		 * Clicks a link corresponding to the passed in Equipment Category name.
		 * <br>
		 * <i> Note: This is a useful method when we add a new Equipment
		 * Category and there are no methods that are currently built out to
		 * support it. The Equipment Category name must be an exact match.</i>
		 * 
		 * @param equipmentCategoryName
		 */
		public EquipmentModal clickLink(String equipmentCategoryName) {
			AutomationHelper.printMethodName();
			WebElement link = driver
					.findElement(By.id("add" + equipmentCategoryName));
			clickEquipmentTab(true);
			link.click();
			return new EquipmentModal();
		}

		/**
		 * Returns a reference to a table that belongs to the corresponding
		 * passed in Equipment Category name. <br>
		 * <i> Note: This is a useful method when we add a new Equipment
		 * Category and there are no methods that are currently built out to
		 * support it. The Equipment Category name must be an exact match.</i>
		 * 
		 * @param equipmentCategoryName
		 * @return Table
		 */
		public JMEDSimTables getTable(String equipmentCategoryName) {
			WebElement table = driver.findElement(
					By.xpath(".//div[contains(@id,'" + equipmentCategoryName
							+ "')][not(@style)]/table[@class='table table-striped table-hover table-bordered']"));
			return new JMEDSimTables(table);
		}

		/**
		 * Clicks the Edit link in the table
		 * 
		 * @param item
		 * @return EquipmentModal - clicks Edit and launches modal.
		 */
		public EquipmentModal clickEditInTableByItem(String item) {
			getSimulatorTable().clickLinkInRow("Item", item, "", "Edit", false);
			return new EquipmentModal();
		}

		/**
		 * Clicks the Delete link in the table.
		 * 
		 * @param item
		 */
		public void clickDeleteInTableByItem(String item) {
			getSimulatorTable().clickLinkInRow("Item", item, "", "Delete",
					false);
		}

		@FindBy(id = "equipmentModal")
		WebElement equipmentModal;

		/**
		 * Modal Page factory sub class for the Personnel modal on My Menu >
		 * Simulations > Edit Simulation page for JMEDSIM after clicking on "Add
		 * Personnel" or Edit in Personnel tab.<br>
		 * (1) Finds a reference to objects on the page <br>
		 * (2) Defines methods to interact with objects on the page.<br>
		 * 
		 * Extends SimulationModalBase
		 * 
		 * @author scott.brazelton
		 *
		 */
		public class EquipmentModal extends SimulationModalBase {

			/**
			 * Modal Constructor: Instantiates super class
			 */
			public EquipmentModal() {
				super(equipmentModal);
			}

			@FindBy(id = "simulatorId")
			WebElement simulatorDropdown;

			/**
			 * Selects a value in the Simulator drop down
			 * 
			 * @param text
			 */
			public void selectSimulator(String text) {
				AutomationHelper.printMethodName();
				AutomationHelper.selectDropdownItem(simulatorDropdown, text);
			}

			/**
			 * Returns the currently selected value in the Simulator drop down.
			 * 
			 * @return String
			 */
			public String readSimulatorSelected() {
				AutomationHelper.printMethodName();
				return AutomationHelper.readSelectedSubItem(simulatorDropdown);
			}

			@FindBy(id = "simulatorRequired")
			WebElement simulatorRequiredLabel;

			/**
			 * Reads Simulator error message (if any)
			 * 
			 * NOTE: returns empty string if no message
			 * 
			 * @return String
			 */
			public String readSimulatorErrorMessage() {
				AutomationHelper.printMethodName();
				return readErrorMessageInEditSimulation(simulatorRequiredLabel);
			}

			@FindBy(id = "item")
			WebElement itemTextfield;

			/**
			 * Sets the Item text field with the passed in text.
			 * 
			 * @param text
			 */
			public void setItem(String text) {
				AutomationHelper.printMethodName();
				AutomationHelper.setTextField(itemTextfield, text);
			}

			/**
			 * Reads the value of the Item text field.
			 * 
			 * @return String
			 */
			public String readItem() {
				AutomationHelper.printMethodName();
				return itemTextfield.getAttribute("value");
			}

			@FindBy(id = "itemRequired")
			WebElement itemRequiredLabel;

			/**
			 * Reads Item error message (if any)
			 * 
			 * NOTE: returns empty string if no message
			 * 
			 * @return String
			 */
			public String readItemErrorMessage() {
				AutomationHelper.printMethodName();
				return readErrorMessageInEditSimulation(itemRequiredLabel);
			}

			@FindBy(id = "qty")
			WebElement qtyTextfield;

			/**
			 * Sets the Qty text field with the passed in text.
			 * 
			 * @param text
			 */
			public void setQty(String text) {
				AutomationHelper.printMethodName();
				AutomationHelper.setTextField(qtyTextfield, text);
			}

			/**
			 * Reads the value of the Qty text field.
			 * 
			 * @return String
			 */
			public String readQty() {
				AutomationHelper.printMethodName();
				return qtyTextfield.getAttribute("value");
			}

			@FindBy(id = "equipmentQtyRequired")
			WebElement qtyRequiredLabel;

			/**
			 * Reads Qty error message (if any)
			 * 
			 * NOTE: returns empty string if no message
			 * 
			 * @return String
			 */
			public String readQtyErrorMessage() {
				AutomationHelper.printMethodName();
				return readErrorMessageInEditSimulation(qtyRequiredLabel);
			}

			@FindBy(id = "note")
			WebElement noteTextfield;

			/**
			 * Sets the Note text field with the passed in text.
			 * 
			 * @param text
			 */
			public void setNote(String text) {
				AutomationHelper.printMethodName();
				AutomationHelper.setTextField(noteTextfield, text);
			}

			/**
			 * Reads the value in the Note text field.
			 * 
			 * @return String
			 */
			public String readNote() {
				AutomationHelper.printMethodName();
				return noteTextfield.getAttribute("value");
			}

			@FindBy(id = "noteRequired")
			WebElement noteRequiredLabel;

			/**
			 * Reads Note error message (if any)
			 * 
			 * NOTE: returns empty string if no message
			 * 
			 * @return String
			 */
			public String readNoteErrorMessage() {
				AutomationHelper.printMethodName();
				return readErrorMessageInEditSimulation(noteRequiredLabel);
			}

		}

	}
	/**
	 * Page factory for the My Menu > Simulations > Edit Simulation > Medical
	 * Record(s)/ Patient Documentation tab for JMEDSIM.<br>
	 * (1) Finds a reference to objects within the scope of the tab<br>
	 * (2) Defines methods to interact with objects on the tab<br>
	 * 
	 * @author scott.brazelton
	 *
	 */
	public class MedicalRecordsPatientDocumentationTab
			extends
				EditSimulationPageFactory {

		@FindBy(xpath = "//div[@id='divSimulationMedRecordsPatientDocs']")
		WebElement simulationMedRecordsPatientDocsTabElements;

		/**
		 * Is the Prerequisites tab elements displayed on the Prerequisites tab
		 * 
		 * @return boolean - true = yes | false = no
		 */
		private boolean isMedRecordsPatientDocsTabElementsPresent() {
			AutomationHelper.adjustWaitTimeToShort();
			boolean isPresent = simulationMedRecordsPatientDocsTabElements
					.isDisplayed();
			AutomationHelper.adjustWaitTimeToNormal();

			return isPresent;
		}

		/**
		 * Waits for Prerequisites tab elements to become available
		 */
		private void waitForMedRecordsPatientDocsTabElements() {

			waitForPageToLoad();
			AutomationHelper.wait(1);
			while (!isMedRecordsPatientDocsTabElementsPresent()) {
				AutomationHelper.wait(1);
			}
		}

		@FindBy(linkText = "Terms and Conditions")
		WebElement termsAndConditionsLink;

		/**
		 * Clicks the Terms and Conditions link
		 * 
		 * @return TermsAndConditionsModal
		 */
		public TermsAndConditionsModal clickTermsAndConditions() {
			AutomationHelper.printMethodName();
			termsAndConditionsLink.click();
			return new TermsAndConditionsModal();
		}

		@FindBy(linkText = "Add Medical Record(s)/ Patient Documentation")
		WebElement addMedRecordsPatientDocLink;

		/**
		 * Clicks the Add Medical Record(s)/ Patient Documentation link.
		 * 
		 * @return PrerequisiteModal
		 */
		public MedicalRecordsPatientDocumentationModal clickAddMedicalRecordsPatientDocumentation() {
			AutomationHelper.printMethodName();
			addMedRecordsPatientDocLink.click();
			return new MedicalRecordsPatientDocumentationModal();
		}

		@FindBy(xpath = ".//div[contains(@id,'MedRecordsPatientDocs')][not(@style)]/table[@class='table table-striped table-hover table-bordered']")
		WebElement medRecordsPatientDocTable;

		/**
		 * Returns a reference to the Medical Record(s)/ Patient Documentation
		 * table.
		 * 
		 * @return JMEDSimTables - Medical Record(s)/ Patient Documentation
		 *         table
		 */
		public JMEDSimTables getMedicalRecordsPatientDocumentationTable() {
			return new JMEDSimTables(medRecordsPatientDocTable);
		}

		/**
		 * Clicks "Edit" in the Medical Record(s)/ Patient Documentation Table
		 * by File Name text
		 * 
		 * @param fileName
		 * @return MedicalRecordsPatientDocumentationModal
		 */
		public MedicalRecordsPatientDocumentationModal clickEditOnMedicalRecordsPatientDocTableByFileName(
				String fileName) {
			AutomationHelper.printMethodName();
			getMedicalRecordsPatientDocumentationTable()
					.clickLinkInRow(fileName, "Edit");
			return new MedicalRecordsPatientDocumentationModal();
		}

		/**
		 * Clicks "Delete" in the Medical Record(s) / Patient Documentation
		 * Table by File Name text
		 * 
		 * @param fileName
		 */
		public void clickDeleteInMedicalRecordsPatientDocumentationTableByFileName(
				String fileName) {
			AutomationHelper.printMethodName();
			getMedicalRecordsPatientDocumentationTable()
					.clickLinkInRow(fileName, "Delete");
		}

		@FindBy(id = "uploadDisclaimerModal")
		WebElement medRecordsPatientDocsTermsAndConditionModal;

		/**
		 * Modal Page factory sub class for the Terms and Conditions modal on My
		 * Menu > Simulations > Edit Simulation page for JMEDSIM after clicking
		 * on "Terms and Conditions" in Medical Record(s)/ Patient Documentation
		 * tab.<br>
		 * (1) Finds a reference to objects on the page <br>
		 * (2) Defines methods to interact with objects on the page.<br>
		 * 
		 * Extends SimulationModalBase
		 * 
		 * @author scott.brazelton
		 *
		 */
		public class TermsAndConditionsModal extends SimulationModalBase {

			/**
			 * Modal Constructor: Instantiates super class
			 */
			public TermsAndConditionsModal() {
				super(medRecordsPatientDocsTermsAndConditionModal);
			}

			@FindBy(xpath = ".//div[@class='modal-body text-info']")
			WebElement textBody;

			/**
			 * Reads the terms and conditions in the terms and conditions modal.
			 * <br>
			 * 
			 * @return String
			 */
			public String readTermsAndConditions() {
				AutomationHelper.printMethodName();
				return textBody.getAttribute("innerText");
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void clickClose() {
				AutomationHelper.scrollIntoView(closeButton);
				super.clickClose();
			}

		}

		@FindBy(id = "medRecordsPatientDocsModal")
		WebElement medRecordsPatientDocsModal;

		/**
		 * Modal Page factory sub class for the Prerequisite modal on My Menu >
		 * Simulations > Edit Simulation page for JMEDSIM after clicking on "Add
		 * Medical Record(s)/ Patient Documentation" in Medical Record(s)/
		 * Patient Documentation tab.<br>
		 * (1) Finds a reference to objects on the page <br>
		 * (2) Defines methods to interact with objects on the page.<br>
		 * 
		 * Extends SimulationModalBase
		 * 
		 * @author scott.brazelton
		 *
		 */
		public class MedicalRecordsPatientDocumentationModal
				extends
					SimulationModalBase {

			/**
			 * Modal Constructor: Instantiates super class
			 */
			public MedicalRecordsPatientDocumentationModal() {
				super(medRecordsPatientDocsModal);
			}

			@FindBy(id = "medRecordsPatientDocDisplayFileName")
			WebElement fileNameTextfield;

			/**
			 * Sets the File Name text field with the passed in text.
			 * 
			 * @param text
			 */
			public void setFileName(String text) {
				AutomationHelper.printMethodName();
				AutomationHelper.setTextField(fileNameTextfield, text);
			}

			/**
			 * Reads the value of the File Name text field.
			 * 
			 * @return String
			 */
			public String readFileName() {
				AutomationHelper.printMethodName();
				return fileNameTextfield.getAttribute("value");
			}

			@FindBy(id = "medRecordsPatientDocDisplayFileNameRequired")
			WebElement medRecordsPatientDocDisplayFileNameRequiredLabel;

			/**
			 * Reads File Name error message (if any)
			 * 
			 * NOTE: returns empty string if no message
			 * 
			 * @return String
			 */
			public String readFileNameErrorMessage() {
				AutomationHelper.printMethodName();
				return readErrorMessageInEditSimulation(
						medRecordsPatientDocDisplayFileNameRequiredLabel);
			}

			@FindBy(id = "medRecordsPatientDocFileName")
			WebElement selectFileField;

			/**
			 * Sets the Select File field with the passed in file path. <br>
			 * <i>E.g. C:\Users\jesse.childress\Pictures\Saved Pictures\Sample
			 * Picture - Koala.jpg </i>
			 * 
			 * @param filePath
			 */
			public void selectFile(String filePath) {
				selectFileField.sendKeys(filePath);
			}

			@FindBy(id = "actualFileName")
			WebElement selectedFileTextfield;

			/**
			 * Reads the Selected File text field. <br>
			 * <b><i>Note: This only displays when editing a previously saved
			 * prerequisite </i></b>
			 * 
			 * @return String
			 */
			public String readSelectedFile() {
				AutomationHelper.printMethodName();
				return selectedFileTextfield.getAttribute("value");
			}

			@FindBy(id = "medRecordsPatientDocFileRequired")
			WebElement fileRequiredLabel;

			/**
			 * Reads Select File error message (if any)
			 * 
			 * NOTE: returns empty string if no message
			 * 
			 * @return String
			 */
			public String readSelectFileErrorMessage() {
				AutomationHelper.printMethodName();
				return readErrorMessageInEditSimulation(fileRequiredLabel);
			}

			@FindBy(id = "medRecordsPatientDocFileDescription")
			WebElement fileDescriptionTextarea;

			/**
			 * Sets the File Description text area with the passed in text.
			 * 
			 * @param text
			 */
			public void setFileDescription(String text) {
				AutomationHelper.printMethodName();
				AutomationHelper.setTextField(fileDescriptionTextarea, text);
			}

			/**
			 * Reads the value of the File Description text area.
			 * 
			 * @return String
			 */
			public String readFileDescription() {
				AutomationHelper.printMethodName();
				return fileDescriptionTextarea.getAttribute("value");
			}

			@FindBy(id = "medRecordsPatientDocFileDescriptionRequired")
			WebElement fileDescriptionRequiredLabel;

			/**
			 * Reads File Description error message (if any)
			 * 
			 * NOTE: returns empty string if no message
			 * 
			 * @return String
			 */
			public String readFileDescriptionErrorMessage() {
				AutomationHelper.printMethodName();
				return readErrorMessageInEditSimulation(
						fileDescriptionRequiredLabel);
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void clickAdd() {
				AutomationHelper.printMethodName();
				WebDriverWait wait = new WebDriverWait(driver, NORMAL_TIMEOUT);
				wait.until(ExpectedConditions.visibilityOf(addButton));
				addButton.click();
				waitForModalLoaderToCompleteByID(
						"loaderMedRecordsPatientDocDiv");
				waitForSavedSuccessfullyModal();
			}

			/**
			 * {@inheritDoc}
			 */
			public void clickSave() {
				AutomationHelper.printMethodName();
				WebDriverWait wait = new WebDriverWait(driver, NORMAL_TIMEOUT);
				wait.until(ExpectedConditions.visibilityOf(saveButton));
				saveButton.click();
				waitForModalLoaderToCompleteByID(
						"medRecordsPatientDocFileDescriptionRequired");
				waitForSavedSuccessfullyModal();
			}
		}

	}
	/**
	 * Page factory for the My Menu > Simulations > Edit Simulation > Simulation
	 * Objective / Outcome / Performance Indicators (Required) tab for
	 * JMEDSIM.<br>
	 * (1) Finds a reference to objects within the scope of the tab<br>
	 * (2) Defines methods to interact with objects on the tab<br>
	 * 
	 * @author jesse.childress
	 *
	 */
	public class SimulationEnvironmentTab extends EditSimulationPageFactory {

		@FindBy(id = "spaceRequirementLocation")
		WebElement spaceRequirementLocationTextarea;

		/**
		 * Sets the Space Requirement/Location text area with the passed in
		 * text.
		 * 
		 * @param text
		 */
		public void setSpaceRequirementLocation(String text) {
			AutomationHelper.printMethodName();
			AutomationHelper.setTextField(spaceRequirementLocationTextarea,
					text);
		}

		/**
		 * Reads the value of the Space Requirement/Location text area.
		 * 
		 * @return String
		 */
		public String readSpaceRequirementLocation() {
			AutomationHelper.printMethodName();
			return spaceRequirementLocationTextarea.getAttribute("value");
		}

		@FindBy(id = "spaceRequirementLocationRequired")
		WebElement spaceRequirementLocationRequiredLabel;

		/**
		 * Reads Space Requirement/Location error message (if any)
		 * 
		 * NOTE: returns empty string if no message
		 * 
		 * @return String
		 */
		public String readSpaceRequirementLocationErrorMessage() {
			AutomationHelper.printMethodName();
			return readErrorMessageInEditSimulation(
					spaceRequirementLocationRequiredLabel);
		}

		@FindBy(id = "clinicalOperationalEnvType")
		WebElement clinicalOperationalEnvironmentTypeTextarea;

		/**
		 * Sets the Clinical/Operational Environment Type text area with the
		 * passed in text.
		 * 
		 * @param text
		 */
		public void setClinicalOperationalEnvironmentType(String text) {
			AutomationHelper.printMethodName();
			AutomationHelper.setTextField(
					clinicalOperationalEnvironmentTypeTextarea, text);
		}

		/**
		 * Reads the value of the Clinical/Operational Environment Type text
		 * area.
		 * 
		 * @return String
		 */
		public String readClinicalOperationalEnvironmentType() {
			AutomationHelper.printMethodName();
			return clinicalOperationalEnvironmentTypeTextarea
					.getAttribute("value");
		}

		@FindBy(id = "clinicalOperationalEnvTypeRequired")
		WebElement clinicalOperationalEnvTypeRequiredLabel;

		/**
		 * Reads Clinical/Operational Environment Type error message (if any)
		 * 
		 * NOTE: returns empty string if no message
		 * 
		 * @return String
		 */
		public String readClinicalOperationalEnvironmentTypeErrorMessage() {
			AutomationHelper.printMethodName();
			return readErrorMessageInEditSimulation(
					clinicalOperationalEnvTypeRequiredLabel);
		}

		@FindBy(id = "saveSimulationEnvironment")
		WebElement saveButton;

		/**
		 * Clicks the save button in the Simulation Environment tab (below
		 * Clinical/Operational Environment Type)
		 */
		public void clickSave() {
			AutomationHelper.printMethodName();
			saveButton.click();
			waitForSavedSuccessfullyModal();
		}

		@FindBy(linkText = "Add Furniture / Prop")
		WebElement addFurniturePropLink;

		/**
		 * Clicks the Add Furniture / Prop link.
		 */
		public EnvironmentFurniturePropModal clickAddFurnitureProp() {
			AutomationHelper.printMethodName();
			// Ensure OOPI tab to ensure it's open
			clickSimulationEnvironmentTab(true);
			addFurniturePropLink.click();
			return new EnvironmentFurniturePropModal();
		}

		@FindBy(xpath = ".//div[contains(@id,'divEnvFurnitureProp')][not(@style)]/table[@class='table table-striped table-hover table-bordered']")
		WebElement furniturePropTable;

		/**
		 * Returns a reference to the Furniture / Prop Table.
		 * 
		 * @return JMEDSimTables - Objective Table
		 */
		public JMEDSimTables getFurniturePropTable() {
			return new JMEDSimTables(furniturePropTable);
		}

		/**
		 * Clicks "Edit" in the Furniture / Prop Table by Item text
		 * 
		 * @param item
		 * @return ObjectiveModal
		 */
		public EnvironmentFurniturePropModal clickEditInFurniturePropTableByItem(
				String item) {

			getFurniturePropTable().clickLinkInRow("Item", item, "", "Edit",
					false);

			return new EnvironmentFurniturePropModal();
		}

		/**
		 * Clicks "Delete" in the Furniture / Prop Table by Item text
		 * 
		 * @param item
		 */
		public void clickDeleteInFurniturePropTableByItem(String item) {

			getFurniturePropTable().clickLinkInRow("Item", item, "", "Delete",
					false);

		}

		@FindBy(id = "envFurniturePropModal")
		WebElement envFurniturePropModal;

		/**
		 * Modal Page factory sub class for the Objective modal on My Menu >
		 * Simulations > Edit Simulation > Simulation Environment tab for
		 * JMEDSIM after clicking on "Add Furniture / Prop" or Edit in Furniture
		 * / Prop table.<br>
		 * (1) Finds a reference to objects on the page <br>
		 * (2) Defines methods to interact with objects on the page.<br>
		 * 
		 * Extends SimulationModalBase
		 * 
		 * @author scott.brazelton
		 *
		 */
		public class EnvironmentFurniturePropModal extends SimulationModalBase {

			/**
			 * Modal Constructor: Instantiates super class
			 */
			public EnvironmentFurniturePropModal() {
				super(envFurniturePropModal);
			}

			@FindBy(id = "envFurniturePropItem")
			WebElement itemTextfield;

			/**
			 * Sets the value of the Item field with the passed in text.
			 * 
			 * @param text
			 */
			public void setItem(String text) {
				AutomationHelper.printMethodName();
				AutomationHelper.setTextField(itemTextfield, text);
			}

			/**
			 * Returns the value currently in the Item field.
			 * 
			 * @return String - Item
			 */
			public String readItem() {
				AutomationHelper.printMethodName();
				return itemTextfield.getAttribute("value");
			}

			@FindBy(id = "envFurniturePropItemRequired")
			WebElement itemRequiredLabel;

			/**
			 * Reads Item error message (if any)
			 * 
			 * NOTE: returns empty string if no message
			 * 
			 * @return String
			 */
			public String readItemErrorMessage() {
				AutomationHelper.printMethodName();
				return readErrorMessageInEditSimulation(itemRequiredLabel);
			}

			@FindBy(id = "envFurniturePropItemQty")
			WebElement qtyTextfield;

			/**
			 * Sets the value of the Qty field with the passed in text.
			 * 
			 * @param text
			 */
			public void setQty(String text) {
				AutomationHelper.printMethodName();
				AutomationHelper.setTextField(qtyTextfield, text);
			}

			/**
			 * Returns the value currently in the Qty field.
			 * 
			 * @return String - Item
			 */
			public String readQty() {
				AutomationHelper.printMethodName();
				return qtyTextfield.getAttribute("value");
			}

			@FindBy(id = "envFurniturePropQtyRequired")
			WebElement qtyRequiredLabel;

			/**
			 * Reads Qty error message (if any)
			 * 
			 * NOTE: returns empty string if no message
			 * 
			 * @return String
			 */
			public String readQtyErrorMessage() {
				AutomationHelper.printMethodName();
				return readErrorMessageInEditSimulation(qtyRequiredLabel);
			}

			@FindBy(id = "notesUse")
			WebElement notesUseTextArea;

			/**
			 * Sets the value of the Notes/Use field with the passed in text.
			 * 
			 * @param text
			 */
			public void setNotesUse(String text) {
				AutomationHelper.printMethodName();
				AutomationHelper.setTextField(notesUseTextArea, text);
			}

			/**
			 * Returns the value currently in the Notes/Use field.
			 * 
			 * @return String - Item
			 */
			public String readNotesUse() {
				AutomationHelper.printMethodName();
				return notesUseTextArea.getAttribute("value");
			}

			@FindBy(id = "notesUseRequired")
			WebElement notesUseRequiredLabel;

			/**
			 * Reads Notes/Use error message (if any)
			 * 
			 * NOTE: returns empty string if no message
			 * 
			 * @return String
			 */
			public String readNotesUseErrorMessage() {
				AutomationHelper.printMethodName();
				return readErrorMessageInEditSimulation(notesUseRequiredLabel);
			}
		}

	}

	/**
	 * Page factory for the My Menu > Simulations > Edit Simulation >
	 * Environment Equipment Setup tab for JMEDSIM.<br>
	 * (1) Finds a reference to objects within the scope of the tab<br>
	 * (2) Defines methods to interact with objects on the tab<br>
	 * 
	 * @author scott.brazelton
	 *
	 */
	public class EnvironmentAndEquipmentSetupTab
			extends
				EditSimulationPageFactory {

		@FindBy(xpath = "//div[@id='divSimulationEnvEquipSetup']")
		WebElement environmentAndEquipmentSetupTabElements;

		/**
		 * Is the Environment and Equipment Setup tab elements displayed on the
		 * Environment and Equipment Setup tab
		 * 
		 * @return boolean - true = yes | false = no
		 */
		private boolean isEnvironmentAndEquipmentSetupTabElementsPresent() {
			AutomationHelper.adjustWaitTimeToShort();
			boolean isPresent = environmentAndEquipmentSetupTabElements
					.isDisplayed();
			AutomationHelper.adjustWaitTimeToNormal();

			return isPresent;
		}

		/**
		 * Waits for Environment and Equipment Setup tab elements to become
		 * available
		 */
		private void waitForEnvironmentAndEquipmentSetupTabElements() {

			waitForPageToLoad();
			AutomationHelper.wait(1);
			while (!isEnvironmentAndEquipmentSetupTabElementsPresent()) {
				AutomationHelper.wait(1);
			}
		}

		@FindBy(linkText = "Terms and Conditions")
		WebElement termsAndConditionsLink;

		/**
		 * Clicks the Terms and Conditions link
		 * 
		 * @return TermsAndConditionsModal
		 */
		public TermsAndConditionsModal clickTermsAndConditions() {
			AutomationHelper.printMethodName();
			termsAndConditionsLink.click();
			return new TermsAndConditionsModal();
		}

		@FindBy(linkText = "Add Environment & Equipment Setup")
		WebElement addEnvironmentAndEquipemntSetupLink;

		/**
		 * Clicks the Add Environment & Equipment Setup link.
		 * 
		 * @return EnvironmentAndEquipmentSetupModal
		 */
		public EnvironmentAndEquipmentSetupModal clickAddEnvironmentAndEquipmentSetup() {
			AutomationHelper.printMethodName();
			addEnvironmentAndEquipemntSetupLink.click();
			return new EnvironmentAndEquipmentSetupModal();
		}

		@FindBy(xpath = ".//div[contains(@id,'EnvEquipSetup')][not(@style)]/table[@class='table table-striped table-hover table-bordered']")
		WebElement environmentAndEquipmentSetupTable;

		/**
		 * Returns a reference to the Environment and Equipment Setup table.
		 * 
		 * @return JMEDSimTables - Environment and Equipment Setup table
		 */
		public JMEDSimTables getEnvironmentAndEquipmentSetupTable() {
			return new JMEDSimTables(environmentAndEquipmentSetupTable);
		}

		/**
		 * Clicks "Edit" in the Environment and Equipment Setup Table by Name
		 * text
		 * 
		 * @param name
		 * @return EnvironmentAndEquipmentSetupModal
		 */
		public EnvironmentAndEquipmentSetupModal clickEditOnEnvironmentAndEquipmentSetupByName(
				String name) {
			AutomationHelper.printMethodName();
			getEnvironmentAndEquipmentSetupTable().clickLinkInRow(name, "Edit");
			return new EnvironmentAndEquipmentSetupModal();
		}

		/**
		 * Clicks "Delete" in the Environment and Equipment Setup Table by Name
		 * 
		 * @param name
		 */
		public void clickDeleteInEnvironmentAndEquipmentSetupTableByFileDescription(
				String name) {
			AutomationHelper.printMethodName();
			getEnvironmentAndEquipmentSetupTable().clickLinkInRow(name,
					"Delete");
		}

		@FindBy(id = "uploadDisclaimerModal")
		WebElement envEquipSetupTermsAndConditionModal;

		/**
		 * Modal Page factory sub class for the Terms and Conditions modal on My
		 * Menu > Simulations > Edit Simulation page for JMEDSIM after clicking
		 * on "Terms and Conditions" in Medical Record(s)/ Patient Documentation
		 * tab.<br>
		 * (1) Finds a reference to objects on the page <br>
		 * (2) Defines methods to interact with objects on the page.<br>
		 * 
		 * Extends SimulationModalBase
		 * 
		 * @author scott.brazelton
		 *
		 */
		public class TermsAndConditionsModal extends SimulationModalBase {

			/**
			 * Modal Constructor: Instantiates super class
			 */
			public TermsAndConditionsModal() {
				super(envEquipSetupTermsAndConditionModal);
			}

			@FindBy(xpath = ".//div[@class='modal-body text-info']")
			WebElement textBody;

			/**
			 * Reads the terms and conditions in the terms and conditions modal.
			 * <br>
			 * 
			 * @return String
			 */
			public String readTermsAndConditions() {
				AutomationHelper.printMethodName();
				return textBody.getAttribute("innerText");
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void clickClose() {
				AutomationHelper.scrollIntoView(closeButton);
				super.clickClose();
			}

		}

		@FindBy(id = "envEquipSetupModal")
		WebElement envEquipSetupModal;

		/**
		 * Modal Page factory sub class for the Prerequisite modal on My Menu >
		 * Simulations > Edit Simulation page for JMEDSIM after clicking on "Add
		 * Environment & Equipment Setup" in Environment and Equipment Setup
		 * tab.<br>
		 * (1) Finds a reference to objects on the page <br>
		 * (2) Defines methods to interact with objects on the page.<br>
		 * 
		 * Extends SimulationModalBase
		 * 
		 * @author scott.brazelton
		 *
		 */
		public class EnvironmentAndEquipmentSetupModal
				extends
					SimulationModalBase {

			/**
			 * Modal Constructor: Instantiates super class
			 */
			public EnvironmentAndEquipmentSetupModal() {
				super(envEquipSetupModal);
			}

			@FindBy(id = "displayFileNameEnvEquipSetup")
			WebElement nameTextfield;

			/**
			 * Sets the Name text field with the passed in text.
			 * 
			 * @param text
			 */
			public void setName(String text) {
				AutomationHelper.printMethodName();
				AutomationHelper.setTextField(nameTextfield, text);
			}

			/**
			 * Reads the value of the Name text field.
			 * 
			 * @return String
			 */
			public String readName() {
				AutomationHelper.printMethodName();
				return nameTextfield.getAttribute("value");
			}

			@FindBy(id = "displayFileNameRequiredEnvEquipSetup")
			WebElement nameRequiredLabel;

			/**
			 * Reads Name error message (if any)
			 * 
			 * NOTE: returns empty string if no message
			 * 
			 * @return String
			 */
			public String readNameErrorMessage() {
				AutomationHelper.printMethodName();
				return readErrorMessageInEditSimulation(nameRequiredLabel);
			}

			@FindBy(id = "fileNameEnvEquipSetup")
			WebElement selectFileField;

			/**
			 * Sets the Select File field with the passed in file path. <br>
			 * <i>E.g. C:\Users\jesse.childress\Pictures\Saved Pictures\Sample
			 * Picture - Koala.jpg </i>
			 * 
			 * @param filePath
			 */
			public void selectFile(String filePath) {
				selectFileField.sendKeys(filePath);
			}

			/**
			 * Reads the Selected File text field. <br>
			 * <b><i>Note: This only displays when editing a previously saved
			 * prerequisite </i></b>
			 * 
			 * @return String
			 */
			public String readSelectedFile() {
				AutomationHelper.printMethodName();
				return selectFileField.getAttribute("value");
			}

			@FindBy(id = "fileRequiredEnvEquipSetup")
			WebElement fileRequiredLabel;

			/**
			 * Reads Select File error message (if any)
			 * 
			 * NOTE: returns empty string if no message
			 * 
			 * @return String
			 */
			public String readSelectFileErrorMessage() {
				AutomationHelper.printMethodName();
				return readErrorMessageInEditSimulation(fileRequiredLabel);
			}

			@FindBy(id = "fileDescriptionEnvEquipSetup")
			WebElement fileDescriptionTextarea;

			/**
			 * Sets the File Description text area with the passed in text.
			 * 
			 * @param text
			 */
			public void setDescription(String text) {
				AutomationHelper.printMethodName();
				AutomationHelper.setTextField(fileDescriptionTextarea, text);
			}

			/**
			 * Reads the value of the Description text area.
			 * 
			 * @return String
			 */
			public String readDescription() {
				AutomationHelper.printMethodName();
				return fileDescriptionTextarea.getAttribute("value");
			}

			@FindBy(id = "fileDescriptionRequiredEnvEquipSetup")
			WebElement fileDescriptionRequiredLabel;

			/**
			 * Reads Description error message (if any)
			 * 
			 * NOTE: returns empty string if no message
			 * 
			 * @return String
			 */
			public String readDescriptionErrorMessage() {
				AutomationHelper.printMethodName();
				return readErrorMessageInEditSimulation(
						fileDescriptionRequiredLabel);
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void clickAdd() {
				AutomationHelper.printMethodName();
				WebDriverWait wait = new WebDriverWait(driver, NORMAL_TIMEOUT);
				wait.until(ExpectedConditions.visibilityOf(addButton));
				addButton.click();
				waitForModalLoaderToCompleteByID("loaderEnvEquipSetupDiv");
				waitForSavedSuccessfullyModal();
			}
		}

	}

	/**
	 * Page factory for the My Menu > Simulations > Edit Simulation >
	 * Standardized Patient Script(s) tab for JMEDSIM.<br>
	 * (1) Finds a reference to objects within the scope of the tab<br>
	 * (2) Defines methods to interact with objects on the tab<br>
	 * 
	 * @author scott.brazelton
	 *
	 */
	public class StandardizedPatientScriptsTab
			extends
				EditSimulationPageFactory {

		/**
		 * Is the specified tab section elements present
		 * 
		 * @param tabSection
		 * @return boolean - true = yes | false = no
		 */
		private boolean isTabSectionElementsPresent(WebElement tabSection) {
			return tabSection.isDisplayed();
		}

		/**
		 * Waits for tab section elements to become available
		 * 
		 * @param tabSection
		 * @param desiredStatus
		 */
		private void waitForTabSectionElements(WebElement tabSection,
				boolean desiredStatus) {

			waitForPageToLoad();
			AutomationHelper.wait(1);
			if (desiredStatus) {
				while (isTabSectionElementsPresent(tabSection)) {
					AutomationHelper.wait(1);
				}
			} else {
				while (!isTabSectionElementsPresent(tabSection)) {
					AutomationHelper.wait(1);
				}
			}
		}

		@FindBy(id = "none")
		WebElement scriptsNoneCheckbox;

		/**
		 * Sets the Scripts "None" check box with the desired status.
		 * 
		 * @param desiredStatus
		 */
		public void setScriptsNoneCheckbox(boolean desiredStatus) {
			AutomationHelper.printMethodName();
			AutomationHelper.setCheckbox(scriptsNoneCheckbox, desiredStatus);

			waitForTabSectionElements(scriptsNoneCheckbox, desiredStatus);

		}

		@FindBy(id = "sPCase")
		WebElement spCaseCheckbox;

		/**
		 * Sets the SP Case? check box with the desired status.
		 * 
		 * @param desiredStatus
		 */
		public void setSPCaseCheckbox(boolean desiredStatus) {
			AutomationHelper.printMethodName();
			AutomationHelper.setCheckbox(spCaseCheckbox, desiredStatus);
			waitForSavedSuccessfullyModal();

		}

		@FindBy(linkText = "Add Standardized Patient Script")
		WebElement addStandardizedPatientScriptLink;

		/**
		 * Clicks the Add Standardized Patient Script link.
		 * 
		 * @return StandardizedPatientScriptModal
		 */
		public StandardizedPatientScriptModal clickAddStandardizedPatientScript() {
			AutomationHelper.printMethodName();
			addStandardizedPatientScriptLink.click();
			return new StandardizedPatientScriptModal();
		}

		@FindBy(xpath = ".//div[contains(@id,'SPScript')][not(@style)]/table[@class='table table-striped table-hover table-bordered']")
		WebElement standardizedPatientScriptsTable;

		/**
		 * Returns a reference to the Environment and Equipment Setup table.
		 * 
		 * @return JMEDSimTables - Environment and Equipment Setup table
		 */
		public JMEDSimTables getStandardizedPatientScriptsTable() {
			return new JMEDSimTables(standardizedPatientScriptsTable);
		}

		/**
		 * Clicks "Edit" in the Standardized Patient Script(s) Table by Name
		 * text
		 * 
		 * @param name
		 * @return StandardizedPatientScriptModal
		 */
		public StandardizedPatientScriptModal clickEditOnStandardizedPatientScriptsByScriptName(
				String name) {
			AutomationHelper.printMethodName();
			getStandardizedPatientScriptsTable().clickLinkInRow(name, "Edit");
			return new StandardizedPatientScriptModal();
		}

		/**
		 * Clicks "Delete" in the Standardized Patient Scripts Table by Name
		 * 
		 * @param name
		 */
		public void clickDeleteInStandardizedPatientScriptsTableByName(
				String name) {
			AutomationHelper.printMethodName();
			getStandardizedPatientScriptsTable().clickLinkInRow(name, "Delete");
		}

		@FindBy(id = "sPScriptModal")
		WebElement sPScriptModal;

		/**
		 * Modal Page factory sub class for the Standardized Patient Script(s)
		 * modal on My Menu > Simulations > Edit Simulation page for JMEDSIM
		 * after clicking on "Add Environment & Equipment Setup" in Environment
		 * and Equipment Setup tab.<br>
		 * (1) Finds a reference to objects on the page <br>
		 * (2) Defines methods to interact with objects on the page.<br>
		 * 
		 * Extends SimulationModalBase
		 * 
		 * @author scott.brazelton
		 *
		 */
		public class StandardizedPatientScriptModal
				extends
					SimulationModalBase {

			/**
			 * Modal Constructor: Instantiates super class
			 */
			public StandardizedPatientScriptModal() {
				super(sPScriptModal);
			}

			@FindBy(id = "sPScriptDisplayFileName")
			WebElement sPScriptDisplayFileNameTextfield;

			/**
			 * Sets the Script Name text field with the passed in text.
			 * 
			 * @param text
			 */
			public void setScriptName(String text) {
				AutomationHelper.printMethodName();
				AutomationHelper.setTextField(sPScriptDisplayFileNameTextfield,
						text);
			}

			/**
			 * Reads the value of the Name text field.
			 * 
			 * @return String
			 */
			public String readScriptName() {
				AutomationHelper.printMethodName();
				return sPScriptDisplayFileNameTextfield.getAttribute("value");
			}

			@FindBy(id = "sPScriptDisplayFileNameRequired")
			WebElement scriptNameRequiredLabel;

			/**
			 * Reads Name error message (if any)
			 * 
			 * NOTE: returns empty string if no message
			 * 
			 * @return String
			 */
			public String readScriptNameErrorMessage() {
				AutomationHelper.printMethodName();
				return readErrorMessageInEditSimulation(
						scriptNameRequiredLabel);
			}

			@FindBy(id = "sPScriptFileName")
			WebElement selectFileField;

			/**
			 * Sets the Select File field with the passed in file path. <br>
			 * <i>E.g. C:\Users\jesse.childress\Pictures\Saved Pictures\Sample
			 * Picture - Koala.jpg </i>
			 * 
			 * @param filePath
			 */
			public void selectFile(String filePath) {
				selectFileField.sendKeys(filePath);
			}

			/**
			 * Reads the Selected File text field. <br>
			 * <b><i>Note: This only displays when editing a previously saved
			 * prerequisite </i></b>
			 * 
			 * @return String
			 */
			public String readSelectedFile() {
				AutomationHelper.printMethodName();
				return selectFileField.getAttribute("value");
			}

			@FindBy(id = "sPScriptFileRequired")
			WebElement fileRequiredLabel;

			/**
			 * Reads Select File error message (if any)
			 * 
			 * NOTE: returns empty string if no message
			 * 
			 * @return String
			 */
			public String readSelectFileErrorMessage() {
				AutomationHelper.printMethodName();
				return readErrorMessageInEditSimulation(fileRequiredLabel);
			}

			@FindBy(id = "sPSScriptFileDescription")
			WebElement fileDescriptionTextarea;

			/**
			 * Sets the File Description text area with the passed in text.
			 * 
			 * @param text
			 */
			public void setDescription(String text) {
				AutomationHelper.printMethodName();
				AutomationHelper.setTextField(fileDescriptionTextarea, text);
			}

			/**
			 * Reads the value of the Description text area.
			 * 
			 * @return String
			 */
			public String readDescription() {
				AutomationHelper.printMethodName();
				return fileDescriptionTextarea.getAttribute("value");
			}

			@FindBy(id = "sPScriptFileDescriptionRequired")
			WebElement fileDescriptionRequiredLabel;

			/**
			 * Reads Description error message (if any)
			 * 
			 * NOTE: returns empty string if no message
			 * 
			 * @return String
			 */
			public String readDescriptionErrorMessage() {
				AutomationHelper.printMethodName();
				return readErrorMessageInEditSimulation(
						fileDescriptionRequiredLabel);
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void clickAdd() {
				AutomationHelper.printMethodName();
				WebDriverWait wait = new WebDriverWait(driver, NORMAL_TIMEOUT);
				wait.until(ExpectedConditions.visibilityOf(addButton));
				addButton.click();
				waitForModalLoaderToCompleteByID("loaderSPScriptDiv");
				waitForSavedSuccessfullyModal();
			}
		}

	}

}
