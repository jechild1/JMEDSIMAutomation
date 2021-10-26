package pageFactories.MyMenuPages;

import pageFactories.IndexBase;

/**
 * Page factory for the My Menu > Feedback page for JMEDSIM <br>
 * (1) Finds a reference to objects on the page<br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends IndexBase
 * 
 * @author jesse.childress
 */
public class FeedbackPageFactory extends IndexBase {
	
	private static String regexURL = BASE_URL + "Feedback/Index.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided.
	 */
	public FeedbackPageFactory() {
		super(regexURL);
	}

}
