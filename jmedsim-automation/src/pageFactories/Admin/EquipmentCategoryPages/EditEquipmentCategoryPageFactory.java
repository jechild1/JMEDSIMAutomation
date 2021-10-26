package pageFactories.Admin.EquipmentCategoryPages;

import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Equipment Categories > Edit for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifyEquipmentCategoryBase
 * 
 * @author scott.brazelton
 *
 */
public class EditEquipmentCategoryPageFactory
		extends
			ModifyEquipmentCategoryBase {
	private static String regexURL = BASE_URL + "EquipmentCategories/Edit/.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public EditEquipmentCategoryPageFactory() {
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
