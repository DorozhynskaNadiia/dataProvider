package parameters;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class ParametersXMLTest {
    private String driverPath = "C:\\Users\\ndorozhynska\\dataProvider\\src\\main\\resources\\chromedriver.exe";
    private WebDriver driver;

    @Test
    @Parameters({"author","searchKey"})
    public void testParameterWithXML(String author, String searchKey) throws InterruptedException{

        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        Thread.sleep(5000);
        driver.get("https://google.com");

        WebElement searchText = driver.findElement(By.name("q"));
        searchText.sendKeys(searchKey);

        System.out.println("Welcome ->"+author+" Your search key is->"+searchKey);
        Thread.sleep(3000);
        System.out.println("Value in Google Search Box = "+searchText.getAttribute("value") +" ::: Value given by input = "+searchKey);
        AssertJUnit.assertTrue(searchText.getAttribute("value").equalsIgnoreCase(searchKey));

        driver.quit();
    }
}
