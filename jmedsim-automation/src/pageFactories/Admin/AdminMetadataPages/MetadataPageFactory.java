package pageFactories.Admin.AdminMetadataPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.IndexBase;
import pageFactories.JMEDSimTables;
import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Metadata > Metadata page for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends IndexBase
 * 
 * @author scott.brazelton
 *
 */
public class MetadataPageFactory extends IndexBase {
	private static String regexURL = BASE_URL + "MetadataDetails/Index.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public MetadataPageFactory() {
		super(regexURL);
	}

	@FindBy(linkText = "Create New Metadata")
	WebElement createNewMetadataLink;

	/**
	 * Clicks Create New Metadata
	 */
	public void clickCreateNewMetadata() {
		AutomationHelper.printMethodName();

		createNewMetadataLink.click();

	}

	@FindBy(xpath = "//table[@class='table table-striped table-hover table-bordered']")
	WebElement metadataTable;

	/**
	 * Returns a reference to the Metadata Table.
	 * 
	 * @return JMEDSimTables
	 */
	public JMEDSimTables getMetadataTable() {
		return new JMEDSimTables(metadataTable);
	}

}
