package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import AbstractComponenets.AbstractComponent;

public class HistoryByStorePageObject extends AbstractComponent {

	WebDriver driver;
	static public int rowCount;
	static public int colCount;
	static double totalSumAugust;
	static double totalSumSep;
	static double totalSumOct;
	static double totalSumNov;
	static double totalSumDec;
	static double totalSumJan;
	static double totalSumFeb;

	public HistoryByStorePageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[text()='Skip for now']")
	WebElement skipForNowButton;

	@FindBy(xpath = "//h2[@class='MuiTypography-root MuiTypography-h2 css-3mg676']")
	WebElement welcomeMsg;

	@FindBy(xpath = "//span[text()='3P Chargebacks']")
	WebElement chargebacksButton;

	@FindBy(xpath = "//span[text()='History by Store']")
	WebElement historyButton;

	@FindBy(id = "drilldown-options-fc")
	WebElement drillDown;

	@FindBy(xpath = "//li[@data-value='reversals']")
	WebElement selectReversal;

	@FindBy(xpath = "//table[@class='MuiTable-root css-l6sbfr-MuiTable-root']")
	WebElement table;

	@FindBy(xpath = "//table[@class='MuiTable-root css-l6sbfr-MuiTable-root']//tr")
	List<WebElement> rows;

	@FindBy(xpath = "//table[@class='MuiTable-root css-l6sbfr-MuiTable-root']//th")
	List<WebElement> cols;

	@FindBy(xpath = "//h6[text()='Store name']")
	WebElement storeName;

	@FindBy(xpath = "//span[text()='Transactions']")
	WebElement transactionButton;

	// skip for now alert
	public void SkipForNow() {

		// wait till the visibility of skip button
		waitForElementToApper(skipForNowButton);
		skipForNowButton.click();
		

	}

	public String getWelcomeMsgg() {
		return welcomeMsg.getText();

	}

	// to click on 3P Chargebacks button
	public void clickChargebacksButton() {

		// wait till the visibility of chargeback button
		waitForElementToApper(chargebacksButton);
		chargebacksButton.click();

	}

//to click on transaction
	public TransactionsPageObject clickTransactions() {
		// wait till the visibility of history by store button
		waitForElementToApper(transactionButton);
		transactionButton.click();
		return new TransactionsPageObject(driver);
	}

	// to click on History by Store
	public void clickHistoryByStore() {
		// wait till the visibility of history by store button
		waitForElementToApper(historyButton);
		historyButton.click();
	}

	// to click on History by Store
	public void clickDrillDown() {
		waitForElementToApper(drillDown);
		drillDown.click();
		selectReversal.click();
	}

	// to get row count
	public void getRowCount() throws InterruptedException {
		waitForElementToApper(table);
		rowCount = rows.size();
		System.out.println("row count : " + rowCount);

	}

	// to get row colum count
	public void getRowColCount() {
		waitForElementToApper(storeName);
		rowCount = rows.size();
		System.out.println("row count : " + rowCount);
		colCount = cols.size();
		System.out.println("Columns count : " + colCount);

	}

	// function to sum all values in column as per input month
	public static double SumLocationvalue(WebDriver driver, int l, String month) {

		double sum = 0;
		double doubleValue = 0.00;
		// TODO Auto-generated method stub
		for (int k = 1; k < rowCount - 2; k++) {
			String value1 = driver.findElement(By.xpath(
					"//table[@class='MuiTable-root css-l6sbfr-MuiTable-root']//tbody/tr[" + k + "]/td[" + l + "]"))
					.getText();
			System.out.println("without modifiying " + value1);
			value1 = value1.replace("$", "");
			doubleValue = Double.parseDouble(value1);
			sum = sum + doubleValue;
		}
		System.out.println("Total of this " + month + "is : " + sum);
		return sum;
	}

	public double validationAug() {
		// Validation for August month
		totalSumAugust = SumLocationvalue(driver, 2, "August");
		System.out.println("Total of this month is : " + totalSumAugust);
		String ExpectedAugustTotal = driver
				.findElement(By.xpath("//table[@class='MuiTable-root css-l6sbfr-MuiTable-root']//tbody/tr[12]/td[2]"))
				.getText();
		System.out.println("Total of this month expected is : " + ExpectedAugustTotal);
		ExpectedAugustTotal = ExpectedAugustTotal.replace("$", "");
		double ExpectedAugustTotalDouble = Double.parseDouble(ExpectedAugustTotal);
		Assert.assertEquals(ExpectedAugustTotalDouble, totalSumAugust);

		// Compare the absolute difference between the values with the tolerance
		double difference = Math.abs(ExpectedAugustTotalDouble - totalSumAugust);

		return difference;
	}

