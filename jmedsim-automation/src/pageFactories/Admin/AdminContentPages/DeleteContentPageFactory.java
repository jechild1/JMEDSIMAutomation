package pageFactories.Admin.AdminContentPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.AutomationHelper;

public class DeleteContentPageFactory extends ContentDetailsBase {
	private static String regexURL = BASE_URL + "Contents/Delete/.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public DeleteContentPageFactory() {
		super(regexURL);
	}

	/**
	 * Click Delete button
	 */
	public void clickDelete() {
		AutomationHelper.printMethodName();
		clickModifyTypeLinkOrButton();
	}

	@FindBy(xpath = "//dt[text()[normalize-space() = 'Description']]/following-sibling::dd[1]")
	WebElement contentDescriptionDescription;

	/**
	 * Reads "Content Description" text
	 * 
	 * @return String
	 */
	public String readContentDescription() {
		AutomationHelper.printMethodName();
		return contentDescriptionDescription.getText().trim();
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

	@FindBy(xpath = "//dt[text()[normalize-space() = 'UpdatedBy']]/following-sibling::dd[1]")
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

	@FindBy(xpath = "//dt[text()[normalize-space() = 'UpdatedOn']]/following-sibling::dd[1]")
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

}
