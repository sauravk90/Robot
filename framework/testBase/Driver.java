package main.testBase;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Driver {

    //Global WebDriver declaration
    public static WebDriver driver;
    public static final Logger logger = Logger.getLogger(Driver.class.getName());

    public static void Initialize(String driverType)
    {
        switch (DriverType.valueOf(driverType))
        {
            case Chrome:
                InitializeChrome();
                break;

            case Firefox:
                InitializeFireFox();
                break;

            default:
                InitializeChrome();
                break;
        }
    }

    private static void InitializeChrome()
    {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/drivers/chromedriver.exe");
        driver = new ChromeDriver();
    }

    private static void InitializeFireFox()
    {
        System.setProperty("webdriver.gecko.driver","C:");
        driver = new FirefoxDriver();
    }

    public enum DriverType
    {
        InternetExplorer,
        Chrome,
        Firefox
    }

    public static void waitForElement(long time, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public static void waitForElementWithPolling(long time, int pollTime, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.pollingEvery(pollTime, TimeUnit.SECONDS);
        wait.ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void clickElement(WebElement element){
        element.click();
        logger.info("Clicked on element : " + element.toString());

    }


}
