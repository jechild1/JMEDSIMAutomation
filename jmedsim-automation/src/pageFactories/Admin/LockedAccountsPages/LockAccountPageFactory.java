package pageFactories.Admin.LockedAccountsPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.ModifyBase;
import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Locked Accounts > Lock an Account for
 * JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifyBase
 * 
 * @author scott.brazelton
 *
 */
public class LockAccountPageFactory extends ModifyBase {
	private static String regexURL = BASE_URL + "LockedOuts/Lock";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public LockAccountPageFactory() {
		super(regexURL);
	}

	/**
	 * Clicks the Lock button
	 */
	public void clickLock() {
		clickSubmitTypeButton();
	}

	@FindBy(id = "UserId")
	WebElement userDropdown;

	/**
	 * Select text in "User" dropdown.
	 * 
	 * @param text
	 */
	public void selectUser(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.selectDropdownItem(userDropdown, text);
	}

	/**
	 * Reads User selected text
	 * 
	 * @return String
	 */
	public String readUserSelected() {
		AutomationHelper.printMethodName();
		return AutomationHelper.readSelectedSubItem(userDropdown);
	}

	/**
	 * Reads User error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readUserErrorMessage() {
		AutomationHelper.printMethodName();
		return readErrorMessageForField(userDropdown.getAttribute("id"));
	}

	@FindBy(id = "LockedReasonId")
	WebElement lockReasonDropdown;

	/**
	 * Select text in "Lock Reason" dropdown.
	 * 
	 * @param text
	 */
	public void selectLockReason(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.selectDropdownItem(lockReasonDropdown, text);
	}

	/**
	 * Reads Lock Reason selected text
	 * 
	 * @return String
	 */
	public String readLockReasonSelected() {
		AutomationHelper.printMethodName();
		return AutomationHelper.readSelectedSubItem(lockReasonDropdown);
	}

	/**
	 * Reads Lock Reason error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readLockReasonErrorMessage() {
		AutomationHelper.printMethodName();
		return readErrorMessageForField(lockReasonDropdown.getAttribute("id"));
	}
}
