package pageFactories.Admin.AdminSimulationPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.IndexBase;
import pageFactories.JMEDSimTables;
import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Simulation > Service Role page for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends IndexBase
 * 
 * @author scott.brazelton
 *
 */
public class ServiceRolePageFactory extends IndexBase {
	private static String regexURL = BASE_URL + "ServiceRoles/Index.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public ServiceRolePageFactory() {
		super(regexURL);
	}

	@FindBy(linkText = "Create New Service Role")
	WebElement createNewServiceRoleLink;

	/**
	 * Clicks Create New Service Role
	 */
	public void clickCreateNewServiceRole() {
		AutomationHelper.printMethodName();

		createNewServiceRoleLink.click();

	}

	@FindBy(xpath = "//table[@class='table table-striped table-hover table-bordered']")
	WebElement serviceRoleTable;

	/**
	 * Returns a reference to the Service Role Table.
	 * 
	 * @return JMEDSimTables
	 */
	public JMEDSimTables getServiceRoleTable() {
		return new JMEDSimTables(serviceRoleTable);
	}
}
