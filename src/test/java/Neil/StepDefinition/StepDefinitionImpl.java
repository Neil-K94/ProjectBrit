package Neil.StepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Neil.TestComponent.BaseTest;
import Neil.pageObjects.landingPage;
import Neil.pageObjects.myCartPage;
import Neil.pageObjects.productCataloge;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest{

	public landingPage landingpg ;
	public productCataloge objProductCat;
	public myCartPage objMyCart;
	
	@Given("I Landed on the Ecommerce page")
	public void I_Landed_on_the_Ecommerce_page() throws IOException
	{
		landingpg = launchApplication();
		landingpg.gotoLandingPage();
	}
	
	@Given("^I Logged in with username (.+) and password (.+)$")
	public void I_Logged_in_with_username_password(String username, String password)
	{
		objProductCat = landingpg.loginToApplication(username, password);	
	}
	@When("^I add productA (.+) and productB (.+) to the cart$")
	public void I_add_productA_and_productB_to_the_cart(String productA, String productB)
	{
		List<WebElement> products = objProductCat.getProductLists();
		objProductCat.get2Products(productA, productB);
		objMyCart = objProductCat.clickTwoProducts();
	}
	@And("^Checkout (.+) and (.+) and submit the order$")
	public void checkout_products_and_submit_order(String productA, String productB)
	{
		Assert.assertTrue(objMyCart.verifyMyCartProducts(productA, productB));
		objMyCart.cardPaymentPage("India");
	}
	
	@Then("{string} message is displayed on confirmation page")
	public void message_displayed_in_confirmation_page(String message)
	{
		Assert.assertTrue(objMyCart.orderConfirmed().equalsIgnoreCase(message));
		driver.close();
	}
	@Then("{string} message is displayed on login page")
	public void Error_message_displayed_on_login_page(String message)
	{
		Assert.assertEquals(landingpg.getErrorText(), message);
	}
}
