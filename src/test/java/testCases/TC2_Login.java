package testCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import pageObjects.loginPage;
import testBase.BaseClass;

public class TC2_Login extends BaseClass{
	
	
	@Test(groups="sanity")
	public void login()
	{
		logger.info("test execution of login started");
		try {
		
		HomePage hp=new HomePage(driver);
		hp.click_accnt();
		hp.click_login();
		loginPage lp=new loginPage(driver);
		lp.setUserName(p.getProperty("username"));
		lp.setPassword(p.getProperty("password"));
		lp.click_login();
		MyAccountPage mp=new MyAccountPage(driver);
		boolean msg=mp.isMyAccountEXists();
		if(msg==true)
		{
			AssertJUnit.assertTrue(true);
		}
		else
		{
			AssertJUnit.assertTrue(false);
		}
		}
		catch(Exception e)
		{
			logger.error("execution failed");
			logger.debug("test case failed");
			AssertJUnit.fail();
		}
		logger.info("test case execution completed");
		

	}
}
