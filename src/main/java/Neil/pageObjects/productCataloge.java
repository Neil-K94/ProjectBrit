package Neil.pageObjects;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import Neil.AbstractComponents.abstractComponent;

public class productCataloge extends abstractComponent {
	
	WebDriver driver;
	List<WebElement> twoProducts;
	Actions actions;
	public productCataloge(WebDriver driver) {
	
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		actions = new Actions(driver);
	}
	
	@FindBy(css="div.col-lg-4")		//This consist of all the product webelements
	private List<WebElement> products;
	
	private By allProductsList = By.cssSelector("div.col-lg-4");		
	/*This is parameter will be passed to the waitForElementToAppear method which comes from abstractComponents
	Since it only accepts by Locator this parameter is created */
	private By addToCartButton = By.cssSelector(".card-body button:last-of-type");
	private By cartAddedAlertMessage = By.cssSelector("div[role='alert']");
	private By spinnerAfterCartAdd = By.cssSelector(".ngx-spinner-overlay .la-3x");
	
	public List<WebElement> getProductLists()
	{
		//This method will get all the products listed in the page
		waitForElementToAppear(allProductsList);
		return products;
	}
	
	public List<WebElement> get2Products(String product1, String product2)
	{
		twoProducts =  getProductLists().stream().filter(TwoProduct -> {
			String productName = TwoProduct.findElement(By.xpath(".//h5/b")).getText();		//b tag consist of the texts adidas and iphone
			return productName.equals(product1) || productName.equals(product2);
		}).collect(Collectors.toList());
		return twoProducts;
	}
	public myCartPage clickTwoProducts()
	{
		//This method will click on the add to cart button of the 2 expected products 
		for(WebElement pro : twoProducts)
		{
			pro.findElement(addToCartButton).click();
			waitForElementToAppear(cartAddedAlertMessage);
			waitForElementToDisappear(spinnerAfterCartAdd);
		}
		gotoCartPage();
		return new myCartPage(driver);
	}
	
}
