package pk_SeleniumProject;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CaptureScreenShot_of_Element {
	String filePath = System.getProperty("user.dir");

	String filepath_failure = filePath + "\\Screenshot_Failure";
  @Test
  public void ChromeBrowser() throws IOException {
	  
	    WebDriverManager.chromedriver().setup();
	    WebDriver driver = new ChromeDriver();
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx");
		WebElement Button = driver.findElement(By.id("ctl00_MainContent_login_button"));
		//Button.click();
	    File file = Button.getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(file,new File(filepath_failure+ "\\"+ "LoginButton.png"));
	    driver.close();
  }
}
