package AbstractComponenets;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {
	
	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		
		this.driver = driver;
	}
	
	public void waitForElementToAppear(WebElement we)
	{
		WebDriverWait wait = new WebDriverWait(driver,40);
		wait.until(ExpectedConditions.visibilityOf(we));

	}

}
