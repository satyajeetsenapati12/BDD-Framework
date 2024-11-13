package com.xenon.report;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.testng.Reporter;

import com.constant.CoreConstants;



/**
 * 
 * We can call currenttestcase and currentTestcseId in step data function if
 * customer wants update it step information after each step execution .
 * 
 * @author dhiraj.bendale
 *
 */
public abstract class Stepdata extends CoreConstants {

	private HashMap<String, String> stepDetails = new HashMap<String, String>();
	// private Logger logger = Logger.getLogger(Report.class);
	private String actualResult = "";

	private enum StepStatusEnum {
		PASS("1"), FAIL("2"), SKIP("3");

		private String value;

		private StepStatusEnum(String value) {
			this.value = value;
		}

		private static String fromString(String value) {
			if (value != null) {
				for (StepStatusEnum s : StepStatusEnum.values()) {
					if (value.equalsIgnoreCase(s.toString())) {
						return s.value;
					}
				}
			}
			return null;
		}
	}

	public HashMap<String, String> stepData(String stepDescription, String value, String status, String screenshotTitle,
			String screenShotLink) {

		actualResult = "-";
		if (status.equalsIgnoreCase("FAIL")) {
			actualResult = value;
			value = "-";
		}

		stepDetails = new HashMap<String, String>();
		String stepStatusId = stepStatus(status.toUpperCase());
		stepDetails.put("stepDescription", stepDescription);
		stepDetails.put("value", value);
		stepDetails.put("actualResult", actualResult);
		stepDetails.put("status", stepStatusId);
		//stepDetails.put("raiseBug", RAISED_BUG);
		stepDetails.put("takeScreenshot", SCREENSHOT);
		stepDetails.put("screenshotLink", screenShotLink);
		//stepDetails.put("buildId", BUILD_ID);
		stepDetails.put("dirName", DIRECTORY_NAME);
		stepDetails.put("screenshotTitle", screenshotTitle);
		return stepDetails;
	}

	public HashMap<String, String> stepData(String stepDescription, String value, String status, String screenshotTitle,
			String screenShotLink, String iterationCount, String executedTimestamp) {

		actualResult = "-";
		if (status.equalsIgnoreCase("FAIL")) {
			actualResult = value;
			value = "-";
		}

		stepDetails = new HashMap<String, String>();
		String stepStatusId = stepStatus(status.toUpperCase());
		stepDetails.put("stepDescription", stepDescription);
		stepDetails.put("value", value);
		stepDetails.put("actualResult", actualResult);
		stepDetails.put("status", stepStatusId);
		//stepDetails.put("raiseBug", RAISED_BUG);
		stepDetails.put("takeScreenshot", SCREENSHOT);
		stepDetails.put("screenshotLink", screenShotLink);
		//stepDetails.put("buildId", BUILD_ID);
		stepDetails.put("dirName", DIRECTORY_NAME);
		stepDetails.put("screenshotTitle", screenshotTitle);
		stepDetails.put("iterationCount", iterationCount);
		stepDetails.put("executedTimestamp", executedTimestamp);
		
		System.out.println(stepDetails);
		
		String hrefTag = "<a href='../" + screenShotLink + "' target='_blank'>" + "screenshot</a>";
		if(StringUtils.isEmpty(value)) {
			Reporter.log(stepDescription + " " + hrefTag);	
		}else {
			Reporter.log(stepDescription + ", " + value + " " + hrefTag);
		}
		
		return stepDetails;
	}
	private String stepStatus(String status) {
		return StepStatusEnum.fromString(status);
	}
}
