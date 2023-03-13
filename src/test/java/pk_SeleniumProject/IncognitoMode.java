package pk_SeleniumProject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class IncognitoMode {
	WebDriver driver;

	@Test(priority = 1)
	public void chrome_Headless() throws InterruptedException {

		// Browser . Object Identifcation. Action
		driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
		// Thread.sleep(5000);
		driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test");
		driver.findElement(By.name("ctl00$MainContent$login_button")).click();
		// Thread.sleep(5000);
		// Verify that user has logged in
		driver.findElement(By.linkText("Logout")).isDisplayed();

	}

	@BeforeTest
	public void launchBrowser() {

		// WebDriverManager.firefoxdriver().setup();
		WebDriverManager.chromedriver().setup();
		// FirefoxOptions options = new FirefoxOptions();
		ChromeOptions options = new ChromeOptions();
		// options.setHeadless(false);
		// options.addArguments("headless");
		// options.setHeadless(true);
		options.addArguments("incognito");

		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx");
	}

	@AfterTest
	public void closeBrowser() {
		// close the all open current browser.
		driver.quit();

	}

}
