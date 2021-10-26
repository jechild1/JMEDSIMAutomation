package pageFactories.Admin.AdminMetadataPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.ModifyBase;
import utilities.AutomationHelper;

/**
 * Abstract Page factory for Create/Edit "Admin > Metadata > Metadata Category"
 * pages that can be found in JMedSIM.<br>
 * (1) Finds a reference to common objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifyBase
 * 
 * @author scott.brazelton
 *
 */
public abstract class ModifyMetadataCategoryBase extends ModifyBase {

	/**
	 * Abstract Create/Edit Page Constructor: Accepts the WebDriver from the
	 * calling page, provides base methods common to create pages, and passes
	 * URL to ModifyPageFactoryBase for further validation.
	 * 
	 * Extends ModifyBase
	 */
	public ModifyMetadataCategoryBase(String regexURL) {
		super(regexURL);
	}

	@FindBy(id = "MetadataCategoryName")
	WebElement metadataCategoryTextfield;

	/**
	 * Set text in field "Metadata Category".
	 * 
	 * @param text
	 */
	public void setMetadataCategory(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(metadataCategoryTextfield, text);
	}

	/**
	 * Reads Metadata Category text
	 * 
	 * @return String
	 */
	public String readMetadataCategory() {
		AutomationHelper.printMethodName();
		return metadataCategoryTextfield.getAttribute("value");
	}
	
	/**
	 * Clears the value currently inside the Metadata Category field.
	 */
	public void clearMetadataCategory() {
		AutomationHelper.printMethodName();
		metadataCategoryTextfield.clear();
	}

	/**
	 * Reads Metadata Category error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readMetadataCategoryErrorMessage() {
		AutomationHelper.printMethodName();
		return readErrorMessageForField(
				metadataCategoryTextfield.getAttribute("id"));
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
