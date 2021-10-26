package pageFactories.Admin.SitesPages;

import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Sites > Edit for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifySiteBase
 * 
 * @author scott.brazelton
 *
 */
public class EditSitePageFactory extends ModifySiteBase {
	private static String regexURL = BASE_URL + "Sites/Edit/.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public EditSitePageFactory() {
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
