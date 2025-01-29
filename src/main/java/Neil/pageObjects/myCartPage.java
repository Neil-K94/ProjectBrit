package Neil.pageObjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Neil.AbstractComponents.abstractComponent;

public class myCartPage extends abstractComponent{
	
	WebDriver driver;
	Actions actions;
	
	public myCartPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		actions = new Actions(driver);
	}
	
	@FindBy(css=".cartSection h3")
	private List<WebElement> myCartProducts;
	@FindBy(css=".totalRow button")
	private WebElement checkOutButton;
	@FindBy(css=".ta-item:nth-of-type(2)")
	private WebElement indiaFromSuggestionBox;
	@FindBy(css=".action__submit")
	private WebElement placeOrderButton;
	@FindBy(css=".hero-primary")
	private WebElement orderConfirmedHeading;
	
	By selectCountryButton = By.cssSelector("input[placeholder='Select Country']");
	By suggestionBox = By.cssSelector(".ta-results:first-of-type");

	public boolean verifyMyCartProducts(String[] arrExpectedProducts)
	{
		ArrayList<String> list = new ArrayList<String>();
		//This method gets the items displayed in the my cart and verifies if the expected items are added to the cart
		 List<WebElement> myCartProduct = myCartProducts;
		 
		 return Arrays.stream(arrExpectedProducts).anyMatch(expectedProduct -> myCartProduct.stream()
					.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(expectedProduct)));
	}
	public boolean verifyMyCartProducts(String strProductA, String strProductB)
	{
		Set<String> setExpectedProducts = new HashSet<String>();
		setExpectedProducts.add(strProductA.toLowerCase());
		setExpectedProducts.add(strProductB.toLowerCase());
		
		for(WebElement cartElement : myCartProducts)
		{
			String cartElementText = cartElement.getText();
			if(cartElementText !=null && setExpectedProducts.remove(cartElementText.trim().toLowerCase()))
			{
				if(setExpectedProducts.isEmpty())
				{
					return true;
				}
			}
		}
		return false;
	}

	public void cardPaymentPage(String countryName)
	{
		//This method does all the operation needs to be performed in the card payment page
		actions.moveToElement(checkOutButton).click().build().perform();
		waitForElementToAppear(selectCountryButton);
		actions.moveToElement(driver.findElement(selectCountryButton)).click().sendKeys(countryName).build().perform();
		waitForElementToAppear(suggestionBox);
		indiaFromSuggestionBox.click();
		placeOrderButton.click();
	}
	
	public String orderConfirmed()
	{
		return orderConfirmedHeading.getText();
	}
}
