package pageFactories.Admin.AdminContentPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Content > Content > Edit for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifyContentBase
 * 
 * @author scott.brazelton
 *
 */
public class EditContentPageFactory extends ModifyContentBase {
	private static String regexURL = BASE_URL + "Contents/Edit/.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public EditContentPageFactory() {
		super(regexURL);
	}

	/**
	 * Clicks the Save button
	 */
	public void clickSave() {
		AutomationHelper.printMethodName();
		clickSubmitTypeButton();
	}

	@FindBy(id = "ContentTypeId")
	WebElement contentTypeDropdown;

	/**
	 * Select text in "Content Type" dropdown.
	 * 
	 * @param text
	 */
	public void selectContentType(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.selectDropdownItem(contentTypeDropdown, text);
	}

	/**
	 * Reads State selected text
	 * 
	 * @return String
	 */
	public String readContentTypeSelected() {
		AutomationHelper.printMethodName();
		return AutomationHelper.readSelectedSubItem(contentTypeDropdown);
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
		return readErrorMessageForField(contentTypeDropdown.getAttribute("id"));
	}

	@FindBy(id = "ContentPath")
	WebElement contentPathTextfield;

	/**
	 * Set text in field "Content Path".
	 * 
	 * @param text
	 */
	public void setContentPath(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(contentPathTextfield, text);
	}

	/**
	 * Reads Content Path text
	 * 
	 * @return String
	 */
	public String readContentPath() {
		AutomationHelper.printMethodName();
		return contentPathTextfield.getAttribute("value");
	}

	/**
	 * Reads Content Path error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readContentPathErrorMessage() {
		AutomationHelper.printMethodName();
		return readErrorMessageForField(
				contentPathTextfield.getAttribute("id"));
	}

	@FindBy(id = "UpdatedBy")
	WebElement updatedByTextfield;

	/**
	 * Set text in field "Updated By".
	 * 
	 * @param text
	 */
	public void setUpdatedBy(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(updatedByTextfield, text);
	}

	/**
	 * Reads Updated By text
	 * 
	 * @return String
	 */
	public String readUpdatedBy() {
		AutomationHelper.printMethodName();
		return updatedByTextfield.getAttribute("value");
	}

	/**
	 * Reads Updated By error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readUpdatedByErrorMessage() {
		AutomationHelper.printMethodName();
		return readErrorMessageForField(updatedByTextfield.getAttribute("id"));
	}

	@FindBy(id = "UpdatedOn")
	WebElement updatedOnTextfield;

	/**
	 * Set text in field "Updated On".
	 * 
	 * @param text
	 */
	public void setUpdatedOn(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(updatedOnTextfield, text);
	}

	/**
	 * Reads Updated On text
	 * 
	 * @return String
	 */
	public String readUpdatedOn() {
		AutomationHelper.printMethodName();
		return updatedOnTextfield.getAttribute("value");
	}

	/**
	 * Reads Updated On error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readUpdatedOnErrorMessage() {
		AutomationHelper.printMethodName();
		return readErrorMessageForField(updatedOnTextfield.getAttribute("id"));
	}
}
