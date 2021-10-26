package pageFactories.Admin.AdminSimulationPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.ModifyBase;
import utilities.AutomationHelper;

/**
 * Abstract Page factory for Create/Edit "Admin > Simulation > Node Connector" pages
 * that can be found in JMedSIM.<br>
 * (1) Finds a reference to common objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifyBase
 * 
 * @author jesse.childress
 *
 */
public abstract class ModifyNodeConnectorBase extends ModifyBase{
	
	/**
	 * Abstract Create/Edit Page Constructor: Accepts the WebDriver from the calling
	 * page, provides base methods common to create pages, and passes URL to
	 * ModifyPageFactoryBase for further validation.
	 * 
	 * Extends ModifyBase
	 */
	public ModifyNodeConnectorBase(String regexURL) {
		super(regexURL);
	}

	@FindBy(id = "ConnectorTypeName")
	WebElement nodeConnectorTextfield;

	/**
	 * Set text in field "Node Connector".
	 * 
	 * @param text
	 */
	public void setNodeConnector(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(nodeConnectorTextfield, text);
	}
	
	/**
	 * Reads the value currently in the Node Connector text field.
	 * @return String
	 */
	public String readNodeConnector() {
		AutomationHelper.printMethodName();
		return nodeConnectorTextfield.getAttribute("value");
	}
	
	/**
	 * Reads Node Connector error message (if any)
	 * @return String
	 */
	public String readNodeConnectorErrorMessage() {
		AutomationHelper.printMethodName();
		return readErrorMessageForField(nodeConnectorTextfield.getAttribute("id"));
	}
	
	
	/**
	 * Clears the text from the Node Connector field.
	 */
	public void clearNodeConnector() {
		AutomationHelper.printMethodName();
		nodeConnectorTextfield.clear();
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
	
	@FindBy (xpath = "//span [@class='text-danger field-validation-error']")
	WebElement errorTextLabel;
	
	/**
	 * Reads the value of the Node Connector error text.
	 * 
	 * @return String
	 */
	public String readErrorText() {
		AutomationHelper.printMethodName();
		return errorTextLabel.getText();
	}

}
