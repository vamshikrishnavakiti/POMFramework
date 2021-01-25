package TestCaseCreation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.internal.BaseClassFinder;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import BaseClass.BrowserSetup;
import ObjectRepository.LoginPage;


public class LoginPageFunctionality extends BaseClassFinder{
    WebDriver driver;
	LoginPage loginpage;
	BrowserSetup BS;
	static String filepath = System.getProperty("user.dir") + "\\config\\file.properties";
	static File file;
	FileInputStream input;
	Properties prop;
	
	
	//step1
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;

	
	//step2
	@BeforeTest
	public void SetExtent(){
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "//test-output//extendedreport.html");
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("Functional Report");
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "LocalHost");
		extent.setSystemInfo("OS", "Windows 10");
		extent.setSystemInfo("Tester Name", "Vamshi");
		extent.setSystemInfo("Browser Name", "Chrome");
	}
	
	//step3
	@AfterTest
	public void endReport(){
		extent.flush();
	}
	
	
	@BeforeMethod
	public void HomePageNavigation() throws Throwable {
		
		file =  new File(filepath);
		FileInputStream input =  new FileInputStream(file);
		Properties prop =  new Properties();
		prop.load(input);
		driver = BrowserSetup.StartBrowser(prop.getProperty("browsername"), (prop.getProperty("url")));
	}

	
	@Test(priority = 0)
	public void LoginPageOne() throws Exception {
       
		//step4
		test =extent.createTest("LoginPageOne");
		loginpage = new LoginPage(driver);
		loginpage.implicitlyWait();
		//loginpage.checkPageIsReady();
		//loginpage.gettoastCloseButton();
		loginpage.checkPageIsReady();
		loginpage.getSignInButton();
		loginpage.implicitlyWait();
		loginpage.setLoginCredentials("chatterjeeamit41@gmail.com", "Testing12345$");
		loginpage.checkPageIsReady();	
		
}
	
	
	@Test(priority = 1)
	public void VerifyTitle() throws Exception {
       
		//step4
		test =extent.createTest("VerifyTitle");
		loginpage = new LoginPage(driver);
		loginpage.implicitlyWait();
	//	loginpage.checkPageIsReady();
		//loginpage.gettoastCloseButton();
		loginpage.checkPageIsReady();
		loginpage.getSignInButton();
		loginpage.implicitlyWait();
		loginpage.setLoginCredentials("chatterjeeamit41@gmail.com", "Testing12345$");
		loginpage.checkPageIsReady();
		String title = driver.getTitle();
		System.out.println(title);
		Assert.assertEquals(title, "eCommerce demo store");
		loginpage.checkPageIsReady();	
}
	
	
	@AfterMethod
	public void tearDown(ITestResult result) throws Exception {
		
		 if (result.getStatus() == ITestResult.FAILURE) {
			   test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName()); // to add name in extent report
			   test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); // to add error/exception in extent report
			   String screenshotPath = LoginPageFunctionality.getScreenshot(driver, result.getName());
			   test.addScreenCaptureFromPath(screenshotPath);// adding screen shot
			  } else if (result.getStatus() == ITestResult.SKIP) {
			   test.log(Status.SKIP, "Test Case SKIPPED IS " + result.getName());
			  }
			  else if (result.getStatus() == ITestResult.SUCCESS) {
			   test.log(Status.PASS, "Test Case PASSED IS " + result.getName());
			  }
		    BS= new BrowserSetup();
			BS.closeBrowser();
			 }
			 
			 public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
			  String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			  TakesScreenshot ts = (TakesScreenshot) driver;
			  File source = ts.getScreenshotAs(OutputType.FILE);
			  
			  // after execution, you could see a folder "FailedTestsScreenshots" under src folder
			  String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";
			  File finalDestination = new File(destination);
			  FileUtils.copyFile(source, finalDestination);
			  return destination;
	}
}