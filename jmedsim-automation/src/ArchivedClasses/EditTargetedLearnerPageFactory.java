package ArchivedClasses;

import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Targeted Learners > Edit for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifyTargetedLearnerBase
 * 
 * @author scott.brazelton
 *
 */
public class EditTargetedLearnerPageFactory extends ModifyTargetedLearnerBase {
	private static String regexURL = BASE_URL + "TargetedLearners/Edit/.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public EditTargetedLearnerPageFactory() {
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
