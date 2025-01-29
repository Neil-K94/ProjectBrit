
# Automating an Ecommerce Website

Following features were used to develop the Automation Framework


## Features

- Maven
Project was build using Maven Structured Framework with all necessary Automation dependencies

- Page object Model
Page object Model mechanism was implemented to drive the locators from respective classes

- Encapsulating Driver
 Drive object creation within Page object classes encapsulating it from Tests

 - Global properties
 Created Base Test which sets browser configuration details and Global properties

- Test Strategy
 Tests can be clubbed & distributed with appropriate annotations

 -  TestNG runner
 Created TestNG runner file to trigger the tests with one Single point of execution control

 - Grouping in TestNG.xml
 Introduced Grouping in TestNG.xml to categorize tests with different tags of execution

 - Data driven testing & Parameterization
  Implemented Data driven testing & Parameterization using TestNG Data provider HashMap & Json File readers

- TestNG Listeners
Implemented TestNG Listeners to capture Screenshot on automatic test failures and logging

- Extent Report wrapper
Create Extent Report wrapper to generate excellent HTML reports for the application

- Parallel execution
 Made Framework necessary changes to support parallel execution with Thread safe mechanism

- TestNG retry mechanism
Implemented TestNG retry mechanism to rerun the failed flaky tests in the application

- TestNG Maven integration plugin
Ran the Framework tests with Maven commands with TestNG Maven integration plugin

- Run time variables
Implemented Maven Run time variables to replace global parameters of test data at runtime

- Jenkins
Integrateed the Framework with Jenkins with parameterized Build pipeline jobs & schedule the jobs on specific time frames.

- Cucumber wrapper
Added Cucumber wrapper to existing framework with cucumber TestNg runner
- Feature file
Created Feature file & step definitions to support cucumber execution of selenium tests








