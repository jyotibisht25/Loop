package pageobjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AbstractComponenets.AbstractComponent;

public class LoginPageObject extends AbstractComponent{
	
	WebDriver driver;
	
	public LoginPageObject(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(linkText="sign-in with password instead")
	WebElement signinLink;
	
	@FindBy(xpath="//input[@class='MuiInputBase-input MuiOutlinedInput-input css-1x5jdmq']")
	WebElement userName;
	
	
	@FindBy(xpath="//input[@type='password']")
	WebElement userPassword;
	

	@FindBy(xpath="//button[@data-testid='login-button']")
	WebElement loginButton;
	 
	
	public void goToURL()
	{
	 driver.get("https://app.tryloop.ai/");
	}
	
	public void loginTryLoop()
	{
		
	// wait till the visibility of page
	waitForElementToApper(signinLink);
	//link on sign in link
	signinLink.click();
		
	}
	
	public void enterEmail(String email) 
	{
		
	// wait till the visibility of email textbox
	waitForElementToApper(userName);
	userName.sendKeys(email);
		
	}
	
	public void enterPassword(String password)
	{
		
	// wait till the visibility of email textbox
	waitForElementToApper(userPassword);
	userPassword.sendKeys(password);
		
	}
	
	public HistoryByStorePageObject loginButton()
	{
		loginButton.click();
		return new HistoryByStorePageObject(driver);
	}
	
	

}
