package pageFactories.Admin.AdminMetadataPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.ModifyBase;
import utilities.AutomationHelper;

/**
 * Abstract Page factory for Create/Edit "Admin > Metadata > Metadata" pages
 * that can be found in JMedSIM.<br>
 * (1) Finds a reference to common objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifyBase
 * 
 * @author scott.brazelton
 *
 */
public abstract class ModifyMetadataBase extends ModifyBase {

	/**
	 * Abstract Create/Edit Page Constructor: Accepts the WebDriver from the
	 * calling page, provides base methods common to create pages, and passes
	 * URL to ModifyPageFactoryBase for further validation.
	 * 
	 * Extends ModifyBase
	 */
	public ModifyMetadataBase(String regexURL) {
		super(regexURL);
	}

	@FindBy(id = "MetadataCategoryId")
	WebElement metadataCategoryDropdown;

	/**
	 * Select text in "Metadata Category" dropdown.
	 * 
	 * @param text
	 */
	public void selectMetadataCategory(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.selectDropdownItem(metadataCategoryDropdown, text);
	}

	/**
	 * Reads Metadata Category selected text
	 * 
	 * @return String
	 */
	public String readMetadataCategorySelected() {
		AutomationHelper.printMethodName();
		return AutomationHelper.readSelectedSubItem(metadataCategoryDropdown);
	}

	/**
	 * Reads Metadata Category error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readMetadataCategoryErrorMessage() {
		AutomationHelper.printMethodName();
		return readErrorMessageForField(
				metadataCategoryDropdown.getAttribute("id"));
	}

	@FindBy(id = "MetadataDetailsName")
	WebElement metadataTextfield;

	/**
	 * Set text in field "Metadata".
	 * 
	 * @param text
	 */
	public void setMetadata(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(metadataTextfield, text);
	}

	/**
	 * Reads Metadata text
	 * 
	 * @return String
	 */
	public String readMetadata() {
		AutomationHelper.printMethodName();
		return metadataTextfield.getAttribute("value");
	}

	/**
	 * Reads Metadata error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readMetadataErrorMessage() {
		AutomationHelper.printMethodName();
		return readErrorMessageForField(metadataTextfield.getAttribute("id"));
	}

	@FindBy(id = "MetadataDetailsDescription")
	WebElement descriptionTextfield;

	/**
	 * Set text in field "Description".
	 * 
	 * @param text
	 */
	public void setDescription(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(descriptionTextfield, text);
	}

	/**
	 * Reads Description text
	 * 
	 * @return String
	 */
	public String readDescription() {
		AutomationHelper.printMethodName();
		return descriptionTextfield.getAttribute("value");
	}

	/**
	 * Reads Description error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readDescriptionErrorMessage() {
		AutomationHelper.printMethodName();
		return readErrorMessageForField(
				descriptionTextfield.getAttribute("id"));
	}
	
	
	
	@FindBy (id = "AdditionalInformation")
	WebElement additionalInfoTextfield;
	
	/**
	 * Set text in field "Additional Info".
	 * 
	 * @param text
	 */
	public void setAdditionalInfo(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(additionalInfoTextfield, text);
	}

	/**
	 * Reads Additional Info text
	 * 
	 * @return String
	 */
	public String readAdditionalInfo() {
		AutomationHelper.printMethodName();
		return additionalInfoTextfield.getAttribute("value");
	}

	/**
	 * Reads Additional Info error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readAdditionalInfoErrorMessage() {
		AutomationHelper.printMethodName();
		return readErrorMessageForField(
				additionalInfoTextfield.getAttribute("id"));
	}
	

	@FindBy(id = "ActiveFlag")
	WebElement activeFlagCheckbox;

	/**
	 * Check/uncheck checkbox in "Active".
	 * 
	 * @param desiredStatus
	 *            - true = checked | false = unchecked
	 */
	public void setActiveCheckbox(boolean desiredStatus) {
		AutomationHelper.printMethodName();
		AutomationHelper.setCheckbox(activeFlagCheckbox, desiredStatus);
	}

	/**
	 * Returns if Active is checked or not
	 * 
	 * @return boolean - true = checked | false = unchecked
	 */
	public boolean isActiveChecked() {
		AutomationHelper.printMethodName();
		return activeFlagCheckbox.isSelected();
	}

	/**
	 * Reads Active error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readActiveErrorMessage() {
		AutomationHelper.printMethodName();
		return readErrorMessageForField(activeFlagCheckbox.getAttribute("id"));
	}

}
