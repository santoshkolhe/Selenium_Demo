package pk_SeleniumProject;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Capture_Screenshot {
	ChromeDriver driver;
	String filePath = System.getProperty("user.dir");

	String filepath_failure = filePath + "\\Screenshot_Failure";
	String filePath_Success = filePath + "\\Screenshot_Success";

	@Test(priority = 1)
	public void Weborder_Login() throws Exception {
		driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
		Thread.sleep(2000);
		driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test");
		driver.findElement(By.name("ctl00$MainContent$login_button")).click();
		Thread.sleep(2000);
		// Verify that user has logged in
		driver.findElement(By.linkText("Logout")).isDisplayed();

	}

	@Test(priority = 2)
	public void Weborder_Logout() throws Exception {
		driver.findElement(By.linkText("Logout1")).click();
	}

	@BeforeTest
	public void LaunchBrowser()

	{
		WebDriverManager.chromedriver().setup();
		// WebDriverManager.firefoxdriver().setup();
		// WebDriverManager.edgedriver().setup();

		// EdgeOptions options = new EdgeOptions();
		// FirefoxOptions options = new FirefoxOptions();
		ChromeOptions options = new ChromeOptions();
		options.setHeadless(false);
		// options.setHeadless(true);
		// options.setHeadless(true);
		// options.addArguments("incognito");
		// driver = new EdgeDriver(options);
		// driver = new FirefoxDriver(options);
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.navigate().to("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx");
	}

	// After every Test Method, this method will get called
	@AfterMethod
	public void CaptureScreenShot(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			File Browserscreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			// FileUtils.copyFile(Browserscreenshot, new
			// File(Relativepath_failure+"\\Login.png"));
			FileUtils.copyFile(Browserscreenshot,
					new File(filepath_failure + "\\" + result.getName() + "_" + System.currentTimeMillis() + ".png"));
		} else if (ITestResult.SUCCESS == result.getStatus()) {
			File Browserscreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			// FileUtils.copyFile(Browserscreenshot, new
			// File(filePath_Success+"\\Login.png"));
			FileUtils.copyFile(Browserscreenshot,
					new File(filePath_Success + "\\" + result.getName() + "_" + System.nanoTime() + ".png"));
		}
	}

	@AfterTest
	public void CloseBrowser() {
		// driver.close(); // Close will close the current browser opened by Selenium
		driver.quit(); // Quit will close all the browser opened by Selenium
	}

}
