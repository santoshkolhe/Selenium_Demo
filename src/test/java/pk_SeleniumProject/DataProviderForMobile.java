package pk_SeleniumProject;


import org.testng.annotations.DataProvider;

public class DataProviderForMobile {
	@DataProvider(name = "Mobile_Devices")
	public Object[][] getDeviceDimension() {
		// Multidimensional Object
		// 3X3 or 4X3 or 4X5
		return new Object[][] {

				{ 768, 1024 }, { 390, 844 }, { 412, 914 }, { 360, 740 } };

	}
}
