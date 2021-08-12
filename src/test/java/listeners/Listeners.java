package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import util.ExtentReportClass;
import util.ReportsGeneratorClass;



public class Listeners implements ITestListener {
	public void onStart(ITestContext context) {
		 System.err.println(context.getCurrentXmlTest().getSuite().getName());
		 ExtentReportClass.startExtentReport(context.getCurrentXmlTest().getSuite().getName());
		
	}

	public void onFinish(ITestContext context) {
 ReportsGeneratorClass.flushReports();
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