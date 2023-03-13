package assignments;

import org.testng.annotations.DataProvider;

public class Spree_CreateAddress_TestData {
	@DataProvider(name = "SpreeCreateAddress")
	  public Object[][] getSpreeCreateAddress() {
	    return new Object[][] {
	    	{"Home27", "Sonu", "Sharma", "1023 Road", "Redwood", "redwood","California","94061","(630) 880-7476"},
	    	{"Home24", "Santosh", "kolhe", "11 Road", "Redwood", "redwood","New York","94062","(630) 880-7477"}
	    };
	  }
}
