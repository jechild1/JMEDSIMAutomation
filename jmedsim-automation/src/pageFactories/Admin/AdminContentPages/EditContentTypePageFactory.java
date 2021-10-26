package pageFactories.Admin.AdminContentPages;

import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Content > Content Type > Edit for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifyContentTypeBase
 * 
 * @author scott.brazelton
 *
 */
public class EditContentTypePageFactory extends ModifyContentTypeBase {
	private static String regexURL = BASE_URL + "ContentTypes/Edit/.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public EditContentTypePageFactory() {
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
