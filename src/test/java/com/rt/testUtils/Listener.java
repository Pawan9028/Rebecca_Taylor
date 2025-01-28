package com.rt.testUtils;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listener extends TestUtils implements ITestListener{
	WebDriver driver;
	ExtentTest test;
	ExtentReports extent = TestUtils.getReporterObject();

	public void onTestStart(ITestResult result) {
		//test = extent.createTest(result.getMethod().getMethodName());// Method Name
		 String className = result.getTestClass().getName();
	        String methodName = result.getMethod().getMethodName();
	        test = extent.createTest(className + " : " + methodName); // Method Name with Class Name
		test.assignCategory("All");
		try {
			List<String> tags = getTicketID(result.getMethod().getMethodName());
			if (!tags.isEmpty()) {
				for (String tag : tags) {
					if (tag.contains("AT-"))
						test.assignCategory(tag);
					else
						test.log(Status.INFO, tag);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	List<String> getTicketID(String methodName) {
		// TODO Auto-generated method stub
		return null;
	}

	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Passed");
	}

	public void onTestFailure(ITestResult result) {
		test.fail(result.getThrowable());
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
					.get(result.getInstance());
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		try {
			test.addScreenCaptureFromPath(getScreenShotPath(result.getMethod().getMethodName(),
					(ChromeDriver) driver),
					result.getMethod().getMethodName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {
		extent.flush();
		try {
			sendReportToEmail();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
