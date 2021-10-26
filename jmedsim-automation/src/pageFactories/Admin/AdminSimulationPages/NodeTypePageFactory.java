package pageFactories.Admin.AdminSimulationPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.IndexBase;
import pageFactories.JMEDSimTables;
import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Simulation > Node Type page for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends IndexBase
 * 
 * @author jesse.childress
 *
 */
public class NodeTypePageFactory extends IndexBase {
	private static String regexURL = BASE_URL + "NodeTypes/Index";
	
	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public NodeTypePageFactory() {
		super(regexURL);
	}
	
	@FindBy (linkText = "Create New Node Type")
	WebElement createNewNodeTypeLink;
	
	/**
	 * Clicks the Create New Node Type link.
	 */
	public void clickCreateNewNodeType() {
		AutomationHelper.printMethodName();
		createNewNodeTypeLink.click();
	}
	
	@FindBy (xpath = "//table[@class='table table-striped table-hover table-bordered']")
	WebElement nodeTypeTable;
	
	/**
	 * Returns a reference to the Note Type table.
	 * 
	 * @return JMEDSimTables
	 */
	public JMEDSimTables getNodeTypeTable() {
		return new JMEDSimTables(nodeTypeTable);
	}

}
