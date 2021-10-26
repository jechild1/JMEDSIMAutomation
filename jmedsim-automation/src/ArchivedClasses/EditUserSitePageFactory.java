package ArchivedClasses;

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
public class EditUserSitePageFactory extends ModifyBase {
	private static String regexURL = BASE_URL + "UserSites/Edit/.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public EditUserSitePageFactory() {
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

	@FindBy(id = "SiteId")
	WebElement siteIdDropdown;

	/**
	 * Select text in "Site Id" dropdown.
	 * 
	 * @param text
	 */
	public void selectSiteId(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.selectDropdownItem(siteIdDropdown, text);
	}

	/**
	 * Reads Site Id selected text
	 * 
	 * @return String
	 */
	public String readSiteIdSelected() {
		AutomationHelper.printMethodName();
		return AutomationHelper.readSelectedSubItem(siteIdDropdown);
	}

	/**
	 * Reads Site Id error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readSiteIdErrorMessage() {
		AutomationHelper.printMethodName();
		return readErrorMessageForField(siteIdDropdown.getAttribute("id"));
	}

	/**
	 * Clicks the Save button
	 */
	public void clickSave() {
		AutomationHelper.printMethodName();
		clickSubmitTypeButton();
	}
}
