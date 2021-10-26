package pageFactories.MyMenuPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.IndexBase;
import pageFactories.JMEDSimTables;

/**
 * Page factory for the My Menu > Simulations page for JMEDSIM <br>
 * (1) Finds a reference to objects on the page<br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends IndexBase
 * 
 * @author jesse.childress
 */
public class SimulationsPageFactory extends IndexBase {

	private static String regexURL = BASE_URL + "Simulations/Index.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided.
	 */
	public SimulationsPageFactory() {
		super(regexURL);
	}

	@FindBy(linkText = "Create New Simulation")
	WebElement createNewSimulationLink;

	/**
	 * Clicks Create New Simulation
	 */
	public void clickCreateNewSimulation() {
		createNewSimulationLink.click();
	}

	@FindBy(xpath = "//table[@class='table table-striped table-hover table-bordered']")
	WebElement simulationsTable;

	/**
	 * Returns a reference to the Simulations table
	 * 
	 * @return JMEDSimTables
	 */
	public JMEDSimTables getSimulationsTable() {
		return new JMEDSimTables(simulationsTable);
	}
}
