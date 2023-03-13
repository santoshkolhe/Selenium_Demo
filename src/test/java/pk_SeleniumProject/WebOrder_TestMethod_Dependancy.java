package pk_SeleniumProject;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebOrder_TestMethod_Dependancy {
	// Global Declaration
		ChromeDriver driver;

		@Test(description="Login from Application")
		public void Login() throws InterruptedException {
			System.out.println("Second Method as per Alphabet");
			driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
			Thread.sleep(2000);
			driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test");
			driver.findElement(By.name("ctl00$MainContent$login_button")).click();
			Thread.sleep(2000);
			//Verify that user has logged in
			driver.findElement(By.linkText("Logout")).isDisplayed();

		}

		@Test(dependsOnMethods= {"Login"},description="Logout from Application",enabled=true)
		public void Logout() throws InterruptedException {
			System.out.println("First Method as per Alphabet");
			driver.findElement(By.linkText("Logout")).click();
			driver.findElement(By.name("ctl00$MainContent$login_button")).isDisplayed();
		}

		@BeforeTest
		public void LaunchBrowser() {
			// Download chromedriver at run time
			WebDriverManager.chromedriver().setup();
			// WebDriverManager.firefoxdriver().setup();
			// driver = new FirefoxDriver();
			driver = new ChromeDriver();
			driver.get("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx");
		}

		@AfterTest
		public void CloseBrowser() {
			driver.quit();
		}

}
