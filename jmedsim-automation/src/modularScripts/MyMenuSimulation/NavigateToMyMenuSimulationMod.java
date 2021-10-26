package modularScripts.MyMenuSimulation;

import configuration.JMEDConfig;
import pageFactories.HomePageFactory;
import pageFactories.MyMenuPages.SimulationsPageFactory;
import utilities.AutomationHelper;

/**
 * Module Test Script
 * 
 * @author scott.brazelton
 *
 */
public class NavigateToMyMenuSimulationMod extends JMEDConfig {

	/**
	 * This test script module navigates to My Menu > Simulation <br>
	 * Pre-Condition: Logged into JmedSIM and top menu accessible <br>
	 * Post-Condition: On My Menu > Simulation Page
	 * 
	 * @param userName
	 */
	public void execute() {
		AutomationHelper.printClassName();
		AutomationHelper.printMethodName();

		new HomePageFactory().clickSimulationInMyMenu();

		validateStaticTitle("MMSPTitle",
				new SimulationsPageFactory().readPageTitle(), "Simulation Page",
				"Page title");
	}

}
