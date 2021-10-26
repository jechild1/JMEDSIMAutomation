package pageFactories.Admin.AdminSimulationPages;

import utilities.AutomationHelper;
/**
 * Page factory for the Admin > Simulation > Node Connector > Edit for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifyNodeConnectorBase
 * 
 * @author jesse.childress
 *
 */
public class EditNodeConnectorPageFactory extends ModifyNodeConnectorBase {
	
	private static String regexURL = BASE_URL + "ConnectorTypes/Edit.*";
	
	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public EditNodeConnectorPageFactory() {
		super(regexURL);
	}

	/**
	 * Clicks the Save button
	 */
	public void clickSave() {
		AutomationHelper.printMethodName();
		clickSubmitTypeButton();
	}

}
