package pages;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.BaseUtilities;
import utilities.Reports;
import utilities.WebdriverWait;

public class RegisterAPatientPage extends Reports{

public Logger logger;
	
	public RegisterAPatientPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		logger = LogManager.getLogger(BaseUtilities.class);
	}
	
	WebdriverWait webdriverWait = new WebdriverWait();
	BaseUtilities baseUtilities = new BaseUtilities();
	
	private final String GIVENTEXTFIELD = "//input[@name='givenName']";
	private final String FAMILYNAMETEXTFIELD = "//input[@name='familyName']";
	private final String NEXTBUTTON = "next-button";
	private final String PATIENTGENDER = "//select[@id='gender-field']";
	private final String PATIENTGENDEROPTIONS = "//select[@id='gender-field']/option";
	private final String ESTIMATEDYEARS = "birthdateYears-field";
	private final String ESTIMATEDMONTHS = "birthdateMonths-field";
	private final String ADDRESSTEXTFIELD = "address1";
	private final String PATIENTPHONENUMBERTEXTFIELD = "//input[@name='phoneNumber']";
	private final String CONFIRMBUTTON = "submit";
	private final String PATIENTDETAILS = "//div[@id='confirmation']//p";
	private final String RELATIONSHIPTYPE = "relationship_type";
	private final String PERSONNAME = "//input[@placeholder='Person Name']";
	private final String PATIENTID = "//em[text()='Patient ID']/following-sibling::span";
	private final String HOMEBUTTON = "//i[@class='icon-home small']";
	private final String PATIENTDETAILS_NAME = "//div[@id='confirmation']//p[1]";
	private final String PATIENTDETAILS_GENDER = "//div[@id='confirmation']//p[2]";
	private final String PATIENTDETAILS_BIRTHDATE = "//div[@id='confirmation']//p[3]";
	private final String PATIENTDETAILS_ADDRESS = "//div[@id='confirmation']//p[4]";
	private final String PATIENTDETAILS_PHONENUMBER = "//div[@id='confirmation']//p[5]";
	private final String PATIENTDETAILS_RELATIVES = "//div[@id='confirmation']//p[6]";
	
	@FindBy(xpath = PATIENTDETAILS_NAME)
	public WebElement patientDetails_name;
	
	@FindBy(xpath = PATIENTDETAILS_GENDER)
	public WebElement patientDetails_gender;
	
	@FindBy(xpath = PATIENTDETAILS_BIRTHDATE)
	public WebElement patientDetails_birthdate;
	
	@FindBy(xpath = PATIENTDETAILS_ADDRESS)
	public WebElement patientDetails_address;
	
	@FindBy(xpath = PATIENTDETAILS_PHONENUMBER)
	public WebElement patientDetails_phoneNumber;
	
	@FindBy(xpath = PATIENTDETAILS_RELATIVES)
	public WebElement patientDetails_relatives;
	
	@FindBy(xpath = GIVENTEXTFIELD)
	public WebElement givenTextField;

	@FindBy(xpath = FAMILYNAMETEXTFIELD)
	public WebElement familyNameTextField;
	
	@FindBy(id = NEXTBUTTON)
	public WebElement nextButton;
	
	@FindBy(xpath = PATIENTGENDER)
	public WebElement patientGender;
	
	@FindBy(xpath = HOMEBUTTON)
	public WebElement homeButton;
	
	@FindBy(xpath = PERSONNAME)
	public WebElement personName;
	
	@FindBy(xpath = PATIENTGENDEROPTIONS)
	public List<WebElement> patientGenderOptions;
	
	@FindBy(xpath = PATIENTDETAILS)
	public List<WebElement> patientDetails;
	
	@FindBy(id = ESTIMATEDYEARS)
	public WebElement estimatedYearsTextField;
	
	@FindBy(id = ESTIMATEDMONTHS)
	public WebElement estimatedMonthsTextField;
	
	@FindBy(id = ADDRESSTEXTFIELD)
	public WebElement addressTextField;
	
	@FindBy(xpath = PATIENTPHONENUMBERTEXTFIELD)
	public WebElement patientPhoneNumberTextField;
	
	@FindBy(id = CONFIRMBUTTON)
	public WebElement confirmButton;
	
	@FindBy(id = RELATIONSHIPTYPE)
	public WebElement relationshipType;
	
	@FindBy(xpath = PATIENTID)
	public WebElement patientID;
	
	public void enterValuesInGivenTextField(String value) {
		webdriverWait.waitForAnElement(givenTextField);
		log(baseUtilities.verifyElementDisplayed(givenTextField), "Given Text-field is present.","Given Text-field is not present.");
		baseUtilities.sendKeys(givenTextField, value);
	}
	
	public void enterValuesInFamilyNameTextField(String value) {
		webdriverWait.waitForAnElement(familyNameTextField);
		log(baseUtilities.verifyElementDisplayed(familyNameTextField), "Family Name Text-field is present.","Family Name Text-field is not present.");
		baseUtilities.sendKeys(familyNameTextField, value);
	}
	
	public void enterValuesInEstimatedYearsTextField(String value) {
		webdriverWait.waitForAnElement(estimatedYearsTextField);
		log(baseUtilities.verifyElementDisplayed(estimatedYearsTextField), "Estimated Years Text-field is present.","Estimated Years Text-field is not present.");
		baseUtilities.sendKeys(estimatedYearsTextField, value);
	}
	
	public void enterValuesInEstimatedMonthsTextField(String value) {
		webdriverWait.waitForAnElement(estimatedMonthsTextField);
		log(baseUtilities.verifyElementDisplayed(estimatedMonthsTextField), "Estimated Months Text-field is present.","Estimated Months Text-field is present.");
		baseUtilities.sendKeys(estimatedMonthsTextField, value);
	}
	
	public void enterValuesInPatientNumberTextField(String value) {
		webdriverWait.waitForAnElement(patientPhoneNumberTextField);
		log(baseUtilities.verifyElementDisplayed(patientPhoneNumberTextField), "Patient Phone Number Text-field is present.","Patient Phone Number Text-field is not present.");
		baseUtilities.sendKeys(patientPhoneNumberTextField, value);
	}
	
	public void enterValuesInPersonNameTextField(String value) {
		webdriverWait.waitForAnElement(personName);
		log(baseUtilities.verifyElementDisplayed(personName), "Person Name Text-field is present.","Person Name Text-field is not present.");
		baseUtilities.sendKeys(personName, value);
	}
	
	public void enterValuesInAddressTextField(String value) {
		webdriverWait.waitForAnElement(addressTextField);
		log(baseUtilities.verifyElementDisplayed(addressTextField), "Address Text-field is present.","Address Text-field is not present.");
		baseUtilities.sendKeys(addressTextField, value);
	}
	
	public void clickConfirmButton() {
		webdriverWait.waitForAnElement(confirmButton);
		log(baseUtilities.verifyElementDisplayed(confirmButton), "Confirm button is present.","Confirm button is not present.");
		baseUtilities.clickElement(confirmButton);
		webdriverWait.waitForAnElement(patientID);
	}
	
	public void clickHomeButton() {
		webdriverWait.waitForAnElement(homeButton);
		log(baseUtilities.verifyElementDisplayed(homeButton), "Home button is present.","Home button is not present.");
		baseUtilities.clickElement(homeButton);
	}
	
	
	public void clickNextButton() {
		webdriverWait.waitForAnElement(nextButton);
		log(baseUtilities.verifyElementDisplayed(nextButton), "Next button is present.","Next button is not present.");
		baseUtilities.clickElement(nextButton);
	}
	
	public void selectValueFromPatientGenderOptions(String option) {
		List<String> value = patientGenderOptions.stream().map(o->o.getText()).collect(Collectors.toList());
		info("Options present in Patient Gender are "+ value);
		baseUtilities.selectElementByValue(patientGender, option);
	}
	
	public void verifyPatientDetails(String givenName,String familyName, String gender, String years, String months, String address, String phoneNumber) {
		log(patientDetails_name.getText().contains(givenName+", "+familyName),"Name data is appropriate", "Name data is not appropriate");
		log(patientDetails_gender.getText().contains(gender),"Gender data is appropriate", "Gender data is not appropriate");
		log(patientDetails_birthdate.getText().contains(years),"Years data is appropriate", "Years data is not appropriate");
		log(patientDetails_address.getText().contains(address),"Address data is appropriate", "Address data is not appropriate");
		log(patientDetails_phoneNumber.getText().contains(phoneNumber),"Phone Number data is appropriate", "Phone Number data is not appropriate");
	}
	
	public void selectValueFromRelationshipTypeOptions() {
		baseUtilities.selectElementByText(relationshipType, "Sibling");
	}
}
