package pageFactories.Admin.AdminSimulationPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.IndexBase;
import pageFactories.JMEDSimTables;
import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Simulation > Simulator Software Version/OS page
 * for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends IndexBase
 * 
 * @author jesse.childress
 *
 */
public class SimulatorSoftwareVersionOSPageFactory extends IndexBase{
	
	private static String regexURL = BASE_URL + "SimulatorSoftwareVersionOes/Index.*";
	
	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public SimulatorSoftwareVersionOSPageFactory() {
		super(regexURL);
	}
	
	@FindBy (linkText = "Create New Simulator Software Version/OS")
	WebElement createNewSimulatorSoftwareVersionOSLink;
	
	/**
	 * Clicks the Create New Simulator Software Version/OS link
	 */
	public void clickCreateNewSimulatorSoftwareVersionOS() {
		AutomationHelper.printMethodName();
		createNewSimulatorSoftwareVersionOSLink.click();
	}
	
	@FindBy(xpath = "//table[@class='table table-striped table-hover table-bordered']")
	WebElement simulatorSoftwareVersionOSTable;

	/**
	 * Returns a reference to the Simulator Software Version/OS Table.
	 * 
	 * @return JMEDSimTables
	 */
	public JMEDSimTables getSimulatorSoftwareVersionOSTable() {
		return new JMEDSimTables(simulatorSoftwareVersionOSTable);
	}
	
	


}
