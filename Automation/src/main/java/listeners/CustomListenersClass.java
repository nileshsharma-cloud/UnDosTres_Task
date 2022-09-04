package listeners;

import java.io.IOException;
import java.net.UnknownHostException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Log;
import utilities.BaseUtilities;
import utilities.Reports;
import utilities.ScreenshotGeneratorFile;

public class CustomListenersClass implements ITestListener, IInvokedMethodListener {

	public Logger logger;
	BaseUtilities baseUtilities = new BaseUtilities();

	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		Reports.test = Reports.extent.createTest(method.getTestMethod().getConstructorOrMethod().getName(),
				method.getTestMethod().getMethodName() + " : " + method.getTestMethod().getDescription());
		Reports.test.assignCategory("regression");
		logger.info("BEFORE INVOCATION METHOD STARTED");
		logger.info(method.getTestMethod().getMethodName() + " method started running----");
	}

	public void afterInvocation(IInvokedMethod method, ITestResult result) {
		logger.info("AFTER INVOCATION METHOD RUNNING");
		if (result.getStatus() == ITestResult.FAILURE) {
			Reports.test.log(Status.FAIL, "Test Case Failed is " + result.getName());
			Reports.test.log(Status.FAIL, "Test Case Failed is " + result.getThrowable());

			/**
			 * To capture screenshot path and store the path of the screenshot in the string
			 * "screenshotPath" We do pass the path captured by this method in to the extent
			 * reports using "logger.addScreenCapture" method.
			 */

			String screenshotPath = null;
			try {
				screenshotPath = ScreenshotGeneratorFile.getScreenshot(baseUtilities.getdriver(), result.getName());
			} catch (Exception e1) {
				Reports.test.fail(e1);
			}

			/**
			 * To add it in the extent report Reports.test.log(Status.FAIL,
			 * Reports.test.addScreenCaptureFromPath(screenshotPath));
			 */

			try {
				Reports.test.fail(result.getTestName()).addScreenCaptureFromPath(screenshotPath);
			} catch (IOException e) {
				Reports.test.fail(e);
			}
		} else if (result.getStatus() == ITestResult.SKIP) {
			Reports.test.log(Status.SKIP, "Test Case Skipped is " + result.getName());
		}

		logger.info("AFTER INVOCATION METHOD RUNNING");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		Reporter.log(result.getMethod().getDescription());
	}

	@Override
	public void onStart(ITestContext context) {
		try {
			baseUtilities.startUp();
		} catch (IOException e) {
			Reports.test.fail(e);
		}
		try {
			Reports.extent = Reports.getInstance();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		logger = LogManager.getLogger(CustomListenersClass.class);
		logger.info("Reports instance getting created successfully.");
	}

	@Override
	public void onFinish(ITestContext context) {
		Reports.extent.flush();
		logger.info("Results displayed.");
		baseUtilities.getdriver().quit();
	}
}
