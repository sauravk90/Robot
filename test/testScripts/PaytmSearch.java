import org.testng.annotations.Test;
import pageObjects.PaytmHomepage;
import testBase.Driver;

import static testBase.Driver.driver;

public class PaytmSearch {

    @Test
    public void search(){
        PaytmHomepage homeObj = new PaytmHomepage(driver);
        System.out.println("object created");
        homeObj.searchBox.sendKeys("python books");
        Driver.clickElement(homeObj.searchBox);

    }
}
