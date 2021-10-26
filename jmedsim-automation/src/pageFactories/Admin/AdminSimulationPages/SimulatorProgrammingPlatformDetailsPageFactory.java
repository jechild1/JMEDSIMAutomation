package pageFactories.Admin.AdminSimulationPages;

import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Simulation > Simulator Programming Platform > Details page for
 * JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends SimulatorProgrammingPlatformDetailsBase
 * 
 * @author jesse.childress
 *
 */
public class SimulatorProgrammingPlatformDetailsPageFactory extends SimulatorProgrammingPlatformDetailsBase {
	
	private static String regexURL = BASE_URL + "SimulatorPlatforms/Details/.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public SimulatorProgrammingPlatformDetailsPageFactory() {
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
