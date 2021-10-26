package ArchivedClasses;

/**
 * Page factory for the Admin > Targeted Learner > Create new Targeted Learner
 * for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifyTargetedLearnerBase
 * 
 * @author scott.brazelton
 *
 */
public class CreateNewTargetedLearnerPageFactory
		extends
			ModifyTargetedLearnerBase {
	private static String regexURL = BASE_URL + "TargetedLearners/Create";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public CreateNewTargetedLearnerPageFactory() {
		super(regexURL);
	}

	/**
	 * Clicks the Create button
	 */
	public void clickCreate() {
		clickSubmitTypeButton();
	}

}
