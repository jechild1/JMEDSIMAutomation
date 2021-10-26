package pageFactories;

/**
 * Page factory for the Home page for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends MenusPageFactory
 * 
 * @author scott.brazelton
 *
 */
public class HomePageFactory extends MenusPageFactory {

	private static String regexURL = BASE_URL + ".*";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public HomePageFactory() {
		super(regexURL);
	}
}
