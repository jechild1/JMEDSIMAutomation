package pageFactories.Admin.AdminSimulationPages;

/**
 * Page factory for the Admin > Simulation > Learner Profile > Create New
 * Learner Profile for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifyLearnerProfileBase
 * 
 * @author scott.brazelton
 *
 */
public class CreateNewLearnerPofilePageFactory
		extends
			ModifyLearnerProfileBase {
	private static String regexURL = BASE_URL + "LearnerProfiles/Create";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public CreateNewLearnerPofilePageFactory() {
		super(regexURL);
	}

	/**
	 * Clicks the Create button
	 */
	public void clickCreate() {
		clickSubmitTypeButton();
	}
}
