package pageFactories.Admin.AdminSimulatorPages;

import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Simulator > Simulator Category > Details page
 * for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends SimulatorCategoryDetailsBase
 * 
 * @author scott.brazelton
 *
 */
public class SimulatorCategoryDetailsPageFactory
		extends
			SimulatorCategoryDetailsBase {
	private static String regexURL = BASE_URL
			+ "SimulatorCategories/Details/.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public SimulatorCategoryDetailsPageFactory() {
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
