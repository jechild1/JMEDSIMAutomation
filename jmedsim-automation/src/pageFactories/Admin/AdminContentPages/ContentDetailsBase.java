package pageFactories.Admin.AdminContentPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.DetailsBase;
import utilities.AutomationHelper;

/**
 * Abstract Page factory for the Details/Delete Admin > Content > Content pages
 * for JMEDSIM.<br>
 * (1) Finds a reference to common objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends DetailsBase
 * 
 * @author scott.brazelton
 *
 */
public abstract class ContentDetailsBase extends DetailsBase {

	/**
	 * Abstract Page Constructor: Accepts the WebDriver from the calling page,
	 * provides base methods common to create pages, and passes URL to
	 * MenusPageFactory for further validation.
	 * 
	 * Extends DetailsBase
	 */
	public ContentDetailsBase(String regexURL) {
		super(regexURL);
	}

	@FindBy(xpath = "//dt[text()[normalize-space() = 'Content Name']]/following-sibling::dd[1]")
	WebElement contentNameDescription;

	/**
	 * Reads "Content Name" text
	 * 
	 * @return String
	 */
	public String readContentName() {
		AutomationHelper.printMethodName();
		return contentNameDescription.getText().trim();
	}

	// TODO: Add content description once synced up between detail and delete

	@FindBy(xpath = "//dt[text()[normalize-space() = 'Content Path']]/following-sibling::dd[1]|//dt[text()[normalize-space() = 'ContentPath']]/following-sibling::dd[1]")
	WebElement contentPathDescription;

	/**
	 * Reads "Content Path" text
	 * 
	 * @return String
	 */
	public String readContentPath() {
		AutomationHelper.printMethodName();
		return contentPathDescription.getText().trim();
	}

	@FindBy(xpath = "//dt[text()[normalize-space() = 'Content Type']]/following-sibling::dd[1]|//dt[text()[normalize-space() = 'ContentPath']]/following-sibling::dd[1]")
	WebElement contentTypeDescription;

	/**
	 * Reads "Content Type" text
	 * 
	 * @return String
	 */
	public String readContentType() {
		AutomationHelper.printMethodName();
		return contentTypeDescription.getText().trim();
	}

	@FindBy(xpath = "//dt[text()[normalize-space() = 'Active']]/following-sibling::dd[1]/input")
	WebElement activeCheckbox;

	/**
	 * Returns if Active is checked or not
	 * 
	 * @return boolean - true = checked | false = not checked
	 */
	public boolean isActiveChecked() {
		AutomationHelper.printMethodName();
		return activeCheckbox.isSelected();
	}
}
