package assignments;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NavigateAllLinks {
	@Test
	public void NavigateToAllLinks1() throws InterruptedException {
		String[] textArray = new String[] { "View all orders", "View all products", "Order","Logout","Check All","Uncheck All" };

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx");
		driver.manage().window().maximize();
		driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
		driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test");
		driver.findElement(By.id("ctl00_MainContent_login_button")).click();
		Thread.sleep(3000);

		List<WebElement> linksize = driver.findElements(By.tagName("a"));

		int linksCount = linksize.size();
		System.out.println("Total no of links Available:" + linksCount);
		String[] links = new String[linksCount];
		System.out.println("List of links Available:");
		// print all the links from webpage
		for (int i = 0; i < linksCount; i++) {
			links[i] = linksize.get(i).getAttribute("href");
			//System.out.println(linksize.get(i).getAttribute("href"));
			System.out.println("Text is : " + linksize.get(i).getText());
			System.out.println("links[" + i + "]" + links[i]);
			Assert.assertNotNull(links[i]);

			for (int j = 0; j < textArray.length; j++) {
				if (linksize.get(i).getText().equals(textArray[j])) {
					System.out.println(linksize.get(i).getText() + "Matchs with " + textArray[j]);
					break;
				}
			}
		}
		// navigate to each Link on the webpage
		for (int i = 0; i < linksCount; i++) {
			if(!links[i].contains("javascript")) {
				driver.navigate().to(links[i]);
				System.out.println(driver.getTitle());
				System.out.println(driver.getCurrentUrl());
				//driver.navigate().back();
			}	else
			{
				System.out.println("Links Contain JavaScript");
			}
			
		}
		driver.close();
	}


}
