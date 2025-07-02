package utilities;

import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Page;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.nio.file.Paths;

public class TestListener implements ITestListener {


    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        ExtentTestManager.getTest().fail(result.getThrowable().getMessage()).addScreenCaptureFromBase64String(CommonFunctions.captureScreenshotBase64(),"Failure Screenshot");

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTestManager.getTest().skip("Test skipped");
    }

    @Override
    public void onFinish(ITestContext context) {

    }
}
