package pageFactories.Admin.AdminSimulatorPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.DetailsBase;
import utilities.AutomationHelper;

/**
 * Abstract Page factory for the Details/Delete Admin > Simulator > Simulator
 * Category Type pages for JMEDSIM.<br>
 * (1) Finds a reference to common objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends DetailsBase
 * 
 * @author scott.brazelton
 *
 */
public abstract class SimulatorCategoryDetailsBase extends DetailsBase {

	/**
	 * Abstract Page Constructor: Accepts the WebDriver from the calling page,
	 * provides base methods common to create pages, and passes URL to
	 * MenusPageFactory for further validation.
	 * 
	 * Extends DetailsBase
	 */
	public SimulatorCategoryDetailsBase(String regexURL) {
		super(regexURL);
	}

	@FindBy(xpath = "//dt[text()[normalize-space() = 'Category']]/following-sibling::dd[1]|//dt[text()[normalize-space() = 'ContentPath']]/following-sibling::dd[1]")
	WebElement categoryDescription;

	/**
	 * Reads "Category" text
	 * 
	 * @return String
	 */
	public String readCategory() {
		AutomationHelper.printMethodName();
		return categoryDescription.getText().trim();
	}

	@FindBy(xpath = "//dt[text()[normalize-space() = 'Description']]/following-sibling::dd[1]|//dt[text()[normalize-space() = 'ContentPath']]/following-sibling::dd[1]")
	WebElement descriptionDescription;

	/**
	 * Reads "Description" text
	 * 
	 * @return String
	 */
	public String readDescription() {
		AutomationHelper.printMethodName();
		return descriptionDescription.getText().trim();
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
