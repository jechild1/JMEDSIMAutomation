package pageFactories.Admin.AdminMetadataPages;

import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Metadata > Metadata Category > Details page for
 * JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends MetadataCategoryDetailsBase
 * 
 * @author scott.brazelton
 *
 */
public class MetadataCategoryDetailsPageFactory
		extends
			MetadataCategoryDetailsBase {
	private static String regexURL = BASE_URL + "MetadataCategories/Details/.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public MetadataCategoryDetailsPageFactory() {
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
