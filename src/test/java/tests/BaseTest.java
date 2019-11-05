package tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    static WebDriver driver;

    @BeforeClass
    public static void driver() {
        System.setProperty("webdriver.chrome.driver", "/Users/nyusupova/Downloads/chromedriver2");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void closeBrowser() {
      //  driver.quit();
    }

}
