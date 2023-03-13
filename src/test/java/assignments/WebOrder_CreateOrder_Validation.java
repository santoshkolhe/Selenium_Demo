package assignments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebOrder_CreateOrder_Validation {
	WebDriver driver;
	String ExpResult;

	@Test(priority = 1)
	public void login() {
// Enter the valid username "Tester"
		// Browser.findelelement.action
		driver.findElement(By.name("ctl00$MainContent$username")).sendKeys("Tester");
		// Enter Password "test"
		driver.findElement(By.name("ctl00$MainContent$password")).sendKeys("test");
		// Click on Submit button
		driver.findElement(By.name("ctl00$MainContent$login_button")).click();
		// Verify that Logout link is present
		// driver.findElement(By.linkText("Logout")).isDisplayed();

	}

	@Test(priority = 2, dataProvider = "Weborder_CreateOrder_Scenario", dataProviderClass = Test_Data.class)
	public void createOrder_VerifyOrder(String quantity, String cname, String street, String city, String zip,
			String card, String cardno, String expirydate, String ExpResult) throws InterruptedException {
// to place the web order
		driver.findElement(By.linkText("Order")).click();
		// to select value from the dropdown
		Select selectProduct = new Select(driver.findElement(By.id("ctl00_MainContent_fmwOrder_ddlProduct")));
		selectProduct.selectByVisibleText("MyMoney");

		Thread.sleep(3000);
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).clear();
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).sendKeys(quantity);
		driver.findElement(By.xpath("//input[@class='btn_dark']")).click();
		// For CustomerName
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtName")).clear();
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtName")).sendKeys(cname);
		// For Street
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox2")).clear();
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox2")).sendKeys(street);
		// For City
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox3")).clear();
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox3")).sendKeys(city);
		// driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox4")).sendKeys("StateName");
		// For Zip
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox5")).clear();
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox5")).sendKeys(zip);
		// For Card

		// driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_0")).sendKeys(card);
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_0")).click();

		// For Card No.
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).clear();
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).sendKeys(cardno);
		// For Expiry Date
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox1")).clear();
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox1")).sendKeys(expirydate);

		driver.findElement(By.linkText("Process")).click();
		Thread.sleep(3000);

		if (ExpResult.equalsIgnoreCase("New order has been successfully added.")) {
			String ActResult = driver
					.findElement(By.xpath("//strong[normalize-space()='New order has been successfully added.']"))
					.getText();
			Assert.assertEquals(ActResult, ExpResult);
			Thread.sleep(3000);
			// driver.findElement(By.linkText("Logout")).click();
		} else if (ExpResult.equalsIgnoreCase("Quantity must be greater than zero.")) {
			String ActResult = driver
					.findElement(By.xpath("//span[@id='ctl00_MainContent_fmwOrder_RegularExpressionValidator1']"))
					.getText();
			Assert.assertEquals(ActResult, ExpResult);
			Thread.sleep(3000);
		} else if (ExpResult.equalsIgnoreCase("Field 'Customer name' cannot be empty.")) {
			String ActResult = driver
					.findElement(By.xpath("//span[@id='ctl00_MainContent_fmwOrder_RequiredFieldValidator2']"))
					.getText();
			Assert.assertEquals(ActResult, ExpResult);
			Thread.sleep(3000);
		} else if (ExpResult.equalsIgnoreCase("Field 'Street' cannot be empty.")) {
			String ActResult = driver
					.findElement(By.xpath("//span[@id='ctl00_MainContent_fmwOrder_RequiredFieldValidator3']"))
					.getText();
			Assert.assertEquals(ActResult, ExpResult);
			Thread.sleep(3000);
		} else if (ExpResult.equalsIgnoreCase("Field 'City' cannot be empty.")) {
			String ActResult = driver
					.findElement(By.xpath("//span[@id='ctl00_MainContent_fmwOrder_RequiredFieldValidator4']"))
					.getText();
			Assert.assertEquals(ActResult, ExpResult);
			Thread.sleep(3000);
		} else if (ExpResult.equalsIgnoreCase("Field 'Zip' cannot be empty.")) {
			String ActResult = driver
					.findElement(By.xpath("//span[@id='ctl00_MainContent_fmwOrder_RequiredFieldValidator5']"))
					.getText();
			Assert.assertEquals(ActResult, ExpResult);
			Thread.sleep(3000);

		} else if (ExpResult.equalsIgnoreCase("Field 'Card Nr' cannot be empty.")) {
			String ActResult = driver
					.findElement(By.xpath("//span[@id='ctl00_MainContent_fmwOrder_RequiredFieldValidator6']"))
					.getText();
			Assert.assertEquals(ActResult, ExpResult);
			Thread.sleep(3000);
		} else if (ExpResult.equalsIgnoreCase("Field 'Expire date' cannot be empty.")) {
			String ActResult = driver
					.findElement(By.xpath("//span[@id='ctl00_MainContent_fmwOrder_RequiredFieldValidator7']"))
					.getText();
			Assert.assertEquals(ActResult, ExpResult);
			Thread.sleep(3000);
		}

	}

	@BeforeTest
	public void launchBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		// Go to Url
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx");
		driver.manage().window().maximize();
	}

	@AfterTest
	public void closeBrowser() {
		driver.quit();

	}

}
