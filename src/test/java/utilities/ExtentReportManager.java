package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {

   private static final String REPORT_DIR = null;
	public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    String repName;

    @Override
    public void onStart(ITestContext testContext) {
    	//reports directory creation
    	
    	final String REPORT_DIR="reports";
    	
    	File reportDir=new File(System.getProperty("user.dir")+File.separator+REPORT_DIR);
    	if(!reportDir.exists()) // if reports named directory available then it's fine.... if not available it wil craete reports named directory
    	{
    		reportDir.mkdirs();
    	}
    	
    	//test report file format
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
                .format(new Date());

        repName = "Test-Report-" + timeStamp + ".html";

        sparkReporter = new ExtentSparkReporter(reportDir+File.separator+repName);

        sparkReporter.config().setDocumentTitle("opencart Automation Report");
        sparkReporter.config().setReportName("opencart Functional Testing");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Application", "opencart");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub Module", "Customers");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");

        String os = testContext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System", os);

        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser", browser);

        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
        if (includedGroups.isEmpty() == false) {
            extent.setSystemInfo("Groups", includedGroups.toString());
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.PASS, result.getName() + " got successfully executed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());

        test.log(Status.FAIL, result.getName() + " got failed");
        test.log(Status.INFO, result.getThrowable().getMessage());

        try {
            String imgPath = new BaseClass().captureScreen(result.getName());
            test.addScreenCaptureFromPath(imgPath);
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());

        test.log(Status.SKIP, result.getName() + " got skipped");
        test.log(Status.INFO, result.getThrowable().getMessage());
    }

    @Override
    public void onFinish(ITestContext testContext) {

        extent.flush();
        try {
        Thread.sleep(2000);//taking some time to generate report completly
        
        //below steps are used to open extent report automatically after execution of test case

        String pathOfExtentReport =
                System.getProperty("user.dir") +File.separator+ REPORT_DIR +File.separator+"repName";
        /*
         * File.seperator
         * in windows supports "\\" this type of path seperator path
         * linux supports- "//" this type of path seperator
         * depending on the OS, file.seperator takes that particluar OS paths and execute it...so there we wont hard code paths
         */

        File extentReport = new File(pathOfExtentReport);

        if(extentReport.exists() && Desktop.isDesktopSupported())
        {
            Desktop.getDesktop().browse(extentReport.toURI()); // this line will open report in the browser automatically
        } 
        else
        	{
        	System.out.println("extentreport not found or desktop not supported");
        	}
        }
        	catch (IOException e) {
        	
            e.printStackTrace();
        } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
}





