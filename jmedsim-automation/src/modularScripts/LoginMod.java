
package modularScripts;

import static org.testng.Assert.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Reporter;

import configuration.JMEDConfig;
import pageFactories.LoginPageFactory;
import pageFactories.HelloUserMenuPages.ChangePasswordPageFactory;
import pageFactories.HelloUserMenuPages.ManageAccountPageFactory;
import utilities.AutomationHelper;
import utilities.ExcelDataConfig;
import utilities.PasswordUtils;

/**
 * Module Test Script
 * 
 * @author scott.brazelton
 *
 */
public class LoginMod extends JMEDConfig {
	
	String userName;
	/**
	 * This test script module opens JMEDSIM, verifies the Government Message,
	 * and then logs in
	 * 
	 * Pre-Condition: All browsers closed & UsersData.xlsx file exists with the
	 * following columns: a. UserName b. Password c. Site d. Type
	 * 
	 * Post-Condition: On JMEDSIM main page
	 * 
	 * @param userName
	 */
	public void execute(String userName) {
		this.userName = userName;
		AutomationHelper.printClassName();
		AutomationHelper.printMethodName();

		ExcelDataConfig ud = getExcelFile("UsersData.xlsx", "JMEDUserData");

		// using UserName instead of default as column key
		ud.setColumnHeaderKey("UserName");
		String password = ud.getData(userName, "Password");
		String type = ud.getData(userName, "Type");

		ExcelDataConfig stv = getExcelFile("JMEDContentMatrix.xlsx",
				"StaticTextValidations");

		LoginPageFactory lpf = new LoginPageFactory();
		lpf.loadPage();

		softAsserter.assertEquals(lpf.readPageTitle(),
				stv.getData("LPTitle", "Title"), "Validating Login Page Title");

		lpf.clickOkInDODBanner();

		lpf.setEmail(userName);

		lpf.setPassword(password);

		lpf.clickLogin();
		
		//To address the STIG in which passwords expire every 60 days, we must see if we land on the Change Password page.
		if(lpf.readPageTitle().equals("Change Password")) {
			updatePassword(userName, password);
		}

		// if no longer on login page, write date/time logged in and return
		if (!lpf.readPageTitle().toLowerCase().equals("log in")) {
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH.mm.ss");
			Date date = new Date();

			ud.writeToWorkSheet(userName, "LastLoginDate", df.format(date));
			return;
		}

		// now check for errors if still on login page
		String emailError = lpf.readEmailError();
		String passwordError = lpf.readPasswordError()
				+ lpf.readSummaryErrors();

		if (!emailError.isEmpty()) {
			softAsserter.assertEquals(emailError, stv.getData(type, "Content"),
					"Validating email error");
		} else {
			softAsserter.assertEquals(passwordError,
					stv.getData(type, "Content"), "Validating password error");
		}
	}
	
	/**
	 * Utility method to update a password after password expires (60 days). This is
	 * due to a STIG item which requires passwords to expire every 60 days.
	 * 
	 * @param userName
	 * @param originalPassword
	 */
	private void updatePassword(String userName, String originalPassword) {
		AutomationHelper.printMethodName();
		Reporter.log("***Password has expired. Updating password***", true);
		ChangePasswordPageFactory changePassword = new ChangePasswordPageFactory();
		
		assertEquals(changePassword.readSummaryErrors(), "Your password has expired.", "Password expired error message");
		String newPassword = PasswordUtils.generateRandomPassword(15);
		String confirmNewPassword = newPassword;
		
		Reporter.log("New Password Generated: " + newPassword, true);
		
		changePassword.setCurrentPassword(originalPassword);
		changePassword.setNewPassword(newPassword);
		changePassword.setConfirmNewPassword(confirmNewPassword);
		
		changePassword.clickChangePassword();
		
		//Validate Success
		ManageAccountPageFactory manageAccount = new ManageAccountPageFactory();
		ExcelDataConfig usersFile = getExcelFile("UsersData.xlsx", "JMEDUserData");

		assertEquals(manageAccount.readSuccessLabel(), "Your password has been changed.", "Password changed successfully.");
		
		//Write new values to datasheet
		String previousPassword1 = usersFile.getData(usersFile.getRowIndex("UserName", userName), usersFile.getColumnIndex("PreviousPassword1"));
	
		//Place Previous Password into PreviousPassword2
		usersFile.writeToWorkSheet(usersFile.getRowIndex("UserName", userName), usersFile.getColumnIndex("PreviousPassword2"), previousPassword1);

		//Place the current password into PreviousPassword1
		usersFile.writeToWorkSheet(usersFile.getRowIndex("UserName", userName), usersFile.getColumnIndex("PreviousPassword1"), originalPassword);

		//Place the new password into the Password field.
		usersFile.writeToWorkSheet(usersFile.getRowIndex("UserName", userName), usersFile.getColumnIndex("Password"), newPassword);
		
		
		
		
	}

}
