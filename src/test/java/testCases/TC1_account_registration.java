package testCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBase.BaseClass;

public class TC1_account_registration extends BaseClass{
	
	//WebDriver driver;
	@Test(groups={"sanity","regression"})
	public void verify_account_registration()
	{
		logger.info("account registration started");
		try {
		HomePage hp=new HomePage(driver);
		hp.click_accnt();
		logger.info("we clicked on account option");
		hp.click_register();
		logger.info("we clicked on register option");
		RegistrationPage rp=new RegistrationPage(driver);
		//rp.setFirstName("siri");
		//give first name randomly with some alphabet combinations
		logger.info("opened registration page");
		rp.setFirstName(randomString().toUpperCase());// as names has to be upper case, convert lower to upper
		
		rp.setLastName(randomString().toUpperCase());
		rp.setEmail(randomString()+"@gmail.com");
		rp.setno("47267698997");
		String pwrd=randomNumbers();
		rp.setPwd(pwrd);
		rp.setConfirmPwd(pwrd);
		rp.clickPolicyBox();
		rp.click_cntinueButton();
		//now get confirmation msg
		String confrmMsg= rp.getConfirmationMsg();
		
		//now do validations
		
		if(confrmMsg.equals("Your Account Has Been Created!"))
		{
			AssertJUnit.assertTrue(true);
		}
		else
		{
			logger.error("registartion got failed");
			logger.debug("debug logs");
			AssertJUnit.assertTrue(false);
			
		}
		
		//Assert.assertEquals(confrmMsg, "Your Account Has Been Created!");
		}
		catch(Exception e)
		{
			AssertJUnit.fail();
		}
		
		
		
	}
	
	
}
