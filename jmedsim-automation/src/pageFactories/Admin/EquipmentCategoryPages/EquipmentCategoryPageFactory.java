package pageFactories.Admin.EquipmentCategoryPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.IndexBase;
import pageFactories.JMEDSimTables;
import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Equipment Category page for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends IndexBase
 * 
 * @author scott.brazelton
 *
 */
public class EquipmentCategoryPageFactory extends IndexBase {
	private static String regexURL = BASE_URL + "EquipmentCategories/Index";

	public EquipmentCategoryPageFactory() {
		super(regexURL);
	}

	@FindBy(xpath = "//table[@class='table table-striped table-hover table-bordered']")
	WebElement equipmentCategoryTable;

	@FindBy(linkText = "Create New Equipment Category")
	WebElement createNewEquipmentCategoryLink;

	/**
	 * Clicks Create New Equipment Category
	 */
	public void clickCreateNewEquipmentCategory() {
		AutomationHelper.printMethodName();

		createNewEquipmentCategoryLink.click();

	}

	/**
	 * Returns a reference to the Equipment Category Table.
	 * 
	 * @return JMEDSimTables
	 */
	public JMEDSimTables getEquipmentCategoryTable() {
		return new JMEDSimTables(equipmentCategoryTable);
	}
}
