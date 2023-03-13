package assignments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class HandlingAlerts {
	WebDriver driver = null;

	@BeforeTest
	public void setup() throws Exception {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://the-internet.herokuapp.com/javascript_alerts");
		// Thread.sleep(5000);
	}

	@AfterTest
	public void closeBrowser() throws Exception {
		 driver.quit();
	}

	@Test(priority=1)
	public void click_for_js_alert() throws InterruptedException {

		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		try {
			String Actalert = driver.switchTo().alert().getText();
			String ExpAlt = "I am a JS Alert";
			driver.switchTo().alert().accept();
			Assert.assertEquals(Actalert, ExpAlt);
			System.out.println(Actalert);
			
			String successMsg= "You successfully clicked an alert";
			String ExpMsg=driver.findElement(By.xpath("//p[text()='You successfully clicked an alert']")).getText();
			Assert.assertEquals(ExpMsg, successMsg);
			System.out.println(ExpMsg);
		} catch (Exception e) {
			System.out.println("unexpected alert not present");
		}

	}

	@Test(priority=2)
	public void click_for_js_confirm() throws InterruptedException {

		driver.findElement(By.xpath("//button[normalize-space()='Click for JS Confirm']")).click();
		try {
			String Actalert = driver.switchTo().alert().getText();
			String ExpMsg = "I am a JS Confirm";
			driver.switchTo().alert().dismiss();
			Assert.assertEquals(Actalert, ExpMsg);
			System.out.println(Actalert);
			
			String successMsg= "You clicked: Cancel";
			String ExpMsg2=driver.findElement(By.xpath("//p[@id='result']")).getText();
			Assert.assertEquals(ExpMsg2, successMsg);
			System.out.println(ExpMsg2);
		} catch (Exception e) {
			System.out.println("Unexpected alert not present");
		}
		
	}
	@Test(priority=3)
	public void click_for_js_prompt() throws InterruptedException {

		driver.findElement(By.xpath("//button[normalize-space()='Click for JS Prompt']")).click();
		try {
			String Actalert = driver.switchTo().alert().getText();
			String ExpAlt = "I am a JS prompt";
			driver.switchTo().alert().sendKeys("PromptAlert");
			driver.switchTo().alert().accept();
			Assert.assertEquals(Actalert, ExpAlt);
			System.out.println(Actalert);
			
			String successMsg= "You entered: PromptAlert";
			String ExpMsg3=driver.findElement(By.xpath("//p[@id='result']")).getText();
			Assert.assertEquals(ExpMsg3, successMsg);
			System.out.println(ExpMsg3);
		} catch (Exception e) {
			System.out.println("Unexpected alert not present");
	}
		
	}
}
