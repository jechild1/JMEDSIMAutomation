package testCases.Admin.Metadata;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import modularScripts.LoginMod;
import pageFactories.HomePageFactory;
import pageFactories.Admin.AdminMetadataPages.CreateMetadataPageFactory;
import pageFactories.Admin.AdminMetadataPages.DeleteMetadataPageFactory;
import pageFactories.Admin.AdminMetadataPages.EditMetadataPageFactory;
import pageFactories.Admin.AdminMetadataPages.MetadataDetailsPageFactory;
import pageFactories.Admin.AdminMetadataPages.MetadataPageFactory;
import testCases.BaseTestScriptConfig;
import utilities.AutomationHelper;
import utilities.ExcelDataConfig;
/**
 * Test Script to exercise Admin > Metadata > Metadata features. This
 * script performs the following functions:
 * <ul>
 * <li>Navigates to the Admin > Metadata > Metadata
 * 
 * <li>Exercises search functionality
 * <li>Views details of existing records
 * <li>Views and asserts the Edit page for an existing record
 * <li>Creates a new Metadata record
 * <li>Asserts error messages fire with incorrect data
 * <li>Asserts record was added to the table
 * <li>Exercises Edit functionality and asserts
 * <li>Deletes record.
 * 
 * @author jesse.childress
 *
 */
public class MetadataAddEditDelete extends BaseTestScriptConfig {
	
	static String PRIMARY_COLUMN = "Metadata";
	static String SECONDARY_COLUMN = "Description";
	
	String existingMetadata;
	String existingDescription;
	String existingMetadataCategory;
	boolean existingActive;
	
	String tempMetadataCategory;
	String tempMetadata;
	String tempDescription;
	String tempAdditionalInfo;
	boolean tempActive;
	
	
	@Test (dataProvider = "loginAccounts")
	public void execute(String userName) {

		// Login to the application using an admin type account
		LoginMod lm= new LoginMod();
		lm.execute(userName);
		
		//Navigate to the Admin > Metadata > Metadata page
		HomePageFactory hp= new HomePageFactory();
		hp.clickMetadataDictionaryInAdmin();
		
		//Search for an existing (Dynamic) Metadata record
		MetadataPageFactory metadataPage = new MetadataPageFactory();
		retrieveExistingRecord();
		
		//The Description can be rather large. Lets make sure we keep it 25 chars or less.
		String searchString = existingDescription;
		if(existingDescription.length() >=50) {
			searchString = searchString.substring(0, 50);
		}
		Reporter.log("Metadata Search String: " + searchString, true);
		metadataPage.setSearchBy(searchString);
		metadataPage.clickSearch();
		
		assertEquals(metadataPage.getMetadataTable().getTableRowCount(), 1, "Existing Record Search - Single Value Expected");
		assertEquals(metadataPage.getMetadataTable().isRowInTable(existingMetadata, existingDescription), true,	"Existing Metadata / Description Record Present");

		//Clear the search
		metadataPage.clearSearchBy();
		metadataPage.clickSearch();
		
		//Pick a random record from the datasheet and assert details.
		retrieveExistingRecord();
		metadataPage.getMetadataTable().clickLinkInRow(existingMetadata, existingDescription, "Details");
		
		/*
		 * Details Page
		 */
		MetadataDetailsPageFactory metadataDetailsPage = new MetadataDetailsPageFactory();
		
		assertEquals(metadataDetailsPage.readMetadataCategory(), existingMetadataCategory, "Edit Metadata - Metadata Cagetory");
		assertEquals(metadataDetailsPage.readMetadata(), existingMetadata, "Edit Metadata - Metadata");
		assertEquals(metadataDetailsPage.readDescription(), existingDescription, "Edit Metadata - Description");
//		assertEquals(editMetadata.readMetadataCategorySelected(), existingMetadataCategory, "Edit Metadata - Metadata Cagetory");
		assertEquals(metadataDetailsPage.isActiveChecked(), existingActive, "Edit Metadata - Active");
		
		// TODO - BUG 986 has this blocked. Uncomment it later, and delete the work
		// around.
		// editMetadata.clickBackToList();
		// TODO - Work around - Delete Me
		metadataDetailsPage.clickMetadataDictionaryInAdmin();
		
		//Assert that the errors will fire.
		performErrorValidation();
		
		//Create a new Metadata record and validate values.
		createNewMetadata();
		
		//Edit Newly created temporary Metadata record
		editNewMetadataRecord();
		
		// Delete the newly created/edited record
		deleteNewlyCreatedRecord();
		
		//Log out
		metadataPage = new MetadataPageFactory();
		metadataPage.clickLogOffInHelloUser();

	}
	
