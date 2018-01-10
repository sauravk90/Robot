package pageObjects;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import testBase.DriverFactory;

public class PaytmHomepage extends PageInit {
    private WebDriver driver;


    @FindBy(xpath = "//a[contains(text(),\"Today's Deals\")]")
    public WebElement dealsText;

    public PaytmHomepage goToDealsPage(String text, String eleName){
        clickOnElement(dealsText,eleName );
        return this;
    }

    public PaytmHomepage(ExtentTest t1) {
        super(t1);
    }

    public static PaytmHomepage init(ExtentTest test) {
        return new PaytmHomepage(test);
    }
}


