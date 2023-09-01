package com.saucedemo.pages;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.saucedemo.base.TestBase;

public class HomePage extends TestBase{

	@FindBy (xpath="//div[@class=\"app_logo\"]") WebElement ele_Logo;
	
	public HomePage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public String validateHomePageTitle()
	{
		return driver.getTitle();
	}
	public void verifyLogo()
	{
		TakesScreenshot scrn = ((TakesScreenshot)driver);
	}
}
