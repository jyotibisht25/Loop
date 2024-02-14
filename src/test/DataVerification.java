package test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageobjects.HistoryByStorePageObject;
import pageobjects.LoginPageObject;

public class DataVerification extends BaseTest {
	public WebDriver driver = initializeDriver();
	public HistoryByStorePageObject historySt;
	// Define the tolerance level
	double tolerance = 0.0001; // Adjust as per your requirements

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

	@Test(priority = 2)
	public void StoreGrandTotalAugust() throws InterruptedException {
		SoftAssert softAssert1 = new SoftAssert();
		historySt.clickChargebacksButton();
		historySt.clickHistoryByStore();
		historySt.clickDrillDown();
		historySt.getRowColCount();
		double difference = historySt.validationAug();
		// Assert if the absolute difference is within the tolerance level
		softAssert1.assertTrue(difference <= tolerance,
				"August month values are not equal to Grand total within tolerance");
		softAssert1.assertAll();
	}

	@Test(priority = 3)
	public void StoreGrandTotalSeptember() throws InterruptedException {
		SoftAssert softAssert2 = new SoftAssert();
		double difference = historySt.validationSep();
		// Assert if the absolute difference is within the tolerance level
		softAssert2.assertTrue(difference <= tolerance,
				"September month values are not equal to Grand Total within tolerance");
		softAssert2.assertAll();
	}

	@Test(priority = 4)
	public void StoreGrandTotalOctober() throws InterruptedException {
		SoftAssert softAssert3 = new SoftAssert();
		double difference = historySt.validationOctober();
		// Assert if the absolute difference is within the tolerance level
		softAssert3.assertTrue(difference <= tolerance,
				"October month values are not equal to Grand total within tolerance");
		softAssert3.assertAll();
	}

	@Test(priority = 5)
	public void StoreGrandTotalNovember() throws InterruptedException {
		SoftAssert softAssert4 = new SoftAssert();
		double difference = historySt.validationNovember();
		// Assert if the absolute difference is within the tolerance level
		softAssert4.assertTrue(difference <= tolerance,
				"November month values are not equal to Grand total within tolerance");
		softAssert4.assertAll();
	}

	@Test(priority = 6)
	public void StoreGrandTotalDecember() throws InterruptedException {
		SoftAssert softAssert5 = new SoftAssert();
		double difference = historySt.validationDecember();
		// Assert if the absolute difference is within the tolerance level
		softAssert5.assertTrue(difference <= tolerance,
				"December month values are not equal to Grand total within tolerance");
		softAssert5.assertAll();
	}

	@Test(priority = 7)
	public void StoreGrandTotalJanuary() throws InterruptedException {
		SoftAssert softAssert6 = new SoftAssert();
		double difference = historySt.validationJanuary();
		// Assert if the absolute difference is within the tolerance level
		softAssert6.assertTrue(difference <= tolerance,
				"January month values are not equal to Grand total within tolerance");
		softAssert6.assertAll();
	}

	@Test(priority = 8)
	public void StoreGrandTotalFebuary() throws InterruptedException {
		SoftAssert softAssert7 = new SoftAssert();
		double difference = historySt.validationFebuary();
		// Assert if the absolute difference is within the tolerance level
		softAssert7.assertTrue(difference <= tolerance,
				"Febuary month values are not equal to Grand total within tolerance");
		softAssert7.assertAll();
	}

}
