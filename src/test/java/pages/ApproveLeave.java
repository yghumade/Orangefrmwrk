package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.LogStatus;

import base.BaseClass;
import pages.ApproveLeave;

public class ApproveLeave extends BaseClass
{
	private static WebDriver driver;

	By pendingLeaveTable = By.xpath("//*[@id='resultTable']/tbody/tr");

	public ApproveLeave(WebDriver driver)
	{
		ApproveLeave.driver = driver;
	}

	/**
	 * @param date
	 */
	public void approveLeave(String date)
	{

		List<WebElement> tRows = driver.findElements(pendingLeaveTable);
		int totalRows = tRows.size();
		int rowNum = 1, colNum;
		for (WebElement trElement : tRows)
		{
			colNum = 1;
			WebElement tCols = trElement.findElement(By.xpath("td[1]"));
			System.out.println(tCols.getText());
			if ((tCols.getText()).equals(date))
			{
				extentLogger.log(LogStatus.INFO, "found the leave request to approve");
				WebElement e = trElement.findElement(By.cssSelector("select.select_action.quotaSelect"));
				Select select = new Select(e);
				select.selectByVisibleText("Approve");
				driver.findElement(By.id("btnSave")).click();
				extentLogger.log(LogStatus.INFO, "leave is approved");
				break;

			}

			rowNum++;
		}

	}

	/**
	 * @param date
	 * @return
	 */
	public boolean verifyLeaveApproval(String date)
	{
		driver.findElement(By.id("leaveList_chkSearchFilter_checkboxgroup_allcheck")).click();
		driver.findElement(By.id("btnSearch")).click();
		List<WebElement> tRows = driver.findElements(pendingLeaveTable);
		int totalRows = tRows.size();
		int rowNum = 1, colNum;
		for (WebElement trElement : tRows)
		{
			colNum = 1;
			WebElement tCols = trElement.findElement(By.xpath("td[1]"));
			System.out.println(tCols.getText());
			if ((tCols.getText()).equals(date))
			{
				String s1 = trElement.findElement(By.xpath("td[6]")).getText();
				if (s1.contains("Scheduled") || s1.contains("Taken"))
				{
					return true;
				} else
				{
					return false;
				}

			}

			rowNum++;
		}

		return true;
	}
}