	/**
	 * Utility method to delete the newly created record and assert that it can no
	 * longer be found.
	 */
	private void deleteNewlyCreatedRecord() {
		MetadataPageFactory metadataIndex = new MetadataPageFactory();
		
		metadataIndex.getMetadataTable().clickLinkInRow(tempMetadata, tempDescription, "Delete");
		
		DeleteMetadataPageFactory deleteMetadata = new DeleteMetadataPageFactory();
		
		assertEquals(deleteMetadata.readMetadata(), tempMetadata, "Delete Metadata - Metadata");
		assertEquals(deleteMetadata.readDescription(), tempDescription, "Delete Metadata - Description");
		assertEquals(deleteMetadata.readAdditionalInfo(), tempAdditionalInfo, "Delete Metadata - Additional Info");
		assertEquals(deleteMetadata.isActiveChecked(), tempActive, "Delete Metadata - Active");
		assertEquals(deleteMetadata.readMetadataCategory(), tempMetadataCategory, "Delete Metadata - Metadata Category");

		deleteMetadata.clickDelete();
		
		//Back on Index page
		metadataIndex = new MetadataPageFactory();
		assertEquals(metadataIndex.getMetadataTable().isRowInTable(tempMetadata, tempDescription, tempMetadataCategory), false, "Metadata Table - Temporary Value Deleted");
	}
	
	/**
	 * Utility method to edit a new <i>Metadata</i> to a different value and assert
	 * that the edit does in fact commit successfully.
	 */
	private void editNewMetadataRecord() {
		MetadataPageFactory metadataIndex = new MetadataPageFactory();
		
		//Navigate to newly created record
		metadataIndex.getMetadataTable().clickLinkInRow(tempMetadata, tempDescription, "Edit");
		
		//Now on the edit page
		EditMetadataPageFactory editMetadata = new EditMetadataPageFactory();
				
		//Update the value of the temporary record, to a new temporary record (random)
		retrieveTemporaryRecord();
		
		//Set new temporary values
		//Set the new fields with the temporary data
		editMetadata.selectMetadataCategory(tempMetadataCategory);
		editMetadata.setMetadata(tempMetadata);
		editMetadata.setDescription(tempDescription);
		editMetadata.setAdditionalInfo(tempAdditionalInfo);
		editMetadata.setActiveCheckbox(tempActive);
		
		//Save the record
		editMetadata.clickSave();
		
		//Ensure that the new record shows up on in the table
		metadataIndex = new MetadataPageFactory();
		AutomationHelper.waitSeconds(3);
		assertEquals(metadataIndex.getMetadataTable().isRowInTable(tempMetadata, tempDescription, tempAdditionalInfo, tempMetadataCategory), true, "Edited Temporary Record Added");
	}
	
	/**
	 * Utility method to create a new <i>Metadata</i> and validate that it in fact
	 * saves and is committed.
	 */
	private void createNewMetadata() {
		
		//First, navigate to the create page
		MetadataPageFactory metadataIndex = new MetadataPageFactory();
		metadataIndex.clickCreateNewMetadata();
		
		CreateMetadataPageFactory createMetadata = new CreateMetadataPageFactory();
		
		//Obtain a temporary record
		retrieveTemporaryRecord();
		
		//Set the new fields with the temporary data
		createMetadata.selectMetadataCategory(tempMetadataCategory);
		createMetadata.setMetadata(tempMetadata);
		createMetadata.setDescription(tempDescription);
		createMetadata.setAdditionalInfo(tempAdditionalInfo);
		createMetadata.setActiveCheckbox(tempActive);
		
		createMetadata.clickCreate();
		
		//Now back on the index page
		metadataIndex = new MetadataPageFactory();
		
		//Assert that the new field is in the table.
		assertEquals(metadataIndex.getMetadataTable().isRowInTable(tempMetadata, tempDescription, tempAdditionalInfo, tempMetadataCategory), true, "New Temporary Record Added");
		//Click the Edit link for the temporary added row
		metadataIndex.getMetadataTable().clickLinkInRow(tempMetadata, tempDescription, "Edit");
		
		//Ensure that the data is present after a create / view
		EditMetadataPageFactory editMetadata = new EditMetadataPageFactory();
		assertEquals(editMetadata.readMetadataCategorySelected(), tempMetadataCategory, "Edit Temporary Metadata Page - Metadata Category");
		assertEquals(editMetadata.readMetadata(), tempMetadata, "Edit Temporary Metadata Page - Metadata");
		assertEquals(editMetadata.readDescription(), tempDescription, "Edit Temporary Metadata Page - Description");
		assertEquals(editMetadata.readAdditionalInfo(), tempAdditionalInfo, "Edit Temporary Metadata Page - Additional Info");
		assertEquals(editMetadata.isActiveChecked(), tempActive, "Edit Temporary Metadata Page - Active");
		
		editMetadata.clickBackToList();

	}
	
