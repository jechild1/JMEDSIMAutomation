package pageFactories.Admin.AdminSimulationPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.ModifyBase;
import utilities.AutomationHelper;

/**
 * Abstract Page factory for Create/Edit "Admin > Simulation > Node Type" pages
 * that can be found in JMedSIM.<br>
 * (1) Finds a reference to common objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifyBase
 * 
 * @author jesse.childress
 *
 */
public abstract class ModifyNodeTypeBase extends ModifyBase{

	/**
	 * Abstract Create/Edit Page Constructor: Accepts the WebDriver from the
	 * calling page, provides base methods common to create pages, and passes
	 * URL to ModifyPageFactoryBase for further validation.
	 * 
	 * Extends ModifyBase
	 */
	public ModifyNodeTypeBase(String regexURL) {
		super(regexURL);
	}

	@FindBy(id = "NodeTypeName")
	WebElement nodeType;

	/**
	 * Set text in field "Node Type".
	 * 
	 * @param text
	 */
	public void setNodeType(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(nodeType, text);
	}
	
	/**
	 * Clears the text from the Node Type field.
	 */
	public void clearNodeType() {
		AutomationHelper.printMethodName();
		nodeType.clear();
	}

	/**
	 * Reads Node Type text
	 * 
	 * @return String
	 */
	public String readNodeType() {
		AutomationHelper.printMethodName();
		return nodeType.getAttribute("value");
	}

	/**
	 * Reads Node Type error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readNodeTypeErrorMessage() {
		AutomationHelper.printMethodName();
		return readErrorMessageForField(nodeType.getAttribute("id"));
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
