package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageobjects.HistoryByStorePageObject;
import pageobjects.LoginPageObject;

/**
 * This class contains tests for data verification. It extends the BaseTest
 * class, which provides basic setup and teardown methods for tests. These tests
 * validate the grand total values for different months.
 */
public class DataVerification extends BaseTest {

	public HistoryByStorePageObject historySt;
	// Define the tolerance level for asserting the differences.
	private double tolerance = 0.0001;

	/**
	 * Test method for logging into the application and navigating to the History By
	 * Store page. It also performs actions like skipping, clicking on buttons, and
	 * getting row/column counts.
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
		historySt.clickChargebacksButton();
		historySt.clickHistoryByStore();
		historySt.clickDrillDown();
		historySt.getRowColCount();
	}

	/**
	 * Test method for verifying the grand total values for the month of August. It
	 * uses SoftAssert to perform assertions with a tolerance level.
	 * 
	 * @throws InterruptedException If the thread is interrupted while waiting.
	 */
	@Test(priority = 2, enabled = true)
	public void StoreGrandTotalAugust() throws InterruptedException {
		SoftAssert softAssert1 = new SoftAssert();

		double difference = historySt.validationAug();
		// Assert if the absolute difference is within the tolerance level
		softAssert1.assertTrue(difference <= tolerance,
				"August month values are not equal to Grand total within tolerance");
		softAssert1.assertAll();
	}

	/**
	 * Test method for verifying the grand total values for the month of September.
	 * It uses SoftAssert to perform assertions with a tolerance level.
	 * 
	 * @throws InterruptedException If the thread is interrupted while waiting.
	 */
	@Test(priority = 3, enabled = true)
	public void StoreGrandTotalSeptember() throws InterruptedException {
		SoftAssert softAssert2 = new SoftAssert();
		double difference = historySt.validationSep();
		// Assert if the absolute difference is within the tolerance level
		softAssert2.assertTrue(difference <= tolerance,
				"September month values are not equal to Grand Total within tolerance");
		softAssert2.assertAll();
	}

	/**
	 * Test method for verifying the grand total values for the month of October. It
	 * uses SoftAssert to perform assertions with a tolerance level.
	 * 
	 * @throws InterruptedException If the thread is interrupted while waiting.
	 */
	@Test(priority = 4, enabled = true)
	public void StoreGrandTotalOctober() throws InterruptedException {
		SoftAssert softAssert3 = new SoftAssert();
		double difference = historySt.validationOctober();
		// Assert if the absolute difference is within the tolerance level
		softAssert3.assertTrue(difference <= tolerance,
				"October month values are not equal to Grand total within tolerance");
		softAssert3.assertAll();
	}

	/**
	 * Test method for verifying the grand total values for the month of Novemeber.
	 * It uses SoftAssert to perform assertions with a tolerance level.
	 * 
	 * @throws InterruptedException If the thread is interrupted while waiting.
	 */
	@Test(priority = 5, enabled = true)
	public void StoreGrandTotalNovember() throws InterruptedException {
		SoftAssert softAssert4 = new SoftAssert();
		double difference = historySt.validationNovember();
		// Assert if the absolute difference is within the tolerance level
		softAssert4.assertTrue(difference <= tolerance,
				"November month values are not equal to Grand total within tolerance");
		softAssert4.assertAll();
	}

	/**
	 * Test method for verifying the grand total values for the month of December.
	 * It uses SoftAssert to perform assertions with a tolerance level.
	 * 
	 * @throws InterruptedException If the thread is interrupted while waiting.
	 */
	@Test(priority = 6, enabled = true)
	public void StoreGrandTotalDecember() throws InterruptedException {
		SoftAssert softAssert5 = new SoftAssert();
		double difference = historySt.validationDecember();
		// Assert if the absolute difference is within the tolerance level
		softAssert5.assertTrue(difference <= tolerance,
				"December month values are not equal to Grand total within tolerance");
		softAssert5.assertAll();
	}

	/**
	 * Test method for verifying the grand total values for the month of January. It
	 * uses SoftAssert to perform assertions with a tolerance level.
	 * 
	 * @throws InterruptedException If the thread is interrupted while waiting.
	 */
	@Test(priority = 7, enabled = true)
	public void StoreGrandTotalJanuary() throws InterruptedException {
		SoftAssert softAssert6 = new SoftAssert();
		double difference = historySt.validationJanuary();
		// Assert if the absolute difference is within the tolerance level
		softAssert6.assertTrue(difference <= tolerance,
				"January month values are not equal to Grand total within tolerance");
		softAssert6.assertAll();
	}

	/**
	 * Test method for verifying the grand total values for the month of Febuary. It
	 * uses SoftAssert to perform assertions with a tolerance level.
	 * 
	 * @throws InterruptedException If the thread is interrupted while waiting.
	 */
	@Test(priority = 8, enabled = true)
	public void StoreGrandTotalFebuary() throws InterruptedException {
		SoftAssert softAssert7 = new SoftAssert();
		double difference = historySt.validationFebruary();
		// Assert if the absolute difference is within the tolerance level
		softAssert7.assertTrue(difference <= tolerance,
				"Febuary month values are not equal to Grand total within tolerance");
		softAssert7.assertAll();
	}

}
