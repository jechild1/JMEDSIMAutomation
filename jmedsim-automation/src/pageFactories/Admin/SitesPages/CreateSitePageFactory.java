package pageFactories.Admin.SitesPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Sites > Create new site page for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifySiteBase
 * 
 * @author scott.brazelton
 *
 */
public class CreateSitePageFactory extends ModifySiteBase {
	private static String regexURL = BASE_URL + "Sites/Create";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public CreateSitePageFactory() {
		super(regexURL);
	}

	/**
	 * Clicks the Create button
	 */
	public void clickCreate() {
		clickSubmitTypeButton();
	}
	
	@FindBy(xpath = "//span[@data-valmsg-for='SiteName']")
	WebElement siteError;
	
	/**
	 * Reads the value of the Site Error message, if present
	 * @return String
	 */
	public String readSiteError() {
		AutomationHelper.printMethodName();
		return siteError.getText();
	}
	
	@FindBy(xpath = "//span[@data-valmsg-for='Address1']")
	WebElement address1Error;
	
	/**
	 * Reads the value of the Address 1 Error message, if present
	 * @return String
	 */
	public String readAddress1Error() {
		AutomationHelper.printMethodName();
		return address1Error.getText();
	}
	
	@FindBy(xpath = "//span[@data-valmsg-for='City']")
	WebElement cityError;
	
	/**
	 * Reads the value of the City Error message, if present
	 * @return String
	 */
	public String readCityError() {
		AutomationHelper.printMethodName();
		return cityError.getText();
	}
	
	@FindBy(xpath = "//span[@data-valmsg-for='State']")
	WebElement stateError;
	
	/**
	 * Reads the value of the State Error message, if present
	 * @return String
	 */
	public String readStateError() {
		AutomationHelper.printMethodName();
		return stateError.getText();
	}
	
	@FindBy(xpath = "//span[@data-valmsg-for='Zipcode']")
	WebElement zipCodeError;
	
	/**
	 * Reads the value of the Zipcode Error message, if present
	 * @return String
	 */
	public String readZipcodeError() {
		AutomationHelper.printMethodName();
		return zipCodeError.getText();
	}
	
	
	
	

}
