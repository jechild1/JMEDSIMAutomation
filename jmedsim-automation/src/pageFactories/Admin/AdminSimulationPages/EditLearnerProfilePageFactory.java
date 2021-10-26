package pageFactories.Admin.AdminSimulationPages;

import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Simulation > Learner Profile > Edit for
 * JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifyLearnerProfileBase
 * 
 * @author scott.brazelton
 *
 */
public class EditLearnerProfilePageFactory extends ModifyLearnerProfileBase {
	private static String regexURL = BASE_URL + "LearnerProfiles/Edit/.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public EditLearnerProfilePageFactory() {
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
