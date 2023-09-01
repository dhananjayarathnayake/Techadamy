package com.saucedemo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;
import com.saucedemo.base.TestBase;

public class LoginPage extends TestBase{
	
	@FindBy (name="user-name") WebElement txt_Username;
	@FindBy (name="password") WebElement txt_Password;
	@FindBy (name="login-button") WebElement btn_Login;
	@FindBy (xpath="//h3[@data-test=\"error\"]") WebElement ele_LoginError;
	
	public LoginPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public String validateLandingPageTitle()
	{
		return driver.getTitle();
	}
	
	public HomePage Login(String username, String password) 
	{
		txt_Username.sendKeys(username);
		//Thread.sleep(10);
		txt_Password.sendKeys(password);
		//Thread.sleep(10);
		btn_Login.click();
		
		return new HomePage();
	}
	
	public void validateInvalidLogin()
	{
		if(ele_LoginError.isDisplayed())
		{
			test.log(Status.PASS, "Error message is displaying: Negative scenario verifird successfully");
		}
		else
		{
			test.log(Status.FAIL, "Verification Failed for Negative scenario");
		}
	}
}
