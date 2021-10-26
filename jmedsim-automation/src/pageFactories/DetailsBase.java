
package pageFactories;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.AutomationHelper;

/**
 * Abstract Page factory for Details & Delete pages that can be found throughout
 * pages in JMedSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends MenusPageFactory
 * 
 * @author scott.brazelton
 *
 */
public class DetailsBase extends MenusPageFactory {

	/**
	 * Abstract Details Page Constructor: Accepts the WebDriver from the calling
	 * page, provides base methods common to create pages, and passes URL to
	 * MenusPageFactory for further validation.
	 * 
	 * Extends MenusPageFactory
	 */
	public DetailsBase(String regexURL) {
		super(regexURL);
	}

	@FindBy(xpath = "//a[starts-with(.,'Back to')]")
	WebElement backToListLink;

	/**
	 * Clicks on the "Back to {Page} List" link
	 */
	public void clickBackToList() {
		AutomationHelper.printMethodName();
		backToListLink.click();
		waitForPageToLoad();
	}

	@FindBy(xpath = "//a[. = 'Edit'] | //input[@value='Delete'] | //input[@value='Unlock']")
	WebElement editOrDeleteButton;

	/**
	 * Clicks "Edit" on details page or "Delete" on delete page
	 */
	protected void clickModifyTypeLinkOrButton() {
		editOrDeleteButton.click();
		waitForPageToLoad();
	}

	@FindBy(xpath = "//label[@id='msg'][@class='alert-danger'][not(@style)]")
	List<WebElement> alertLabel;

	/**
	 * Is there an alert message on the page
	 * 
	 * @return boolean - true = yes | false = no
	 */
	public boolean isAlertMessageOnPage() {
		AutomationHelper.printMethodName();
		boolean isAlertOnPage = false;

		AutomationHelper.adjustWaitTimeToShort();
		isAlertOnPage = AutomationHelper.isWebElementOnPage(alertLabel);
		AutomationHelper.adjustWaitTimeToNormal();

		return isAlertOnPage;
	}

	/**
	 * Read the alert message on the page
	 * 
	 * @return
	 */
	public String readAlertMessage() {
		AutomationHelper.printMethodName();
		return alertLabel.get(0).getText();
	}
}
