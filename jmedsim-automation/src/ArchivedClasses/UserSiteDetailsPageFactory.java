package ArchivedClasses;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.DetailsBase;
import utilities.AutomationHelper;

/**
 * Page factory for the Admin > User Sites > Details page for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends DetailsBase
 * 
 * @author scott.brazelton
 *
 */
public class UserSiteDetailsPageFactory extends DetailsBase {
	private static String regexURL = BASE_URL + "UserSites/Details/.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public UserSiteDetailsPageFactory() {
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

	@FindBy(xpath = "//dt[text()[normalize-space() = 'Site']]/following-sibling::dd[1]")
	WebElement siteDescription;

	/**
	 * Reads "Site" text
	 * 
	 * @return String
	 */
	public String readSite() {
		AutomationHelper.printMethodName();
		return siteDescription.getText().trim();
	}

	/**
	 * Clicks "Edit" link
	 */
	public void clickEdit() {
		AutomationHelper.printMethodName();
		clickModifyTypeLinkOrButton();
	}
}
