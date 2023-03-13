package pk_SeleniumProject;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
public class Weborder_Login {
	@Test
	public void login_Valid_Scenario() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();//ctrl+shift+O  use for import file
		driver.manage().window().maximize();
		//Go to URL
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx");
		//Enter the valid user name
		driver.findElement(By.name("ctl00$MainContent$username")).sendKeys("Tester");
		//Enter the valid Password
		driver.findElement(By.name("ctl00$MainContent$password")).sendKeys("test");
		//Enter click on Login Button
		driver.findElement(By.name("ctl00$MainContent$login_button")).click();
		//Check the Expected Result
		driver.findElement(By.linkText("Logout")).isDisplayed();
		driver.quit();
		}
	
		
	}


