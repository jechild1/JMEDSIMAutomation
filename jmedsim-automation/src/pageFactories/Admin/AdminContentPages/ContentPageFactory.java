package pageFactories.Admin.AdminContentPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.IndexBase;
import pageFactories.JMEDSimTables;
import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Content > Content page for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends IndexBase
 * 
 * @author scott.brazelton
 *
 */
public class ContentPageFactory extends IndexBase {
	private static String regexURL = BASE_URL + "Contents/Index.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public ContentPageFactory() {
		super(regexURL);
	}

	@FindBy(linkText = "Create New Content")
	WebElement createNewContentLink;

	/**
	 * Clicks Create New Content
	 */
	public void clickCreateNewContent() {
		AutomationHelper.printMethodName();

		createNewContentLink.click();

	}

	@FindBy(xpath = "//table[@class='table  table-striped table-hover table-bordered']")
	WebElement contentTable;

	/**
	 * Returns a reference to the Content Table.
	 * 
	 * @return JMEDSimTables
	 */
	public JMEDSimTables getContentTable() {
		return new JMEDSimTables(contentTable);
	}
}
