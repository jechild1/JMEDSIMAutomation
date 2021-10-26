package pageFactories.Admin.AdminSimulatorPages;

import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Simulator > Simulator Category > Delete page for
 * JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends SimulatorCategoryDetailsBase
 * 
 * @author scott.brazelton
 *
 */
public class DeleteSimulatorCategoryPageFactory
		extends
			SimulatorCategoryDetailsBase {
	private static String regexURL = BASE_URL + "SimulatorCategories/Delete/.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public DeleteSimulatorCategoryPageFactory() {
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
