package com.seleniummaven.uiActions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.seleniummaven.testBase.TestBase;
import com.seleniummaven.utilfns.UtilFunctions;

/**
 * @author AyubKhan
 *
 */
public class Selenium extends TestBase
{
	public static final Logger logger = Logger.getLogger(Selenium.class);
	public WebDriverWait wait;
	Alert alert;
	String locatorType,locatorValue,alertmsg,jsoutput;
	public JavascriptExecutor jsexecutor;
	Select select;
	WebElement we;
	
	/**
	 * @param locator
	 * @param delimiter
	 */
	public void getLocatorTypeAndValue(String locator,String delimiter)
	{
		if(locator!=null)
		{
			try
			{
				String split[] = locator.split(delimiter);
				locatorType = split[0];
				locatorValue = split[1];
				logger.info("Getting locator type : "+locatorType+" Getting locator value : "+locatorValue);
			}catch(Exception e)
			{
				logger.info("Exception thrown : "+e.getStackTrace());
			}
			
		}
		else
		{
			logger.info("Locator value is null");
		}
	}
	

	/**
	 * @param locator
	 * @return
	 * @throws Exception
	 */
	public String clickElement(String locator) throws Exception
	{
		if(locator!=null)
		{
			try
			{
				getWebElement(locator).click();
				logger.info("WebElement for the locator is clicked");
				return "success";
			}catch(Exception e)
			{
				return "Element not found for the locator : "+locator;
			}
		}
		else
		{
			logger.info("Locator should not be null");
			return "Locator is null";
		}
	}
	
	/**
	 * @param locator
	 * @param text
	 * @return
	 * @throws Exception
	 */
	public String typeText(String locator,String text) throws Exception
	{
		if(locator!=null)
		{
			try
			{
				getWebElement(locator).sendKeys(text);
				logger.info("Typed "+text+" text");
				return "success";				
			}catch(Exception e)
			{
				logger.info("Element not found for the locator : "+locator);
				return "Element not found for the locator : "+locator;
			}
		}
		else
		{
			logger.info("Locator should not be null");
			return "Locator is null";
		}
	}
	
	/**
	 * @param url
	 */
	public void openURL(String url)
	{
		if(url!=null)
		{
			if(url.toLowerCase().contains("http://"))
			{
				driver.get(url);
				logger.info("Navigating to "+url);
			}
			else
				logger.info("Invalid URL");
		}
		else
			logger.info("URL should not be null");
	}
	
	/**
	 * @param locator
	 * @return
	 * @throws Exception
	 */
	public String getTextFromWebElement(String locator) throws Exception
	{
		try
		{
			logger.info("Getting text of webelement for locator"+locator);
			return getWebElement(locator).getText();
		}catch(Exception e)
		{
			logger.info("Exception thrown : "+e.getStackTrace());
			return null;
		}
	}
	
	/**
	 * @return
	 */
	public String getTitle()
	{
		try
		{
			logger.info("Getting title of current page");
			return driver.getTitle();
		}catch(Exception e)
		{
			logger.info("Exception thrown : "+e.getStackTrace());
			return null;
		}
		
	}
	
	/**
	 * @return
	 */
	public String currentURL()
	{
		try
		{
			logger.info("Getting current URL");
			return driver.getCurrentUrl();
		}catch(Exception e)
		{
			logger.info("Exception thrown : "+e.getStackTrace());
			return null;
		}		
		
	}
	
	/** To close the browser
	 * @author AyubKhan
	 */
	public void browserClose()
	{
		try
		{
			logger.info("Closing the browser");
			driver.close();
		}catch(Exception e)
		{
			logger.info("Exception thrown : "+e.getStackTrace());
		}
	}
	
