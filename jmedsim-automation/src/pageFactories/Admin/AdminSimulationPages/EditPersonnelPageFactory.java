package pageFactories.Admin.AdminSimulationPages;

import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Simulation > Personnel > Edit for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifyPersonnelBase
 * 
 * @author jesse.childress
 *
 */
public class EditPersonnelPageFactory extends ModifyPersonnelBase {
	private static String regexURL = BASE_URL + "Personnels/Edit/.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public EditPersonnelPageFactory() {
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
