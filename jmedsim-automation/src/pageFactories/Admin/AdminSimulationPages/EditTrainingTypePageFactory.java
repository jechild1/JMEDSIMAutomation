package pageFactories.Admin.AdminSimulationPages;

import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Simulation > Training Type > Edit for
 * JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifyTrainingTypeBase
 * 
 * @author scott.brazelton
 *
 */
public class EditTrainingTypePageFactory extends ModifyTrainingTypeBase {
	private static String regexURL = BASE_URL + "TrainingTypes/Edit/.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public EditTrainingTypePageFactory() {
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
