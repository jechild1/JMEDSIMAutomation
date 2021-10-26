package pageFactories.Admin.AdminSimulatorPages;

/**
 * Page factory for the Admin > Simulator > Simulator > Create New Simulator for
 * JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifySimulatorBase
 * 
 * @author scott.brazelton
 *
 */
public class CreateSimulatorPageFactory extends ModifySimulatorBase {
	private static String regexURL = BASE_URL + "Simulators/Create";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public CreateSimulatorPageFactory() {
		super(regexURL);
	}

	/**
	 * Clicks the Create button
	 */
	public void clickCreate() {
		clickSubmitTypeButton();
	}
}
