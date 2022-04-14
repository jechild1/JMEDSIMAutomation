package pageFactories.Admin.AdminSimulationPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.ModifyBase;
import utilities.AutomationHelper;

/**
 * Abstract Page factory for Create/Edit "Admin > Simulation > Simulator Initial
 * Physiologic Parameters Template" pages that can be found in JMedSIM.<br>
 * (1) Finds a reference to common objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifyBase
 * 
 * @author jesse.childress
 *
 */
public abstract class ModifyInitialPhysiologicParametersTemplateBase extends ModifyBase {

	/**
	 * Abstract Create/Edit Page Constructor: Accepts the WebDriver from the calling
	 * page, provides base methods common to create pages, and passes URL to
	 * ModifyPageFactoryBase for further validation.
	 * 
	 * Extends ModifyBase
	 */
	public ModifyInitialPhysiologicParametersTemplateBase(String regexURL) {
		super(regexURL);
	}

	@FindBy(id = "DisplayFileName")
	WebElement templateFileNameTextfield;

	/**
	 * Sets the text in field <i>Template File Name </i>
	 * 
	 * @param text
	 */
	public void setTemplateFileName(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(templateFileNameTextfield, text);
	}

	/**
	 * Clears the value of the Template Field name
	 */
	public void clearTemplateFileName() {
		AutomationHelper.printMethodName();
		templateFileNameTextfield.clear();
	}

	/**
	 * Returns the value of the <i>Template File Name</i> text field.
	 * 
	 * @return String
	 */
	public String readTemplateFileName() {
		AutomationHelper.printMethodName();
		return templateFileNameTextfield.getAttribute("value");
	}
	
	/**
	 * Reads <i>Template File Name</i>  error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readTemplateFileNameErrorMessage() {
		AutomationHelper.printMethodName();
		return readErrorMessageForField(templateFileNameTextfield.getAttribute("id"));
	}
	
	@FindBy (id="ActualFileName")
	WebElement actualFileNameTextField;
	
	/**
	 * Sets the text in field <i>Actual File Name </i>
	 * 
	 * @param text
	 */
	public void setActualFileName(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(actualFileNameTextField, text);
	}

	/**
	 * Returns the value of the <i>Actual File Name</i> text field.
	 * 
	 * @return String
	 */
	public String readActualFileName() {
		AutomationHelper.printMethodName();
		return actualFileNameTextField.getAttribute("value");
	}
	
	@FindBy (id="FileDescription")
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
		AutomationHelper.waitSeconds(1);
		AutomationHelper.printMethodName();
		return readErrorMessageForField(activeFlagCheckbox.getAttribute("id"));
	}
	
	
	
	
	
	
	
	

}
