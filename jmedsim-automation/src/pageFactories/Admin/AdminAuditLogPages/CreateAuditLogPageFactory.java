package pageFactories.Admin.AdminAuditLogPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pageFactories.ModifyBase;
import utilities.AutomationHelper;
/**
 * Page factory for the Admin > Audit Log > Create New page for JMEDSIM.<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * Extends ModifyBase
 * 
 * @author jesse.childress
 *
 */
public class CreateAuditLogPageFactory extends ModifyBase {

	public static String regexURL = BASE_URL + "ApplicationAudits/Create";

	/**
	 * Page Constructor: Instantiates super class with URL provided
	 */
	public CreateAuditLogPageFactory() {
		super(regexURL);
	}

	@FindBy(id = "EventDate")
	WebElement eventDateTextfield;

	/**
	 * Sets the Event Date Text field
	 * 
	 * @param text
	 */
	public void setEventDate(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(eventDateTextfield, text);
	}

	/**
	 * Returns the value in the Event Date text field
	 * 
	 * @return String
	 */
	public String readEventDate() {
		AutomationHelper.printMethodName();
		return eventDateTextfield.getAttribute("value").trim();
	}

	@FindBy(id = "EventType")
	WebElement eventTypeTextfield;

	/**
	 * Sets the Event Type Text field
	 * 
	 * @param text
	 */
	public void setEventType(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(eventTypeTextfield, text);
	}

	/**
	 * Returns the value in the Event Type text field
	 * 
	 * @return String
	 */
	public String readEventType() {
		AutomationHelper.printMethodName();
		return eventTypeTextfield.getAttribute("value").trim();
	}

	@FindBy(id = "EventStatus")
	WebElement eventStatusTextfield;

	/**
	 * Sets the Event Status Text field
	 * 
	 * @param text
	 */
	public void setEventStatus(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(eventStatusTextfield, text);
	}

	/**
	 * Returns the value in the Event Status text field
	 * 
	 * @return String
	 */
	public String readEventStatus() {
		AutomationHelper.printMethodName();
		return eventStatusTextfield.getAttribute("value").trim();
	}

	@FindBy(id = "UserId")
	WebElement userIDTextfield;

	/**
	 * Sets the User Id Text field
	 * 
	 * @param text
	 */
	public void setUserID(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(userIDTextfield, text);
	}

	/**
	 * Returns the value in the User Id text field
	 * 
	 * @return String
	 */
	public String readUserID() {
		AutomationHelper.printMethodName();
		return userIDTextfield.getAttribute("value").trim();
	}

	@FindBy(id = "IPAddress")
	WebElement idAddressTextfield;

	/**
	 * Sets the IP Address Text field
	 * 
	 * @param text
	 */
	public void setIPAddress(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(idAddressTextfield, text);
	}

	/**
	 * Returns the value in the IP Address text field
	 * 
	 * @return String
	 */
	public String readIPAddress() {
		AutomationHelper.printMethodName();
		return idAddressTextfield.getAttribute("value").trim();
	}

	@FindBy(id = "ObjectId")
	WebElement objectIDTextfield;

	/**
	 * Sets the Object ID Text field
	 * 
	 * @param text
	 */
	public void setObjectID(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(objectIDTextfield, text);
	}

	/**
	 * Returns the value in the Object ID text field
	 * 
	 * @return String
	 */
	public String readObjectID() {
		AutomationHelper.printMethodName();
		return objectIDTextfield.getAttribute("value").trim();
	}

	@FindBy(id = "ObjectName")
	WebElement objectNameTextfield;

	/**
	 * Sets the Object Name Text field
	 * 
	 * @param text
	 */
	public void setObjectName(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(objectNameTextfield, text);
	}

	/**
	 * Returns the value in the Object Name text field
	 * 
	 * @return String
	 */
	public String readObjectName() {
		AutomationHelper.printMethodName();
		return objectNameTextfield.getAttribute("value").trim();
	}

	@FindBy(id = "Description")
	WebElement descriptionTextfield;

	/**
	 * Sets the Description Text field
	 * 
	 * @param text
	 */
	public void setDescription(String text) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(descriptionTextfield, text);
	}

	/**
	 * Returns the value in the Description text field
	 * 
	 * @return String
	 */
	public String readDescription() {
		AutomationHelper.printMethodName();
		return descriptionTextfield.getAttribute("value").trim();
	}

	/**
	 * Clicks the Create button
	 */
	public void clickCreate() {
		clickSubmitTypeButton();
	}
}
