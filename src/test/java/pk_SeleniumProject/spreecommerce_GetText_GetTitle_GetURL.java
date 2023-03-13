package pk_SeleniumProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class spreecommerce_GetText_GetTitle_GetURL {
	WebDriver driver;

	@Test(priority = 3)
	public void SPORTSWEAR() throws InterruptedException  {
		
		// Click on the Sportswear
		driver.findElement(By.xpath("//a[@class='nav-link main-nav-bar-item main-nav-bar-category-button dropdown-toggle'][normalize-space()='Sportswear']")).click();
	    
		Thread.sleep(3000);
		// Verify that text is present.
		String actualTextValue = driver.findElement(By.xpath("//div[normalize-space()='Sportswear']")).getText();
		String expectedValue = "SPORTSWEAR";
		Assert.assertEquals(actualTextValue, expectedValue);
		
		// Verify the web Title is matched
		String actTitle = "Sportswear - Spree Demo Site";
		String expTitle = driver.getTitle();
		Assert.assertEquals(actTitle, expTitle);
		
		 //Verify current Url is matched
		 String expURL="https://demo.spreecommerce.org/t/categories/sportswear";
		 String ectURL=driver.getCurrentUrl(); 
		 Assert.assertEquals(expURL,ectURL);
		 
	}

	@Test(priority=2)
	public void MEN() throws InterruptedException {
		// Click on the Men
		
		driver.findElement(By.xpath("//a[@class='nav-link main-nav-bar-item main-nav-bar-category-button dropdown-toggle'][normalize-space()='Men']")).click();
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

	@Test(priority=1)
	public void WOMEN() throws InterruptedException  {
		// Click on the Women
		driver.findElement(By.xpath("//a[@class='nav-link main-nav-bar-item main-nav-bar-category-button dropdown-toggle'][normalize-space()='Women']")).click();
		
		Thread.sleep(3000);
		// Verify that text is present.
		String actualTextValue = driver.findElement(By.xpath("//div[normalize-space()='Women']")).getText();
		String expectedValue = "WOMEN";
		Assert.assertEquals(actualTextValue, expectedValue);
		
		// Verify the web Title is matched.
		String expTitle = "Women - Spree Demo Site";
		String actTitle = driver.getTitle();
		Assert.assertEquals(actTitle, expTitle);
		
		// Verify current Url is matched.
		String expURL = "https://demo.spreecommerce.org/t/categories/women";
		String actURL = driver.getCurrentUrl();
		Assert.assertEquals(expURL, actURL);
	}

	@BeforeTest
	public void launchBrowser() {
		//Download the browser driver and launch the chrome browser
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		// Go to URL
		driver.get("https://demo.spreecommerce.org/");
	}

	@AfterTest
	public void closeBrowser() {
		//close the all open current browser.
		driver.quit();;
	}

}
