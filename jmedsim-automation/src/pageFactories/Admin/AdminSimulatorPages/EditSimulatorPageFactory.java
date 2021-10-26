package pageFactories.Admin.AdminSimulatorPages;

import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Simulator > Simulator > Edit for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifySimulatorBase
 * 
 * @author scott.brazelton
 *
 */
public class EditSimulatorPageFactory extends ModifySimulatorBase {
	private static String regexURL = BASE_URL + "Simulators/Edit/.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public EditSimulatorPageFactory() {
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
