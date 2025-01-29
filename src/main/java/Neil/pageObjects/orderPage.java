package Neil.pageObjects;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Neil.AbstractComponents.abstractComponent;

public class orderPage extends abstractComponent{
	
	WebDriver driver;
	
	public orderPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".ng-star-inserted tr td:nth-of-type(2)")
	private List<WebElement> orderNameTexts;
	
	public boolean verifyOrderHistory(String strProductA, String strProductB)
	{		Set<String> expectedHash = new HashSet<String>();
		 	expectedHash.add(strProductA.toLowerCase());
		 	expectedHash.add(strProductB.toLowerCase());
	
		 for(WebElement orderText : orderNameTexts)
		 {
			 expectedHash.remove(orderText.getText().trim().toLowerCase());
		 }
		 if(expectedHash.isEmpty())
		 {
			 return true;
		 }
		 return false;
	}

}
