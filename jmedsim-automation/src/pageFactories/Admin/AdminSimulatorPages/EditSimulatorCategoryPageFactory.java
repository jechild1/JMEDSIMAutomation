package pageFactories.Admin.AdminSimulatorPages;

import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Simulator > Simulator Category > Edit for
 * JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifySimulatorCategoryBase
 * 
 * @author scott.brazelton
 *
 */
public class EditSimulatorCategoryPageFactory
		extends
			ModifySimulatorCategoryBase {
	private static String regexURL = BASE_URL + "SimulatorCategories/Edit/.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public EditSimulatorCategoryPageFactory() {
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
