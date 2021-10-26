package pageFactories.Admin.AdminSimulationPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.ModifyBase;
import utilities.AutomationHelper;

/**
 * Abstract Page factory for Create/Edit "Admin > Simulation > Training Type"
 * pages that can be found in JMedSIM.<br>
 * (1) Finds a reference to common objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifyBase
 * 
 * @author scott.brazelton
 *
 */
public abstract class ModifyTrainingTypeBase extends ModifyBase {

	/**
	 * Abstract Create/Edit Page Constructor: Accepts the WebDriver from the
	 * calling page, provides base methods common to create pages, and passes
	 * URL to ModifyPageFactoryBase for further validation.
	 * 
	 * Extends ModifyBase
	 */
	public ModifyTrainingTypeBase(String regexURL) {
		super(regexURL);
	}

	@FindBy(id = "TrainingTypeName")
	WebElement trainingTypeTextfield;

	/**
	 * Set text in field "Training Type".
	 * 
	 * @param text
	 */
	public void setTrainingType(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(trainingTypeTextfield, text);
	}

	/**
	 * Reads Training Type text
	 * 
	 * @return String
	 */
	public String readTrainingType() {
		AutomationHelper.printMethodName();
		return trainingTypeTextfield.getAttribute("value");
	}

	/**
	 * Reads Training Type error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readTrainingTypeErrorMessage() {
		AutomationHelper.printMethodName();
		return readErrorMessageForField(
				trainingTypeTextfield.getAttribute("id"));
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
