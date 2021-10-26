package pageFactories.Admin.AdminSimulationPages;

/**
 * Page factory for the Admin > Simulation > Simulator Programming Platform >
 * Create New Simulator Programming Platform for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifySimulatorProgrammingPlatformBase
 * 
 * @author jesse.childress
 *
 */
public class CreateSimulatorProgrammingPlatformPageFactory extends ModifySimulatorProgrammingPlatformBase {
	
	private static String regexURL = BASE_URL + "SimulatorPlatforms/Create";
	
	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public CreateSimulatorProgrammingPlatformPageFactory() {
		super(regexURL);
	}

	/**
	 * Clicks the Create button
	 */
	public void clickCreate() {
		clickSubmitTypeButton();
	}

}
