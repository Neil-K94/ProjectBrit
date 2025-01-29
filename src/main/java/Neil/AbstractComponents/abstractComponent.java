package Neil.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Neil.pageObjects.orderPage;

public class abstractComponent {
	
	WebDriver driver;
	
	public abstractComponent(WebDriver driver)
	{
		this.driver = driver;
	}
	
	@FindBy(css="li button[routerlink='/dashboard/cart']")
	WebElement cartHeaderButton;
	@FindBy(css="li button[routerlink='/dashboard/myorders']")
	WebElement ordersHeaderButton;
	@FindBy(css="li button[routerlink='/dashboard/']")
	WebElement homeHeaderButton;
	
	public void waitForElementToAppear(By findBy)
	{
		//This method defines the explicit wait until the element is appeared
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForElementToAppear(WebElement findBy)
	{
		//This method defines the explicit wait until the element is appeared
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	public void waitForElementToDisappear(By finBy)
	{
		//This method defines the explicit wait until the element is disappeared
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(finBy));
	}
	public void gotoCartPage()
	{
		cartHeaderButton.click();
	}
	public orderPage gotoOrdersPage()
	{
		ordersHeaderButton.click();
		return new orderPage(driver);
	}
}
