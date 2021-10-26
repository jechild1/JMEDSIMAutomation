package pageFactories.Admin.AdminSimulationPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.IndexBase;
import pageFactories.JMEDSimTables;

/**
 * Page factory for the Admin > Simulation > Simulator Platform page for
 * JMEDSIM.<br>
 * (1) Finds a reference to objects on the page<br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends IndexBase
 * 
 * @author jesse.childress
 *
 */
public class SimulatorProgrammingPlatformPageFactory extends IndexBase {
	private static String regexURL = BASE_URL + "SimulatorPlatforms/Index.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public SimulatorProgrammingPlatformPageFactory() {
		super(regexURL);
	}
	
	@FindBy(linkText = "Create New Simulator Programming Platform")
	WebElement createNewSimulatorPlatformLink;
	
	/**
	 * Clicks the Create New Simulator Platform link.
	 */
	public void clickCreateNewSimulatorPlatform() {
		createNewSimulatorPlatformLink.click();
	}
	
	@FindBy(xpath = "//table[@class='table table-striped table-hover table-bordered']")
	WebElement simulatorPlatformTable;

	/**
	 * Returns a reference to the Personnel Table.
	 * 
	 * @return JMEDSimTables
	 */
	public JMEDSimTables getSimulatorPlatformTable() {
		return new JMEDSimTables(simulatorPlatformTable);
	}
	

}
