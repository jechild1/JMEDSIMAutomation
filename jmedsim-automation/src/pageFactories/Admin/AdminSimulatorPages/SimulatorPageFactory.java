package pageFactories.Admin.AdminSimulatorPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.IndexBase;
import pageFactories.JMEDSimTables;
import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Simulator > Simulator page for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends IndexBase
 * 
 * @author scott.brazelton
 *
 */
public class SimulatorPageFactory extends IndexBase {
	private static String regexURL = BASE_URL + "Simulators/Index";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public SimulatorPageFactory() {
		super(regexURL);
	}

	@FindBy(linkText = "Create New Simulator")
	WebElement createNewSimulatorLink;

	/**
	 * Clicks Create New Simulator
	 */
	public void clickCreateNewSimulator() {
		AutomationHelper.printMethodName();

		createNewSimulatorLink.click();

	}

	@FindBy(xpath = "//table[@class='table table-striped table-hover table-bordered']")
	WebElement simulatorTable;

	/**
	 * Returns a reference to the Simulator Table.
	 * 
	 * @return JMEDSimTables
	 */
	public JMEDSimTables getSimulatorTable() {
		return new JMEDSimTables(simulatorTable);
	}
}
