package pageFactories.MyMenuPages;

/**
 * Page factory for the My Menu > Simulations > Create New Simulation for
 * JMEDSIM <br>
 * (1) Finds a reference to objects on the page.<br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifyBase
 * 
 * @author jesse.childress
 *
 */
public class CreateNewSimulationPageFactory extends ModifySimulationBase {

	private static String regexURL = BASE_URL + "Simulations/Create";

	/**
	 * Page Constructor: Instantiates super class with URL provided.
	 */
	public CreateNewSimulationPageFactory() {
		super(regexURL);
	}

	/**
	 * Clicks the Create button
	 */
	public void clickCreate() {
		clickSubmitTypeButton();
	}

}
