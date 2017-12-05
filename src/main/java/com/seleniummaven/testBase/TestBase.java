package com.seleniummaven.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.Logger;
import org.openqa.selenium.chrome.*;

import com.seleniummaven.appFunctions.AppSpecificFns;
import com.seleniummaven.helper.Logger.LoggerHelper;
import com.seleniummaven.uiActions.Selenium;

public class TestBase 
{
	//To register the logger 
	private static final Logger logger = Logger.getLogger(TestBase.class);
	
	public static WebDriver driver;
	public static Properties OR = new Properties();
	public static Selenium selenium = new Selenium();
	public static TestBase testbase = new TestBase();
	public static AppSpecificFns fns = new AppSpecificFns();
	public File f1;
	public FileInputStream file;
	public static Config config = new Config(OR);
		
	public void getBrowser(String browser)
	{
		if(System.getProperty("os.name").contains("Window")) //To find the OS
		{
			if(browser.equalsIgnoreCase("firefox"))
			{
				System.setProperty("webdriver.gecko.driver", config.getFirefoxDriver());
				driver = new FirefoxDriver();
				logger.info("Launching Firefox browser");
				//driver.get("http://automationpractice.com/index.php");
				//driver.manage().window().maximize();
			}
			else if (browser.equalsIgnoreCase("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", config.getChromeDriver());
				driver = new ChromeDriver();
				logger.info("Launching Chrome browser");
				driver.manage().window().maximize();
			}
			else if(browser.equalsIgnoreCase("ie"))
			{
				System.setProperty("webdriver.ie.driver", config.getIEDriver());
				driver = new InternetExplorerDriver();
				logger.info("Launching IE browser");
				driver.manage().window().maximize();
			}
			else
			{
				logger.info("Browser name does not match");
			}
		}
		else
		{
			logger.info("Framework doesnt support other than Windows OS");
		}
	}
	
	
	public void loadPropertiesFile() throws IOException 
	{
		//PropertyConfigurator.configure(System.getProperty("user.dir")+
				//"\\src\\main\\java\\com\\seleniummaven\\config\\log4j.properties");
		logger.info("Execution starts");
		//OR = new Properties();
		f1 = new File(System.getProperty("user.dir")+
				"\\src\\main\\java\\com\\seleniummaven\\config\\config.properties");
		file = new FileInputStream(f1);
		OR.load(file);
		logger.info("Loading config.properties");
		
		f1 = new File(System.getProperty("user.dir")+
				"\\src\\main\\java\\com\\seleniummaven\\properties\\objectrepository.properties");
		file = new FileInputStream(f1);
		OR.load(file);
		logger.info("Loading objectrepository.properties");
		
	}
	
/*	public static void main(String[] args) throws IOException 
	{
		TestBase testbase = new TestBase();
		testbase.loadPropertiesFile();
		testbase.getBrowser("firefox");
		//testbase.getScreenShot("IDE");
	}*/

}
