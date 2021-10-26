package pageFactories.Admin.AdminContentPages;

import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Content > Content Type > Details page for
 * JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ContentTypeDetailsBase
 * 
 * @author scott.brazelton
 *
 */
public class ContentTypeDetailsPageFactory extends ContentTypeDetailsBase {
	private static String regexURL = BASE_URL + "ContentTypes/Details/.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public ContentTypeDetailsPageFactory() {
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
