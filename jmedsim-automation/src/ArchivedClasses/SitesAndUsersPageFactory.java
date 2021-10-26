package ArchivedClasses;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.IndexBase;
import pageFactories.JMEDSimTables;

/**
 * Page factory for the Admin > Users & Roles page for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends IndexBase
 * 
 * @author scott.brazelton
 *
 */
public class SitesAndUsersPageFactory extends IndexBase {
	private static String regexURL = BASE_URL + "UserSites/Index";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public SitesAndUsersPageFactory() {
		super(regexURL);
	}

	@FindBy(xpath = "//table[@class='table  table-striped table-hover table-bordered']")
	WebElement sitesAndUsersTable;

	/**
	 * Returns a reference to the Sites and Users Table.
	 * 
	 * @return JMEDSimTables
	 */
	public JMEDSimTables getSitesAndUsersTable() {
		return new JMEDSimTables(sitesAndUsersTable);
	}
}
