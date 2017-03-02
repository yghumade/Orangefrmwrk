package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.LeavePage;
import com.relevantcodes.extentreports.LogStatus;

import base.BaseClass;

public class LeavePage extends BaseClass
{
	private static WebDriver driver;
	WebDriverWait wait;

	By ApplyLeaveTabTitle = By.xpath("//h1[text()='Apply Leave']");
	By leaveTypeDropdown = By.id("applyleave_txtLeaveType");
	By leaveFromDate = By.id("applyleave_txtFromDate");
	By leaveToDate = By.id("applyleave_txtToDate");
	By commentTextBox = By.id("applyleave_txtComment");
	By applyBtn = By.id("applyBtn");
	By leaveBalance = By.id("applyleave_leaveBalance");
	By leaveTable = By.xpath("//*[@id='resultTable']/tbody/tr");

	public LeavePage(WebDriver driver)
	{

		LeavePage.driver = driver;
	}

	/**
	 * //method to get text from Leave Page
	 * 
	 * @return
	 */
	public String GetLeavePageText()
	{
		extentLogger.log(LogStatus.INFO, "In GetLeavePageText");
		String ApplyLeavePageText = "";
		ApplyLeavePageText = driver.findElement(ApplyLeaveTabTitle).getText();
		return ApplyLeavePageText;
	}

	/**
	 * @param leaveType
	 * @param fromDate
	 * @param toDate
	 * @param comment
	 * @throws InterruptedException
	 */
	public void applyLeave(String leaveType, String fromDate, String toDate, String comment) throws InterruptedException
	{
		new WebDriverWait(driver, 10);
		WebElement element = driver.findElement(leaveTypeDropdown);
		extentLogger.log(LogStatus.INFO, "found leave type dropdown");
		Select dropdown = new Select(element);
		dropdown.selectByVisibleText(leaveType);
		extentLogger.log(LogStatus.INFO, "selected leave type from the dropdown");
		element = driver.findElement(leaveFromDate);
		element.clear();
		element.sendKeys(fromDate);
		// element.submit();
		extentLogger.log(LogStatus.INFO, "entered fromdate of the leave");
		element = driver.findElement(leaveToDate);
		element.clear();
		element.sendKeys(toDate);
		// element.submit();
		extentLogger.log(LogStatus.INFO, "entered todate of the leave");
		driver.findElement(commentTextBox).sendKeys(comment);
		extentLogger.log(LogStatus.INFO, "entered comments");
		// wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(leaveBalance));
		Thread.sleep(3000);
		String text = driver.findElement(leaveBalance).getText();
		System.out.print(text);

		driver.findElement(applyBtn).click();
		extentLogger.log(LogStatus.INFO, "clicked leave apply button");

	}

	/**
	 * @param date
	 * @return
	 */
	public boolean verifyLeaveCreation(String date)
	{
		// driver.findElement(By.id("menu_leave_viewLeaveModule"));
		driver.findElement(By.xpath("//*[@id='menu_leave_viewMyLeaveList']")).click();
		List<WebElement> tRows = driver.findElements(leaveTable);
		int totalRows = tRows.size();
		int rowNum = 1, colNum;
		boolean recordExists = false;
		for (WebElement trElement : tRows)
		{
			colNum = 1;
			WebElement tCols = trElement.findElement(By.xpath("td[1]"));
			System.out.println(tCols.getText());
			if ((tCols.getText()).equals(date))
			{
				
				  WebElement e = trElement.findElement(By.cssSelector(
				 "select.select_action.quotaSelect")); Select select= new
				 Select(e); select.selectByVisibleText("Approve");
				 driver.findElement(By.id("btnSave")).click();
				 
				recordExists = true;
				break;

			}

			rowNum++;
		}
		return recordExists;

	}
}
