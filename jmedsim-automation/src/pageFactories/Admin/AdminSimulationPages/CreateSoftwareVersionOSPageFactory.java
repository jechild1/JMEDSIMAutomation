package pageFactories.Admin.AdminSimulationPages;

/**
 * Page factory for the Admin > Simulation > Simulator Software Version/OS>
 * Create Software Version/OS for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifySimulatorSoftwareVersionOSBase
 * 
 * @author jesse.childress
 *
 */
public class CreateSoftwareVersionOSPageFactory extends ModifySimulatorSoftwareVersionOSBase {
	
	private static String regexURL = BASE_URL + "SimulatorSoftwareVersionOes/Create";
	
	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public CreateSoftwareVersionOSPageFactory() {
		super(regexURL);
	}

	/**
	 * Clicks the Create button
	 */
	public void clickCreate() {
		clickSubmitTypeButton();
	}

}
