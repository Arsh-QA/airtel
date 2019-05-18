package in.airtel.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import in.airtel.generic.SeleniumLib;

public class HDFCCardPage 
{
	SeleniumLib slib;
	public HDFCCardPage(WebDriver driver)
	{
		slib = new SeleniumLib(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[text()='CANCEL']")
	private WebElement cancelButton;
	
	//step
	public void clickCancelBtn()
	{
		cancelButton.click();
	}
}
