package pageFactories.Admin.SitesPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.IndexBase;
import pageFactories.JMEDSimTables;
import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Sites page for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends IndexBase
 * 
 * @author scott.brazelton
 *
 */
public class SitesPageFactory extends IndexBase {
	private static String regexURL = BASE_URL + "Sites/Index";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public SitesPageFactory() {
		super(regexURL);
	}

	@FindBy(linkText = "Create New Site")
	WebElement createNewSiteLink;

	/**
	 * Clicks Create New Site
	 */
	public void clickCreateNewSite() {
		AutomationHelper.printMethodName();

		createNewSiteLink.click();

	}

	@FindBy(xpath = "//table[@class='table table-striped table-hover table-bordered']")
	WebElement sitesTable;

	/**
	 * Returns a reference to the Sites Table.
	 * 
	 * @return JMEDSimTables
	 */
	public JMEDSimTables getSitesTable() {
		return new JMEDSimTables(sitesTable);
	}

}
