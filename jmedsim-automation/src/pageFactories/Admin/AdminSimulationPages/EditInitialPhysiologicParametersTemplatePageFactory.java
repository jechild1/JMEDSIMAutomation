package pageFactories.Admin.AdminSimulationPages;

import utilities.AutomationHelper;
/**
 * Page factory for the Admin > Simulation > Edit Initial Physiologic Template Page /OS.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifySimulatorSoftwareVersionOSBase
 * 
 * @author jesse.childress
 *
 */
public class EditInitialPhysiologicParametersTemplatePageFactory extends ModifyInitialPhysiologicParametersTemplateBase {
	
	private static String regexURL = BASE_URL + "InitialPhysiologicParametersTemplates/Edit.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public EditInitialPhysiologicParametersTemplatePageFactory() {
		super(regexURL);
	}

	/**
	 * Clicks the Save button
	 */
	public void clickSave() {
		AutomationHelper.printMethodName();
		clickSubmitTypeButton();
	}

}
