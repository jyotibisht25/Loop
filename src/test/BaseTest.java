package test;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * This class serves as the base class for all test classes.
 * It initializes the WebDriver for Chrome browser with specific configurations.
 */
public class BaseTest {

	private static final String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";
	private static final String CHROMEDRIVER_PATH = "/Users/sachinbagla/Documents/chromedriver";
	
	// Instance of WebDriver for interacting with the browser.
	protected WebDriver driver;

	 /**
     * Constructor for BaseTest class.
     * Initializes WebDriver for Chrome with specified options.
     */
	public BaseTest() {
		System.setProperty(WEBDRIVER_CHROME_DRIVER, CHROMEDRIVER_PATH);
		ChromeOptions options = new ChromeOptions();
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		this.driver = driver;
	}
}
	
