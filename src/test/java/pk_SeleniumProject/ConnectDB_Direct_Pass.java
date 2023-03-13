package pk_SeleniumProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ConnectDB_Direct_Pass {
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
	public void ConnectMySQLDB() throws ClassNotFoundException, SQLException, InterruptedException {
		// Connection URL Syntax: "jdbc:mysql://ipaddress:portnumber/db_name"
		String dbUrl = "jdbc:mysql://localhost:3306/weborder";

		// Database Username
		String username = "root";

		// Database Password
		String password = "root";

		// Query to Execute
		String query = "select * from login;";

		// Load mysql jdbc driver
		Class.forName("com.mysql.cj.jdbc.Driver");

		// Create Connection to DB
		Connection con = DriverManager.getConnection(dbUrl, username, password);

		// Create Statement Object
		Statement stmt = con.createStatement();

		// Execute the SQL Query. Store results in ResultSet
		ResultSet rs = stmt.executeQuery(query);

		// While Loop to iterate through all data and print results
		while (rs.next()) {
			String Username = rs.getString(1);
			String Password = rs.getString(2);
			System.out.println(Username + "  " + Password);
			// Pass Data to Login OrnageHRM

			// Browser . Object Identifcation. Action
			driver.findElement(By.id("ctl00_MainContent_username")).sendKeys(Username);
			Thread.sleep(2000);
			driver.findElement(By.id("ctl00_MainContent_password")).sendKeys(Password);
			driver.findElement(By.name("ctl00$MainContent$login_button")).click();
			Thread.sleep(2000);
			// Verify that user has logged in
			driver.findElement(By.linkText("Logout")).click();
			// Close the browser at end
		}
		// closing DB Connection
		con.close();
	}

}