	/**
	 * Utility Method that searches for an temporary record (dynamically gathered
	 * from data sheet) and assigns global variables for a task.
	 */
	private void retrieveTemporaryRecord() {

		ExcelDataConfig nodeTypeFile = getExcelFile("JMEDAdminDataMap.xlsx", "MetadataDataMap");

		List<Integer> rowIndexs = new ArrayList<Integer>();
		for (int i = 1; i <= nodeTypeFile.getRowCount(); i++) {

			// If the row is a Temporary row, then add it to the list.
			if (nodeTypeFile.getData(i, nodeTypeFile.getColumnIndex("Type")).equalsIgnoreCase("Temporary")) {
				rowIndexs.add(i);
			}
		}

		// Generate a random number that is between 1 and the upper limit of the
		// temporary records.
		Random rand = new Random();
		int rowIndex = rowIndexs.get(rand.nextInt(rowIndexs.size()));

		tempMetadataCategory = nodeTypeFile.getData(rowIndex, nodeTypeFile.getColumnIndex("Metadata Category"));
		tempMetadata = nodeTypeFile.getData(rowIndex, nodeTypeFile.getColumnIndex("Metadata"));
		tempDescription = nodeTypeFile.getData(rowIndex, nodeTypeFile.getColumnIndex("Description"));
		tempAdditionalInfo = nodeTypeFile.getData(rowIndex, nodeTypeFile.getColumnIndex("Additional Info"));
		tempActive = Boolean.valueOf(nodeTypeFile.getData(rowIndex, nodeTypeFile.getColumnIndex("Active")));
	}
		
	/**
	 * Utility method to handle checking for errors when incorrect data is entered
	 */
	private void performErrorValidation() {		
		
		//First, navigate to the Create page.
		MetadataPageFactory metadataIndex = new MetadataPageFactory();
		metadataIndex.clickCreateNewMetadata();
		
		//Obtain a reference to the datasheet
		ExcelDataConfig errorMessagesDatasheet = getExcelFile("JMEDContentMatrix.xlsx", "StaticTextValidations");
		
		//Now on the Create Metadata page
		CreateMetadataPageFactory createMetadata = new CreateMetadataPageFactory();
		
		//Make all "Required fields" errors fire by entering nothing
		createMetadata.clickCreate();
		
		String metadataCategoryExpectedError = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATEMETADATA_metadata_category_required"), "Content");
		assertEquals(createMetadata.readMetadataCategoryErrorMessage(), metadataCategoryExpectedError, "Create Metadata - Metadata Category Required");
		
		String metadataExpectedError = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATEMETADATA_metadata_required"), "Content");
		assertEquals(createMetadata.readMetadataErrorMessage(), metadataExpectedError, "Create Metadata - Metadata Required");
		
		String metadataDescriptionError = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATEMETADATA_description_required"), "Content");
		assertEquals(createMetadata.readDescriptionErrorMessage(), metadataDescriptionError, "Create Metadata - Description Required");
		
		String additionalInfoError = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATEMETADATA_additional_info_required"), "Content");
		assertEquals(createMetadata.readAdditionalInfoErrorMessage(), additionalInfoError, "Create Metadata - Additional Info Required");
		
		//Set the fields with values (bogus ones)
		String twoHundredFiftyOneChars = "One morning, when Gregor Samsa woke from troubled dreams, he found himself transformed in his bed into a horrible vermin. He lay on his armour-like back, and if he lifted his head a little he could see his brown belly, slightly domed and divided by ar";
		String fiveHundredOneChars = "abc def ghi jkl mno pqrs tuv wxyz ABC DEF GHI JKL MNO PQRS TUV WXYZ !\"§ $%& /() =?* '<> #|; ²³~ @`´ ©«» ¤¼× {} abc def ghi jkl mno pqrs tuv wxyz ABC DEF GHI JKL MNO PQRS TUV WXYZ !\"§ $%& /() =?* '<> #|; ²³~ @`´ ©«» ¤¼× {} abc def ghi jkl mno pqrs tuv wxyz ABC DEF GHI JKL MNO PQRS TUV WXYZ !\"§ $%& /() =?* '<> #|; ²³~ @`´ ©«» ¤¼× {} abc def ghi jkl mno pqrs tuv wxyz ABC DEF GHI JKL MNO PQRS TUV WXYZ !\"§ $%& /() =?* '<> #|; ²³~ @`´ ©«» ¤¼× {} abc def ghi jkl mno pqrs tuv wxyz ABC DEF GHI JKL MNO PQR";
		
		createMetadata.setMetadata(twoHundredFiftyOneChars);
		createMetadata.setDescription(fiveHundredOneChars);
		createMetadata.setAdditionalInfo(fiveHundredOneChars);
		
		createMetadata.clickCreate();
		
		metadataExpectedError = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATEMETADATA_metadata_max_chars"), "Content");
		assertEquals(createMetadata.readMetadataErrorMessage(), metadataExpectedError, "Create Metadata - Metadata Max Characters");

		metadataDescriptionError = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATEMETADATA_description_max_chars"), "Content");
		assertEquals(createMetadata.readDescriptionErrorMessage(), metadataDescriptionError, "Create Metadata - Description Max Characters");
		
		additionalInfoError = errorMessagesDatasheet.getData(errorMessagesDatasheet.getRowIndex("TestDataID", "CREATEMETADATA_additional_info_max_chars"), "Content");
		assertEquals(createMetadata.readAdditionalInfoErrorMessage(), additionalInfoError, "Create Metadata - Additional Info Max Characters");
		
		//TODO - When JMED-592 is corrected, uncomment this code and do not navigate out of create page.
		createMetadata.clickBackToList();
//		createMetadata.clickMetadataDictionaryInAdmin();
		
		metadataIndex = new MetadataPageFactory();

	}
		
