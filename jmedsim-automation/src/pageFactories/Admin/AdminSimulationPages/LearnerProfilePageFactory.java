package pageFactories.Admin.AdminSimulationPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.IndexBase;
import pageFactories.JMEDSimTables;
import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Simulation > Learner Profile page for
 * JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends IndexBase
 * 
 * @author scott.brazelton
 *
 */
public class LearnerProfilePageFactory extends IndexBase {
	private static String regexURL = BASE_URL + "LearnerProfiles/Index.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public LearnerProfilePageFactory() {
		super(regexURL);
	}

	@FindBy(linkText = "Create New Learner Profile")
	WebElement createNewLearnerProfileLink;

	/**
	 * Clicks Create New Learner Profile
	 */
	public void clickCreateNewLearnerProfile() {
		AutomationHelper.printMethodName();

		createNewLearnerProfileLink.click();
		waitForPageToLoad();

	}

	@FindBy(xpath = "//table[@class='table table-striped table-hover table-bordered']")
	WebElement learnerProfileTable;

	/**
	 * Returns a reference to the Learner Profile Table.
	 * 
	 * @return JMEDSimTables
	 */
	public JMEDSimTables getLearnerProfileTable() {
		return new JMEDSimTables(learnerProfileTable);
	}

}
