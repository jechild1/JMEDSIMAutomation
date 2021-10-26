package pageFactories.Admin.AdminMetadataPages;

import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Metadata > Metadata > Details page for
 * JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends MetadataDetailsBase
 * 
 * @author scott.brazelton
 *
 */
public class MetadataDetailsPageFactory extends MetadataDetailsBase {
	private static String regexURL = BASE_URL + "MetadataDetails/Details/.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public MetadataDetailsPageFactory() {
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
