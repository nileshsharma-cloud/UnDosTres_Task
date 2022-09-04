package utilities;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * This method will create report.
 * 
 * @author Nilesh Sharma
 */
public class Reports {
	public static ExtentReports extent;
	public static ExtentTest test;
	Logger logger = Logger.getLogger(Log.class.getName());

	public static ExtentReports getInstance() throws UnknownHostException {

		if (extent == null) {
			Date date = new Date();
			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +File.separator + "reports" +File.separator+ "extent_"+ date.toString().replace(":", "_").replace(" ", "_") + ".html");
			extent = new ExtentReports();
			htmlReporter.loadXMLConfig(System.getProperty("user.dir") + File.separator + "src"
					+ File.separator +"main" + File.separator + "java" + File.separator + File.separator+"config"+File.separator+"ExtentReportsConfiguration.xml");
			
			// attach only HtmlReporter
			extent.setSystemInfo("OS", System.getProperty("os.name"));
	        extent.setSystemInfo("Browser", BaseUtilities.prop.getProperty("Browser"));
	        extent.setSystemInfo("Host Name", InetAddress.getLocalHost().getHostName());
	        extent.setSystemInfo("Environment", "Local");
	        extent.setSystemInfo("User Name", "Nilesh Sharma");
	        
	        //configuration items to change the look and feel
	        //add content, manage tests etc
	        htmlReporter.config().setChartVisibilityOnOpen(true);
	        htmlReporter.config().setDocumentTitle("UnDos Tres");
	        htmlReporter.config().setReportName("UnDos Tres Automation");
	        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
	        htmlReporter.config().setTheme(Theme.STANDARD);
			extent.attachReporter(htmlReporter);
		}
		return extent;
	}

	/**
	 * This method will show logs
	 * 
	 * @param status
	 * @param details
	 */
	public void log(boolean condition, String passMessage, String failMessage) {
		if (condition) {
			assertTrue(condition, passMessage);
			test.log(Status.PASS, "EXPECTED - "+passMessage);
			Reporter.log("EXPECTED - "+passMessage);
			logger.info(passMessage);
		} else {
			test.log(Status.FAIL, "ACTUAL - "+failMessage);
			Reporter.log("ACTUAL - "+failMessage);
			logger.info(failMessage);
		}
	}
	
	public void info(String message) {
		test.log(Status.INFO, message);
		logger.info(message);
	}
}