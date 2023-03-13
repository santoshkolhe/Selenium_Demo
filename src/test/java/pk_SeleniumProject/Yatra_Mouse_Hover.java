package pk_SeleniumProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Yatra_Mouse_Hover {
	@Test
	public void MouseHoverYatra() throws InterruptedException
	{
		WebDriverManager.chromedriver().setup();
		// create Edge instance and maximize it
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get( "https://www.yatra.com/");
        Thread.sleep(3000);
        
		WebElement MyAccount = driver.findElement(By.xpath("//a[contains(text(),'My Account')]"));
		Actions action = new Actions(driver);
        action.moveToElement(MyAccount).perform();
        driver.findElement(By.id("signInBtn")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//p[text()='Welcome to Yatra!']")).isDisplayed();
		driver.quit();
	}

}
