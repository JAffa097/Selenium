package driverandscriptspackage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import util.ExcelData;
import util.ExtentReportClass;
import util.ReportsGeneratorClass;
import util.WordReport;

public class ScriptsClass extends DriverClass {

	public String individualImagesPath;
	public String wordDocReportPath;
	public Properties prop;
	public ExtentReportClass reportclass;
	public WordReport wordReport;
	public ReportsGeneratorClass reportingclass;

	

	public WebDriver driver;
	
	
	public ScriptsClass(WebDriver driver, ReportsGeneratorClass reportingclass)  {
		this.driver=driver;
		loadProperties();
		this.reportingclass=reportingclass;
		
		
	}

	protected void click(WebElement element) {
		try {

		element.click();
		}catch(Exception e) {
			failTestCase(e.getMessage());
			e.printStackTrace();
		}
	}

	protected void enterText(WebElement element, String message) {
try {
		element.sendKeys(message);
}catch(Exception e) {
	e.printStackTrace();
	failTestCase(e.getMessage());
	
}
	}
	protected void enterText(WebElement element, Keys keys) {
try {
		element.sendKeys(keys);
}catch(Exception e) {
	e.printStackTrace();
	failTestCase(e.getMessage());
}
	}
	
	protected void selectFromDropDown(WebElement element, String value) {
		try {
		Select select = new Select(element);
		select.selectByVisibleText(value);
		}catch(Exception e) {
			e.printStackTrace();
			failTestCase(e.getMessage());
		}
	}
	
	protected void selectDate(WebElement element, String date) {
		try {
		click(element);
		String day = date.split("-")[2];
		String month = date.split("-")[1];
		String year = date.split("-")[0];
		selectFromDropDown(driver.findElement(By.className("ui-datepicker-month")), month);
		selectFromDropDown(driver.findElement(By.className("ui-datepicker-year")), year);
		click(driver.findElement(By.xpath("//a[text()='"+day+"']")));
		}catch(Exception e) {
			e.printStackTrace();
			failTestCase(e.getMessage());
		}
	}

	protected void highlight(WebElement element) {
		try {

		((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", element);
		}catch(Exception e) {
			e.printStackTrace();
			failTestCase(e.getMessage());
		}
	}
	protected void navigateTo(String URL) {
		
		driver.get(URL);
	}
	public void quitDriver() {
		driver.quit();
	}
	public String getSessionID() {
		return ((RemoteWebDriver)driver).getSessionId().toString();
	}
	
	public String takeScreenShot() {
		String screenShotPath = individualImagesPath+new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(new Date())+".jpeg";
		try {
			
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(screenShotPath);
		FileUtils.copyFile(src, dest);
		screenShotPath=dest.getAbsolutePath();
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return screenShotPath;
		
	}
	
	public void printLog(Status status, String description) {
		reportingclass.printLog(status, description, takeScreenShot());

	}
	
	public void printLog(Status status, String description, Boolean attachImage) {
		if(attachImage) {
			reportingclass.printLog(status, description, takeScreenShot(), attachImage);
		}
		else {
			 printLog(status, description);
		}
		
		if(status.name().equalsIgnoreCase("fail"))
			closeExecution(Status.FAIL);
		
		
	}
	
	public void closeExecution(Status status) {
		reportingclass.closeExecution(status);
		quitDriver();

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
	public void failTestCase(String error) {
		
		printLog(Status.FAIL, error);
		Assert.fail();
		
	}
	
	

}
