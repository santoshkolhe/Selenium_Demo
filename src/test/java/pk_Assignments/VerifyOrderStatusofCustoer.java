package pk_Assignments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pk_SeleniumProject.BaseClass;

public class VerifyOrderStatusofCustoer extends BaseClass{
  @Test
  public void Verify_the_status_of_Order_Status_for_customer() throws InterruptedException {
	  WebDriverManager.chromedriver().setup();
	  // Launch the browser (firefox or chrome) and maximize the browser.
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		//Go to URL 
		driver.get("https://admin-demo.nopcommerce.com/admin/ ");
		//Enter the valid user name
		driver.findElement(By.xpath("//input[@id='Email']")).clear();
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("admin@yourstore.com");
		//Enter the valid Password
		driver.findElement(By.xpath("//input[@id='Password']")).clear();
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("admin");
		//Go to Sales tab and Click on Orders link.
		driver.findElement(By.xpath("//button[text()='Log in']")).click();
		
		
		//Check the Expected Result
		driver.findElement(By.xpath("//p[normalize-space()='Sales']")).isDisplayed();
		//Click on Sales Tab
		driver.findElement(By.xpath("//p[normalize-space()='Sales']")).click();
		
		driver.findElement(By.xpath("//p[normalize-space()='Orders']")).click();
		Thread.sleep(3000);
		
		//Click on “View” for particular customer who is having “Pending” status
		driver.findElement(By.xpath("//td[normalize-space()='Pending']//following-sibling::td[7]")).click();
		
		//Click on “Change Status” and Select “Complete” from dropdown and click on “Save” button
		driver.findElement(By.xpath("//button[@id='btnChangeOrderStatus']")).click();
		
		Select selectOrder = new Select(driver.findElement(By.xpath("//select[@id='OrderStatusId']")));
		selectOrder.selectByVisibleText("Complete");
		//Click on Yes button of Confirmation
		driver.findElement(By.xpath("//button[@id='btnSaveOrderStatus']")).click();
		try{   
			   driver.switchTo().alert().accept();  
			  }catch(Exception e){ 
			   System.out.println("unexpected alert not present");   
			  }
		driver.navigate().back();
		//Click on “back to order list “ link verify that Order Status Change to “Complete” also verify color change to “green”
		String text=driver.findElement(By.xpath("//span[@class='grid-report-item green'] ")).getText();
		if (text.contains("green")) {
			System.out.println("Status Changed Successfully");
		}
		//Change the Status back to original Status called Pending
		
		driver.findElement(By.xpath("//td[normalize-space()='Pending']//following-sibling::td[7]")).click();
        driver.findElement(By.xpath("//button[@id='btnChangeOrderStatus']")).click();
		
		Select selectOrder2 = new Select(driver.findElement(By.xpath("//select[@id='OrderStatusId']")));
		selectOrder2.selectByVisibleText("Pending");
		// Logout of the application
		driver.findElement(By.xpath("//button[@id='btnSaveOrderStatus']")).click();
		try{   
			   driver.switchTo().alert().accept();  
			  }catch(Exception e){ 
			   System.out.println("unexpected alert not present");   
			  }
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		
		//driver.quit();
		}
  }

