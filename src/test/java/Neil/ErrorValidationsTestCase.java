package Neil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import Neil.TestComponent.BaseTest;
import Neil.pageObjects.landingPage;
import Neil.pageObjects.myCartPage;
import Neil.pageObjects.productCataloge;

public class ErrorValidationsTestCase extends BaseTest{
	
	@Test(dataProvider = "getData", retryAnalyzer = Neil.TestComponent.Retry.class)
	public void loginErrorValidation(HashMap<String, String> map) {
		
		//This method checks if the wrong password cannot be logged in
		landpg.gotoLandingPage();
		landpg.loginToApplication(map.get("InvalidEmail"),map.get("Password"));
		Assert.assertEquals(landpg.getErrorText(), "Incorrect email or password.");
	}
	
	@Test(dataProvider = "getData")
	public void productErrorValidation(HashMap<String, String> map)
	{
		String[] arrNotExpectingProducts = {"InvalidProductA" , "InvalidProductB"};
		landpg.gotoLandingPage();
		productCataloge objProductCat = landpg.loginToApplication(map.get("Email"), map.get("Password"));
		/*landpg is the object that comes from the base class since this class is extended by the base class
		landpg is the global variable of Base class we can use it here as well */
		
		List<WebElement> products = objProductCat.getProductLists();
		objProductCat.get2Products(map.get("ProductA"), map.get("ProductB"));
		
		myCartPage objMyCart = objProductCat.clickTwoProducts();
		Assert.assertFalse(objMyCart.verifyMyCartProducts(arrNotExpectingProducts));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") 
				+ "\\src\\test\\java\\Neil\\data\\ErrorValidationData.json");
		return new Object[][] {{data.get(0)}};
	}
}
