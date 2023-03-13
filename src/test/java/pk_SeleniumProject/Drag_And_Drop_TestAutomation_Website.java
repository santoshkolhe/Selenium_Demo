package pk_SeleniumProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Drag_And_Drop_TestAutomation_Website {
  @Test
 	public void DragDrop() throws InterruptedException {
		 
	    WebDriverManager.chromedriver().setup();
	    ChromeDriver driver = new ChromeDriver();
		// maximize browser
		driver.manage().window().maximize();
		// Open webpage
		driver.get("http://testautomationpractice.blogspot.com/");
		// Add 5 seconds wait
		Thread.sleep(5000);
		// Create object of actions class
		Actions act=new Actions(driver);
		// find element which we need to drag
		WebElement drag=driver.findElement(By.xpath("//h5[contains(text(),'Mary')]"));
		//WebElement drag=driver.findElement(By.id("draggable"));
		// find element which we need to drop
		WebElement drop=driver.findElement(By.id("trash"));
		// this will drag element to destination
		act.dragAndDrop(drag, drop).perform();
		 Thread.sleep(5000);
		//driver.quit();
	 
		}
}
