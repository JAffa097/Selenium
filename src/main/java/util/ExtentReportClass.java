package util;

import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportClass {
	String testcasename;
	private static String reportName;
	public static ExtentHtmlReporter htmlreporter;
	public static ExtentReports reporter;
	public static ExtentTest currentlyRunningSuit;
	public ExtentTest currentlyRunningTest;

	

	public static  void startExtentReport(String suiteName) {
		//String date=new Date();
		ExtentReportClass.reportName = suiteName;
		htmlreporter = new ExtentHtmlReporter("Reports\\ExtentReport.html");
		htmlreporter.config().setDocumentTitle("My Personal Project");
		htmlreporter.setAppendExisting(true);
		htmlreporter.setStartTime(new Date());
		reporter = new ExtentReports();
		reporter.attachReporter(htmlreporter);
		currentlyRunningSuit = reporter.createTest(reportName);
		currentlyRunningSuit.getModel().setStartTime(new Date());
	}
	public void startTestCaseReport(String testCaseName) {
		this.testcasename=testCaseName;
		currentlyRunningTest=	currentlyRunningSuit.createNode(testCaseName);
		
	}

	public void log(Status status, String description) {

		currentlyRunningTest.log(status, description);

	}

	public void log(Status status, String description, String imagePath) {
		try {
			
			currentlyRunningTest.log(status, description,MediaEntityBuilder.createScreenCaptureFromPath(imagePath).build());
					
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void closeExtentReport() {
		htmlreporter.setEndTime(new Date());
		currentlyRunningSuit.getModel().setEndTime(new Date());
		reporter.flush();

	}
	
	

}
