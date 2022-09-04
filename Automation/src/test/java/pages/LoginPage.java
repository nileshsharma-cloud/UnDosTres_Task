package pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.BaseUtilities;
import utilities.Reports;
import utilities.WebdriverWait;

public class LoginPage extends Reports {

	public Logger logger;
	public HomePage homePage; 
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		logger = LogManager.getLogger(BaseUtilities.class);
	}
	
	WebdriverWait webdriverWait = new WebdriverWait();
	BaseUtilities baseUtilities = new BaseUtilities();
	
	private final String USERNAMETEXTFIELD = "username";
	private final String PASSWORDTEXTFIELD = "password";
	private final String LOGINBUTTON = "loginButton";
	private final String LOCATIONFORTHESESSIONTEXT = "//label[@for='sessionLocation']";
	private final String INPATIENTWARDLINK = "Inpatient Ward";
	private final String USERNAMEDROPDOWN = "selected-location";
	
	@FindBy(id = USERNAMETEXTFIELD)
	public WebElement usernameTextField;
	
	@FindBy(id = PASSWORDTEXTFIELD)
	public WebElement passwordTextField;
	
	@FindBy(id = LOGINBUTTON)
	public WebElement loginButton;
	
	@FindBy(xpath = LOCATIONFORTHESESSIONTEXT)
	public WebElement locationForTheSessionText;
	
	@FindBy(id = INPATIENTWARDLINK)
	public WebElement inPatientWardLink;
	
	@FindBy(id = USERNAMEDROPDOWN)
	public WebElement usernameDropdown;
	
	
	public void enterValuesInUsernameField(String username) {
		webdriverWait.waitForAnElement(usernameTextField);
		log(baseUtilities.verifyElementDisplayed(usernameTextField), "Username field is present.","Username field is not present.");
		baseUtilities.sendKeys(usernameTextField, username);
		logger.info("Username field is present and entered text in username field is "+username);
	}
	
	public void enterValuesInPasswordField(String password) {
		webdriverWait.waitForAnElement(passwordTextField);
		log(baseUtilities.verifyElementDisplayed(passwordTextField), "Password field is present.","Password field is not present.");
		baseUtilities.sendKeys(passwordTextField, password);
		logger.info("Password field is present");
	}
	
	public void clickLoginButton() {
		webdriverWait.waitForAnElement(loginButton);
		log(baseUtilities.verifyElementDisplayed(loginButton),
				"Login button is present.","Login button is not present.");
		baseUtilities.clickElement(loginButton);
	}
	
	public void clickInPatientWardLink() {
		webdriverWait.waitForAnElement(inPatientWardLink);
		log(baseUtilities.verifyElementDisplayed(locationForTheSessionText), baseUtilities.getTextOfWebElement(locationForTheSessionText)+" text is present",
				baseUtilities.getTextOfWebElement(locationForTheSessionText)+" text is not present");
		log(baseUtilities.verifyElementDisplayed(inPatientWardLink), "Inpatient ward button is present.","Inpatient ward button is not present.");
		baseUtilities.clickElement(inPatientWardLink);
	}
	
	public void verifyUserLoggedinSuccessfully(String username) {
		webdriverWait.waitForAnElement(usernameDropdown);
		log(baseUtilities.getTextOfWebElement(usernameDropdown).equalsIgnoreCase(username), baseUtilities.getTextOfWebElement(usernameDropdown)+" logged in successfully to the application",
				baseUtilities.getTextOfWebElement(usernameDropdown)+" logged in successfully to the application");
	}
	
	public void verifyUserLoggedOutSuccessfully() {
		webdriverWait.waitForAnElement(usernameTextField);
		log(baseUtilities.verifyElementDisplayed(usernameTextField), "User logged out successfully","User not logged out");
	}
	
}
