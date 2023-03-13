package pk_SeleniumProject;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeTest;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Weborder_CreateOrder_VerifyOrder_SiblingXpath {
	WebDriver driver;
	String ExpName;
	  @Test(priority=1)
	  public void Login() 
	  {
		driver.findElement(By.name("ctl00$MainContent$username")).sendKeys("Tester");
		driver.findElement(By.name("ctl00$MainContent$password")).sendKeys("test");
		driver.findElement(By.name("ctl00$MainContent$login_button")).click();
		//driver.findElement(By.linkText("Logout")).isDisplayed();
	  }
	  
	  @Test(priority=2)
	  public void CreateOrder_VerifyOrder() throws InterruptedException 
	  {
		  
		// to place the web order
			driver.findElement(By.linkText("Order")).click();
			// to select value from the dropdown
			Select selectProduct = new Select(driver.findElement(By.id("ctl00_MainContent_fmwOrder_ddlProduct")));
			selectProduct.selectByVisibleText("ScreenSaver");
			//selectProduct.selectByIndex(0);
			//selectProduct.selectByValue("");
			Thread.sleep(3000);
			driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).sendKeys("5");
			driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtUnitPrice")).sendKeys("100");
			driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtDiscount")).sendKeys("10");
			driver.findElement(By.xpath("//input[@class='btn_dark']")).click();
			//Generate Random number
			Random randomGenerator = new Random();
			int randomInt = randomGenerator.nextInt(1000);
			ExpName = "Dixit"+randomInt;
			
			driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtName")).sendKeys(ExpName);
			driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox2")).sendKeys("BTM");
			driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox3")).sendKeys("Bangalore");
			driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox4")).sendKeys("Karnataka");
			driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox5")).sendKeys("560076");
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
			Thread.sleep(3000);
			//String ActOutputname = driver.findElement(By.xpath("//td[normalize-space()='Dixit']")).getText();
			 String ActName = driver.findElement(By.xpath("//td[normalize-space()='" + ExpName + "']")).getText();
			//String ExpOutputname = "Dixit";
			Assert.assertEquals(ActName, ExpName);

		
	  }
	  
	  @Test(priority=3)
	  public void UpdateOrder_VerifyUpdateOrder() throws InterruptedException 
	  {
		  
		  driver.findElement(By.xpath("//td[normalize-space()='" + ExpName + "']//following-sibling::td/input")).click();
		  driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox3")).clear();
		  driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox3")).sendKeys("Chennai");
		  driver.findElement(By.id("ctl00_MainContent_fmwOrder_UpdateButton")).click();
		  String ActCity = driver.findElement(By.xpath("//td[normalize-space()='" + ExpName + "']//following-sibling::td[5]")).getText();
		  String ExpCity = "Chennai";
		  Assert.assertEquals(ActCity, ExpCity);
		  
	  }
	  @BeforeTest
	  public void launchBrowser() 
	  {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx?");	
		driver.manage().window().maximize();
	  }
	  @AfterTest
	  public void closebrowser() 
	  {
		  driver.quit();
	  }
}
