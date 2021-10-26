package pageFactories.Admin.AdminSimulationPages;

import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Simulation > Node Type > Delete page for
 * JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends NodeTypeBase
 * 
 * @author jesse.childress
 *
 */
public class DeleteNodeTypePageFactory extends NodeTypeBase{
	
	private static String regexURL = BASE_URL + "NodeTypes/Delete.*";
	
	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public DeleteNodeTypePageFactory() {
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
