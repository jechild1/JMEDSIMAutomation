package pageFactories.Admin.AdminContentPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.ModifyBase;
import utilities.AutomationHelper;

/**
 * Abstract Page factory for Create/Edit "Admin > Content > Content" pages that
 * can be found in JMedSIM.<br>
 * (1) Finds a reference to common objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifyBase
 * 
 * @author scott.brazelton
 *
 */
public abstract class ModifyContentBase extends ModifyBase {

	/**
	 * Abstract Create/Edit Page Constructor: Accepts the WebDriver from the
	 * calling page, provides base methods common to create pages, and passes
	 * URL to ModifyPageFactoryBase for further validation.
	 * 
	 * Extends ModifyBase
	 */
	public ModifyContentBase(String regexURL) {
		super(regexURL);
	}

	@FindBy(id = "ContentName")
	WebElement contentNameTextfield;

	/**
	 * Set text in field "Content Name".
	 * 
	 * @param text
	 */
	public void setContentName(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(contentNameTextfield, text);
	}

	/**
	 * Reads Content Name text
	 * 
	 * @return String
	 */
	public String readContentName() {
		AutomationHelper.printMethodName();
		return contentNameTextfield.getAttribute("value");
	}

	/**
	 * Reads Content Name error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readContentNameErrorMessage() {
		AutomationHelper.printMethodName();
		return readErrorMessageForField(
				contentNameTextfield.getAttribute("id"));
	}
	
	/**
	 * Clears the value in the <i>Content Name</i> text field.
	 */
	public void clearContentName() {
		AutomationHelper.printMethodName();
		contentNameTextfield.clear();
	}

	@FindBy(id = "ContentDescription")
	WebElement contentDescriptionTextfield;

	/**
	 * Set text in field "Content Description".
	 * 
	 * @param text
	 */
	public void setContentDescription(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(contentDescriptionTextfield, text);
	}

	/**
	 * Reads Content Description text
	 * 
	 * @return String
	 */
	public String readContentDescription() {
		AutomationHelper.printMethodName();
		return contentDescriptionTextfield.getAttribute("value");
	}

	/**
	 * Reads Content Description error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readContentDescriptionErrorMessage() {
		AutomationHelper.printMethodName();
		return readErrorMessageForField(
				contentDescriptionTextfield.getAttribute("id"));
	}
	
	/**
	 * Clears the value in the <i>Content Description</i> text field.
	 */
	public void clearContentDescription() {
		AutomationHelper.printMethodName();
		contentDescriptionTextfield.clear();
	}

	@FindBy(id = "Metadata")
	WebElement metadataTextfield;

	/**
	 * Set text in field "Metadata".
	 * 
	 * @param text
	 */
	public void setMetadata(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(metadataTextfield, text);
	}

	/**
	 * Reads Metadata text
	 * 
	 * @return String
	 */
	public String readMetadata() {
		AutomationHelper.printMethodName();
		return metadataTextfield.getAttribute("value");
	}

	/**
	 * Reads Metadata error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readMetadataErrorMessage() {
		AutomationHelper.printMethodName();
		return readErrorMessageForField(metadataTextfield.getAttribute("id"));
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
