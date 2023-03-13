package assignments;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class spreecommerce_CreateAdd_UpdateAdd_DeleteAdd_VerifyAddress {
	WebDriver driver;
	String ExpName;

	@Test(priority = 1)
	public void login() throws InterruptedException {

		// Browser.findelelement.action
		driver.findElement(By.name("spree_user[email]")).sendKeys("santosh.k@yopmail.com");
		// Enter Password "test"
		driver.findElement(By.name("spree_user[password]")).sendKeys("P@ssw0rd");
		// Click on Submit button
		driver.findElement(By.xpath("//input[@name='commit']")).click();
		Thread.sleep(3000);

	}

	@Test(priority = 2)
	public void createAdress() throws InterruptedException {

		// Click on Add new Address
		driver.findElement(By.xpath("//a[normalize-space()='Add new address']")).click();
		Thread.sleep(3000);
		String ActText = "NEW ADDRESS";
		String ExpText = driver.findElement(By.xpath("//h3[normalize-space()='New Address']")).getText();
		Assert.assertEquals(ActText, ExpText);

		// Generate Random number
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(1000);
		ExpName = "Home_" + randomInt;

		driver.findElement(By.id("address_label")).sendKeys(ExpName);
		driver.findElement(By.id("address_firstname")).sendKeys("Santosh");
		driver.findElement(By.id("address_lastname")).sendKeys("Kolhe");
		driver.findElement(By.id("address_address1")).sendKeys("1017 Middlefield Road");
		driver.findElement(By.id("address_address2")).sendKeys("Redwood");
		driver.findElement(By.id("address_city")).sendKeys("redwood");
		// to select value from the dropdown
		Select selectState = new Select(driver.findElement(By.id("address_state_id")));
		selectState.selectByVisibleText("California");

		driver.findElement(By.id("address_zipcode")).sendKeys("94061");

		Select selectCountry = new Select(driver.findElement(By.id("address_country_id")));
		selectCountry.selectByVisibleText("United States");

		driver.findElement(By.id("address_phone")).sendKeys("(630) 880-7476");

		driver.findElement(By.xpath("//input[@name='commit']")).click();
		Thread.sleep(5000);

		String ActName = driver.findElement(By.xpath("//h4[normalize-space()='" + ExpName + "']")).getText();
		Assert.assertEquals(ActName, ExpName);
		Thread.sleep(5000);

		String ActOutput = "MY ACCOUNT";
		String ExpOutput = driver.findElement(By.xpath("//h3[normalize-space()='My Account']")).getText();
		Assert.assertEquals(ActOutput, ExpOutput);
		Thread.sleep(3000);
	}

	@Test(priority = 3)
	public void updateAddress() throws InterruptedException {
		// Using Xpath with Parent Following-siblings concept
		driver.findElement(By.xpath(
				"//h4[normalize-space()='" + ExpName + "']//parent::address//parent::div//following-sibling::div/a"))
				.click();
		driver.findElement(By.id("address_address1")).clear();
		driver.findElement(By.id("address_address1")).sendKeys("1023 Road");
		driver.findElement(By.xpath("//input[@name='commit']")).click();
		// Identify updated or not using Following-siblings concept
		String ActAddress = driver
				.findElement(By.xpath("//h4[normalize-space()='" + ExpName + "']//following-sibling::div[1]"))
				.getText();
		String ExpAdress = "1023 Road Redwood,";
		Assert.assertEquals(ActAddress, ExpAdress);
	}

	@Test(priority = 4)
	public void deleteUpdatedOrder() throws InterruptedException {
		// For Select Checkbox using Parent Following-siblings concept
		driver.findElement(By.xpath(
				"//h4[normalize-space()='" + ExpName + "']//parent::address//parent::div//following-sibling::div/a[2]"))
				.click();
		driver.findElement(By.id("delete-address-popup-confirm")).click();
		// Verify User got Deleted
		Boolean ActValue = driver.getPageSource().contains(ExpName);
		Assert.assertFalse(ActValue);
	}

	@BeforeTest
	public void launchBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		// Go to Url
		driver.get("https://demo.spreecommerce.org/login");
		// Maximize window
		driver.manage().window().maximize();
	}

	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}

}
