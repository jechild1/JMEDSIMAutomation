package pageFactories.Admin.AdminSimulationPages;

import utilities.AutomationHelper;
/**
 * Page factory for the Admin > Simulation > Node Type > Edit for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifyNodeTypeBase
 * 
 * @author jesse.childress
 *
 */
public class EditNodeTypePageFactory extends ModifyNodeTypeBase {
	
	private static String regexURL = BASE_URL + "NodeTypes/Edit.*";
	
	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public EditNodeTypePageFactory() {
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
