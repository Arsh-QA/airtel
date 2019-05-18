package in.airtel.scripts.payment.prepaid;

import org.testng.annotations.Test;

import in.airtel.generic.BaseLib;
import in.airtel.generic.ExcelUtilities;
import in.airtel.pageobjects.CheckPacksNServicesPage;
import in.airtel.pageobjects.DashboardPage;
import in.airtel.pageobjects.HDFCCardPage;
import in.airtel.pageobjects.LoginPage;
import in.airtel.pageobjects.PaymentOptionsPage;

public class APBPaymentTest extends BaseLib 
{
	String filepath = "./src/test/resources/testdata/testData.xlsx";
	@Test(priority = 1, description = "Sufficient Balance flow through Left Navigation Area", enabled = false)
	public void sufficientBalance(){
		LoginPage lp = new LoginPage(driver);

		String username = ExcelUtilities.readData(filepath, "Sheet1", 3, 0);
		String password = ExcelUtilities.readData(filepath, "Sheet1", 3, 1);
		lp.login(username, password);

		DashboardPage dp = new DashboardPage(driver);
		dp.verifyDashboardText();
		dp.clickPrepaid();

		CheckPacksNServicesPage pns = new CheckPacksNServicesPage(driver);
		String amount = ExcelUtilities.readData(filepath, "Sheet1", 3, 2);
		pns.sendAmount(amount);
		pns.clickRechargeBtn();

		PaymentOptionsPage pop = new PaymentOptionsPage(driver);
		pop.clickPayBtn();
		String mpin = ExcelUtilities.readData(filepath, "Sheet1", 3, 3);
		pop.enterMPin(mpin);
		pop.clickPayBtn();
		pop.validateMPin();
		pop.clickCancel();
		pop.clickOkay();
	}

	@Test(priority = 2, description = "Insufficient Balance flow through Left Navigation Area", enabled = true)
	public void insufficientBalance(){
		LoginPage lp = new LoginPage(driver);
		String username = ExcelUtilities.readData(filepath, "Sheet1", 4, 0);
		String password = ExcelUtilities.readData(filepath, "Sheet1", 4, 1);
		lp.login(username, password);

		DashboardPage dp = new DashboardPage(driver);
		dp.verifyDashboardText();
		dp.clickPrepaid();

		CheckPacksNServicesPage pns = new CheckPacksNServicesPage(driver);
		String amount = ExcelUtilities.readData(filepath, "Sheet1", 4, 2);
		pns.sendAmount(amount);
		pns.clickRechargeBtn();

		PaymentOptionsPage pop = new PaymentOptionsPage(driver);
		pop.clickLoadBtn();
		String mpin = ExcelUtilities.readData(filepath, "Sheet1", 4, 3);
		pop.enterMPin(mpin);
		pop.clickLoadBtn();
		pop.enterCVV();
		pop.clickContinueToPay();

		HDFCCardPage hcp = new HDFCCardPage(driver);
		hcp.clickCancelBtn();
		driver.switchTo().alert().accept();

		pop.validateLoadFailText();
		pop.clickBackBtn();
		pop.clickOkay();
	}
}