package pageFactories.Admin.AdminSimulationPages;

import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Simulation > Edit Software Version /OS.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifySimulatorSoftwareVersionOSBase
 * 
 * @author jesse.childress
 *
 */
public class EditSoftwareVersionOSPageFactory extends ModifySimulatorSoftwareVersionOSBase {
	
	private static String regexURL = BASE_URL + "SimulatorSoftwareVersionOes/Edit/.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public EditSoftwareVersionOSPageFactory() {
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
