package pageObjects;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import testBase.DriverFactory;

public class PaytmHomepage extends PageInit {
    private WebDriver driver;


    @FindBy(xpath = "//*[@id=\"app\"]/div/div[2]/div[2]/div[2]/input")
    public WebElement searchBox;

    public PaytmHomepage inputSearch(String text, String eleName){
        setText(searchBox, text, eleName);
        return this;
    }

    public PaytmHomepage(ExtentTest t1) {
        super(t1);
    }

    public static PaytmHomepage init(ExtentTest test) {
        return new PaytmHomepage(test);
    }
}


