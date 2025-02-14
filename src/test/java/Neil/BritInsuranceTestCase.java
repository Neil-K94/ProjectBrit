package Neil;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Neil.TestComponent.BaseTest;


public class BritInsuranceTestCase extends BaseTest{
	
	@Test(dataProvider="getData")
	public void britInsurance(HashMap<String, String> map) throws IOException, InterruptedException
	{
	
		britLandingPage.gotoLandingPage();
		List<String> actualSearchElementTexts = britLandingPage.getSearchElements(map.get("searchKeyWord"));
		List<String> expectedSearchElementTexts = Arrays.asList(map.get("searchResult1"), map.get("searchResult2"),
				map.get("searchResult3"), map.get("searchResult4"), map.get("searchResult5"));
		Assert.assertEquals(actualSearchElementTexts, expectedSearchElementTexts);
	
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String, String>> data =  getJsonDataToMap(System.getProperty("user.dir") 
				+ "\\src\\test\\java\\Neil\\data\\BritInsuranceLandingPageData.json");
		return new Object[][] {{data.get(0)}};
	}

}
