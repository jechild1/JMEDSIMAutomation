package pageFactories.Admin.AdminSimulationPages;

import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Simulation > Personnels > Delete page for
 * JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends PersonnelDetailsBase
 * 
 * @author jesse.childress
 *
 */
public class DeletePersonnelPageFactory extends PersonnelDetailsBase {
	private static String regexURL = BASE_URL + "Personnels/Delete/.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public DeletePersonnelPageFactory() {
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
