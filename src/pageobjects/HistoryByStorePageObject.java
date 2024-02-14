package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import AbstractComponenets.AbstractComponent;

/**
 * This class represents the Page Object for the History by Store page in a web application.
 * It contains methods to interact with various elements on the page and perform validations.
 */
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

	/**
	 * Constructs a new HistoryByStorePageObject with the specified WebDriver.
	 * 
	 * @param driver The WebDriver instance to use.
	 */
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

	@FindBy(xpath = "//button[@data-testid='pagination-next']")
	WebElement nextButton;

	/**
	 * Clicks the 'Skip for now' button.
	 */
	public void SkipForNow() {
		waitForElementToAppear(skipForNowButton);
		skipForNowButton.click();
	}

	/**
	 * Retrieves the welcome message text.
	 * 
	 * @return The text of the welcome message.
	 */
	public String getWelcomeMsgg() {
		return welcomeMsg.getText();
	}

	/**
	 * Clicks the '3P Chargebacks' button.
	 */
	public void clickChargebacksButton() {
		waitForElementToAppear(chargebacksButton);
		chargebacksButton.click();
	}

	/**
	 * Clicks the 'Transactions' button and navigates to the Transactions page.
	 * 
	 * @return A TransactionsPageObject representing the Transactions page.
	 */
	public TransactionsPageObject clickTransactions() {
		waitForElementToAppear(transactionButton);
		transactionButton.click();
		return new TransactionsPageObject(driver);
	}

	/**
	 * Clicks the 'History by Store' button.
	 */
	public void clickHistoryByStore() {
		waitForElementToAppear(historyButton);
		historyButton.click();
	}

	/**
	 * Clicks the 'Drill Down' button.
	 */
	public void clickDrillDown() {
		waitForElementToAppear(drillDown);
		drillDown.click();
		selectReversal.click();
	}

	/**
	 * Retrieves the number of rows in the table.
	 * 
	 * @throws InterruptedException if an interrupt occurs while waiting for the
	 *                              element to appear.
	 */
	public void getRowCount() throws InterruptedException {
		waitForElementToAppear(table);
		rowCount = rows.size();
		System.out.println("row count : " + rowCount);
	}

	/**
	 * Retrieves the number of rows and columns in the table.
	 */
	public void getRowColCount() {
		waitForElementToAppear(storeName);
		rowCount = rows.size();
		System.out.println("row count : " + rowCount);
		colCount = cols.size();
		System.out.println("Columns count : " + colCount);
	}

	/**
	 * Sums all values in a column corresponding to a specified month.
	 * 
	 * @param driver The WebDriver instance to use.
	 * @param l      The index of the column.
	 * @param month  The name of the month.
	 * @return The total sum of values in the column.
	 * @throws InterruptedException if an interrupt occurs while waiting for the
	 *                              element to appear.
	 */
	public double sumLocationValue(WebDriver driver, int l, String month) throws InterruptedException {
		Actions actions = new Actions(driver);
		double sum = 0;
		double doubleValue = 0.00;
		for (int k = 1; k < rowCount - 2; k++) {
			String value1 = driver.findElement(By.xpath(
					"//table[@class='MuiTable-root css-l6sbfr-MuiTable-root']//tbody/tr[" + k + "]/td[" + l + "]"))
					.getText();
			System.out.println("without modifying " + value1);
			value1 = value1.replace("$", "");
			doubleValue = Double.parseDouble(value1);
			sum = sum + doubleValue;
		}
		System.out.println("Total of this " + month + " is : " + sum);
		return sum;
	}

	/**
	 * Performs validation for the month of August.
	 * 
	 * @return The absolute difference between expected and actual values.
	 * @throws InterruptedException if an interrupt occurs while summing the values.
	 */
	public double validationAug() throws InterruptedException {
		totalSumAugust = sumLocationValue(driver, 2, "August");
		System.out.println("Total of this month is : " + totalSumAugust);
		String expectedAugustTotal = driver
				.findElement(By.xpath("//table[@class='MuiTable-root css-l6sbfr-MuiTable-root']//tbody/tr[12]/td[2]"))
				.getText();
		System.out.println("Total of this month expected is : " + expectedAugustTotal);
		expectedAugustTotal = expectedAugustTotal.replace("$", "");
		double expectedAugustTotalDouble = Double.parseDouble(expectedAugustTotal);
		Assert.assertEquals(expectedAugustTotalDouble, totalSumAugust);

		// Compare the absolute difference between the values with the tolerance
		double difference = Math.abs(expectedAugustTotalDouble - totalSumAugust);

		return difference;
	}

	/**
	 * Performs validation for the month of September.
	 * 
	 * @return The absolute difference between expected and actual values.
	 * @throws InterruptedException if an interrupt occurs while summing the values.
	 */
	public double validationSep() throws InterruptedException {
		totalSumSep = sumLocationValue(driver, 3, "September");
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

	/**
	 * Performs validation for the month of October.
	 * 
	 * @return The absolute difference between expected and actual values.
	 * @throws InterruptedException if an interrupt occurs while summing the values.
	 */
	public double validationOctober() throws InterruptedException {
		totalSumOct = sumLocationValue(driver, 4, "October");
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

	/**
	 * Performs validation for the month of November.
	 * 
	 * @return The absolute difference between expected and actual values.
	 * @throws InterruptedException if an interrupt occurs while summing the values.
	 */
	public double validationNovember() throws InterruptedException {
		totalSumNov = sumLocationValue(driver, 5, "November");
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

	/**
	 * Performs validation for the month of December.
	 * 
	 * @return The absolute difference between expected and actual values.
	 * @throws InterruptedException if an interrupt occurs while summing the values.
	 */
	public double validationDecember() throws InterruptedException {
		totalSumDec = sumLocationValue(driver, 6, "December");
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

	/**
	 * Performs validation for the month of January.
	 * 
	 * @return The absolute difference between expected and actual values.
	 * @throws InterruptedException if an interrupt occurs while summing the values.
	 */
	public double validationJanuary() throws InterruptedException {
		totalSumJan = sumLocationValue(driver, 7, "January");
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

	/**
	 * Performs validation for the month of February.
	 * 
	 * @return The absolute difference between expected and actual values.
	 * @throws InterruptedException if an interrupt occurs while summing the values.
	 */
	public double validationFebruary() throws InterruptedException {
		totalSumFeb = sumLocationValue(driver, 8, "February");
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
