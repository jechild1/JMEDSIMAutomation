package pageFactories.Admin.AdminSimulatorPages;

import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Simulator > Simulator > Details page for
 * JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends SimulatorDetailsBase
 * 
 * @author scott.brazelton
 *
 */
public class SimulatorDetailsPageFactory extends SimulatorDetailsBase {
	private static String regexURL = BASE_URL + "Simulators/Details/.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public SimulatorDetailsPageFactory() {
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
