package pageFactories.Admin.AdminSimulatorPages;

/**
 * Page factory for the Admin > Simulator > Simulator Category > Create New
 * Simulator Category for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifySimulatorCategoryBase
 * 
 * @author scott.brazelton
 *
 */
public class CreateNewSimulatorCategoryPageFactory
		extends
			ModifySimulatorCategoryBase {
	private static String regexURL = BASE_URL + "SimulatorCategories/Create";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public CreateNewSimulatorCategoryPageFactory() {
		super(regexURL);
	}

	/**
	 * Clicks the Create button
	 */
	public void clickCreate() {
		clickSubmitTypeButton();
	}
}
