package com.testBase;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.constant.Constants;
import com.constant.CoreConstants;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Driver {

	private static WebDriver selenium;

//	EventFiringWebDriver e_driver;
	private Logger logger = LogManager.getLogger(Driver.class);

	public Driver() {
		logger.info("Setting up the selenium ...");

		// setting up chrome
		
		WebDriverManager.chromedriver().setup();

		// setting download location
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);

		// Put this into prefs map to switch off browser notification
		chromePrefs.put("profile.default_content_setting_values.notifications", 2);
		// chromePrefs.put("download.default_directory", Xenon.configProperties.get("DOWNLOAD_LOCATION"));

		// setting chrome extensions
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-extensions");
		options.addArguments("--disable-infobars");
		options.addArguments("--test-type");
		options.addArguments("--start-maximized");
		options.addArguments("--disable-popup-blocking");

		options.setPageLoadStrategy(PageLoadStrategy.NONE);

		options.addArguments("--disable-default-apps");
		options.setExperimentalOption("prefs", chromePrefs);

		selenium = new ChromeDriver(options);

		selenium.get(Constants.ApplicationURL);
		// Setting default timeout for finding the element instead of 0 sec.
		logger.info("Setting default selenium timout: " + CoreConstants.DEFAULTTIME);
		selenium.manage().timeouts().implicitlyWait(CoreConstants.DEFAULTTIME, TimeUnit.SECONDS);
		selenium.manage().timeouts().pageLoadTimeout(CoreConstants.DEFAULTTIME, TimeUnit.SECONDS);
		selenium.manage().timeouts().setScriptTimeout(120, TimeUnit.SECONDS);
		logger.info("Selenium server setup done successfully ...");
		// logger.info("Xenon is running tests on " + CoreConstants.BROWSER);
		logger.info("Setting up event firing selenium instance");
		// e_driver = new EventFiringWebDriver(selenium);
		// EventHandler eventListenerHandler = new EventHandler();
		// e_driver.register(eventListenerHandler);
		logger.info("Registered successfully..............");
		System.out.println("Browser instantiated successfully");

	}

	public static WebDriver getInstance() {
		new Driver();
		System.out.println("====================GetInstance Class=============");
		return selenium;

	}

	/*
	 * public WebDriver getSelenium() { return e_driver; }
	 */

}
