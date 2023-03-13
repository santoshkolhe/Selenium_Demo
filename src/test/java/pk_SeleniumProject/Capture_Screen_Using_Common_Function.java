package pk_SeleniumProject;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Capture_Screen_Using_Common_Function {
	ChromeDriver driver;

	@Test(priority = 1)
	public void OrangeHRM_Login() throws Exception {
		driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
		Thread.sleep(2000);
		driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test");
		driver.findElement(By.name("ctl00$MainContent$login_button")).click();
		Thread.sleep(2000);
		// Verify that user has logged in
		driver.findElement(By.linkText("Logout")).isDisplayed();
		
		
	}

	@Test(priority = 2)
	public void OrangeHRM_Logout() throws Exception {
		driver.findElement(By.linkText("Logout1")).click();
	}

	@BeforeTest
	public void LaunchBrowser()

	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx");
	}

	@AfterMethod
	public void CaptureScreenShot(ITestResult result) throws Exception {
		if (ITestResult.FAILURE == result.getStatus()) {
			BaseClass.getScreenshotfailure(driver, result.getName());
			
		}
		else if (ITestResult.SUCCESS == result.getStatus()) 
		{
			BaseClass.getScreenshotSuccess(driver, result.getName());
		}
			
	}

	@AfterTest
	public void CloseBrowser() {
		driver.quit();
	}


}
