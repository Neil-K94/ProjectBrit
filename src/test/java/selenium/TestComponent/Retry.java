package selenium.TestComponent;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
	
	int count = 0;
	int maxTry = 1;

	//Listeners class will be monitoring if a test case is failed or not, when failed the test case is re-executed
	// based on the count
	@Override
	public boolean retry(ITestResult result) {
		
		if(count < maxTry)
		{
			count++;
			return true;
		}
		return false;
	}

}
