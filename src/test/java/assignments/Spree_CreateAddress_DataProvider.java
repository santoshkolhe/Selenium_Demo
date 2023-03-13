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

public class Spree_CreateAddress_DataProvider extends Spree_CreateAddress_TestData {
	WebDriver driver;

	@Test(priority = 1)
	public void login() throws InterruptedException {

	
		driver.findElement(By.name("spree_user[email]")).sendKeys("santosh.k@yopmail.com");
		// Enter Password "test"
		driver.findElement(By.name("spree_user[password]")).sendKeys("P@ssw0rd");
		// Click on Submit button
		driver.findElement(By.xpath("//input[@name='commit']")).click();
		Thread.sleep(3000);
		
		
	}
	
	@Test(priority = 2, dataProvider="SpreeCreateAddress")
	public void newAddress(String homeadd,String fname, String lname,String add1, String add2,String city,String state, String zipcode,String phoneno) throws InterruptedException {

		//Click on Add new Address
		driver.findElement(By.xpath("//a[normalize-space()='Add new address']")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.id("address_label")).sendKeys(homeadd);
		driver.findElement(By.id("address_firstname")).sendKeys(fname);
		driver.findElement(By.id("address_lastname")).sendKeys(lname);
		driver.findElement(By.id("address_address1")).sendKeys(add1);
		driver.findElement(By.id("address_address2")).sendKeys(add2);
		driver.findElement(By.id("address_city")).sendKeys(city);
		
		// to select value from the dropdown
		Select selectState = new Select(driver.findElement(By.id("address_state_id")));
		selectState.selectByVisibleText(state);
		
		driver.findElement(By.id("address_zipcode")).sendKeys(zipcode);
		
		Select selectCountry = new Select(driver.findElement(By.id("address_country_id")));
		selectCountry.selectByVisibleText("United States");
		
		driver.findElement(By.id("address_phone")).sendKeys(phoneno);
		
		driver.findElement(By.xpath("//input[@name='commit']")).click();
		Thread.sleep(5000);

		//String ActOutput = "MY ACCOUNT";
		//String ExpOutput = driver.findElement(By.xpath("//h3[normalize-space()='My Account']")).getText();
		//Assert.assertEquals(ActOutput, ExpOutput);
			
	}

	
	@BeforeTest
	public void launchBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		// Go to Url
		driver.get("https://demo.spreecommerce.org/login");
		//Maximize window
		driver.manage().window().maximize();
	}

	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}
}
