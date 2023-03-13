package pk_SeleniumProject;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Weborder_CreateOrder_UpdateOder_VerifyOrder {
	WebDriver driver;

	@Test(priority = 1)
	public void login() {
		WebElement UserName = driver.findElement(By.name("ctl00$MainContent$username"));
		UserName.sendKeys("Tester");

		// search the password

		WebElement password = driver.findElement(By.name("ctl00$MainContent$password"));
		password.sendKeys("test");
		// click on the login button
		driver.findElement(By.name("ctl00$MainContent$login_button")).click();

		// driver.findElement(By.linkText("Logout")).isDisplayed();
	}

	@Test(priority = 2)
	public void CreateOrder_VerifyOrder() throws InterruptedException {
		// to place the web order
		driver.findElement(By.linkText("Order")).click();
		// to select value from the dropdown
		Select selectProduct = new Select(driver.findElement(By.id("ctl00_MainContent_fmwOrder_ddlProduct")));
		selectProduct.selectByVisibleText("ScreenSaver");
		// selectProduct.selectByIndex(0);
		// selectProduct.selectByValue("");
		Thread.sleep(3000);
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).sendKeys("5");
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtUnitPrice")).sendKeys("100");
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtDiscount")).sendKeys("10");
		driver.findElement(By.xpath("//input[@class='btn_dark']")).click();
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtName")).sendKeys("Dixit");
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox2")).sendKeys("Add line1");
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox3")).sendKeys("CityName");
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox4")).sendKeys("StateName");
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox5")).sendKeys("098765");
		driver.findElement(By.xpath("//input[@value='MasterCard']")).click();
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).sendKeys("1234123412341234");
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox1")).sendKeys("09/25");
		driver.findElement(By.linkText("Process")).click();
		Thread.sleep(3000);
		// Verify that order is placed/confirmed
		String ActOutput = driver.findElement(By.tagName("strong")).getText();
		String ExpOutput = "New order has been successfully added.";
		Assert.assertEquals(ExpOutput, ActOutput);

		// Verify the current url
		String ActUrl = driver.getCurrentUrl();
		String ExpUrl = "http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Process.aspx";
		Assert.assertEquals(ExpUrl, ActUrl);

		// Verify the title of the page
		String ActTitle = driver.getTitle();
		String ExpTitle = "Web Orders";
		Assert.assertEquals(ExpTitle, ActTitle);
		// Go to View All Order and Verify the Added user
		driver.findElement(By.linkText("View all orders")).click();
		String ActOutputname = driver.findElement(By.xpath("//td[normalize-space()='Dixit']")).getText();
		String ExpOutputname = "Dixit";
		Assert.assertEquals(ActOutputname, ExpOutputname);

	}

	@BeforeTest
	public void launchBrowser() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx?");
		driver.manage().window().maximize();
	}

	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}
}
