package pageFactories.Admin.AdminAuditLogPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.JMEDSimTables;
import pageFactories.MenusPageFactory;
import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Audit Log page for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends MenusPageFactory
 * 
 * @author jesse.childress
 *
 */
public class AuditLogPageFactory extends MenusPageFactory {

	public static String regexURL = BASE_URL + "ApplicationAudits/Index";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public AuditLogPageFactory() {
		super(regexURL);
	}

	@FindBy(linkText = "Create New")
	WebElement createNewLink;

	/**
	 * Clicks the Create New link
	 */
	public void clickCreateNew() {
		AutomationHelper.printMethodName();
		createNewLink.click();

	}

	@FindBy(xpath = "//table[@class = 'table']")
	WebElement eventTable;

	/**
	 * Returns a reference to the Events table.
	 * 
	 * @return JMEDSimTables - Event table
	 */
	public JMEDSimTables getEventTable() {
		return new JMEDSimTables(eventTable);
	}

}
