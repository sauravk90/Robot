package pageObjects;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import testBase.DriverFactory;

/**
 * Created by Saurav PC on 06-01-2018.
 */
public class PageInit {

    protected static WebDriver driver;
    private static ExtentTest pageInfo;
    private static WebDriverWait wait;

    public PageInit(ExtentTest t1) {
        pageInfo = t1;
        driver = DriverFactory.getDriver();
        wait =  new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    protected void setText(WebElement elem, String text, String elementName) {
        //pageInfo.("set " + elementName + "'s text as '" + text + "'");
        wait.until(ExpectedConditions.elementToBeClickable(elem)).clear();
        elem.sendKeys(text);
    }

    protected void selectVisibleText(WebElement elem, String text, String elementName) {
        Select sel = new Select(elem);
        sel.selectByVisibleText(text);
        //pageInfo.info("select from " + elementName + ". Selected text '" + text + "'");
    }

    protected void selectValue(WebElement elem, String value, String elementName) {
        Select sel = new Select(elem);
        sel.selectByVisibleText(value);
        //pageInfo.info("select from " + elementName + ". Selected value '" + value + "'");
    }

    protected void clickOnElement(WebElement elem, String elementName) {
        //pageInfo.info("Click on " + elementName);
        wait.until(ExpectedConditions.elementToBeClickable(elem)).click();
    }

    protected boolean isStringPresentInWebTable(WebElement table, String text) {
        if (table.findElements(By.xpath(".//tr/td[contains(text(), '" + text + "')]")).size() > 0)
            return true;
        else
            return false;
    }
}
