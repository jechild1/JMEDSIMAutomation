package pageFactories.Admin.AdminContentPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.IndexBase;
import pageFactories.JMEDSimTables;
import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Content > Content > Details > Add Metadata page
 * for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends IndexBase
 * 
 * @author scott.brazelton
 *
 */
public class AddMetadataToContentPageFactory extends IndexBase {
	private static String regexURL = BASE_URL
			+ "ContentMetadatas/MetadataList/.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public AddMetadataToContentPageFactory() {
		super(regexURL);
	}

	@FindBy(xpath = "//dt[text()[normalize-space() = 'Content']]/following-sibling::dd[1]")
	WebElement contentDescription;

	/**
	 * Reads "Content" text
	 * 
	 * @return String
	 */
	public String readContent() {
		AutomationHelper.printMethodName();
		return contentDescription.getText().trim();
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

	@FindBy(xpath = "//table[@class='table table-striped table-hover table-bordered']")
	WebElement addMetadataToContentTable;

	/**
	 * Returns a reference to the Content Table.
	 * 
	 * @return JMEDSimTables
	 */
	public JMEDSimTables getAddMetadataToContentTable() {
		return new JMEDSimTables(addMetadataToContentTable);
	}

	@FindBy(linkText = "Back to Content Details")
	WebElement backToContentDetailsLink;

	/**
	 * Clicks Back to Content Details
	 */
	public void clickBackToContentDetails() {
		AutomationHelper.printMethodName();

		backToContentDetailsLink.click();

	}
}
