package pageFactories;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.AutomationHelper;

/**
 * Abstract Page factory for Menus that can be found throughout any page in
 * JMedSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends JMEDSIMBase
 * 
 * @author scott.brazelton
 *
 */
public abstract class MenusPageFactory extends JMEDSIMBase {

	private Actions action = new Actions(driver);

	/**
	 * Abstract Navigation Menu Constructor: Accepts the WebDriver from the
	 * calling page, instantiates the elements on the page, and verifies the URL
	 */
	public MenusPageFactory(String regexURL) {
		PageFactory.initElements(driver, this);

		AutomationHelper.waitForPageToLoad(NORMAL_TIMEOUT);

		waitForPageToLoad();

		assertTrue(this.getCurrentUrl().matches(regexURL),
				"Validate URL changed to " + regexURL);
	}

	// Home Page / JMedSIM logo
	@FindBy(xpath = "//p[@class='site-title']/a")
	WebElement homeLogo;

	/**
	 * Clicks the Home logo.
	 */
	public void clickHomeLogo() {
		AutomationHelper.printMethodName();
		homeLogo.click();
	}

	// MY MENU

	@FindBy(linkText = "My Menu")
	WebElement myMenuDropdown;

	/**
	 * Clicks the My Menu navigation link.
	 * 
	 * @return reference to MenusPageFactory
	 */
	private MenusPageFactory clickMyMenu() {

		myMenuDropdown.click();

		return this;
	}

	@FindBy(linkText = "Simulation")
	WebElement simulationButton;

	/**
	 * Clicks My Menu > Simulations
	 */
	public void clickSimulationInMyMenu() {
		AutomationHelper.printMethodName();

		// Click My Menu, than move to simulations link and click
		clickMyMenu().simulationButton.click();
	}
	
	@FindBy (linkText = "Metadata Dictionary")
	WebElement metadataDictionaryButton;
	
	
	/**
	 * Clicks My Menu > Metadata Dictionary
	 */
	public void clickMetadataDictionaryInMyMenu() {
		AutomationHelper.printMethodName();
		clickMyMenu().metadataDictionaryButton.click();
	}

	@FindBy(linkText = "Feedback")
	WebElement feedbackButton;

	/**
	 * Clicks My Menu > Feedback
	 */
	public void clickFeedbackInMyMenu() {
		AutomationHelper.printMethodName();
		clickMyMenu().feedbackButton.click();
	}

	// ADMIN

	@FindBy(linkText = "Admin")
	WebElement adminDropdown;

	/**
	 * Clicks the Admin navigation link.
	 * 
	 * @return reference to MenusPageFactory
	 */
	private MenusPageFactory clickAdmin() {
		action.moveToElement(adminDropdown).perform();

		adminDropdown.click();

		return this;
	}

	@FindBy(linkText = "Users & Roles")
	WebElement usersAndRolesButton;

	/**
	 * Clicks Admin > Users & Roles
	 */
	public void clickUsersAndRolesInAdmin() {
		AutomationHelper.printMethodName();

		clickAdmin().usersAndRolesButton.click();

	}

	@FindBy(linkText = "Locked Accounts")
	WebElement lockedAccountsButton;

	/**
	 * Clicks Admin > Locked Accounts
	 */
	public void clickLockedAccountsInAdmin() {
		AutomationHelper.printMethodName();
		clickAdmin().lockedAccountsButton.click();
	}
	
	@FindBy (linkText = "Site POC")
	WebElement sitePOCButton;
	
	/**
	 * Click Admin > Site POC
	 */
	public void clickSitePOCInAdmin() {
		AutomationHelper.printMethodName();
		clickAdmin().sitePOCButton.click();
	}

	@FindBy(linkText = "Sites")
	WebElement sitesButton;

	/**
	 * Clicks Admin > Sites
	 */
	public void clickSitesInAdmin() {
		AutomationHelper.printMethodName();

		clickAdmin().sitesButton.click();

	}

	@FindBy(linkText = "Equipment Category")
	WebElement equipmentCategoryButton;

	/**
	 * Clicks Admin > Equipment Category
	 */
	public void clickEquipmentCategoryInAdmin() {
		AutomationHelper.printMethodName();

		clickAdmin().equipmentCategoryButton.click();

	}

	@FindBy(linkText = "Targeted Learner")
	WebElement targetedLearnerButton;

	/**
	 * Clicks Admin > Targeted Learner
	 */
	public void clickTargetedLearnerInAdmin() {
		AutomationHelper.printMethodName();

		clickAdmin().targetedLearnerButton.click();

	}

	@FindBy(linkText = "User Sites")
	WebElement userSitesButton;

	/**
	 * Clicks Admin > User Sites
	 */
	public void clickUserSitesInAdmin() {
		AutomationHelper.printMethodName();

		clickAdmin().userSitesButton.click();
	}
	
	@FindBy(linkText = "Log Verbosity")
	WebElement logVerbosityButton;
	
