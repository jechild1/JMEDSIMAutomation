package pageFactories.unitTests;

import org.testng.annotations.Test;

import pageFactories.LoginPageFactory;
import pageFactories.ResetPasswordPageFactory;
import utilities.AutomationHelper;

public class ForgotPasswordTest extends TestBase{
	
	@Test
	public void execute() {
		LoginPageFactory lpf = new LoginPageFactory();
		lpf.loadPage();

		lpf.clickOkInDODBanner();
		
		lpf.clickForgotYourPassword();	
		
		// Reset Password Page
		// (1) Enter Email on Reset Password Page
		ResetPasswordPageFactory resetPW = new ResetPasswordPageFactory();
		resetPW.setEmail("jesse.r.childress3.ctr@mail.mil");
		resetPW.clickSubmit();
		
		//Display all errors
		resetPW.clickSubmit();
		System.out.println("Error 1: " + resetPW.readSecurityAnswer1Error());
		System.out.println("Error 2: " + resetPW.readSecurityAnswer2Error());
		System.out.println("Error 3: " + resetPW.readSecurityAnswer3Error());
		
		//Error 1
		resetPW.setSecurityAnswer1("Some Text");
		resetPW.clickSubmit();
		System.out.println("Error 1: " + resetPW.readSecurityAnswer1Error());
		
		//Error 2
		resetPW.setSecurityAnswer2("Some Text");
		resetPW.clickSubmit();
		System.out.println("Error 2: " + resetPW.readSecurityAnswer2Error());
		
		//Error 3
		resetPW.setSecurityAnswer3("Some Text");
		resetPW.clickSubmit();
		System.out.println("Error 3: " + resetPW.readSecurityAnswer3Error());
		
		//Answers don't match
		System.out.println("Final Answer: " + resetPW.readSecurityAnswersDontMatchError());

		
		
		
	}
	
	

}
