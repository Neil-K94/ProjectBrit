package Neil.PageObjects;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Neil.AbstractComponents.AbstractComponent;


public class BritInsurance extends AbstractComponent{
	
	WebDriver driver;
	
	public BritInsurance(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="button[type='button']")
	WebElement SearchButton;
	
	@FindBy(xpath="//input[@type='search']")
	WebElement searchField;
	
	@FindBy(xpath = "//div[@class='header--search__results']/div/a")
	List<WebElement> searchElements;
	
	public void gotoLandingPage() throws InterruptedException
	{
		driver.get("https://www.britinsurance.com/");
		Thread.sleep(6000);
	}
	
	//Method performs clicking on the search button and passing the search Keyword
	public List<String> getSearchElements(String strSearchKey)
	{
		SearchButton.click();
		waitForElementToAppear(searchField);
		searchField.sendKeys(strSearchKey);
		return searchElements.stream()
        .map(e -> e.getText().trim())  // Get text and trim spaces
        .filter(text -> !text.isEmpty()) // Remove empty text values
        .collect(Collectors.toList());
		
	}

}
