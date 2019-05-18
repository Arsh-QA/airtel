package in.airtel.scripts.payment.prepaid;

import org.testng.annotations.Test;

import in.airtel.generic.BaseLib;
import in.airtel.generic.ExcelUtilities;
import in.airtel.pageobjects.CheckPacksNServicesPage;
import in.airtel.pageobjects.DashboardPage;
import in.airtel.pageobjects.HDFCCardPage;
import in.airtel.pageobjects.LoginPage;
import in.airtel.pageobjects.PaymentOptionsPage;

public class DebitCardPaymentTest extends BaseLib{

	String filepath = "./src/test/resources/testdata/testData.xlsx";

	@Test
	public void cancelRecharge(){
		LoginPage lp = new LoginPage(driver);
		String username = ExcelUtilities.readData(filepath, "Sheet1", 4, 0);
		String password = ExcelUtilities.readData(filepath, "sheet1", 4, 1);
		lp.login(username, password);

		DashboardPage dp = new DashboardPage(driver);
		dp.clickPrepaidTileArrow();

		CheckPacksNServicesPage pns = new CheckPacksNServicesPage(driver);
		pns.clickMostFavouriteRecharge();

		PaymentOptionsPage pop = new PaymentOptionsPage(driver);
		pop.clickDebitCreditTab();
		pop.enterCVV();
		pop.clickContinueToPay();

		if (driver.getCurrentUrl().contains("www.airtel.in")) {
			pop.enterDirectOTP();
			pop.clickDirectOTPSubmitBtn();
			pop.validateIncorrectDirectOTPMsg();
			pop.clickDirectOTPRedirectURL();
			
			HDFCCardPage hcp = new HDFCCardPage(driver);
			hcp.clickCancelBtn();
			driver.switchTo().alert().accept();
			
			
		} else {
			HDFCCardPage hcp = new HDFCCardPage(driver);
			hcp.clickCancelBtn();
			driver.switchTo().alert().accept();
		}






	}
}