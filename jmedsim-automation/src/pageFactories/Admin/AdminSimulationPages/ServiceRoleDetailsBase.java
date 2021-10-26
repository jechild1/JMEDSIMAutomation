package pageFactories.Admin.AdminSimulationPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.DetailsBase;
import utilities.AutomationHelper;

/**
 * Abstract Page factory for the Details/Delete Admin > Simulation > Service
 * Role pages for JMEDSIM.<br>
 * (1) Finds a reference to common objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends DetailsBase
 * 
 * @author scott.brazelton
 *
 */
public abstract class ServiceRoleDetailsBase extends DetailsBase {

	/**
	 * Abstract Page Constructor: Accepts the WebDriver from the calling page,
	 * provides base methods common to create pages, and passes URL to
	 * MenusPageFactory for further validation.
	 * 
	 * Extends DetailsBase
	 */
	public ServiceRoleDetailsBase(String regexURL) {
		super(regexURL);
	}

	@FindBy(xpath = "//dt[text()[normalize-space() = 'Service Code']]/following-sibling::dd[1]")
	WebElement serviceCodeDescription;

	/**
	 * Reads "Service Code" text
	 * 
	 * @return String
	 */
	public String readServiceCode() {
		AutomationHelper.printMethodName();
		return serviceCodeDescription.getText().trim();
	}

	@FindBy(xpath = "//dt[text()[normalize-space() = 'Service Role']]/following-sibling::dd[1]")
	WebElement serviceRoleDescription;

	/**
	 * Reads "Service Role" text
	 * 
	 * @return String
	 */
	public String readServiceRole() {
		AutomationHelper.printMethodName();
		return serviceRoleDescription.getText().trim();
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
