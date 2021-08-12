package first;

import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;

public class BeforeSuiteClass {

	@BeforeSuite
	public void startReporting(ITestContext ctx) {
		
		String suiteName = ctx.getCurrentXmlTest().getName();
	}
}
