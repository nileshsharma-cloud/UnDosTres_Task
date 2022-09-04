package utilities;

import java.time.Duration;

import org.apache.commons.logging.Log;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebdriverWait{

	public Logger logger = LogManager.getLogger(Log.class.getClass().getSimpleName());
	
	public WebdriverWait wait(int seconds) {

		try {
			Thread.sleep(seconds * 1000);
		} catch (Exception e) {
			logger.info("Timeout exception");
		}
		return this;
	}

	public WebElement waitForElementToBeVisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(BaseUtilities.driver, Duration.ofSeconds(20));
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	public WebElement waitForElementToBePresent(By element) {

		WebDriverWait wait = new WebDriverWait(BaseUtilities.driver, Duration.ofSeconds(20));
		return wait.until(ExpectedConditions.presenceOfElementLocated(element));
	}
	
	public WebElement waitForElementToBeClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(BaseUtilities.driver, Duration.ofSeconds(20));
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public Boolean waitForTextToBePresent(WebElement element, String textToMatch) {
		WebDriverWait wait = new WebDriverWait(BaseUtilities.driver, Duration.ofSeconds(20));
		return wait.until(ExpectedConditions.textToBePresentInElement(element,textToMatch));	
	}
	
	public void waitForAnElement(WebElement element) {
		waitForElementToBeVisible(element);
		waitForElementToBeClickable(element);
	}
	
	public void waitForElementToBeInvisible(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(BaseUtilities.driver, Duration.ofSeconds(20));
			wait.until(ExpectedConditions.invisibilityOf(element));
		} catch (Exception e) {
			logger.info("Spinner is not present");
		}
	}
}
