package com.seleniummaven.appFunctions;

import com.seleniummaven.testBase.TestBase;
import com.seleniummaven.uiActions.Selenium;
import com.seleniummaven.uiActions.VerificationHelper;

import java.util.Properties;

import org.apache.log4j.Logger;

import com.seleniummaven.testBase.Config;
//import com.seleniummaven.testBase.TestBase;

public class AppSpecificFns extends TestBase
{
	private static final Logger logger = Logger.getLogger(AppSpecificFns.class);
	//TestBase testbase = new TestBase();
	//Config conf = new Config(OR);
	//Selenium selenium = new Selenium();
	public boolean loginToApp() throws Exception
	{
		selenium.openURL(config.getURL());
		selenium.clickElement("signin");
		selenium.typeText("email", config.getUserName());
		selenium.typeText("password", config.getPassword());
		selenium.clickElement("signinbtn");
		if(VerificationHelper.verifyElementPresent("signout"))
		{
			logger.info("Signin Successfull");
			return true;
		}
		else 
		{
			logger.info("Signin Unsuccessful");
			return false;
		}
	}
	
	public void searchProduct(String locator,String searchText) throws Exception
	{
		selenium.openURL(config.getURL());
		selenium.typeText(locator,searchText);
		logger.info("Searching for "+searchText);
		selenium.clickElement("searchbutton");
	}
	
/*	public static void main(String args[]) throws Exception
	{
		AppSpecificFns as = new AppSpecificFns();
		as.loginToApp();
	}*/
}
