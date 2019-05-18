package in.airtel.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import in.airtel.generic.SeleniumLib;

public class PrepaidRechargeFailedPage {
	SeleniumLib slib;
	PrepaidRechargeFailedPage(WebDriver driver){
		slib = new SeleniumLib(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//h2//i")
	private WebElement rechargeIcon;
	
	
}
