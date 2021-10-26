package pageFactories.Admin.AdminSimulationPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.IndexBase;
import pageFactories.JMEDSimTables;
import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Simulation > Training Type page for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends IndexBase
 * 
 * @author scott.brazelton
 *
 */
public class TrainingTypePageFactory extends IndexBase {
	private static String regexURL = BASE_URL + "TrainingTypes/Index";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public TrainingTypePageFactory() {
		super(regexURL);
	}

	@FindBy(linkText = "Create New Training Type")
	WebElement createNewTrainingTypeLink;

	/**
	 * Clicks Create New Training Type
	 */
	public void clickCreateNewTrainingType() {
		AutomationHelper.printMethodName();

		createNewTrainingTypeLink.click();

	}

	@FindBy(xpath = "//table[@class='table table-striped table-hover table-bordered']")
	WebElement trainingTypeTable;

	/**
	 * Returns a reference to the Training Type Table.
	 * 
	 * @return JMEDSimTables
	 */
	public JMEDSimTables getTrainingTypeTable() {
		return new JMEDSimTables(trainingTypeTable);
	}
}
