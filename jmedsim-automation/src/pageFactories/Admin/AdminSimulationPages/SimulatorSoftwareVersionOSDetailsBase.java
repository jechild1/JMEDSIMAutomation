package pageFactories.Admin.AdminSimulationPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.DetailsBase;
import utilities.AutomationHelper;

/**
 * Abstract Page factory for the Details/Delete page of Admin > Simulation >
 * Simulator Software Version/OS Details pages for JMEDSIM.<br>
 * (1) Finds a reference to common objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends DetailsBase
 * 
 * @author jesse.childress
 *
 */
public class SimulatorSoftwareVersionOSDetailsBase extends DetailsBase {
	
	/**
	 * Abstract Page Constructor: Accepts the WebDriver from the calling page,
	 * provides base methods common to create pages, and passes URL to
	 * MenusPageFactory for further validation.
	 * 
	 * Extends DetailsBase
	 */
	public SimulatorSoftwareVersionOSDetailsBase(String regexURL) {
		super(regexURL);
	}
	
	@FindBy(xpath = "//dt[text()[normalize-space() = 'Software Version/OS']]/following-sibling::dd[1]")
	WebElement softwareVersionOS;

	/**
	 * Reads "Software Version/OS" text
	 * 
	 * @return String
	 */
	public String readSoftwareVersionOS() {
		AutomationHelper.printMethodName();
		return softwareVersionOS.getText().trim();
	}
	
	@FindBy(xpath = "//dt[text()[normalize-space() = 'Description']]/following-sibling::dd[1]")
	WebElement description;

	/**
	 * Reads "Description" text
	 * 
	 * @return String
	 */
	public String readDescription() {
		AutomationHelper.printMethodName();
		return description.getText().trim();
	}

	@FindBy(xpath = "//dt[text()[normalize-space() = 'Active']]/following-sibling::dd[1]/input")
	WebElement activeCheckbox;

	/**
	 * Returns if Active is checked or not
	 * 
	 * @return boolean - true = checked | false = not checked
	 */
	public boolean isActiveChecked() {
		AutomationHelper.printMethodName();
		return activeCheckbox.isSelected();
	}

}
