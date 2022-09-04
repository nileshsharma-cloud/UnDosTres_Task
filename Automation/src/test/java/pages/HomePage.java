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

public class HomePage extends Reports{

public Logger logger;
	
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		logger = LogManager.getLogger(BaseUtilities.class);
	}
	
	WebdriverWait webdriverWait = new WebdriverWait();
	BaseUtilities baseUtilities = new BaseUtilities();
	
	private final String LOGOUTBUTTON = "//li[@class='nav-item logout']/a";
	private final String USERNAMEDROPDOWN = "selected-location";
	private final String REGISTERAPATIENT = "referenceapplication-registrationapp-registerPatient-homepageLink-referenceapplication-registrationapp-registerPatient-homepageLink-extension";
	private final String FINDPATIENTRECORD = "coreapps-activeVisitsHomepageLink-coreapps-activeVisitsHomepageLink-extension";
	
	@FindBy(xpath = LOGOUTBUTTON)
	public WebElement logoutButton;
	
	@FindBy(id = USERNAMEDROPDOWN)
	public WebElement usernameDropdownText;
	
	@FindBy(id = REGISTERAPATIENT)
	public WebElement registerAPatientLink;
	
	@FindBy(id = FINDPATIENTRECORD)
	public WebElement findPatientRecordLink;
	
	public void clickLogoutButton() {
		webdriverWait.waitForAnElement(logoutButton);
		log(baseUtilities.verifyElementDisplayed(logoutButton), "Logout button is present.","Logout button is not present.");
		baseUtilities.clickElement(logoutButton);
	}
	
	public void clickFindPatientRecordLink() {
		webdriverWait.waitForAnElement(findPatientRecordLink);
		log(baseUtilities.verifyElementDisplayed(findPatientRecordLink), "Find Patient Record link is present.","Find Patient Record link is not present.");
		baseUtilities.clickElement(findPatientRecordLink);
	}
	
	public void clickRegisterAPatientLink() {
		webdriverWait.waitForAnElement(registerAPatientLink);
		log(baseUtilities.verifyElementDisplayed(registerAPatientLink), "Register a patient is present.","Register a patient is not present.");
		baseUtilities.clickElement(registerAPatientLink);
	}
	
}
