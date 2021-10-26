package pageFactories.MyMenuPages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.ModifyBase;
import utilities.AutomationHelper;

/**
 * Abstract page factory for Create/Edit "My Menu > Simulations" pages that can
 * be found in JMedSIM<br>
 * (1) Finds a reference to common objects on the page.<br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifyBase
 * 
 * @author jesse.childress
 *
 */
public abstract class ModifySimulationBase extends ModifyBase {

	/**
	 * Abstract Create/Edit Page Constructor: Accepts the WebDriver from the
	 * calling page, provides base methods common to create pages, and passes
	 * URL to ModifyBase for further validation.
	 * 
	 * @param regexURL
	 */
	public ModifySimulationBase(String regexURL) {
		super(regexURL);
	}

	// case insensitive ID search
	@FindBy(css = "input[id='SimulationTitle' i]")
	WebElement titleTextfield;

	/**
	 * Sets the value of the Title text field.
	 * 
	 * @param text
	 */
	public void setTitle(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(titleTextfield, text);
	}

	/**
	 * Reads the value currently in the Title text field.
	 * 
	 * @return String
	 */
	public String readTitle() {
		AutomationHelper.printMethodName();
		return titleTextfield.getAttribute("value");
	}

	@FindBy(id = "simulationTitleRequired")
	List<WebElement> titleRequiredLabel;

	/**
	 * Reads Title error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readTitleErrorMessage() {
		AutomationHelper.printMethodName();
		String error = readErrorMessageForField(
				titleTextfield.getAttribute("id"));

		if (error.equals("")
				&& AutomationHelper.isWebElementOnPage(titleRequiredLabel)) {
			error = readErrorMessageInEditSimulation(titleRequiredLabel.get(0));
		}

		return error;
	}

	// case insensitive ID search
	@FindBy(css = "input[id='simulationDurationHour' i]")
	WebElement durationHoursTextfield;

	/**
	 * Sets the value of the Duration text field.
	 * 
	 * @param text
	 */
	public void setDurationHours(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(durationHoursTextfield, text);
	}

	/**
	 * Reads the value currently in the Duration text field.
	 * 
	 * @return String
	 */
	public String readDurationHours() {
		AutomationHelper.printMethodName();
		return durationHoursTextfield.getAttribute("value");
	}

	// case insensitive ID search
	@FindBy(css = "input[id='simulationDurationMinute' i]")
	WebElement durationMinuteTextfield;

	/**
	 * Sets the value of the Duration text field.
	 * 
	 * @param text
	 */
	public void setDurationMinutes(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(durationMinuteTextfield, text);
	}

	/**
	 * Reads the value currently in the Duration minutes text field.
	 * 
	 * @return String
	 */
	public String readDurationMinutes() {
		AutomationHelper.printMethodName();
		return durationMinuteTextfield.getAttribute("value");
	}

	@FindBy(id = "simulationDurationRequired")
	List<WebElement> durationRequiredLabel;

	/**
	 * Reads Duration error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readDurationErrorMessage() {
		AutomationHelper.printMethodName();
		String error = readErrorMessageForField(
				durationHoursTextfield.getAttribute("id"));

		if (error.equals("")
				&& AutomationHelper.isWebElementOnPage(durationRequiredLabel)) {
			error = readErrorMessageInEditSimulation(
					durationRequiredLabel.get(0));
		}

		return error;
	}

	// case insensitive ID search
	@FindBy(css = "select[id='Author' i]")
	WebElement authorDropdown;

	/**
	 * Selects a value from the Author Dropdown.
	 * 
	 * @param text
	 */
	public void selectAuthor(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.selectDropdownItem(authorDropdown, text);
	}

	/**
	 * Reads the value currently in the Author Dropdown field.
	 * 
	 * @return String
	 */
	public String readAuthorSelected() {
		AutomationHelper.printMethodName();
		return AutomationHelper.readSelectedSubItem(authorDropdown);
	}

	@FindBy(id = "simulationAuthorRequired")
	List<WebElement> authorRequiredLabel;

