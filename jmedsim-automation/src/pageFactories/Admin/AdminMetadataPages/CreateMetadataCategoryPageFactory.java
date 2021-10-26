package pageFactories.Admin.AdminMetadataPages;

/**
 * Page factory for the Admin > Metadata > Metadata Category > Create New
 * Metadata Category for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifyMetadataCategoryBase
 * 
 * @author scott.brazelton
 *
 */
public class CreateMetadataCategoryPageFactory
		extends
			ModifyMetadataCategoryBase {
	private static String regexURL = BASE_URL + "MetadataCategories/Create";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public CreateMetadataCategoryPageFactory() {
		super(regexURL);
	}

	/**
	 * Clicks the Create button
	 */
	public void clickCreate() {
		clickSubmitTypeButton();
	}
}
