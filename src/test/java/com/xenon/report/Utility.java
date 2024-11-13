package com.xenon.report;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;


import org.testng.Assert;

public class Utility {

	

	/**
	 * @return date
	 * 
	 *         return current date time having format date month year hour min
	 *         sec.
	 */

	public String getCurrentTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public void verifyPropertyValue(HashMap<String, String> config) {
		Set<String> keys = config.keySet();
		for (String key : keys) {
			Assert.assertTrue(!config.get(key).isEmpty(), "Value of " + key + " should not empty.");
		}

	}
}
