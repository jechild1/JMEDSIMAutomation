package pageFactories.Admin.AdminSimulationPages;

import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Simulation > Service Roles > Edit for
 * JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifyServiceRoleBase
 * 
 * @author scott.brazelton
 *
 */
public class EditServiceRolePageFactory extends ModifyServiceRoleBase {
	private static String regexURL = BASE_URL + "ServiceRoles/Edit/.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public EditServiceRolePageFactory() {
		super(regexURL);
	}

	/**
	 * Clicks the Save button
	 */
	public void clickSave() {
		AutomationHelper.printMethodName();
		clickSubmitTypeButton();
	}
}
