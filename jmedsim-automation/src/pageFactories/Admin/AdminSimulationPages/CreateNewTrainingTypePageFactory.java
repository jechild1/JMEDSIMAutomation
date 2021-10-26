package pageFactories.Admin.AdminSimulationPages;

/**
 * Page factory for the Admin > Simulation > Training Type > Create New Training
 * Type for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifyTrainingTypeBase
 * 
 * @author scott.brazelton
 *
 */
public class CreateNewTrainingTypePageFactory extends ModifyTrainingTypeBase {
	private static String regexURL = BASE_URL + "TrainingTypes/Create";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public CreateNewTrainingTypePageFactory() {
		super(regexURL);
	}

	/**
	 * Clicks the Create button
	 */
	public void clickCreate() {
		clickSubmitTypeButton();
	}
}
