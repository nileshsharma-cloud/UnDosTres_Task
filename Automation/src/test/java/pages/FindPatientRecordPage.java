package pages;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.BaseUtilities;
import utilities.Reports;
import utilities.WebdriverWait;

public class FindPatientRecordPage extends Reports {

	public Logger logger;

	public FindPatientRecordPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		logger = LogManager.getLogger(BaseUtilities.class);
	}

	WebdriverWait webdriverWait = new WebdriverWait();
	BaseUtilities baseUtilities = new BaseUtilities();

	private final String PATIENTSEARCH = "patient-search";
	private final String PATIENTRESULTS = "//tbody[@role='alert']/tr";
	private final String SPINNER = "//img[@class='search-spinner']";
	private final String NOMATCHINGRECORDSFOUNDTEXT = "//td[@class='dataTables_empty']";

	@FindBy(id = PATIENTSEARCH)
	public WebElement patientSearch;

	@FindBy(xpath = SPINNER)
	public WebElement spinner;
	
	@FindBy(xpath = PATIENTRESULTS)
	public List<WebElement> patientResults;
	
	@FindBy(xpath = NOMATCHINGRECORDSFOUNDTEXT)
	public WebElement noMatchingRecordsFoundText;
	
	public void enterValuesInPatientSearchTextField(String value) {
		webdriverWait.waitForAnElement(patientSearch);
		log(baseUtilities.verifyElementDisplayed(patientSearch), "Patient Search Text-field is present.","Patient Search Text-field is not present.");
		baseUtilities.sendKeys(patientSearch, value);
	}
	
	public void verifyPatientResults() {
		webdriverWait.waitForElementToBeInvisible(spinner);
		log(patientResults.size()>0,"Patient Record displayed correctly","Patient Record is not displayed correctly");
	}
	
	public void verifyDeletedPatientResults() {
		webdriverWait.waitForElementToBeInvisible(spinner);
		log(baseUtilities.verifyElementDisplayed(noMatchingRecordsFoundText),"Deleted Patient is not displayed","Deleted Patient is still displayed");
	}
}
