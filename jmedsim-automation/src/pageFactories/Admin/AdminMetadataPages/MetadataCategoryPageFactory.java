package pageFactories.Admin.AdminMetadataPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.IndexBase;
import pageFactories.JMEDSimTables;
import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Metadata > Metadata Category page for
 * JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends IndexBase
 * 
 * @author scott.brazelton
 *
 */
public class MetadataCategoryPageFactory extends IndexBase {
	private static String regexURL = BASE_URL + "MetadataCategories/Index.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public MetadataCategoryPageFactory() {
		super(regexURL);
	}

	@FindBy(linkText = "Create Metadata Category")
	WebElement createNewMetadataCategoryLink;

	/**
	 * Clicks Create Metadata Category
	 */
	public void clickCreateMetadataCategory() {
		AutomationHelper.printMethodName();

		createNewMetadataCategoryLink.click();

	}

	@FindBy(xpath = "//table[@class='table table-striped table-hover table-bordered']")
	WebElement metadataCategoryTable;

	/**
	 * Returns a reference to the Metadata Category Table.
	 * 
	 * @return JMEDSimTables
	 */
	public JMEDSimTables getMetadataCategoryTable() {
		return new JMEDSimTables(metadataCategoryTable);
	}
}
