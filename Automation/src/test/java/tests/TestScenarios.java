package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import listeners.CustomListenersClass;
import pages.FindPatientRecordPage;
import pages.HomePage;
import pages.LoginPage;
import pages.PatientInfoPage;
import pages.RegisterAPatientPage;
import readData.ReadDataFromExcel;
import utilities.BaseUtilities;

@Listeners(CustomListenersClass.class)
public class TestScenarios {

	BaseUtilities baseUtilities = new BaseUtilities();
	WebDriver driver;

	@Test(priority = 1, enabled = true, description = "Login with valid credentials.")
	public void login() throws IOException {
		
		ReadDataFromExcel rd = new ReadDataFromExcel("Sheet1");
		LoginPage loginPage = new LoginPage(BaseUtilities.driver);
		
		loginPage.enterValuesInUsernameField(rd.getkeyValue("username"));
		loginPage.enterValuesInPasswordField(rd.getkeyValue("password"));
		String inPatientWardText = baseUtilities.getTextOfWebElement(loginPage.inPatientWardLink);
		loginPage.clickInPatientWardLink();
		loginPage.clickLoginButton();
		loginPage.verifyUserLoggedinSuccessfully(inPatientWardText);
	}
	

	@Test(priority = 2, enabled = true, description = "Login with valid credentials.")
	public void logout() throws IOException {
		
		ReadDataFromExcel rd = new ReadDataFromExcel("Sheet1");
		LoginPage loginPage = new LoginPage(BaseUtilities.driver);
		HomePage homePage = new HomePage(BaseUtilities.driver);
		
		loginPage.enterValuesInUsernameField(rd.getkeyValue("username"));
		loginPage.enterValuesInPasswordField(rd.getkeyValue("password"));
		String inPatientWardText = baseUtilities.getTextOfWebElement(loginPage.inPatientWardLink);
		loginPage.clickInPatientWardLink();
		loginPage.clickLoginButton();
		loginPage.verifyUserLoggedinSuccessfully(inPatientWardText);
		homePage.clickLogoutButton();
		loginPage.verifyUserLoggedOutSuccessfully();
	}
	
	@Test(priority = 3, enabled = true, description = "Register A Patient")
	public void registerapatient() throws IOException {
		
		ReadDataFromExcel rd = new ReadDataFromExcel("Sheet1");
		LoginPage loginPage = new LoginPage(BaseUtilities.driver);
		HomePage homePage = new HomePage(BaseUtilities.driver);
		RegisterAPatientPage registerAPatientPage = new RegisterAPatientPage(BaseUtilities.driver);
		
		String givenField = BaseUtilities.prop.getProperty("Given")+"_"+baseUtilities.randomWordsGenerator(5);
		String familyNameField = BaseUtilities.prop.getProperty("FamilyName")+"_"+baseUtilities.randomWordsGenerator(5);
		String genderOption = BaseUtilities.prop.getProperty("GenderOption");
		String years = BaseUtilities.prop.getProperty("Years");
		String months = BaseUtilities.prop.getProperty("Months");
		String address = BaseUtilities.prop.getProperty("Address");
		String phoneNumber = BaseUtilities.prop.getProperty("PhoneNumber");
		
		loginPage.enterValuesInUsernameField(rd.getkeyValue("username"));
		loginPage.enterValuesInPasswordField(rd.getkeyValue("password"));
		String inPatientWardText = baseUtilities.getTextOfWebElement(loginPage.inPatientWardLink);
		loginPage.clickInPatientWardLink();
		loginPage.clickLoginButton();
		loginPage.verifyUserLoggedinSuccessfully(inPatientWardText);
		homePage.clickRegisterAPatientLink();
		registerAPatientPage.enterValuesInGivenTextField(givenField);
		registerAPatientPage.enterValuesInFamilyNameTextField(familyNameField);
		registerAPatientPage.clickNextButton();
		registerAPatientPage.selectValueFromPatientGenderOptions(BaseUtilities.prop.getProperty("GenderOption"));
		registerAPatientPage.clickNextButton();
		registerAPatientPage.enterValuesInEstimatedYearsTextField(BaseUtilities.prop.getProperty("Years"));
		registerAPatientPage.enterValuesInEstimatedMonthsTextField(BaseUtilities.prop.getProperty("Months"));
		registerAPatientPage.clickNextButton();
		registerAPatientPage.enterValuesInAddressTextField(BaseUtilities.prop.getProperty("Address"));
		registerAPatientPage.clickNextButton();
		registerAPatientPage.enterValuesInPatientNumberTextField(BaseUtilities.prop.getProperty("PhoneNumber"));
		registerAPatientPage.clickNextButton();
		registerAPatientPage.selectValueFromRelationshipTypeOptions();
		registerAPatientPage.enterValuesInPersonNameTextField(BaseUtilities.prop.getProperty("PersonName"));
		registerAPatientPage.clickNextButton();
		registerAPatientPage.verifyPatientDetails(givenField, familyNameField, genderOption, years, months, address, phoneNumber);
		registerAPatientPage.clickConfirmButton();
		homePage.clickLogoutButton();
		loginPage.verifyUserLoggedOutSuccessfully();
	}
	
