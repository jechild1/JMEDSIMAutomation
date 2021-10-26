package pageFactories.Admin.LogVerbosityPages;

import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Edit Log Verbosity JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifyLogVerbosityBase
 * 
 * @author jesse.childress
 *
 */
public class EditLogVerbosityPageFactory extends ModifyLogVerbosityBase {
	
	private static String regexURL = BASE_URL + "ApplicationInformations/LogVerbosityEdit";
	
	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public EditLogVerbosityPageFactory() {
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