	public double validationSep() {
		// Validation for September month
		totalSumSep = SumLocationvalue(driver, 3, "September");
		System.out.println("Total of this month is : " + totalSumSep);
		String ExpectedSepTotal = driver
				.findElement(By.xpath("//table[@class='MuiTable-root css-l6sbfr-MuiTable-root']//tbody/tr[12]/td[3]"))
				.getText();
		System.out.println("Total of this month expected is : " + ExpectedSepTotal);
		ExpectedSepTotal = ExpectedSepTotal.replace("$", "");
		double ExpectedSepTotalDouble = Double.parseDouble(ExpectedSepTotal);
		Assert.assertEquals(ExpectedSepTotalDouble, totalSumSep);

		// Compare the absolute difference between the values with the tolerance
		double difference = Math.abs(ExpectedSepTotalDouble - totalSumSep);

		return difference;
	}

	public double validationOctober() {
		// validation for October month
		totalSumOct = SumLocationvalue(driver, 4, "October");
		System.out.println("Total of this month is : " + totalSumOct);
		String ExpectedOctTotal = driver
				.findElement(By.xpath("//table[@class='MuiTable-root css-l6sbfr-MuiTable-root']//tbody/tr[12]/td[4]"))
				.getText();
		System.out.println("Total of this month expected is : " + ExpectedOctTotal);
		ExpectedOctTotal = ExpectedOctTotal.replace("$", "");
		ExpectedOctTotal = ExpectedOctTotal.replace(",", "");
		double ExpectedOctTotalDouble = Double.parseDouble(ExpectedOctTotal);
		// Compare the absolute difference between the values with the tolerance
		double difference = Math.abs(ExpectedOctTotalDouble - totalSumOct);

		return difference;

	}

	public double validationNovember() {
		totalSumNov = SumLocationvalue(driver, 5, "November");
		System.out.println("Total of this month is : " + totalSumNov);
		String ExpectedNovTotal = driver
				.findElement(By.xpath("//table[@class='MuiTable-root css-l6sbfr-MuiTable-root']//tbody/tr[12]/td[5]"))
				.getText();
		System.out.println("Total of this month expected is : " + ExpectedNovTotal);
		ExpectedNovTotal = ExpectedNovTotal.replace("$", "");
		ExpectedNovTotal = ExpectedNovTotal.replace(",", "");
		double ExpectedNovTotalDouble = Double.parseDouble(ExpectedNovTotal);
		// Compare the absolute difference between the values with the tolerance
		double difference = Math.abs(ExpectedNovTotalDouble - totalSumNov);

		return difference;
	}

	public double validationDecember() {
		totalSumDec = SumLocationvalue(driver, 6, "December");
		System.out.println("Total of this month is : " + totalSumDec);
		String ExpectedDecTotal = driver
				.findElement(By.xpath("//table[@class='MuiTable-root css-l6sbfr-MuiTable-root']//tbody/tr[12]/td[5]"))
				.getText();
		System.out.println("Total of this month expected is : " + ExpectedDecTotal);
		ExpectedDecTotal = ExpectedDecTotal.replace("$", "");
		ExpectedDecTotal = ExpectedDecTotal.replace(",", "");
		double ExpectedDecTotalDouble = Double.parseDouble(ExpectedDecTotal);
		// Compare the absolute difference between the values with the tolerance
		double difference = Math.abs(ExpectedDecTotalDouble - totalSumDec);

		return difference;
	}

	public double validationJanuary() {
		totalSumJan = SumLocationvalue(driver, 7, "January");
		System.out.println("Total of this month is : " + totalSumJan);
		String ExpectedJanTotal = driver
				.findElement(By.xpath("//table[@class='MuiTable-root css-l6sbfr-MuiTable-root']//tbody/tr[12]/td[5]"))
				.getText();
		System.out.println("Total of this month expected is : " + ExpectedJanTotal);
		ExpectedJanTotal = ExpectedJanTotal.replace("$", "");
		ExpectedJanTotal = ExpectedJanTotal.replace(",", "");
		double ExpectedJanTotalDouble = Double.parseDouble(ExpectedJanTotal);
		// Compare the absolute difference between the values with the tolerance
		double difference = Math.abs(ExpectedJanTotalDouble - totalSumJan);

		return difference;
	}

	public double validationFebuary() {
		totalSumFeb = SumLocationvalue(driver, 8, "Febuary");
		System.out.println("Total of this month is : " + totalSumFeb);
		String ExpectedFebTotal = driver
				.findElement(By.xpath("//table[@class='MuiTable-root css-l6sbfr-MuiTable-root']//tbody/tr[12]/td[5]"))
				.getText();
		System.out.println("Total of this month expected is : " + ExpectedFebTotal);
		ExpectedFebTotal = ExpectedFebTotal.replace("$", "");
		ExpectedFebTotal = ExpectedFebTotal.replace(",", "");
		double ExpectedFebTotalDouble = Double.parseDouble(ExpectedFebTotal);
		// Compare the absolute difference between the values with the tolerance
		double difference = Math.abs(ExpectedFebTotalDouble - totalSumFeb);

		return difference;
	}
}
