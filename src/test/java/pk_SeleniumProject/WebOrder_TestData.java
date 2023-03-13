package pk_SeleniumProject;
import org.testng.annotations.DataProvider;

public class WebOrder_TestData {
	/*
	 * @Test(dataProvider = "dp") public void f(Integer n, String s) { }
	 * 
	 * @DataProvider public Object[][] dp() { return new Object[][] { new Object[] {
	 * 1, "a" }, new Object[] { 2, "b" }, }; }
	 */
	@DataProvider(name = "WebOrderLogin")
	public Object[][] getSwagLabLogin() {
		// Multidimensional Object
		// 3X3 or 4X3 or 4X5
		return new Object[][] {

				{"Tester", "test"},
				{"test", "Tester"},
				{"Tester", "Tester"},
				{"test", "test"}
				};

	}
	@DataProvider(name = "LoginExcelData")
	public Object[][] ReadDataFromExcel() throws Exception{
		BaseClass excel = new BaseClass();
		String RelativePath = System.getProperty("user.dir");
		//Object[][] testObjArray = excel.getExcelData("C:\\Users\\adixit\\git\\abhikdixit-Maven_Selenium_WebDriver_4\\Maven_Selenium_WebDriver_4\\OrangeHRM_TestData.xls","Login");
		Object[][] testObjArray = excel.getExcelData(RelativePath+"\\TestData\\weborder.xls","Login");
		System.out.println(testObjArray);
		return testObjArray;

	}
	@DataProvider(name = "Weborder_Login_Scenario")
	public Object[][] LoginTcs() {
		// Multidimensional Object
		// 3X3 or 4X3 or 4X5
		return new Object[][] {

				{ "Tester", "test", "List of All Orders" }, 
				{ "Tester1", "test", "Invalid Login or Password." },
				{ "Tester", "test1", "Invalid Login or Password." },
				{ "", "test", "Invalid Login or Password." },
				{ "Tester", "", "Invalid Login or Password." },
				};

	}
}
