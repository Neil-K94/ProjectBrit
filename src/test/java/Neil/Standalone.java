package Neil;

import java.time.Duration;
import java.util.Arrays;
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

import Neil.pageObjects.landingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Standalone {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String[] ExpectedProducts = {"ADIDAS ORIGINAL" , "IPHONE 13 PRO"};
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		
		landingPage lp = new landingPage(driver);
		
		driver.findElement(By.id("userEmail")).sendKeys("neilfs13@gmail.com");
		driver.findElement(By.cssSelector("input[id='userPassword']")).sendKeys("TestSelenium123");
		driver.findElement(By.cssSelector("input#login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.col-lg-4")));
		
		List<WebElement> products = driver.findElements(By.cssSelector("div.col-lg-4"));
		//The following code will iterate throughout the products Webelements using stream function
		
		/*
		WebElement product = products.stream().filter(findExpectedProd -> 
		findExpectedProd.findElement(By.xpath(".//h5/b")).getText()		
		.equals("ADIDAS ORIGINAL")).findFirst().orElse(null);
		*/
		
		/* adding ".//" before xpath will only search inside the products list webelement,
		 * if the xpath starts with // , it will search the whole document */
		
		
		/* suppose we want to click 2 specific elements, we can use the following commands to store 2 elements
		 * in a list and then iterate through the list using foreach to click the expected items
		 */
		
		List<WebElement> productsToSelect = products.stream().filter(TwoProduct -> {
			String productName = TwoProduct.findElement(By.xpath(".//h5/b")).getText();		//b tag consist of the texts adidas and iphone
			return productName.equals(ExpectedProducts[0]) || productName.equals(ExpectedProducts[1]);
		}).collect(Collectors.toList());
		
		
		for(WebElement pro : productsToSelect)
		{
			pro.findElement(By.cssSelector(".card-body button:last-of-type")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[role='alert']")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ngx-spinner-overlay .la-3x")));			
		}
		
		//product.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[role='alert']")));
		//This command will wait till the alert box in the bottom right is displayed 
		//once the add to card button is clicked
		
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ngx-spinner-overlay .la-3x")));
		/* once add to cart button is clicked there will be a spinner, this code is to wait until the 
		 * spinner is disappeared
		 */
		driver.findElement(By.cssSelector("li button[routerlink='/dashboard/cart']")).click();
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		
		Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(ExpectedProducts[0]));
		
		Boolean allProductMatch = Arrays.stream(ExpectedProducts).anyMatch(expectedProduct -> cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(expectedProduct)));
		/*This code iterates through ExpectedProduct array and the list cartproducts to match the webelements text 
		which is in the cart webpage, if both the elements match then true will be passed to the boolean variable */
		
		Assert.assertTrue(allProductMatch);
		Actions a = new Actions(driver);
		
		a.moveToElement(driver.findElement(By.cssSelector(".totalRow button"))).click().build().perform();	//checkout button
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Select Country']")));
		//wait until select country element is displayed
		a.moveToElement(driver.findElement(By.cssSelector("input[placeholder='Select Country']"))).click().sendKeys("India").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results:first-of-type"))); //waits for the suggetion box to appears once india is send in the text field
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click(); //Selecting india from the suggestion box
		driver.findElement(By.cssSelector(".action__submit")).click();	//Selecting place order button
		
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		driver.close();
}
	}