	@Test(priority = 4, enabled = true, description = "Find a Patient Record")
	public void findapatientrecord() throws IOException {
		
		ReadDataFromExcel rd = new ReadDataFromExcel("Sheet1");
		LoginPage loginPage = new LoginPage(BaseUtilities.driver);
		HomePage homePage = new HomePage(BaseUtilities.driver);
		RegisterAPatientPage registerAPatientPage = new RegisterAPatientPage(BaseUtilities.driver);
		FindPatientRecordPage findPatientRecordPage = new FindPatientRecordPage(BaseUtilities.driver);
		
		String givenField = BaseUtilities.prop.getProperty("Given")+"_"+baseUtilities.randomWordsGenerator(5);
		String familyNameField = BaseUtilities.prop.getProperty("FamilyName")+"_"+baseUtilities.randomWordsGenerator(5);
		String genderOption = BaseUtilities.prop.getProperty("GenderOption");
		String years = BaseUtilities.prop.getProperty("Years");
		String months = BaseUtilities.prop.getProperty("Months");
		String address = BaseUtilities.prop.getProperty("Address");
		String phoneNumber = BaseUtilities.prop.getProperty("PhoneNumber");
		
		loginPage.enterValuesInUsernameField(rd.getkeyValue("username"));
		loginPage.enterValuesInPasswordField(rd.getkeyValue("password"));
		String inPatientWardText = baseUtilities.getTextOfWebElement(loginPage.inPatientWardLink);
		loginPage.clickInPatientWardLink();
		loginPage.clickLoginButton();
		loginPage.verifyUserLoggedinSuccessfully(inPatientWardText);
		homePage.clickRegisterAPatientLink();
		registerAPatientPage.enterValuesInGivenTextField(givenField);
		registerAPatientPage.enterValuesInFamilyNameTextField(familyNameField);
		registerAPatientPage.clickNextButton();
		registerAPatientPage.selectValueFromPatientGenderOptions(BaseUtilities.prop.getProperty("GenderOption"));
		registerAPatientPage.clickNextButton();
		registerAPatientPage.enterValuesInEstimatedYearsTextField(BaseUtilities.prop.getProperty("Years"));
		registerAPatientPage.enterValuesInEstimatedMonthsTextField(BaseUtilities.prop.getProperty("Months"));
		registerAPatientPage.clickNextButton();
		registerAPatientPage.enterValuesInAddressTextField(BaseUtilities.prop.getProperty("Address"));
		registerAPatientPage.clickNextButton();
		registerAPatientPage.enterValuesInPatientNumberTextField(BaseUtilities.prop.getProperty("PhoneNumber"));
		registerAPatientPage.clickNextButton();
		registerAPatientPage.selectValueFromRelationshipTypeOptions();
		registerAPatientPage.enterValuesInPersonNameTextField(BaseUtilities.prop.getProperty("PersonName"));
		registerAPatientPage.clickNextButton();
		registerAPatientPage.verifyPatientDetails(givenField, familyNameField, genderOption, years, months, address, phoneNumber);
		registerAPatientPage.clickConfirmButton();
		String text = baseUtilities.getTextOfWebElement(registerAPatientPage.patientID);
		registerAPatientPage.clickHomeButton();
		homePage.clickFindPatientRecordLink();
		findPatientRecordPage.enterValuesInPatientSearchTextField(text);
		findPatientRecordPage.verifyPatientResults();
		homePage.clickLogoutButton();
		loginPage.verifyUserLoggedOutSuccessfully();
	}
	
