package pageFactories.Admin.AdminMetadataPages;

import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Metadata > Metadata > Delete page for
 * JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends MetadataDetailsBase
 * 
 * @author scott.brazelton
 *
 */
public class DeleteMetadataPageFactory extends MetadataDetailsBase {
	private static String regexURL = BASE_URL + "MetadataDetails/Delete/.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public DeleteMetadataPageFactory() {
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
