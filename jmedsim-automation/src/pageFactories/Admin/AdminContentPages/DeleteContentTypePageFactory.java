package pageFactories.Admin.AdminContentPages;

import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Content > Content Type > Delete page for
 * JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ContentTypeDetailsBase
 * 
 * @author scott.brazelton
 *
 */
public class DeleteContentTypePageFactory extends ContentTypeDetailsBase {
	private static String regexURL = BASE_URL + "ContentTypes/Delete/.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public DeleteContentTypePageFactory() {
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
