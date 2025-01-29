package Neil.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Neil.AbstractComponents.abstractComponent;

public class landingPage extends abstractComponent{
	
	WebDriver driver;
	
	public landingPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	//WebElement userEmail1 = driver.findElement(By.id("userEmail"));
	//WebElements are initialized in private to provide encapsulation
	@FindBy(id="userEmail")
	private WebElement userEmail;
	
	@FindBy(css="input[id='userPassword']")
	private WebElement password;
	
	@FindBy(css="input#login")
	private WebElement Login;
	
	@FindBy(css=".toast-message")
	private WebElement errorToastMessage;
	
	public void gotoLandingPage()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public productCataloge loginToApplication(String email, String passWord)
	{
		userEmail.sendKeys(email);
		password.sendKeys(passWord);
		Login.click();
		return new productCataloge(driver);
	}
	public String getErrorText()
	{
		waitForElementToAppear(errorToastMessage);
		return errorToastMessage.getText();
	}
	
}
