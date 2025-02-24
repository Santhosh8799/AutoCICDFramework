package Santhosh.TestComponent;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Santhosh.Resource.ExtentReportNG;

public class Listeners extends BaseTest implements ITestListener {

    ExtentReports extent = ExtentReportNG.getReports();
    ThreadLocal<ExtentTest> thread = new ThreadLocal<>(); // Thread Safe - During parallel Execution Failed test Status was reporting to another Test

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        thread.set(test); // Set the ExtentTest object in ThreadLocal
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        if (thread.get() != null) {
            thread.get().log(Status.PASS, "Test Passed");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (thread.get() != null) {
            thread.get().fail(result.getThrowable());

            try {
                WebDriver driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
                        .get(result.getInstance());
                String path = getScreenshot(result.getMethod().getMethodName(), driver);
                thread.get().addScreenCaptureFromPath(path, result.getMethod().getMethodName());
            } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
