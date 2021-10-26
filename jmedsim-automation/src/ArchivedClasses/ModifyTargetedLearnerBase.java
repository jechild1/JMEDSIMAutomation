package ArchivedClasses;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.ModifyBase;
import utilities.AutomationHelper;

/**
 * Abstract Page factory for Create/Edit "Admin > Targeted Learner" pages that
 * can be found in JMedSIM.<br>
 * (1) Finds a reference to common objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifyBase
 * 
 * @author scott.brazelton
 *
 */
public abstract class ModifyTargetedLearnerBase extends ModifyBase {
	
	/**
	 * Abstract Create/Edit Page Constructor: Accepts the WebDriver from the
	 * calling page, provides base methods common to create pages, and passes
	 * URL to ModifyPageFactoryBase for further validation.
	 * 
	 * Extends ModifyBase
	 */
	public ModifyTargetedLearnerBase(String regexURL) {
		super(regexURL);
	}
	
	@FindBy(id = "TargetedLearnerName")
	WebElement targetedLearnerNameTextfield;

	/**
	 * Set text in field "Targeted Learner".
	 * 
	 * @param text
	 */
	public void setTargetedLearner(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(targetedLearnerNameTextfield, text);
	}

	/**
	 * Reads Targeted Learner text
	 * 
	 * @return String
	 */
	public String readTargetedLearner() {
		AutomationHelper.printMethodName();
		return targetedLearnerNameTextfield.getAttribute("value");
	}

	/**
	 * Reads Targeted Learner error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readTargetedLearnerErrorMessage() {
		AutomationHelper.printMethodName();
		return readErrorMessageForField(
				targetedLearnerNameTextfield.getAttribute("id"));
	}

	@FindBy(id = "TargetedLearnerDescription")
	WebElement targetedLearnerDescriptionTextfield;

	/**
	 * Set text in field "Description".
	 * 
	 * @param text
	 */
	public void setDescription(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(targetedLearnerDescriptionTextfield,
				text);
	}

	/**
	 * Reads Description text
	 * 
	 * @return String
	 */
	public String readDescription() {
		AutomationHelper.printMethodName();
		return targetedLearnerDescriptionTextfield.getAttribute("value");
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
		return readErrorMessageForField(
				targetedLearnerDescriptionTextfield.getAttribute("id"));
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
