package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage{
	
	/*
	 * 1/constructor
	 * 2.locators
	 * 3.actions
	 */

	//constructor
	
	public RegistrationPage(WebDriver driver)
	{
		super(driver);
	}
	
	//locators
	@FindBy(xpath="//input[@id='input-firstname']") WebElement first_name;
	@FindBy(xpath="//input[@id='input-lastname']") WebElement last_name;
	@FindBy(xpath="//input[@id='input-email']")  WebElement email;
	@FindBy(xpath="//input[@id='input-telephone']") WebElement mobile_no;
	@FindBy(xpath="//input[@id='input-password']") WebElement pwd;
	@FindBy(xpath="//input[@id='input-confirm']") WebElement confirm_pwd;
	@FindBy(xpath="//input[@name='agree']") WebElement policy_check;
	@FindBy(xpath="//input[@value='Continue']") WebElement continue_button;
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") WebElement confirm_msg;
	
	//actions
	
	public void setFirstName(String firstname)
	{
		first_name.sendKeys(firstname);
	}
	
	public void setLastName(String lastname)
	{
		last_name.sendKeys(lastname);
	}
	public void setEmail(String mail)
	{
		email.sendKeys(mail);
	}
	public void setno(String num)
	{
		mobile_no.sendKeys(num);
	}
	
	public void setPwd(String passwrd)
	{
		pwd.sendKeys(passwrd);
	}
	public void setConfirmPwd(String passwrd)
	{
		confirm_pwd.sendKeys(passwrd);
	}
	
	public void clickPolicyBox()
	{
		policy_check.click();
	}
	
	public void click_cntinueButton()
	{
		continue_button.click();
	}
	
	//for confirmation msgs we writing one sep method to get the text
	
	public String getConfirmationMsg()
	{
		try {
			return confirm_msg.getText();
		}
		catch (Exception e)
		{
			return e.getMessage();
		}
	}
}
