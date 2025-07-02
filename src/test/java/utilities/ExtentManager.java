package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getExtent(){
        return extent;
    }
    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance("Report/Automation-Report.html");
        }
        return extent;
    }

    public static void createInstance(String filePath) {
        ExtentSparkReporter reporter = new ExtentSparkReporter(filePath);
        reporter.config().setTheme(Theme.STANDARD);
        reporter.config().setDocumentTitle("Automation Test Report");
        reporter.config().setReportName("Test Execution Report");

        extent = new ExtentReports();
        extent.attachReporter(reporter);

        // Optional system info
        extent.setSystemInfo("OS", System.getProperty("Windows"));
        extent.setSystemInfo("System Under Test", System.getProperty("Takealot"));
        extent.setSystemInfo("Tester", "Solly");

    }

}
