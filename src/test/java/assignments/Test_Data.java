package assignments;

import org.testng.annotations.DataProvider;

public class Test_Data {
	// For Create_Order_All_Scenarios
		@DataProvider(name = "Weborder_CreateOrder_Scenario")
		public Object[][] OrderValidation() {
			
			return new Object[][] {

			{ "","Pranali","Koparkhairane","Navi Mumbai","400706","Visa","987698743","09/12","Quantity must be greater than zero."},
			{ "1","","Koparkhairane","Navi Mumbai","400706","Visa","987698743","09/12","Field 'Customer name' cannot be empty."},
			{ "1","Pranali","","Navi Mumbai","400706","Visa","987698743","09/12","Field 'Street' cannot be empty."},
			{ "1","Pranali","Koparkhairane","","400706","Visa","987698743","09/12","Field 'City' cannot be empty."},
			{ "1","Pranali","Koparkhairane","Navi Mumbai","","Visa","987698743","09/12","Field 'Zip' cannot be empty."},
			
			{ "1","Pranali","Koparkhairane","Navi Mumbai","400706","Visa","","09/12","Field 'Card Nr' cannot be empty."},
			{ "1","Pranali","Koparkhairane","Navi Mumbai","400706","Visa","987698743","","Field 'Expire date' cannot be empty."},
			
		};
	}

}
