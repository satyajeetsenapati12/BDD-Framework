package com.util;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.constant.CoreConstants;

public class Timer {

	private static Logger logger = LogManager.getLogger(Timer.class);

	private static Timer instance = null;

	protected Timer() {

	} // Exists only to defeat instantiation.

	public static Timer getInstance() {
		if (instance == null)
			instance = new Timer();
		return instance;
	}

	public void pause(int secounds) {
		try {
			TimeUnit.SECONDS.sleep(secounds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	
	  public void pageLoad() { pageLoadMethod(); }
	  
	  private void pageLoadMethod() { ExpectedCondition<Boolean> expectation = new
	  ExpectedCondition<Boolean>() { public Boolean apply(WebDriver driver) {
	  return ((JavascriptExecutor)
	  driver).executeScript("return document.readyState").equals("complete"); } };
	  
	  WebDriverWait wait = new WebDriverWait(CoreConstants.driver,Duration.ofSeconds(10));
	  try { wait.until(expectation); } catch (Throwable error) {
	  logger.warn("Timeout waiting for Page Load Request to complete."); } }
	  
	  public void pageLoad(int secounds) { pageLoadMethod(secounds); }
	  
	  private void pageLoadMethod(int secounds) { JavascriptExecutor js =
	  (JavascriptExecutor) CoreConstants.driver;
	 
	  if
	  (js.executeScript("return document.readyState").toString().equals("complete")
	  ) { System.out.println("Page Is loaded."); return; }
	  
	  for (int i = 0; i < secounds; i++) { pause(1); if
	  (js.executeScript("return document.readyState").toString().equals("complete")
	  ) { break; } } }
	 
	
	/*
	 * protected boolean waitForJQueryProcessing(int timeOutInSeconds) { boolean
	 * jQcondition = false; try { new WebDriverWait(driver, timeOutInSeconds) {
	 * }.until(new ExpectedCondition<Boolean>() { public Boolean apply(WebDriver
	 * driverObject) { return (Boolean) ((JavascriptExecutor)
	 * driverObject).executeScript("return jQuery.active == 0");
	 * 
	 * } }); jQcondition = (Boolean) ((JavascriptExecutor)
	 * driver).executeScript("return jQuery.active == 0");
	 * driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS); return
	 * jQcondition; } catch (Exception e) { logger.info(e.getMessage()); } finally {
	 * driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); } return
	 * jQcondition; }
	 */
}
