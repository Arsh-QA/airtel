package in.airtel.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import in.airtel.generic.SeleniumLib;

public class PaymentOptionsPage 
{
	SeleniumLib slib;

	public PaymentOptionsPage(WebDriver driver)
	{
		slib = new SeleniumLib(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//button[contains(text(),'VALIDATE & PAY')]")
	private WebElement payBtn;

	@FindBy(xpath="//input[@placeholder='mPin']")
	private WebElement mPin;

	@FindBy(id="notifications")
	private WebElement wrongMpinNotification;

	@FindBy(xpath="//button[contains(text(),'CANCEL')]")
	private WebElement cancelBtn;

	@FindBy(xpath="//button[contains(text(),'Okay')]")
	private WebElement okayBtn;

	@FindBy(xpath="//p[@class='cicle-out']")
	private WebElement walletBalance;

	@FindBy(xpath="//button[contains(text(),'VALIDATE & LOAD MONEY')]")
	private WebElement loadBtn;

	@FindBy(css="input[id='cvv']")
	private WebElement cardCVV;

	@FindBy(xpath="(//button[contains(text(),'CONTINUE TO PAY')])[1]")
	private WebElement continueToPayBtn;

	@FindBy(xpath="//div[text()='Payment Failed. Balance was not loaded in your Airtel Payments Bank account.']")
	private WebElement balanceLoadPopUp;

	@FindBy(xpath="//button[contains(text(),'VALIDATE')]//following-sibling::button")
	private WebElement backBtn;

	@FindBy(xpath="//span[text()='Debit/Credit Card']")
	private WebElement debitCreditCardTab;

	@FindBy(css="input[name='otp']")
	private WebElement directOTPField;

	@FindBy(xpath="//button[text()='Submit']")
	private WebElement directOTPSubmit;

	@FindBy(xpath="//div[text(),'Please enter the correct OTP'] ")
	private WebElement incorrectDirectOTPMsg;
	
	@FindBy(xpath="//button[contains(text(),'Cancel')]")
	private WebElement directOTPCancel;
	
	@FindBy(xpath="//a[contains(text(),'Redirect')] ")
	private WebElement directOTPRedirectURL;

	//step
	public void clickPayBtn(){
		payBtn.click();
	}

	//feature
	public void enterMPin(String mpin){
		slib.enterData(mPin, mpin);
	}

	//feature
	public void validateMPin(){
		slib.explicitWait(wrongMpinNotification, 2);
		String actual = wrongMpinNotification.getText();
		String expected = "You have entered an incorrect mPIN. 5 incorrect entries will block your mPin for 24 hours. If you have forgotten your mPin, please reset it.";
		slib.validate(actual, expected, "Airtel Payments Bank sufficient balance flow validated");
	}

	//step
	public void clickCancel(){
		cancelBtn.click();
	}

	//step
	public void clickOkay(){
		okayBtn.click();
	}

	//feature
	public double walletBal(){
		String balance = slib.getElementText(walletBalance);
		double walletBalance = Double.parseDouble(balance);
		return walletBalance;
	}

	//step
	public void clickLoadBtn(){
		loadBtn.click();
	}

	//feature
	public void enterCVV(){
		slib.scrollToElement(cardCVV);
		slib.enterData(cardCVV, "645");
	}

	//step
	public void clickContinueToPay(){
		continueToPayBtn.click();
	}

	//feature
	public void validateLoadFailText(){
		String actual = balanceLoadPopUp.getText();
		String expected = "Payment Failed. Balance was not loaded in your Airtel Payments Bank account.";
		slib.validate(actual, expected, "The load money fail message validated");
	}

	//step
	public void clickBackBtn(){
		backBtn.click();
	}

	//step
	public void clickDebitCreditTab(){
		debitCreditCardTab.click();
	}

	//feature
	public void enterDirectOTP(){
		slib.enterData(directOTPField, "123987");
	}

	//step
	public void clickDirectOTPSubmitBtn(){
		slib.clickButton(directOTPSubmit);
	}

	//feature
	public void validateIncorrectDirectOTPMsg(){
		String expected = "Please enter the correct OTP";
		String actual = incorrectDirectOTPMsg.getText();
		slib.validate(actual, expected, "Incorrect Direct OTP Message Validated");
	}
	
	//step
	public void clickDirectOTPCancelBtn(){
		slib.clickButton(directOTPField);
	}
	
	//step
	public void clickDirectOTPRedirectURL(){
		directOTPRedirectURL.click();
	}
}