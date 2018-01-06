import org.testng.annotations.Test;
import pageObjects.PaytmHomepage;
import testBase.TestBase;


public class PaytmSearch extends TestBase {

    @Test
    public void search(){
        // Create a pNode instance
        pNode = extent.createTest("QAVsite", "Verify HomePage");

        PaytmHomepage.init(pNode)
                .inputSearch("Python books", "Search Box");

        pNode.pass(driver.getTitle() +" contain "+"QA manual");

    }
}
