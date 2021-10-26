package testCases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import modularScripts.LoginMod;
import modularScripts.MyMenuSimulation.CreateNewSimulationMod;
import modularScripts.MyMenuSimulation.DeleteSimulationMod;
import modularScripts.MyMenuSimulation.EditSimulationMod;
import modularScripts.MyMenuSimulation.NavigateToMyMenuSimulationMod;
import modularScripts.MyMenuSimulation.VerifySimulationDetailsMod;

/**
 * Test Script
 * 
 * @author scott.brazelton
 *
 */
public class ValidateNewSimulationAfterCreation extends BaseTestScriptConfig {

	/**
	 * This test script creates a new simulation based on the Data Map supplied,
	 * validates the data, and then deletes the simulation. <br>
	 * Pre-Condition: All browsers closed <br>
	 * Post-Condition: All browsers closed. All modules validated
	 * 
	 * @param userName
	 */
	@Test
	@Parameters({"userName", "simulationDataMapID", "validateFieldErrors"})
	public void execute(String userName, String simulationDataMapID,
			boolean validateFieldErrors) {

		// Login
		new LoginMod().execute(userName);

		// Navigate to Simulation table
		new NavigateToMyMenuSimulationMod().execute();

		// Create new Simulation
		new CreateNewSimulationMod().execute(userName, simulationDataMapID,
				validateFieldErrors);

		// Navigate to Simulation table
		new NavigateToMyMenuSimulationMod().execute();

		// Add initial simulation details to Edit Simulation page
		new EditSimulationMod().execute(simulationDataMapID,
				validateFieldErrors, true);

		// Navigate to Simulation table
		new NavigateToMyMenuSimulationMod().execute();

		// Verify details of newly created simulation
		new VerifySimulationDetailsMod().execute(simulationDataMapID, true);

		// Navigate to Simulation table
		new NavigateToMyMenuSimulationMod().execute();

		// Delete simulation
		new DeleteSimulationMod().execute(simulationDataMapID, true);

	}
}
