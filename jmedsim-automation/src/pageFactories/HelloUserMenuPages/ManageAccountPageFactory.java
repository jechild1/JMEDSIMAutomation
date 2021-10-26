package pageFactories.HelloUserMenuPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.MenusPageFactory;

/**
 * Page factory for the Hello User > Manage Account page for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page.<br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends MenusPageFactory
 * 
 * @author jesse.childress
 *
 */
public class ManageAccountPageFactory extends MenusPageFactory {

	private static String regexURL = BASE_URL + "Manage/Index.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public ManageAccountPageFactory() {
		super(regexURL);
	}

	@FindBy(linkText = "Change your password")
	WebElement changeYourPasswordLink;

	/**
	 * Clicks the <i>Change your password</i> link.
	 */
	public void clickChangeYourPassword() {
		changeYourPasswordLink.click();
	}
	
	@FindBy (xpath = "//p[@class='text-success']")
	WebElement successMessageLabel;
	
	/**
	 * Returns the text inside of the Success Labe which is located on the Manage Account page. 
	 * Note: This more than likely contains "Your password has been changed."
	 * @return String
	 */
	public String readSuccessLabel() {
		return successMessageLabel.getText();
	}

}
