/*
 * PropertFiles.java 2015
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
package com.xenon.report;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @author dhiraj.bendale
 *
 */
public class PropertyFiles {
	private Logger logger = LogManager.getLogger(PropertyFiles.class);

	private static PropertyFiles instance = null;
	private Utility utility = new Utility();

	protected PropertyFiles() {

	} // Exists only to defeat instantiation.

	public static PropertyFiles getInstance() {
		if (instance == null)
			instance = new PropertyFiles();
		return instance;
	}

	/**
	 * @param filename
	 * @return
	 */
	public HashMap<String, String> readProperty(String filename) {
		logger.info("Reading propertis file.." + filename);
		HashMap<String, String> config = new HashMap<String, String>();
		try {
			InputStream input = PropertyFiles.class.getClassLoader().getResourceAsStream(filename);
			Properties properties = new Properties();
			properties.load(input);
			Enumeration<Object> enuKeys = properties.keys();
			while (enuKeys.hasMoreElements()) {
				String key = (String) enuKeys.nextElement();
				String value = properties.getProperty(key);
				config.put(key.trim(), value.trim());
			}
		} catch (IOException e) {
			logger.error("Problem while properties file...");
			logger.error(e.getMessage());
		}
		utility.verifyPropertyValue(config);
		return config;
	}

	/**
	 * @param filename
	 * @return
	 */
	public HashMap<String, String> readProperty(String[] locatorsFileList) {
		logger.info("Reading propertis file.." + locatorsFileList);
		HashMap<String, String> config = new HashMap<String, String>();
		try {
			Properties merged = new Properties();
			for (String filename : locatorsFileList) {
				InputStream input = PropertyFiles.class.getClassLoader().getResourceAsStream(filename);
				Properties properties = new Properties();
				properties.load(input);
				merged.putAll(properties);
			}
			Enumeration<Object> enuKeys = merged.keys();
			while (enuKeys.hasMoreElements()) {
				String key = (String) enuKeys.nextElement();
				String value = merged.getProperty(key);
				config.put(key.trim(), value.trim());
			}
		} catch (IOException e) {
			logger.error("Problem while properties file...");
			logger.error(e.getMessage());
		}
		return config;
	}

	/**
	 * @param filepath
	 * @param table
	 */
	public void updatePropertyFile(String filepath, HashMap<String, String> table) {
		try {
			FileInputStream in = new FileInputStream(filepath);
			Properties props = new Properties();
			props.load(in);
			in.close();
			FileOutputStream out = new FileOutputStream(filepath);
			Set<String> keys = table.keySet();
			for (String key : keys) {
				props.setProperty(key, table.get(key));
			}
			props.store(out, null);
			out.close();
		} catch (Exception e) {
			logger.info("Not able update Property file... ");
			logger.info(e.getMessage());
		}
	}

	/**
	 * @param filepath
	 * @param table
	 */
	public void createPropertyFile(String filepath, HashMap<String, String> table) {
		try {

			Properties props = new Properties();
			FileOutputStream out = new FileOutputStream(filepath);
			Set<String> keys = table.keySet();
			for (String key : keys) {
				props.setProperty(key, table.get(key));
			}
			props.store(out, null);
			out.close();
		} catch (Exception e) {
			logger.info("Not able create new Property file... ");
			logger.info(e.getMessage());
		}
	}
}