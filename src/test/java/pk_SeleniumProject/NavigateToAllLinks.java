package pk_SeleniumProject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NavigateToAllLinks {
	@Test
	public void Navigate_To_AllLinks() {
		
		String[] textArray = new String[] { "Images", "Gmail", "Sign in", "Google apps", "Google Images", "Privacy",
				"Terms", "Settings", "Advertising", "Business", "About", "Search settings", "हिन्दी", "বাংলা",
				"  How Search works " };

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://www.google.co.in/");

		List<WebElement> linksize = driver.findElements(By.tagName("a"));

		int linksCount = linksize.size();
		System.out.println("Total no of links Available:" + linksCount);
		//The Array declaration is of two types, either we can specify the size of the Array
		//or without specifying the size of the Array. A String Array can be declared as follows:
		//String[] stringArray1   //Declaration of the String Array without specifying the size  
		//String[] stringArray2 = new String[2];   //Declarartion by specifying the size
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
			driver.navigate().to(links[i]);
			System.out.println(driver.getTitle());
			System.out.println(driver.getCurrentUrl());
			//driver.navigate().back();
			
		}
		driver.close();
	}

}
