/*
 * FrameworkConstants.java 2014 Copyright � 2014, Jade Global, Inc. All rights reserved. This software is the
 * confidential and proprietary information of Jade Global, Inc. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the terms of the license agreement you entered
 * into with Jade Global, Inc.
 */
package TestBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;

import com.testBase.Driver;
import com.xenon.report.Report;

/**
 * @author dhiraj.bendale
 */
public abstract class CoreConstants {
	// public static String BROWSER = "";

	public static int DEFAULTTIME = 60;

	public static String SCREENSHOTFOLDER = "target/screenshots";

	public static String ApplicationURL = "https://test.salesforce.com/";

	public static String CURRENT_TESTCASE = "";

	// public static HashMap<String, String> CONFIG_DATA;
	public static HashMap<String, String> DATA;

	public static String EXECUTION_PATH = "";

	// public static String BUILD_ID = "";
	public static String DIRECTORY_NAME = "";

	// public static String SCHEMA_NAME = "";
	public static String SCREENSHOTTILE = "";

	// public static String XENON_SERVER_WS = "";
	// public static boolean PRODUCTION = false;
	public static String SCREENSHOT = "";

	// public static String RAISED_BUG = "";
	public static List<String> TEST_EXEC = new ArrayList<String>();

	public static List<String> DATASHEET_TEST_EXEC = new ArrayList<String>();

	// public static boolean DEVELOPMENT_MODE = false;
	// public static HashMap<String, List<String>> EXECUTION_TESTCASE = new HashMap<String, List<String>>();
	public static String PRE_TESTCASE = "";

	// public static String CURRENT_TESTCASE_ID = "";
	public static String RUNTIME_LOG = "";

	public static String LOG_FILE = "";

	// public static String PRIVATE_MODE = "";
	// public static String DOWNLOAD_PATH = "";
	public static String LOG_FILE_PATH = "Logs/logs.txt";

	public static HashMap<String, String> TEST_DESCRIPTION = new HashMap<String, String>();

	public static boolean READ_EXCEL = true;
	// public static Integer currentDataSetCounter = 0;
	// public static String DATASHEET_INFORMATION = "";

	public static WebDriver driver = Driver.getInstance();

	public static Report report = Report.getInstance();

	public static enum screenShotType {
			BROWSER, DESKTOP, MOBILE, NONE
	}

	public static enum stepStaus {
			PASS, FAIL, SKIP
	}

	public static HashMap<String, Integer> STEP_COUNTER = new HashMap<String, Integer>();
}
