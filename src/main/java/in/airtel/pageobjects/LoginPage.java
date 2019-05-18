package in.airtel.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import in.airtel.generic.SeleniumLib;

public class LoginPage
{
	SeleniumLib slib;
	
	@FindBy(id="number-one")
	private WebElement userName;
	
	@FindBy(id="pasword")
	private WebElement pasword;
	
	@FindBy(id="loginButtonSpan")
	private WebElement loginBtn;
	
	
	public LoginPage(WebDriver driver)
	{
		slib = new SeleniumLib(driver);
		PageFactory.initElements(driver, this);
	}
	
	//feature
	public void login(String username,String password)
	{
		slib.enterData(userName, username);
		slib.enterData(pasword, password);
		loginBtn.click();
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
