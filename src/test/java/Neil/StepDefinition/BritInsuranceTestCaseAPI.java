package Neil.StepDefinition;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;


public class BritInsuranceTestCaseAPI {
	private static String BASE_URL = "https://api.restful-api.dev";
    private static String objectId;
    private Response response;
    
    //First Scenario
    @Given("I set up the API endpoint {string}")
    public void i_set_up_the_api_endpoint(String endpoint) {
        RestAssured.baseURI = BASE_URL + endpoint;
    }
    
    @When("I send a POST request with the following details:")
    public void i_send_a_post_request_with_the_following_details(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        Map<String, String> requestData = data.get(0);
        
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", requestData.get("name"));
        
        JSONObject dataObject = new JSONObject();
        dataObject.put("year", Integer.parseInt(requestData.get("year")));
        dataObject.put("price", Double.parseDouble(requestData.get("price")));
        dataObject.put("CPU model", requestData.get("CPU model"));
        dataObject.put("Hard disk size", requestData.get("Hard disk size"));
        
        requestBody.put("data", dataObject);
        
        response = given()
                .contentType(ContentType.JSON)
                .body(requestBody.toJSONString())
                .when()
                .post();
        
        objectId = response.jsonPath().getString("id");  // Store object ID for future tests
        }

    @Then("the response status should be {int}")
    public void the_response_status_should_be(Integer expectedStatus) {
        Assert.assertEquals(response.getStatusCode(), expectedStatus.intValue());
    }

    @Then("the response should contain the field {string}")
    public void the_response_should_contain_the_field(String fieldName) {
        Assert.assertNotNull(response.jsonPath().getString(fieldName));
    }
    
    //Second Scenario
    @Given("I have an existing object with ID")
    public void i_have_an_existing_object_with_id() {
        Assert.assertNotNull(objectId, "No object ID found. Ensure POST request was successful.");
    }

    @When("I send a PATCH request to update {string} to {string}")
    public void i_send_a_patch_request_to_update_to(String field, String newValue) {
        JSONObject requestBody = new JSONObject();
        requestBody.put(field, newValue);

        response = given()
                .contentType(ContentType.JSON)
                .body(requestBody.toJSONString())
                .when()
                .patch(BASE_URL + "/objects/" + objectId);
    }

    @When("I send a GET request for that object")
    public void i_send_a_get_request_for_that_object() {
        response = given()
                .when()
                .get(BASE_URL + "/objects/" + objectId);
    }

    @Then("the response should contain {string}")
    public void the_response_should_contain(String expectedValue) {
        Assert.assertTrue(response.asString().contains(expectedValue), "Response does not contain expected value.");
    }
    
    //Third Scenario
    @Then("the response should contain the updated name")
    public void the_response_should_contain_the_updated_name() {
        String responseName = response.jsonPath().getString("name");
        Assert.assertEquals(responseName, "Updated MacBook Pro 16", "Object name does not match expected value.");
    }

    //Forth Scenario
    @When("I send a DELETE request for that object")
    public void i_send_a_delete_request_for_that_object() {
        response = given()
                .when()
                .delete(BASE_URL + "/objects/" + objectId);
    }

    @Then("the object should no longer exist")
    public void the_object_should_no_longer_exist() {
        Response getResponse = given()
                .when()
                .get(BASE_URL + "/objects/" + objectId);
        
        Assert.assertEquals(getResponse.getStatusCode(), 404, "Object was not deleted.");
    }
    
}
