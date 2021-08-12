package util;

import java.io.File;
import java.io.FileInputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import org.testng.Assert;

import com.aventstack.extentreports.Status;

public class ReportsGeneratorClass {
	String reportName;
	String individualImagesPath;
	String wordDocReportPath;
	Properties prop;
	public  ExtentReportClass reportclass;
	WordReport wordReport;
	Map<String,String> wordReportImages = new LinkedHashMap<String, String>();

	public ReportsGeneratorClass(String reportName) {
	this.reportName=reportName;
	loadProperties();
	startReporting(reportName);

		}
	
	public void loadProperties() {
		prop = new Properties();
		try {
			
			prop.load(new FileInputStream(new File("src\\main\\resources\\config.properties")));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		wordDocReportPath=prop.getProperty("wordDocReportPath");
		individualImagesPath =prop.getProperty("individualImagesPath");
		
	}
	public void startReporting(String methodName) {
		reportclass = new ExtentReportClass();
		reportclass.startTestCaseReport(methodName);
	}
public void printLog(Status status, String description, String imagePath) {
		
		wordReportImages.put(description, imagePath);
		reportclass.log(status, description);
		if(status.name().equalsIgnoreCase("fail")) {
		closeExecution(Status.FAIL);
		}
		
	}
	
	public void printLog(Status status, String description,  String imagePath,Boolean attachImage) {
		if(attachImage) {
			String screenshotpath =  imagePath;
			wordReportImages.put(description, screenshotpath);
			reportclass.log(status, description, screenshotpath);
		}
		else {
			 printLog(status, description,  imagePath);
		}
		
		if(status.name().equalsIgnoreCase("fail")) {
			closeExecution(Status.FAIL);
			Assert.fail();
		}
		
		
	}
	
	public void closeExecution(Status status) {
		//reportclass.closeExtentReport();
		wordReport =new WordReport(wordDocReportPath,reportName,wordReportImages);
		wordReport.createWordReport(status);
		
		
	}
	public  static void flushReports() {
		ExtentReportClass.closeExtentReport();;
	}
	
	
}
