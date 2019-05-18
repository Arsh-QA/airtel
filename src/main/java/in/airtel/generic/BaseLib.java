package in.airtel.generic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseLib 
{
	public static WebDriver driver;


	@BeforeClass
	@Parameters({"browser"})
	public static void preClass(String browserName)
	{
		if(browserName.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "./exefiles/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
			Reporter.log("Chrome browser launched", true);
		}

		else if(browserName.equalsIgnoreCase("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "./exefiles/geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().deleteAllCookies();
			Reporter.log("Firefox browser launched", true);
		}

		else if(browserName.equalsIgnoreCase("ie"))
		{
			System.setProperty("webdriver.ie.driver", "./exefiles/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			driver.manage().deleteAllCookies();
			Reporter.log("IE browser launched", true);
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@BeforeMethod
	@Parameters({"baseurl"})
	public static void preCondition(String url)
	{
		driver.navigate().to(url);
		Reporter.log(url+" URL navigated", true);
	}	

	@AfterMethod
	public static void postCondition()
	{
		driver.findElement(By.xpath("//h1/following-sibling::a")).click();
		driver.findElement(By.xpath("//h1/following-sibling::ul//a[text()='Logout']")).click();
	}

	@AfterClass
	public static void postClass()
	{
		driver.close();
		Reporter.log("Browser closed", true);
		if(driver!=null)
		{
			driver.quit();
			Reporter.log("All sessions are closed", true);
		}
	}
}