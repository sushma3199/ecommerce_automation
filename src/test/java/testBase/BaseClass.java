package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver;
	
	//Logger class initialisation
	public Logger logger;
	
	//properties class initialization
	public Properties p;
	@BeforeClass(groups={"sanity","regression","datadriven"})
	@Parameters ({"os","browser"})
	public void setup(String os,String br) throws IOException
	{
		
		logger=LogManager.getLogger(this.getClass());
		
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			MutableCapabilities options;
			if(br.equalsIgnoreCase("chrome"))
			{
				ChromeOptions chromeOptions=new ChromeOptions();
				//chromeOptions.setPlatformName("linux");
				options=chromeOptions;
			}
			else if(br.equalsIgnoreCase("firefox"))
			{
				FirefoxOptions firefoxoptions=new FirefoxOptions();
				//firefoxoptions.setPlatformName("linux");
				options=firefoxoptions;
			}
			else {
				throw new RuntimeException("invalid browser");
			}
			
			//br
			
			
			
			 driver=new RemoteWebDriver(new URL("http://localhost:4444"),options);
			
		} 
	
		
		
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(br.toLowerCase())
			{
			case "chrome":WebDriverManager.chromedriver().setup();driver=new ChromeDriver();break;
			case "edge":WebDriverManager.chromedriver().setup();driver=new EdgeDriver();break;
			case "firefox":WebDriverManager.chromedriver().setup();driver=new FirefoxDriver();break;
		default: System.out.println("invalid browser"); return;
			}
		}
		
		//FileReader file=new FileReader("./src//test//resources//config.properties");
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String url=p.getProperty("appUrl");
		System.out.println(url);
		driver.get(url);
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups={"sanity","regression","datadriven"})
	public void closeBrowser()
	{
		if (driver != null) {
	        driver.quit();
	    }
	}
	
	//random strings
	
	public String randomString()
	{
		String str=RandomStringUtils.randomAlphabetic(6);
		return str;
	}
	
	//random numbers
	public String randomNumbers()
	{
		String no=RandomStringUtils.randomNumeric(10);
		return no;
	}
	
	//random alphanumeric
	public String randomALphaNumeric()
	{
		String alpha_num=RandomStringUtils.randomAlphanumeric(15);
		return alpha_num;
	}
	
	//taking screenshots of failed testcases
	
	public String captureScreen(String tname) throws IOException
	{
		//in which format u want ur screenshot to be saved as (yyyymmddhhmmss) timstamp format
		
		String timestamp=new SimpleDateFormat("yyyymmddhhmmss").format(new Date());
		
		//we have getSCreenshotAs method in TakesScreenshot interface so 1st we r intializing it
		TakesScreenshot screenshot=(TakesScreenshot) driver;
		//getting screen shot using getSCreenshotAs() method
		File sourceFile=screenshot.getScreenshotAs(OutputType.FILE);
		//getting target file path (screenshot folder path)
		String targetFilePath=System.getProperty("user.dir"+"//screenshots//"+tname+"_"+timestamp+".png");
		File targetFile=new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
		
	}
}
