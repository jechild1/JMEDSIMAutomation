package pageFactories.unitTests.AdminAuditLog;

import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.AdminAuditLogPages.AuditLogPageFactory;
import pageFactories.unitTests.TestBase;

public class AuditLogPageFactoryTest extends TestBase {

	@Test
	public void execute() {
		LoginMod lm = new LoginMod();
		lm.execute("scott_DHA_system_admin@mail.mil");

		HomePageFactory hpf = new HomePageFactory();

		hpf.clickAuditLogInAdmin();

		AuditLogPageFactory auditLog = new AuditLogPageFactory();

		System.out.println(
				"EVENTS TABLE: " + auditLog.getEventTable().readTableRowValue(
						"EventDate", "12/10/2018 3:09:50 PM", "UserId", true));

		auditLog.clickCreateNew();

	}

}
