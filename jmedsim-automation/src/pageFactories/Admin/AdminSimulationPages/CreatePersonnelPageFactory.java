package pageFactories.Admin.AdminSimulationPages;

/**
 * Page factory for the Admin > Simulation > Personnel > Create New Personnel
 * Profile for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifyPersonnelBase
 * 
 * @author jesse.childress
 *
 */
public class CreatePersonnelPageFactory extends ModifyPersonnelBase {
	private static String regexURL = BASE_URL + "Personnels/Create";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public CreatePersonnelPageFactory() {
		super(regexURL);
	}

	/**
	 * Clicks the Create button
	 */
	public void clickCreate() {
		clickSubmitTypeButton();
	}
}
