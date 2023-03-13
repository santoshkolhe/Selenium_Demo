package pk_SeleniumProject;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebOrder_Login_Parameters {
	ChromeDriver driver;
	@Test(priority = 1)
	@Parameters({"uname","upass"})
	public void ValidLogin(String uname, String upass) {
		// for login
		driver.findElement(By.id("ctl00_MainContent_username")).sendKeys(uname);
		driver.findElement(By.id("ctl00_MainContent_password")).sendKeys(upass);
		driver.findElement(By.id("ctl00_MainContent_login_button")).click();
		driver.findElement(By.linkText("Logout")).click();
		//driver.findElement(By.linkText("View all orders")).isDisplayed();
	}
	
	@BeforeTest
	public void LaunchBrowser() {
		// to launch browser
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://secure.smartbearsoftware.com/samples/testcomplete11/WebOrders/login.aspx");
	}

	@AfterTest

	public void afterTest() {
		// to close the browser
		driver.quit();

	}
}
