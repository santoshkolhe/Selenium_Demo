package pk_SeleniumProject;


import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExtentReportExample extends BaseClass {
	
		// Calling ExtentReport from Base Class
			@BeforeClass()
			public void CallingReport() {
				BaseClass.startReport();
			}
			
			//builds a new report using the html template 
					//ExtentSparkReporter htmlReporter;
				//	ExtentReports extent;
					//helps to generate the logs in test report.
					//ExtentTest test;
					ChromeDriver driver;

					@Test(priority= 2)
					public void Sign_On() throws InterruptedException

					{

						test = extent.createTest("Test Case 2", "Login to WebOrder Application");
						driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
						Thread.sleep(2000);
						driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test");
						driver.findElement(By.name("ctl00$MainContent$login_button")).click();
						Thread.sleep(2000);
						// Verify that user has logged in
						driver.findElement(By.linkText("Logoutt")).isDisplayed();
						// Close the browser at end
					}

					@Test(priority= 1)
					public void LaunchApplication()

					{

						test = extent.createTest("Test Case 1", "Launching Chrome Browser");

						WebDriverManager.chromedriver().setup();
						//WebDriverManager.chromedriver().setup();
						//driver = new ChromeDriver();
						driver = new ChromeDriver();
						driver.manage().window().maximize();
						driver.get("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx");
					}

					/*
					@BeforeClass()
					
					public void startReport() {
						// initialize the HtmlReporter
						htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") +"/test-output/ExtentReport.html");
						//initialize ExtentReports and attach the HtmlReporter
						extent = new ExtentReports();
						extent.attachReporter(htmlReporter);
						//To add system or environment info by using the setSystemInfo method.
						extent.setSystemInfo("OS", System.getProperty("os.name"));
						extent.setSystemInfo("Browser", "Chrome Latest");
						extent.setSystemInfo("QA Name", "Abhi");

						//configuration items to change the look and feel
						//add content, manage tests etc
						//htmlReporter.config().setChartVisibilityOnOpen(true);
						htmlReporter.config().setDocumentTitle("WebOrder - Extent Report");
						htmlReporter.config().setReportName("Smoke Test Report");
						//htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
						htmlReporter.config().setTheme(Theme.DARK);
						htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

					}
	                */
					@AfterTest
					public void CloseBrowser()

					{
						driver.quit();
						//to write or update test information to reporter
						extent.flush();
					}

					@AfterMethod
					public void getResult(ITestResult result) throws Exception {
						if(result.getStatus() == ITestResult.FAILURE) {
							test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
							test.fail(result.getThrowable());
							String screenshotPath = BaseClass.getScreenshotfailure(driver, result.getName());
							//To add it in the extent report 
							test.addScreenCaptureFromPath(screenshotPath);//This is for Screenshot
							
						}
						else if(result.getStatus() == ITestResult.SUCCESS) {
							test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" PASSED ", ExtentColor.GREEN));
							//String screenshotPath = BaseClass.getScreenhot(driver, result.getName());
							//To add it in the extent report 
							//test.addScreenCaptureFromPath(screenshotPath);//This is for Screenshot
						}
						else {
							test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" SKIPPED ", ExtentColor.ORANGE));
							test.skip(result.getThrowable());
						}
					}

}
