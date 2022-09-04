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

public class PatientInfoPage extends Reports {

	public Logger logger;
	public HomePage homePage; 
	
	public PatientInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		logger = LogManager.getLogger(BaseUtilities.class);
	}
	
	WebdriverWait webdriverWait = new WebdriverWait();
	BaseUtilities baseUtilities = new BaseUtilities();
	
	private final String GIVENFIELD = "//em[text()='Given']/preceding-sibling::span";
	private final String FAMILYNAME = "//em[text()='Family Name']/preceding-sibling::span";
	private final String GENDER = "//div[@class='gender-age col-auto']/span[1]";
	private final String AGE = "//div[@class='gender-age col-auto']/span[2]";
	private final String ADDRESS = "//em[text()='Address']/parent::span";
	private final String TELEPHONENUmber = "coreapps-telephoneNumber";
	private final String DELETEPATIENT = "//div[@class='col-11 col-lg-10' and normalize-space()='Delete Patient']";
	private final String DELETEREASON = "delete-reason";
	private final String DELETEPOPUPCONFIRMBUTTON = "//div[@id='delete-patient-creation-dialog']//button[text()='Confirm']";
	private final String SHOWCONTACTINFO = "coreapps-showContactInfo";
	
	@FindBy(id = SHOWCONTACTINFO)
	public WebElement showContactInfo;
	
	@FindBy(xpath = GIVENFIELD)
	public WebElement givenField;
	
	@FindBy(xpath = FAMILYNAME)
	public WebElement familyName;
	
	@FindBy(xpath = GENDER)
	public WebElement gender;
	
	@FindBy(xpath = AGE)
	public WebElement age;
	
	@FindBy(xpath = ADDRESS)
	public WebElement address;
	
	@FindBy(id = TELEPHONENUmber)
	public WebElement telephoneNumber;
	
	@FindBy(xpath = DELETEPATIENT)
	public WebElement deletePatientLink;
	
	@FindBy(id = DELETEREASON)
	public WebElement deleteReason;
	
	@FindBy(xpath = DELETEPOPUPCONFIRMBUTTON)
	public WebElement deletePopupConfirmButton;
	
	public void clickDeletePatientLink() {
		webdriverWait.waitForAnElement(deletePatientLink);
		log(baseUtilities.verifyElementDisplayed(deletePatientLink),
				"Delete Patient button is present.","Delete Patient button is not present.");
		baseUtilities.clickElement(deletePatientLink);
	}
	
	public void clickShowContactInfoDropdown() {
		webdriverWait.waitForAnElement(showContactInfo);
		log(baseUtilities.verifyElementDisplayed(showContactInfo),
				"Show Contact Info drop-down is present","Show Contact Info drop-down is not present");
		baseUtilities.clickElement(showContactInfo);
	}
	
	public void enterValuesInDeleteReasonField(String password) {
		webdriverWait.waitForAnElement(deleteReason);
		log(baseUtilities.verifyElementDisplayed(deleteReason), "Delete Reason field is present.","Delete Reason field is not present.");
		baseUtilities.sendKeys(deleteReason, password);
		logger.info("Password field is present");
	}
	
	public void clickDeletePopupConfirmButton() {
		webdriverWait.waitForAnElement(deletePopupConfirmButton);
		log(baseUtilities.verifyElementDisplayed(deletePopupConfirmButton),
				"Delete Pop-up confirm button is present.","Delete Pop-up confirm button is not present.");
		baseUtilities.clickElement(deletePopupConfirmButton);
	}
	
	public void verifyPatientDetails(String given, String familyNameField, String genderOption, String years, String addressfield, String phoneNumber) {
		webdriverWait.waitForAnElement(givenField);
		clickShowContactInfoDropdown();
		log(baseUtilities.getTextOfWebElement(givenField).equalsIgnoreCase(given), 
				baseUtilities.getTextOfWebElement(givenField)+" value is present for Given field",
				baseUtilities.getTextOfWebElement(givenField)+" value is not present for Given field");
		
		log(baseUtilities.getTextOfWebElement(familyName).equalsIgnoreCase(familyNameField), 
				baseUtilities.getTextOfWebElement(familyName)+" value is present for Family Name field",
				baseUtilities.getTextOfWebElement(familyName)+" value is not present for Family Name field");
		
		log(baseUtilities.getTextOfWebElement(gender).contains(genderOption), 
				baseUtilities.getTextOfWebElement(gender)+" value is present for Gender field",
				baseUtilities.getTextOfWebElement(gender)+" value is not present for Gender field");
		
		log(baseUtilities.getTextOfWebElement(age).contains(years), 
				baseUtilities.getTextOfWebElement(givenField)+" value is present for Age",
				baseUtilities.getTextOfWebElement(givenField)+" value is not present for Age");
		
		log(baseUtilities.getTextOfWebElement(address).equalsIgnoreCase(addressfield), 
				baseUtilities.getTextOfWebElement(address)+" value is present for Address field",
				baseUtilities.getTextOfWebElement(address)+" value is not present for Address field");
		
		log(baseUtilities.getTextOfWebElement(telephoneNumber).equalsIgnoreCase(phoneNumber), 
				baseUtilities.getTextOfWebElement(telephoneNumber)+" value is present for Telephone Number field",
				baseUtilities.getTextOfWebElement(telephoneNumber)+" value is not present for Telephone Number field");
	}
	
}