	/**
	 * Reads Author error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readAuthorErrorMessage() {
		AutomationHelper.printMethodName();
		String error = readErrorMessageForField(
				authorDropdown.getAttribute("id"));

		if (error.equals("")
				&& AutomationHelper.isWebElementOnPage(authorRequiredLabel)) {
			error = readErrorMessageInEditSimulation(
					authorRequiredLabel.get(0));
		}

		return error;
	}

	// case insensitive ID search
	@FindBy(css = "select[id='SiteId' i]")
	WebElement siteDropdown;

	/**
	 * Selects a value for the Site.
	 * 
	 * @param text
	 */
	public void selectSite(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.selectDropdownItem(siteDropdown, text);
	}

	/**
	 * Reads the currently selected value of the Site drop down.
	 * 
	 * @return String
	 */
	public String readSiteSelected() {
		AutomationHelper.printMethodName();
		return AutomationHelper.readSelectedSubItem(siteDropdown);
	}

	/**
	 * Reads Site (Content Information) error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readSiteErrorMessage() {
		AutomationHelper.printMethodName();
		return readErrorMessageForField(siteDropdown.getAttribute("id"));
	}

	// case insensitive ID search
	@FindBy(css = "input[id='ContentKeywords' i]")
	WebElement contentKeywordsTextfield;

	/**
	 * Sets the value for the Content Keywords text field.
	 * 
	 * @param text
	 */
	public void setContentKeywords(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(contentKeywordsTextfield, text);
	}

	/**
	 * Reads the value of the Content Keywords text field.
	 * 
	 * @return String
	 */
	public String readContentKeywords() {
		AutomationHelper.printMethodName();
		return contentKeywordsTextfield.getAttribute("value");
	}

	@FindBy(id = "simulationContentKeywordsRequired")
	List<WebElement> contentKeywordsRequiredLabel;

	/**
	 * Reads Content Keywords error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readContentKeywordsErrorMessage() {
		AutomationHelper.printMethodName();
		String error = readErrorMessageForField(
				contentKeywordsTextfield.getAttribute("id"));

		if (error.equals("") && AutomationHelper
				.isWebElementOnPage(contentKeywordsRequiredLabel)) {
			error = readErrorMessageInEditSimulation(
					contentKeywordsRequiredLabel.get(0));
		}

		return error;
	}

	// case insensitive ID search
	@FindBy(css = "select[id='TrainingTypeId' i]")
	WebElement trainingTypeDropdown;

	/**
	 * Selects a value from the Training Type drop down.
	 * 
	 * @param text
	 */
	public void selectTrainingType(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.selectDropdownItem(trainingTypeDropdown, text);
	}

	/**
	 * Reads the value currently in the Training Type Dropdown field.
	 * 
	 * @return String
	 */
	public String readTrainingTypeSelected() {
		AutomationHelper.printMethodName();
		return AutomationHelper.readSelectedSubItem(trainingTypeDropdown);
	}

	/**
	 * Reads Training Type error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readTrainingTypeErrorMessage() {
		AutomationHelper.printMethodName();
		return readErrorMessageForField(
				trainingTypeDropdown.getAttribute("id"));
	}

	// case insensitive ID search
	@FindBy(css = "input[id='ActiveFlag' i]")
	WebElement activeCheckbox;

	/**
	 * Sets the Active check box with the passed in desired status
	 * 
	 * @param desiredStatus
	 */
	public void setActiveCheckbox(boolean desiredStatus) {
		AutomationHelper.printMethodName();
		AutomationHelper.setCheckbox(activeCheckbox, desiredStatus);
	}

	/**
	 * Returns if Active is checked or not
	 * 
	 * @return boolean - true = checked | false = unchecked
	 */
	public boolean isActiveChecked() {
		AutomationHelper.printMethodName();
		return activeCheckbox.isSelected();
	}

	/**
	 * Click the Save button on the Edit Simulation page.
	 */
	public void clickSave() {
		AutomationHelper.printMethodName();
		clickSubmitTypeButton();
	}

	/**
	 * Generic protected method to read an error message for a field
	 * 
	 * NOTE: returns an empty string if span not found
	 * 
	 * @param field
	 * @return String
	 */
	private String readErrorMessageInEditSimulation(WebElement field) {
		return field.getAttribute("style").contains("display: inline-block;")
				? field.getText()
				: "";
	}
}
