package com.seleniummaven.uiActions;

import org.testng.log4testng.Logger;

import com.seleniummaven.testBase.TestBase;

public class VerificationHelper extends TestBase 
{
	private static final Logger logger = Logger.getLogger(VerificationHelper.class);
	
	
	public static boolean verifyElementPresent(String locator)
	{
		//Selenium selenium = new Selenium();
		boolean isDisplayed = false;
		if(locator!=null)
		{
			try
			{
				isDisplayed= selenium.getWebElement(locator).isDisplayed();
				logger.info("Webelement "+selenium.getWebElement(locator).getText()+" is displayed");
			}catch(Exception e)
			{
				logger.info("Web element not displayed "+e);
			}
		}
		else
			logger.info("Locator should not be null");
		return isDisplayed;
	}
	
	public static boolean verifyElementNotPresent(String locator)
	{
		//Selenium selenium = new Selenium();
		boolean isDisplayed = false;
		if(locator!=null)
		{
			try
			{
				selenium.getWebElement(locator).isDisplayed();
				logger.info("Webelement "+selenium.getWebElement(locator).getText()+" is displayed");
			}catch(Exception e)
			{
				logger.info("Web element not displayed "+e);
				isDisplayed = true;
			}
		}
		else
			logger.info("Locator should not be null");
		return isDisplayed;
	}
	
	public static boolean verifyTextPresent(String locator, String text)
	{
		//Selenium selenium = new Selenium();
		boolean isDisplayed = false;
		if(locator!=null && text!=null)
		{
			try
			{
				if(selenium.getWebElement(locator).getText()==text)
					{
						logger.info("Text : "+text+"is present");
						isDisplayed=true;
					}
				else
				{
					logger.info("Text : "+text+"is not present");
					isDisplayed=false;
				}
			}catch(Exception e)
			{
				logger.info("Exception caught :"+e);
			}
		}
		else
			logger.info("Locator/Text should not be null");
		return isDisplayed;
	}
	
	public boolean verifyTitle(String title)
	{
		boolean isDisplayed=false;
		if(title!=null)
		{
			if(selenium.getTitle().equalsIgnoreCase(title))
			{
				logger.info("Title is matched");
				isDisplayed=true;
			}
			else
			{
				logger.info("Title is not matched");
				return isDisplayed;
			}
			return isDisplayed;
		}
		else
		{
			logger.info("Title should not be null");
			return isDisplayed;
		}
	}
}
