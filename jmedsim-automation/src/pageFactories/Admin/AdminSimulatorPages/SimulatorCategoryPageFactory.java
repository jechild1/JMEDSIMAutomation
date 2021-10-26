package pageFactories.Admin.AdminSimulatorPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.IndexBase;
import pageFactories.JMEDSimTables;
import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Simulator > Simulator Category page for
 * JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends IndexBase
 * 
 * @author scott.brazelton
 *
 */
public class SimulatorCategoryPageFactory extends IndexBase {
	private static String regexURL = BASE_URL + "SimulatorCategories/Index";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public SimulatorCategoryPageFactory() {
		super(regexURL);
	}

	@FindBy(linkText = "Create New Simulator Category")
	WebElement createNewSimulatorCategoryLink;

	/**
	 * Clicks Create New Simulator Category
	 */
	public void clickCreateNewSimulatorCategory() {
		AutomationHelper.printMethodName();

		createNewSimulatorCategoryLink.click();

	}

	@FindBy(xpath = "//table[@class='table table-striped table-hover table-bordered']")
	WebElement simulatorCategoryTable;

	/**
	 * Returns a reference to the Simulator Category Table.
	 * 
	 * @return JMEDSimTables
	 */
	public JMEDSimTables getSimulatorCategoryTable() {
		return new JMEDSimTables(simulatorCategoryTable);
	}
}
