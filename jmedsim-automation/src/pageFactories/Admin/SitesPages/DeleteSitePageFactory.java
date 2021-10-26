package pageFactories.Admin.SitesPages;

import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Sites > Delete page for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends SiteDetailsBase
 * 
 * @author scott.brazelton
 *
 */
public class DeleteSitePageFactory extends SiteDetailsBase {
	private static String regexURL = BASE_URL + "Sites/Delete/.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public DeleteSitePageFactory() {
		super(regexURL);
	}

	/**
	 * Click Delete button
	 */
	public void clickDelete() {
		AutomationHelper.printMethodName();
		clickModifyTypeLinkOrButton();
	}

}
