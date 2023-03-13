package assignments;

import org.testng.annotations.DataProvider;

public class WebOrder_TestData {
	@DataProvider(name = "Weborder_Login")
	public Object[][] LoginTest() {
		// Multidimensional Object
		// 3X3 or 4X3 or 4X5
		return new Object[][] {

				{ "Tester", "test", "List of All Orders" }, 
				
				};
				
	}
				@DataProvider(name = "Weborder_CreateOrder")
				public Object[][] CreateWebOrder() {
					// Multidimensional Object
					// 3X3 or 4X3 or 4X5
					return new Object[][] {

							{ "MyMoney","2","10","1","Santosh","Pune","Mumbai","Maharashatra","123434","1234234","09/12","http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Process.aspx" }, 
							
							};


}}
