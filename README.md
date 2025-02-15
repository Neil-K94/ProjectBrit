
# Automating Brit Insurance

API Automation Testing using Cucumber, Rest Assured

## Features

📌 Technologies & Tools Used

✅ Java – Language for automation

✅ Rest Assured – API Testing Framework

✅ Cucumber – BDD Testing Framework

✅ TestNG – Test Execution Framework

✅ Maven – Build Management

✅ Cucumber Reports – Test Reporting






## Test Scripts

Test Scrips are located under : 
 > src/test/java/Neil.StepDefinition
    1.BritnInsuranceTestCaseAPI.java
    2.BritnInsuranceTestCaseUI.java

Page Object Model folder structre is kept to prepare all the action methods and is located under :
> src/main/java/Neil.PageObjects


All the Scenarios was developed in the Cucumber Feature file which is located in : 

>   src/test/java/Cucumber
    1.BritInsurance.feature
    2.BritInsuranceAPI.feature
## Running Tests

1️⃣ Run All Scenarios : Execute all Cucumber tests using Maven

```bash
  mvn test
```
2️⃣ Run Specific Scenarios Using Tags

```bash
  mvn test -Dcucumber.options="--tags @Functional"
```
3️⃣Run Tests Using TestNG Runner

    Open src\test\java\Cucumber
    Open TestNgTestRunner.java
    Right-click and select "Run as TestNG Test"

## Reports & Logs


1️⃣ Cucumber HTML Report
After execution, the Cucumber HTML Report is generated at:

    > target/cucumber.html

2️⃣ Console Logs
Test execution logs are displayed in the Eclipse console.



