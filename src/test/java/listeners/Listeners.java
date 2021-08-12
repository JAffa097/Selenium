package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;



public class Listeners implements ITestListener {
	public void onStart(ITestContext context) {
		 System.err.println(context.getCurrentXmlTest().getSuite().getName());
		
	}

	public void onFinish(ITestContext context) {
 
	}

	public void onTestStart(ITestResult result) {
		 System.err.println("on Test Start");
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