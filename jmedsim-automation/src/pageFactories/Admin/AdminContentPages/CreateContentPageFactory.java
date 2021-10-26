package pageFactories.Admin.AdminContentPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Content > Content > Create New Content for
 * JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifyContentBase
 * 
 * @author scott.brazelton
 *
 */
public class CreateContentPageFactory extends ModifyContentBase {
	private static String regexURL = BASE_URL + "Contents/Create";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public CreateContentPageFactory() {
		super(regexURL);
	}

	/**
	 * Clicks the Create button
	 */
	public void clickCreate() {
		clickSubmitTypeButton();
	}
	
	/**
	 * Clicks the Save button
	 */
	public void clickSave() {
		clickSubmitTypeButton();
	}

	@FindBy(name = "postedFile")
	WebElement postedFileFileupload;

	/**
	 * Set the file path of "Select File"
	 * 
	 * @param filePath
	 */
	public void setSelectFile(String filePath) {
		postedFileFileupload.sendKeys(filePath);
	}

	/**
	 * Reads Select File error message (if any)
	 * 
	 * NOTE: returns empty string if no message
	 * 
	 * @return String
	 */
	public String readSelectFileErrorMessage() {
		AutomationHelper.printMethodName();
		return readErrorMessageForField("ContentPath");
	}

}
