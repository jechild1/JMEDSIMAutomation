package pageFactories.Admin.UsersAndRoles;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.ModifyBase;
import utilities.AutomationHelper;

/**
 * Page factory for the Admin > User Sites > Edit for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifyBase
 * 
 * @author scott.brazelton
 *
 */
public class EditAssignedRolePageFactory extends ModifyBase {
	private static String regexURL = BASE_URL + "UserRoles/Edit/.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public EditAssignedRolePageFactory() {
		super(regexURL);
	}

	@FindBy(xpath = "//select[@id='UserId']")
	WebElement userIdDropdown;

	/**
	 * Reads User Id selected text
	 * 
	 * @return String
	 */
	public String readUserIdSelected() {
		AutomationHelper.printMethodName();
		return AutomationHelper.readSelectedSubItem(userIdDropdown);
	}

	/**
	 * Reads User Id error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readUserIdErrorMessage() {
		AutomationHelper.printMethodName();
		return readErrorMessageForField(userIdDropdown.getAttribute("id"));
	}

	@FindBy(id = "RoleId")
	WebElement roleIdDropdown;

	/**
	 * Select text in "Role" dropdown.
	 * 
	 * @param text
	 */
	public void selectRole(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.selectDropdownItem(roleIdDropdown, text);
	}

	/**
	 * Reads Role selected text
	 * 
	 * @return String
	 */
	public String readRoleSelected() {
		AutomationHelper.printMethodName();
		return AutomationHelper.readSelectedSubItem(roleIdDropdown);
	}

	/**
	 * Reads Role error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readRoleErrorMessage() {
		AutomationHelper.printMethodName();
		return readErrorMessageForField(roleIdDropdown.getAttribute("id"));
	}

	/**
	 * Clicks the Save button
	 */
	public void clickSave() {
		AutomationHelper.printMethodName();
		clickSubmitTypeButton();
	}

}
