package com.saucedemo.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.saucedemo.base.TestBase;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.HomePage;

public class LoginTest_Negative extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	
	public LoginTest_Negative()
	{
		super();
	}

	@BeforeSuite
	public void Setup()
	{
		initialization();
		loginPage = new LoginPage();
	}
	
	@Test (priority = 1)
	public void validateLandingPageTitle1()
	{
		String tit = loginPage.validateLandingPageTitle();
		Assert.assertEquals(tit, prop.get("LandingPageTitle"));
		test.log(Status.PASS,"User Landing to the correct page | LandingPage Title: "+tit);
	}
	
	@Parameters({"InvalidUsername","InvalidPassword"})
	@Test (priority = 2)
	public void Login(String username, String password)
	{
		test.log(Status.INFO,"User Enter "+ username + " and " +password);
		homePage = loginPage.Login(username, password);
	}
	
	@Test(priority = 3)
	public void validateInvalidLogin()
	{
		loginPage.validateInvalidLogin();
	}
	
	@Test(priority = 4)
	public void validateLandingPageTitle2()
	{
		//We can verify title using Assert in the negative scenario using assertNotEquals. 
		//Then we can validate that land page title differ to the home page. But in this application both titles are same
		//Therefore I have us asserNotEquals to verify URLs and check whether there is the landpage(login page) title
		String tit2 = loginPage.validateLandingPageTitle();
		String currentURL = driver.getCurrentUrl();
		Assert.assertNotEquals(currentURL, prop.get("HomePageURL"));
		Assert.assertEquals(tit2, prop.get("LandingPageTitle"));
		test.log(Status.PASS, "Verification Success for Negative case: User is not navigated to the right page");
	}
	
	@AfterSuite
	public void CloseApplication()
	{
		driver.quit();
	}
}

