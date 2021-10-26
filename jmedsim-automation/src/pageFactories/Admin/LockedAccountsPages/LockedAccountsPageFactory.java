package pageFactories.Admin.LockedAccountsPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.IndexBase;
import pageFactories.JMEDSimTables;
import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Locked Accounts page for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends IndexBase
 * 
 * @author scott.brazelton
 *
 */
public class LockedAccountsPageFactory extends IndexBase {
	private static String regexURL = BASE_URL + "LockedOuts/Index";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public LockedAccountsPageFactory() {
		super(regexURL);
	}

	@FindBy(linkText = "Lock an Account")
	WebElement lockAnAccountLink;

	/**
	 * Clicks Lock an Account
	 */
	public void clickLockAnAccount() {
		AutomationHelper.printMethodName();

		lockAnAccountLink.click();

	}

	@FindBy(xpath = "//table[@class='table table-striped table-hover table-bordered']")
	WebElement lockedAccountsTable;

	/**
	 * Returns a reference to the Locked Accounts Table.
	 * 
	 * @return JMEDSimTables
	 */
	public JMEDSimTables getLockedAccountsTable() {
		return new JMEDSimTables(lockedAccountsTable);
	}
}