	/** To take screenshot and image name is saved with format "<IMAGENAME>_dd_MM_yyyy_hh_mm_ss"
	 * @param imageName - Image name
	 * @return - Returns image name in the format "<IMAGENAME>_dd_MM_yyyy_hh_mm_ss"
	 * @throws IOException
	 */
	public static String getScreenShot(String imageName) throws IOException
	{
		if(imageName.equals(""))
		{
			imageName="blank";
		}
		try
		{
			File image =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String actualImageName = System.getProperty("user.dir")+"\\Screenshots\\"
					+imageName+"_"+UtilFunctions.getDateAndTime()+".png";
			FileUtils.copyFile(image, new File(actualImageName));
			return actualImageName;
		}catch(Exception e)
		{
			logger.info("Exception thrown : "+e.getStackTrace());
			return null;
		}
	}	
	
	/**
	 * @param time
	 * @param element
	 * @return
	 */
	public WebElement waitForElement(long time, WebElement element)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, time);
			logger.info("Waiting for element to be clickable");
			return wait.until(ExpectedConditions.elementToBeClickable(element));
		}catch(Exception e)
		{
			logger.info("Exception thrown : "+e.getStackTrace());
			return null;
		}
	}
	
	/**
	 * @param time
	 */
	//Specifies the amount of time the driver should wait when searching for an element if it is not immediately present
	public void setImplicitWait(long time)
	{
		try
		{
			driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
			logger.info("Implicit wait is called");
		}catch(Exception e)
		{
			logger.info("Exception thrown : "+e.getStackTrace());
		}
	}
	
	//Sets the amount of time to wait for a page load to complete before throwing an error
	/**
	 * @param time
	 */
	public void setPageLoadTimeout(long time)
	{
		try
		{
			driver.manage().timeouts().pageLoadTimeout(time, TimeUnit.SECONDS);
			logger.info("Setting page loading timeout");
		}catch(Exception e)
		{
			logger.info("Exception thrown : "+e.getStackTrace());
		}
	}
	
	/**
	 * @param locator
	 * @return
	 * @throws Exception
	 */
	public WebElement getLocator(String locator) throws Exception 
	{
		if(locator!=null)
		{
			try
			{
				String[] split = locator.split(":");
				String locatorType = split[0];
				String locatorValue = split[1];
				if(locatorType.toLowerCase().equals("id"))
					return driver.findElement(By.id(locatorValue));
				else if (locatorType.toLowerCase().equals("name"))
					return driver.findElement(By.name(locatorValue));
				else if(locatorType.toLowerCase().equals("classname") || locatorType.toLowerCase().equals("class"))
					return driver.findElement(By.className(locatorValue));
				else if(locatorType.toLowerCase().equals("tagname") || locatorType.toLowerCase().equals("tag"))
					return driver.findElement(By.tagName(locatorValue));
				else if(locatorType.toLowerCase().equals("linktext") || locatorType.toLowerCase().equals("link"))
					return driver.findElement(By.linkText(locatorValue));
				else if(locatorType.toLowerCase().equals("partiallinktext") || locatorType.toLowerCase().equals("partiallink"))
					return driver.findElement(By.partialLinkText(locatorValue));
				else if(locatorType.toLowerCase().equals("cssselector") || locatorType.toLowerCase().equals("css"))
					return driver.findElement(By.cssSelector(locatorValue));
				else if(locatorType.toLowerCase().equals("xpath"))
					return driver.findElement(By.xpath(locatorValue));
				else
					{
						logger.info("Unknown type locator");
						throw new Exception("Unknown type locator : "+locatorType);
					}
			}catch(Exception e)
			{
				logger.info("Exception thrown : "+e.getStackTrace());
				return null;
			}
		}
		else
		{
			logger.info("Locator cant be null");
			return null;
		}
	}
	
	/**
	 * @param locators
	 * @return
	 * @throws Exception
	 */
	public List<WebElement> getLocators(String locators) throws Exception 
	{
		if(locators!=null)
		{
			try
			{
				String[] split =locators.split(":");
				String locatorType = split[0];
				String locatorValue = split[1];
				if(locatorType.toLowerCase().equals("id"))
					return driver.findElements(By.id(locatorValue));
				else if(locatorType.toLowerCase().equals("class") || 
						locatorType.toLowerCase().equals("classname"))
					return driver.findElements(By.className(locatorValue));
				else if(locatorType.toLowerCase().equals("name"))
					return driver.findElements(By.name(locatorValue));
				else if(locatorType.toLowerCase().equals("tagname") ||
						locatorType.toLowerCase().equals("tag"))
					return driver.findElements(By.tagName(locatorValue));
				else if(locatorType.toLowerCase().equals("linktext") ||
						locatorType.toLowerCase().equals("link"))
					return driver.findElements(By.linkText(locatorValue));
				else if(locatorType.toLowerCase().equals("partiallinktext"))
					return driver.findElements(By.partialLinkText(locatorValue));
				else if(locatorType.toLowerCase().equals("cssselector") || 
						locatorType.toLowerCase().equals("css"))
					return driver.findElements(By.cssSelector(locatorValue));
				else if(locatorType.toLowerCase().equals("xpath"))
					return driver.findElements(By.xpath(locatorValue));
				else
					throw new Exception("Unknown type locator : "+locatorType);
			}catch(Exception e)
			{
				logger.info("Exception thrown : "+e.getStackTrace());
				return null;
			}
		}
		else
		{
			logger.info("Locators cant be null");
			return null;
		}
	}
	
	/**
	 * @param locator
	 * @return
	 * @throws Exception
	 */
	public WebElement getWebElement(String locator) throws Exception
	{
/*		String loc = OR.getProperty(locator);
		WebElement we = getLocator(loc);
		return we;*/
		return getLocator(OR.getProperty(locator));
	}
	
	/**
	 * @param locator
	 * @return
	 * @throws Exception
	 */
	public List<WebElement> getWebElements(String locator) throws Exception
	{
		return getLocators(OR.getProperty(locator));
	}
	
	//Working fine. Is it good approach to use isDisplayed() to check element is clickable or not
	/**
	 * @param seconds
	 * @param locator
	 * @return
	 * @throws Exception
	 */
	public Boolean waitForElementToBeClickable(int seconds, String locator) throws Exception
	{
		try
		{
			wait = new WebDriverWait(driver,seconds);
			getLocatorTypeAndValue(locator, ":");
			if(locatorType.equalsIgnoreCase("id"))
			{
				logger.info("Waiting for webelement to become clickable for the locator type : "+locatorType+
						" locator value : "+locatorValue);
				return wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorValue))).isDisplayed();
				/*WebElement we = wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorValue)));
				if(we!=null)
				{
					System.out.println("Element is clickable");
					return true;
				}
				else
				{
					System.out.println("Element is not clickable");
					return false;
				}*/
			}
			else if(locatorType.equalsIgnoreCase("class") || locatorType.equalsIgnoreCase("classname"))
			{
				logger.info("Waiting for webelement to become clickable for the locator type : "+locatorType+
						" locator value : "+locatorValue);
				return wait.until(ExpectedConditions.elementToBeClickable(By.className(locatorValue))).isDisplayed();
			}
			else if(locatorType.equalsIgnoreCase("name"))
			{
				logger.info("Waiting for webelement to become clickable for the locator type : "+locatorType+
						" locator value : "+locatorValue);
				return wait.until(ExpectedConditions.elementToBeClickable(By.name(locatorValue))).isDisplayed();
			}
			else if(locatorType.equalsIgnoreCase("linktext") || locatorType.equals("link"))
			{
				logger.info("Waiting for webelement to become clickable for the locator type : "+locatorType+
						" locator value : "+locatorValue);
				return wait.until(ExpectedConditions.elementToBeClickable(By.linkText(locatorValue))).isDisplayed();
			}
			else if(locatorType.equalsIgnoreCase("partiallink") || locatorType.equalsIgnoreCase("partiallink"))
			{
				logger.info("Waiting for webelement to become clickable for the locator type : "+locatorType+
						" locator value : "+locatorValue);
				return wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(locatorValue))).isDisplayed();
			}
			else if(locatorType.equalsIgnoreCase("tagname") || locatorType.equalsIgnoreCase("tag"))
			{
				logger.info("Waiting for webelement to become clickable for the locator type : "+locatorType+
						" locator value : "+locatorValue);
				return wait.until(ExpectedConditions.elementToBeClickable(By.tagName(locatorValue))).isDisplayed();
			}
			else if(locatorType.equalsIgnoreCase("cssselector") || locatorType.equalsIgnoreCase("css"))
			{
				logger.info("Waiting for webelement to become clickable for the locator type : "+locatorType+
						" locator value : "+locatorValue);
				return wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locatorValue))).isDisplayed();
			}
			else if(locatorType.equalsIgnoreCase("xpath"))
			{
				logger.info("Waiting for webelement to become clickable for the locator type : "+locatorType+
						" locator value : "+locatorValue);
				return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue))).isDisplayed();
			}
			else
			{
				logger.info("Unknown type locator");
				throw new Exception("Unknown type locator");
			}
		}catch(Exception e)
		{
			logger.info("Exception thrown : "+e.getStackTrace());
			return false;
		}
		
	}
	
	/**
	 * @param seconds
	 * @param locator
	 * @param text
	 * @return
	 * @throws Exception
	 */
	public Boolean waitForTextToAppear(int seconds, String locator, String text) throws Exception 
	{
		try
		{
			wait = new WebDriverWait(driver,seconds);
			getLocatorTypeAndValue(locator, ":");
			if(locatorType.equalsIgnoreCase("id"))
			{
				logger.info("Waiting for text to appear for the locator type : "+locatorType+
						" locator value : "+locatorValue);
				return wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id(locatorValue), text)); 
			}
			else if(locatorType.equalsIgnoreCase("class") || locatorType.equalsIgnoreCase("classname"))
			{
				logger.info("Waiting for text to appear for the locator type : "+locatorType+
						" locator value : "+locatorValue);
				return wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className(locatorValue), text));
			}
			else if(locatorType.equalsIgnoreCase("name"))
			{
				logger.info("Waiting for text to appear for the locator type : "+locatorType+
						" locator value : "+locatorValue);
				return wait.until(ExpectedConditions.textToBePresentInElementLocated(By.name(locatorValue), text));
			}
			else if(locatorType.equalsIgnoreCase("linktext") || locatorType.equals("link"))
			{
				logger.info("Waiting for text to appear for the locator type : "+locatorType+
						" locator value : "+locatorValue);
				return wait.until(ExpectedConditions.textToBePresentInElementLocated(By.linkText(locatorValue), text));
			}
			else if(locatorType.equalsIgnoreCase("partiallink") || locatorType.equalsIgnoreCase("partiallink"))
			{
				logger.info("Waiting for text to appear for the locator type : "+locatorType+
						" locator value : "+locatorValue);
				return wait.until(ExpectedConditions.textToBePresentInElementLocated(By.partialLinkText(locatorValue), text));
			}
			else if(locatorType.equalsIgnoreCase("tagname") || locatorType.equalsIgnoreCase("tag"))
			{
				logger.info("Waiting for text to appear for the locator type : "+locatorType+
						" locator value : "+locatorValue);
				return wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName(locatorValue), text));
			}
			else if(locatorType.equalsIgnoreCase("cssselector") || locatorType.equalsIgnoreCase("css"))
			{
				logger.info("Waiting for text to appear for the locator type : "+locatorType+
						" locator value : "+locatorValue);
				return wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(locatorValue), text));
			}
			else if(locatorType.equalsIgnoreCase("xpath"))
			{
				logger.info("Waiting for text to appear for the locator type : "+locatorType+
						" locator value : "+locatorValue);
				return wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(locatorValue), text));
			}
			else
			{
				logger.info("Unknown type locator");
				throw new Exception("Unknown type locator");
			}
		}catch(Exception e)
		{
			logger.info("Exception thrown : "+e.getStackTrace());
			return false;
		}		
	}
	
	//Working fine. Is it good approach to use isDisplayed() to check element is clickable or not
	/**
	 * @param seconds
	 * @param locator
	 * @return
	 * @throws Exception
	 */
	public boolean waitForElementToBecomeVisible(int seconds,String locator) throws Exception
	{
		try
		{
			wait = new WebDriverWait(driver,seconds);
			getLocatorTypeAndValue(locator, ":");
			if(locatorType.equalsIgnoreCase("id"))
			{
				logger.info("Waiting for Element to become visible for the locator type : "+locatorType+
						" locator value : "+locatorValue);
				return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue))).isDisplayed();
			}
			else if(locatorType.equalsIgnoreCase("class") || locatorType.equalsIgnoreCase("classname"))
			{
				logger.info("Waiting for Element to become visible for the locator type : "+locatorType+
						" locator value : "+locatorValue);
				return wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorValue))).isDisplayed();
			}
			else if(locatorType.equalsIgnoreCase("name"))
			{
				logger.info("Waiting for Element to become visible for the locator type : "+locatorType+
						" locator value : "+locatorValue);
				return wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue))).isDisplayed();
			}
			else if(locatorType.equalsIgnoreCase("linktext") || locatorType.equals("link"))
			{
				logger.info("Waiting for Element to become visible for the locator type : "+locatorType+
						" locator value : "+locatorValue);
				return wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue))).isDisplayed();
			}
			else if(locatorType.equalsIgnoreCase("partiallink") || locatorType.equalsIgnoreCase("partiallink"))
			{
				logger.info("Waiting for Element to become visible for the locator type : "+locatorType+
						" locator value : "+locatorValue);
				return wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locatorValue))).isDisplayed();
			}
			else if(locatorType.equalsIgnoreCase("tagname") || locatorType.equalsIgnoreCase("tag"))
			{
				logger.info("Waiting for Element to become visible for the locator type : "+locatorType+
						" locator value : "+locatorValue);
				return wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorValue))).isDisplayed();
			}
			else if(locatorType.equalsIgnoreCase("cssselector") || locatorType.equalsIgnoreCase("css"))
			{
				logger.info("Waiting for Element to become visible for the locator type : "+locatorType+
						" locator value : "+locatorValue);
				return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue))).isDisplayed();
			} 
			else if(locatorType.equalsIgnoreCase("xpath"))
			{
				logger.info("Waiting for Element to become visible for the locator type : "+locatorType+
						" locator value : "+locatorValue);
				return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue))).isDisplayed();
			} 
			else
			{
				logger.info("Unknown type locator");
				throw new Exception("Unknown type locator");
			}
		}catch(Exception e)
		{
			logger.info("Exception thrown : "+e.getStackTrace());
			return false;
		}
	}
	
	//Working fine
	/**
	 * @param seconds
	 * @param action
	 * @param input
	 * @return
	 */
	public String waitForAlertAndHandle(int seconds,String action,String input)
	{
		try
		{
			wait = new WebDriverWait(driver,seconds);
			wait.until(ExpectedConditions.alertIsPresent());
			logger.info("Alert is displayed");
			alert= driver.switchTo().alert();
			alertmsg = alert.getText();
			logger.info("Message displayed on alert box : "+alertmsg);
			if(action.equalsIgnoreCase("accept"))
			{
				alert.accept();
				logger.info("Clicked on Ok");
			}
			else if(action.equalsIgnoreCase("dismiss"))
			{
				alert.dismiss();
				logger.info("Clicked on Cancel");
			}
			else if(action.equalsIgnoreCase("input"))
			{
				alert.sendKeys(input);
				logger.info("Typed the input : "+input);
				alert.accept();
			}
			return alertmsg;
		}catch(Exception e)
		{
			logger.info("Exception thrown : "+e);
			return "Exception thrown : "+e;
		}
		
	}
	
