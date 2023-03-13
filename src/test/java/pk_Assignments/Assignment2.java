package pk_Assignments;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import assignments.Test_Data;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Assignment2 {
	WebDriver driver;
	WebDriverWait wait;
	String orderno;

	@Test(description = "Login into application")
	public void login() {

		driver.findElement(By.xpath("//button[normalize-space()='Log in']")).click();
		String ActResult = driver.findElement(By.xpath("//h1[normalize-space()='Dashboard']")).getText();
		String ExpResult = "Dashboard";
		Assert.assertEquals(ActResult, ExpResult);
		System.out.println("User is Logged In!!");

	}

	@Test(dependsOnMethods = { "login" }, description = "Navigate to Sales Order Page", enabled = true)

	public void navigateToSalesOrderPage() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Sales']")));

		driver.findElement(By.xpath("//p[normalize-space()='Sales']")).click();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//a[@class='nav-link']//p[contains(text(),'Orders')]")));

		driver.findElement(By.xpath("//a[@class='nav-link']//p[contains(text(),'Orders')]")).click();
		String ActResult1 = driver.findElement(By.xpath("//h1[normalize-space()='Orders']")).getText();
		String ExpResult1 = "Orders";
		Assert.assertEquals(ActResult1, ExpResult1);

		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//tr[@class='odd']//span[@class='grid-report-item yellow'][normalize-space()='Pending']//parent::td//preceding-sibling::td[1]")));
		orderno = driver.findElement(By.xpath(
				"//tr[@class='odd']//span[@class='grid-report-item yellow'][normalize-space()='Pending']//parent::td//preceding-sibling::td[1]"))
				.getText();

	}

	@Test(dependsOnMethods = {"navigateToSalesOrderPage" }, dataProvider = "Different_Status_Scenario", dataProviderClass = Test_Data.class, description = "Change Order status to different values and verify colours", enabled = true)
	public void changeOrderStatus(String updateorderstatus, String ExpColor) {

		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//tr[@class='odd']/td[2][normalize-space()='3']//following-sibling::td[8]/a")));

		driver.findElement(
				By.xpath("//tr[@class='odd']/td[2][normalize-space()='" + orderno + "']//following-sibling::td[8]/a"))
				.click();
		String ActOrder = driver.findElement(By.xpath("//h1[@class='float-left']")).getText();
		String ExpOrder = "Edit order details - " + orderno + " back to order list";
		Assert.assertEquals(ActOrder, ExpOrder);

		driver.findElement(By.xpath("//button[@id='btnChangeOrderStatus']")).click();
		Select status = new Select(driver.findElement(By.id("OrderStatusId")));
		status.selectByVisibleText(updateorderstatus);
		driver.findElement(By.xpath("//button[@id='btnSaveOrderStatus']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//button[@id='btnSaveOrderStatus-action-confirmation-submit-button']")));
		driver.findElement(By.xpath("//button[@id='btnSaveOrderStatus-action-confirmation-submit-button']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='back to order list']")));
		driver.findElement(By.xpath("//a[normalize-space()='back to order list']")).click();

		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//tr[@class='odd']/td[2][normalize-space()='" + orderno + "']//following-sibling::td[1]/span")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//tr[@class='odd']/td[2][normalize-space()='" + orderno + "']//following-sibling::td[1]/span")));
		Assert.assertTrue(driver
				.findElement(By.xpath(
						"//tr[@class='odd']/td[2][normalize-space()='" + orderno + "']//following-sibling::td[1]/span"))
				.getAttribute("class").contains(ExpColor));

	}

	@Test(dependsOnMethods = {"changeOrderStatus" }, description = "Reset Order status back to Pending", enabled = true)
	public void resetOrderStatus() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"//tr[@class='odd']/td[2][normalize-space()='" + orderno + "']//following-sibling::td[1]/span")));
		String orderstatus = driver
				.findElement(By.xpath(
						"//tr[@class='odd']/td[2][normalize-space()='" + orderno + "']//following-sibling::td[1]/span"))
				.getAttribute("class");
		if (orderstatus.contains("green") || orderstatus.contains("blue") || orderstatus.contains("red")) {
			wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//tr[@class='odd']/td[2][normalize-space()='3']//following-sibling::td[8]/a")));
			driver.findElement(By.xpath("//tr[@class='odd']/td[2][normalize-space()='3']//following-sibling::td[8]/a"))
					.click();
			String ActOrder = driver.findElement(By.xpath("//h1[@class='float-left']")).getText();
			String ExpOrder = "Edit order details - " + orderno + " back to order list";
			Assert.assertEquals(ActOrder, ExpOrder);
			driver.findElement(By.xpath("//button[@id='btnChangeOrderStatus']")).click();
			Select status = new Select(driver.findElement(By.id("OrderStatusId")));
			status.selectByVisibleText("Pending");
			driver.findElement(By.xpath("//button[@id='btnSaveOrderStatus']")).click();
			wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//button[@id='btnSaveOrderStatus-action-confirmation-submit-button']")));
			driver.findElement(By.xpath("//button[@id='btnSaveOrderStatus-action-confirmation-submit-button']"))
					.click();
			wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='back to order list']")));
			driver.findElement(By.xpath("//a[normalize-space()='back to order list']")).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
					"//tr[@class='odd']/td[2][normalize-space()='" + orderno + "']//following-sibling::td[1]/span")));
			Assert.assertTrue(driver.findElement(By.xpath(
					"//tr[@class='odd']/td[2][normalize-space()='" + orderno + "']//following-sibling::td[1]/span"))
					.getAttribute("class").contains("yellow"));
		}

	}

	@Test(dependsOnMethods = { "resetOrderStatus" }, description = "Logout from application", enabled = true)

	public void logout() {
		driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();
		String ActTitle = driver.getTitle();
		String ExpTitle = "Your store. Login";
		Assert.assertEquals(ActTitle, ExpTitle);
		System.out.println("User is Logged Out!!");
	}

	@BeforeTest
	public void LaunchBrowser() {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://admin-demo.nopcommerce.com/login");
		driver.manage().window().maximize();
	}

	@AfterTest
	public void CloseBrowser() {
		// Close the Browser
		driver.quit();
	}

}
