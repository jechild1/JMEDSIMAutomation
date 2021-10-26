package pageFactories;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.AutomationHelper;

/**
 * Page factory for the Login page for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * @author scott.brazelton
 *
 */
public class LoginPageFactory extends JMEDSIMBase {

	/**
	 * Page Constructor: Accepts the WebDriver from the calling page &
	 * instantiates the elements on the page.
	 */
	public LoginPageFactory() {
		PageFactory.initElements(driver, this);
	}

	/**
	 * Clicks "Ok" in DOD modal banner
	 */
	public void clickOkInDODBanner() {
		AutomationHelper.printMethodName();

		clickOkInModal();

	}

	@FindBy(id = "Email")
	WebElement email;

	/**
	 * This method sets the Email text field
	 * 
	 * @param text
	 */
	public void setEmail(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(email, text);
	}

	@FindBy(xpath = "//span[contains(@class,'text-danger') and @data-valmsg-for='Email']")
	WebElement emailError;

	/**
	 * Reads the error for the email text field if exists
	 */
	public String readEmailError() {
		AutomationHelper.printMethodName();
		return emailError.getText();
	}

	@FindBy(id = "Password")
	WebElement password;

	/**
	 * This method sets the Password text field
	 * 
	 * @param text
	 */
	public void setPassword(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(password, text);
	}

	@FindBy(xpath = "//span[contains(@class,'text-danger') and @data-valmsg-for='Password']")
	WebElement passwordError;

	/**
	 * Reads the error for the email text field if exists
	 */
	public String readPasswordError() {
		AutomationHelper.printMethodName();
		return passwordError.getText();
	}

	@FindBy(id = "RememberMe")
	WebElement rememberMe;

	/**
	 * This method sets the Password text field
	 * 
	 * @param desiredStatus
	 *            - true = checked | false = not checked
	 */
	public void setRememberMeCheckbox(boolean desiredStatus) {
		AutomationHelper.printMethodName();
		AutomationHelper.setCheckbox(rememberMe, desiredStatus);
	}

	@FindBy(xpath = "//input[(@value='Log in')]")
	WebElement login;

	/**
	 * Click on "Login"
	 */
	public void clickLogin() {
		AutomationHelper.printMethodName();
		login.click();
	}

	@FindBy(linkText = "Register as a new user")
	WebElement registerNewUser;

	/**
	 * Click on "Register as a new user"
	 */
	public void clickRegisterAsANewUser() {
		AutomationHelper.printMethodName();
		registerNewUser.click();
	}
	
	@FindBy (linkText = "Forgot your password?")
	WebElement forgotYourPassword;
	
	/**
	 * Click on "Forgot your password?"
	 */
	public void clickForgotYourPassword() {
		AutomationHelper.printMethodName();
		forgotYourPassword.click();
	}

	@FindBy(xpath = "//form/div[contains(@class,'validation-summary-errors')]")
	List<WebElement> summaryErrors;

	/**
	 * Reads the errors that show up in the form summary after submission
	 */
	public String readSummaryErrors() {
		AutomationHelper.printMethodName();

		// do not want this to error if doesn't exist - so passing web element
		// to a list and then checking list for null

		String errors = "";

		AutomationHelper.adjustWaitTimeToShort();
		if (summaryErrors.size() > 0) {
			errors = summaryErrors.get(0).getText();
		}
		AutomationHelper.adjustWaitTimeToNormal();

		return errors;
	}

}
