@tag
Feature: Purchase the product from ecommerce website
  I want to use this template for my feature file
  
  Background:
	Given I Landed on the Ecommerce page

  @Regression
  Scenario Outline: Positive Test for Submitting the Order
    Given I Logged in with username <username> and password <password>
    When I add productA <ProductA> and productB <ProductB> to the cart
    And  Checkout <ProductA> and <ProductB> and submit the order
    Then "Thankyou for the order." message is displayed on confirmation page	

    Examples: 
      | username 						| password 				| ProductA			| ProductB 			|
      | neilfs13@gmail.com	| TestSelenium123	|	IPHONE 13 PRO	| BANARSI SAREE |
      

