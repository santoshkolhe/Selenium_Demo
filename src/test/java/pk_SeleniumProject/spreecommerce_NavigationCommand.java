package pk_SeleniumProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class spreecommerce_NavigationCommand {
	WebDriver driver;

	@Test(priority = 1)
	public void SPORTSWEAR() throws InterruptedException {
		driver.findElement(By.linkText("WOMEN")).click();
		Thread.sleep(5000);
		// driver.findElement(By.linkText("SKIRTS")).isDisplayed();
		// Display Women

		String ActTextValue = driver.findElement(By.xpath("//div[normalize-space()='Women']")).getText();
		String ExpTextValue = "WOMEN";
		Assert.assertEquals(ActTextValue, ExpTextValue);

		// Verify Current URL

		String ExpURL = "https://demo.spreecommerce.org/t/categories/women";
		String ActURL = driver.getCurrentUrl();
		Assert.assertEquals(ActURL, ExpURL);

		// Verify Title

		String ExpTitle = "Women - Spree Demo Site";
		String ActTitle = driver.getTitle();
		Assert.assertEquals(ActTitle, ExpTitle);

		// Click on Sub-Products
		driver.findElement(By.xpath("//a[text()='Dresses']")).click();
		Thread.sleep(3000);
		//driver.findElement(By.xpath("//div[@title='Floral Wrap Dress']")).click();
		
		String subproduct = driver.findElement(By.xpath("//div[normalize-space()='Dresses']")).getText();
		String ExpProduct = "DRESSES";
		Assert.assertEquals(subproduct, ExpProduct);

		// Navigate Back
		driver.navigate().back();
		Thread.sleep(3000);
		String ActTextValueBack = driver.findElement(By.xpath("//div[normalize-space()='Women']")).getText();
		String ExpTextValueBack = "WOMEN";
		Assert.assertEquals(ActTextValueBack, ExpTextValueBack);
		
		// Navigate Forward
		driver.navigate().forward();
		Thread.sleep(3000);
		String subproductForward = driver.findElement(By.xpath("//div[normalize-space()='Dresses']")).getText();
		String ExpProductForward = "DRESSES";
		Assert.assertEquals(subproductForward, ExpProductForward);

	}

	//@Test(priority = 2)
	public void MEN() throws InterruptedException {
		// Click on the Men

		driver.findElement(By.xpath(
				"//a[@class='nav-link main-nav-bar-item main-nav-bar-category-button dropdown-toggle'][normalize-space()='Men']"))
				.click();
		// Verify that text is present.
		Thread.sleep(3000);
		String actualTextValue = driver.findElement(By.xpath("//div[normalize-space()='Men']")).getText();
		String expectedValue = "MEN";
		Assert.assertEquals(actualTextValue, expectedValue);

		// Verify the web Title is matched.
		String expTitle = "Men - Spree Demo Site";
		String actTitle = driver.getTitle();
		Assert.assertEquals(actTitle, expTitle);

		// Verify current Url is matched.
		String expURL = "https://demo.spreecommerce.org/t/categories/men";
		String actURL = driver.getCurrentUrl();
		Assert.assertEquals(expURL, actURL);
	}

	//@Test(priority = 3)
	public void WOMEN() throws InterruptedException {
		// Click on the Sportswear
		driver.findElement(By.xpath(
				"//a[@class='nav-link main-nav-bar-item main-nav-bar-category-button dropdown-toggle'][normalize-space()='Sportswear']"))
				.click();

		Thread.sleep(3000);
		// Verify that text is present.
		String actualTextValue = driver.findElement(By.xpath("//div[normalize-space()='Sportswear']")).getText();
		String expectedValue = "SPORTSWEAR";
		Assert.assertEquals(actualTextValue, expectedValue);

		// Verify the web Title is matched
		String actTitle = "Sportswear - Spree Demo Site";
		String expTitle = driver.getTitle();
		Assert.assertEquals(actTitle, expTitle);

		// Verify current Url is matched
		String expURL = "https://demo.spreecommerce.org/t/categories/sportswear";
		String ectURL = driver.getCurrentUrl();
		Assert.assertEquals(expURL, ectURL);
		
		//click on the subproducts
		driver.findElement(By.xpath("//div[@title='Floral Wrap Dress']")).click();
		String subProduct =driver.findElement(By.xpath("//span[@text()='Floral Wrap Dress']")).getText();
	    String ExpProduct ="Floral Wrap Dress";
	    Assert.assertEquals(subProduct, ExpProduct);
	}

	@BeforeTest
	public void launchBrowser() {
		// Download the browser driver and launch the chrome browser
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		// Go to URL
		driver.get("https://demo.spreecommerce.org/");
	}

	@AfterTest
	public void closeBrowser() {
		// close the all open current browser.
		driver.quit();
		;
	}

}
