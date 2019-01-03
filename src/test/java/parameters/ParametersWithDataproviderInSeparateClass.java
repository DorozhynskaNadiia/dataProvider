package parameters;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ParametersWithDataproviderInSeparateClass {
    WebDriver driver;
    String driverPath = "C:\\Users\\ndorozhynska\\dataProvider\\src\\main\\resources\\chromedriver.exe";

    @BeforeTest
    public void setup(){
        System.setProperty("webdriver.firefox.marionette", driverPath);
        driver = new ChromeDriver();
        driver.get("https://google.com");
    }

    @Test(dataProvider="SearchProvider",dataProviderClass= DataProviderClass.class)
    public void testMethod(String author,String searchKey) throws InterruptedException{

        WebElement searchText = driver.findElement(By.name("q"));
        searchText.sendKeys(searchKey);
        System.out.println("Welcome ->"+author+" Your search key is->"+searchKey);
        Thread.sleep(3000);
        String testValue = searchText.getAttribute("value");
        System.out.println(testValue +"::::"+searchKey);
        searchText.clear();
        Assert.assertTrue(testValue.equalsIgnoreCase(searchKey));
    }
}
