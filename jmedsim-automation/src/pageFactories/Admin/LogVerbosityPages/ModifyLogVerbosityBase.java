package pageFactories.Admin.LogVerbosityPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.ModifyBase;
import utilities.AutomationHelper;

/**
 * Abstract Page factory for Edit Admin > Log Verbosity" pages that can be found
 * in JMedSIM.<br>
 * (1) Finds a reference to common objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifyBase
 * 
 * @author jesse.childress
 *
 */
public abstract class ModifyLogVerbosityBase extends ModifyBase {

	/**
	 * Abstract Create/Edit Page Constructor: Accepts the WebDriver from the calling
	 * page, provides base methods common to create pages, and passes URL to
	 * ModifyPageFactoryBase for further validation.
	 * 
	 * Extends ModifyBase
	 */
	public ModifyLogVerbosityBase(String regexURL) {
		super(regexURL);
	}

	@FindBy(id = "IsSuccess")
	WebElement successfulCheckbox;

	/**
	 * Returns the checked status of the <i>Successful</i> checkbox.
	 * 
	 * @return boolean - <i>true</i>if checked | <i>false</i> if unchecked.
	 */
	public boolean isSuccessfulChecked() {
		AutomationHelper.printMethodName();
		return successfulCheckbox.isSelected();
	}
	
	/**
	 * Sets the <i>Successful</i> checkbox with the passed in desired status.
	 * 
	 * @param desiredStatus
	 */
	public void setSuccessfulCheckbox(boolean desiredStatus) {
		AutomationHelper.printMethodName();
		AutomationHelper.setCheckbox(successfulCheckbox, desiredStatus);
	}

	@FindBy(id = "IsError")
	WebElement errorsCheckbox;

	/**
	 * Returns the checked status of the <i>Errors</i> checkbox.
	 * 
	 * @return boolean - <i>true</i>if checked | <i>false</i> if unchecked.
	 */
	public boolean isErrorsChecked() {
		AutomationHelper.printMethodName();
		return errorsCheckbox.isSelected();
		
	}
	
	/**
	 * Sets the <i>Errors</i> checkbox with the passed in desired status.
	 * 
	 * @param desiredStatus
	 */
	public void setErrorsCheckbox(boolean desiredStatus) {
		AutomationHelper.printMethodName();
		AutomationHelper.setCheckbox(errorsCheckbox, desiredStatus);
	}

	@FindBy(id = "IsWarn")
	WebElement warningsCheckbox;

	/**
	 * Returns the checked status of the <i>Warnings</i> checkbox.
	 * 
	 * @return boolean - <i>true</i>if checked | <i>false</i> if unchecked.
	 */
	public boolean isWarningsChecked() {
		AutomationHelper.printMethodName();
		return warningsCheckbox.isSelected();
	}

	/**
	 * Sets the <i>Warnings</i> checkbox with the passed in desired status.
	 * 
	 * @param desiredStatus
	 */
	public void setWarningsCheckbox(boolean desiredStatus) {
		AutomationHelper.printMethodName();
		AutomationHelper.setCheckbox(warningsCheckbox, desiredStatus);
	}

	@FindBy(id = "IsInfo")
	WebElement informationalCheckbox;

	/**
	 * Returns the checked status of the <i>Informational</i> checkbox.
	 * 
	 * @return boolean - <i>true</i>if checked | <i>false</i> if unchecked.
	 */
	public boolean isInformationalChecked() {
		AutomationHelper.printMethodName();
		return informationalCheckbox.isSelected();
	}
	
	/**
	 * Sets the <i>Informational</i> checkbox with the passed in desired status.
	 * 
	 * @param desiredStatus
	 */
	public void setInformationalCheckbox(boolean desiredStatus) {
		AutomationHelper.printMethodName();
		AutomationHelper.setCheckbox(informationalCheckbox, desiredStatus);
	}

	@FindBy(id = "IsIDebug")
	WebElement debugCheckbox;

	/**
	 * Returns the checked status of the <i>Debug</i> checkbox.
	 * 
	 * @return boolean - <i>true</i>if checked | <i>false</i> if unchecked.
	 */
	public boolean isDebugChecked() {
		AutomationHelper.printMethodName();
		return debugCheckbox.isSelected();
	}
	
	/**
	 * Sets the <i>Debug</i> checkbox with the passed in desired status.
	 * 
	 * @param desiredStatus
	 */
	public void setDebugCheckbox(boolean desiredStatus) {
		AutomationHelper.printMethodName();
		AutomationHelper.setCheckbox(debugCheckbox, desiredStatus);
	}

}
