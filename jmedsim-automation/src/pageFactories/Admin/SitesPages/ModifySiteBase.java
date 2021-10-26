package pageFactories.Admin.SitesPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.ModifyBase;
import utilities.AutomationHelper;

/**
 * Abstract Page factory for Create/Edit "Admin > Site" pages that can be found
 * in JMedSIM.<br>
 * (1) Finds a reference to common objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifyBase
 * 
 * @author scott.brazelton
 *
 */
public abstract class ModifySiteBase extends ModifyBase {

	/**
	 * Abstract Create/Edit Page Constructor: Accepts the WebDriver from the
	 * calling page, provides base methods common to create pages, and passes
	 * URL to ModifyPageFactoryBase for further validation.
	 * 
	 * Extends ModifyBase
	 */
	public ModifySiteBase(String regexURL) {
		super(regexURL);
	}

	@FindBy(id = "SiteName")
	WebElement siteNameTextfield;

	/**
	 * Set text in field "Site".
	 * 
	 * @param text
	 */
	public void setSite(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(siteNameTextfield, text);
	}

	/**
	 * Reads Site text
	 * 
	 * @return String
	 */
	public String readSite() {
		AutomationHelper.printMethodName();
		return siteNameTextfield.getAttribute("value");
	}

	/**
	 * Reads Site error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readSiteErrorMessage() {
		AutomationHelper.printMethodName();
		return readErrorMessageForField(siteNameTextfield.getAttribute("id"));
	}

	@FindBy(id = "Address1")
	WebElement address1Textfield;

	/**
	 * Set text in field "Address1".
	 * 
	 * @param text
	 */
	public void setAddress1(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(address1Textfield, text);
	}

	/**
	 * Reads Address1 text
	 * 
	 * @return String
	 */
	public String readAddress1() {
		AutomationHelper.printMethodName();
		return address1Textfield.getAttribute("value");
	}

	/**
	 * Reads Address1 error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readAddress1ErrorMessage() {
		AutomationHelper.printMethodName();
		return readErrorMessageForField(address1Textfield.getAttribute("id"));
	}

	@FindBy(id = "Address2")
	WebElement address2Textfield;

	/**
	 * Set text in field "Address2".
	 * 
	 * @param text
	 */
	public void setAddress2(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(address2Textfield, text);
	}

	/**
	 * Reads Address2 text
	 * 
	 * @return String
	 */
	public String readAddress2() {
		AutomationHelper.printMethodName();
		return address2Textfield.getAttribute("value");
	}

	/**
	 * Reads Address2 error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readAddress2ErrorMessage() {
		AutomationHelper.printMethodName();
		return readErrorMessageForField(address2Textfield.getAttribute("id"));
	}

	@FindBy(id = "City")
	WebElement cityTextfield;

	/**
	 * Set text in field "City".
	 * 
	 * @param text
	 */
	public void setCity(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(cityTextfield, text);
	}

	/**
	 * Reads City text
	 * 
	 * @return String
	 */
	public String readCity() {
		AutomationHelper.printMethodName();
		return cityTextfield.getAttribute("value");
	}

	/**
	 * Reads City error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readCityErrorMessage() {
		AutomationHelper.printMethodName();
		return readErrorMessageForField(cityTextfield.getAttribute("id"));
	}

	@FindBy(id = "State")
	WebElement stateDropdown;

	/**
	 * Select text in "State" dropdown.
	 * 
	 * @param text
	 */
	public void selectState(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.selectDropdownItem(stateDropdown, text);
	}

	/**
	 * Reads State selected text
	 * 
	 * @return String
	 */
	public String readStateSelected() {
		AutomationHelper.printMethodName();
		return AutomationHelper.readSelectedSubItem(stateDropdown);
	}

	/**
	 * Reads State error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readStateErrorMessage() {
		AutomationHelper.printMethodName();
		return readErrorMessageForField(stateDropdown.getAttribute("id"));
	}

	@FindBy(id = "Zipcode")
	WebElement zipcodeTextfield;

	/**
	 * Set text in field "Zipcode".
	 * 
	 * @param text
	 */
	public void setZipcode(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(zipcodeTextfield, text);
	}

	/**
	 * Reads Zipcode text
	 * 
	 * @return String
	 */
	public String readZipcode() {
		AutomationHelper.printMethodName();
		return zipcodeTextfield.getAttribute("value");
	}

	/**
	 * Reads Zipcode error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readZipcodeErrorMessage() {
		AutomationHelper.printMethodName();
		return readErrorMessageForField(zipcodeTextfield.getAttribute("id"));
	}

	@FindBy(id = "ActiveFlag")
	WebElement activeFlagCheckbox;

	/**
	 * Check/uncheck checkbox in "Active Site".
	 * 
	 * @param desiredStatus
	 *            - true = checked | false = unchecked
	 */
	public void setActiveSiteCheckbox(boolean desiredStatus) {
		AutomationHelper.printMethodName();
		AutomationHelper.setCheckbox(activeFlagCheckbox, desiredStatus);
	}

	/**
	 * Returns if Active Site is checked or not
	 * 
	 * @return boolean - true = checked | false = unchecked
	 */
	public boolean isActiveSiteChecked() {
		AutomationHelper.printMethodName();
		return activeFlagCheckbox.isSelected();
	}

	/**
	 * Reads Active Site error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readActiveSiteErrorMessage() {
		AutomationHelper.printMethodName();
		return readErrorMessageForField(activeFlagCheckbox.getAttribute("id"));
	}
}
