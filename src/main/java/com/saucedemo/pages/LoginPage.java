package com.saucedemo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
		txt_Password.sendKeys(password);
		btn_Login.click();
		return new HomePage();
	}
	
	public void validateInvalidLogin()
	{
		if(ele_LoginError.isDisplayed())
		{
			System.out.println("Error message is displaying: Negative scenario verifird successfully");
		}
	}
}
