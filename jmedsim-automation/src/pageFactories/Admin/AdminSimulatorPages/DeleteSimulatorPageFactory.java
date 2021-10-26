package pageFactories.Admin.AdminSimulatorPages;

import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Simulator > Simulator > Delete page for
 * JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends SimulatorDetailsBase
 * 
 * @author scott.brazelton
 *
 */
public class DeleteSimulatorPageFactory extends SimulatorDetailsBase {
	private static String regexURL = BASE_URL + "Simulators/Delete/.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public DeleteSimulatorPageFactory() {
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
