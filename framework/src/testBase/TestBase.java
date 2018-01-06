package testBase;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import utility.globalConst.ConfigInput;
import org.apache.log4j.*;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utility.reportManager.ExtentManager;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static utility.globalConst.FilePath.resourcesPath;

public class TestBase extends DriverFactory {

    public static ExtentReports extent;
    public static ExtentTest pNode;
    public static final Logger logger = Logger.getLogger(TestBase.class.getName());

    @BeforeSuite(alwaysRun = true)
    @Parameters({"browser"})
    public void setUp(String browser) throws TestException {

        // load the automation.properties file
        ConfigInput.init();
        extent = ExtentManager.getExtent();

        DriverFactory.getDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(ConfigInput.url);
        logger.info(browser + " browser initialized!");
    }

    @BeforeClass
    public void beforeClass(){
         pNode = extent.createTest(getClass().getSimpleName());
         //pNode.log(Log, " pNode started");
        loadPropertiesFile();
    }

    @BeforeMethod
    public void beforeMethod(Method result){
        //pNode = extent.startTest(result.getName());
        //pNode.log(LogStatus.INFO, " pNode started");
        //loadPropertiesFile();
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        getResult(result);
        extent.flush();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass(){
        //extent.endTest(pNode);
        //extent.flush();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown(){
        driver.quit();
        logger.info("Test Execution was completed.");
    }

    public void getResult(ITestResult result){
        if (result.getStatus() == ITestResult.SUCCESS) {
            pNode.log(Status.PASS, result.getName());
        }
            else if(result.getStatus() == ITestResult.SKIP){
                pNode.log(Status.SKIP, "Test is skipped with reason " + result.getThrowable());
            }
        else if(result.getStatus() == ITestResult.FAILURE){
            pNode.log(Status.FAIL, result.getThrowable());
            // Screenshot code to be added here
        }
        else if(result.getStatus() == ITestResult.STARTED){
             pNode.log(Status.INFO, result.getName());
        }
        System.out.print("\nEND TEST: " + result.getName());
    }

    public void loadPropertiesFile(){
        String log4jConfPath = resourcesPath + "\\log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);
        logger.info("log4j configurations successfully loaded..");

    }

}


