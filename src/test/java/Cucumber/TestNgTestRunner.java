package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
	
	@CucumberOptions(
			features="src/test/java/Cucumber", 
			glue="Neil.StepDefinition", 
			monochrome = true, 
			tags= "@Funtional" , 
			plugin = {"html:target/cucumber.html"}
			)
	public class TestNgTestRunner extends AbstractTestNGCucumberTests{
		
	}

