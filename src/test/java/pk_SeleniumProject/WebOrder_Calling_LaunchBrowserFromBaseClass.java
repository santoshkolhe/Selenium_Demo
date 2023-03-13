package pk_SeleniumProject;

import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class WebOrder_Calling_LaunchBrowserFromBaseClass extends BaseClass{
	@BeforeTest
	@Parameters("browser")
	public void preCondition(String browser) throws Exception {
		LaunchBrowser(browser);
		
	}
	@AfterTest
	public void postCondition() {
		CloseBrowser();
	}

	@Test(priority = 1)
	public void login() throws InterruptedException {
		

		driver.findElement(By.xpath("//input[@id='ctl00_MainContent_username']")).sendKeys("Tester");
		driver.findElement(By.xpath("//input[@id='ctl00_MainContent_password']")).sendKeys("test");
		driver.findElement(By.xpath("//input[@id='ctl00_MainContent_login_button']")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Order")).click();
		Thread.sleep(2000);
	}
	

}
