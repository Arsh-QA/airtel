package in.airtel.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import in.airtel.generic.SeleniumLib;

public class DashboardPage {
	SeleniumLib slib;
	public DashboardPage(WebDriver driver){
		slib = new SeleniumLib(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//title[contains(text(),'Services in India')]")
	private WebElement pageTitle;

	@FindBy(xpath="//main[@class='self-account']//h3[text()='Account Overview']")
	private WebElement pageText;

	@FindBy(xpath="(//div[@class='col-sm-3 col-xs-2 p-0'])[1]")
	private WebElement prepaidNumber;

	@FindBy(xpath="//span[contains(text(),'balance')]/../../preceding-sibling::h5//a")
	private WebElement prepaidTileArrow;

	//feature
	public void verifyDashboardText(){
		slib.explicitWait(pageText, 10);
		String Expected = "Account Overview";
		String Actual = slib.getElementText(pageText);
		slib.validate(Actual, Expected, "Dashboard page is verified");
	}

	//feature
	public void verifyPostpaidDashboard(WebDriver driver){
		WebElement ele = slib.customWait(driver, "//h5[contains(text(),'Bills & Payments')]");
		String expected ="Bills & Payments";
		slib.validateStringContains(ele, expected, "Dashboard for postpaid is validated");
	}

	//feature
	public void verifyPrepaidDashboard(WebDriver driver){
		WebElement ele = slib.customWait(driver, "//h5[contains(text(),'Balance & Recharges')]");
		String expected = "Balance & Recharges";
		slib.validateStringContains(ele, expected, "Dashboard for prepaid is validated");
	}

	//step
	public void clickPrepaid(){
		prepaidNumber.click();
	}

	//step
	public void clickPrepaidTileArrow(){
		prepaidTileArrow.click();
	}
}