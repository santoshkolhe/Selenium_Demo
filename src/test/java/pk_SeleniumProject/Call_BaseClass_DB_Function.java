package pk_SeleniumProject;

import java.sql.SQLException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Call_BaseClass_DB_Function extends BaseClass {
	ChromeDriver driver;

	@BeforeTest
	public void LaunchBrowser() {
		// Launch the Browser
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		// ChromeDriver driver = new ChromeDriver();
		// WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx");
	}

	@AfterTest
	public void CloseBrowser() {
		// driver.close(); // Close will close the current browser opened by Selenium
		driver.quit(); // Quit will close all the browser opened by Selenium
	}
	@Test
	public void ConnectSQLDB_With_LoginApp() throws ClassNotFoundException, SQLException, InterruptedException,
			InstantiationException, IllegalAccessException {

		ArrayList<String> sqlData = ConnectMySQLDatabase("jdbc:mysql://localhost:3306/weborder", "root", "root","select * from login");
		System.out.println("SQL Data from DB Example Class : " + sqlData + "\n Array List Size : " + sqlData.size());

		for (String sqlValues : sqlData) {
			System.out.println(sqlValues + " split : 0\t" + sqlValues.split("~")[0]);
			System.out.println(sqlValues + " split : 1\t" + sqlValues.split("~")[1]);
			String username=sqlValues.split("~")[0];
			String password=sqlValues.split("~")[1];
			//String expresult=sqlValues.split("~")[1];

			// Browser . Object Identifcation. Action
			driver.findElement(By.id("ctl00_MainContent_username")).sendKeys(username);
			Thread.sleep(2000);
			driver.findElement(By.id("ctl00_MainContent_password")).sendKeys(password);
			driver.findElement(By.name("ctl00$MainContent$login_button")).click();
			Thread.sleep(2000);
			// Verify that user has logged in
			driver.findElement(By.linkText("Logout")).click();
		}

	}


}
