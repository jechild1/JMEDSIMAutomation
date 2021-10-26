package pageFactories.Admin.AdminSimulationPages;

/**
 * Page factory for the Admin > Simulation > Service Role > Create New Service
 * Role for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifyServiceRoleBase
 * 
 * @author scott.brazelton
 *
 */
public class CreateNewServiceRolePageFactory extends ModifyServiceRoleBase {
	private static String regexURL = BASE_URL + "ServiceRoles/Create";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public CreateNewServiceRolePageFactory() {
		super(regexURL);
	}

	/**
	 * Clicks the Create button
	 */
	public void clickCreate() {
		clickSubmitTypeButton();
	}
}
