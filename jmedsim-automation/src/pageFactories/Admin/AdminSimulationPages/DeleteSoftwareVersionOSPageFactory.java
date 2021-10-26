package pageFactories.Admin.AdminSimulationPages;

import utilities.AutomationHelper;
/**
 * Page factory for the Admin > Simulation > Software Version/OS> Delete page for
 * JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends PersonnelDetailsBase
 * 
 * @author jesse.childress
 *
 */
public class DeleteSoftwareVersionOSPageFactory extends SimulatorSoftwareVersionOSDetailsBase {
	private static String regexURL = BASE_URL + "SimulatorSoftwareVersionOes/Delete/.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public DeleteSoftwareVersionOSPageFactory() {
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