	@Test(priority = 5, enabled = true, description = "View the Patient")
	public void viewthepatient() throws IOException {
		
		ReadDataFromExcel rd = new ReadDataFromExcel("Sheet1");
		LoginPage loginPage = new LoginPage(BaseUtilities.driver);
		HomePage homePage = new HomePage(BaseUtilities.driver);
		RegisterAPatientPage registerAPatientPage = new RegisterAPatientPage(BaseUtilities.driver);
		PatientInfoPage patientInfoPage = new PatientInfoPage(BaseUtilities.driver);
		
		String givenField = BaseUtilities.prop.getProperty("Given")+"_"+baseUtilities.randomWordsGenerator(5);
		String familyNameField = BaseUtilities.prop.getProperty("FamilyName")+"_"+baseUtilities.randomWordsGenerator(5);
		String genderOption = BaseUtilities.prop.getProperty("GenderOption");
		String years = BaseUtilities.prop.getProperty("Years");
		String months = BaseUtilities.prop.getProperty("Months");
		String address = BaseUtilities.prop.getProperty("Address");
		String phoneNumber = BaseUtilities.prop.getProperty("PhoneNumber");
		
		loginPage.enterValuesInUsernameField(rd.getkeyValue("username"));
		loginPage.enterValuesInPasswordField(rd.getkeyValue("password"));
		String inPatientWardText = baseUtilities.getTextOfWebElement(loginPage.inPatientWardLink);
		loginPage.clickInPatientWardLink();
		loginPage.clickLoginButton();
		loginPage.verifyUserLoggedinSuccessfully(inPatientWardText);
		homePage.clickRegisterAPatientLink();
		registerAPatientPage.enterValuesInGivenTextField(givenField);
		registerAPatientPage.enterValuesInFamilyNameTextField(familyNameField);
		registerAPatientPage.clickNextButton();
		registerAPatientPage.selectValueFromPatientGenderOptions(BaseUtilities.prop.getProperty("GenderOption"));
		registerAPatientPage.clickNextButton();
		registerAPatientPage.enterValuesInEstimatedYearsTextField(BaseUtilities.prop.getProperty("Years"));
		registerAPatientPage.enterValuesInEstimatedMonthsTextField(BaseUtilities.prop.getProperty("Months"));
		registerAPatientPage.clickNextButton();
		registerAPatientPage.enterValuesInAddressTextField(BaseUtilities.prop.getProperty("Address"));
		registerAPatientPage.clickNextButton();
		registerAPatientPage.enterValuesInPatientNumberTextField(BaseUtilities.prop.getProperty("PhoneNumber"));
		registerAPatientPage.clickNextButton();
		registerAPatientPage.selectValueFromRelationshipTypeOptions();
		registerAPatientPage.enterValuesInPersonNameTextField(BaseUtilities.prop.getProperty("PersonName"));
		registerAPatientPage.clickNextButton();
		registerAPatientPage.verifyPatientDetails(givenField, familyNameField, genderOption, years, months, address, phoneNumber);
		registerAPatientPage.clickConfirmButton();
		patientInfoPage.verifyPatientDetails(givenField, familyNameField, genderOption, years, address, phoneNumber);
		homePage.clickLogoutButton();
		loginPage.verifyUserLoggedOutSuccessfully();
	}
	
