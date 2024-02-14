package pageobjects;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import AbstractComponenets.AbstractComponent;

public class TransactionsPageObject extends AbstractComponent {

	WebDriver driver;

	public TransactionsPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[text()='3P Chargebacks']")
	WebElement chargebacksButton;

	@FindBy(xpath = "//span[text()='Transactions']")
	WebElement transactionButton;

	@FindBy(xpath = "//span[text()='Locations']")
	WebElement selectLocation;

	@FindBy(xpath = "//button[text()='Clear']")
	WebElement clearButton;

	@FindBy(xpath = "//p[text()='Artisan Alchemy']")
	WebElement selectAA;

	@FindBy(xpath = "//p[text()='Blissful Buffet']")
	WebElement selectBB;

	@FindBy(xpath = "//button[@data-testid='applyBtn']")
	WebElement applyButton;

	@FindBy(xpath = "//span[text()='Marketplaces']")
	WebElement selectMarketplaces;

	@FindBy(xpath = "//p[text()='Grubhub']")
	WebElement selectGrubhub;

	@FindBy(xpath = "//button[text()='Download']")
	WebElement downloadButton;

	@FindBy(xpath = "///button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium css-1p58fjm-MuiButtonBase-root-MuiIconButton-root']")
	WebElement downloadButton1;

	// to click on 3P Chargebacks button
	public void clickChargebacksButton() {

		// wait till the visibility of chargeback button
		waitForElementToApper(chargebacksButton);
		chargebacksButton.click();

	}

	// to click select location
	public void selectLocation() {

		// wait till the visibility of locationb button
		waitForElementToApper(selectLocation);
		selectLocation.click();
		waitForElementToApper(clearButton);
		clearButton.click();
		waitForElementToApper(selectAA);
		selectAA.click();
		selectBB.click();
		applyButton.click();
	}

	// to select marketplaces
	public void selectMarketplaces() {

		// wait till the visibility of locationb button
		waitForElementToApper(selectMarketplaces);
		selectMarketplaces.click();
		waitForElementToApper(clearButton);
		clearButton.click();
		selectGrubhub.click();
		applyButton.click();
	}

	// download csv file
	public void downloadCSVFile() {
		Actions actions = new Actions(driver);

		// Scroll down the page by performing keyboard actions (e.g., pressing
		// PAGE_DOWN)
		actions.sendKeys(Keys.PAGE_DOWN).build().perform();
		waitForElementToApper(downloadButton);
		downloadButton.click();

	}

	public boolean validateCSVFile()
			throws FileNotFoundException, IOException, CsvValidationException, InterruptedException {
		Thread.sleep(5000);
		int columnIndex = 0; // Specify the index of the column you want to retrieve (0-based index) for
								// order it is 0
		int countRows = 0;
		List<String> columnData = new ArrayList<>();
		List<Long> columnDataInt = new ArrayList<>();
		String csvFile = "/Users/sachinbagla/Downloads/chargebacks_payouts_overview.csv";
		// to check if order is acsending order
		try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
			String[] line;
			while ((line = reader.readNext()) != null) {
				// Extract data from the specified column
				if (line.length > columnIndex) {
					columnData.add(line[columnIndex]);
					countRows++;
				}
			}
			// Use the extracted column data for further processing or assertions
			columnDataInt = columnData.stream().skip(1).map(s -> Long.valueOf(s.substring(2)))
					.collect(Collectors.toList());
//	         for (String data : columnData) {
//	        	  data = data.replace("O-", "");
//	          System.out.println(data);
//	         // Integer integerValue = Integer.parseInt(data);
//	          columnDataInt.add(Integer.parseInt(data));
//	          }
		}
		for (int i = 0; i < columnDataInt.size() - 1; i++) {
			if (columnDataInt.get(i) > columnDataInt.get(i + 1)) {
				return false;
			}
		}
		return true;
		// Assert if the CSV file is organized by order
		// Assert.assertTrue(isOrdered, "CSV file is not organized by order");

	}
}
