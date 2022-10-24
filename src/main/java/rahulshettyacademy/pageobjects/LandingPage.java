package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//WebElement userEmails=driver.findElement(By.id("userEmail"));
	
	@FindBy(id="userEmail")
	WebElement userEmails;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement submit;
	
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	
	public ProductCatalogue loginApplication(String email,String password)
	{
		//super(driver);
		userEmails.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();	
		ProductCatalogue productcatalouge = new ProductCatalogue(driver);
		return productcatalouge;

	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	 public void goTo()
	 {
		 driver.get("https://rahulshettyacademy.com/client/");
	 }
	 
	

}
