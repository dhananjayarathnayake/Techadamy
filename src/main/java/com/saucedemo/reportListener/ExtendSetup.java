package com.saucedemo.reportListener;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.saucedemo.base.TestBase;

public class ExtendSetup extends TestBase{
	
	public static ExtentReports setExtentReport()
	{
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
		Date date = new Date();
		String CurrentDate = format.format(date);
		String testcaseName = prop.getProperty("ReportName");
		String newpath = System.getProperty("user.dir")+"/reports/ExecutionReport_"+CurrentDate+".html";
		ExtentSparkReporter spark = new ExtentSparkReporter(newpath);
		
		extent = new ExtentReports();
		extent.attachReporter(spark);
		
		spark.config().setDocumentTitle("Execution Report");
		spark.config().setTheme(Theme.DARK);
		spark.config().setReportName("SauceDemo");
		return extent;
		
	}

}
