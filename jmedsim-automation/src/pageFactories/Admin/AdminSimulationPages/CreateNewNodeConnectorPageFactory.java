package pageFactories.Admin.AdminSimulationPages;
/**
 * Page factory for the Admin > Simulation > Node Type > Create New Node Connector
 * Profile for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifyNodeConnectorBase
 * 
 * @author jesse.childress
 *
 */
public class CreateNewNodeConnectorPageFactory extends ModifyNodeConnectorBase{
	
	private static String regexURL = BASE_URL + "ConnectorTypes/Create";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public CreateNewNodeConnectorPageFactory() {
		super(regexURL);
	}

	/**
	 * Clicks the Create button
	 */
	public void clickCreate() {
		clickSubmitTypeButton();
	}



}
