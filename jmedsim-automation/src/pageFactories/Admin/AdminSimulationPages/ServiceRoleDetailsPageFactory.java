package pageFactories.Admin.AdminSimulationPages;

import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Simulation > Service Role > Details page for
 * JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ServiceRoleDetailsBase
 * 
 * @author scott.brazelton
 *
 */
public class ServiceRoleDetailsPageFactory extends ServiceRoleDetailsBase {
	private static String regexURL = BASE_URL + "ServiceRoles/Details/.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public ServiceRoleDetailsPageFactory() {
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
