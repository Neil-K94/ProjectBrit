package cucumber.StepDefinition;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.testng.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import selenium.PageObjects.*;
import selenium.TestComponent.BaseTest;

public class BritInsuranceTestCaseUI extends BaseTest{

	
	public BritInsurance britLandingPg;
	public List<String> listActualSearchElements;
	
	@Given("I Landed on the Brit Insurance Landing page")
	public void I_landed_on_Brit_insurance_home_page() throws IOException, InterruptedException
	{
		britLandingPg = launchApplication();
		britLandingPg.gotoLandingPage();
	}
	
	@When("I Search for {string}")
	public void I_search_for_IFRS(String strSearchKey)
	{
		listActualSearchElements = britLandingPg.getSearchElements(strSearchKey);
	}
	
	@Then("Following details should be listed as the search result")
	public void Search_result_should_match_the_expected_search_list(DataTable dataTable)
	{
		List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        Map<String, String> requestData = data.get(0);
        
		List<String> listExpectedSearchElementTexts = Arrays.asList(
				requestData.get("SearchResult1"), 
				requestData.get("SearchResult2"), 
				requestData.get("SearchResult3"),
				requestData.get("SearchResult4"),
				requestData.get("SearchResult5"));
		Assert.assertEquals(listActualSearchElements, listExpectedSearchElementTexts);
		driver.close();
	}
	
}
