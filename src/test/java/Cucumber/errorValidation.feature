
@tag
Feature: Error validation verifications
  I want to use this template for my feature file


  @tag2
  Scenario Outline: Title of your scenario outline
		Given I Landed on the Ecommerce page
    When  I Logged in with username <username> and password <password>
    Then "Incorrect email or password." message is displayed on login page	

    Examples: 
      | username  						| password |
      | neilfs13122@gmail.com |  123456  |