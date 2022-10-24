package rahulshettyacademy.Tests;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest{
	
	String productName="ZARA COAT 3";
	
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws Exception
	{
		
		
		//LandingPage landingPage=launchApplication();
		ProductCatalogue productcatalouge=landingPage.loginApplication(input.get("email"),input.get("password"));
		
		List<WebElement>products =productcatalouge.getProductList();
		productcatalouge.addProductToCart(input.get("product"));
		
		CartPage cartpage=productcatalouge.goToCartPage();
			
		Boolean match=cartpage.verifyCartProduct(input.get("product"));
		Assert.assertTrue(match);
		CheckOutPage checkoutpage =cartpage.goToCheckOut();
		checkoutpage.selectCountry("india");
		ConfirmationPage confirmationPage =checkoutpage.submitOrder();
		
		String confirmMessage = confirmationPage.getConformationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}
		
			
		@Test(dependsOnMethods= {"submitOrder"}) 
		public void orderHistoryTest()
		{
			ProductCatalogue productcatalouge=landingPage.loginApplication("ambreshwer95@gmail.com","Password@1");
			OrderPage orderPage=productcatalouge.goToOredersPage();
			Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
		}
		
		
		
		@DataProvider
		public Object[][] getData() throws IOException
		{
			/*
			HashMap<String,String> map = new HashMap<String,String>();
			map.put("email", "ambreshwer95@gmail.com");
			map.put("password", "Password@1");
			map.put("product", "ZARA COAT 3");
			
			HashMap<String,String> map1 = new HashMap<String,String>();
			map1.put("email", "ambreshwer96@gmail.com");
			map1.put("password", "Password@1");
			map1.put("product", "ADIDAS ORIGINAL");
			*/
			
			List<HashMap<String,String>> data	=	getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
			
			return new Object[][]  {{data.get(0)},{data.get(1)}};
		}
		
		
		
		/*
		@DataProvider
		public Object[][] getData()
		{
			return new Object[][] {{"ambreshwer95@gmail.com","Password@1","ZARA COAT 3"},{"ambreshwer96@gmail.com","Password@1","ADIDAS ORIGINAL"}};
		}
		*/
		
		
		
		
		/*
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		
		driver.get("https://rahulshettyacademy.com/client/");
		driver.findElement(By.id("userEmail")).sendKeys("ambreshwer95@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Password@1");
		driver.findElement(By.id("login")).click();
	
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products =driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List <WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
		boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		ProductCatalogue productcatalouge = new ProductCatalogue(driver);
		
		CartPage cartpage=new CartPage(driver); 
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector(".actions a")).click();
		
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
		*/

	}


