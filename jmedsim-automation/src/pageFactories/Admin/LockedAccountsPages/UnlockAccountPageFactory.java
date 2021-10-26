package pageFactories.Admin.LockedAccountsPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.DetailsBase;
import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Locked Accounts > Unlock page for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends DetailsBase
 * 
 * @author scott.brazelton
 *
 */
public class UnlockAccountPageFactory extends DetailsBase {
	private static String regexURL = BASE_URL + "LockedOuts/Unlock/.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public UnlockAccountPageFactory() {
		super(regexURL);
	}

	@FindBy(xpath = "//dt[text()[normalize-space() = 'User']]/following-sibling::dd[1]")
	WebElement userDescription;

	/**
	 * Reads "User" text
	 * 
	 * @return String
	 */
	public String readUser() {
		AutomationHelper.printMethodName();
		return userDescription.getText().trim();
	}

	@FindBy(xpath = "//dt[text()[normalize-space() = 'Reason']]/following-sibling::dd[1]")
	WebElement reasonDescription;

	/**
	 * Reads "Reason" text
	 * 
	 * @return String
	 */
	public String readReason() {
		AutomationHelper.printMethodName();
		return reasonDescription.getText().trim();
	}

	@FindBy(xpath = "//dt[text()[normalize-space() = 'Locked By']]/following-sibling::dd[1]")
	WebElement lockedByDescription;

	/**
	 * Reads "Locked By" text
	 * 
	 * @return String
	 */
	public String readLockedBy() {
		AutomationHelper.printMethodName();
		return lockedByDescription.getText().trim();
	}

	@FindBy(xpath = "//dt[text()[normalize-space() = 'Locked On']]/following-sibling::dd[1]")
	WebElement lockedOnDescription;

	/**
	 * Reads "Locked On" text
	 * 
	 * @return String
	 */
	public String readLockedOn() {
		AutomationHelper.printMethodName();
		return lockedOnDescription.getText().trim();
	}

	/**
	 * Click Unlock button
	 */
	public void clickUnlock() {
		AutomationHelper.printMethodName();
		clickModifyTypeLinkOrButton();
	}
}
