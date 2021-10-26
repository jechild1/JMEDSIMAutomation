package testCases.Admin;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.EquipmentCategoryPages.CreateNewEquipmentCategoryPageFactory;
import pageFactories.Admin.EquipmentCategoryPages.DeleteEquipmentCategoryPageFactory;
import pageFactories.Admin.EquipmentCategoryPages.EquipmentCategoryPageFactory;
import testCases.BaseTestScriptConfig;
import utilities.AutomationHelper;
import utilities.ExcelDataConfig;

/**
 * Test script that performs an Add/Edit/Delete test for adding Equipment
 * Categories under an Admin role.
 * 
 * @author jesse.childress
 *
 */
public class EquipmentCategoryAddEditDelete extends BaseTestScriptConfig {

	// Global Variables
	String equipmentCategory;
	String description;
	boolean active;

	@Test(dataProvider = "loginAccounts")
	public void execute(String userName) {

		// Log in as a Super User to edit Equipment Category
		LoginMod login = new LoginMod();
		login.execute(userName);
		Reporter.log("Logged in as user: " + userName, true);

		// Navigate to Equipment Category on the page
		HomePageFactory hp = new HomePageFactory();
		hp.clickEquipmentCategoryInAdmin();
		EquipmentCategoryPageFactory equipmentCat = new EquipmentCategoryPageFactory();

		// Search for an existing category (from existing ones on data sheet)
		searchForExistingEquipmentCategory(equipmentCat);

		// Navigate to the Create New Equipment category
		equipmentCat.clickCreateNewEquipmentCategory();

		// Instantiate Create New Equipment Cateogry page
		CreateNewEquipmentCategoryPageFactory newEquipCat = new CreateNewEquipmentCategoryPageFactory();

		// Ensure error messages fire when no data entered.
		assertErrorMessages(newEquipCat);

		// Add a new category (from temporary categories in datasheet)
		addNewEquipmentCategory(newEquipCat);

		// Validate new record was added on the equipment category page
		// New reference to page, as it has changed
		equipmentCat = new EquipmentCategoryPageFactory();
		equipmentCat.gotoTablePageWithRow(
				equipmentCat.getEquipmentCategoryTable(), "Equipment Category",
				equipmentCategory);
		assertEquals(
				equipmentCat.getEquipmentCategoryTable().readTableRowValue(
						"Equipment Category", equipmentCategory,
						"Equipment Category", true),
				equipmentCategory, "Equipment Category Table Row");
		assertEquals(equipmentCat.getEquipmentCategoryTable().readTableRowValue(
				"Equipment Category", equipmentCategory, "Description", true),
				description, "Description Table Row");

//		// Navigate to the Simulations page.
//		equipmentCat.clickSimulationInMyMenu();
//		// Create a new simulation, add a new table record for the newly created
//		// equipment category, assert that it is there, delete it, and delete
//		// the newly
//		// created simulation.
//		createNewSimulation(equipmentCategory);
//
//		// Navigate back to the Equipment Category page
//		SimulationsPageFactory simulations = new SimulationsPageFactory();
//		simulations.clickEquipmentCategoryInAdmin();

		// Delete the newly create equipment category
//		equipmentCat = new EquipmentCategoryPageFactory();
		equipmentCat.gotoTablePageWithRow(
				equipmentCat.getEquipmentCategoryTable(), "Equipment Category",
				equipmentCategory);
		equipmentCat.getEquipmentCategoryTable()
				.clickLinkInRow(equipmentCategory, "Delete");
		DeleteEquipmentCategoryPageFactory deleteEquip = new DeleteEquipmentCategoryPageFactory();
		assertEquals(deleteEquip.readEquipmentCategory(), equipmentCategory,
				"Delete Equipment Category");
		deleteEquip.clickDelete();

		assertFalse(
				equipmentCat.getEquipmentCategoryTable()
						.isRowInTable(equipmentCategory),
				"Assert False - Equipment Category found in row");

	}

//	/**
//	 * Utility method to create a new simulation, and then populate data for the
//	 * newly created Equipment Category.
//	 * 
//	 * @param equipmentCategoryName
//	 */
//	private void createNewSimulation(String equipmentCategoryName) {
//		// Instantiate page
//		SimulationsPageFactory simulations = new SimulationsPageFactory();
//
//		// Click Create a new simualtion
//		simulations.clickCreateNewSimulation();
//
//		// Create New Simulation (Administrative Details) page
//		CreateNewSimulationPageFactory createSim = new CreateNewSimulationPageFactory();
//
//		// Set fields in order to proceed (fake data)
//		String simulationTitle = "Equipment Category Test - Add/Edit/Delete";
//		String simulationDurationHours = "1";
//		String simulationDurationMinutes = "5";
//		String simulationAuthor = "Jesse DHA Super User";
//		String simulationSite = "TATRC";
//		String simulationContentKeywords = "Content Keywords";
//		String simulationTrainingType = "Head trauma";
//		boolean simulationActiveCheckbox = true;
//
//		createSim.setTitle(simulationTitle);
//		createSim.setDurationHours(simulationDurationHours);
//		createSim.setDurationMinutes(simulationDurationMinutes);
//		createSim.selectAuthor(simulationAuthor);
//		createSim.selectSite(simulationSite);
//		createSim.setContentKeywords(simulationContentKeywords);
//		createSim.selectTrainingType(simulationTrainingType);
//		createSim.setActiveCheckbox(simulationActiveCheckbox);
//		createSim.clickCreate();
//
//		EditSimulationPageFactory editSim = new EditSimulationPageFactory();
//
//		// Add a new record
//		// Variables to store data
//		String item = "Item 1 text";
//		String qty = "3";
//		String note = "Equipment Note 1";
//
//		// Click the link and launch the modal
//		EquipmentModal equip = editSim.getEquipmentTab()
//				.clickLink(equipmentCategoryName);
//
//		// Set the fields and add the record
//		equip.setItem(item);
//		equip.setQty(qty);
//		equip.setNote(note);
//		equip.clickAdd();
//
//		// TODO - Temporary (Need to refresh to show table)
//		// TEMP BUG
//		createSim.clickHomeLogo();
//		HomePageFactory home = new HomePageFactory();
//		home.clickSimulationInMyMenu();
//		// TODO - End Temporary
//
//		// Reference to Simulations Page Factory
//		SimulationsPageFactory spf = new SimulationsPageFactory();
//
//		// Navigate back to the newly created simulation and click Edit to open
//		// it
//		spf.gotoTablePageWithRow(spf.getSimulationsTable(), "Title",
//				simulationTitle);
//		spf.getSimulationsTable().clickLinkInRow(simulationTitle, "Edit");
//
//		// Assert that the table has the correct values in it.
//		// New reference to page
//		EditSimulationPageFactory espf = new EditSimulationPageFactory();
//
//		assertEquals(
//				espf.getEquipmentTab().getTable(equipmentCategoryName)
//						.readTableRowValue("Item", item, "Item", true),
//				item, "Equipment: " + equipmentCategoryName + ": Item column");
//		assertEquals(
//				espf.getEquipmentTab().getTable(equipmentCategoryName)
//						.readTableRowValue("Item", item, "Qty", true),
//				qty, "Equipment: " + equipmentCategoryName + ": Qty column");
//		assertEquals(
//				espf.getEquipmentTab().getTable(equipmentCategoryName)
//						.readTableRowValue("Item", item, "Note", true),
//				note, "Equipment: " + equipmentCategoryName + ": Note column");
//		// Delete the value out of the table, as this will be necessary in order
//		// to be
//		// able to delete the equipment category name out of Admin > Equipment
//		// Category.
//		espf.getEquipmentTab().getTable(equipmentCategoryName)
//				.clickLinkInRow(item, "Delete");
//		espf.clickOKOnAlert();
//
//		// Navigate back to Simulations and delete entire record
//		espf.clickSimulationInMyMenu();
//		spf.gotoTablePageWithRow(spf.getSimulationsTable(), "Title",
//				simulationTitle);
//		spf.getSimulationsTable().clickLinkInRow(simulationTitle, "Delete");
//		DeleteSimulationPageFactory deleteSim = new DeleteSimulationPageFactory();
//		// TODO - Potential to add more asserts here in the future.
//		assertEquals(deleteSim.readTitle(), simulationTitle,
//				"Delete Simulation - Title");
//		assertEquals(deleteSim.readTrainingType(), simulationTrainingType,
//				"Delete Simulation - Training Type");
//		deleteSim.clickDelete();
//
//		// Left back on the Simulations Page.
//
//	}

