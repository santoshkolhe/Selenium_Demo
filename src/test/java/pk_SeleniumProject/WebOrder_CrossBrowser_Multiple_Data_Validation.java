package pk_SeleniumProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebOrder_CrossBrowser_Multiple_Data_Validation {

	WebDriver driver;
	//To call DataProvider method without extending the class
	@Test(priority = 1, dataProvider = "Weborder_Login_Scenario",dataProviderClass = WebOrder_TestData.class)
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
			driver.findElement(By.linkText("Logout")).click();
		} else
		{
			String ActResult = driver.findElement(By.id("ctl00_MainContent_status")).getText();
			Assert.assertEquals(ActResult, ExpResult);
		}

	}
	@BeforeTest
	@Parameters("browser")
	public void LaunchBrowser(String browser) throws Exception {

		if(browser.equalsIgnoreCase("firefox")){
		    WebDriverManager.firefoxdriver().setup();
		    driver = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("chrome")){
		    WebDriverManager.chromedriver().setup();
		    driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("edge")){
		    WebDriverManager.edgedriver().setup();
		    driver = new EdgeDriver();
		}
		/*else if(browser.equalsIgnoreCase("ie")){
		    WebDriverManager.iedriver().setup();
		    driver = new InternetExplorerDriver();
		}*/
		else{
			//If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}
	
	}
	@AfterTest
	public void CloseBrowser() {
		// driver.close(); // Close will close the current browser opened by Selenium
		driver.quit(); // Quit will close all the browser opened by Selenium
	}

}
