package pk_SeleniumProject;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Weborder_Login_DataProvider extends WebOrder_TestData {
	WebDriver driver;

	@Test(dataProvider="WebOrderLogin")
	public void Login(String uname,String password) throws InterruptedException {

		// Browser . Object Identifcation. Action
		driver.findElement(By.id("ctl00_MainContent_username")).sendKeys(uname);
		Thread.sleep(2000);
		driver.findElement(By.id("ctl00_MainContent_password")).sendKeys(password);
		driver.findElement(By.name("ctl00$MainContent$login_button")).click();
		Thread.sleep(2000);
		// Verify that user has logged in
		driver.findElement(By.linkText("Logout")).isDisplayed();
		driver.findElement(By.linkText("Logout")).click();
		// Close the browser at end
	}

	@BeforeTest
	public void LaunchBrowser() {
		// Launch the Browser
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		// ChromeDriver driver = new ChromeDriver();
		// WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx");
	}

	@AfterTest
	public void CloseBrowser() {
		// driver.close(); // Close will close the current browser opened by Selenium
		driver.quit(); // Quit will close all the browser opened by Selenium
}}
