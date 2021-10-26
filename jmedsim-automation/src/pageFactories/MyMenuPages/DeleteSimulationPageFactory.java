package pageFactories.MyMenuPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.AutomationHelper;

/**
 * Page factory for the My Menu > Simulation > Delete page for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends DetailsBase
 * 
 * @author scott.brazelton
 *
 */
public class DeleteSimulationPageFactory extends SimulationDetailsBase {
	private static String regexURL = BASE_URL + "Simulations/Delete/.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public DeleteSimulationPageFactory() {
		super(regexURL);
	}

	// TODO: fix when spelling error corrected
	@FindBy(xpath = "//dt[text()[normalize-space() = 'Duration (hours)']]/following-sibling::dd[1]")
	WebElement durationHoursDescription;

	/**
	 * Reads "Duration Hours" text
	 * 
	 * @return String
	 */
	public String readDurationHours() {
		AutomationHelper.printMethodName();
		return durationHoursDescription.getText().trim();
	}

	// TODO: fix when spelling error corrected
	@FindBy(xpath = "//dt[text()[normalize-space() = 'Duration (minutes)']]/following-sibling::dd[1]")
	WebElement durationMinutesDescription;

	/**
	 * Reads "Duration Minutes" text
	 * 
	 * @return String
	 */
	public String readDurationMinutes() {
		AutomationHelper.printMethodName();
		return durationMinutesDescription.getText().trim();
	}

	/**
	 * Click Delete button
	 */
	public void clickDelete() {
		AutomationHelper.printMethodName();
		clickModifyTypeLinkOrButton();
	}

}
