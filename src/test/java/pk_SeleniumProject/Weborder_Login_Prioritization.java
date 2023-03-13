package pk_SeleniumProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Weborder_Login_Prioritization {
	WebDriver driver;

	@Test(priority=1)
	public void login() {

		// Enter the valid user name
		driver.findElement(By.name("ctl00$MainContent$username")).sendKeys("Tester");
		// Enter the valid Password
		driver.findElement(By.name("ctl00$MainContent$password")).sendKeys("test");
		// Enter click on Login Button
		driver.findElement(By.name("ctl00$MainContent$login_button")).click();
		// Check the Expected Result
		driver.findElement(By.linkText("Logout")).isDisplayed();

	}
	@Test(priority=2)
     public void logout() {
    	 driver.findElement(By.linkText("Logout")).click();
    	 driver.findElement(By.name("ctl00$MainContent$login_button")).isDisplayed();
     }
	@BeforeTest
	public void launchBrowser() {
		// Download the browser driver and launch the chrome browser
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();// ctrl+shift+O use for import file
		driver.manage().window().maximize();
		// Go to URL
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx");
	}

	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}

}
