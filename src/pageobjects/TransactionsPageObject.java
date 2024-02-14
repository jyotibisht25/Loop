package pageobjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import AbstractComponenets.AbstractComponent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents the Page Object for the Transactions page in a web application.
 * It contains methods to interact with various elements on the page and perform actions such as filtering and downloading CSV files.
 */
public class TransactionsPageObject extends AbstractComponent {

    WebDriver driver;

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

    /**
     * Constructs a new TransactionsPageObject with the specified WebDriver.
     * @param driver The WebDriver instance to use.
     */
    public TransactionsPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Clicks the '3P Chargebacks' button.
     */
    public void clickChargebacksButton() {
        waitForElementToAppear(chargebacksButton);
        chargebacksButton.click();
    }

    /**
     * Selects locations for filtering.
     */
    public void selectLocation() {
        waitForElementToAppear(selectLocation);
        selectLocation.click();
        waitForElementToAppear(clearButton);
        clearButton.click();
        waitForElementToAppear(selectAA);
        selectAA.click();
        selectBB.click();
        applyButton.click();
    }

    /**
     * Selects marketplaces for filtering.
     */
    public void selectMarketplaces() {
        waitForElementToAppear(selectMarketplaces);
        selectMarketplaces.click();
        waitForElementToAppear(clearButton);
        clearButton.click();
        selectGrubhub.click();
        applyButton.click();
    }

    /**
     * Initiates the download of a CSV file.
     */
    public void downloadCSVFile() {
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.PAGE_DOWN).build().perform();
        waitForElementToAppear(downloadButton);
        downloadButton.click();
    }

    /**
     * Validates the downloaded CSV file.
     * @return true if the CSV file is ordered; otherwise, false.
     * @throws FileNotFoundException if the CSV file is not found.
     * @throws IOException if an I/O error occurs while reading the CSV file.
     * @throws CsvValidationException if a validation error occurs while reading the CSV file.
     * @throws InterruptedException if the current thread is interrupted while waiting.
     */
    public boolean validateCSVFile() throws FileNotFoundException, IOException, CsvValidationException, InterruptedException {
        Thread.sleep(5000);
        int columnIndex = 0;
        int countRows = 0;
        List<String> columnData = new ArrayList<>();
        List<Long> columnDataInt = new ArrayList<>();
        String csvFile = "/Users/sachinbagla/Downloads/chargebacks_payouts_overview.csv";
        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                if (line.length > columnIndex) {
                    columnData.add(line[columnIndex]);
                    countRows++;
                }
            }
            columnDataInt = columnData.stream().skip(1).map(s -> Long.valueOf(s.substring(2))).collect(Collectors.toList());
        }
        for (int i = 0; i < columnDataInt.size() - 1; i++) {
            if (columnDataInt.get(i) > columnDataInt.get(i + 1)) {
                return false;
            }
        }
        return true;
    }
}
