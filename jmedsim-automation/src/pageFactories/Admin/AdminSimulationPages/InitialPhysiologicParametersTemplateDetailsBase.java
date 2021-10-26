package pageFactories.Admin.AdminSimulationPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.DetailsBase;
import utilities.AutomationHelper;

/**
 * Abstract Page factory for the Details/Delete page of Admin > Simulation >
 * Initial Physiologic Parameters Template Details pages for JMEDSIM.<br>
 * (1) Finds a reference to common objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends DetailsBase
 * 
 * @author jesse.childress
 *
 */
public abstract class InitialPhysiologicParametersTemplateDetailsBase extends DetailsBase {
	
	/**
	 * Abstract Page Constructor: Accepts the WebDriver from the calling page,
	 * provides base methods common to create pages, and passes URL to
	 * MenusPageFactory for further validation.
	 * 
	 * Extends DetailsBase
	 */
	public InitialPhysiologicParametersTemplateDetailsBase(String regexURL) {
		super(regexURL);
	}
	
	@FindBy(xpath = "//dt[text()[normalize-space() = 'Template Name']]/following-sibling::dd[1]")
	WebElement templateName;

	/**
	 * Reads "Template Name" text
	 * 
	 * @return String
	 */
	public String readTemplateName() {
		AutomationHelper.printMethodName();
		return templateName.getText().trim();
	}
	
	@FindBy(xpath = "//dt[text()[normalize-space() = 'ActualFileName']]/following-sibling::dd[1]")
	WebElement actualFileName;

	/**
	 * Reads "ActualFileName" text
	 * 
	 * @return String
	 */
	public String readActualFileName() {
		AutomationHelper.printMethodName();
		return actualFileName.getText().trim();
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
	
	@FindBy(xpath = "//dt[text()[normalize-space() = 'Content Type']]/following-sibling::dd[1]")
	WebElement contentType;

	/**
	 * Reads "Content Type" text
	 * 
	 * @return String
	 */
	public String readContentType() {
		AutomationHelper.printMethodName();
		return contentType.getText().trim();
	}


}
