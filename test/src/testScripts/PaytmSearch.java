import org.testng.annotations.Test;
import pageObjects.PaytmHomepage;
import testBase.TestBase;


public class PaytmSearch extends TestBase {

    @Test
    public void search(){
        // Create a pNode instance
        pNode = extent.createTest("QAV site", "Verify HomePage");

        PaytmHomepage.init(pNode)
                .goToDealsPage("Clicking on Deals","Deals Button");

        pNode.pass(driver.getTitle() +" contain "+"Amazon");

    }
}
