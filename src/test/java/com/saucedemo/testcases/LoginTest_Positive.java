package com.saucedemo.testcases;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.saucedemo.base.TestBase;
import com.saucedemo.pages.HomePage;
import com.saucedemo.pages.LoginPage;

public class LoginTest_Positive extends TestBase{
	LoginPage loginpage;
	HomePage homepage;
	
	public LoginTest_Positive() {
		super();
	}

	@BeforeSuite
	public void setUp()
	{
		initialization();
		loginpage = new LoginPage();
	}
	
	@Test(priority=1)
	public void validateLandingPageTitle()
	{
		String title = loginpage.validateLandingPageTitle();
		org.testng.Assert.assertEquals(title, prop.get("LandingPageTitle"));
		test.log(Status.PASS, "User Landing to the correct page | LoginPage Title: "+title);
	}
	
	@Parameters({"ValidUsername","ValidPassword"})
	@Test(priority=2)
	public  void Login(String username, String password)
	{
		homepage = loginpage.Login(username, password);
		
	}

	@Test(priority=3)
	public void verifyLogo()
	{
		homepage.verifyLogo();
	}
	
	@Test(priority=4)
	public void validateLandingPageTitle2()
	{
		String title2 = loginpage.validateLandingPageTitle();
		org.testng.Assert.assertEquals(title2, prop.get("HomePageTitle"));
		System.out.println("HomePage Title: "+title2);
		test.log(Status.PASS, "HomePage Title: "+title2);
		test.log(Status.PASS, "Verification Success for Positive case: User is navigated to the right page");
	}
	
	@AfterSuite
	public void closeApplication() {
		driver.quit();
	}
}
