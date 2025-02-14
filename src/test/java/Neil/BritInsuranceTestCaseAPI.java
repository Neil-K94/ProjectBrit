package Neil;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class BritInsuranceTestCaseAPI {
	
	private static String strObjectId; 

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://api.restful-api.dev";
        strObjectId = createObjectAndGetId();
    }

    @Test
    public String createObjectAndGetId() {
        JSONObject objRequestBody = new JSONObject();
        objRequestBody.put("name", "Apple MacBook Pro 16");
        JSONObject data = new JSONObject();
        data.put("year", 2019);
        data.put("price", 1849.99);
        data.put("CPU model", "Intel Core i9");
        data.put("Hard disk size", "1 TB");
        objRequestBody.put("data", data);

        Response objResponse = given()
            .contentType(ContentType.JSON)
            .body(objRequestBody.toJSONString())
            .when()
            .post("/objects")
            .then()
            .statusCode(200)
            .extract().response();

        return objResponse.jsonPath().getString("id");
    }
    
    //Postitive Test updating the name
    @Test(priority = 1)
    public void testUpdateSingleField() {
        JSONObject objRequestBody = new JSONObject();
        objRequestBody.put("name", "Updated MacBook Pro");

        Response objResponse = given()
            .contentType(ContentType.JSON)
            .body(objRequestBody.toJSONString())
            .when()
            .patch("/objects/" + strObjectId)
            .then()
            .statusCode(200)
            .extract().response();

        Assert.assertEquals(objResponse.jsonPath().getString("name"), "Updated MacBook Pro");
    }

    // Positive Test Updating multiple fields
    @Test(priority = 2)
    public void testUpdateMultipleFields() {
        JSONObject objRequestBody = new JSONObject();
        objRequestBody.put("name", "Updated MacBook Pro 16");
        JSONObject updatedData = new JSONObject();
        updatedData.put("price", 1999.99);
        objRequestBody.put("data", updatedData);

        Response objResponse = given()
            .contentType(ContentType.JSON)
            .body(objRequestBody.toJSONString())
            .when()
            .patch("/objects/" + strObjectId)
            .then()
            .statusCode(200)
            .extract().response();

        Assert.assertEquals(objResponse.jsonPath().getString("name"), "Updated MacBook Pro 16");
        Assert.assertEquals(objResponse.jsonPath().getFloat("data.price"), 1999.99f);
    }

    // Negative Test using invalid id to update
    @Test(priority = 3)
    public void testUpdateWithInvalidId() {
        JSONObject objRequestBody = new JSONObject();
        objRequestBody.put("name", "Invalid Update");

        	given()
            .contentType(ContentType.JSON)
            .body(objRequestBody.toJSONString())
            .when()
            .patch("/objects/99999") 
            .then()
            .statusCode(404);
    }

    // Negative testing sending an empty request body
    @Test(priority = 4)
    public void testEmptyRequestBody() {
        	given()
            .contentType(ContentType.JSON)
            .body("{}")
            .when()
            .patch("/objects/" + strObjectId)
            .then()
            .statusCode(400);
    }

    //Negative Test Sending number insted of string
    @Test(priority = 5)
    public void testUpdateWithIncorrectDataType() {
        JSONObject objRequestBody = new JSONObject();
        objRequestBody.put("name", 1234);  // Incorrect type (should be string) test case should fail

        	given()
            .contentType(ContentType.JSON)
            .body(objRequestBody.toJSONString())
            .when()
            .patch("/objects/" + strObjectId)
            .then()
            .statusCode(400);
    }

    // Negative Test case Trying to update with invalid id
    @Test(priority = 6)
    public void testUpdateRestrictedField() {
        JSONObject objRequestBody = new JSONObject();
        objRequestBody.put("id", "99999");

        	given()
            .contentType(ContentType.JSON)
            .body(objRequestBody.toJSONString())
            .when()
            .patch("/objects/" + strObjectId)
            .then()
            .statusCode(404);
    }

}
