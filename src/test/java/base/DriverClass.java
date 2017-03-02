package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import utility.PropertiesFileReader;

public class DriverClass
{
	public static  WebDriver driver;
	static String driverPath = "E:\\drivers\\";
	static PropertiesFileReader prreader = new PropertiesFileReader();
	private static String appURL = prreader.getPropertyvalues("APPLICATION_URL");
	
	//sets selenium driver
		public  void setDriver(String browserType)
		{
			switch (browserType)
			{
			case "chrome":
				driver = initChromeDriver();
				break;
			case "firefox":
				driver = initFirefoxDriver();
				break;
			default:
				System.out.println("browser : " + browserType + " is invalid, Launching Firefox as browser of choice..");
				driver = initFirefoxDriver();
			}
		}

		public  WebDriver initChromeDriver()
		{
			System.out.println("Launching google chrome with new profile..");
			;
			System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.navigate().to(appURL);
			return driver;
		}

		public  WebDriver initFirefoxDriver()
		{
			System.out.println("Launching Firefox browser..");
			// System.setProperty("webdriver.gecko.driver", driverPath
			// + "geckodriver.exe");
			WebDriver driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.navigate().to(appURL);
			return driver;
		}
		

}
