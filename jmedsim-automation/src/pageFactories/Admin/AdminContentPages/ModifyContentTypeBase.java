package pageFactories.Admin.AdminContentPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.ModifyBase;
import utilities.AutomationHelper;

/**
 * Abstract Page factory for Create/Edit "Admin > Content > Content Type" pages
 * that can be found in JMedSIM.<br>
 * (1) Finds a reference to common objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifyBase
 * 
 * @author scott.brazelton
 *
 */
public abstract class ModifyContentTypeBase extends ModifyBase {

	/**
	 * Abstract Create/Edit Page Constructor: Accepts the WebDriver from the
	 * calling page, provides base methods common to create pages, and passes
	 * URL to ModifyPageFactoryBase for further validation.
	 * 
	 * Extends ModifyBase
	 */
	public ModifyContentTypeBase(String regexURL) {
		super(regexURL);
	}

	@FindBy(id = "ContentTypeName")
	WebElement contentTypeTextfield;

	/**
	 * Set text in field "Content Type".
	 * 
	 * @param text
	 */
	public void setContentType(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(contentTypeTextfield, text);
	}

	/**
	 * Reads Content Type text
	 * 
	 * @return String
	 */
	public String readContentType() {
		AutomationHelper.printMethodName();
		return contentTypeTextfield.getAttribute("value");
	}

	/**
	 * Reads Content Type error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readContentTypeErrorMessage() {
		AutomationHelper.printMethodName();
		return readErrorMessageForField(
				contentTypeTextfield.getAttribute("id"));
	}
	
	/**
	 * Clears the Content Type text field.
	 */
	public void clearContentType() {
		AutomationHelper.printMethodName();
		contentTypeTextfield.clear();
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
