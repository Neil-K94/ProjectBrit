
@tag
Feature: API Testing with Cucumber and Rest Assured

  @Funtional
  Scenario Outline: Create a new object via POST request
		Given I set up the API endpoint "/objects"
    When I send a POST request with the following details:
     |	name            | year | price  	| CPU model     | Hard disk size |
     | MacBook Pro 16  	| 2023 | 2499.99 	| Intel Core i9 | 1 TB           |
    Then the response status should be 200
    And the response should contain the field "id"
    
    @Funtional
    Scenario: Update an existing object using PATCH
    Given I have an existing object with ID
    When I send a PATCH request to update "name" to "Updated MacBook Pro 16"
    When I send a GET request for that object
    Then the response status should be 200
    And the response should contain "Updated MacBook Pro 16"
    
    @Funtional
    Scenario: Get the object details using GET
    Given I have an existing object with ID
    When I send a GET request for that object
    Then the response status should be 200
    And the response should contain the updated name
    
    @Funtional
    Scenario: Delete the object using DELETE
    Given I have an existing object with ID
    When I send a DELETE request for that object
    Then the response status should be 200
    And the object should no longer exist

