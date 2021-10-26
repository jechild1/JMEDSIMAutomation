package pageFactories.Admin.AdminNotifications;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.IndexBase;
import pageFactories.JMEDSimTables;
import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Notifications > Notifications Recipient Roles page for
 * JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends IndexBase
 * 
 * @author jesse.childress
 *
 */
public class NotificationRecipientRolesPageFactory extends IndexBase {
	
	private static String regexURL = BASE_URL + "NotificationRecipientRoles/Index";
	
	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public NotificationRecipientRolesPageFactory() {
		super(regexURL);
	}
	
	@FindBy (linkText = "Create New Recipient Role")
	WebElement createNewRecipientRoleLink;
	
	/**
	 * Clicks the Create New Recipient link.
	 */
	public void clickCreateNewRecipient() {
		AutomationHelper.printMethodName();
		createNewRecipientRoleLink.click();
	}
	
	@FindBy(xpath = "//table[@class='table table-striped table-hover table-bordered']")
	WebElement notificationRecipientRoleTable;

	/**
	 * Returns a reference to the Notification Recipient Role Table.
	 * 
	 * @return JMEDSimTables
	 */
	public JMEDSimTables getSimulatorCategoryTable() {
		return new JMEDSimTables(notificationRecipientRoleTable);
	}

}