	@Test(priority = 8, enabled = true, description = "Delete Registered Patient")
	public void deleteregisteredpatient() throws IOException {
		
		ReadDataFromExcel rd = new ReadDataFromExcel("Sheet1");
		LoginPage loginPage = new LoginPage(BaseUtilities.driver);
		HomePage homePage = new HomePage(BaseUtilities.driver);
		RegisterAPatientPage registerAPatientPage = new RegisterAPatientPage(BaseUtilities.driver);
		FindPatientRecordPage findPatientRecordPage = new FindPatientRecordPage(BaseUtilities.driver);
		PatientInfoPage patientInfoPage = new PatientInfoPage(BaseUtilities.driver);
		
		String givenField = BaseUtilities.prop.getProperty("Given")+"_"+baseUtilities.randomWordsGenerator(5);
		String familyNameField = BaseUtilities.prop.getProperty("FamilyName")+"_"+baseUtilities.randomWordsGenerator(5);
		String genderOption = BaseUtilities.prop.getProperty("GenderOption");
		String years = BaseUtilities.prop.getProperty("Years");
		String months = BaseUtilities.prop.getProperty("Months");
		String address = BaseUtilities.prop.getProperty("Address");
		String phoneNumber = BaseUtilities.prop.getProperty("PhoneNumber");
		String reason = "Patient Deceased";
		
		loginPage.enterValuesInUsernameField(rd.getkeyValue("username"));
		loginPage.enterValuesInPasswordField(rd.getkeyValue("password"));
		String inPatientWardText = baseUtilities.getTextOfWebElement(loginPage.inPatientWardLink);
		loginPage.clickInPatientWardLink();
		loginPage.clickLoginButton();
		loginPage.verifyUserLoggedinSuccessfully(inPatientWardText);
		homePage.clickRegisterAPatientLink();
		registerAPatientPage.enterValuesInGivenTextField(givenField);
		registerAPatientPage.enterValuesInFamilyNameTextField(familyNameField);
		registerAPatientPage.clickNextButton();
		registerAPatientPage.selectValueFromPatientGenderOptions(BaseUtilities.prop.getProperty("GenderOption"));
		registerAPatientPage.clickNextButton();
		registerAPatientPage.enterValuesInEstimatedYearsTextField(BaseUtilities.prop.getProperty("Years"));
		registerAPatientPage.enterValuesInEstimatedMonthsTextField(BaseUtilities.prop.getProperty("Months"));
		registerAPatientPage.clickNextButton();
		registerAPatientPage.enterValuesInAddressTextField(BaseUtilities.prop.getProperty("Address"));
		registerAPatientPage.clickNextButton();
		registerAPatientPage.enterValuesInPatientNumberTextField(BaseUtilities.prop.getProperty("PhoneNumber"));
		registerAPatientPage.clickNextButton();
		registerAPatientPage.selectValueFromRelationshipTypeOptions();
		registerAPatientPage.enterValuesInPersonNameTextField(BaseUtilities.prop.getProperty("PersonName"));
		registerAPatientPage.clickNextButton();
		registerAPatientPage.verifyPatientDetails(givenField, familyNameField, genderOption, years, months, address, phoneNumber);
		registerAPatientPage.clickConfirmButton();
		patientInfoPage.verifyPatientDetails(givenField, familyNameField, genderOption, years, address, phoneNumber);
		String text = baseUtilities.getTextOfWebElement(registerAPatientPage.patientID);
		patientInfoPage.clickDeletePatientLink();
		patientInfoPage.enterValuesInDeleteReasonField(reason);
		patientInfoPage.clickDeletePopupConfirmButton();
		findPatientRecordPage.enterValuesInPatientSearchTextField(text);
		findPatientRecordPage.verifyPatientResults();
		homePage.clickLogoutButton();
		loginPage.verifyUserLoggedOutSuccessfully();
	}
	
}
