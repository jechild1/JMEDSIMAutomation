package pageFactories.Admin.AdminMetadataPages;

/**
 * Page factory for the Admin > Metadata > Metadata > Create New Metadata for
 * JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifyMetadataBase
 * 
 * @author scott.brazelton
 *
 */
public class CreateMetadataPageFactory extends ModifyMetadataBase {
	private static String regexURL = BASE_URL + "MetadataDetails/Create";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public CreateMetadataPageFactory() {
		super(regexURL);
	}

	/**
	 * Clicks the Create button
	 */
	public void clickCreate() {
		clickSubmitTypeButton();
	}
}
