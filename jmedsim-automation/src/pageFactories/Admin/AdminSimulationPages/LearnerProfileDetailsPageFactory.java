package pageFactories.Admin.AdminSimulationPages;

import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Simulation > Learner Profile > Details page for
 * JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends LearnerProfileDetailsBase
 * 
 * @author scott.brazelton
 *
 */
public class LearnerProfileDetailsPageFactory
		extends
			LearnerProfileDetailsBase {
	private static String regexURL = BASE_URL + "LearnerProfiles/Details/.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public LearnerProfileDetailsPageFactory() {
		super(regexURL);
	}

	/**
	 * Clicks "Edit" link
	 */
	public void clickEdit() {
		AutomationHelper.printMethodName();
		clickModifyTypeLinkOrButton();
	}
}
