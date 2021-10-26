package pageFactories.Admin.AdminSimulationPages;

import utilities.AutomationHelper;

/**
 * Page factory for the Admin > Simulation > Initial Physiologic Parameters Template Details page for
 * JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends InitialPhysiologicParametersTemplateDetailsBase
 * 
 * @author jesse.childress
 *
 */
public class InitialPhysiologicParametersTemplateDetailsPageFactory extends InitialPhysiologicParametersTemplateDetailsBase {
	
	private static String regexURL = BASE_URL + "InitialPhysiologicParametersTemplates/Details/.*";
	
	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public InitialPhysiologicParametersTemplateDetailsPageFactory() {
		super(regexURL);
	}

	/**
	 * Clicks "Edit" link
	 */
	public void clickEdit() {
		AutomationHelper.printMethodName();
		clickModifyTypeLinkOrButton();
	}

}
