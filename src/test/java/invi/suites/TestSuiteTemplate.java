package invi.suites;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import invi.utils.Date;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
})
public class TestSuiteTemplate {


    private static ExtentHtmlReporter htmlReporter;
    private static ExtentReports extent;

    @BeforeClass
    public static void suiteSetUp() {
        htmlReporter = new ExtentHtmlReporter("reports/" + Date.getDateToFileName() + "_all_test_suite.html");

        // create ExtentReports and attach reporter(s)
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @AfterClass
    public static void suiteTeardown() {
        // calling flush writes everything to the log file
        extent.flush();
    }

    public static ExtentReports getExtent() {
        return extent;
    }
}
