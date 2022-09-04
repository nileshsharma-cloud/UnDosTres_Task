/**
 *
 */
package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.sikuli.script.SikuliException;

import config.BrowserConfiguration;

/**
 * @author Nilesh Sharma
 *
 */

public class BaseUtilities {

	public static WebDriver driver;
	public static Properties prop;
	public Logger logger = LogManager.getLogger(BaseUtilities.class);
	public Reports reports = new Reports();
	WebdriverWait webdriverWait = new WebdriverWait();
	
	
	public void startUp() throws IOException {
		
		prop = new Properties();
		
		BrowserConfiguration browserConfig = new BrowserConfiguration();
		FileInputStream fin = new FileInputStream(System.getProperty("user.dir") + File.separator + "src"
				+ File.separator +"main" + File.separator + "java" + File.separator +"properties" + File.separator + "Properties.properties");
		prop.load(fin);

		prop.load(new FileInputStream(System.getProperty("user.dir") + File.separator + "src"
				+ File.separator +"main" + File.separator + "java" + File.separator + "properties" + File.separator + "log4j.properties"));

		PropertyConfigurator.configure(prop);
		driver = browserConfig.openBrowser(driver,prop.getProperty("Browser"));
	}

	public WebDriver getdriver() {
		return driver;
	}
	
	public BaseUtilities clickElement(WebElement element) {
		try {
			webdriverWait.waitForElementToBeVisible(element);
			webdriverWait.waitForElementToBeClickable(element);
			element.click();
			logger.info("Web Element clicked is - " + element);
		} catch (Exception e) {
			Reports.test.fail(e);
		}
		return this;
	}

	public boolean verifyElementDisplayed(WebElement element) {
		boolean value = element.isDisplayed();
		logger.info(element+" is displayed.");
		return value;
	}
	
	public String getTextOfWebElement(WebElement element) {
		webdriverWait.waitForAnElement(element);
		logger.info("Text present on WebElement is "+element);
		return element.getText();
	}
	
	public List<WebElement> getElements(By elements){
		return driver.findElements(elements);
	}

	public BaseUtilities sendKeys(WebElement element, String passValues) {
		webdriverWait.waitForElementToBeVisible(element);
		webdriverWait.waitForElementToBeClickable(element);
		clickElementByJS(element);
		element.clear();
		element.sendKeys(passValues);
		return this;
	}

	public String randomWordsGenerator(int numberOfWords) {
		String generatedstring = RandomStringUtils.randomAlphabetic(numberOfWords);
		return (generatedstring);
	}

	public BaseUtilities selectElementByText(WebElement element, String value) {
		webdriverWait.waitForElementToBeVisible(element);
		webdriverWait.waitForElementToBeClickable(element);
		Select select = new Select(element);
		select.selectByVisibleText(value);
		return this;
	}
	
	public BaseUtilities selectElementByValue(WebElement element, String value) {
		webdriverWait.waitForElementToBeVisible(element);
		webdriverWait.waitForElementToBeClickable(element);
		Select select = new Select(element);
		select.selectByValue(value);
		return this;
	}

	public BaseUtilities clickElementByJS(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		return this;
	}

	public BaseUtilities scrollToTheElement(WebElement element) {

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView();", element);
		element.click();
		return this;
	}

	public BaseUtilities scrollingToTheElement(WebElement element) {

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView();", element);
		return this;
	}

	public BaseUtilities doubleClick(WebElement element) {
		Actions actions = new Actions(driver);
		WebElement elementLocator = element;
		actions.doubleClick(elementLocator).perform();
		return this;
	}

	public BaseUtilities scrollToBottomOfPage() {
		JavascriptExecutor js = (JavascriptExecutor) getdriver();
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		return this;
	}

	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	public String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}

	public String acceptAlert() {
		String alertText = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		logger.info("Alert accepted.");
		return alertText;
	}

	public int generateRandomnumbers() {
		Random rand = new Random();
		return rand.nextInt(100000);
	}

	public int getRandomInteger(int maximum, int minimum) {
		return ((int) (Math.random() * (maximum - minimum))) + minimum;
	}


	public void selectDropdownUsingMouseHover(WebElement element, String subelement) {
		Actions actions = new Actions(getdriver());
		actions.moveToElement(element);
		webdriverWait.wait(3);
		actions.moveToElement(element);
		actions.click().build().perform();
	}

	public void mouseHoverOnElement(WebElement element) {
		WebElement home = element;
		Actions actions = new Actions(driver);
		Action mouseOverHome = actions.moveToElement(home).build();
		mouseOverHome.perform();
		webdriverWait.wait(2);
	}


	public void clickUsingMouseActions(WebElement element1, WebElement element2) {
		Actions act = new Actions(driver);
		act.moveToElement(element1).build().perform();
		webdriverWait.wait(3);
		element2.click();
	}

	public void clickUsingActionsClass(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).click().perform();
	}

	public boolean isFileDownloaded(String downloadPath, String fileName) {
		boolean flag = false;
		File dir = new File(downloadPath);
		File[] dir_contents = dir.listFiles();

		for (int i = 0; i < dir_contents.length; i++) {
			if (dir_contents[i].getName().equals(fileName))
				return flag = true;
		}
		return flag;
	}

	public List<String> getFileName(String filePath) {
		File dir = new File(filePath);
		File[] dir_contents = dir.listFiles();
		List<String> files = new ArrayList<String>();

		for (int i = 0; i < dir_contents.length; i++) {
			if (dir_contents[i].isFile()) {
				String fileName = dir_contents[i].getName();
				files.add(fileName);
			}
		}

		for (int i = 0; i < files.size() - 1; i++)
			for (int k = i + 1; k < files.size(); k++) {
				System.out.println(files.get(i).compareTo(files.get(k)));
				int number = files.get(i).compareTo(files.get(k));
			}
//
//		List<List<String>> f = Stream.of(files).collect(Collectors.toList());
//		List<String> max = f.stream().max(Comparator.comparing(i->i)).get();
//		
		System.out.println(files);
		return files;
	}

	public boolean deleteSpecificFile(String pathOfFile) {
		boolean flag = false;

		File myFile = new File(pathOfFile);
		if (myFile.delete()) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	public String getAlphaNumericString(int n)
    {
        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index = (int)(AlphaNumericString.length()* Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }
}