package pageFactories.Admin.AdminMetadataPages;

import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Metadata > Metadata > Edit for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifyMetadataBase
 * 
 * @author scott.brazelton
 *
 */
public class EditMetadataPageFactory extends ModifyMetadataBase {
	private static String regexURL = BASE_URL + "MetadataDetails/Edit/.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public EditMetadataPageFactory() {
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
