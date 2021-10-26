package pageFactories;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.AutomationHelper;

/**
 * Page factory for Reset Password page that can be found from the link on the
 * login page of JMedSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends JMEDSIMBase
 * 
 * @author jesse.childress
 *
 */
public class ResetPasswordPageFactory extends MenusPageFactory {

	private static String regexURL = BASE_URL + "ResetMyPassword.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public ResetPasswordPageFactory() {
		super(regexURL);
	}

	@FindBy(id = "eMail")
	WebElement emailTextfield;

	/**
	 * Sets the Email field
	 * 
	 * @param text
	 */
	public void setEmail(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(emailTextfield, text);
	}

	/**
	 * Reads the email field
	 * 
	 * @return
	 */
	public String readEmail() {
		AutomationHelper.printMethodName();
		return emailTextfield.getText();
	}

	@FindBy(id = "emailError")
	WebElement emailError;

	/**
	 * Reads the error message when an email is not present.
	 * 
	 * @return String
	 */
	public String readEmailError() {
		AutomationHelper.printMethodName();

		if (emailError.getAttribute("style").equals("display: none;")) {
			return "";
		} else {
			return emailError.getText();
		}
	}

	@FindBy(id = "getSecurityQuestions")
	WebElement submitButtonGetSecurityQuestions;
	@FindBy(id = "submitSecurityQuestions")
	WebElement submitbuttonSubmitSecurityQuestions;

	/**
	 * Clicks the Submit button the Reset Password page
	 */
	public void clickSubmit() {
		AutomationHelper.printMethodName();

		if (submitButtonGetSecurityQuestions.getAttribute("style")
				.equals("display: none;")) {
			submitbuttonSubmitSecurityQuestions.click();
		} else {
			submitButtonGetSecurityQuestions.click();
		}
	}

	@FindBy(id = "secutiryAnswer1")
	WebElement securityQuestion1;

	/**
	 * Sets the Security Question 1 text field.
	 * 
	 * @param text
	 */
	public void setSecurityAnswer1(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(securityQuestion1, text);
	}

	/**
	 * Reads the text inside the Security 1 Answer field.
	 * 
	 * @return String
	 */
	public String readSecurityAnswer1() {
		AutomationHelper.printMethodName();
		return securityQuestion1.getText();
	}

	@FindBy(id = "secutiryAnswer1Error")
	WebElement securityAnswer1Error;

	/**
	 * Reads the Error text
	 * 
	 * @return String
	 */
	public String readSecurityAnswer1Error() {
		AutomationHelper.printMethodName();

		if (securityAnswer1Error.getAttribute("style")
				.equals("display: none;")) {
			return "";
		} else {
			return securityAnswer1Error.getText();
		}
	}

	@FindBy(id = "secutiryAnswer2")
	WebElement securityQuestion2;

	/**
	 * Sets the Security Question 2 text field.
	 * 
	 * @param text
	 */
	public void setSecurityAnswer2(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(securityQuestion2, text);
	}

	/**
	 * Reads the text inside the Security 2 Answer field.
	 * 
	 * @return String
	 */
	public String readSecurityAnswer2() {
		AutomationHelper.printMethodName();
		return securityQuestion2.getText();
	}

	@FindBy(id = "secutiryAnswer2Error")
	WebElement securityAnswer2Error;

	/**
	 * Reads the Error text for Security Question #2
	 * 
	 * @return String
	 */
	public String readSecurityAnswer2Error() {
		AutomationHelper.printMethodName();

		if (securityAnswer2Error.getAttribute("style")
				.equals("display: none;")) {
			return "";
		} else {
			return securityAnswer2Error.getText();
		}
	}

	@FindBy(id = "secutiryAnswer3")
	WebElement securityQuestion3;

	/**
	 * Sets the Security Question 3 text field.
	 * 
	 * @param text
	 */
	public void setSecurityAnswer3(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(securityQuestion3, text);
	}

	/**
	 * Reads the text inside the Security 3 Answer field.
	 * 
	 * @return String
	 */
	public String readSecurityAnswer3() {
		AutomationHelper.printMethodName();
		return securityQuestion3.getText();
	}

	@FindBy(id = "secutiryAnswer3Error")
	WebElement securityAnswer3Error;

	/**
	 * Reads the Error text for Security Question #3
	 * 
	 * @return String
	 */
	public String readSecurityAnswer3Error() {
		AutomationHelper.printMethodName();

		if (securityAnswer3Error.getAttribute("style")
				.equals("display: none;")) {
			return "";
		} else {
			return securityAnswer3Error.getText();
		}
	}

	@FindBy(id = "secutiryAnswersDontMatch")
	WebElement securityAnswersDontMatchError;

	/**
	 * Reads the Security answers don't match error message
	 * 
	 * @return String
	 */
	public String readSecurityAnswersDontMatchError() {
		AutomationHelper.printMethodName();

		if (securityAnswersDontMatchError.getAttribute("style")
				.equals("display: none;")) {
			return "";
		} else {
			return securityAnswersDontMatchError.getText();
		}
	}

	@FindBy(id = "newPassword")
	WebElement newPassword;

	/**
	 * Sets the value for the Enter New Password text field.
	 * 
	 * @param text
	 */
	public void setNewPassword(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(newPassword, text);
	}

	@FindBy(id = "newPasswordError")
	WebElement newPasswordError;
	
	/**
	 * Reads the error that can be associated with the new password
	 * @return String
	 */
	public String readNewPasswordError() {
		AutomationHelper.printMethodName();
		if (newPasswordError.getAttribute("style")
				.equals("display: none;")) {
			return "";
		} else {
			return newPasswordError.getText();
		}
	}
	
	@FindBy(id = "confirmPassword")
	WebElement confirmPassword;
		
	/**
	 * Sets the value for the Confirm Password text field.
	 * 
	 * @param text
	 */
	public void setConfirmPassword(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(confirmPassword, text);
	}
	
	@FindBy(id = "passwordResetConfirmationDiv")
	WebElement passwordConfirmationDiv;
	
	/**
	 * Reads the value of the Password Confirmation div panel
	 * @return String
	 */
	public String readPasswordConfirmation() {
		AutomationHelper.printMethodName();
		return passwordConfirmationDiv.getText();
	}
	
	@FindBy(id = "confirmPasswordError")
	WebElement confirmPasswordError;
	
	/**
	 * Reads the error that can be associated with the confirm password
	 * @return String
	 */
	public String readConfirmPasswordError() {
		AutomationHelper.printMethodName();
		if (confirmPasswordError.getAttribute("style")
				.equals("display: none;")) {
			return "";
		} else {
			return confirmPasswordError.getText();
		}
	}
	
	@FindBy(id = "passwordsDontMatch")
	WebElement passwordsDontMatchError;
	
	/**
	 * Reads the error that can be present when passwords don't match.
	 * @return String
	 */
	public String readPasswordsDontMatchError() {
		AutomationHelper.printMethodName();
		if (passwordsDontMatchError.getAttribute("style")
				.equals("display: none;")) {
			return "";
		} else {
			return passwordsDontMatchError.getText();
		}
	}

	@FindBy(id = "changePassword")
	WebElement resetPasswordButton;
	
	/**
	 * Clicks the Reset Password button
	 */
	public void clickResetPassword() {
		AutomationHelper.printMethodName();
		resetPasswordButton.click();
	}
}
