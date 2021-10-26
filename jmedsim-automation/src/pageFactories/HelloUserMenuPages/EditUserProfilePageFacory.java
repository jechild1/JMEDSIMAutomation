package pageFactories.HelloUserMenuPages;

/**
 * Page factory for the Edit User Profile page for JMEDSIM <br>
 * (1) Finds a reference to objects on the page<br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends UserProfileBase
 * 
 * @author jesse.childress
 */
public class EditUserProfilePageFacory extends UserProfileBase {

	// Note: Ended the regex with .* due to some database ID that is specific to
	// the logged in user. It generally looks like .../Edit/12 where 12 is the
	// user ID.
	private static String regexURL = BASE_URL + "UserProfiles/Edit.*";

	/**
	 * Page Constructor: Instantiates super class with URL provided.
	 */
	public EditUserProfilePageFacory() {
		super(regexURL);
	}

}
