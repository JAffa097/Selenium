package pages;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;

import driverandscriptspackage.ScriptsClass;

import util.ReportsGeneratorClass;


public class OrangeLoginPage extends ScriptsClass   {
	public Map<String,String> testdata;

	@FindBy(id="txtUsername") WebElement userID;
	@FindBy(id="txtPassword") WebElement password;
	@FindBy(id="btnLogin") WebElement loginButton;
	

	
	public OrangeLoginPage(WebDriver driver, ReportsGeneratorClass reportingclass, Map<String, String> testdata)  {
		super(driver, reportingclass);
		PageFactory.initElements(driver, this);
		this.testdata=testdata;
		
	}
	public void navigatetoLoginPage() {
		navigateTo("https://opensource-demo.orangehrmlive.com/");
	}
	
	public void Login()
	{
		enterText(userID, testdata.get("Username"));
		highlight(userID);
		printLog(Status.PASS, "Navigated to Login page", true);
		enterText(password,  testdata.get("Password"));
		click(loginButton);
		printLog(Status.PASS, "Navigated to Home Page");
		
	}
	
	
	
	
	
	
}
