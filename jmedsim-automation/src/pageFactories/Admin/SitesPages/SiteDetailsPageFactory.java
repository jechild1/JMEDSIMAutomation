package pageFactories.Admin.SitesPages;

import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Sites > Details page for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends SiteDetailsBase
 * 
 * @author scott.brazelton
 *
 */
public class SiteDetailsPageFactory extends SiteDetailsBase {
	private static String regexURL = BASE_URL + "Sites/Details/.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public SiteDetailsPageFactory() {
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
