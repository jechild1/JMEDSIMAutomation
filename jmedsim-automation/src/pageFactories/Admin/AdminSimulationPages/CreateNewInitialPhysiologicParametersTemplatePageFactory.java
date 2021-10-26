package pageFactories.Admin.AdminSimulationPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Simulation > Create New Initial Physiologic
 * Parameters Template for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifyInitialPhysiologicParametersTemplateBase
 * 
 * @author jesse.childress
 *
 */
public class CreateNewInitialPhysiologicParametersTemplatePageFactory extends ModifyInitialPhysiologicParametersTemplateBase {

	private static String regexURL = BASE_URL + "InitialPhysiologicParametersTemplates/Create";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public CreateNewInitialPhysiologicParametersTemplatePageFactory() {
		super(regexURL);
	}

	@FindBy(id = "DisplayFileName")
	WebElement templateNameTextfield;

	/**
	 * Sets the Template Name text field with the passed in text.
	 * 
	 * @param text
	 */
	public void setTemplateName(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(templateNameTextfield, text);
	}

	/**
	 * Reads the value of the Template Name text field.
	 * 
	 * @return String
	 */
	public String readTemplateName() {
		AutomationHelper.printMethodName();
		return templateNameTextfield.getAttribute("value");
	}

	/**
	 * Reads Name error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readNameErrorMessage() {
		AutomationHelper.printMethodName();
		WebElement errorSpan = driver.findElement(By.xpath("//span[@data-valmsg-for ='DisplayFileName']"));
		return errorSpan.getText();
	}

	@FindBy(name = "postedFile")
	WebElement selectFileField;

	/**
	 * Sets the Select File field with the passed in file path. <br>
	 * <i>E.g. C:\Users\jesse.childress\Pictures\Saved Pictures\Sample Picture -
	 * Koala.jpg </i>
	 * 
	 * @param filePath
	 */
	public void selectFile(String filePath) {
		AutomationHelper.printMethodName();
		selectFileField.sendKeys(filePath);
	}

	@FindBy(id = "ActualFileName")
	WebElement selectedFileTextfield;

	/**
	 * Reads the Selected File text field. <br>
	 * <b><i>Note: This only displays when editing a previously saved prerequisite
	 * </i></b>
	 * 
	 * @return String
	 */
	public String readSelectedFile() {
		AutomationHelper.printMethodName();
		return selectedFileTextfield.getAttribute("value");
	}
	
	/**
	 * Reads Selected File error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readSelectedFileErrorMessage() {
		AutomationHelper.printMethodName();
		WebElement errorSpan = driver.findElement(By.xpath("//span[@data-valmsg-for ='ActualFileName']"));
		return errorSpan.getText();
	}

	@FindBy(id = "FileDescription")
	WebElement description;

	/**
	 * Sets the Description field with the passed in text.
	 * 
	 * @param text
	 */
	public void setDescription(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(description, text);
	}

	/**
	 * Returns the value currently in the Description text field.
	 * 
	 * @return String
	 */
	public String readDescription() {
		AutomationHelper.printMethodName();
		return description.getAttribute("value");
	}

	/**
	 * Reads the Description error message (if any). Note: Returns an empty string
	 * if no message present.
	 * 
	 * @return String
	 */
	public String readDescriptionErrorMessage() {
		AutomationHelper.printMethodName();
		WebElement errorSpan = driver.findElement(By.xpath("//span[@data-valmsg-for = 'FileDescription']"));
		return errorSpan.getText();
	}

	@FindBy(name = "ActiveFlag")
	WebElement activeCheckbox;

	/**
	 * Sets a checkbox with the passed in status.
	 * 
	 * @param desiredStatus
	 */
	public void setActiveCheckbox(boolean desiredStatus) {
		AutomationHelper.printMethodName();
		AutomationHelper.setCheckbox(activeCheckbox, desiredStatus);
	}

	/**
	 * Reads the Active Checkbox status.
	 * 
	 * @return boolean
	 */
	public boolean readActiveCheckboxStatus() {
		AutomationHelper.printMethodName();
		return activeCheckbox.isSelected();
	}

	@FindBy(xpath = "//input[@value='Create']")
	WebElement createButton;

	/**
	 * Clicks the Create button.
	 */
	public void clickCreate() {
		AutomationHelper.printMethodName();
		createButton.click();
	}

	@FindBy(linkText = "Back to Initial Physiologic Parameters Template List")
	WebElement backToInitialPhysiologicLink;

	/**
	 * Clicks the Back to Initial Physiologic Parameters Template List link.
	 */
	public void clickBackToInitialPhysiologicParametersTemplateListLink() {
		AutomationHelper.printMethodName();
		backToInitialPhysiologicLink.click();
	}

}
