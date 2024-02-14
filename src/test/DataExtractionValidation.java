package test;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.opencsv.exceptions.CsvValidationException;
import pageobjects.HistoryByStorePageObject;
import pageobjects.LoginPageObject;
import pageobjects.TransactionsPageObject;

/**
 * This class represents a test suite for data extraction validation. It extends
 * the BaseTest class, which provides basic setup and teardown methods for
 * tests. This suite includes tests for logging in, selecting location and
 * marketplaces, generating CSV files, and validating CSV files.
 */
public class DataExtractionValidation extends BaseTest {

	public HistoryByStorePageObject historySt;
	public TransactionsPageObject transaction;

	/**
	 * Test method for logging into the application and verifying successful login.
	 * The method navigates to the TryLoop application, enters login credentials,
	 * and asserts the welcome message upon successful login.
	 */
	@Test(priority = 1)
	public void loginTryLoop() {
		// create login object to call action methods to login
		driver.get("https://app.tryloop.ai/");
		LoginPageObject login = new LoginPageObject(driver);
		login.loginTryLoop();
		login.enterEmail("QA-engineer-assignment@test.com");
		login.enterPassword("QApassword123$");

		// After clicking the login button History by store page will be clicked
		historySt = login.loginButton();

		historySt.SkipForNow();
		String welcomeMsg = historySt.getWelcomeMsgg();
		Assert.assertTrue(welcomeMsg.equalsIgnoreCase("Welcome back, QA Engineer 👋"));
	}

	/**
	 * Test method for selecting location and marketplaces. The method clicks on the
	 * Chargebacks button, navigates to Transactions page, selects a location, and
	 * selects marketplaces.
	 */
	@Test(priority = 2)
	public void selectLocationMarketplaces() {
		historySt.clickChargebacksButton();
		transaction = historySt.clickTransactions();
		transaction.selectLocation();
		transaction.selectMarketplaces();
	}

	/**
	 * Test method for generating a CSV file. The method triggers the download of a
	 * CSV file.
	 */
	@Test(priority = 3)
	public void generateCSVFile() {
		transaction.downloadCSVFile();
	}

	/**
	 * Test method for validating a CSV file. The method validates the downloaded
	 * CSV file.
	 * 
	 * @throws CsvValidationException If there is an error in CSV validation.
	 * @throws FileNotFoundException  If the CSV file is not found.
	 * @throws IOException            If there is an I/O error.
	 * @throws InterruptedException   If the thread is interrupted while waiting.
	 */
	@Test(priority = 4)
	public void validateCSVFile()
			throws CsvValidationException, FileNotFoundException, IOException, InterruptedException {
		Boolean result = transaction.validateCSVFile();
		System.out.println(result);
	}

}
