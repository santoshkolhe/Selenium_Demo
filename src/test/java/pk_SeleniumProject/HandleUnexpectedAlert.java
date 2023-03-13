package pk_SeleniumProject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HandleUnexpectedAlert {
	WebDriver driver = null;

	 @BeforeTest
	 public void setup() throws Exception {
	  WebDriverManager.edgedriver().setup();
	  driver = new EdgeDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  driver.get("http://only-testing-blog.blogspot.com/2014/06/alert_6.html");
	  //Thread.sleep(5000);
	 }

	 @AfterTest
	 public void tearDown() throws Exception {
	  driver.quit();
	 }

	 @Test
	 public void HandleAlert() throws InterruptedException {
	  //To handle unexpected alert on page load.
	  try{   
	   driver.switchTo().alert().dismiss();  
	  }catch(Exception e){ 
	   System.out.println("unexpected alert not present");   
	  }
	  
	  driver.findElement(By.xpath("//input[@name='fname']")).sendKeys("Abhi");
	  driver.findElement(By.xpath("//input[@name='lname']")).sendKeys("Dixit");
	  driver.findElement(By.xpath("//input[@type='submit']")).click();
	 }

}
