package pageFactories.Admin.AdminMetadataPages;

import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Metadata > Metadata Category > Edit for
 * JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifyMetadataCategoryBase
 * 
 * @author scott.brazelton
 *
 */
public class EditMetadataCategoryPageFactory
		extends
			ModifyMetadataCategoryBase {
	private static String regexURL = BASE_URL + "MetadataCategories/Edit/.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public EditMetadataCategoryPageFactory() {
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
