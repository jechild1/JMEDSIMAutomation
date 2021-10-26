package pageFactories.Admin.SitePOCPages;

import pageFactories.IndexBase;

/**
 * Page factory for the Admin > Site POC page for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends IndexBase
 * 
 * @author jesse.childress
 *
 */
public class SitePOCPageFactory extends IndexBase {
	
	private static String regexURL = BASE_URL + "SitePOC/Index.*";
	
	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public SitePOCPageFactory() {
		super(regexURL);
	}
	
	//NO OBJECTS CODED YET>
	

}
