package in.airtel.generic;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class SeleniumLib 
{
	WebDriver driver;

	/*************constructor**************/
	public SeleniumLib(WebDriver driver)
	{
		this.driver = driver;
	}

	/*******************enter data**********/
	public void enterData(WebElement txtBx, String input)
	{
		if (txtBx.getAttribute("value")!=null)
		{
			txtBx.clear();
			txtBx.sendKeys(input);
		}
		else
		{
			txtBx.sendKeys(input);
		}
	}

	/**************click button***********/
	public void clickButton(WebElement button)
	{
		if(button.getAttribute("type").equals("button"))
		{
			button.click();
		}
		else if(button.getAttribute("type").equals("submit"))
		{
			button.submit();
		}
	}

	/*************click checkbox***********/
	public void clickCheckbox(WebElement checkBx)
	{
		if(checkBx.isSelected())
		{
			Reporter.log("Checkbox is already selected", true);
		}
		else
		{
			checkBx.click();
		}
	}

	/**********select option by text*******/
	public void selectOption(WebElement dropdown, String text)
	{
		Select sel = new Select(dropdown);
		sel.selectByVisibleText(text);
	}

	/*************mouse hover**************/
	public void mouseHover(WebElement element)
	{
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}

	/**************hard code wait************/
	public void hardCodeWait(int secs)
	{
		try
		{
			Thread.sleep(secs*1000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}

	/***************explicit wait*************/
	public void explicitWait(WebElement element, int secs)
	{
		WebDriverWait wait = new WebDriverWait(driver, secs);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/****************verify actual and expected result*******/
	public void validate(String actual, String expected, String passedMsg)
	{
		Assert.assertEquals(actual, expected);
		Reporter.log(passedMsg, true);
	}

	/*****************get element text*****************/
	public String getElementText(WebElement element)
	{
		String text = element.getText();
		return  text;
	}

	/*************get page title**********************/
	public String getPageTitle()
	{
		String title = driver.getTitle();
		return title;
	}

	/*************validate element displayed************/
	public void validateElementDisplayed(WebElement element, String passedMsg)
	{
		Assert.assertTrue(element.isDisplayed());
		Reporter.log(passedMsg, true);
	}

	/***********validate String contains*************/
	public void validateStringContains(WebElement element, String data, String passedMsg)
	{
		Assert.assertTrue(element.getText().contains(data));
		Reporter.log(passedMsg, true);
	}

	/*************Fluent wait*************************/
	public WebElement customWait(WebDriver driver, String xpath)
	{
		FluentWait<WebDriver> fw = new FluentWait<WebDriver>(driver);
		fw.withTimeout(Duration.ofSeconds(20));
		fw.pollingEvery(Duration.ofMillis(10));
		fw.ignoring(NoSuchElementException.class);

		WebElement ele = fw.until(new Function<WebDriver, WebElement>()
		{

			public WebElement apply(WebDriver driver)
			{
				WebElement wb = driver.findElement(By.xpath(xpath));
				return wb;
			}
		});
		return ele;
	}
	
	/*************Scrolling to an element***************/
	public void scrollToElement(WebElement ele)
	{
		String jscode = "arguments[0].scrollIntoView(true)";
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript(jscode, ele);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}