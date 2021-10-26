package pageFactories.unitTests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.HelloUserMenuPages.EditUserProfilePageFacory;
import utilities.AutomationHelper;

public class UserProfilePageFactoryTest {

	@Test
	public void execute() {
		LoginMod lm = new LoginMod();
		lm.execute("jesse_DHA_super_user@mail.mil");
		
		HomePageFactory hpf = new HomePageFactory();
		hpf.clickProfileInHelloUser();
		
		AutomationHelper.waitForPageToLoad(15);
		
		
		EditUserProfilePageFacory eup = new EditUserProfilePageFacory();
		System.out.println(eup.readUserID());
		
		System.out.println(eup.readDisplayName());
		System.out.println(eup.readSite());
		System.out.println(eup.readSecurityQuestion1());
		System.out.println(eup.readSecurityAnswer1());
		System.out.println(eup.readSecurityQuestion2());
		System.out.println(eup.readSecurityAnswer2());
		System.out.println(eup.readSecurityQuestion3());
		System.out.println(eup.readSecurityAnswer3());
		System.out.println(eup.readTermsAndConditions());
		
//		eup.clickSaveButton();
		
		eup.setDisplayName("Test Display Name");
		eup.selectSite("TATRC Sunshine");
		eup.selectSecurityQuestion1("What is your favorite color?");
		eup.setSecurityAnswer1("Blue");
		eup.selectSecurityQuestion2("Who was your childhood hero?");
		eup.setSecurityAnswer2("Jesus");
		eup.selectSecurityQuestion3("What is your father's middle name?");
		eup.setSecurityAnswer3("NMN");
		
		eup.setAgreeToTermsAndConditionsCheckbox(true);
		eup.setAgreeToTermsAndConditionsCheckbox(false);
		
	}
	
	@AfterClass
	public void afterClass() {
		AutomationHelper.finishTest();
	}
}
