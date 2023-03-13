package pk_SeleniumProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class HDFCNetBanking_Example {
	WebDriver driver;

	@Test(priority = 1)
	public void verifyPassword_Field() throws InterruptedException {
		//Go Inside the Frame
				driver.switchTo().frame("login_page");
				
				driver.findElement(By.xpath("//input[@name='fldLoginUserId']")).sendKeys("1000");
				//Thread.sleep(2000);

				driver.findElement(By.xpath("//a[normalize-space()='CONTINUE']")).click();
				Thread.sleep(3000);
				String ExpText = driver.findElement(By.xpath(("//label[normalize-space()='Password/IPIN']"))).getText();

				String ActText = "Password/IPIN";
				// String = driver.getCurrentUrl();
				Assert.assertEquals(ActText, ExpText);
				//Thread.sleep(2000);
				//It will take you out of the frame
				driver.switchTo().defaultContent();

			}
	

	@BeforeTest
	public void launchBrowser() {
		// Download the browser driver and launch the chrome browser
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		// Go to URL
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
	}

	@AfterTest
	public void closeBrowser() {
		// close the all open current browser.
		driver.quit();
		
	}

}
