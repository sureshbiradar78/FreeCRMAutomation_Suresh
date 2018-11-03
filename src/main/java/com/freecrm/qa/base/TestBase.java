package com.freecrm.qa.base;
import com.freecrm.qa.utilities.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.freecrm.qa.utilities.TestUtil;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	
	public TestBase() throws IOException
	{
		try
		{
			prop=new Properties();
			FileInputStream ip=new FileInputStream("D:/Selenium/java Examples/FreeCRMTest_Suresh/src/main/java/com/freecrm/qa/config/config.properties");
			prop.load(ip);
		}
	
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

	}
	
	public static void initialization() throws InterruptedException
	{
		String browserName=prop.getProperty("browser");
		if(browserName.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\chromedriver\\chromedriver.exe");
			driver=new ChromeDriver();
		}
		else if(browserName.equals("FF"))
		{
			System.setProperty("Webdriver.gecko.driver", "D:\\Selenium\\geckodriver\\geckodriver.exe" );
			driver=new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
		
	}
}
