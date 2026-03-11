package pageObjects;


	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.PageFactory;

	public class HomePage extends BasePage{

		/*
		 * 1. constructor
		 * 2. locatprs
		 * 3. actions
		 */
		
		// constructor
		/*WebDriver driver;
		HomePage(WebDriver driver)
		{
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}
		
		*instead of writing constructor every time in every single page object class
		*we have created a BasePage - this is a parent page object class of all page object classes
		*in BasePage itself we create page factory related constructor an call this method in all page factory classes
		*
		*/
		
		public HomePage(WebDriver driver)
		{
			super(driver);// super method is used to call immediate parent class/ immediate parent method/ immediate parent variables
		}
		
		//locators
		@FindBy(xpath="//span[normalize-space()='My Account']") WebElement my_accnt_option;
		@FindBy(xpath="//a[normalize-space()='Register']") WebElement register_option;
		@FindBy(xpath="//a[normalize-space()='Login']") WebElement login_option;
		
		//actions
		
		public void click_accnt()
		{
			my_accnt_option.click();
		}
		
		public void click_register()
		{
			register_option.click();
		}
		public void click_login()
		{
			login_option.click();
		}
	}