	/**
	 * Clicks Admin > Log Verbosity
	 */
	public void clickLogVerbosityInAdmin() {
		AutomationHelper.printMethodName();
		clickAdmin().logVerbosityButton.click();
	}

	@FindBy(xpath = "//a[@class='dropdown-toggle'][text()='Simulation']")
	WebElement simulationDropdown;

	/**
	 * Moves cursor to Simulation item in "Admin".
	 * 
	 * @return reference to MenusPageFactory
	 */
	private MenusPageFactory moveToSimulationInAdmin() {

		action.moveToElement(simulationDropdown).perform();

		return this;
	}

	@FindBy(linkText = "Service Role")
	WebElement serviceRoleButton;

	/**
	 * Clicks Admin > Simulation > Service Role
	 */
	public void clickServiceRoleInAdmin() {
		AutomationHelper.printMethodName();

		clickAdmin().moveToSimulationInAdmin().serviceRoleButton.click();

	}

	@FindBy(linkText = "Training Type")
	WebElement trainingTypeButton;

	/**
	 * Clicks Admin > Simulation > Training Type
	 */
	public void clickTrainingTypeInAdmin() {
		AutomationHelper.printMethodName();

		clickAdmin().moveToSimulationInAdmin().trainingTypeButton.click();

	}

	@FindBy(linkText = "Learner Profile")
	WebElement learnerProfileButton;

	/**
	 * Clicks Admin > Simulation > Learner Profile
	 */
	public void clickLearnerProfileInAdmin() {
		AutomationHelper.printMethodName();

		clickAdmin().moveToSimulationInAdmin().learnerProfileButton.click();

	}

	@FindBy(linkText = "Personnel")
	WebElement personnelButton;

	/**
	 * Clicks Admin > Simulation > Personnel
	 */
	public void clickPersonnelInAdmin() {
		AutomationHelper.printMethodName();

		clickAdmin().moveToSimulationInAdmin().personnelButton.click();

	}
	
	@FindBy(linkText = "Simulator Programming Platform")
	WebElement simulatorProgrammingPlatformButton;
	
	/**
	 * Clicks Admin > Simulation > Simulator Programming Platform
	 */
	public void clickSimulatorProgrammingPlatformInAdmin() {
		AutomationHelper.printMethodName();
		clickAdmin().moveToSimulationInAdmin().simulatorProgrammingPlatformButton.click();
	}

	@FindBy(linkText = "Simulator Software Version/OS")
	WebElement simulatorSoftwareVersionOSButton;
	
	/**
	 * Clicks Admin > Simulation > Simulator Platform
	 */
	public void clickSimulatorSoftwareVersionsOSInAdmin() {
		AutomationHelper.printMethodName();
		clickAdmin().moveToSimulationInAdmin().simulatorSoftwareVersionOSButton.click();
	}
	
	@FindBy(linkText = "Initial Physiologic Parameters Template")
	WebElement initialPhysiologicParametersTemplateButton;
	
	/**
	 * Clicks Admin > Simulation > Initial Physiologic Parameters Template
	 */
	public void clickInitialPhysiologicParametersTemplateInAdmin() {
		AutomationHelper.printMethodName();
		clickAdmin().moveToSimulationInAdmin().initialPhysiologicParametersTemplateButton.click();
	}
	
	@FindBy (linkText = "Node Type")
	WebElement nodeTypeButton;
	
	/**
	 * Clicks Admin > Simulation > Node Type
	 */
	public void clickNodeTypeInAdmin() {
		AutomationHelper.printMethodName();
		clickAdmin().moveToSimulationInAdmin().nodeTypeButton.click();
	}
	
	@FindBy (linkText = "Node Connector")
	WebElement nodeConnectorButton;
	
	/**
	 * Clicks Admin > Simulation > Node Connector
	 */
	public void clickNodeConnectorInAdmin() {
		AutomationHelper.printMethodName();
		clickAdmin().moveToSimulationInAdmin().nodeConnectorButton.click();
	}
		
	@FindBy(xpath = "//a[@class='dropdown-toggle'][text()='Content']")
	WebElement contentDropdown;
	

	/**
	 * Moves cursor to Content item in "Admin".
	 * 
	 * @return reference to MenusPageFactory
	 */
	private MenusPageFactory moveToContentInAdmin() {

		action.moveToElement(contentDropdown).perform();

		return this;
	}

	@FindBy(xpath = "//a[not(@class)][text()='Content']")
	WebElement contentButton;

	/**
	 * Clicks Admin > Content > Content
	 */
	public void clickContentInAdmin() {
		AutomationHelper.printMethodName();

		clickAdmin().moveToContentInAdmin().contentButton.click();

	}

	@FindBy(linkText = "Content Type")
	WebElement contentTypeButton;

	/**
	 * Clicks Admin > Content > Content Type
	 */
	public void clickContentTypeInAdmin() {
		AutomationHelper.printMethodName();

		clickAdmin().moveToContentInAdmin().contentTypeButton.click();

	}

