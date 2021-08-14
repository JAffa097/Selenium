package first;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import driverandscriptspackage.DriverClass;
import pages.OrangeHomePage;
import pages.OrangeLoginPage;
import util.ExcelData;
import util.ReportsGeneratorClass;

public class OrangeHrmAssignLeave {
	OrangeLoginPage loginPage;
	OrangeHomePage homePage;
	ReportsGeneratorClass reportingclass;
	@Parameters("Port")
	@BeforeMethod
	public void setup(String Port,Method method) {
		reportingclass=new ReportsGeneratorClass(method.getName());
		loginPage = new OrangeLoginPage(new DriverClass().getDriver(Port),  reportingclass, new ExcelData(method.getName()).getTestData());
		homePage = new OrangeHomePage(loginPage.driver, reportingclass, new ExcelData(method.getName()).getTestData());
	}
  @Test(enabled=true)
  public void assignLeave() throws IOException {
	  loginPage.navigatetoLoginPage();
	  loginPage.Login();
	  homePage.assignLeave();
	
  }
 
  
  @AfterMethod
	public void cleanDriver()  {
	  homePage.closeExecution(Status.PASS);
	  
	}
  
}

