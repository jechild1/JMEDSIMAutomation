package pageFactories;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import configuration.JMEDConfig;
import utilities.AutomationHelper;

/**
 * Base abstract Page factory for JMEDSIM that contains common wrapper methods
 * by:<br>
 * (1) Finds a reference to objects on the page <br>
 * (2) Defines methods to interact with objects on the page.<br>
 * 
 * @author scott.brazelton
 *
 */
public abstract class JMEDSIMBase extends JMEDConfig {

	
	public void initializeElements() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "h2")
	WebElement pageTitle;
	
	

	/**
	 * Reads the page title on the page (which is also the H2 class.)
	 * 
	 * @return String
	 */
	public String readPageTitle() {
		AutomationHelper.printMethodName();
		return pageTitle.getText();
	}

	@FindBy(css = "h1")
	WebElement pageHeader;

	/**
	 * Returns the page header on the page (This is the bold header on the main
	 * login page - which is also the H1 class.)
	 * 
	 * @return String
	 */
	public String readPageHeader() {
		AutomationHelper.printMethodName();
		return pageHeader.getText();
	}

	@FindBy(xpath = "//div[@class='modal fade in'][@id='pageLoader']")
	List<WebElement> pageLoadingModal;

	/**
	 * Is the modal displayed on the page
	 * 
	 * @return boolean - true = yes | false = no
	 */
	protected boolean isPageLoading() {
		boolean isPageLoading = false;

		AutomationHelper.adjustWaitTimeToShort();
		isPageLoading = AutomationHelper.isWebElementOnPage(pageLoadingModal);
		AutomationHelper.adjustWaitTimeToNormal();

		return isPageLoading;
	}

	// Generic modal elements

	@FindBy(className = "modal-header")
	WebElement modalHeader;

	/**
	 * Reads the modal's header
	 * 
	 * @return String
	 */
	protected String readModalHeader() {
		return modalHeader.getText();
	}

	@FindBy(className = "modal-body")
	WebElement modalBody;

	/**
	 * Reads the modal's header
	 * 
	 * @return String
	 */
	protected String readModalBody() {
		return modalBody.getText();
	}

	@FindBy(id = "buttonOk")
	WebElement modalOkButton;

	/**
	 * Clicks "Ok" button in modal
	 */
	protected void clickOkInModal() {
		WebDriverWait wait = new WebDriverWait(driver, NORMAL_TIMEOUT);
		wait.until(ExpectedConditions.visibilityOf(modalOkButton));
		modalOkButton.click();
		waitForBackdropToBeRemoved();

	}

	/**
	 * Generic protected method to read an error message for a field (generally
	 * the same as the field ID)
	 * 
	 * NOTE: returns an empty string if span not found
	 * 
	 * @param field
	 * @return String
	 */
	protected String readErrorMessageForField(String field) {
		AutomationHelper.adjustWaitTimeToShort();
		List<WebElement> errorMessages = driver
				.findElements(By.xpath("//span[@for='" + field
						+ "']|//span[@data-valmsg-for='" + field + "']"));
		AutomationHelper.adjustWaitTimeToNormal();

		return errorMessages.size() > 0 ? errorMessages.get(0).getText() : "";
	}

	/**
	 * Bootstrap elements are sometimes disabled through the class name where
	 * javascript takes care of making it disabled instead of HTML.
	 * 
	 * This method checks for instances where a element is disabled by bootstrap
	 * rather than HTML
	 * 
	 * @param element
	 * @return boolean - returns true if enabled; false if not enabled.
	 */
	private boolean isBootstrapElementEnabled(WebElement element) {
		String className = element.getAttribute("class").toLowerCase();

		return !className.contains("disabled");
	}

	@FindBy(xpath = "//a[@rel='prev']")
	WebElement tablePreviousButton;

	@FindBy(className = "PagedList-skipToPrevious")
	WebElement tablePreviousButtonParent;

	/**
	 * Cycles through back to first paginated table page if not on it already
	 */
	public void gotoFirstTablePage() {
		AutomationHelper.printMethodName();

		while (isBootstrapElementEnabled(tablePreviousButtonParent)) {
			tablePreviousButton.click();

			waitForPageToLoad();
		}
	}

	@FindBy(xpath = "//a[@rel='next']")
	WebElement tableNextButton;

	@FindBy(className = "PagedList-skipToNext")
	WebElement tableNextButtonParent;

	/**
	 * Cycles through paginated table until a specified row can be found
	 * 
	 * @param table
	 * @param primaryColumnHeader
	 * @param primaryColumnValue
	 * 
	 * @throws NotFoundException
	 *             - if specified row is not found
	 */
	public void gotoTablePageWithRow(JMEDSimTables table, String primaryColumnHeader,
			String primaryColumnValue) {
		AutomationHelper.printMethodName();

		// first - make sure already on first paginated page
		gotoFirstTablePage();

		while (!table.isRowInTableByValue(primaryColumnHeader,
				primaryColumnValue)) {
			if (isBootstrapElementEnabled(tableNextButtonParent)) {
				tableNextButton.click();

				waitForPageToLoad();

			} else {
				throw new NotFoundException(String.format(
						"'%s' could not be found in column '%s' of table.  Attempted to look at all pages.  Please check the value and try again",
						primaryColumnValue, primaryColumnHeader));
			}
		}
	}

	/**
	 * Waits for loading icon to disable - indicating page is loaded
	 */
	public void waitForPageToLoad() {
		while (isPageLoading()) {
			AutomationHelper.wait(1);
		}
	}

	// TODO - JESS - PR
	// Following code was placed here to be accessible to multiple classes.
	@FindBy(xpath = "//div[@id='modalSavedSuccessfully']")
	WebElement modalSavedSuccessfullyModal;

	/**
	 * Is the saved successfully modal displayed on the page
	 * 
	 * @return boolean - true = yes | false = no
	 */
	private boolean isSavedSuccessfullyModalPresent() {
		return modalSavedSuccessfullyModal.getAttribute("aria-hidden")
				.equals("false") ? true : false;
	}

	/**
	 * Waits for Saved Successfully Modal to close
	 */
	protected void waitForSavedSuccessfullyModal() {

		waitForPageToLoad();
		AutomationHelper.wait(1);
		while (isSavedSuccessfullyModalPresent()) {
			AutomationHelper.wait(1);
		}
	}

	@FindBy(xpath = "//div[@class='modal-backgroup fade in']|//div[@id='bannerModal']")
	List<WebElement> modalBackdrop;

	/**
	 * Is the modal backdrop on the page
	 * 
	 * @return boolean - true = yes | false = no
	 */
	private boolean isModalBackdropPresent() {
		boolean isPresent = false;
		AutomationHelper.adjustWaitTimeToShort();
		if (modalBackdrop.size() > 0) {
			isPresent = modalBackdrop.get(0).isDisplayed();
		}

		AutomationHelper.adjustWaitTimeToNormal();
		return isPresent;

	}

	/**
	 * Waits for modal backdrop on page to be removed
	 */
	protected void waitForBackdropToBeRemoved() {
		waitForPageToLoad();
		AutomationHelper.wait(1);
		while (isModalBackdropPresent()) {
			AutomationHelper.wait(1);
		}
	}
}
