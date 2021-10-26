package pageFactories.Admin.AdminSimulationPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.IndexBase;
import pageFactories.JMEDSimTables;
import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Simulation > Node Connector page for
 * JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends IndexBase
 * 
 * @author jesse.childress
 *
 */
public class NodeConnectorPageFactory extends IndexBase {

	private static String regexURL = BASE_URL + "ConnectorTypes/Index";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public NodeConnectorPageFactory() {
		super(regexURL);
	}

	@FindBy(linkText = "Create New Node Connector")
	WebElement createNewNodeConnectorLink;

	/**
	 * Clicks the Create New Node Connector link.
	 */
	public void clickCreateNewNodeConnector() {
		AutomationHelper.printMethodName();
		createNewNodeConnectorLink.click();
	}

	@FindBy(xpath = "//table[@class='table table-striped table-hover table-bordered']")
	WebElement nodeConnectorTable;

	/**
	 * Returns a reference to the Node Connector table.
	 * 
	 * @return JMEDSimTables
	 */
	public JMEDSimTables getNodeConnectorTable() {
		return new JMEDSimTables(nodeConnectorTable);
	}

}
