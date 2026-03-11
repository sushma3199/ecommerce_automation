package testCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import pageObjects.loginPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC3_LoginDataDrivenTest extends BaseClass{
	
	@Test(dataProvider="logindata",dataProviderClass=DataProviders.class,groups="datadriven") //getting data provider from another class (DataProvider class which is under utilities)
	public void login(String usrnme,String pwd, String exp)
	{
		logger.info("login using data driven test execution started");
		
		try {
		//homepage
		HomePage hp=new HomePage(driver);
		hp.click_accnt();
		hp.click_login();
		
		//login page
		loginPage lp=new loginPage(driver);
		lp.setUserName(usrnme);
		lp.setPassword(pwd);
		lp.click_login();
		
		//my account page
		MyAccountPage mp=new MyAccountPage(driver);
		boolean my_accnt_status=mp.isMyAccountEXists();
		
		/*
		 * 
		 *  valid - login sucess - test case pass- logout
		 *  		login fail- test case fail
		 *  
		 *  invalid - login sucess - test case fail - logout
		 *  			login fail- test case pass
		 */
		
		if(exp.equalsIgnoreCase("valid"))
		{
			if(my_accnt_status==true)
			{
				mp.click_logout();
				AssertJUnit.assertTrue(true);
			}
			else
			{
				AssertJUnit.assertTrue(false);
			}
		}
		else
		{
			if(my_accnt_status==true)
			{
				mp.click_logout();
				AssertJUnit.assertTrue(false);
			}
			else
			{
				AssertJUnit.assertTrue(true);
			}
		}
		}
		catch(Exception e)
		{
			AssertJUnit.fail();
		}
			logger.info("login data driven execution is completed");
		
	}
	
	

}
