package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import testBase.BaseClass;

public class MyAccountPage extends BasePage{
	
	public MyAccountPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//h2[normalize-space()='My Account']") WebElement confrmMsg;
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']") WebElement logout;
	
	//get confirmation msg
	
	public boolean isMyAccountEXists()
	{
		
			if(confrmMsg.getText().equals("My Account"))
			{
				return true;
			}
			else
				return false;
		
	}
	
	public void click_logout()
	{
		logout.click();
	}

}
