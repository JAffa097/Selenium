package first;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import driverandscriptspackage.DriverClass;
import pages.OrangeHomePage;
import pages.OrangeLoginPage;
import util.ReportsGeneratorClass;

public class OrangeHRm {
	OrangeLoginPage loginPage;
	ReportsGeneratorClass reportingclass;
	@BeforeMethod
	@Parameters("Port")
	public void setup(String Port,Method method ) {
		reportingclass=new ReportsGeneratorClass(method.getName());
		loginPage = new OrangeLoginPage(new DriverClass().getDriver(Port),  reportingclass);
		
	}
  @Test(enabled=true)
  public void loginTest() throws IOException {
	  loginPage.navigatetoLoginPage();
	  loginPage.Login();
	 
	
  }
 
  
  @AfterMethod
	public void cleanDriver()  {
	  loginPage.closeExecution(Status.PASS);
	  
	}
  
}
