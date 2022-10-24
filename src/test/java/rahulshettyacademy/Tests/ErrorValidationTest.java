package rahulshettyacademy.Tests;


import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidationTest extends BaseTest{
	
	
	@Test(groups={"ErrorHandling"})
	public void submitOrder() throws Exception
	{
		//String productName="ZARA COAT 3";
		
		
		ProductCatalogue productcatalouge=landingPage.loginApplication("ambreshwer95@gmail.com","Pasword@1");
		Assert.assertEquals("Incorrect email  password.", landingPage.getErrorMessage());
	}
	
	
	
	@Test
	public void productErrorValidation() throws Exception
	{
		
		String productName="ZARA COAT 3";
		ProductCatalogue productcatalouge=landingPage.loginApplication("anshika@gmail.com","Iamking@000");
		
		List<WebElement>products =productcatalouge.getProductList();
		productcatalouge.addProductToCart(productName);
		
		CartPage cartpage=productcatalouge.goToCartPage();
			
		Boolean match=cartpage.verifyCartProduct("ZARA COAT 33");
		Assert.assertFalse(match);
	}

}
