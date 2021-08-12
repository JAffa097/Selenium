package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import driverandscriptspackage.ScriptsClass;
import util.ReportsGeneratorClass;

public class OrangeHomePage extends ScriptsClass{
	
	@FindBy(xpath="//span[text()='Assign Leave']") WebElement assignLeave;
	@FindBy(id="assignleave_txtEmployee_empName") WebElement empNameTextBox;
	@FindBy(id="assignleave_txtLeaveType") WebElement leaveTypeDropDown;
	@FindBy(id="assignleave_txtFromDate") WebElement fromDate;
	@FindBy(id="assignleave_txtToDate") WebElement toDate;
	@FindBy(id="assignleave_txtComment") WebElement comment;
	@FindBy(id="assignBtn") WebElement assign;
	public OrangeHomePage(WebDriver driver, ReportsGeneratorClass reportingclass) {
		super(driver, reportingclass);
		PageFactory.initElements(driver, this);
	}

	public void assignLeave(){
		click(assignLeave);
		printLog(Status.PASS, "Clicked on Assign Leave");
		click(empNameTextBox);
		enterText(empNameTextBox, "Melan");
		enterText(empNameTextBox, Keys.ENTER);
		printLog(Status.PASS, "Selected employee");
		selectFromDropDown(leaveTypeDropDown, "CAN - Personal");
		selectDate(fromDate, "2020-Jun-15");
		selectDate(toDate, "2020-Jun-17");
		printLog(Status.PASS, "Selected startdate and enddate");
		click(assign);
		printLog(Status.PASS, "clicked on assign");
		
	}
}
