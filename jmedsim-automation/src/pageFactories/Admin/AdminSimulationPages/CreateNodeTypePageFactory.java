package pageFactories.Admin.AdminSimulationPages;

/**
 * Page factory for the Admin > Simulation > Personnel > Create New Node Type for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifyNodeTypeBase
 * 
 * @author jesse.childress
 *
 */
public class CreateNodeTypePageFactory extends ModifyNodeTypeBase {
	
	private static String regexURL = BASE_URL + "NodeTypes/Create";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public CreateNodeTypePageFactory() {
		super(regexURL);
	}

	/**
	 * Clicks the Create button
	 */
	public void clickCreate() {
		clickSubmitTypeButton();
	}

}
