package base;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pages.Dashboard;




public class BaseClass extends DriverClass
{
	public static ExtentReports extent = new ExtentReports(setReportingPath(), true);
	public static ExtentTest extentLogger;

	By UsernameField = By.id("txtUsername");
	By PasswordField = By.id("txtPassword");
	By LoginBtn = By.id("btnLogin");
	By LoginFailedMsg = By.id("spanMessage");

	// Sets extent report file path..
	public static String setReportingPath()
	{
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("YYYY-MM-dd HH-mm-ss");
		String path = "E:\\TestResults\\Reports\\Report" + sdf.format(date) + "\\TestReport" + sdf1.format(date)
				+ ".html";
		return path;
	}

	// login method which returns object of dashboard page..
	public Dashboard LoginToHRM(String UserName, String Password)
	{

		driver.findElement(UsernameField).sendKeys(UserName);
		extentLogger.log(LogStatus.INFO, "UserName entered");
		//extentLogger.info("UserName entered");
		driver.findElement(PasswordField).sendKeys(Password);
		extentLogger.log(LogStatus.INFO, "password entered");
		//extentLogger.info("password entered");
		driver.findElement(LoginBtn).click();
		extentLogger.log(LogStatus.INFO, "Login button clicked");
		//extentLogger.info("Login button clicked");

		return new Dashboard(driver);
	}

	// Returns login failure error message..
	public String GetLoginFailureMessage()
	{
		String LoginErrorMsg = "";
		try
		{

			LoginErrorMsg = driver.findElement(LoginFailedMsg).getText();

		} catch (Exception e)
		{
			System.out.print("Could not find the element" + e.getMessage());
		}
		return LoginErrorMsg;
	}

	public void closeBrowser()
	{
		driver.quit();
	}

	// getDriver() method is called from TestListner class
	public static WebDriver getDriver()
	{
		return driver;
	}

}
