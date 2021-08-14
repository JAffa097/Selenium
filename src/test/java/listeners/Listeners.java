package listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import util.ExtentReportClass;
import util.ReportsGeneratorClass;



public class Listeners implements ITestListener {
	public void onStart(ITestContext context) {
	String port=	context.getCurrentXmlTest().getParameter("Port");
		if(!(port.equals("")||port.equals(" "))) {
		try {
			Runtime.getRuntime().exec("cmd /c start \"\" createcontainer.bat");
			Thread.sleep(5000);
			 Runtime.getRuntime().exec("taskkill /f /im cmd.exe") ;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		}
		 ExtentReportClass.startExtentReport(context.getCurrentXmlTest().getSuite().getName());
		
	}

	public void onFinish(ITestContext context) {
		String port=	context.getCurrentXmlTest().getParameter("Port");
 ReportsGeneratorClass.flushReports();
 if(!(port.equals("")||port.equals(" "))) {
 try {
	Runtime. getRuntime().exec("cmd /c start \"\" stopcontainer.bat");
	Thread.sleep(5000);
	Runtime.getRuntime().exec("taskkill /f /im cmd.exe") ;
} catch (Exception e) {
	
	e.printStackTrace();
}
 }

	}

	public void onTestStart(ITestResult result) {
		 System.out.println("on Test Start");
	}

	public void onTestSuccess(ITestResult result) {

	}

	public void onTestFailure(ITestResult result) {
      // result.getClass().
	}

	public void onTestSkipped(ITestResult result) {

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}
}