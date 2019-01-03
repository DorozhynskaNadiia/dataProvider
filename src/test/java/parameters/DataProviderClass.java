package parameters;

import org.testng.annotations.DataProvider;

public class DataProviderClass {
    @DataProvider(name="SearchProvider")
    public static Object[][] getDataFromDataprovider(){
        return new Object[][] {
                { "111", "111" },
                { "222", "222" },
                { "333", "333" }
        };
    }
}
