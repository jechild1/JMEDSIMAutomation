package pageFactories.Admin.AdminContentPages;

/**
 * Page factory for the Admin > Content > Content Type > Create New Content Type
 * for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifyContentTypeBase
 * 
 * @author scott.brazelton
 *
 */
public class CreateContentTypePageFactory extends ModifyContentTypeBase {
	private static String regexURL = BASE_URL + "ContentTypes/Create";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public CreateContentTypePageFactory() {
		super(regexURL);
	}

	/**
	 * Clicks the Create button
	 */
	public void clickCreate() {
		clickSubmitTypeButton();
	}
}
