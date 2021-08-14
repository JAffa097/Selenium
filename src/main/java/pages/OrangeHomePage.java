package pages;

import java.util.Map;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import driverandscriptspackage.ScriptsClass;
import util.ReportsGeneratorClass;

public class OrangeHomePage extends ScriptsClass{
	public Map<String,String> testdata;
	
	@FindBy(xpath="//span[text()='Assign Leave']") WebElement assignLeave;
	@FindBy(id="assignleave_txtEmployee_empName") WebElement empNameTextBox;
	@FindBy(id="assignleave_txtLeaveType") WebElement leaveTypeDropDown;
	@FindBy(id="assignleave_txtFromDate") WebElement fromDate;
	@FindBy(id="assignleave_txtToDate") WebElement toDate;
	@FindBy(id="assignleave_txtComment") WebElement comment;
	@FindBy(id="assignBtn") WebElement assign;
	public OrangeHomePage(WebDriver driver, ReportsGeneratorClass reportingclass, Map<String,String> testdata) {
		super(driver, reportingclass);
		PageFactory.initElements(driver, this);
		this.testdata=testdata;
	}

	public void assignLeave(){
		click(assignLeave);
		printLog(Status.PASS, "Clicked on Assign Leave");
		click(empNameTextBox);
		enterText(empNameTextBox, testdata.get("EmployeeName"));
		enterText(empNameTextBox, Keys.ENTER);
		printLog(Status.PASS, "Selected employee");
		selectFromDropDown(leaveTypeDropDown, testdata.get("LeaveType"));
		selectDate(fromDate, testdata.get("FromDate"));
		selectDate(toDate, testdata.get("ToDate"));
		printLog(Status.PASS, "Selected startdate and enddate");
		click(assign);
		printLog(Status.PASS, "clicked on assign");
		
	}
}
