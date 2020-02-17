package test.java.globalFunctions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

// Extent report for reporting the Execution results and logs of the testcases
public class extentReport
{

        // Creating the htmlreporter and binding the path of the html report.
        public ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("reports/sampleReport.html");
        //Creating an instance for the report
        public ExtentReports extent = new ExtentReports();
        // attaching the instance with the html reporter
}