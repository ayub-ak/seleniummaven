package com.seleniummaven.utilfns;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;

import com.seleniummaven.testBase.*;

public class UtilFunctions extends TestBase
{
	private final static Logger logger = Logger.getLogger(UtilFunctions.class);
	/** To wait for milliseconds 
	 * @param millisec
	 */
	public static void waitFor(int millisec)
	{
		try
		{
			logger.info("Sleeping for "+millisec+" Milli Seconds");
			Thread.sleep(millisec);
		}catch(InterruptedException e)
		{
			logger.info("Failed to load :"+e);
		}
	}
	
	/** To get current date and time in the format "dd_MM_yyyy_hh_mm_ss"
	 * @return - Returns current date and time in the format "dd_MM_yyyy_hh_mm_ss" 
	 */
	public static String getDateAndTime()
	{
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		return formatter.format(calendar.getTime());
	}
}
