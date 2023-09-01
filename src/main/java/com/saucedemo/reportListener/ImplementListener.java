package com.saucedemo.reportListener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.saucedemo.base.TestBase;


public class ImplementListener extends TestBase implements ITestListener{

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Testcase: "+result.getMethod().getMethodName()+" is Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL, "Testcase: "+result.getMethod().getMethodName()+" is Failed");
		test.log(Status.FAIL, result.getThrowable());
		
		TakesScreenshot scrn = ((TakesScreenshot)driver);
		File SrcFile = scrn.getScreenshotAs(OutputType.FILE);
		
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
		Date date = new Date();
		String CurrentDate = format.format(date);
		String newpath = System.getProperty("user.dir")+"/reports/screnshots/"+CurrentDate+".jpeg";
		File desFile = new File(newpath);
		//test.log(Status.FAIL, test.addScreenCaptureFromPath(newpath));
		test.addScreenCaptureFromPath(newpath, "Testcase failure screenshot");
		
		try {
			FileUtils.copyFile(SrcFile, desFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, "Testcase: "+result.getMethod().getMethodName()+" is Skipped");
		test.log(Status.SKIP, result.getThrowable());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onStart(ITestContext context) {
		extent = ExtendSetup.setExtentReport();
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
	
 
}
