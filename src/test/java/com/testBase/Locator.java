/*
 * Locator.java 2015
 * 
 * Copyright � 2014, Jade Global, Inc. All rights reserved.
 * 
 * This software is the confidential and proprietary information of Jade Global, Inc.
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you entered into with Jade Global,
 * Inc.
 */

/**
 * @author dhiraj.bendale
 */

package com.testBase;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;

public class Locator {

	private static Logger logger = LogManager.getLogger(Locator.class);
	private static Locator instance = null;

	private enum locatorType {
		ID, XPATH, LINK, CSSSELECTOR, NAME, CLASSNAME
	};
	// Exists only to defeat instantiation.

	public static Locator getInstance() {
		if (instance == null)
			instance = new Locator();
		return instance;
	}

	public By getLocator(String elementLocator) {
		By by = getLocatorValue(elementLocator);
		return by;
	}

	private By getLocatorValue(String locatorvalue) {
		String[] value = getLocatorType(locatorvalue);
		By by = null;
		if (value.length < 2) {
			Assert.fail("Please check element locator format." + locatorvalue);
		}
		switch (locatorType.valueOf((value[0]).toUpperCase())) {
		case ID:
			by = By.id(value[1]);
			break;
		case XPATH:
			by = By.xpath(value[1]);
			break;
		case LINK:
			by = By.linkText(value[1]);
			break;
		case NAME:
			by = By.name(value[1]);
			break;
		case CSSSELECTOR:
			by = By.cssSelector(value[1]);
			break;
		case CLASSNAME:
			by = By.className(value[1]);
			break;
		default:
			logger.error("Please check element locator format.");
			Assert.fail("Please check element locator format." + locatorvalue);
			break;
		}
		return by;
	}

	private String[] getLocatorType(String locatorwithValue) {
		String[] values = null;
		if (locatorwithValue.contains("#")) {
			values = locatorwithValue.split("#");
		} else {
			Assert.fail("Please check element locator - " + locatorwithValue);
		}
		return values;
	}
}
