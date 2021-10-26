package pageFactories.Admin.EquipmentCategoryPages;

import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Equipment Category > Details page for
 * JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends EquipmentCategoryDetailsBase
 * 
 * @author scott.brazelton
 *
 */
public class EquipmentCategoryDetailsPageFactory
		extends
			EquipmentCategoryDetailsBase {
	private static String regexURL = BASE_URL
			+ "EquipmentCategories/Details/.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public EquipmentCategoryDetailsPageFactory() {
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
