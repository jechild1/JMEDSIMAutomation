package pageFactories.HelloUserMenuPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.MenusPageFactory;
import utilities.AutomationHelper;

public abstract class UserProfileBase extends MenusPageFactory {

	/**
	 * Abstract Create & Edit Page Constructor: Accepts the WebDriver from the
	 * calling page, provides base methods common to create & edit pages, and
	 * passes URL to MenusPageFactory for further validation.
	 * 
	 * Extends MenusPageFactory
	 */
	public UserProfileBase(String regexURL) {
		super(regexURL);
	}

	@FindBy(id = "UserId")
	WebElement userIdDropdown;

	/**
	 * Reads the User Id text
	 * 
	 * @return String
	 */
	public String readUserID() {
		AutomationHelper.printMethodName();
		return AutomationHelper.readSelectedSubItem(userIdDropdown);
	}

	@FindBy(id = "DisplayName")
	WebElement displayNameTextfield;

	/**
	 * Sets text in field "Display Name".
	 * 
	 * @param text
	 */
	public void setDisplayName(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(displayNameTextfield, text);
	}

	/**
	 * Reads Display Name text
	 * 
	 * @return String
	 */
	public String readDisplayName() {
		AutomationHelper.printMethodName();
		return displayNameTextfield.getAttribute("value");
	}

	@FindBy(id = "siteId")
	WebElement siteDropdown;

	/**
	 * Select text in "Site" drop down.
	 * 
	 * @param text
	 */
	public void selectSite(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.selectDropdownItem(siteDropdown, text);
	}

	/**
	 * Reads "Site" selected text
	 * 
	 * @return
	 */
	public String readSite() {
		AutomationHelper.printMethodName();
		return AutomationHelper.readSelectedSubItem(siteDropdown);
	}

	@FindBy(id = "SecurityQuestion1Id")
	WebElement securityQuestion1Dropdown;

	/**
	 * Select text in the "Security Question 1" drop down.
	 * 
	 * @param text
	 */
	public void selectSecurityQuestion1(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.selectDropdownItem(securityQuestion1Dropdown, text);
	}

	/**
	 * Reads "Security Question 1" text
	 * 
	 * @return String
	 */
	public String readSecurityQuestion1() {
		AutomationHelper.printMethodName();
		return AutomationHelper.readSelectedSubItem(securityQuestion1Dropdown);
	}

	@FindBy(id = "SecurityAnswer1")
	WebElement securityAnswer1Textfield;

	/**
	 * Set text in field "Security Answer 1".
	 * 
	 * @param text
	 */
	public void setSecurityAnswer1(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(securityAnswer1Textfield, text);
	}

	/**
	 * Reads the "Security Answer 1" text.
	 * 
	 * @return String
	 */
	public String readSecurityAnswer1() {
		AutomationHelper.printMethodName();
		return securityAnswer1Textfield.getAttribute("value");
	}

	@FindBy(id = "SecurityQuestion2Id")
	WebElement securityQuestion2Dropdown;

	/**
	 * Select text in the "Security Question 2" drop down.
	 * 
	 * @param text
	 */
	public void selectSecurityQuestion2(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.selectDropdownItem(securityQuestion2Dropdown, text);
	}

	/**
	 * Reads "Security Question 2" text
	 * 
	 * @return String
	 */
	public String readSecurityQuestion2() {
		AutomationHelper.printMethodName();
		return AutomationHelper.readSelectedSubItem(securityQuestion2Dropdown);
	}

	@FindBy(id = "SecurityAnswer2")
	WebElement securityAnswer2Textfield;

	/**
	 * Set text in field "Security Answer 2".
	 * 
	 * @param text
	 */
	public void setSecurityAnswer2(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(securityAnswer2Textfield, text);
	}

	/**
	 * Reads the "Security Answer 2" text.
	 * 
	 * @return String
	 */
	public String readSecurityAnswer2() {
		AutomationHelper.printMethodName();
		return securityAnswer2Textfield.getAttribute("value");
	}

	@FindBy(id = "SecurityQuestion3Id")
	WebElement securityQuestion3Dropdown;

	/**
	 * Select text in the "Security Question 3" drop down.
	 * 
	 * @param text
	 */
	public void selectSecurityQuestion3(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.selectDropdownItem(securityQuestion3Dropdown, text);
	}

	/**
	 * Reads "Security Question 3" text
	 * 
	 * @return String
	 */
	public String readSecurityQuestion3() {
		AutomationHelper.printMethodName();
		return AutomationHelper.readSelectedSubItem(securityQuestion3Dropdown);
	}

	@FindBy(id = "SecurityAnswer3")
	WebElement securityAnswer3Textfield;

	/**
	 * Set text in field "Security Answer 3".
	 * 
	 * @param text
	 */
	public void setSecurityAnswer3(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(securityAnswer3Textfield, text);
	}

	/**
	 * Reads the "Security Answer 3" text.
	 * 
	 * @return String
	 */
	public String readSecurityAnswer3() {
		AutomationHelper.printMethodName();
		return securityAnswer3Textfield.getAttribute("value");
	}

	@FindBy(id = "AgreedToTerms")
	WebElement agreeToTermsAndConditionsCheckbox;

	/**
	 * Check/uncheck checkbox in "Agree to terms and conditions".
	 * 
	 * @param desiredStatus
	 *            - true = checked | false = unchecked
	 */
	public void setAgreeToTermsAndConditionsCheckbox(boolean desiredStatus) {
		AutomationHelper.printMethodName();
		AutomationHelper.setCheckbox(agreeToTermsAndConditionsCheckbox,
				desiredStatus);;
	}

	@FindBy(linkText = "Terms and Conditions")
	WebElement termsAndConditionsLink;

	/**
	 * Clicks the Terms and COnditions link.
	 */
	public void clickTermsAndConditions() {
		AutomationHelper.printMethodName();
		termsAndConditionsLink.click();
	}

	/**
	 * Reads the checked status of the Terms and Conditions check box.
	 * 
	 * @return boolean - true if checked; false if unchecked.
	 */
	public boolean readTermsAndConditions() {
		AutomationHelper.printMethodName();
		return agreeToTermsAndConditionsCheckbox.isSelected();
	}

	@FindBy(xpath = "//input[@value='Save']")
	WebElement saveButton;

	/**
	 * Clicks the Save button
	 */
	public void clickSaveButton() {
		saveButton.click();
	}

}
