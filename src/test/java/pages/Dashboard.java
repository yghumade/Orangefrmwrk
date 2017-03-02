package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import pages.ApproveLeave;
import pages.LeavePage;

public class Dashboard
{
	private static  WebDriver driver;
	By DashboardText = By.xpath("//h1[text()='Dashboard']");
	By ApplyLeaveLink = By.xpath("//span[text()='Apply Leave']");
	By leaveTab = By.xpath("//*[@id='menu_leave_viewLeaveModule']/b");
	By leaveList = By.id("menu_leave_viewLeaveList");
	
	public Dashboard(WebDriver driver)
	{
		Dashboard.driver = driver;

		
	}
	
	public String GetDashboardText()
	{
		String ActualText = "";
		ActualText = driver.findElement(DashboardText).getText();
				
		return  ActualText;
	}
	
	public LeavePage goToLeavePage()
	{
		
		driver.findElement(ApplyLeaveLink).click();
		return new LeavePage(driver);
	}
	
      public ApproveLeave goToApproveLeavePage(){
		
		
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(leaveTab)).build().perform();
		driver.findElement(leaveList).click();
		
		return new ApproveLeave(driver);
	}
}
