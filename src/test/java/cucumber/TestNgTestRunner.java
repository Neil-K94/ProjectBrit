package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
	
	//This class is used to execute the Test case which is in the Step definition
	@CucumberOptions(
			features="src/test/java/Cucumber", 
			glue="cucumber.StepDefinition", 
			monochrome = true, 
			tags= "@Funtional" , 
			plugin = {"html:target/cucumber.html"}
			)
	public class TestNgTestRunner extends AbstractTestNGCucumberTests{
		
	}

