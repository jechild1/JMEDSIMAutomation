package pageFactories.Admin.AdminSimulationPages;

import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Simulation > Node Connector > Delete page for
 * JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends NodeConnectorBase
 * 
 * @author jesse.childress
 *
 */
public class DeleteNodeConnectorPageFactory extends NodeConnectorBase {

	private static String regexURL = BASE_URL + "ConnectorTypes/Delete.*";
	
	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public DeleteNodeConnectorPageFactory() {
		super(regexURL);
	}

	/**
	 * Click Delete button
	 */
	public void clickDelete() {
		AutomationHelper.printMethodName();
		clickModifyTypeLinkOrButton();
	}
}
