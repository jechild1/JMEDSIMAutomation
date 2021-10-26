package pageFactories.Admin.LogVerbosityPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.IndexBase;
import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Log Verbosity for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends IndexBase
 * 
 * @author jesse.childress
 *
 */
public class LogVerbosityPageFactory extends IndexBase {
	
	private static String regexURL = BASE_URL + "ApplicationInformations/LogVerbosity";
	
	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public LogVerbosityPageFactory() {
		super(regexURL);
	}
	
	
	@FindBy(linkText = "Edit")
	WebElement editLink;
	
	/**
	 * Clicks the Edit link
	 */
	public void clickEditLink() {
		AutomationHelper.printMethodName();
		editLink.click();
	}
	
	
	
	
	
	
	
	

}
