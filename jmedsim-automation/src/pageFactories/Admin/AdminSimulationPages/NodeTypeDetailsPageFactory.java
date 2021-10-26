package pageFactories.Admin.AdminSimulationPages;

import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Simulation > Node Type > Details page for
 * JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends NodeTypeBase
 * 
 * @author jesse.childress
 *
 */
public class NodeTypeDetailsPageFactory extends NodeTypeBase {

	private static String regexURL = BASE_URL + "NodeTypes/Details.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public NodeTypeDetailsPageFactory() {
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
