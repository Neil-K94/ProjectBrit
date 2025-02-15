package Neil.TestComponent;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Neil.Resources.ExtendReportNG;


public class Listeners extends BaseTest implements ITestListener {
	
	//ITestListeners always listen to the test results	
	ExtentTest test;
	ExtentReports extent = ExtendReportNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); //This is used to fix concurrency issue while the test runs in parallel
	@Override
	//Log is added to the report whenever the test case is started
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	@Override
	//Log is added to the report whenever the test case is Success
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test Passed");
	}

	@Override
	////Log is added to the report whenever the test case is Failed and a screen shot is attached to the report
	public void onTestFailure(ITestResult result) {
		extentTest.get().log(Status.FAIL, "Test Failed");
		extentTest.get().fail(result.getThrowable());
		
		try 
		{
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
					.get(result.getInstance());
		} 
		catch (Exception e1) 
		{
			e1.printStackTrace();
		} 
		
		String filePath = null;
		try 
		{	
			filePath = getScreenShot(result.getMethod().getMethodName(), driver);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath , result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
		extent.flush();
	}

}
