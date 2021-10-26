package pageFactories.Admin.AdminSimulatorPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.ModifyBase;
import utilities.AutomationHelper;

public abstract class ModifySimulatorBase extends ModifyBase {

	/**
	 * Abstract Create/Edit Page Constructor: Accepts the WebDriver from the
	 * calling page, provides base methods common to create pages, and passes
	 * URL to ModifyPageFactoryBase for further validation.
	 * 
	 * Extends ModifyBase
	 */
	public ModifySimulatorBase(String regexURL) {
		super(regexURL);
	}

	@FindBy(id = "SimulatorName")
	WebElement nameTextfield;

	/**
	 * Set text in field "Name".
	 * 
	 * @param text
	 */
	public void setName(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(nameTextfield, text);
	}

	/**
	 * Reads Name text
	 * 
	 * @return String
	 */
	public String readName() {
		AutomationHelper.printMethodName();
		return nameTextfield.getAttribute("value");
	}

	/**
	 * Reads Name error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readNameErrorMessage() {
		AutomationHelper.printMethodName();
		return readErrorMessageForField(nameTextfield.getAttribute("id"));
	}

	@FindBy(id = "SimulatorDescription")
	WebElement descriptionTextfield;

	/**
	 * Set text in field "Description".
	 * 
	 * @param text
	 */
	public void setDescription(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(descriptionTextfield, text);
	}

	/**
	 * Reads Description text
	 * 
	 * @return String
	 */
	public String readDescription() {
		AutomationHelper.printMethodName();
		return descriptionTextfield.getAttribute("value");
	}

	/**
	 * Reads Description error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readDescriptionErrorMessage() {
		AutomationHelper.printMethodName();
		return readErrorMessageForField(
				descriptionTextfield.getAttribute("id"));
	}

	@FindBy(id = "SimulatorCategoryId")
	WebElement simulatorCategoryDropdown;

	/**
	 * Select text in "Simulator Category" dropdown.
	 * 
	 * @param text
	 */
	public void selectSimulatorCategory(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.selectDropdownItem(simulatorCategoryDropdown, text);
	}

	/**
	 * Reads Simulator Category selected text
	 * 
	 * @return String
	 */
	public String readSimulatorCategorySelected() {
		AutomationHelper.printMethodName();
		return AutomationHelper.readSelectedSubItem(simulatorCategoryDropdown);
	}

	/**
	 * Reads Simulator Category error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readSimulatorCategoryErrorMessage() {
		AutomationHelper.printMethodName();
		return readErrorMessageForField(
				simulatorCategoryDropdown.getAttribute("id"));
	}

	@FindBy(id = "ActiveFlag")
	WebElement activeFlagCheckbox;

	/**
	 * Check/uncheck checkbox in "Active".
	 * 
	 * @param desiredStatus
	 *            - true = checked | false = unchecked
	 */
	public void setActiveCheckbox(boolean desiredStatus) {
		AutomationHelper.printMethodName();
		AutomationHelper.setCheckbox(activeFlagCheckbox, desiredStatus);
	}

	/**
	 * Returns if Active is checked or not
	 * 
	 * @return boolean - true = checked | false = unchecked
	 */
	public boolean isActiveChecked() {
		AutomationHelper.printMethodName();
		return activeFlagCheckbox.isSelected();
	}

	/**
	 * Reads Active error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readActiveErrorMessage() {
		AutomationHelper.printMethodName();
		return readErrorMessageForField(activeFlagCheckbox.getAttribute("id"));
	}

}
