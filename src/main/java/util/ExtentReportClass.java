package util;

import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportClass {
	private String reportName;
	public static ExtentHtmlReporter htmlreporter;
	public static ExtentReports reporter;
	public static ExtentTest currentlyRunningTest;

	public ExtentReportClass(String reportName) {
		this.reportName = reportName;
	}

	public void startExtentReport() {
		//String date=new Date();
		htmlreporter = new ExtentHtmlReporter("Reports\\ExtentReport.html");
		htmlreporter.config().setDocumentTitle("My Personal Project");
		htmlreporter.setAppendExisting(true);
		htmlreporter.setStartTime(new Date());
		reporter = new ExtentReports();
		reporter.attachReporter(htmlreporter);
		currentlyRunningTest = reporter.createTest(reportName);
		currentlyRunningTest.getModel().setStartTime(new Date());
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

	public void closeExtentReport() {
		htmlreporter.setEndTime(new Date());
		currentlyRunningTest.getModel().setEndTime(new Date());
		reporter.flush();

	}
	
	

}
