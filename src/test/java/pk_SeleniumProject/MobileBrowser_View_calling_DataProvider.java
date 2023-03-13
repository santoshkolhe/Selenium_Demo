package pk_SeleniumProject;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MobileBrowser_View_calling_DataProvider extends DataProviderForMobile {
	ChromeDriver driver;

	  @Test(dataProvider="Mobile_Devices")
	  public void Mobile_Devices(int heigt, int width) {
		    WebDriverManager.chromedriver().setup();
		    driver = new ChromeDriver();
	        //Iphone Pro
	        Dimension d = new Dimension(heigt, width);
	        //driver.manage().window().maximize();
	        driver.manage().window().setSize(d);
	        driver.get("https://demo.spreecommerce.org/t/categories/women");
	        driver.quit();
	  }


	
}
