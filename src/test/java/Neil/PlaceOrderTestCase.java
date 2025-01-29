package Neil;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Neil.AbstractComponents.abstractComponent;
import Neil.TestComponent.BaseTest;
import Neil.pageObjects.landingPage;
import Neil.pageObjects.myCartPage;
import Neil.pageObjects.orderPage;
import Neil.pageObjects.productCataloge;
import io.github.bonigarcia.wdm.WebDriverManager;

public class PlaceOrderTestCase extends BaseTest{

		@Test(dataProvider="getData")
		public void placeOrder(HashMap<String, String> map) throws IOException
		{
		
		landpg.gotoLandingPage();
		productCataloge objProductCat = landpg.loginToApplication(map.get("Email"), map.get("Password"));
		
		/*landpg is the object that comes from the base class since this class is extended by the base class
		landpg is the global variable of Base class we can use it here as well */
		
		List<WebElement> products = objProductCat.getProductLists();
		objProductCat.get2Products(map.get("ProductA"), map.get("ProductB"));
		
		myCartPage objMyCart = objProductCat.clickTwoProducts();
		Assert.assertTrue(objMyCart.verifyMyCartProducts(map.get("ProductA"), map.get("ProductB")));
		objMyCart.cardPaymentPage("India");
		Assert.assertTrue(objMyCart.orderConfirmed().equalsIgnoreCase("Thankyou for the order."));
		}
		
		@Test(dependsOnMethods = {"placeOrder"} , dataProvider = "getData")
		public void orderHistory(HashMap<String, String> map)
		{
			landpg.gotoLandingPage();
			productCataloge objProductCat = landpg.loginToApplication(map.get("Email"), map.get("Password"));
			orderPage objOrderPage = objProductCat.gotoOrdersPage();
			Assert.assertTrue(objOrderPage.verifyOrderHistory(map.get("ProductA"), map.get("ProductB")));
		}
		@DataProvider
		public Object[][] getData() throws IOException
		{
			List<HashMap<String, String>> data =  getJsonDataToMap(System.getProperty("user.dir") 
					+ "\\src\\test\\java\\Neil\\data\\purchaseOrderData.json");
			return new Object[][] {{data.get(0)}};
			
			
			/*
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("Email", "neilfs13@gmail.com");
			map.put("Password", "TestSelenium123");
			map.put("ProductA", "IPHONE 13 PRO");
			map.put("ProductB", "BANARSI SAREE");
			
			return new Object[][] {{map}};
			*/
			
		}
	}