	@FindBy(xpath = "//a[@class='dropdown-toggle'][text()='Metadata']")
	WebElement metadataDropdown;

	/**
	 * Moves cursor to Metadata item in "Admin".
	 * 
	 * @return reference to MenusPageFactory
	 */
	private MenusPageFactory moveToMetadataInAdmin() {

		action.moveToElement(metadataDropdown).perform();

		return this;
	}

//	@FindBy(xpath = "//a[not(@class)][text()='Metadata Dictionary']")
	@FindBy(linkText = "Metadata Dictionary")
	WebElement metadataDictButton;

	/**
	 * Clicks Admin > Metadata > Metadata Dictionary
	 */
	public void clickMetadataDictionaryInAdmin() {
		AutomationHelper.printMethodName();

		clickAdmin().moveToMetadataInAdmin().metadataDictButton.click();

	}

	@FindBy(linkText = "Metadata Category")
	WebElement metadataCategoryButton;

	/**
	 * Clicks Admin > Metadata > Metadata Category
	 */
	public void clickMetadataCategoryInAdmin() {
		AutomationHelper.printMethodName();

		clickAdmin().moveToMetadataInAdmin().metadataCategoryButton.click();

	}

	@FindBy(xpath = "//a[@class='dropdown-toggle'][text()='Simulator']")
	WebElement simulatorDropdown;

	/**
	 * Moves cursor to Simulator item in "Admin".
	 * 
	 * @return reference to MenusPageFactory
	 */
	private MenusPageFactory moveToSimulatorInAdmin() {

		action.moveToElement(simulatorDropdown).perform();

		return this;
	}

	@FindBy(xpath = "//a[not(@class)][text()='Simulator']")
	WebElement simulatorButton;

	/**
	 * Clicks Admin > Simulator > Simulator
	 */
	public void clickSimulatorInAdmin() {
		AutomationHelper.printMethodName();

		clickAdmin().moveToSimulatorInAdmin().simulatorButton.click();

	}

	@FindBy(linkText = "Simulator Category")
	WebElement simulatorCategoryButton;

	/**
	 * Clicks Admin > Simulator > Simulator Category
	 */
	public void clickSimulatorCategoryInAdmin() {
		AutomationHelper.printMethodName();
		clickAdmin().moveToSimulatorInAdmin().simulatorCategoryButton.click();
	}
	
	@FindBy(xpath = "//a[@class='dropdown-toggle'][text()='Notifications']")
	WebElement notificationsDropdown;

	/**
	 * Moves cursor to Notifications item in "Admin".
	 * 
	 * @return reference to MenusPageFactory
	 */
	private MenusPageFactory moveToNotificationsInAdmin() {
		action.moveToElement(notificationsDropdown).perform();
		return this;
	}
	
	@FindBy(linkText = "Notification Recipients")
	WebElement notificationRecipientsButton;
	
	/**
	 * Clicks Admin > Notifications > Notification Recipients
	 */
	public void clickNotificationRecipientsInAdmin() {
		AutomationHelper.printMethodName();
		clickAdmin().moveToNotificationsInAdmin().notificationRecipientsButton.click();
	}
	
	@FindBy(linkText = "Notifications Recipient Roles")
	WebElement notificationRecipientRolesButton;
	
	/**
	 * Clicks Admin > Notifications > Notification Recipient Roles
	 */
	public void clickNotificationRecipientRolesInAdmin() {
		AutomationHelper.printMethodName();
		clickAdmin().moveToNotificationsInAdmin().notificationRecipientRolesButton.click();
	}
	

	@FindBy(linkText = "Audit Log")
	WebElement auditLogButton;

	/**
	 * Clicks Admin > Audit Log
	 */
	public void clickAuditLogInAdmin() {
		AutomationHelper.printMethodName();
		clickAdmin().auditLogButton.click();
	}

	// user Hello menu

	@FindBy(xpath = "//a[starts-with(text(),'Hello')]")
	WebElement helloUserDropdown;

	/**
	 * Clicks the Hello {User} navigation link.
	 * 
	 * @return reference to MenusPageFactory
	 */
	private MenusPageFactory clickHelloUserMenu() {

		helloUserDropdown.click();

		return this;
	}

	@FindBy(linkText = "Log off")
	WebElement logOffButton;

	/**
	 * Clicks Hello {User} > Log off
	 */
	public void clickLogOffInHelloUser() {
		AutomationHelper.printMethodName();

		clickHelloUserMenu().logOffButton.click();

	}

	@FindBy(linkText = "Profile")
	WebElement profileButton;

	/**
	 * Clicks Hello {User} > Profile
	 */
	public void clickProfileInHelloUser() {
		AutomationHelper.printMethodName();

		clickHelloUserMenu().profileButton.click();

	}

	@FindBy(linkText = "Manage Account")
	WebElement manageAccountButton;

	/**
	 * Clicks Hello {User} > Profile
	 */
	public void clickManageAccountInHelloUser() {
		AutomationHelper.printMethodName();

		clickHelloUserMenu().manageAccountButton.click();

	}

}
