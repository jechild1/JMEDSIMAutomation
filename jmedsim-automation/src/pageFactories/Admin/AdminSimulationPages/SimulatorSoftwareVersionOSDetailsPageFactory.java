package pageFactories.Admin.AdminSimulationPages;

import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Simulation > Simulator Software Version/OS> Details page for
 * JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends PersonnelDetailsBase
 * 
 * @author jesse.childress
 *
 */
public class SimulatorSoftwareVersionOSDetailsPageFactory extends SimulatorSoftwareVersionOSDetailsBase {
	private static String regexURL = BASE_URL + "SimulatorSoftwareVersionOes/Details/.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public SimulatorSoftwareVersionOSDetailsPageFactory() {
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
