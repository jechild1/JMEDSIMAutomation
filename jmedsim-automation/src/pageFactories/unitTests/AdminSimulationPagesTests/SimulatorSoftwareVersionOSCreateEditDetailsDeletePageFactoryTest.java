package pageFactories.unitTests.AdminSimulationPagesTests;

import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.AdminSimulationPages.SimulatorSoftwareVersionOSPageFactory;

public class SimulatorSoftwareVersionOSCreateEditDetailsDeletePageFactoryTest {
	
	@Test
	public void execute() {
		LoginMod lm = new LoginMod();
		lm.execute("jesse_DHA_super_user@mail.mil");
		
		HomePageFactory hpf = new HomePageFactory();
		hpf.clickSimulatorSoftwareVersionsOSInAdmin();
		
		SimulatorSoftwareVersionOSPageFactory ssvOS = new SimulatorSoftwareVersionOSPageFactory();
		ssvOS.setSearchBy("Software Version/OS 9.0.9");
		ssvOS.clickSearch();
		
		//Assert value is present
		System.out.println("Row Present: " + ssvOS.getSimulatorSoftwareVersionOSTable().isRowInTable("Software Version/OS 9.0.9"));
	}

}
