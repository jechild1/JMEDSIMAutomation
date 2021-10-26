package pageFactories.Admin.AdminSimulationPages;

import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Simulation > Node Connector > Details page for
 * JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends NodeConnectorBase
 * 
 * @author jesse.childress
 *
 */
public class NodeConnectorDetailsPageFactory extends NodeConnectorBase {

	private static String regexURL = BASE_URL + "ConnectorTypes/Details.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public NodeConnectorDetailsPageFactory() {
		super(regexURL);
	}
	
	/**
	 * Clicks "Edit" link
	 */
	public void clickEdit() {
		AutomationHelper.printMethodName();
		clickModifyTypeLinkOrButton();
	}
}
