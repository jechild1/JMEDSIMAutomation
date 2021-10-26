package pageFactories.Admin.EquipmentCategoryPages;

/**
 * Page factory for the Admin > Equipment Category > Create new Equipment
 * Category for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifyEquipmentCategoryBase
 * 
 * @author scott.brazelton
 *
 */
public class CreateNewEquipmentCategoryPageFactory
		extends
			ModifyEquipmentCategoryBase {
	private static String regexURL = BASE_URL + "EquipmentCategories/Create";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public CreateNewEquipmentCategoryPageFactory() {
		super(regexURL);
	}

	/**
	 * Clicks the Create button
	 */
	public void clickCreate() {
		clickSubmitTypeButton();
	}
}
