package testCases.Admin;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.JMEDSimTables;
import pageFactories.Admin.SitesPages.CreateSitePageFactory;
import pageFactories.Admin.SitesPages.DeleteSitePageFactory;
import pageFactories.Admin.SitesPages.EditSitePageFactory;
import pageFactories.Admin.SitesPages.SitesPageFactory;
import testCases.BaseTestScriptConfig;
import utilities.AutomationHelper;
import utilities.ExcelDataConfig;

/**
 * Test script that performs an Add/Edit/Delete test for adding Sites under an
 * Admin role.
 * 
 * @author jesse.childress
 *
 */
public class SitesAddEditDelete extends BaseTestScriptConfig {

	static String userName = "jesse_DHA_super_user@mail.mil";

	String site;
	String address1;
	String address2;
	String city;
	String state;
	String stateCode;
	String zip;
	boolean active;

	String editedSite;
	String editedAddress1;
	String editedAddress2;
	String editedCity;
	String editedState;
	String editedStateCode;
	String editedZip;
	boolean editedActive;

	@Test
	public void performSiteAddEditDelete() {

		// (1) Login as user
		LoginMod lm = new LoginMod();
		lm.execute(userName);

		// (2) Navigate to the Sites page
		HomePageFactory home = new HomePageFactory();
		home.clickSitesInAdmin();

		// (3) Ensure search functionality works
		SitesPageFactory sites = new SitesPageFactory();
		validateSearchFunctionality(sites);

		// (4) Create new site & Assert errors are presented.
		sites.clickCreateNewSite();
		assertErrorsArePresent();
		// Add new site
		createNewSite(sites);

		// (5) Validate site shows back on Sites page.
		// Get a new reference to the table
		sites = new SitesPageFactory();
		// We must aggregate the string because the table builds the string out
		// based on the input. There are commas missing here on purpose, because
		// the application has them missing.
		// Furthermore, there exist a case where the comma has a space after it when we
		// have a value for address 2, but not when it's empty.
		String commaChar = null;
		if (address2.equals("")) {
			commaChar = ",";
		} else {
			commaChar = ", ";
		}
		String address = address1 + commaChar + address2 + " " + city + " " + stateCode + " " + zip;

		// Note: If row is not present, this method will throw an exception
		sites.gotoTablePageWithRow(sites.getSitesTable(), "Site", site);
		assertEquals(sites.getSitesTable().isRowInTable(site, address), true, "Table Row Check: ");

		// (6) Edit newly created site
		sites.getSitesTable().clickLinkInRow(site, address, "Edit");
		editSite();
		String editedAddress = editedAddress1 + ", " + editedAddress2 + " " + editedCity + " " + editedStateCode + " " + editedZip;
		sites.gotoTablePageWithRow(sites.getSitesTable(), "Site", editedSite);
		assertTrue(sites.getSitesTable().isRowInTable(editedSite, editedAddress), "Table Row Check - after edit: ");

		// (7)Delete Site
		sites.getSitesTable().clickLinkInRow(editedSite, editedAddress, "Delete");
		DeleteSitePageFactory deleteSite = new DeleteSitePageFactory();
		deleteSite.clickDelete();
		// Ensure record removed
		// New reference to class as table is gone.
		sites = new SitesPageFactory();

		// Assert that the row is deleted.
		// Note: While we find a false, and there is more pages to look at, click next
		// and wait for the page to load.
		int pageCounter = 1;
		boolean rowFound = true;
		do {
			// if the page counter is > 1, we need to navigate forward in the table and wait
			// for it to load.
			if (pageCounter > 1) {
				JMEDSimTables.clickSkipToNextNavigationLink();
				sites.waitForPageToLoad();
			}

			rowFound = sites.getSitesTable().isRowInTable(editedSite, editedAddress);

			assertEquals(rowFound, false, "Edited site deleted - page: " + pageCounter);
			pageCounter++;

		} while (JMEDSimTables.isSkipToNextNavigationPresent());
	}

	/**
	 * Utility method to ensure that errors are present when attempting to create a
	 * site with no data in it.
	 */
	private void assertErrorsArePresent() {
		// Instantiate page
		CreateSitePageFactory createSite = new CreateSitePageFactory();

		// Click Create prior to entering any data
		createSite.clickCreate();

		// Obtain a reference to the datasheet where the errors messages are
		// stored.
		ExcelDataConfig errorDataFile = getExcelFile("JMEDContentMatrix.xlsx", "StaticTextValidations");

		// Assert errors are present
		assertEquals(createSite.readSiteError(), errorDataFile.getData(errorDataFile.getRowIndex("TestDataID", "CREATESITE_SiteRequired"), "Content"), "Site Error Check: ");
		assertEquals(createSite.readAddress1Error(), errorDataFile.getData(errorDataFile.getRowIndex("TestDataID", "CREATESITE_Address1Required"), "Content"),
				"Address 1 Error Check: ");
		assertEquals(createSite.readCityError(), errorDataFile.getData(errorDataFile.getRowIndex("TestDataID", "CREATESITE_CityRequired"), "Content"), "City Error Check: ");
		assertEquals(createSite.readStateError(), errorDataFile.getData(errorDataFile.getRowIndex("TestDataID", "CREATESITE_StateRequired"), "Content"), "State Error Check: ");
		assertEquals(createSite.readZipcodeError(), errorDataFile.getData(errorDataFile.getRowIndex("TestDataID", "CREATESITE_ZipRequired"), "Content"), "Zipcode Error Check: ");
	}

