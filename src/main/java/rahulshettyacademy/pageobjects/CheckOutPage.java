package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	
	WebDriver driver;

	public CheckOutPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath="//button[contains(@class,'ta-item')][2]")
	WebElement SelectCountry;
	
	@FindBy(css=".actions a")
	WebElement Submit;
	
	By results = By.cssSelector(".ta-results");
	
	
	public void selectCountry(String countryName)
	{
		Actions a = new Actions(driver);
		a.sendKeys(country,countryName).build().perform();
		waitForElementToAppear(results);
		SelectCountry.click();
		//driver.findElement(By.cssSelector(".actions a")).click();
	}
	
	
	public ConfirmationPage submitOrder()
	{
		WebElement e = driver.findElement(By.cssSelector(".btnn"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click(0);", e);
		//Submit.click();
		return new ConfirmationPage(driver);
	}


	
	
	
	
	

}
