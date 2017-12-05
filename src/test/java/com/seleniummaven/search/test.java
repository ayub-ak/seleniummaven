package com.seleniummaven.search;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.seleniummaven.appFunctions.AppSpecificFns;
import com.seleniummaven.helper.Logger.LoggerHelper;
import com.seleniummaven.testBase.TestBase;

public class test 
{
	AppSpecificFns as = new AppSpecificFns();
	private final static Logger logger = LoggerHelper.getLogger(test.class);
	
	@BeforeClass
	public void setUp() throws Exception
	{
		TestBase tb = new TestBase();
		tb.loadPropertiesFile();
		tb.getBrowser("firefox");
	}
	
	@Test
	public void testmethod() throws Exception 
	{
		/*logger.info("Test msg 1"); */
		boolean val =as.loginToApp();
		System.out.println("Value : "+val);
	}
}
