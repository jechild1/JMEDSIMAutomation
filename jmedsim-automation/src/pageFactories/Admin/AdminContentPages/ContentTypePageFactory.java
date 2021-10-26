package pageFactories.Admin.AdminContentPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.IndexBase;
import pageFactories.JMEDSimTables;
import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Content > Content Type page for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends IndexBase
 * 
 * @author scott.brazelton
 *
 */
public class ContentTypePageFactory extends IndexBase {
	private static String regexURL = BASE_URL+"ContentTypes/Index.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public ContentTypePageFactory() {
		super(regexURL);
	}

	@FindBy(linkText = "Create New Content Type")
	WebElement createNewContentTypeLink;

	/**
	 * Clicks Create New Content Type
	 */
	public void clickCreateNewContentType() {
		AutomationHelper.printMethodName();

		createNewContentTypeLink.click();

	}

	@FindBy(xpath = "//table[@class='table table-striped table-hover table-bordered']")
	WebElement contentTypeTable;

	/**
	 * Returns a reference to the Content Table.
	 * 
	 * @return JMEDSimTables
	 */
	public JMEDSimTables getContentTypeTable() {
		return new JMEDSimTables(contentTypeTable);
	}
}
