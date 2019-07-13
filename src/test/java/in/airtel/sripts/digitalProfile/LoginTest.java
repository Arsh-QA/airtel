package in.airtel.sripts.digitalProfile;

import org.testng.annotations.Test;

import in.airtel.generic.BaseLib;
import in.airtel.generic.ExcelUtilities;
import in.airtel.pageobjects.DashboardPage;
import in.airtel.pageobjects.LoginPage;

public class LoginTest extends BaseLib
{
	@Test(priority=1, description="Login with Postpaid number", enabled=true)
	public void validPostpaidLogin()
	{
		LoginPage lp = new LoginPage(driver);
		String filepath = "./testdata/testData.xlsx";
		String username = ExcelUtilities.readData(filepath, "Sheet1", 1, 0);
		String password = ExcelUtilities.readData(filepath, "Sheet1", 1, 1);
		lp.login(username, password);

		DashboardPage dp = new DashboardPage(driver);
		dp.verifyDashboardText();
		dp.verifyPostpaidDashboard(driver);
	}

	@Test(description="Login with Prepaid number", enabled=false, dependsOnMethods={"validPostpaidLogin"})
	public void validPrepaidLogin()
	{
		LoginPage lp = new LoginPage(driver);
		String filepath = "./testdata/testData.xlsx";
		String username = ExcelUtilities.readData(filepath, "Sheet1", 2, 0);
		String password = ExcelUtilities.readData(filepath, "Sheet1", 2, 1);
		lp.login(username, password);

		DashboardPage dp = new DashboardPage(driver);
		dp.verifyDashboardText();
		dp.verifyPrepaidDashboard(driver);
	}
}