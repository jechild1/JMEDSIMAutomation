package pageFactories.MyMenuPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.DetailsBase;
import utilities.AutomationHelper;

/**
 * Abstract Page factory for the Details/Delete My Menu > Simulation type pages
 * for JMEDSIM.<br>
 * (1) Finds a reference to common objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends DetailsBase
 * 
 * @author scott.brazelton
 *
 */
public abstract class SimulationDetailsBase extends DetailsBase {
	/**
	 * Abstract Page Constructor: Accepts the WebDriver from the calling page,
	 * provides base methods common to create pages, and passes URL to
	 * MenusPageFactory for further validation.
	 * 
	 * Extends DetailsBase
	 */
	public SimulationDetailsBase(String regexURL) {
		super(regexURL);
	}

	@FindBy(xpath = "//dt[text()[normalize-space() = 'Title']]/following-sibling::dd[1]")
	WebElement titleDescription;

	/**
	 * Reads "Title" text
	 * 
	 * @return String
	 */
	public String readTitle() {
		AutomationHelper.printMethodName();
		return titleDescription.getText().trim();
	}

	// TODO: fix when spelling error corrected
	@FindBy(xpath = "//dt[text()[normalize-space() = 'Duration (hh:mm)']]/following-sibling::dd[1]")
	WebElement durationDescription;

	/**
	 * Reads "Duration" text
	 * 
	 * @return String
	 */
	public String readDuration() {
		AutomationHelper.printMethodName();
		return durationDescription.getText().trim();
	}

	@FindBy(xpath = "//dt[text()[normalize-space() = 'Author']]/following-sibling::dd[1]")
	WebElement authorDescription;

	/**
	 * Reads "Author" text
	 * 
	 * @return String
	 */
	public String readAuthor() {
		AutomationHelper.printMethodName();
		return authorDescription.getText().trim();
	}

	@FindBy(xpath = "//dt[text()[normalize-space() = 'Contact Information']]/following-sibling::dd[1]")
	WebElement contactInformationDescription;

	/**
	 * Reads "Contact Information" text
	 * 
	 * @return String
	 */
	public String readContactInformation() {
		AutomationHelper.printMethodName();
		return contactInformationDescription.getText().trim();
	}

	@FindBy(xpath = "//dt[text()[normalize-space() = 'Created By']]/following-sibling::dd[1]")
	WebElement createdByDescription;

	/**
	 * Reads "Created By" text
	 * 
	 * @return String
	 */
	public String readCreatedBy() {
		AutomationHelper.printMethodName();
		return createdByDescription.getText().trim();
	}

	@FindBy(xpath = "//dt[text()[normalize-space() = 'Created On']]/following-sibling::dd[1]")
	WebElement createdOnDescription;

	/**
	 * Reads "Created On" text
	 * 
	 * @return String
	 */
	public String readCreatedOn() {
		AutomationHelper.printMethodName();
		return createdOnDescription.getText().trim();
	}

	@FindBy(xpath = "//dt[text()[normalize-space() = 'Content Keywords']]/following-sibling::dd[1]")
	WebElement contentKeywordsDescription;
	/**
	 * Reads "Content Keywords" text
	 * 
	 * @return String
	 */
	public String readContentKeywords() {
		AutomationHelper.printMethodName();
		return contentKeywordsDescription.getText().trim();
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

	@FindBy(xpath = "//dt[text()[normalize-space() = 'Updated By']]/following-sibling::dd[1]")
	WebElement updatedByDescription;

	/**
	 * Reads "Updated By" text
	 * 
	 * @return String
	 */
	public String readUpdatedBy() {
		AutomationHelper.printMethodName();
		return updatedByDescription.getText().trim();
	}

	@FindBy(xpath = "//dt[text()[normalize-space() = 'Updated On']]/following-sibling::dd[1]")
	WebElement updatedOnDescription;

	/**
	 * Reads "Updated On" text
	 * 
	 * @return String
	 */
	public String readUpdatedOn() {
		AutomationHelper.printMethodName();
		return updatedOnDescription.getText().trim();
	}

	@FindBy(xpath = "//dt[text()[normalize-space() = 'Training Type']]/following-sibling::dd[1]")
	WebElement trainingTypeDescription;

	/**
	 * Reads "Training Type" text
	 * 
	 * @return String
	 */
	public String readTrainingType() {
		AutomationHelper.printMethodName();
		return trainingTypeDescription.getText().trim();
	}

}
