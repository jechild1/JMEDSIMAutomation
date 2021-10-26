package pageFactories.Admin.AdminSimulationPages;

import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Simulation > Edit Simulator Programming Platform.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifySimulatorProgrammingPlatformBase
 * 
 * @author jesse.childress
 *
 */
public class EditSimulatorProgrammingPlatformPageFactory extends ModifySimulatorProgrammingPlatformBase{
	
	private static String regexURL = BASE_URL + "SimulatorPlatforms/Edit/.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public EditSimulatorProgrammingPlatformPageFactory() {
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
