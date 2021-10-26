package pageFactories.unitTests.AdminSimulationPagesTests;

import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.AdminSimulationPages.SimulatorProgrammingPlatformPageFactory;

public class SimulatorProgrammingPlatformCreateEditDetailsDeletePageFactoryTest {
	
	@Test
	public void execute() {
		LoginMod lm = new LoginMod();
		lm.execute("jesse_DHA_super_user@mail.mil");
		
		HomePageFactory hpf = new HomePageFactory();
		hpf.clickSimulatorProgrammingPlatformInAdmin();
		
		SimulatorProgrammingPlatformPageFactory simPlat = new SimulatorProgrammingPlatformPageFactory();
		simPlat.setSearchBy("Simulator Platform 3");
		simPlat.clickSearch();
		
		//Assert value is present
		System.out.println("Row Present: " + simPlat.getSimulatorPlatformTable().isRowInTable("Simulator Platform 3"));
	}

}
