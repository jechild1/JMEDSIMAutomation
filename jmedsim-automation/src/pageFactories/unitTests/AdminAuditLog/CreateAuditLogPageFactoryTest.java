package pageFactories.unitTests.AdminAuditLog;

import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.AdminAuditLogPages.AuditLogPageFactory;
import pageFactories.Admin.AdminAuditLogPages.CreateAuditLogPageFactory;
import pageFactories.unitTests.TestBase;
import utilities.AutomationHelper;

public class CreateAuditLogPageFactoryTest extends TestBase {
	@Test
	public void someTest() {
		
	
	LoginMod lm = new LoginMod();
	lm.execute("scott_DHA_system_admin@mail.mil");

	HomePageFactory hpf = new HomePageFactory();
	
	hpf.clickAuditLogInAdmin();
	AuditLogPageFactory auditLog = new AuditLogPageFactory();
	auditLog.clickCreateNew();
	
	//Create New Log
	CreateAuditLogPageFactory create = new CreateAuditLogPageFactory();
	
	create.setEventDate("12/11/2018 7:25:00 AM");
	System.out.println("Event Date: " + create.readEventDate());
	
	create.setEventType("Event Type Field");
	System.out.println("Event Type: " + create.readEventType());
	
	create.setEventStatus("Event Status Field");
	System.out.println("Event Status: " + create.readEventStatus());
	
	create.setUserID("User ID Field");
	System.out.println("User ID: " + create.readUserID());
	
	create.setIPAddress("123.456.789.101");
	System.out.println("IP Address: " + create.readIPAddress());
	
	create.setObjectID("Object ID Field");
	System.out.println("Object ID: " + create.readObjectID());
	
	create.setObjectName("Object Name Field");
	System.out.println("Object Name: " + create.readObjectName());
	
	create.setDescription("Description Field");
	System.out.println("Description: " + create.readDescription());
	
	create.clickCreate();
	
	AutomationHelper.wait(15);
	
	
	
	
	
	
	
	
	
	
	}
}
