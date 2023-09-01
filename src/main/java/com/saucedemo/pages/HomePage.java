package com.saucedemo.pages;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;
import com.saucedemo.base.TestBase;

public class HomePage extends TestBase{

	@FindBy (xpath="//div[@class=\"app_logo\"]") WebElement ele_Logo;
	@FindBy (xpath="//button[text()='Open Menu']") WebElement btn_OpenMenu;
	
	public HomePage()
	{
		PageFactory.initElements(driver, this);
	}
	public void verifyLogo()
	{
		if(ele_Logo.isDisplayed()) {
		TakesScreenshot scrn = ((TakesScreenshot)driver);
		File SrcFile = scrn.getScreenshotAs(OutputType.FILE);
		
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
		Date date = new Date();
		String CurrentDate = format.format(date);
		String newpath = System.getProperty("user.dir")+"/reports/screnshots/"+CurrentDate+"_Login to the portal successfully.jpeg";
		File desFile = new File(newpath);
			test.log(Status.PASS, "Login to the portal successfully");
			try {
				test.addScreenCaptureFromPath(newpath, "Login to the portal successfully");
				FileUtils.copyFile(SrcFile, desFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}else {
			test.log(Status.FAIL, "Unable to login to the portal");
		}
	}
}
