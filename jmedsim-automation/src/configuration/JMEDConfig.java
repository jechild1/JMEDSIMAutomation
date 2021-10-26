package configuration;

import utilities.ExcelDataConfig;

/**
 * Abstract class containing methods to set up browser type and configuration.
 * 
 * Extends TestConfig
 * 
 * @author jesse.childress
 *
 */
public abstract class JMEDConfig extends TestConfig {

	// private static final String ADDRESS =
	// "https://nat-p12.esp.tatrc.org/JMedSim_Test/";
	// private static final String ADDRESS =
	// "https://nat-p12.esp.tatrc.org/JMedSim_SH/";

	protected final static String BASE_URL = "https://nat-p12.tatrc.org/JMedSim_Test/";

	public JMEDConfig() {
		super(BASE_URL);
	}

	public void validateStaticContent(String expectedTextTestDataID,
			String actualText, String pageAreaName, String fieldName) {

		validateStaticText(expectedTextTestDataID, actualText, pageAreaName,
				fieldName, "Content");

	}

	public void validateStaticTitle(String expectedTextTestDataID,
			String actualText, String pageAreaName, String fieldName) {

		validateStaticText(expectedTextTestDataID, actualText, pageAreaName,
				fieldName, "Title");

	}

	private void validateStaticText(String expectedTextTestDataID,
			String actualText, String pageAreaName, String fieldName,
			String dataType) {

		ExcelDataConfig staticTextValidations = getExcelFile(
				"JMEDContentMatrix.xlsx", "StaticTextValidations");

		softAsserter.assertEquals(actualText,
				staticTextValidations.getData(expectedTextTestDataID, dataType),
				String.format("Validating %s text in Edit Simulation (%s)",
						fieldName, pageAreaName));

	}

}
