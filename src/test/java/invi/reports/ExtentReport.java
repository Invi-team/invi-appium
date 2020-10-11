package invi.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import org.junit.After;
import org.junit.Before;

public class ExtentReport {
    private ExtentHtmlReporter htmlReporter;
    private ExtentReports extent;

    @Before
    public void reportSetUp() {
        htmlReporter = new ExtentHtmlReporter("extent.html");

        // create ExtentReports and attach reporter(s)
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @After
    public void reportTeardown() {
        // calling flush writes everything to the log file
        extent.flush();
    }

    public ExtentHtmlReporter getHtmlReporter() {
        return htmlReporter;
    }

    public ExtentReports getExtent() {
        return extent;
    }
}
