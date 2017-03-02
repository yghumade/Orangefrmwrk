package testsuites;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.Dashboard;
import pages.ApproveLeave;
import pages.LeavePage;
import utility.ExcelSheetDataReader;
import utility.PropertiesFileReader;

import com.relevantcodes.extentreports.LogStatus;

import base.BaseClass;
import jxl.read.biff.BiffException;

public class BaseTest extends BaseClass
{
	static PropertiesFileReader prreader = new PropertiesFileReader();
	private static String browserType = prreader.getPropertyvalues("BROWSER_TYPE");
	static BaseClass BC = new BaseClass();
	LeavePage LP = new LeavePage(driver);

	@BeforeMethod
	public void setup()
	{
		BC.setDriver(browserType);
		//extent.addSystemInfo("Build", "V1.0");
	}

	@AfterMethod
	public void tearDown()
	{
		BC.closeBrowser();
		extent.endTest(extentLogger);

		// writing everything to document.
		extent.flush();
	}

	@Test(enabled = false, priority = 1)
	public static void VerifyTestSkip()
	{
		BaseClass.extentLogger = extent.startTest("VerifyTestSkip", "Verify test Skip feature");
		extentLogger.log(LogStatus.INFO, "test1");
		extentLogger.log(LogStatus.SKIP, "Test Case skipped");

	}

	@Test(enabled=false,priority = 2, dataProvider="login")
	public static void VerifyLoginSuccess(String UserName, String Password) throws Exception
	{

		//BaseClass.extentLogger = extent.startTest("VerifyLoginSuccess", "Verify login with valid Credentials");
		//extentLogger.assignCategory("Smoke Test");
		//extentLogger.log(LogStatus.INFO, "Test execution started");
		Dashboard dashboard = BC.LoginToHRM(UserName, Password);
		String ActualText = dashboard.GetDashboardText();
		System.out.print(ActualText);
		BC.closeBrowser();
		if (ActualText.equals("Dashboard"))
		{
			//extentLogger.log(LogStatus.PASS, "Test Case Passed");
		} else
		{

			//extentLogger.log(LogStatus.FAIL, "Test Case Failed");

		}
		Assert.assertEquals(ActualText, "Dashboard");

	}

	@Test(enabled = true, priority = 3)
	public void VerifyLoginFailure() throws BiffException, IOException
	{

		BaseClass.extentLogger = extent.startTest("VerifyLoginFailure", "Verify login with invalid Credentials");
		extentLogger.assignCategory("Smoke Test");
		extentLogger.log(LogStatus.INFO, "Test execution started");
		BC.LoginToHRM("rkelly", "rkelly");
		String ActualText = BC.GetLoginFailureMessage();
		if (ActualText.equals("Invalid credentials"))
		{
			extentLogger.log(LogStatus.PASS, "Test Case Passed");
		} else
		{
			extentLogger.log(LogStatus.FAIL, "Test Case Failed");
			
		}
		Assert.assertEquals(ActualText, "Invalid credentials");
	}

	@DataProvider
	public Object[][] login() throws Exception{
 
         Object[][] testObjArray = ExcelSheetDataReader.getTableArray(".//src//test//resources//testdata//TestAuto.xlsx","Sheet1");
 
         return (testObjArray);
 
		}
	
	@DataProvider
	public Object[][] ApplyLeave() throws Exception{
 
        Object[][] testObjArray = ExcelSheetDataReader.getTableArray(".//src//test//resources//testdata//TestAuto.xlsx","Sheet2");
		 return (testObjArray);
	}
       /* return new Object[][]
				{
						{ "Casual", "2016-11-25", "2016-11-25", "test comment" } };
 
		}*/

	@Test(dataProvider = "ApplyLeave", enabled = false, priority = 4)
	public void VerifyApplyLeave(String leaveType, String fromDate, String toDate, String comment) throws Exception
	{


		BaseClass.extentLogger = extent.startTest("VerifyApplyLeave", "Verify leave apply");
		extentLogger.assignCategory("Regression Test");
		extentLogger.log(LogStatus.INFO, "Test execution started");
		Dashboard dashboard = BC.LoginToHRM("dkelly", "dkelly");
		LeavePage leavepage = dashboard.goToLeavePage();
		leavepage.applyLeave(leaveType, fromDate, toDate, comment);
		boolean bool = leavepage.verifyLeaveCreation(fromDate);
		if (bool)
		{
			extentLogger.log(LogStatus.PASS, "Test Case Passed");
		} else
		{
			extentLogger.log(LogStatus.FAIL, "Test Case Failed");

		}
		Assert.assertTrue(bool);

	}


	@DataProvider
	public Object[][] ApproveLeave() throws Exception{
 
         Object[][] testObjArray = ExcelSheetDataReader.getTableArray(".//src//test//resources//testdata//TestAuto.xlsx","Sheet3");
 
         return (testObjArray);
 
		}
	
	@Test(dataProvider = "ApproveLeave", enabled = false, priority = 5)
	public void VerifyLeaveApproval(String fromDate) throws BiffException, IOException
	{

	
		BaseClass.extentLogger = extent.startTest("VerifyLeaveApproval", "Verify leave approve");
		extentLogger.assignCategory("Regression Test");
		extentLogger.log(LogStatus.INFO, "Test execution started");
		Dashboard dashboard = BC.LoginToHRM("rkelly", "rkelly");
		ApproveLeave approveleave = dashboard.goToApproveLeavePage();
		approveleave.approveLeave(fromDate);
		boolean bool = approveleave.verifyLeaveApproval(fromDate);
		if (bool)
		{
			extentLogger.log(LogStatus.PASS, "Test Case Passed");
		} else
		{
			extentLogger.log(LogStatus.FAIL, "Test Case Failed");

		}
		Assert.assertTrue(bool);

	}

}
