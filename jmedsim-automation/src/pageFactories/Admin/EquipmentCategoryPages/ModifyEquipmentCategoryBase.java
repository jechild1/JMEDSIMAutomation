package pageFactories.Admin.EquipmentCategoryPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.ModifyBase;
import utilities.AutomationHelper;

/**
 * Abstract Page factory for Create/Edit "Admin > Equipment Category" pages that
 * can be found in JMedSIM.<br>
 * (1) Finds a reference to common objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifyBase
 * 
 * @author scott.brazelton
 *
 */
public abstract class ModifyEquipmentCategoryBase extends ModifyBase {

	/**
	 * Abstract Create/Edit Page Constructor: Accepts the WebDriver from the
	 * calling page, provides base methods common to create pages, and passes
	 * URL to ModifyPageFactoryBase for further validation.
	 * 
	 * Extends ModifyBase
	 */
	public ModifyEquipmentCategoryBase(String regexURL) {
		super(regexURL);
	}

	@FindBy(id = "EquipmentCategoryName")
	WebElement equipmentCategoryNameTextfield;

	/**
	 * Set text in field "Equipment Category".
	 * 
	 * @param text
	 */
	public void setEquipmentCategory(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(equipmentCategoryNameTextfield, text);
	}

	/**
	 * Reads Equipment Category text
	 * 
	 * @return String
	 */
	public String readEquipmentCategory() {
		AutomationHelper.printMethodName();
		return equipmentCategoryNameTextfield.getAttribute("value");
	}

	/**
	 * Reads Equipment Category error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readEquipmentCategoryErrorMessage() {
		AutomationHelper.printMethodName();
		return readErrorMessageForField(
				equipmentCategoryNameTextfield.getAttribute("id"));
	}

	@FindBy(id = "EquipmentCategoryDescription")
	WebElement equipmentCategoryDescriptionTextfield;

	/**
	 * Set text in field "Description".
	 * 
	 * @param text
	 */
	public void setDescription(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(equipmentCategoryDescriptionTextfield,
				text);
	}

	/**
	 * Reads Description text
	 * 
	 * @return String
	 */
	public String readDescription() {
		AutomationHelper.printMethodName();
		return equipmentCategoryDescriptionTextfield.getAttribute("value");
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
				equipmentCategoryDescriptionTextfield.getAttribute("id"));
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
