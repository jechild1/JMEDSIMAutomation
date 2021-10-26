package pageFactories.Admin.AdminSimulationPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.DetailsBase;
import utilities.AutomationHelper;
/**
 * Abstract Page factory for the Details/Delete of Simulation > Node Type
 * Details pages for JMEDSIM.<br>
 * (1) Finds a reference to common objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends DetailsBase
 * 
 * @author jesse.childress
 *
 */
public abstract class NodeTypeBase extends DetailsBase {
	
	/**
	 * Abstract Page Constructor: Accepts the WebDriver from the calling page,
	 * provides base methods common to create pages, and passes URL to
	 * MenusPageFactory for further validation.
	 * 
	 * Extends DetailsBase
	 */
	public NodeTypeBase(String regexURL) {
		super(regexURL);
	}
	
	@FindBy(xpath = "//dt[text()[normalize-space() = 'Node Type']]/following-sibling::dd[1]")
	WebElement nodeType;

	/**
	 * Reads "Node Type" text
	 * 
	 * @return String
	 */
	public String readNodeType() {
		AutomationHelper.printMethodName();
		return nodeType.getText().trim();
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
