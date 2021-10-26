package pageFactories.Admin.AdminSimulationPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.IndexBase;
import pageFactories.JMEDSimTables;
import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Simulation > Personnel page for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends IndexBase
 * 
 * @author scott.brazelton
 *
 */
public class PersonnelPageFactory extends IndexBase {
	private static String regexURL = BASE_URL + "Personnels/Index.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public PersonnelPageFactory() {
		super(regexURL);
	}

	@FindBy(linkText = "Create New Personnel")
	WebElement createNewPersonnelLink;

	/**
	 * Clicks Create New Personnel
	 */
	public void clickCreateNewPersonnel() {
		AutomationHelper.printMethodName();

		createNewPersonnelLink.click();

	}

	@FindBy(xpath = "//table[@class='table table-striped table-hover table-bordered']")
	WebElement personnelTable;

	/**
	 * Returns a reference to the Personnel Table.
	 * 
	 * @return JMEDSimTables
	 */
	public JMEDSimTables getPersonnelTable() {
		return new JMEDSimTables(personnelTable);
	}
}
