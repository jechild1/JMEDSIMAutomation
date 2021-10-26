package pageFactories.Admin.UsersAndRoles;

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
public class UsersAndRolesPageFactory extends IndexBase {
	private static String regexURL = BASE_URL + "UserRoles/Index";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public UsersAndRolesPageFactory() {
		super(regexURL);
	}

	@FindBy(xpath = "//table[@class='table  table-striped table-hover table-bordered']")
	WebElement usersAndRolesTable;

	/**
	 * Returns a reference to the Users and Roles Table.
	 * 
	 * @return JMEDSimTables
	 */
	public JMEDSimTables getUsersAndRolesTable() {
		return new JMEDSimTables(usersAndRolesTable);
	}

}
