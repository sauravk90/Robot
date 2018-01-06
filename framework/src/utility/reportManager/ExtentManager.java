package utility.reportManager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import static utility.globalConst.FilePath.basePath;
import static utility.globalConst.FilePath.testBasePath;

/**
 * Created by Saurav PC on 06-01-2018.
 */
public class ExtentManager {

    public static ExtentReports extent;
    public static ExtentTest test;
    public static ExtentHtmlReporter htmlReporter;
    private static String filePath = testBasePath + "\\report\\extentreport.html";

    public static ExtentReports getExtent(){
        if (extent != null)
            return extent; //avoid creating new instances of html file
        else
        {extent = new ExtentReports();
         extent.attachReporter(getHtmlReporter());}

        return extent;
    }

    private static ExtentHtmlReporter getHtmlReporter() {

        htmlReporter = new ExtentHtmlReporter(filePath);

        // make the charts visible on report open
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("QAV automation report");
        htmlReporter.config().setReportName("Regression cycle");
        return htmlReporter;
    }

    public static ExtentTest createTest(String name, String description){
        test = extent.createTest(name, description);
        return test;
    }

    public static void quitExtent() {
        extent.flush();
        extent = null;
    }

}
