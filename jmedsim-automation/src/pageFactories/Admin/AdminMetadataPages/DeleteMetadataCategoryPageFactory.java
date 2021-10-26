package pageFactories.Admin.AdminMetadataPages;

import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Metadata > Metadata Category > Delete page for
 * JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends MetadataCategoryDetailsBase
 * 
 * @author scott.brazelton
 *
 */
public class DeleteMetadataCategoryPageFactory
		extends
			MetadataCategoryDetailsBase {
	private static String regexURL = BASE_URL + "MetadataCategories/Delete/.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public DeleteMetadataCategoryPageFactory() {
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
