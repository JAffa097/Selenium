package driverandscriptspackage;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class DriverClass {
	private WebDriver driver;
	
	public static FileInputStream file;
	public static Properties properties;

	public void initializeDriver() throws IOException {
		try {

		file = new FileInputStream("src/main/resources/config.properties");
		//Thread.sleep(1000);
		
		properties = new Properties();
		properties.load(file);
		//Thread.sleep(2000);
		
		if ("chrome".equalsIgnoreCase("chrome")) {
			//System.setProperty("webdriver.chrome.driver", properties.getProperty("chromedriverpath"));
			System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Java\\Selenium\\chromedriver_win32\\chromedriver.exe");
			driver =new ChromeDriver();

		} else {
			System.setProperty("webdriver.edge.driver", properties.getProperty("edgedriverpath"));
			//driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public WebDriver getDriver(String port)  {
		if(driver==null)
			try {
				if(port.equals("")||port.equalsIgnoreCase(" "))
				initializeDriver();
				else
					getRemoteDriver(port);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		return driver;
	}
	public void getRemoteDriver(String port) throws MalformedURLException  {
		 System.setProperty("webdriver.chrome.whitelistedIps", "");
		DesiredCapabilities dc = new DesiredCapabilities().chrome();
		dc.setBrowserName("chrome");
		dc.setPlatform(Platform.LINUX);
		ChromeOptions co = new ChromeOptions();
		co.merge(dc);
		co.addArguments("--verbose","--headless","--disable-web-security","--ignore-certificate-errors","--allow-insecure-locathost");
		
	
			driver= new RemoteWebDriver(new URL("http://localhost:"+port+"/wd/hub"), co);
		
		
		
	}
		

}
