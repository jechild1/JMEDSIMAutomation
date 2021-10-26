package pageFactories.Admin.SitesPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.DetailsBase;
import utilities.AutomationHelper;

/**
 * Abstract Page factory for the Details/Delete Admin > Sites pages for
 * JMEDSIM.<br>
 * (1) Finds a reference to common objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends DetailsBase
 * 
 * @author scott.brazelton
 *
 */
public abstract class SiteDetailsBase extends DetailsBase {

	/**
	 * Abstract Page Constructor: Accepts the WebDriver from the calling page,
	 * provides base methods common to create pages, and passes URL to
	 * MenusPageFactory for further validation.
	 * 
	 * Extends DetailsBase
	 */
	public SiteDetailsBase(String regexURL) {
		super(regexURL);
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

	@FindBy(xpath = "//dt[text()[normalize-space() = 'Address']]/following-sibling::dd[1]")
	WebElement addressDescription;

	/**
	 * Reads "Address" text
	 * 
	 * @return String
	 */
	public String readAddress() {
		AutomationHelper.printMethodName();
		return addressDescription.getText().trim();
	}

	@FindBy(xpath = "//dt[text()[normalize-space() = 'Active Site']]/following-sibling::dd[1]/input")
	WebElement activeSiteCheckbox;

	/**
	 * Returns if Active Site is checked or not
	 * 
	 * @return boolean - true = checked | false = not checked
	 */
	public boolean isActiveSiteChecked() {
		AutomationHelper.printMethodName();
		return activeSiteCheckbox.isSelected();
	}

}
