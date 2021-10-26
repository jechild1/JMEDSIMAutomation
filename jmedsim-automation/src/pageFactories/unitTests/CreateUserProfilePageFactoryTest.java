package pageFactories.unitTests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.HelloUserMenuPages.CreateUserProfilePageFactory;
import utilities.AutomationHelper;

public class CreateUserProfilePageFactoryTest {
	
	@Test
	public void execute() {
		LoginMod lm = new LoginMod();
		lm.execute("jesse_DHA_super_user@mail.mil");
		
		HomePageFactory hpf = new HomePageFactory();
		hpf.clickProfileInHelloUser();
		
		
		CreateUserProfilePageFactory cup = new CreateUserProfilePageFactory();
		
		System.out.println(cup.readUserID());
		System.out.println(cup.readDisplayName());
		System.out.println(cup.readSite());
		System.out.println(cup.readSecurityQuestion1());
		System.out.println(cup.readSecurityAnswer1());
		System.out.println(cup.readSecurityQuestion2());
		System.out.println(cup.readSecurityAnswer2());
		System.out.println(cup.readSecurityQuestion3());
		System.out.println(cup.readSecurityAnswer3());
		System.out.println(cup.readTermsAndConditions());
		
//		cup.clickSaveButton();
		
		cup.setDisplayName("Test Display Name");
		cup.selectSite("TATRC Sunshine");
		cup.selectSecurityQuestion1("What is your favorite color?");
		cup.setSecurityAnswer1("Blue");
		cup.selectSecurityQuestion2("Who was your childhood hero?");
		cup.setSecurityAnswer2("Jesus");
		cup.selectSecurityQuestion3("What is your father's middle name?");
		cup.setSecurityAnswer3("NMN");
		
		cup.setAgreeToTermsAndConditionsCheckbox(true);
		cup.setAgreeToTermsAndConditionsCheckbox(false);
		
		
		System.out.println(cup.readUserID());
		System.out.println(cup.readDisplayName());
		System.out.println(cup.readSite());
		System.out.println(cup.readSecurityQuestion1());
		System.out.println(cup.readSecurityAnswer1());
		System.out.println(cup.readSecurityQuestion2());
		System.out.println(cup.readSecurityAnswer2());
		System.out.println(cup.readSecurityQuestion3());
		System.out.println(cup.readSecurityAnswer3());
		System.out.println(cup.readTermsAndConditions());
		
	}
	
	@AfterClass
	public void afterClass() {
		AutomationHelper.finishTest();
	}

}
