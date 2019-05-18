package in.airtel.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import in.airtel.generic.SeleniumLib;

public class CheckPacksNServicesPage 
{
	SeleniumLib slib;
	
	public CheckPacksNServicesPage(WebDriver driver)
	{
		slib = new SeleniumLib(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="offerAmount_web")
	private WebElement enterAmount;
	
	@FindBy(xpath="//button[contains(text(),'Recharge')]")
	private WebElement rechargeBtn;
	
	@FindBy(xpath="(//h4[contains(text(),'Most')]/..//div)[9]//a")
	private WebElement mostFavouriteRecharge;
	
	//feature
	public void sendAmount(String amount)
	{
		slib.enterData(enterAmount, amount);
		
	}
	//step
	public void clickRechargeBtn()
	{
		rechargeBtn.click();
	}
	
	//step
	public void clickMostFavouriteRecharge(){
		mostFavouriteRecharge.click();
	}
}
