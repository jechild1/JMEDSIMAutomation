package testCases;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.LoginPageFactory;
import pageFactories.ResetPasswordPageFactory;
import utilities.AutomationHelper;
import utilities.ExcelDataConfig;

/**
 * This test ensures that a user can answer their password can be reset via the
 * Forgot Your Password link on the login page.
 * <ul>
 * <li>Launches JMedSim and clicks the OK in DOD Banner, and then proceeds to
 * click the "Forgot your password?" link.
 * <li>Enters the email address for the password reset, and asserts errors fire
 * when necessary.
 * <li>Answer all the email security questions and assert the errors fire.
 * <li>Set the new password and confirm the success message and assert the
 * errors fire. Store new password in datafile.
 * <li>Close the browser and re-launch the application. Ensure the new username
 * / password combination works.
 * <li>Close the application again.
 * 
 * <li>Launches JMedSim and clicks the OK in DOD Banner, and then proceeds to
 * click the "Forgot your password?" link, a second time.
 * <li>Enters the email address for the password reset, and asserts errors fire
 * when necessary.
 * <li>Answer all the email security questions and assert the errors fire.
 * <li>Set the new password back to the original and confirm the success message
 * and assert the errors fire. Store original password in datafile.
 * <li>Close the browser and re-launch the application. Ensure the original
 * username / password combination works.
 * 
 * @author jesse.childress
 *
 */
public class ForgotPassword extends BaseTestScriptConfig {
	
	public static String tempNewPassword = "!TemporaryNewPassword123";
	public static String tempConfirmNewPassword = "!TemporaryNewPassword123";
	public static String originalEmail;
	public static String originalPassword;
	
		
	/**
	 * This test ensures that the password can be reset for a user and that a
	 * user can successfully login after resetting. It then changes the password
	 * back to the original. It also handles asserting error validation.
	 * 
	 * @param email
	 * @param password
	 */
	@Test (dataProvider = "getAccount")
	public void performPasswordReset(String email, String password ) {
		
		originalEmail = email;
		originalPassword = password;
		
		//Launch the application and navigate to Reset Password page
		loginAndNavigateToResetPassword();
		
		//Instantiate ResetPW page.
		ResetPasswordPageFactory resetPW = new ResetPasswordPageFactory();
		
		//Create a reference to the Error Messages datasheet
		ExcelDataConfig errorFile = getExcelFile("JMEDContentMatrix.xlsx", "StaticTextValidations");
		
		// RESET PASSWORD PAGE
		
		// (1) Attempt submission without email
		// (2) Enter Email on Reset Password Page
		enterEmailForPasswordReset( resetPW, errorFile);
		
		
		// (3) Validate errors when no security answers entered
		// (4) Validate error when invalid answer was entered
		// (5) Set valid answers
		answerSecurityQuestions(resetPW, errorFile);

		// (6) Set New Password, validate errors, and store new password in data sheet
		setNewPasswordAndConfirmSuccess(resetPW, errorFile, tempNewPassword);

		// (7) Close the browser after password updated
		driver.close();
		
		// (8) Ensure new password works after changing
		LoginMod login = new LoginMod();
		login.execute(email);
		driver.close();
		
		// (9) Reset password back to original		
		loginAndNavigateToResetPassword();
		
		// (10) Perform all checks again with new password
		//Reset reference to prevent session errors
		resetPW = new ResetPasswordPageFactory();
		enterEmailForPasswordReset(resetPW, errorFile);
		answerSecurityQuestions(resetPW, errorFile);
		setNewPasswordAndConfirmSuccess(resetPW, errorFile, originalPassword);
		
		// (11) Close browser after password updated (back to original)
		driver.close();
		
		// (12) Ensure new password works after changing (back to original)
		login = new LoginMod();
		login.execute(email);


		
	}
	
	@DataProvider
	private String[][] getAccount() {
		
		String emailAccount = "jesse.r.childress3.ctr@mail.mil";
		
		AutomationHelper.printMethodName();

		ExcelDataConfig dataFile = getExcelFile("UsersData.xlsx", "JMedUserData");
		
		int rowIndex = dataFile.getRowIndex("UserName", emailAccount);
		int colIndex = dataFile.getColumnIndex("Password");
		
		String password = dataFile.getData(rowIndex, colIndex);
		
		String[][] credentials = new String[][]{{emailAccount , password}};
		
		return credentials;
	}
	
	/**
	 * Launches JMedSim and clicks the OK in DOD Banner, and then proceeds to
	 * click the "Forgot your password?" link. <br>
	 * Note: This method is necessary because the modular script of LoginMod
	 * attempts to set teh user name / password and then proceed. That is not
	 * needed here as we simply click the <i>Forgot your password?</i> link.
	 * 
	 */
	private void loginAndNavigateToResetPassword() {

		LoginPageFactory lpf = new LoginPageFactory();
		lpf.loadPage();

		lpf.clickOkInDODBanner();

		lpf.clickForgotYourPassword();
	}
	
