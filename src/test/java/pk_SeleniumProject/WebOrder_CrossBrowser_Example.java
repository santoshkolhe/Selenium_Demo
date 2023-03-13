package pk_SeleniumProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebOrder_CrossBrowser_Example {
	// Global Variable
	WebDriver driver;

	@Test
	public void Login() throws InterruptedException {
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx");
		driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
		Thread.sleep(2000);
		driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test");
		driver.findElement(By.name("ctl00$MainContent$login_button")).click();
		Thread.sleep(2000);
		// Verify that user has logged in
		driver.findElement(By.linkText("Logout")).isDisplayed();
		// Close the browser at end
	}

	@BeforeTest
	@Parameters("browser")
	public void LaunchBrowser(String browser) throws Exception {

		if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		/*
		 * else if(browser.equalsIgnoreCase("ie")){ WebDriverManager.iedriver().setup();
		 * driver = new InternetExplorerDriver(); }
		 */
		else {
			// If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}

	}

	@AfterTest
	public void CloseBrowser() {
		// driver.close(); // Close will close the current browser opened by Selenium
		driver.quit(); // Quit will close all the browser opened by Selenium
	}
}