	/**
	 * Utility method to ensure that error message are presented to the user
	 * when invalid data is entered.
	 * 
	 * @param newEquipCat
	 */
	private void assertErrorMessages(CreateNewEquipmentCategoryPageFactory newEquipCat) {
		// Clicking create should produce error messages.
		newEquipCat.clickCreate();

		// Get the error messages from the datasheet
		ExcelDataConfig errorMessagesDatasheet = getExcelFile("JMEDContentMatrix.xlsx", "StaticTextValidations");

		String equipCatError = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID",	"CREATENEWEQUIPCAT_equipCatReq"), "Content");
		String descriptionError = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID","CREATENEWEQUIPCAT_description"), "Content");

		assertEquals(newEquipCat.readEquipmentCategoryErrorMessage(),equipCatError, "Equipment Category Error Message");
		assertEquals(newEquipCat.readDescriptionErrorMessage(),	descriptionError, "Description Error Message");
	}
	

	/**
	 * Utility method to add a new equipment category on the Admin page.
	 * 
	 * @param newEquipCat
	 */
	private void addNewEquipmentCategory(
			CreateNewEquipmentCategoryPageFactory newEquipCat) {

		// Pull data from datasheet (Randonly, with only Type = "Temporary";
		ExcelDataConfig equipCatData = getExcelFile("JMEDAdminDataMap.xlsx",
				"EquipmentCategoryDataMap");

		int rowCount = equipCatData.getRowCount();
		ArrayList<Integer> validRows = new ArrayList<Integer>();

		for (int i = 1; i <= rowCount; i++) {
			if (equipCatData.getData(i, equipCatData.getColumnIndex("Type"))
					.equals("Temporary")) {
				validRows.add(i);
			}
		}

		int randomRowIndex = AutomationHelper.generateRandomInteger(0,
				validRows.size() - 1);

		equipmentCategory = equipCatData.getData(validRows.get(randomRowIndex),
				equipCatData.getColumnIndex("EquipmentCategory"));
		description = equipCatData.getData(validRows.get(randomRowIndex),
				equipCatData.getColumnIndex("Description"));
		active = Boolean
				.valueOf(equipCatData.getData(validRows.get(randomRowIndex),
						equipCatData.getColumnIndex("Active")));

		newEquipCat.setEquipmentCategory(equipmentCategory);
		newEquipCat.setDescription(description);
		newEquipCat.setActiveCheckbox(active);

		newEquipCat.clickCreate();

	}

