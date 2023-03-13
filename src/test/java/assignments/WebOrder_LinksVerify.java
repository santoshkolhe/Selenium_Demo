package assignments;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebOrder_LinksVerify {
	WebDriver driver;
	@Test(priority = 1)
	public void login() {
		driver.findElement(By.name("ctl00$MainContent$username")).sendKeys("Tester");
		driver.findElement(By.name("ctl00$MainContent$password")).sendKeys("test");
		driver.findElement(By.name("ctl00$MainContent$login_button")).click();
		}

	@Test(priority = 2)
	public void navigateToAllLinks() {

		String[] textArray = new String[] { "View all orders", "View all products", "Order" };
		List<WebElement> linksize = driver.findElements(By.tagName("a"));
		int linksCount = linksize.size();
		System.out.println("Total no of links Available:" + linksCount);
		String[] links = new String[linksCount];
		System.out.println("List of links Available:");
		String a = new String();

		for (int i = 0; i < linksCount; i++) {
			a = linksize.get(i).getAttribute("href");
			if (a.contains(".aspx")) {
				links[i] = a;
				System.out.println("Text is : " + linksize.get(i).getText());
				System.out.println("links[" + i + "]" + links[i]);
				Assert.assertNotNull(links[i]);
			}
			for (int j = 0; j < textArray.length; j++) {
				if (linksize.get(i).getText().equals(textArray[j])) {
					System.out.println(linksize.get(i).getText() + "Matchs with " + textArray[j]);
					break;
					}
			}
		}
		// navigate to each Link on the webpage
		for (int i = 0; i < linksCount; i++) {
			if (links[i] != null) {
				driver.navigate().to(links[i]);
				System.out.println(driver.getTitle());
				System.out.println(driver.getCurrentUrl());
			}
		}
	}

	@BeforeTest
	public void launchBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx");
		driver.manage().window().maximize();
	}

	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}
 }

