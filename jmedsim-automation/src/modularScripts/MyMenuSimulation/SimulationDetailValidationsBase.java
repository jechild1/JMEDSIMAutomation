package modularScripts.MyMenuSimulation;

import java.util.HashMap;

import configuration.JMEDConfig;
import pageFactories.JMEDSimTables;
import pageFactories.MyMenuPages.SimulationDetailsBase;
import pageFactories.MyMenuPages.SimulationDetailsPageFactory;
import utilities.ExcelDataConfig;

/**
 * Base Module Test Script for My Menu > Simulation > Detail/Delete pages to
 * test Administrative Details content that is on both pages
 * 
 * @author scott.brazelton
 *
 */
/**
 * @author scott.brazelton
 *
 */
public abstract class SimulationDetailValidationsBase extends JMEDConfig {

	// Administrative Detail tab of data map
	protected ExcelDataConfig addm;
	// prefix to use edit column if editing
	protected String pre;
	// boolean to tell if on initial creation or not
	protected boolean isOriginal;

	// SimulationDetailsPageFactory
	protected SimulationDetailsPageFactory sd;

	// Hashmap to store data map ID's
	private HashMap<String, String> dmIDs = new HashMap<String, String>();

	/**
	 * Sets a reference to a data map ID
	 * 
	 * @param hashmapKey
	 * @param dmID
	 */
	protected void setDMID(String hashmapKey, String dmID) {
		dmIDs.put(hashmapKey, dmID);
	}

	/**
	 * Returns a generic object reference of a data map ID based on the key
	 * supplied
	 * 
	 * @param hashmapKey
	 * @return String
	 */
	protected String getDMID(String hashmapKey) {
		return dmIDs.get(hashmapKey);
	}

	/**
	 * Performs validation on Administrative Details like items between the
	 * Simulation Details & Delete pages
	 */
	protected void validateAdministrativeDetails(SimulationDetailsBase sdb) {

		String addmID = getDMID("adminDetailsDataMapID");

		softAsserter.assertEquals(sdb.readTitle(),
				addm.getData(addmID, pre + "Title"),
				"Validating 'Title' in Administrative Details");

		// TODO: uncomment once Delete is made consistent with details
		// softAsserter.assertEquals(sdb.readDuration(),
		// addm.getData(addmID, pre + "DurationHours") + ":"
		// + addm.getData(addmID, pre + "DurationMinutes"),
		// "Validating 'Duration (minutes)' in Administrative Details");

		softAsserter.assertEquals(sdb.readAuthor(),
				addm.getData(addmID, pre + "Author"),
				"Validating 'Author' in Administrative Details");

		// TODO: information is missing from delete - see if can be added
		// softAsserter.assertEquals(sdb.readContactInformation(),
		// addm.getData(addmID,
		// "ContactInformation"),
		// "Validating 'Contact Information' in Administrative Details");

		// TODO: information is missing from delete - see if can be added
		// softAsserter.assertEquals(sdb.readCreatedBy(),
		// addm.getData(addmID,
		// "CreatedBy"),
		// "Validating 'Created By' in Administrative Details");

		softAsserter.assertEquals(sdb.readCreatedOn(),
				addm.getData(addmID, "CreatedOn"),
				"Validating 'Created On' in Administrative Details");

		// TODO: information is missing from delete - see if can be added
		// softAsserter.assertEquals(sdb.readContentKeywords(),
		// addm.getData(addmID,
		// "ContentKeywords"),
		// "Validating 'Content Keywords' in Administrative Details");

		softAsserter.assertEquals(sdb.isActiveChecked(),
				addm.getData(addmID, pre + "Active").equals("") ? false : true,
				"Validating 'Active' in Administrative Details");

		softAsserter.assertEquals(sdb.readUpdatedBy(),
				addm.getData(addmID, "UpdatedBy"),
				"Validating 'Updated By' in Administrative Details");

		softAsserter.assertEquals(sdb.readUpdatedOn(),
				addm.getData(addmID, "UpdatedOn"),
				"Validating 'Updated On' in Administrative Details");

		softAsserter.assertEquals(sdb.readTrainingType(),
				addm.getData(addmID, pre + "TrainingType"),
				"Validating 'Training Type' in Administrative Details");

	}

	/**
	 * Performs on-demand validations against specific row columns of a table
	 * 
	 * NOTE: Excel mapping must provide GroupTestDataID & ChangeType
	 * 
	 * @param dm
	 * @param dmGroupId
	 * @param table
	 * @param colKey
	 * @param colsToTest
	 * @param isColsEditable
	 */
	protected void validateTableColumns(ExcelDataConfig dm, String dmGroupId,
			JMEDSimTables table, String colKey, String[] colsToTest,
			boolean isColsEditable) {

		for (int i = 1; i <= dm.getRowCount(); i++) {
			String groupID = dm.getData(i, "GroupTestDataID");
			String changeType = dm.getData(i, "ChangeType");
			String editLine = changeType.equals("Edit") && !isOriginal
					&& isColsEditable ? pre : "";

			if (groupID.equals(dmGroupId)) {
				String eColKeyValue = dm.getData(i, editLine + colKey);

				for (String col : colsToTest) {
					String eColValue = dm.getData(i, editLine + col);

					// Verify row is present if not expected delete
					if ((!isOriginal && !changeType.equals("Delete"))
							|| isOriginal) {

						// Verify expected column value equals actual column
						// value
						String aColValue = table.readTableRowValue(colKey,
								eColKeyValue, col, false);
						softAsserter.assertEquals(aColValue, eColValue,
								String.format(
										"Validate '%s' %s added to table for %s %s in details",
										eColValue, col, eColKeyValue, colKey));

					} // end row present

					// Verify row deleted if expected
					if (!isOriginal && changeType.equals("Delete")) {

						boolean isRowInTable = table.isRowInTableByValue(colKey,
								eColKeyValue);
						softAsserter.assertEquals(isRowInTable, false,
								String.format(
										"Validate '%s' %s deleted from table in details",
										eColKeyValue, colKey));
					} // end row deleted

				} // end for colsToTest
			} // end if group equals expected loop
		} // end for

	}

}
