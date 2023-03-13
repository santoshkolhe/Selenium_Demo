package assignments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class WebOrder_Multitest_CreateOrder {

	WebDriver driver;

	// To call DataProvider method without extending the class
	@Test(priority = 1, dataProvider = "Weborder_Login", dataProviderClass = WebOrder_TestData.class)
	public void ValidLogin(String uname, String password, String ExpResult) {
		// for login
		driver.get("http://secure.smartbearsoftware.com/samples/testcomplete11/WebOrders/login.aspx");
		driver.findElement(By.id("ctl00_MainContent_username")).clear();
		driver.findElement(By.id("ctl00_MainContent_username")).sendKeys(uname);
		driver.findElement(By.id("ctl00_MainContent_password")).clear();
		driver.findElement(By.id("ctl00_MainContent_password")).sendKeys(password);
		driver.findElement(By.id("ctl00_MainContent_login_button")).click();
		if (ExpResult.equalsIgnoreCase("List of All Orders")) {
			String ActResult = driver.findElement(By.xpath("//h2[normalize-space()='List of All Orders']")).getText();
			Assert.assertEquals(ActResult, ExpResult);
			// driver.findElement(By.linkText("Logout")).click();
		} else {
			String ActResult = driver.findElement(By.id("ctl00_MainContent_status")).getText();
			Assert.assertEquals(ActResult, ExpResult);
		}

	}

	@Test(priority = 2, dataProvider = "Weborder_CreateOrder", dataProviderClass = WebOrder_TestData.class)
	public void CreateOrder_VerifyOrder(String product, String quantity, String price, String dis, String cname,
			String street, String city, String state, String zip, String cardno, String expdate, String ExpUrl)
			throws InterruptedException {
		// to place the web order
		driver.findElement(By.linkText("Order")).click();
		// to select value from the dropdown
		Select selectProduct = new Select(driver.findElement(By.id("ctl00_MainContent_fmwOrder_ddlProduct")));
		selectProduct.selectByVisibleText(product);
		// selectProduct.selectByIndex(0);
		// selectProduct.selectByValue("");
		Thread.sleep(3000);
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).sendKeys(quantity);
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtUnitPrice")).sendKeys(price);
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtDiscount")).sendKeys(dis);
		driver.findElement(By.xpath("//input[@class='btn_dark']")).click();
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtName")).sendKeys(cname);
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox2")).sendKeys(street);
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox3")).sendKeys(city);
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox4")).sendKeys(state);
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox5")).sendKeys(zip);
		driver.findElement(By.xpath("//input[@value='MasterCard']")).click();
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).sendKeys(cardno);
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox1")).sendKeys(expdate);
		driver.findElement(By.linkText("Process")).click();
		Thread.sleep(3000);
		// Verify that order is placed/confirmed
		String ActOutput = driver.findElement(By.tagName("strong")).getText();
		String ExpOutput = "New order has been successfully added.";
		Assert.assertEquals(ExpOutput, ActOutput);

		// Verify the current url
		String ActUrl = driver.getCurrentUrl();
		// String ExpUrl = ;
		Assert.assertEquals(ExpUrl, ActUrl);

		// Verify the title of the page
		String ActTitle = driver.getTitle();
		String ExpTitle = "Web Orders";
		Assert.assertEquals(ExpTitle, ActTitle);
		// Go to View All Order and Verify the Added user
		driver.findElement(By.linkText("View all orders")).click();
		String ActOutputname = driver.findElement(By.xpath("//td[normalize-space()='Santosh']")).getText();
		String ExpOutputname = cname;
		Assert.assertEquals(ActOutputname, ExpOutputname);

	}

	@BeforeTest
	@Parameters("browser")
	public void LaunchBrowser(String browser) throws Exception {

		if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		/*
		 * else if(browser.equalsIgnoreCase("ie")){ WebDriverManager.iedriver().setup();
		 * driver = new InternetExplorerDriver(); }
		 */
		else {
			// If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}

	}

	@AfterTest
	public void CloseBrowser() {
		// driver.close();
		driver.quit();
	}
}
