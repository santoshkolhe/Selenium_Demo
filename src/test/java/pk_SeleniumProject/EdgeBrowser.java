package pk_SeleniumProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class EdgeBrowser {
	WebDriver driver;

	@Test(priority = 1)
	public void EdgeBroser() throws InterruptedException {
		WebDriverManager.edgedriver().setup();
		// WebDriverManager.chromedriver().setup();      
		// FirefoxOptions options = new FirefoxOptions();
		// ChromeOptions options = new ChromeOptions();
        EdgeOptions options = new EdgeOptions();
		// options.setHeadless(false);       
		options.addArguments("headless");
		// options.setHeadless(true);       
		options.addArguments("incognito");
		driver = new EdgeDriver(options);
		//driver = new FirefoxDriver(options);       
		// driver = new ChromeDriver(options);        
		driver.manage().window().maximize();
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx");    // Browser . Object Identifcation. Action
		driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
		
	    // Thread.sleep(5000);      
        driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test");
        driver.findElement(By.name("ctl00$MainContent$login_button")).click();
        // Thread.sleep(5000);
        // Verify that user has logged in
        driver.findElement(By.linkText("Logout")).isDisplayed();
        //driver.quit();
	}

		
}
