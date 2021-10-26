package pageFactories.HelloUserMenuPages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.MenusPageFactory;
import utilities.AutomationHelper;

/**
 * Page factory for the Hello User > Manage Account > Change Password page for
 * JMEDSIM.<br>
 * (1) Finds a reference to objects on the page.<br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends MenusPageFactory
 * 
 * @author jesse.childress
 *
 */
public class ChangePasswordPageFactory extends MenusPageFactory {

	private static String regexURL = BASE_URL + "Manage/ChangePassword";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public ChangePasswordPageFactory() {
		super(regexURL);
	}

	@FindBy(id = "OldPassword")
	WebElement currentPasswordTextfield;

	/**
	 * Sets the Current Password text field with the passed in text.
	 * 
	 * @param text
	 */
	public void setCurrentPassword(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(currentPasswordTextfield, text);
	}

	/**
	 * Clears the text in the Current password field.
	 */
	public void clearCurrentPassword() {
		AutomationHelper.printMethodName();
		currentPasswordTextfield.clear();
	}

	@FindBy(id = "NewPassword")
	WebElement newPasswordTextfield;

	/**
	 * Sets the New Password text field with the passed in text.
	 * 
	 * @param text
	 */
	public void setNewPassword(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(newPasswordTextfield, text);
	}

	/**
	 * Clears the text in the New password field.
	 */
	public void clearNewPassword() {
		AutomationHelper.printMethodName();
		newPasswordTextfield.clear();
	}

	@FindBy(id = "ConfirmPassword")
	WebElement confirmNewPasswordTextfield;

	/**
	 * Sets the Confirm New Password text field with the passed in text.
	 * 
	 * @param text
	 */
	public void setConfirmNewPassword(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(confirmNewPasswordTextfield, text);
	}

	/**
	 * Clears the text in the Confirm new password field.
	 */
	public void clearConfirmNewPassword() {
		AutomationHelper.printMethodName();
		confirmNewPasswordTextfield.clear();
	}

	@FindBy(xpath = "//input[(@value='Change password')]")
	WebElement changePassword;

	/**
	 * Clicks the <i>Change password</i> button
	 */
	public void clickChangePassword() {
		AutomationHelper.printMethodName();
		changePassword.click();
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

	@FindBy(xpath = "//p[@class='text-success']")
	WebElement successMessage;

	/**
	 * Reads the Success message for password changes
	 * 
	 * @return String
	 */
	public String readSuccessMessage() {
		AutomationHelper.printMethodName();
		return successMessage.getText();
	}

}