	/**
	 * Enters the email address for the password reset, and asserts errors fire
	 * when necessary.
	 * 
	 * @param resetPW
	 * @param errorFile
	 */
	private void enterEmailForPasswordReset(ResetPasswordPageFactory resetPW, ExcelDataConfig errorFile) {
		// RESET PASSWORD PAGE
		// (1) Attempt submission without email
		resetPW.clickSubmit();
		assertEquals(resetPW.readEmailError(), errorFile.getData(errorFile.getRowIndex("TestDataID", "RESETPW_EmailRequired"), errorFile.getColumnIndex("Content")), "Email Required Error");
		
		// (2) Enter Email on Reset Password Page
		resetPW.setEmail(originalEmail);
		resetPW.clickSubmit();
	}
	
	/**
	 * Answer all the email security questions and assert the errors fire.
	 * 
	 * @param resetPW
	 * @param errorFile
	 */
	private void answerSecurityQuestions(ResetPasswordPageFactory resetPW, ExcelDataConfig errorFile) {

		// (3) Validate errors when no security answers entered
		resetPW.clickSubmit();
		assertEquals(resetPW.readSecurityAnswer1Error(), errorFile.getData(errorFile.getRowIndex("TestDataID", "RESETPW_Answer1Required"), errorFile.getColumnIndex("Content")), "Security Answer 1 Error");
		assertEquals(resetPW.readSecurityAnswer2Error(), errorFile.getData(errorFile.getRowIndex("TestDataID", "RESETPW_Answer2Required"), errorFile.getColumnIndex("Content")), "Security Answer 2 Error");
		assertEquals(resetPW.readSecurityAnswer3Error(), errorFile.getData(errorFile.getRowIndex("TestDataID", "RESETPW_Answer3Required"), errorFile.getColumnIndex("Content")), "Security Answer 3 Error");
		
		// (4) Validate error when invalid answer was entered
		resetPW.setSecurityAnswer1("Answer 1 - not real");
		resetPW.setSecurityAnswer2("Answer 2 - not real");
		resetPW.setSecurityAnswer3("Answer 3 - not real");
		resetPW.clickSubmit();
		assertEquals(resetPW.readSecurityAnswersDontMatchError(), errorFile.getData(errorFile.getRowIndex("TestDataID", "RESETPW_SecurityAnswersDontMatch"), errorFile.getColumnIndex("Content")), "Security Answers do not match");
		
		// (5) Set valid answers
		ExcelDataConfig profileDataPoints = getExcelFile("UsersData.xlsx", "UserProfile");
		String securityAnswer1 = profileDataPoints.getData(profileDataPoints.getRowIndex("UserName", originalEmail), profileDataPoints.getColumnIndex("SecurityAnswer1"));
		String securityAnswer2 = profileDataPoints.getData(profileDataPoints.getRowIndex("UserName", originalEmail), profileDataPoints.getColumnIndex("SecurityAnswer2"));
		String securityAnswer3 = profileDataPoints.getData(profileDataPoints.getRowIndex("UserName", originalEmail), profileDataPoints.getColumnIndex("SecurityAnswer3"));
		
		resetPW.setSecurityAnswer1(securityAnswer1);
		resetPW.setSecurityAnswer2(securityAnswer2);
		resetPW.setSecurityAnswer3(securityAnswer3);
		resetPW.clickSubmit();
	}
	
	/**
	 * Set the new password and confirm the success message and assert the errors fire. Store new password in datafile.
	 * 
	 * @param resetPW
	 * @param errorFile
	 * @param password
	 */
	private void setNewPasswordAndConfirmSuccess(ResetPasswordPageFactory resetPW, ExcelDataConfig errorFile, String password) {
		
		//TODO - Add error checks here
		//Click Reset Password without anything to ensure that the errors fire
		resetPW.clickResetPassword();
		assertEquals(resetPW.readNewPasswordError(), errorFile.getData(errorFile.getRowIndex("TestDataID", "RESETPW_PasswordIsRequired"), errorFile.getColumnIndex("Content")), "New Password Error Validation");
		assertEquals(resetPW.readConfirmPasswordError(), errorFile.getData(errorFile.getRowIndex("TestDataID", "RESETPW_ConfirmPassword"), errorFile.getColumnIndex("Content")), "Confirm New Password Error Validation");

		//Set non matching values in password fields
		resetPW.setNewPassword("ABC");
		resetPW.setConfirmPassword("123");
		resetPW.clickResetPassword();
		assertEquals(resetPW.readPasswordsDontMatchError(), errorFile.getData(errorFile.getRowIndex("TestDataID", "RESETPW_PasswordsDontMatch"), errorFile.getColumnIndex("Content")), "Passwords Do Not Match validation");

		//Continue with legitimate test
		// (6) Set new password and ensure success message
		resetPW.setNewPassword(password);
		resetPW.setConfirmPassword(password);

		resetPW.clickResetPassword();

		// Check password success message
		assertEquals(resetPW.readPasswordConfirmation(), errorFile.getData(errorFile.getRowIndex("TestDataID", "RESETPW_ResetConfirmation"), errorFile.getColumnIndex("Content")), "Password change reset successful");

		// Store new password in datasheet
		ExcelDataConfig dataFile = getExcelFile("UsersData.xlsx", "JMedUserData"); dataFile.writeToWorkSheet(dataFile.getRowIndex("UserName", originalEmail), dataFile.getColumnIndex("Password"), password);
	}
	
	

}