/*	public String handlePopup()
	{
		String text= driver.switchTo().alert().getText();
		logger.info("Text present on alert : "+text);
		driver.switchTo().
	}*/
	
	//Tested and working fine
	/**
	 * @author AyubKhan
	 * @param locator - Element locator
	 * @return - Returns true if element present else returns false
	 * @throws Exception
	 */
	public boolean isElementPresent(String locator) throws Exception
	{
		try
		{
			getLocatorTypeAndValue(locator, ":");
			if(locatorType.toLowerCase().equals("id"))
			{	 
				logger.info("Finding web element using ID for locator : "+locatorValue);
				return driver.findElements(By.id(locatorValue)).size()!=0 ? true:false;
			}
			else if (locatorType.toLowerCase().equals("name"))
			{
				logger.info("Finding web element using Name for locator : "+locatorValue);
				return driver.findElements(By.name(locatorValue)).size()!=0 ? true:false;
			}
			else if(locatorType.toLowerCase().equals("classname") || locatorType.toLowerCase().equals("class"))
			{
				logger.info("Finding web element using ClassName for locator : "+locatorValue);
				return driver.findElements(By.className(locatorValue)).size()!=0 ? true:false;
			}
			else if(locatorType.toLowerCase().equals("tagname") || locatorType.toLowerCase().equals("tag"))
			{
				logger.info("Finding web element using TagName for locator : "+locatorValue);
				return driver.findElements(By.tagName(locatorValue)).size()!=0 ? true:false;
			}
			else if(locatorType.toLowerCase().equals("linktext") || locatorType.toLowerCase().equals("link"))
			{
				logger.info("Finding web element using LinkText for locator : "+locatorValue);
				return driver.findElements(By.linkText(locatorValue)).size()!=0 ? true:false;
			}
			else if(locatorType.toLowerCase().equals("partiallinktext") || locatorType.toLowerCase().equals("partiallink"))
			{
				logger.info("Finding web element using PartialLinkText for locator : "+locatorValue);
				return driver.findElements(By.partialLinkText(locatorValue)).size()!=0 ? true:false;
			}
			else if(locatorType.toLowerCase().equals("cssselector") || locatorType.toLowerCase().equals("css"))
			{
				logger.info("Finding web element using CSSSelector for locator : "+locatorValue);
				return driver.findElements(By.cssSelector(locatorValue)).size()!=0 ? true:false;
			}
			else if(locatorType.toLowerCase().equals("xpath"))
			{
				logger.info("Finding web element using XPath for locator : "+locatorValue);
				return driver.findElements(By.xpath(locatorValue)).size()!=0 ? true:false;
			}
			else
			{
				logger.info("Unknown type locator");
				throw new Exception("Unknown type locator : "+locatorType);
			}
		}catch(Exception e)
		{
			logger.info("Exception thrown : "+e.getStackTrace());
			return false;
		}
	}
	
	/**
	 * @author AyubKhan
	 * @param script - Java script to be executed
	 * @param outputmode - output if script returns any output, nooutput if script does not return any output 
	 * @return - Returns script output if available else returns null
	 */
	public String executeJS(String script, String outputmode)
	{
		try
		{
			jsexecutor = (JavascriptExecutor) driver;
			if(outputmode.equalsIgnoreCase("output"))
			{
				logger.info("Executing the script : "+script);
				jsoutput= (String)jsexecutor.executeScript(script);
				logger.info("Output : "+jsoutput);
				return jsoutput;
			}
			else if(outputmode.equalsIgnoreCase("nooutput"))
			{
				logger.info("Executing the script : "+script);
				jsexecutor.executeScript(script);
				return null;
			}
			else
			{
				logger.info("Proivde correct output mode");
				return null;
			}
		}catch(Exception e)
		{
			logger.info("Exception thrown : "+e.getStackTrace());
			return null;
		}
		
	}
	
	
	/**
	 * @return - Returns domain name
	 */
	public String getDomain()
	{
		try
		{
			logger.info("Getting the domain name ");
			return executeJS("return document.domain", "output");
		}catch(Exception e)
		{
			logger.info("Exception thrown : "+e.getStackTrace());
			return null;
		}
	}
	
	//Working fine
	/** To select an option from Dropdown or Listbox control
	 * @author AyubKhan
	 * @param locator - Web element locator
	 * @param selectType
	 * 			- Use selectbyvalue if selecting option using value field
	 * 			- Use selectbyindex if selecting option using index
	 * 			- Use selectbyvisibletext if selecting option using visible text
	 * @param input - Input for selectByValue or selectByIndex or selectByVisibleText
	 * @throws Exception
	 */
	public void selectOptionFromDropdown(String locator,String selectType,String input) throws Exception
	{
		try
		{
			logger.info("Getting dropdown/listbox WebElement");
			select = new Select(getWebElement(locator));
			if(selectType.equalsIgnoreCase("selectbyvalue"))
			{
				logger.info("Selecting an option using value : "+input);
				select.selectByValue(input);
			}
			else if(selectType.equalsIgnoreCase("selectbyindex"))
			{
				logger.info("Selecting an option using index : "+input);
				select.selectByIndex(Integer.parseInt(input));
			}
			else if(selectType.equalsIgnoreCase("selectbyvisibletext"))
			{
				logger.info("Selecting an option using visible text : "+input);
				select.selectByVisibleText(input);
			}
			else
				logger.info("Provide the correct SelectType");
		}catch(Exception e)
		{
			logger.info("Exception thrown : "+e);
		}
		
	}
	
	//Working fine
	/** To deselect a option in dropdown or listbox
	 * @author AyubKhan
	 * @param locator - Web element locator
	 * @param selectType
	 * 			- Use deselectbyvalue if deselecting option using value field
	 * 			- Use deselectbyindex if deselecting option using index
	 * 			- Use deselectbyvisibletext if deselecting option using visible text
	 * @param input
	 * @throws Exception 
	 */
	public void deselectOptionFromDropdown(String locator,String selectType,String input) throws Exception
	{
		logger.info("Getting dropdown/listbox WebElement");
		select = new Select(getWebElement(locator));
		try
		{
			if(selectType.equalsIgnoreCase("deselectbyvalue"))
			{
				logger.info("Deselecting an option using value");
				select.deselectByValue(input);
			}
			else if(selectType.equalsIgnoreCase("deselectbyindex"))
			{
				logger.info("Deselecting an option using index");
				select.deselectByIndex(Integer.parseInt(input));
			}
			else if(selectType.equalsIgnoreCase("deselectbyvisibletext"))
			{
				logger.info("Deselecting an option using visible text");
				select.deselectByVisibleText(input);
			}
			else
				logger.info("Provide the correct input");
			
		}catch(Exception e)
		{
			logger.info("Exception thrown : "+e);
		}
	}
	
	//Working fine
	/** To deselect all the options from Dropdown or Listbox
	 * @param locator - Element locator
	 * @throws Exception
	 */
	public void deselectAll(String locator) throws Exception
	{
		try
		{
			select = new Select(getWebElement(locator));
			if(select.isMultiple())
			{
				logger.info("Deselecting all the options from Listbox/Dropdown");
				select.deselectAll();
			}
			else
				logger.info("Not multi select supported");
		}catch(Exception e)
		{
			logger.info("Exception thrown : "+e.getStackTrace());
		}
	}
	
	//Working fine
	/** To check if the listbox or dropdown is multi selecctable
	 * @return - Returns true if the dropdown or listbox is multi selectable else returns false 
	 * @throws Exception 
	 */
	public Boolean isMultiSelectSupported(String locator) throws Exception
	{
		try
		{
			select = new Select(getWebElement(locator));
			logger.info("Is multiple selection supported : "+select.isMultiple());
			return select.isMultiple()==true ? true:false;
		}catch(Exception e)
		{
			logger.info("Exception thrown : "+e.getStackTrace());
			return false;
		}
	}
	
	/** To navigate to specified URL
	 * @author AyubKhan
	 * @param URL
	 */
	public void navigateToURL(String URL)
	{
		if(URL!=null && URL.toLowerCase().contains("http://"))
		{
			try
			{
				logger.info("Navigating to URL : "+URL);
				driver.navigate().to(URL);
			}catch(Exception e)
			{
				logger.info("Exception thrown : "+e.getStackTrace());
			}
		}
		
	}
	
	/** To navigate back from current URL
	 * @author AyubKhan
	 */
	public void navigateBack()
	{
		try
		{
			logger.info("Navigating back");
			driver.navigate().back();
		}catch(Exception e)
		{
			logger.info("Exception thrown : "+e.getStackTrace());
		}
	}
	
	/** To navigate forward from current URL
	 * @author AyubKhan
	 */
	public void navigateForward()
	{
		try
		{
			logger.info("Navigating forward");
			driver.navigate().forward();
		}catch(Exception e)
		{
			logger.info("Exception thrown : "+e.getStackTrace());
		}
	}
	
	/** To switch between windows 
	 * @param windownumber - Specify which window should become active. 
	 * 						 0 - Parent window
	 * 						 1 - child1, 2 - child 2 etc
	 */
	public void handleMultipleWindow(int windownumber)
	{
		try
		{
			logger.info("Getting all the windows");
			Set<String> windows = driver.getWindowHandles();
			logger.info("Switching to window : "+windownumber+1);
			driver.switchTo().window((String) windows.toArray()[windownumber]);
		}catch(Exception e)
		{
			logger.info("Exception thrown : "+e.getStackTrace());
		}
	}
	
	/** To check the web element is enabled 
	 * @param locator - Web element locator
	 * @return - Returns true if the specified web element is enabled else returns false
	 * @throws Exception
	 */
	public Boolean isWebElementEnabled(String locator) throws Exception
	{
		logger.info("Getting Web Element");
		we = getWebElement(locator);
		logger.info("Checking whether webelement is enabled");
		try
		{
			if(we.isEnabled())
			{
				logger.info("Web Element is enabled");
				return true;
			}
			else
			{
				logger.info("Web Element is not enabled");
				return false;
			}
		}catch(Exception e)
		{
			logger.info("Exception thrown : "+e.getStackTrace());
			return false;
		}
	}
	
	/** To check the web element is displayed 
	 * @param locator - Web element locator
	 * @return - Returns true if the specified web element is displayed else returns false
	 * @throws Exception
	 */
	public Boolean isWebElementDisplayed(String locator) throws Exception
	{
		logger.info("Getting Web Element");
		we = getWebElement(locator);
		logger.info("Checking whether webelement is displayed");
		try
		{
			if(we.isDisplayed())
			{
				logger.info("Web Element is displayed");
				return true;
			}
			else
			{
				logger.info("Web Element is not displayed");
				return false;
			}
		}catch(Exception e)
		{
			logger.info("Exception thrown : "+e.getStackTrace());
			return false;
		}
	}
	
	/** To check the web element (This operation only applies to input elements such as checkboxes, 
	 * options in a select and radio buttons) is selected 
	 * @param locator - Web element locator
	 * @return - Returns true if the specified web element is selected else returns false
	 * @throws Exception
	 */
	public Boolean isWebElementSelected(String locator) throws Exception
	{
		logger.info("Getting Web Element");
		we = getWebElement(locator);
		logger.info("Checking whether webelement is selected");
		try
		{
			if(we.isSelected())
			{
				logger.info("Web Element is selected");
				return true;
			}
			else
			{
				logger.info("Web Element is not selected");
				return false;
			}
		}catch(Exception e)
		{
			logger.info("Exception thrown : "+e.getStackTrace());
			return false;
		}
	}
	
/*	public static void main(String args[]) throws IOException
	{
		try{
		Selenium sel = new Selenium();
		TestBase tb = new TestBase();
		tb.loadPropertiesFile();
		tb.getBrowser("firefox");
		//sel.openURL("http://automationpractice.com/index.php");
		System.out.println(sel.title());
		}catch(Exception e)
		{
			System.out.println("Exception : "+e);
		}
		
	}*/
}
