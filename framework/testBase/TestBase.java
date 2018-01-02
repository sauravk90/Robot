package main.testBase;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import main.utilities.Constants;
import org.apache.log4j.*;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase extends Driver {

    public static ExtentReports extent;
    public static ExtentTest test;
    public ITestResult result;
    public Properties OR;
    public File f1;
    public FileInputStream file;
    public static final Logger logger = Logger.getLogger(TestBase.class.getName());

    static {
        Calendar calender = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        extent = new ExtentReports(System.getProperty("user.dir")+"/src/main/report/test" + formater.format(calender.getTime())+".html" , false);
    }

    @BeforeSuite
    @Parameters({"browser"})
    public void setUp(String browser) throws TestException {

        Driver.Initialize(browser);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.navigate().to(Constants.URL);
        logger.info(browser + " browser initialized!");
    }

    @BeforeMethod
    public void beforeMethod(Method result){
        test = extent.startTest(result.getName());
        test.log(LogStatus.INFO, " test started");
        loadPropertiesFile();
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        getResult(result);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass(){
        extent.endTest(test);
        extent.flush();
    }

    @AfterSuite
    public void tearDown(){
        driver.quit();
        logger.info("Test Execution was completed.");
    }

    public void getResult(ITestResult result){
        if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(LogStatus.PASS, result.getName(), "Test is passed!");
        }
            else if(result.getStatus() == ITestResult.SKIP){
                test.log(LogStatus.SKIP, result.getName(), "Test is skipped with reason " + result.getThrowable());
            }
        else if(result.getStatus() == ITestResult.FAILURE){
            test.log(LogStatus.FAIL, result.getName(), "Test failed! " + result.getThrowable());
            // Screenshot code to be added here
        }
        else if(result.getStatus() == ITestResult.STARTED){
            test.log(LogStatus.INFO, result.getName(), "Test is started");
        }
    }

    public void loadPropertiesFile(){
        String log4jConfPath = System.getProperty("user.dir") + "/src/main/config/log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);
        logger.info("log4j configurations successfully loaded..");

    }

}


