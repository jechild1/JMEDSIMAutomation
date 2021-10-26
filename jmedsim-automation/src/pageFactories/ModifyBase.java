package pageFactories;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.AutomationHelper;

/**
 * Abstract Page factory for Create & Edit pages that can be found throughout
 * pages in JMedSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends MenusPageFactory
 * 
 * @author scott.brazelton
 *
 */
public abstract class ModifyBase extends MenusPageFactory {

	/**
	 * Abstract Create & Edit Page Constructor: Accepts the WebDriver from the
	 * calling page, provides base methods common to create pages, and passes
	 * URL to MenusPageFactory for further validation.
	 * 
	 * Extends MenusPageFactory
	 */
	public ModifyBase(String regexURL) {
		super(regexURL);
	}

	@FindBy(xpath = "//input[@value = 'Create']|//input[@value = 'Save']|//input[@value = 'Lock']")
	WebElement createButton;

	/**
	 * Clicks the submit button (either 'Create' or 'Save') on page
	 */
	protected void clickSubmitTypeButton() {
		createButton.click();
		waitForPageToLoad();
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

}
