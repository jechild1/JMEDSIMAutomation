package pageFactories.Admin.EquipmentCategoryPages;

import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Equipment Category > Delete page for
 * JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends EquipmentCategoryDetailsBase
 * 
 * @author scott.brazelton
 *
 */
public class DeleteEquipmentCategoryPageFactory
		extends
			EquipmentCategoryDetailsBase {
	private static String regexURL = BASE_URL + "EquipmentCategories/Delete/.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public DeleteEquipmentCategoryPageFactory() {
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
