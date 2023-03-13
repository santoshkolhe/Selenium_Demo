package pk_Assignments;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pk_SeleniumProject.BaseClass;

public class Assignment3 {
	WebDriver driver;
	WebDriverWait wait;
	String orderno, email;
	//BaseClass object = new BaseClass();

	@Test(description = "Login into application")
	public void login() {
		driver.findElement(By.xpath("//button[normalize-space()='Log in']")).click();
		String ActResult = driver.findElement(By.xpath("//h1[normalize-space()='Dashboard']")).getText();
		String ExpResult = "Dashboard";
		Assert.assertEquals(ActResult, ExpResult);
		System.out.println("User is Logged In!!");

	}

	@Test(dependsOnMethods = { "login" }, description = "Navigate to Customers Page", enabled = true)

	public void navigateToCustomersPage() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='#']//p[contains(text(),'Customers')]")));

		driver.findElement(By.xpath("//a[@href='#']//p[contains(text(),'Customers')]")).click();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//a[@href='/Admin/Customer/List']//p[contains(text(),'Customers')]")));

		driver.findElement(By.xpath("//a[@href='/Admin/Customer/List']//p[contains(text(),'Customers')]")).click();
		String ActResult1 = driver.findElement(By.xpath("//h1[normalize-space()='Customers']")).getText();
		String ExpResult1 = "Customers";
		Assert.assertEquals(ActResult1, ExpResult1);

	}

	@Test(dependsOnMethods = { "navigateToCustomersPage" }, description = "Add New Customer Details", enabled = true)
	public void addNewCustomer() {

		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[normalize-space()='Add new']")));
		driver.findElement(By.xpath("//a[normalize-space()='Add new']")).click();
		String ActTitle = driver.getTitle();
		String ExpTitle = "Add a new customer / nopCommerce administration";
		Assert.assertEquals(ActTitle, ExpTitle);

		Random random = new Random();
		// Integer Random No
		int append = random.nextInt(1000);

		email = "Daisy" + append + "@nopCommerce.com";

		driver.findElement(By.id("Email")).sendKeys(email);
		System.out.println();
		driver.findElement(By.id("Password")).sendKeys("Daisy@123");
		driver.findElement(By.id("FirstName")).sendKeys("Daisy");
		driver.findElement(By.id("LastName")).sendKeys("Alexander");
		driver.findElement(By.id("Gender_Female")).click();
		driver.findElement(By.id("Company")).sendKeys("Amazon");
		driver.findElement(By.id("IsTaxExempt")).click();
		driver.findElement(By.xpath("//div[@class='input-group-append']//div[@role='listbox']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[normalize-space()='Test store 2']")));
		driver.findElement(By.xpath("//li[normalize-space()='Test store 2']")).click();
		Select vendor = new Select(driver.findElement(By.id("VendorId")));
		vendor.selectByVisibleText("Vendor 1");
		driver.findElement(By.id("AdminComment")).sendKeys("This is the new customer to be added.");
		driver.findElement(By.xpath("//button[@name='save']")).click();
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissable']")).isDisplayed());
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[normalize-space()='" + email + "']")));
		String ActEmail = driver.findElement(By.xpath("//td[normalize-space()='" + email + "']")).getText();
		Assert.assertEquals(ActEmail, email);
		System.out.println("New User is added!!");

	}

	@Test(dependsOnMethods = {"addNewCustomer" }, description = "Check status of customer as Active or Inactive", enabled = true)
	public void checkCustomerStatus() throws Exception {
		Boolean status = driver
				.findElement(By.xpath("//td[normalize-space()='" + email + "']//following-sibling::td[4]/i"))
				.getAttribute("class").contains("true");

		Assert.assertTrue(status);

		if (status) {
			driver.findElement(By.xpath("//td[normalize-space()='" + email + "']//following-sibling::td[5]/a")).click();
			String ActTitle = driver.getTitle();
			String ExpTitle = "Edit customer details / nopCommerce administration";
			Assert.assertEquals(ActTitle, ExpTitle);
			driver.findElement(By.xpath("//input[@id='Active']")).click();
			driver.findElement(By.xpath("//button[@name='save']")).click();
			Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissable']"))
					.isDisplayed());
			wait.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//td[normalize-space()='" + email + "']//following-sibling::td[4]/i")));
			Assert.assertTrue(
					driver.findElement(By.xpath("//td[normalize-space()='" + email + "']//following-sibling::td[4]/i"))
							.getAttribute("class").contains("false"));
			System.out.println("User status has been updated!!");

		}

	}

	@Test(dependsOnMethods = { "checkCustomerStatus" }, description = "Logout from application", enabled = true)
	public void logout() {
		driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();
		String ActTitle = driver.getTitle();
		String ExpTitle = "Your store. Login";
		Assert.assertEquals(ActTitle, ExpTitle);
		System.out.println("User is Logged Out!!");
	}

	@BeforeTest
	public void LaunchBrowser() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://admin-demo.nopcommerce.com/login");
		driver.manage().window().maximize();
	}

	@AfterTest
	public void CloseBrowser() {

		driver.quit();
	}
	
}
