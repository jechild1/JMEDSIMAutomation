
package pageFactories;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.AutomationHelper;

/**
 * Abstract Page factory for Index pages throughout JMedSIM.<br>
 * 
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends MenusPageFactory
 * 
 * @author scott.brazelton
 *
 */
public abstract class IndexBase extends MenusPageFactory {

	/**
	 * Abstract Index Page Constructor: Accepts the WebDriver from the calling
	 * page, provides base methods common to create pages, and passes URL to
	 * MenusPageFactory for further validation.
	 * 
	 * Extends MenusPageFactory
	 */
	public IndexBase(String regexURL) {
		super(regexURL);
	}

	@FindBy(id = "SearchString")
	WebElement searchTextfield;

	/**
	 * Sets text for "Search by"
	 * 
	 * @param searchText
	 */
	public void setSearchBy(String searchText) {
		AutomationHelper.printMethodName();

		AutomationHelper.setTextField(searchTextfield, searchText);

	}
	
	/**
	 * Clears the value currently in the "Search by" text field.
	 */
	public void clearSearchBy() {
		AutomationHelper.printMethodName();
		searchTextfield.clear();
	}

	//This button is showing up as both a button that Says "Search, and more recently, a magnifying glass icon.
	@FindBy(xpath = "//button[text()='Search']|//button//span[@class='glyphicon glyphicon-search']")
	WebElement searchButton;

	/**
	 * Clicks "Search"
	 */
	public void clickSearch() {
		AutomationHelper.printMethodName();

		searchButton.click();
		waitForPageToLoad();
	}
	
	
	
	
}
