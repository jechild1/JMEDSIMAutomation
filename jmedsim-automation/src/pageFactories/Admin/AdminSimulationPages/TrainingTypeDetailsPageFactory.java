package pageFactories.Admin.AdminSimulationPages;

import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Simulation > Training Type > Details page for
 * JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends TrainingTypeDetailsBase
 * 
 * @author scott.brazelton
 *
 */
public class TrainingTypeDetailsPageFactory extends TrainingTypeDetailsBase {
	private static String regexURL = BASE_URL + "TrainingTypes/Details/.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public TrainingTypeDetailsPageFactory() {
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
