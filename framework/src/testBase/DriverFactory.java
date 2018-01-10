package testBase;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.propertiesManager.AutProperties;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class DriverFactory {

    //WebDriver declaration
    public static WebDriver driver;

    //Supported Browsers
    private static final String SAFARI = "safari";
    private static final String FIREFOX = "firefox";
    private static final String CHROME = "chrome";

    public static final Logger logger = Logger.getLogger(DriverFactory.class.getName());

    public static WebDriver getDriver() {

        if(driver == null){
            driver = createDriver();
            driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }

        return driver;
    }

    public static WebDriver createDriver(){
        String driverType = AutProperties.getInstance().getProperty("browser.name");

        if(driverType.equalsIgnoreCase(CHROME))
                InitializeChrome();

        else if (driverType.equalsIgnoreCase(FIREFOX))
                InitializeFireFox();

        else if (driverType.equalsIgnoreCase(SAFARI))
                InitializeChrome();         //TODO : Safari code here

        else
            throw new TestException("Invalid browser type provided in automation.properties");

        return driver;
        }

    private static WebDriver InitializeChrome()
    {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/../framework/src/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        return driver;
    }

    private static WebDriver InitializeFireFox()
    {
        System.setProperty("webdriver.gecko.driver","C:");
        driver = new FirefoxDriver();
        return driver;
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

    public static void sendKey(WebElement element, String text){
        element.sendKeys(text);
        logger.info("Sent text to : " + element.toString() + ", text : " + text);

    }


}
