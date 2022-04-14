package pageFactories.Admin.AdminSimulationPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.ModifyBase;
import utilities.AutomationHelper;

/**
 * Abstract Page factory for Create/Edit "Admin > Simulation > Personnel" pages
 * that can be found in JMedSIM.<br>
 * (1) Finds a reference to common objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifyBase
 * 
 * @author scott.brazelton
 *
 */
public abstract class ModifyPersonnelBase extends ModifyBase {

	/**
	 * Abstract Create/Edit Page Constructor: Accepts the WebDriver from the
	 * calling page, provides base methods common to create pages, and passes
	 * URL to ModifyPageFactoryBase for further validation.
	 * 
	 * Extends ModifyBase
	 */
	public ModifyPersonnelBase(String regexURL) {
		super(regexURL);
	}

	@FindBy(id = "PersonnelName")
	WebElement personnelTextfield;

	/**
	 * Set text in field "Personnel".
	 * 
	 * @param text
	 */
	public void setPersonnel(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(personnelTextfield, text);
	}
	
	/**
	 * Clears the text from the Personnel field.
	 */
	public void clearPersonnel() {
		AutomationHelper.printMethodName();
		personnelTextfield.clear();
	}

	/**
	 * Reads Personnel text
	 * 
	 * @return String
	 */
	public String readPersonnel() {
		AutomationHelper.printMethodName();
		return personnelTextfield.getAttribute("value");
	}

	/**
	 * Reads Personnel error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readPersonnelErrorMessage() {
		AutomationHelper.printMethodName();
		return readErrorMessageForField(personnelTextfield.getAttribute("id"));
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
		//Allow a moment for the error to appear
		AutomationHelper.waitSeconds(1);
		AutomationHelper.printMethodName();
		return readErrorMessageForField(activeFlagCheckbox.getAttribute("id"));
	}
}
