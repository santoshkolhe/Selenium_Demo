package assignments;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.IllegalFormatException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	// Below code is to read Excel file

	private static Sheet ExcelWSheet;
	private static Workbook ExcelWBook;

	// This method is to read the test data from the Excel
	public String[][] getExcelData(String fileName, String sheetName)
			throws EncryptedDocumentException, IOException, IllegalFormatException {
		String[][] arrayExcelData = null;
		FileInputStream ExcelFile = new FileInputStream(fileName);
		ExcelWBook = WorkbookFactory.create(ExcelFile);
		ExcelWSheet = ExcelWBook.getSheet(sheetName);
		// System.out.println(ExcelWSheet);
		int totalNoOfRows = ExcelWSheet.getLastRowNum();
		int totalNoOfCols_0 = ExcelWSheet.getRow(0).getLastCellNum();
		arrayExcelData = new String[totalNoOfRows][totalNoOfCols_0];
		for (int i = 0; i < totalNoOfRows; i++) {
			int totalNoOfCols = ExcelWSheet.getRow(i).getLastCellNum();
			// arrayExcelData = new String [totalNoOfRows][totalNoOfCols];
			for (int j = 0; j < totalNoOfCols; j++) {
				arrayExcelData[i][j] = ExcelWSheet.getRow(i + 1).getCell(j).getStringCellValue();
				// System.out.println(arrayExcelData[i][j]);
			}
		}
		System.out.println(arrayExcelData);
		return arrayExcelData;
	}

	// Read DB Function
	// Connection objectmy
	static Connection con = null;
	// Statement object
	private static Statement stmt;

	public ArrayList<String> ConnectMySQLDatabase(String DB_URL, String DB_USER, String DB_PASSWORD, String DB_QUERY)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		String dbClass = "com.mysql.cj.jdbc.Driver";
		Class.forName(dbClass);
		// Get connection to DB
		con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		// Statement object to send the SQL statement to the Database
		stmt = con.createStatement();
		String query = DB_QUERY;
		// Get the contents of userinfo table from DB
		ResultSet res = stmt.executeQuery(query);
		// Print the result untill all the records are printed
		// res.next() returns true if there is any next record else returns
		// false

		ArrayList<String> sqlData = new ArrayList<String>();
		while (res.next()) {
			System.out.print("\t" + res.getString("username"));
			System.out.println("\t" + res.getString("password"));
			sqlData.add(res.getString("username") + "~" + res.getString("password"));
			// Adminadmin123
		}

		// Close DB connection
		if (con != null) {
			con.close();
		}
		return sqlData;
	}

	public WebDriver driver;

	public void LaunchBrowser(String browser) throws Exception {

		if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.get("http://secure.smartbearsoftware.com/samples/testcomplete11/WebOrders/login.aspx");
		} else if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.get("http://secure.smartbearsoftware.com/samples/testcomplete11/WebOrders/login.aspx");
		} else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			driver.get("http://secure.smartbearsoftware.com/samples/testcomplete11/WebOrders/login.aspx");
		}

		else {

			throw new Exception("Browser is not correct");
		}

	}

	public void CloseBrowser() {

		driver.quit();
	}

	// public WebDriver driver;

	public void LaunchBrowserForWeborder(String browser) throws Exception {

		if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.get("http://secure.smartbearsoftware.com/samples/testcomplete11/WebOrders/login.aspx");
			driver.manage().window().maximize();
		} else if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.get("http://secure.smartbearsoftware.com/samples/testcomplete11/WebOrders/login.aspx");
			driver.manage().window().maximize();
		} else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			driver.get("http://secure.smartbearsoftware.com/samples/testcomplete11/WebOrders/login.aspx");
			driver.manage().window().maximize();
		}

		else {

			throw new Exception("Browser is not correct");
		}

	}

	public void CloseBrowserforWeborder() {

		driver.quit();
	}

}
