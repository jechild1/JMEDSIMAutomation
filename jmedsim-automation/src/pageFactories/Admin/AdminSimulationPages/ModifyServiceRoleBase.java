package pageFactories.Admin.AdminSimulationPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.ModifyBase;
import utilities.AutomationHelper;

/**
 * Abstract Page factory for Create/Edit "Admin > Simulation > Service Role"
 * pages that can be found in JMedSIM.<br>
 * (1) Finds a reference to common objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifyBase
 * 
 * @author scott.brazelton
 *
 */
public abstract class ModifyServiceRoleBase extends ModifyBase {

	/**
	 * Abstract Create/Edit Page Constructor: Accepts the WebDriver from the
	 * calling page, provides base methods common to create pages, and passes
	 * URL to ModifyPageFactoryBase for further validation.
	 * 
	 * Extends ModifyBase
	 */
	public ModifyServiceRoleBase(String regexURL) {
		super(regexURL);
	}

	@FindBy(id = "ServiceCode")
	WebElement serviceCodeTextfield;

	/**
	 * Set text in field "Service Code".
	 * 
	 * @param text
	 */
	public void setServiceCode(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(serviceCodeTextfield, text);
	}
	
	/**
	 * Clears the text out of the Service Code field.
	 */
	public void clearServiceCode() {
		AutomationHelper.printMethodName();
		serviceCodeTextfield.clear();
	}

	/**
	 * Reads Service Code text
	 * 
	 * @return String
	 */
	public String readServiceCode() {
		AutomationHelper.printMethodName();
		return serviceCodeTextfield.getAttribute("value");
	}

	/**
	 * Reads Service Code error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readServiceCodeErrorMessage() {
		AutomationHelper.printMethodName();
		return readErrorMessageForField(
				serviceCodeTextfield.getAttribute("id"));
	}

	@FindBy(id = "ServiceRoleName")
	WebElement serviceRoleNameTextfield;

	/**
	 * Set text in field "Service Role".
	 * 
	 * @param text
	 */
	public void setServiceRole(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(serviceRoleNameTextfield, text);
	}

	/**
	 * Clears the text from the Service Role field.
	 */
	public void clearServiceRole() {
		AutomationHelper.printMethodName();
		serviceRoleNameTextfield.clear();
	}
	/**
	 * Reads Service Role text
	 * 
	 * @return String
	 */
	public String readServiceRole() {
		AutomationHelper.printMethodName();
		return serviceRoleNameTextfield.getAttribute("value");
	}

	/**
	 * Reads Service Role error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readServiceRoleErrorMessage() {
		AutomationHelper.printMethodName();
		return readErrorMessageForField(
				serviceRoleNameTextfield.getAttribute("id"));
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
