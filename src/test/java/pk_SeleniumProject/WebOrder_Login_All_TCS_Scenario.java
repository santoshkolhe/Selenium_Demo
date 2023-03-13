package pk_SeleniumProject;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebOrder_Login_All_TCS_Scenario {
	ChromeDriver driver;
	//To call DataProvider method without extending the class
	@Test(priority = 1, dataProvider = "Weborder_Login_Scenario",dataProviderClass = WebOrder_TestData.class)
	public void ValidLogin(String uname, String password, String ExpResult) {
		// for login
		driver.findElement(By.id("ctl00_MainContent_username")).clear();
		driver.findElement(By.id("ctl00_MainContent_username")).sendKeys(uname);
		driver.findElement(By.id("ctl00_MainContent_password")).clear();
		driver.findElement(By.id("ctl00_MainContent_password")).sendKeys(password);
		driver.findElement(By.id("ctl00_MainContent_login_button")).click();
		if (ExpResult.equalsIgnoreCase("List of All Orders")) {
			String ActResult = driver.findElement(By.xpath("//h2[normalize-space()='List of All Orders']")).getText();
			Assert.assertEquals(ActResult, ExpResult);
			driver.findElement(By.linkText("Logout")).click();
		} else
		{
			String ActResult = driver.findElement(By.id("ctl00_MainContent_status")).getText();
			Assert.assertEquals(ActResult, ExpResult);
		}

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