	/**
	 * Returns a random user of type DHA Super User or DHA System Admin from the
	 * UsersData.xlsx
	 * 
	 * @return String[]
	 */
	@DataProvider
	private String[] loginAccounts() {

		ExcelDataConfig usersFile = getExcelFile("UsersData.xlsx", "JMEDUserData");

		List<String> adminUserNames = new ArrayList<String>();

		// Sting[] to store the different types of admin accounts. We will want
		// to test for each of these.
		String[] adminTypes = { "DHA Super User", "DHA System Admin" };

		// Get the row count for the data file
		int rowCount = usersFile.getRowCount();

		// Loop through each different admin role outlined above.
		for (String currentAdminType : adminTypes) {

			// For each user type, loop through the data set until we find the
			// accounts in which the TYPE matches the admin type.
			for (int i = 1; i <= rowCount; i++) {

				String currentUserNameInDatasheet = usersFile.getData(i, "Type");

				if (currentAdminType.equals(currentUserNameInDatasheet)) {
					adminUserNames.add(usersFile.getData(i, "UserName"));
				}
			}
		}

		String[] returnArray = adminUserNames.stream().toArray(String[]::new);
		int arrayCount = returnArray.length;

		// Return a String [] that only contains a random user with a login of
		// DHA Super User
		return new String[] { returnArray[AutomationHelper.generateRandomInteger(0, arrayCount - 1)] };
	}

	/**
	 * Utility Method that searches for an existing record (dynamically gathered
	 * from data sheet) and assigns global variables for a task.
	 */
	private void retrieveExistingRecord() {

		ExcelDataConfig nodeTypeFile = getExcelFile("JMEDAdminDataMap.xlsx", "MetadataDataMap");

		List<Integer> rowIndexs = new ArrayList<Integer>();
		for (int i = 1; i <= nodeTypeFile.getRowCount(); i++) {

			// If the row is a Temporary row, then add it to the list.
			if (nodeTypeFile.getData(i, nodeTypeFile.getColumnIndex("Type")).equalsIgnoreCase("Permanent")) {
				rowIndexs.add(i);
			}
		}

		// Generate a random number that is between 1 and the upper limit of the
		// temporary records.
		Random rand = new Random();
		int rowIndex = rowIndexs.get(rand.nextInt(rowIndexs.size()));

		existingMetadata = nodeTypeFile.getData(rowIndex, nodeTypeFile.getColumnIndex("Metadata"));
		existingDescription = nodeTypeFile.getData(rowIndex, nodeTypeFile.getColumnIndex("Description"));
		existingMetadataCategory = nodeTypeFile.getData(rowIndex, nodeTypeFile.getColumnIndex("Metadata Category"));
		existingActive = Boolean.valueOf(nodeTypeFile.getData(rowIndex, nodeTypeFile.getColumnIndex("Active")));
	}
	

}
