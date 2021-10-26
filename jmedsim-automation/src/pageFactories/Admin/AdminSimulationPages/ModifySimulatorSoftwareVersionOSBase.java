package pageFactories.Admin.AdminSimulationPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.ModifyBase;
import utilities.AutomationHelper;

/**
 * Abstract Page factory for Create/Edit "Admin > Simulation > Simulator Software Version/OS" pages
 * that can be found in JMedSIM.<br>
 * (1) Finds a reference to common objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifyBase
 * 
 * @author jesse.childress
 *
 */
public abstract class ModifySimulatorSoftwareVersionOSBase extends ModifyBase{
	
	/**
	 * Abstract Create/Edit Page Constructor: Accepts the WebDriver from the
	 * calling page, provides base methods common to create pages, and passes
	 * URL to ModifyPageFactoryBase for further validation.
	 * 
	 * Extends ModifyBase
	 */
	public ModifySimulatorSoftwareVersionOSBase(String regexURL) {
		super(regexURL);
	}
	
	@FindBy (id="SoftwareVersionOSName")
	WebElement softwareVersionOSTextfield;
	
	/**
	 * Sets text in field "Software Version/OS"
	 * @param text
	 */
	public void setSoftwareVersionOS(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(softwareVersionOSTextfield, text);
	}
	
	/**
	 * Clears the text from the Software Version/OS text field.
	 */
	public void clearSoftwareVersionOS() {
		AutomationHelper.printMethodName();
		softwareVersionOSTextfield.clear();
	}
	
	/**
	 * Returns the value of the Software Version/OS text field
	 * @return String
	 */
	public String readSoftwareVersionOS() {
		AutomationHelper.printMethodName();
		return softwareVersionOSTextfield.getAttribute("value");
	}
	
	/**
	 * Reads Software Version OS error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readSoftwareVersionOSErrorMessage() {
		AutomationHelper.printMethodName();
		return readErrorMessageForField(softwareVersionOSTextfield.getAttribute("id"));
	}
	
	
	
	@FindBy (id="SoftwareVersionOSDescription")
	WebElement descriptionTextfield;
	
	/**
	 * Sets text in field "Description"
	 * @param text
	 */
	public void setDescription(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(descriptionTextfield, text);
	}
	
	/**
	 * Clears the text from the Description text field.
	 */
	public void clearDescription() {
		AutomationHelper.printMethodName();
		descriptionTextfield.clear();
	}
	
	/**
	 * Returns the value of the Description text field
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
		return readErrorMessageForField(descriptionTextfield.getAttribute("id"));
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
		AutomationHelper.wait(1);
		AutomationHelper.printMethodName();
		return readErrorMessageForField(activeFlagCheckbox.getAttribute("id"));
	}

}
