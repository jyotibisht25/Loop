package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import AbstractComponenets.AbstractComponent;

/**
 * This class represents the Page Object for the Login page in a web application.
 * It contains methods to interact with various elements on the login page.
 */
public class LoginPageObject extends AbstractComponent {

    WebDriver driver;

    @FindBy(linkText = "sign-in with password instead")
    WebElement signInLink;

    @FindBy(xpath = "//input[@class='MuiInputBase-input MuiOutlinedInput-input css-1x5jdmq']")
    WebElement userName;

    @FindBy(xpath = "//input[@type='password']")
    WebElement userPassword;

    @FindBy(xpath = "//button[@data-testid='login-button']")
    WebElement loginButton;

    /**
     * Constructs a new LoginPageObject with the specified WebDriver.
     * @param driver The WebDriver instance to use.
     */
    public LoginPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Navigates to the login page URL.
     */
    public void goToURL() {
        driver.get("https://app.tryloop.ai/");
    }

    /**
     * Clicks the 'sign-in with password instead' link to switch to password login.
     */
    public void loginTryLoop() {
        waitForElementToAppear(signInLink);
        signInLink.click();
    }

    /**
     * Enters the user's email into the email input field.
     * @param email The user's email address.
     */
    public void enterEmail(String email) {
        waitForElementToAppear(userName);
        userName.sendKeys(email);
    }

    /**
     * Enters the user's password into the password input field.
     * @param password The user's password.
     */
    public void enterPassword(String password) {
        waitForElementToAppear(userPassword);
        userPassword.sendKeys(password);
    }

    /**
     * Clicks the login button and navigates to the HistoryByStorePageObject page.
     * @return A HistoryByStorePageObject representing the History by Store page.
     */
    public HistoryByStorePageObject loginButton() {
        loginButton.click();
        return new HistoryByStorePageObject(driver);
    }

}
