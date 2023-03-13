package assignments;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeTest;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Weborder_spreecommerce_Form {
	WebDriver driver;

	@Test(priority = 1)
	public void login() throws InterruptedException {
		driver.findElement(By.xpath("//button[@id='account-button']//*[name()='svg']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[normalize-space()='LOGIN']")).click();

		Thread.sleep(3000);

		// enter email id
		WebElement email = driver.findElement(By.xpath("//input[@id='spree_user_email']"));
		email.sendKeys("santosh.k@yopmail.com");

		// enter password
		WebElement password = driver.findElement(By.xpath("//input[@id='spree_user_password']"));
		password.sendKeys("P@ssw0rd");
		// click on the login button
		driver.findElement(By.xpath("//input[@class='btn btn-primary btn-block spree-btn mt-2']")).click();

	}

	@Test(priority = 2)
	public void AddNewAddress() throws InterruptedException {
		// click on the Add New Address

		driver.findElement(By.xpath("//a[normalize-space()='Add new address']")).click();

		// enter Address details

		Random randomnuber = new Random();
		int randomInt = randomnuber.nextInt(1000);
		String ExpName = "Home" + randomInt;
		driver.findElement(By.xpath("//input[@id='address_label']")).sendKeys(ExpName);
		Thread.sleep(3000);

		driver.findElement(By.xpath("//input[@id='address_firstname']")).sendKeys("Santosh");
		driver.findElement(By.xpath("//input[@id='address_lastname']")).sendKeys("Kolhe");
		driver.findElement(By.xpath("//input[@id='address_address1']")).sendKeys("1 East 34th Street");

		// to select value from the dropdown
		Select country = new Select(driver.findElement(By.xpath("//select[@id='address_country_id']")));
		country.selectByVisibleText("United States");

		Select state = new Select(driver.findElement(By.xpath("//select[@id='address_state_id']")));
		state.selectByVisibleText("New York");

		driver.findElement(By.xpath("//input[@id='address_city']")).sendKeys("Queens and Staten Island");
		driver.findElement(By.xpath("//input[@id='address_zipcode']")).sendKeys("10005");

		driver.findElement(By.xpath("//input[@id='address_phone']")).sendKeys("9090909090");
		// to select value from the dropdown

		Thread.sleep(3000);

		driver.findElement(By.xpath("//input[@value='Save']")).click();
		Thread.sleep(3000);
		// Verify that address is added or not
		String ActOutput = driver.findElement(By.xpath("//h3[text()='My Account']")).getText();
		String ExpOutput = "MY ACCOUNT";
		Assert.assertEquals(ExpOutput, ActOutput);
		Thread.sleep(3000);
	}

	@BeforeTest
	public void launchBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://demo.spreecommerce.org/");
		driver.manage().window().maximize();
	}

	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}

}
