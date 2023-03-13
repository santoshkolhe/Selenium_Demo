package pk_Assignments;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.github.bonigarcia.wdm.WebDriverManager;

import pk_SeleniumProject.BaseClass;

public class Assignment5 {

	WebDriver driver;
	WebDriverWait wait;
	String orderno;
	int price,qty=2;
	BaseClass object = new BaseClass();

	@Test(description = "Login into application")
	public void login() {
		// Go to URL

		test = extent.createTest("Test Case 1", "Login to Application");
		driver.findElement(By.id("username")).sendKeys("Dimpal@shoptools.com");
		driver.findElement(By.id("password")).sendKeys("Dimpal@123");
		driver.findElement(By.name("login")).click();
		String ActResult = driver.findElement(By.xpath("//h1[normalize-space()='My Account']")).getText();
		String ExpResult = "MY ACCOUNT";
		Assert.assertEquals(ActResult, ExpResult);
		System.out.println("User is Logged In!!");

	}

	@Test(dependsOnMethods = { "login" }, description = "Verify Cart is Empty", enabled = true)
	public void checkCartIsEmpty(){
		
		test = extent.createTest("Test Case 2", "Verify Cart is Empty");
		wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[@class='icon_bag_alt']")));
		driver.findElement(By.xpath("//i[@class='icon_bag_alt']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//p[normalize-space()='Your cart is currently empty.']")).isDisplayed());
		driver.findElement(By.xpath("//a[normalize-space()='Dismiss']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='My Account']")).click();
	}
	
	@Test(dependsOnMethods = { "checkCartIsEmpty" }, description = "Browse product and Add to cart", enabled = true)
	public void browseAndAddToCart() {

		test = extent.createTest("Test Case 3", "Browse product and Add to cart");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Orders']")));
		driver.findElement(By.xpath("//a[normalize-space()='Orders']")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Browse products']")));
		driver.findElement(By.xpath("//a[normalize-space()='Browse products']")).click();

		String ActResult = driver.getTitle();
		String ExpResult = "Products – ToolsQA Demo Site";
		Assert.assertEquals(ActResult, ExpResult);

		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[@class='noo-product-inner']//a[normalize-space()='black lux graphic t-shirt']")));
		driver.findElement(
				By.xpath("//div[@class='noo-product-inner']//a[normalize-space()='black lux graphic t-shirt']"))
				.click();

		String ActTitle = driver.getTitle();
		String ExpTitle = "black lux graphic t-shirt – ToolsQA Demo Site";
		Assert.assertEquals(ActTitle, ExpTitle);

		String ActURL = driver.getCurrentUrl();
		String ExpURL = "https://shop.demoqa.com/product/black-lux-graphic-t-shirt/";
		Assert.assertEquals(ActURL, ExpURL);

		String ActText = driver.findElement(By.xpath("//h1[@class='product_title entry-title']")).getText();
		String ExpText = "BLACK LUX GRAPHIC T-SHIRT";
		Assert.assertEquals(ActText, ExpText);

		Assert.assertTrue(driver.findElement(By.xpath("//button[normalize-space()='Add to cart']"))
				.getAttribute("class").contains("disabled"));
		
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='single-product-content']//bdi[1]")));

		String priceText = driver.findElement(By.xpath("//div[@class='single-product-content']//bdi[1]")).getText();
		//System.out.println(priceText);
		
		StringBuffer p = new StringBuffer(priceText);
		p.delete(0,1);
		//System.out.println(p);
		String priceString = p.toString();
		price = Double.valueOf(priceString).intValue();
		
		
		Select color = new Select(driver.findElement(By.id("pa_color")));
		color.selectByVisibleText("Black");

		Select size = new Select(driver.findElement(By.id("pa_size")));
		size.selectByVisibleText("34");

		Assert.assertTrue(driver.findElement(By.xpath("//button[normalize-space()='Add to cart']")).isEnabled());

		driver.findElement(By.xpath("//button[normalize-space()='Add to cart']")).click();

		String ActMsg = driver.findElement(By.xpath("//div[@role='alert']")).getText();
		System.out.println(ActMsg);

	}

	@Test(dependsOnMethods = {
			"browseAndAddToCart" }, description = "View Cart - Update Product Quantity - Verify Total Price ", enabled = true)
	public void viewCart() {
		
		test = extent.createTest("Test Case 4", "View Cart - Update Product Quantity - Verify Total Price");

		driver.findElement(By.xpath("//a[normalize-space()='View cart']")).click();

		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[normalize-space()='Cart']")));
		String ActText = driver.findElement(By.xpath("//h1[normalize-space()='Cart']")).getText();
		String ExpText = "CART";
		Assert.assertEquals(ActText, ExpText);

		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//a[normalize-space()='black lux graphic t-shirt - Black']")));
		String ActProduct = driver.findElement(By.xpath("//a[normalize-space()='black lux graphic t-shirt - Black']"))
				.getText();
		String ExpProduct = "BLACK LUX GRAPHIC T-SHIRT - BLACK";
		Assert.assertEquals(ActProduct, ExpProduct);
        /*
		driver.findElement(By.xpath(
				"//a[normalize-space()='black lux graphic t-shirt - Black']//parent::td//following-sibling::td[2]//button[@class='qty-increase']"))
				.click();
        */
		String strQty = Integer.toString(qty);
		driver.findElement(By.xpath("//input[@class='input-text qty text']")).clear();
		driver.findElement(By.xpath("//input[@class='input-text qty text']")).sendKeys(strQty);
		driver.findElement(By.xpath("//input[@name='update_cart']")).click();
		

		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//a[normalize-space()='black lux graphic t-shirt - Black']")));
		
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='woocommerce-Price-amount amount']")));

		String totalText = element.getAttribute("textContent");
		System.out.println("TotalText= "+totalText);
		
		StringBuffer t = new StringBuffer(totalText);
		t.delete(0,1);
		System.out.println(t);
		String totalString= t.toString();
		int actTotal = Double.valueOf(totalString).intValue();
		
		int expTotal = price * Integer.valueOf(qty);
		
		if (actTotal== expTotal)
		{
			System.out.println("Price * Quantity is equal to Total.");
		}
		
		else
		{
			System.out.println("Price * Quantity is not equal to Total.");
		}

	}

	@Test(dependsOnMethods = { "viewCart" }, description = "Logout from application", enabled = true)

	public void logout() {
		
		test = extent.createTest("Test Case 5", "Logout from application");	
		driver.findElement(By.xpath("//a[@class='icon_close_alt2']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//p[normalize-space()='Your cart is currently empty.']")).isDisplayed());
		driver.findElement(By.xpath("//a[normalize-space()='My Account']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Log out']")).click();	
		String ActText = driver.findElement(By.xpath("//h2[normalize-space()='Login']")).getText();
		String ExpText = "LOGIN";
		Assert.assertEquals(ActText, ExpText);
		System.out.println("User is Logged Out!!");
	}

	@BeforeTest
	@Parameters("browser")
		
	public void preCondition(String browser) throws Exception {

		LaunchBrowser(browser);
		driver.get("https://shop.demoqa.com/my-account/");
		driver.manage().window().maximize();

	}
	/*
	@BeforeTest
	public void LaunchBrowser() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://shop.demoqa.com/my-account/");
		driver.manage().window().maximize();
	}
	*/
	
	@AfterMethod
	public void CaptureScreenShot(ITestResult result) throws Exception {
		if (ITestResult.FAILURE == result.getStatus()) {
			BaseClass.getScreenshotfailure(driver, result.getName());

		} else if (ITestResult.SUCCESS == result.getStatus()) {
			BaseClass.getScreenshotSuccess(driver, result.getName());
		}

	}
	
	public void getResult(ITestResult result) throws Exception {
		if(result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
			test.fail(result.getThrowable());
			String screenshotPath = BaseClass.getScreenshotfailure(driver, result.getName());
			//To add it in the extent report 
			test.addScreenCaptureFromPath(screenshotPath);//This is for Screenshot
			
		}
		else if(result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" PASSED ", ExtentColor.GREEN));
			//String screenshotPath = BaseClass.getScreenhot(driver, result.getName());
			//To add it in the extent report 
			//test.addScreenCaptureFromPath(screenshotPath);//This is for Screenshot
		}
		else {
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" SKIPPED ", ExtentColor.ORANGE));
			test.skip(result.getThrowable());
		}
	}
	
	@BeforeClass()
	public void CallingReport() {
		BaseClass.startReport();
	}


	@AfterTest
	
	public void postCondition() {

		CloseBrowser();
		//extent.flush();
	}

}
