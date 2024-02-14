package test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvValidationException;

import pageobjects.HistoryByStorePageObject;
import pageobjects.LoginPageObject;
import pageobjects.TransactionsPageObject;

public class DataExtractionValidation extends BaseTest {

	public WebDriver driver = initializeDriver();
	public HistoryByStorePageObject historySt;
	public TransactionsPageObject transaction;

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
	public void selectLocationMarketplaces() {
		historySt.clickChargebacksButton();
		transaction = historySt.clickTransactions();
		transaction.selectLocation();
		transaction.selectMarketplaces();
	}

	@Test(priority = 3)
	public void generateCSVFile() {
		transaction.downloadCSVFile();
	}

	@Test(priority = 4)
	public void validateCSVFile()
			throws CsvValidationException, FileNotFoundException, IOException, InterruptedException {
		Boolean result = transaction.validateCSVFile();
		System.out.println(result);
	}

}
