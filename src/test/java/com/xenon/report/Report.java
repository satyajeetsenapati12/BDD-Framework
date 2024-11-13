
package com.xenon.report;

//import java.util.HashMap;
//import org.apache.log4j.Logger;
//import com.xenon.core.framework.CoreConstants;
//import com.xenon.core.framework.ScreenShot;
//import com.xenon.core.framework.Utility;
//import com.xenon.report.Stepdata;
//
//public class Report extends Stepdata {
//
//	private ScreenShot screenShot = new ScreenShot();
//	public static Integer stepCounter = 1;
//	public static HashMap<Integer, HashMap<String, String>> stepData = new HashMap<Integer, HashMap<String, String>>();
//	private Utility utility = new Utility();
//	private String scrnShotLink = "";
//	private static Report instance = null;
//	private Logger logger = Logger.getLogger(Report.class);
//	private HashMap<String, String> steps = new HashMap<String, String>();
//
//	protected Report() {
//
//	} // Exists only to defeat instantiation.
//
//	public static Report getInstance() {
//		if (instance == null)
//			instance = new Report();
//		return instance;
//	}
//
//	public void logs(String stepDescription, String value, String status, String elementLocator, screenShotType type) {
//		CoreConstants.SCREENSHOTTILE = CoreConstants.CURRENT_TESTCASE + "_" + utility.getCurrentTime();
//		switch (type) {
//	
//		case BROWSER:
//			if (status.equalsIgnoreCase("PASS")) {
//				steps = stepData(stepDescription, value, status, CoreConstants.SCREENSHOTTILE,
//						getBrowserScreenShot(status));
//			} else {
//				steps = stepData(stepDescription, "Object not found " + elementLocator, status,
//						CoreConstants.SCREENSHOTTILE, getBrowserScreenShot(status));
//			}
//			break;
//		
//		case DESKTOP:
//			if (status.equalsIgnoreCase("PASS")) {
//				steps = stepData(stepDescription, value, status, CoreConstants.SCREENSHOTTILE,
//						getDesktopScreenShot(status));
//			} else {
//				steps = stepData(stepDescription, "Object not found " + elementLocator, status,
//						CoreConstants.SCREENSHOTTILE, getDesktopScreenShot(status));
//			}
//			break;
//			
//		case MOBILE:
//			if (status.equalsIgnoreCase("PASS")) {
//				steps = stepData(stepDescription, value, status, CoreConstants.SCREENSHOTTILE,
//						getMobileScreenShot(status));
//			} else {
//				steps = stepData(stepDescription, "Object not found " + elementLocator, status,
//						CoreConstants.SCREENSHOTTILE, getMobileScreenShot(status));
//			}
//			break;
//
//			
//		default:
//			throw new RuntimeException("Unsupported Screnshot type: " + type);
//		}
//		logger.info("Step number is : " + stepCounter);
//		stepData.put(stepCounter, steps);
//		Report.stepCounter++;
//	}
//
//	private String getBrowserScreenShot(String status) {
//		
//		screenShot.getDriverInstance();
//		if (CoreConstants.SCREENSHOT.equalsIgnoreCase("yes")) {
//			scrnShotLink = screenShot.saveBrowserScreenShot(CoreConstants.SCREENSHOTFOLDER,
//					CoreConstants.SCREENSHOTTILE);
//		} else if (CoreConstants.SCREENSHOT.equalsIgnoreCase("onFail") && status.equalsIgnoreCase("ONFAIL")) {
//			scrnShotLink = screenShot.saveBrowserScreenShot(CoreConstants.SCREENSHOTFOLDER,
//					CoreConstants.SCREENSHOTTILE);
//		}
//
//		return scrnShotLink;
//	}
//	
//	private String getMobileScreenShot(String status) {
//		screenShot.getDriverInstance();
//		if (CoreConstants.SCREENSHOT.equalsIgnoreCase("yes")) {
//			scrnShotLink = screenShot.saveMobileScreenShot(CoreConstants.SCREENSHOTFOLDER,
//					CoreConstants.SCREENSHOTTILE);
//		} else if (CoreConstants.SCREENSHOT.equalsIgnoreCase("onFail") && status.equalsIgnoreCase("ONFAIL")) {
//			scrnShotLink = screenShot.saveMobileScreenShot(CoreConstants.SCREENSHOTFOLDER,
//					CoreConstants.SCREENSHOTTILE);
//		}
//
//		return scrnShotLink;
//	}
//
//	private String getDesktopScreenShot(String status) {
//		screenShot.getDriverInstance();
//		if (CoreConstants.SCREENSHOT.equalsIgnoreCase("yes")) {
//			scrnShotLink = screenShot.saveDeskTopScreenShot(CoreConstants.SCREENSHOTFOLDER,
//					CoreConstants.SCREENSHOTTILE);
//		} else if (CoreConstants.SCREENSHOT.equalsIgnoreCase("onFail") && status.equalsIgnoreCase("ONFAIL")) {
//			scrnShotLink = screenShot.saveDeskTopScreenShot(CoreConstants.SCREENSHOTFOLDER,
//					CoreConstants.SCREENSHOTTILE);
//		}
//
//		return scrnShotLink;
//	}
//
//}
//=======
//package com.xenon.utility.selenium;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;

import org.testng.Reporter;

import com.constant.CoreConstants;
import com.testBase.ScreenShot;
import com.xenon.report.Stepdata;

public class Report extends Stepdata {

	private ScreenShot screenShot = new ScreenShot();
	public static Integer stepCounter = 1;
	private Utility utility = new Utility();
	private String scrnShotLink = "";
	private static Report instance = null;

	public Report() {

	} // Exists only to defeat instantiation.

	public static Report getInstance() {
		if (instance == null)
			instance = new Report();
		return instance;
	}

	
	
	private String getBrowserScreenShot(String status) {
		
			scrnShotLink = screenShot.saveBrowserScreenShot(CoreConstants.SCREENSHOTFOLDER,
					CoreConstants.SCREENSHOTTILE);
		
		return scrnShotLink;
	}

	
	public void logs(String stepDescription, String status ) {
		CoreConstants.SCREENSHOTTILE = CoreConstants.CURRENT_TESTCASE + "_" + utility.getCurrentTime();
		System.out.println("Screenshot title formed :- " + CoreConstants.SCREENSHOTTILE);
	//	String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format( new Date() );
		String screenShotLink=getBrowserScreenShot(status);
			/*if (status.equalsIgnoreCase("PASS")) {
				steps = stepData(stepDescription, value, status, CoreConstants.SCREENSHOTTILE,
						getBrowserScreenShot(status), count,timeStamp);
			} else {
				steps = stepData(stepDescription, "Object not found " + elementLocator, status,
						CoreConstants.SCREENSHOTTILE, getBrowserScreenShot(status), count,timeStamp);
			}
		
		logger.info("Step number is : " + stepCounter);
		stepData.put(stepCounter, steps);*/
		Report.stepCounter++;
		
		String hrefTag = "<a href='../" + screenShotLink + "' target='_blank'>" + "screenshot</a>";
		
			Reporter.log(stepDescription + " " + hrefTag);	
		
		
	}
	

}