	/**
	 * @param sites
	 */
	private void createNewSite(SitesPageFactory sites) {
		// Instantiate page
		CreateSitePageFactory createSite = new CreateSitePageFactory();
		// Pull values from data map
		ExcelDataConfig siteDataSheet = getExcelFile("JMEDAdminDataMap.xlsx", "SitesDataMap");

		// Pull values from datasheet
		// Consider NOT hardcoding this.
		// String testDataID = "TATRC_Site_Test1";
		int rowCount = siteDataSheet.getRowCount();
		// The Random Row index will get a row from 1 to 10.
		int randomRowIndex = AutomationHelper.generateRandomInteger(1, rowCount - 1);

		site = siteDataSheet.getData(randomRowIndex, siteDataSheet.getColumnIndex("Site"));
		address1 = siteDataSheet.getData(randomRowIndex, siteDataSheet.getColumnIndex("Address1"));
		address2 = siteDataSheet.getData(randomRowIndex, siteDataSheet.getColumnIndex("Address2"));
		city = siteDataSheet.getData(randomRowIndex, siteDataSheet.getColumnIndex("City"));
		state = siteDataSheet.getData(randomRowIndex, siteDataSheet.getColumnIndex("State"));
		stateCode = siteDataSheet.getData(randomRowIndex, siteDataSheet.getColumnIndex("StateCode"));
		zip = siteDataSheet.getData(randomRowIndex, siteDataSheet.getColumnIndex("Zip"));
		active = Boolean.valueOf(siteDataSheet.getData(randomRowIndex, siteDataSheet.getColumnIndex("Active")));

		// Set the values
		createSite.setSite(site);
		createSite.setAddress1(address1);
		createSite.setAddress2(address2);
		createSite.setCity(city);
		createSite.selectState(state);
		createSite.setZipcode(zip);
		createSite.setActiveSiteCheckbox(active);
		createSite.clickCreate();

	}

	private void editSite() {
		EditSitePageFactory editSite = new EditSitePageFactory();

		// Pull values from data map
		ExcelDataConfig siteDataSheet = getExcelFile("JMEDAdminDataMap.xlsx", "SitesDataMap");

		// Pull values from datasheet
		// Consider NOT hardcoding this.
		String testDataID = "TATRC_Site_EDITTEST";
		int rowIndex = siteDataSheet.getRowIndex("TestDataID", testDataID);

		editedSite = siteDataSheet.getData(rowIndex, siteDataSheet.getColumnIndex("Site"));
		editedAddress1 = siteDataSheet.getData(rowIndex, siteDataSheet.getColumnIndex("Address1"));
		editedAddress2 = siteDataSheet.getData(rowIndex, siteDataSheet.getColumnIndex("Address2"));
		editedCity = siteDataSheet.getData(rowIndex, siteDataSheet.getColumnIndex("City"));
		editedState = siteDataSheet.getData(rowIndex, siteDataSheet.getColumnIndex("State"));
		editedStateCode = siteDataSheet.getData(rowIndex, siteDataSheet.getColumnIndex("StateCode"));
		editedZip = siteDataSheet.getData(rowIndex, siteDataSheet.getColumnIndex("Zip"));
		editedActive = Boolean.valueOf(siteDataSheet.getData(rowIndex, siteDataSheet.getColumnIndex("Active")));

		// Edit the values
		editSite.setSite(editedSite);
		editSite.setAddress1(editedAddress1);
		editSite.setAddress2(editedAddress2);
		editSite.setCity(editedCity);
		editSite.selectState(editedState);
		editSite.setZipcode(editedZip);
		editSite.setActiveSiteCheckbox(editedActive);
		editSite.clickSave();

	}

	private void validateSearchFunctionality(SitesPageFactory sites) {
		sites.setSearchBy("TATRC Golden State");
		sites.clickSearch();

		// Ensure only one value returns in table
		assertEquals(sites.getSitesTable().getTableRowsForVisibleTable().size(), 1, "Table results of 1");
		assertEquals(sites.getSitesTable().isRowInTable("TATRC Golden State", "1313 Disneyland Dr., Anaheim CA 92802"), true, "Table Row Present");

		assertEquals(sites.getSitesTable().readTableRowValue("Site", "TATRC Golden State", "Address", false).trim(), "1313 Disneyland Dr., Anaheim CA 92802", "Table Row Present");

		// Return table to normal
		sites.clearSearchBy();
		sites.clickSearch();
	}

}
