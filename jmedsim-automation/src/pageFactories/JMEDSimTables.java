package pageFactories;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import utilities.AutomationHelper;
import utilities.Tables;

/**
 * Table class for JMEDSim which exists primary to do paging. Most methods in
 * this class utilize the methods in the core table class, but include
 * pagination checks.
 * 
 * @author jesse.childress
 *
 */
public class JMEDSimTables extends Tables {

	// This is required in order to give the table class access to the base, which
	// is needed in order to call waitForPageToLoad() between page refreshes (the
	// spinner)
	JMEDSIMBase base = new JMEDSIMBase() {	};

	// Constructor, calling super.
	public JMEDSimTables(WebElement tableReference) {
		super(tableReference);
		// Until we initialize the elements, we can't use them. Without this, the code
		// appears to execute, but does not actually.
		base.initializeElements();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see utilities.Tables#isRowInTable(java.lang.String[])
	 */
	public boolean isRowInTable(String... rowValues) {
		// Boolean to indicate the row is found
		boolean found = false;
		int pagedTables = 0;

		String myConcatenatedString = "";
		for (String currentRowValue : rowValues) {
			// We must add a space between each value and the next. The final space is
			// trimmed in the next line.
			myConcatenatedString += currentRowValue + " ";
		}

		myConcatenatedString = ".*" + AutomationHelper.escapeStringForRegEx(myConcatenatedString).trim() + ".*";

		Reporter.log("Beginning isRowInTable search for " + myConcatenatedString + ".", true);

		// First, Try to find this on the first page. May not have to cycle
		List<WebElement> tableRows = table.findElements(By.tagName("tr"));
		int rowIterator = 0;
		for (WebElement currentRow : tableRows) {

			// Consider a .equals instead of .contains.
			String text = currentRow.getText().replace("\n", "").trim();
			System.out.println("Performing search on page " + readTablePageNumber() + " for row text: " + text);
			if (text.matches(myConcatenatedString)) {
				found = true;
				// TODO - Create a method to see which number is highlighted
				System.out.println("MATCH FOUND IN ROW " + rowIterator + " OF THE CURRENT TABLE PAGE.");
				break;
			}
			rowIterator++;
		}

		// If we did not find a match, we must table through.
		if (!found) {

			// Begin by starting on the first page.
			// If not already on the first page, ensure we go back to the first page
			while (isSkipToPreviousNavigationPresent()) {
				clickSkipToPreviousNavigationLink();
				base.waitForPageToLoad();
			}

			boolean finalTablePage = false;
			// Loop through each row and look for the string we pass in.
			while (!found && !finalTablePage) {

				if (!isSkipToNextNavigationPresent()) {
					finalTablePage = true;
				}

				// Create a list of table rows
				tableRows = table.findElements(By.tagName("tr"));

				// Increment the number of times we have checked a table for the value
				pagedTables += 1;
				rowIterator = 0;

				for (WebElement currentRow : tableRows) {

					// Consider a .equals instead of .contains.
					String text = currentRow.getText().replace("\n", "").trim();
					System.out.println("Performing search on page " + pagedTables + " for row text: " + text);
					if (text.matches(myConcatenatedString)) {
						found = true;
						System.out.println("MATCH FOUND IN ROW " + rowIterator + " OF TABLE PAGE " + pagedTables);
						break;
					}
					rowIterator++;
				}

				if (!found) {
					System.out.println("MATCH NOT FOUND ON PAGE " + pagedTables + " FOR VALUE " + "'" + myConcatenatedString + "'");

					if (isSkipToNextNavigationPresent()) {
						clickSkipToNextNavigationLink();
						base.waitForPageToLoad();
					}
				}
			}
		} // end if not found

		Reporter.log("Table search completed for " + myConcatenatedString + " and Row Found = " + found + " after searching through " + pagedTables + "pages.");
		Reporter.log("", true);

		return found;
	}
	
	public void deleteRowInTable(String rowContainsText) {
		// Boolean to indicate the row is found
//		boolean found = false;
		int pagedTables = 0;

		rowContainsText = ".*" + AutomationHelper.escapeStringForRegEx(rowContainsText).trim() + ".*";

		Reporter.log("Beginning Table Delete search for partial string '" + rowContainsText + "'.", true);

		// First, Try to find this on the first page. May not have to cycle
		List<WebElement> tableRows = table.findElements(By.tagName("tr"));
		int rowIterator = 0;
		
		for (WebElement currentRow : tableRows) {
			//Get the current row text
			String text = currentRow.getText().replace("\n", "").trim();
			
			System.out.println("Performing search on page " + readTablePageNumber() + " for row text: " + text);
			
			if (text.matches(rowContainsText)) {
//				found = true;
				// TODO - Create a method to see which number is highlighted
				System.out.println("MATCH FOUND IN ROW " + rowIterator + " OF THE TABLE PAGE " + pagedTables +1 + ".");
				
				clickLinkInRow(".*" + rowContainsText + "", "Delete");
				
//				break;
			}
			rowIterator++;
		}

		// If we did not find a match, we must table through.


			// Begin by starting on the first page.
			// If not already on the first page, ensure we go back to the first page
//			while (isSkipToPreviousNavigationPresent()) {
//				clickSkipToPreviousNavigationLink();
//				base.waitForPageToLoad();
//			}

			boolean finalTablePage = false;
			// Loop through each row and look for the string we pass in.
			while (!finalTablePage) {

				if (!isSkipToNextNavigationPresent()) {
					finalTablePage = true;
				}

				// Create a list of table rows
				tableRows = table.findElements(By.tagName("tr"));

				// Increment the number of times we have checked a table for the value
				pagedTables += 1;
				rowIterator = 0;

				for (WebElement currentRow : tableRows) {

					// Consider a .equals instead of .contains.
					String text = currentRow.getText().replace("\n", "").trim();
					System.out.println("Performing search on page " + pagedTables + " for row text: " + text);
					if (text.matches(rowContainsText)) {

						System.out.println("MATCH FOUND IN ROW " + rowIterator + " OF THE TABLE PAGE " + pagedTables + ".");
						
						clickLinkInRow(rowContainsText, "Delete");		
  						System.out.println();
						
						//Delete page
						
					}
					rowIterator++;
				}

//				if (!found) {
//					System.out.println("MATCH NOT FOUND ON PAGE " + pagedTables + " FOR VALUE " + "'" + rowContainsText + "'");

					if (isSkipToNextNavigationPresent()) {
						clickSkipToNextNavigationLink();
						base.waitForPageToLoad();
					}
//				}
			}

//		Reporter.log("Table search completed for " + rowContainsText + " and Row Found = " + found + " after searching through " + pagedTables + "pages.");
		Reporter.log("", true);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see utilities.Tables#readTableRowValue(java.lang.String, java.lang.String,
	 * java.lang.String, boolean)
	 */
	public String readTableRowValue(String primaryColumnHeader, String primaryColumnValue, String columnToRead, boolean throwEx) {

		String text;
		boolean found = false;

		// First, Try to get the text from the table on the page that were on. If that
		// works, we can be done.

		text = super.readTableRowValue(primaryColumnHeader, primaryColumnValue, columnToRead, false);

		// If we did not find any text, we must attempt to page through the table to
		// find it.
		int tableCounter = 1;
		if (text == null) {

			// Make sure we go to the first page of the table.
			while (isSkipToPreviousNavigationPresent()) {
				clickSkipToPreviousNavigationLink();
				base.waitForPageToLoad();
			}

			boolean finalTablePage = false;

			// After we get to the first page, we must check one page at a time until we
			// cycle through all of them.

			while (!found && !finalTablePage) {

				// First, navigate forward
				if (isSkipToNextNavigationPresent()) {
					clickSkipToNextNavigationLink();
					base.waitForPageToLoad();
					tableCounter++;
				}

				// Search for record again
				System.out.println("Searching for value in column '" + columnToRead + "' on table page: " + tableCounter);
				text = super.readTableRowValue(primaryColumnHeader, primaryColumnValue, columnToRead, false);

				if (text != null) {

					found = true;
					System.out.println("Found value '" + text + "' in column '" + columnToRead + "' on table page " + tableCounter);
					break;
				}

				if (!isSkipToNextNavigationPresent()) {
					finalTablePage = true;
				}
			}
		}
		// Else we found text on the first page
		else {
			System.out.println("Found value '" + text + "' in column '" + columnToRead + "' on table page " + tableCounter);
		}

		if (text == null && throwEx) {
			throw new RuntimeException("Cannot find a value in the '" + columnToRead + "' column where the primary column header is '" + primaryColumnHeader
					+ "' and the identifying value in that primary column is '" + primaryColumnValue + "'. Looked through " + tableCounter + " table pages.");
		}

		return text;
	}

	/**
	 * Clicks a link in table row with that matches a single passed in value, and
	 * contains the passed in Link Text.
	 * 
	 * @param rowValueOne
	 * @param linkText
	 */
	public void clickLinkInRow(String rowValueOne, String linkText) {
		Reporter.log("Beginning link click for row " + rowValueOne + " and link title " + linkText + ".", true);

		WebElement myRow = null;
		// First, try to find the table row / link on the current page before we do
		// paging.
		System.out.println("Searching for value '" + rowValueOne + "' on the initial page " + readTablePageNumber() + " for link text '" + linkText + "'.");

		// Create a list of table rows
		List<WebElement> tableRows = table.findElements(By.tagName("tr"));

		// Loop through all the table rows to find the row containing rowValueOne
		for (WebElement currentRow : tableRows) {
			String currentRowText = currentRow.getText().trim().replace("\n", "");
			//TODO - ADDED
			AutomationHelper.escapeStringForRegEx(currentRowText);
			if (currentRowText.matches(rowValueOne)) {
				myRow = currentRow;
				break;
			}
		}

		// If we did not find the row on the first pass, we must work with pagination.
		if (myRow == null) {

			// Make sure we go to the first page of the table.
			while (isSkipToPreviousNavigationPresent()) {
				clickSkipToPreviousNavigationLink();
				base.waitForPageToLoad();
			}

			//Keeps track of which table page were on.
			int tableCounter = 1;
			//Keeps track of the final table, when reached.
			boolean finalTablePage = false;

			//Keep performing this loop until we reach the end of the table and haven't found a row.
			while (myRow == null && !finalTablePage) {

				System.out.println("Searching for value '" + rowValueOne + "' on table page " + tableCounter + " for link text '" + linkText + "'.");

				if (!isSkipToNextNavigationPresent()) {
					finalTablePage = true;
					
				}
				
				// Create a list of table rows
				tableRows = table.findElements(By.tagName("tr"));

				// Loop through all the table rows to find the row containing rowValueOne
				for (WebElement currentRow : tableRows) {
					String currentRowText = currentRow.getText().trim();
					if (currentRowText.contains(rowValueOne)) {
						myRow = currentRow;
						break;
					}
				}

				if (myRow == null) {
					if (isSkipToNextNavigationPresent()) {
						clickSkipToNextNavigationLink();
						base.waitForPageToLoad();
						tableCounter++;
					}
				} else {
					System.out.println("Found row text '" + rowValueOne + "' on table page " + tableCounter + " with link text of '" + linkText + "'.");
					break;
				}

			}

			// If we did not find a row, we should throw an exception and not
			// process further.
			if (myRow == null) {
				throw new RuntimeException("Cannot click link. There are no rows found in the table for " + rowValueOne);
			}
		} // end If Row Wasnt found initially

		WebElement link = myRow.findElement(By.linkText(linkText));
		link.click();

		Reporter.log("Link " + linkText + " clicked for row " + rowValueOne + ".", true);
		Reporter.log("", true);
	}
	
	/**
	 * Clicks a link in table row with that matches a single passed in value, and
	 * contains the passed in Link Text.
	 * 
	 * @param rowValueOne
	 * @param rowValueTwo
	 * @param linkText
	 */
	public void clickLinkInRow(String rowValueOne, String rowValueTwo, String linkText) {
		Reporter.log(
				"Beginning link click for row " + rowValueOne + " "	+ rowValueTwo + " and link text " + linkText + ".", true);

		// Take the two row values and concatenate them.
		String myConcatenatedString = rowValueOne.trim() + " "	+ rowValueTwo.trim();
		
		WebElement myRow = null;
		
		// First, try to find the table row / link on the current page before we do
		// paging.
		System.out.println("Searching for value '" + rowValueOne + "' and '" + rowValueTwo + "'" + "' on the initial page " + readTablePageNumber() + " for link text '" + linkText + "'.");

		// Create a list of table rows
		List<WebElement> tableRows = table.findElements(By.tagName("tr"));

		// Loop through all the table rows to find the row containing the value
		// 1 and value 2 combination.
		for (WebElement currentRow : tableRows) {
			if (currentRow.getText().trim().contains(myConcatenatedString)) {
				myRow = currentRow;
				break;
			}
		}

		// If we did not find the row on the first pass, we must work with pagination.
		if (myRow == null) {

			// Make sure we go to the first page of the table.
			while (isSkipToPreviousNavigationPresent()) {
				clickSkipToPreviousNavigationLink();
				base.waitForPageToLoad();
			}

			//Keeps track of which table page were on.
			int tableCounter = 1;
			//Keeps track of the final table, when reached.
			boolean finalTablePage = false;

			//Keep performing this loop until we reach the end of the table and haven't found a row.
			while (myRow == null && !finalTablePage) {

				System.out.println("Searching for value '" + rowValueOne + "' and '" + rowValueTwo + "' on table page " + tableCounter + " for link text '" + linkText + "'.");

				if (!isSkipToNextNavigationPresent()) {
					finalTablePage = true;
					
				}
				
				// Create a list of table rows
				tableRows = table.findElements(By.tagName("tr"));

				// Loop through all the table rows to find the row containing the value
				// 1 and value 2 combination.
				for (WebElement currentRow : tableRows) {
					if (currentRow.getText().trim().contains(myConcatenatedString)) {
						myRow = currentRow;
						break;
					}
				}

				if (myRow == null) {
					if (isSkipToNextNavigationPresent()) {
						clickSkipToNextNavigationLink();
						base.waitForPageToLoad();
						tableCounter++;
					}
				} else {
					System.out.println("Found row text '" + rowValueOne + "' and '" + rowValueTwo + "' on table page " + tableCounter + " with link text of '" + linkText + "'.");
					break;
				}

			}

			// If we did not find a row, we should throw an exception and not
			// process further.
			if (myRow == null) {
				throw new RuntimeException("Cannot click link. There are no rows found in the table for " + rowValueOne  + "' and '" + rowValueTwo );
			}
		} // end If Row Was'nt found initially

		WebElement link = myRow.findElement(By.linkText(linkText));
		link.click();

		Reporter.log("Link " + linkText + " clicked for row " + rowValueOne + "' and '" + rowValueTwo , true);
		Reporter.log("", true);
	}

}
