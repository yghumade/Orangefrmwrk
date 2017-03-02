package listners;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.relevantcodes.extentreports.LogStatus;
import base.BaseClass;

//import utility.TestUtil;

public class TestListner extends BaseClass implements ITestListener
{
	String screnShotDirectory = "E:\\TestResults\\Screenshots\\screenshot";
	WebDriver driver;

	public void onFinish(ITestContext arg0)
	{
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext arg0)
	{
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0)
	{
		// TODO Auto-generated method stub

	}

	// this method captures screenshot and attach it to extent report..
	public void onTestFailure(ITestResult result)
	{

		System.out.println("onTestFailure=====> Test Case failed.");
		try
		{

			Date date = new Date(); // creates a date based on current date/time
			// provides a formatting string for your eventual output
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf1 = new SimpleDateFormat("YYYY-MM-DD HH-mm-ss");
			// get the webdriver instance from base class to capture
			// screenshot..
			driver = BaseClass.getDriver();
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File f = new File(screnShotDirectory + sdf.format(date) + "\\" + result.getMethod().getMethodName()
					+ sdf1.format(date) + ".jpg");
			FileUtils.copyFile(src, f);
			String path = f.getAbsolutePath();
			BaseClass.extentLogger.log(LogStatus.FAIL,
					"Test Case Failed" + BaseClass.extentLogger.addScreenCapture(path));
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult arg0)
	{
		// TODO Auto-generated method stub

	}

	public void onTestStart(ITestResult arg0)
	{
		// TODO Auto-generated method stub

	}

	public void onTestSuccess(ITestResult result)
	{

		System.out.println("onTestSuccess ====> Test Case passed.");

	}

}