	/**
	 * Utility methods that handles searching for an existing (permanent)
	 * equipment category. This method selects a random equipment category to
	 * search. It then asserts that it is in the table.
	 * 
	 * @param equipmentCat
	 */
	private void searchForExistingEquipmentCategory(
			EquipmentCategoryPageFactory equipmentCat) {
		// Get a reference to the datasheet
		ExcelDataConfig equipmentDataFile = getExcelFile(
				"JMEDAdminDataMap.xlsx", "EquipmentCategoryDataMap");

		// Look at all the permanent records and select a ramdon one to search
		// for.
		List<String> rowList = new ArrayList<String>();

		for (int i = 1; i <= equipmentDataFile.getRowCount(); i++) {
			if (equipmentDataFile
					.getData(i, equipmentDataFile.getColumnIndex("Type"))
					.equalsIgnoreCase("Permanent")) {
				rowList.add(equipmentDataFile.getData(i, "EquipmentCategory"));
			}
		}

		String equipmentCategory = rowList.get(
				AutomationHelper.generateRandomInteger(0, rowList.size() - 1));
		System.out
				.println("Equipment Category for Search: " + equipmentCategory);

		equipmentCat.setSearchBy(equipmentCategory);
		equipmentCat.clickSearch();

		assertEquals(
				equipmentCat.getEquipmentCategoryTable().readTableRowValue(
						"Equipment Category", equipmentCategory,
						"Equipment Category", true),
				equipmentCategory, "Equipment Category Search");

		// Clear current search results to display the entire table.
		equipmentCat.clearSearchBy();
		equipmentCat.clickSearch();
	}

	/**
	 * Returns a random user of type DHA System Admin or DHA Super User from the UsersData.xlsx
	 * @return String[]
	 */
	@DataProvider
	private String[] loginAccounts() {

		ExcelDataConfig usersFile = getExcelFile("UsersData.xlsx",
				"JMEDUserData");

		List<String> adminUserNames = new ArrayList<String>();

		// Sting[] to store the different types of admin accounts. We will want
		// to test for each of these.
		String[] adminTypes = {"DHA System Admin", "DHA Super User"};

		// Get the row count for the data file
		int rowCount = usersFile.getRowCount();

		// Loop through each different admin role outlined above.
		for (String currentAdminType : adminTypes) {

			// For each user type, loop through the data set until we find the
			// accounts in which the TYPE matches the admin type.
			for (int i = 1; i <= rowCount; i++) {

				String currentUserNameInDatasheet = usersFile.getData(i,
						"Type");

				if (currentAdminType.equals(currentUserNameInDatasheet)) {
					adminUserNames.add(usersFile.getData(i, "UserName"));
				}
			}
		}

		String[] returnArray = adminUserNames.stream().toArray(String[]::new);
		int arrayCount = returnArray.length;

		// Return a String [] that only contains a random user with a login of
		// DHA
		// System Admin or DHA Super User
		return new String[]{returnArray[AutomationHelper
				.generateRandomInteger(0, arrayCount - 1)]};
	}

}
