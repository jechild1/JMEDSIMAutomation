package pageFactories.Admin.AdminSimulationPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.JMEDSimTables;
import pageFactories.MenusPageFactory;
import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Simulation > Initial Physiologic Parameters
 * Template for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends MenusPageFactory (Not Index base, due to no search search
 * Functionality at the top)
 * 
 * @author jesse.childress
 *
 */
public class InitialPhysiologicParametersTemplatePageFactory extends MenusPageFactory {

	private static String regexURL = BASE_URL + "InitialPhysiologicParametersTemplates/Index";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public InitialPhysiologicParametersTemplatePageFactory() {
		super(regexURL);
	}

	@FindBy(linkText = "Create New Initial Physiologic Parameters Template")
	WebElement createNewInitialPhysiologicParametersTemplateLink;

	/**
	 * Clicks the Create New Initial Physiologic Parameters Template link.
	 */
	public void clickCreateInitialPhysiologicParametersTemplate() {
		AutomationHelper.printMethodName();
		createNewInitialPhysiologicParametersTemplateLink.click();
	}

	@FindBy(xpath = "//table[@class='table table-striped table-hover table-bordered']")
	WebElement initialPhysiologicParametersTemplateTable;

	/**
	 * Returns a reference to the Personnel Table.
	 * 
	 * @return JMEDSimTables
	 */
	public JMEDSimTables getInitialPhysiologicParametersTemplateTable() {
		return new JMEDSimTables(initialPhysiologicParametersTemplateTable);
	}

}
