package pageFactories.Admin.AdminSimulationPages;

import utilities.AutomationHelper;
/**
 * Page factory for the Admin > Simulation > Initial Physiologic Parameters Template > Delete page for
 * JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends PersonnelDetailsBase
 * 
 * @author jesse.childress
 *
 */
public class DeleteInitialPhysiologicParametersTemplatePageFactory extends InitialPhysiologicParametersTemplateDetailsBase{
	
	private static String regexURL = BASE_URL + "InitialPhysiologicParametersTemplates/Delete/.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public DeleteInitialPhysiologicParametersTemplatePageFactory() {
		super(regexURL);
	}

	/**
	 * Click Delete button
	 */
	public void clickDelete() {
		AutomationHelper.printMethodName();
		clickModifyTypeLinkOrButton();
	}

}
