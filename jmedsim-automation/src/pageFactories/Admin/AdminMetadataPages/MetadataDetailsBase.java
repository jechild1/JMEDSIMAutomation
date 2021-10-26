package pageFactories.Admin.AdminMetadataPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.DetailsBase;
import utilities.AutomationHelper;

/**
 * Abstract Page factory for the Details/Delete Admin > Metadata > Metadata
 * pages for JMEDSIM.<br>
 * (1) Finds a reference to common objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends DetailsBase
 * 
 * @author scott.brazelton
 *
 */
public abstract class MetadataDetailsBase extends DetailsBase {

	/**
	 * Abstract Page Constructor: Accepts the WebDriver from the calling page,
	 * provides base methods common to create pages, and passes URL to
	 * MenusPageFactory for further validation.
	 * 
	 * Extends DetailsBase
	 */
	public MetadataDetailsBase(String regexURL) {
		super(regexURL);
	}

	@FindBy(xpath = "//dt[text()[normalize-space() = 'Metadata']]/following-sibling::dd[1]")
	WebElement metadataDescription;

	/**
	 * Reads "Metadata" text
	 * 
	 * @return String
	 */
	public String readMetadata() {
		AutomationHelper.printMethodName();
		return metadataDescription.getText().trim();
	}

	@FindBy(xpath = "//dt[text()[normalize-space() = 'Description']]/following-sibling::dd[1]")
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
	
	@FindBy(xpath = "//dt[text()[normalize-space() = 'Additional Info']]/following-sibling::dd[1]")
	WebElement additionalInfoDescription;

	/**
	 * Reads "Additional Info" text
	 * 
	 * @return String
	 */
	public String readAdditionalInfo() {
		AutomationHelper.printMethodName();
		return additionalInfoDescription.getText().trim();
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

	@FindBy(xpath = "//dt[text()[normalize-space() = 'Metadata Category']]/following-sibling::dd[1]")
	WebElement metadataCategoryDescription;

	/**
	 * Reads "Metadata Category" text
	 * 
	 * @return String
	 */
	public String readMetadataCategory() {
		AutomationHelper.printMethodName();
		return metadataCategoryDescription.getText().trim();
	}
}
