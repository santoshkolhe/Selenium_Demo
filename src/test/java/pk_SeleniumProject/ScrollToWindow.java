package pk_SeleniumProject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ScrollToWindow {
	ChromeDriver driver;
    @Test
    public void ByPage() throws InterruptedException {
    	WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Launch the application		
        driver.get("https://stackoverflow.com/");
		Thread.sleep(2000);
        //This will scroll the web page till end.		
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(2000);
        driver.findElementByLinkText("About").isDisplayed();
        driver.findElementByLinkText("Explore the network").isDisplayed();
        Thread.sleep(2000);
        js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
        Thread.sleep(4000);
        driver.findElementByLinkText("Join the community").isDisplayed();
    }
}
