package config;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import utilities.BaseUtilities;

public class BrowserConfiguration extends BaseUtilities {

	public Logger logger = LogManager.getLogger(Log.class.getClass().getSimpleName());

	public WebDriver openBrowser(WebDriver driver, String browserName) {
		switch (browserName) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "resources"
			 		+ File.separator + "chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			Map<String, Object> prefs = new HashMap<String, Object>();
			options.addArguments("--start-maximized");
			prefs.put("download.default_directory",System.getProperty("user.dir") + File.separator + "src" +File.separator + "main"
					+ File.separator + "java" + File.separator + "downloads");
			options.setExperimentalOption("prefs", prefs);
			driver = new ChromeDriver(options);
			driver.manage().deleteAllCookies();
			driver.get(System.getProperty("URL"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			return driver;

		case "firefox":
			logger.info("Firefox Browser is launching-----");
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + System.getProperty("user.dir") + File.separator + "resources"
							+ File.separator + "geckodriver.exe");
			driver = new FirefoxDriver();
			driver.get(System.getProperty("URL"));
			driver.manage().window().maximize();
			break;
		}
		return driver;
	}
}
