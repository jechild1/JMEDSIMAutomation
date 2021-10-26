package pageFactories.unitTests;

import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.AdminSimulationPages.InitialPhysiologicParametersTemplatePageFactory;
import pageFactories.Admin.AdminSimulationPages.PersonnelPageFactory;
import pageFactories.Admin.AdminSimulationPages.SimulatorSoftwareVersionOSPageFactory;
import pageFactories.Admin.UsersAndRoles.UsersAndRolesPageFactory;
import pageFactories.MyMenuPages.EditSimulationPageFactory;
import pageFactories.MyMenuPages.EditSimulationPageFactory.AdministrativeDetailsTab;
import pageFactories.MyMenuPages.SimulationsPageFactory;

public class JesseTest {

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		
//		System.out.println(LocalDate.now().format(DateTimeFormatter.ofPattern("M/d/yyyy")));
//		
//		String date = "04/16/2019 7:58:00AM";
//		
//		System.out.println(date.substring(0, date.indexOf(" ")));
//
//	}
	
	@Test
	public void execute() {
		
		String userName = "jesse_DHA_super_user@mail.mil";
		
		LoginMod lm = new LoginMod();
		lm.execute(userName);
		
		HomePageFactory hp = new HomePageFactory();
//		hp.clickInitialPhysiologicParametersTemplateInAdmin();
//		InitialPhysiologicParametersTemplatePageFactory initial = new InitialPhysiologicParametersTemplatePageFactory();
//		initial.getInitialPhysiologicParametersTemplateTable().readTablePageNumber();
		
//		hp.clickSimulatorSoftwareVersionsOSInAdmin();
//		SimulatorSoftwareVersionOSPageFactory swOs = new SimulatorSoftwareVersionOSPageFactory();
//		System.out.println(swOs.getSimulatorSoftwareVersionOSTable().readTablePageNumber());
		
		hp.clickSimulationInMyMenu();
		
		SimulationsPageFactory sp = new SimulationsPageFactory();
		
		sp.getSimulationsTable().clickLinkInRow("Chemical Attack", "Edit");
		
		EditSimulationPageFactory editSim = new EditSimulationPageFactory();
		
		editSim.getAdministrativeDetailsTab().getServiceRoleTable().isRowInTable("60A");
		
		
		
	}

}
