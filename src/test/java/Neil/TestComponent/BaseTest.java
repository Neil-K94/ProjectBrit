package Neil.TestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Neil.pageObjects.BritInsurance;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
		public WebDriver driver;
		protected BritInsurance britLandingPage;
		
		public WebDriver initialiseDriver() throws IOException
		{
			//properties class
			Properties prop = new Properties();
			FileInputStream fileInput = new FileInputStream(System.getProperty("user.dir")
					+ "\\src\\main\\java\\Neil\\Resources\\GlobalProperties.properties");
			prop.load(fileInput);
			
			String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
			//String browserName = prop.getProperty("browser");
			
			if(browserName.contains("chrome"))
			{
				ChromeOptions options = new ChromeOptions();
				WebDriverManager.chromedriver().setup();
				if(browserName.contains("headless"))
				{					
					options.addArguments("headless");
				}
				driver = new ChromeDriver(options);
				driver.manage().window().setSize(new Dimension(1440, 900));	//this command lets the chrome run in full screen mode even thougn in the headless 
			}
			else if(browserName.equals("Firefox"))
			{
				driver = new FirefoxDriver();
			}
			else if(browserName.equals("Edge"))
			{
				driver = new EdgeDriver();
			}
			driver.manage().window().maximize();				
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			return driver;
		}
		
		@BeforeMethod(alwaysRun = true)
		public BritInsurance launchApplication() throws IOException
		{
			driver = initialiseDriver();
			britLandingPage = new BritInsurance(driver);
			return britLandingPage;
		}

		@AfterMethod(alwaysRun =  true)
		public void teardown()
		{
			driver.close();
		}
		
		public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
		{
			//Reads jason file to String
			String jsoncontent = FileUtils.readFileToString(new File (filePath), StandardCharsets.UTF_8);
			//Converts the String to HashMap
			//Repository name is jackson databind
			ObjectMapper mapper = new ObjectMapper();
			List<HashMap<String, String>> data = mapper.readValue(jsoncontent, new TypeReference<List<HashMap<String, String>>>() {
			});
			return data;
		}
		
		public String getScreenShot(String testCaseName, WebDriver driver) throws IOException
		{
			TakesScreenshot ts = (TakesScreenshot)driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
			FileUtils.copyFile(source, file);
			return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		}

}
