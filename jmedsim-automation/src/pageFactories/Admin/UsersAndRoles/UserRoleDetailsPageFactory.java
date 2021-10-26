package pageFactories.Admin.UsersAndRoles;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.DetailsBase;
import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Users & Roles > Details page for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends DetailsPageFactoryBase
 * 
 * @author scott.brazelton
 *
 */
public class UserRoleDetailsPageFactory extends DetailsBase {
	private static String regexURL = BASE_URL + "UserRoles/Details/.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public UserRoleDetailsPageFactory() {
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

	@FindBy(xpath = "//dt[text()[normalize-space() = 'Role']]/following-sibling::dd[1]")
	WebElement roleDescription;

	/**
	 * Reads "Role" text
	 * 
	 * @return String
	 */
	public String readRole() {
		AutomationHelper.printMethodName();
		return roleDescription.getText().trim();
	}

	/**
	 * Clicks "Edit" link
	 */
	public void clickEdit() {
		AutomationHelper.printMethodName();
		clickModifyTypeLinkOrButton();
	}

}
