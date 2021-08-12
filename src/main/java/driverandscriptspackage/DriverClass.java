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
	private ThreadLocal<ChromeDriver> driver= new ThreadLocal();
	RemoteWebDriver driver1;;
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
			driver.set(new ChromeDriver());

		} else {
			System.setProperty("webdriver.edge.driver", properties.getProperty("edgedriverpath"));
			//driver = new EdgeDriver();
		}
		driver.get().manage().window().maximize();
		driver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public WebDriver getDriver(String s)  {
		if(driver.get()==null)
			try {
				initializeDriver();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return driver.get();
	}
	public WebDriver getDriver() throws MalformedURLException  {
		 System.setProperty("webdriver.chrome.whitelistedIps", "");
		DesiredCapabilities dc = new DesiredCapabilities().chrome();
		dc.setBrowserName("chrome");
		dc.setPlatform(Platform.LINUX);
		ChromeOptions co = new ChromeOptions();
		co.merge(dc);
		co.addArguments("--verbose","--headless","--disable-web-security","--ignore-certificate-errors","--allow-insecure-locathost");
		
	
			return  new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), co);
		
		
		
	}
		

}
