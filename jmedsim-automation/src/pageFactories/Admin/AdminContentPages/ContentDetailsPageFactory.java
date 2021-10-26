package pageFactories.Admin.AdminContentPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.JMEDSimTables;
import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Content > Content > Details page for
 * JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ContentDetailsBase
 * 
 * @author scott.brazelton
 *
 */
public class ContentDetailsPageFactory extends ContentDetailsBase {
	private static String regexURL = BASE_URL + "Contents/Details/.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public ContentDetailsPageFactory() {
		super(regexURL);
	}

	/**
	 * Clicks "Edit" link
	 */
	public void clickEdit() {
		AutomationHelper.printMethodName();
		clickModifyTypeLinkOrButton();
	}

	@FindBy(xpath = "//dt[text()[normalize-space() = 'Description']]/following-sibling::dd[1]")
	WebElement descriptionDescription;

	/**
	 * Reads "Description" text
	 * 
	 * @return String
	 */
	public String readDescription() {
		AutomationHelper.printMethodName();
		return descriptionDescription.getText().trim();
	}
	
	@FindBy(xpath = "//dt[text()[normalize-space() = 'Metadata']]/following-sibling::dd[1]")
	WebElement metadataLabel;
	
	/**
	 * Reads the <i>Metadata</i> label
	 * @return String
	 */
	public String readMetadata() {
		AutomationHelper.printMethodName();
		return metadataLabel.getText().trim();
	}

	@FindBy(linkText = "Add Metadata")
	WebElement addMetadataLink;

	/**
	 * Clicks Add Metadata
	 */
	public void clickAddMetadata() {
		AutomationHelper.printMethodName();

		addMetadataLink.click();

	}

	@FindBy(xpath = "//table[@class='table table-striped table-hover table-bordered']")
	WebElement contentMetadataTable;

	/**
	 * Returns a reference to the Content Table.
	 * 
	 * @return JMEDSimTables
	 */
	public JMEDSimTables getContentMetadataTable() {
		return new JMEDSimTables(contentMetadataTable);
	}
}
