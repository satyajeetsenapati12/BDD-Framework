package TestBase;



/*
 * DotTestListener.java 2014 Copyright � 2014, Jade Global, Inc. All rights reserved. This software is the confidential
 * and proprietary information of Jade Global, Inc. ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the terms of the license agreement you entered into
 * with Jade Global, Inc.
 */



import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.exception.ExceptionUtils;
//import org.apache.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.constant.CoreConstants;
import com.testBase.DotTestListener;
import com.xenon.report.Report;
import com.xenon.report.Testcase;

public class DoTTestListener  extends TestListenerAdapter {

	// public Report report;

	private Report report = Report.getInstance();

	private String testCaseStatus = "";

	private static Logger logger = LogManager.getLogger(DotTestListener.class);

	private String testMethod = "";

	public HashMap<String, HashMap<Integer, HashMap<String, String>>> testCaseData = new HashMap<String, HashMap<Integer, HashMap<String, String>>>();

	/*
	 * @see org.testng.TestListenerAdapter#onTestFailure(org.testng.ITestResult)
	 */
	@Override
	public void onTestFailure(ITestResult tr) {
		logger.info(tr.getMethod() + " test case is failed.");
		testCaseStatus = "FAIL";
		setResult(tr, testCaseStatus);
	}

	/*
	 * @see org.testng.TestListenerAdapter#onTestSkipped(org.testng.ITestResult)
	 */
	@Override
	public void onTestSkipped(ITestResult tr) {
		testMethod = tr.getName();
		testCaseStatus = "SKIP";
		CoreConstants.PRE_TESTCASE = testMethod;
		setResult(tr, testCaseStatus);
	}

	/*
	 * @see org.testng.TestListenerAdapter#onTestSuccess(org.testng.ITestResult)
	 */
	@Override
	public void onTestSuccess(ITestResult tr) {
		System.out.println("---On sucess----------------");
		// methodWiseStepCounter.put(CoreConstants.CURRENT_TESTCASE_ID,
		// Report.stepCounter - 1);
		testCaseStatus = "PASS";
		setResult(tr, testCaseStatus);
	}

	/*
	 * @see org.testng.TestListenerAdapter#onTestStart(org.testng.ITestResult)
	 */
	@Override
	public void onTestStart(ITestResult testResult) {
		System.out.println("---On start----------------");

		String testMethod = testResult.getMethod().getMethodName();
		// getTestCaseID(testMethod);

		// Increasing step counter method wise
		/*
		 * if (methodWiseStepCounter.containsKey(CoreConstants.CURRENT_TESTCASE_ID)) {
		 * methodWiseStepCounter.put(CoreConstants.CURRENT_TESTCASE_ID,
		 * (methodWiseStepCounter.get(CoreConstants.CURRENT_TESTCASE_ID) + 1)); } else {
		 * methodWiseStepCounter.put(CoreConstants.CURRENT_TESTCASE_ID, 1); } if
		 * (executedTestMethodsForTestcase.containsKey(CoreConstants.CURRENT_TESTCASE_ID )) { if
		 * (executedTestMethodsForTestcase.get(CoreConstants.CURRENT_TESTCASE_ID)
		 * .contains(testResult.getMethod().getQualifiedName())) {
		 * methodWiseStepCounter.put(CoreConstants.CURRENT_TESTCASE_ID, 1); if
		 * (methodsIntialStepCount.containsKey(testResult.getMethod().getQualifiedName() )) {
		 * methodWiseStepCounter.put(CoreConstants.CURRENT_TESTCASE_ID,
		 * methodsIntialStepCount.get(testResult.getMethod().getQualifiedName())); } else {
		 * methodsIntialStepCount.put(testResult.getMethod().getQualifiedName(),
		 * methodWiseStepCounter.get(CoreConstants.CURRENT_TESTCASE_ID)); } } else { if
		 * (methodsIntialStepCount.containsKey(testResult.getMethod().getQualifiedName() )) {
		 * methodWiseStepCounter.put(CoreConstants.CURRENT_TESTCASE_ID,
		 * methodsIntialStepCount.get(testResult.getMethod().getQualifiedName())); } else {
		 * methodsIntialStepCount.put(testResult.getMethod().getQualifiedName(),
		 * methodWiseStepCounter.get(CoreConstants.CURRENT_TESTCASE_ID)); }
		 * executedTestMethodsForTestcase.get(CoreConstants.CURRENT_TESTCASE_ID)
		 * .add(testResult.getMethod().getQualifiedName()); } } else { ArrayList<String> executedMethods = new
		 * ArrayList<String>(); executedMethods.add(testResult.getMethod().getQualifiedName());
		 * executedTestMethodsForTestcase.put(CoreConstants.CURRENT_TESTCASE_ID, executedMethods); }
		 */

		// finding for dataset
		/*
		 * if (testResult.getParameters() != null && testResult.getParameters().length > 0) { if
		 * (methodDatasetCounter.containsKey(testResult.getMethod().getQualifiedName())) {
		 * methodDatasetCounter.put(testResult.getMethod().getQualifiedName(),
		 * (methodDatasetCounter.get(testResult.getMethod().getQualifiedName()) + 1)); } else {
		 * methodDatasetCounter.put(testResult.getMethod().getQualifiedName(), 1); } } else {
		 * methodDatasetCounter.put(testResult.getMethod().getQualifiedName(), 0); }
		 * System.out.println("******** Testcase Id " + CoreConstants.CURRENT_TESTCASE_ID); System.out.println(
		 * "******** Dataset counter: " + methodDatasetCounter.get(testResult.getMethod().getQualifiedName()));
		 * Report.stepCounter = methodWiseStepCounter.get(CoreConstants.CURRENT_TESTCASE_ID);
		 */

		CoreConstants.CURRENT_TESTCASE = testResult.getMethod().getMethodName();
		System.out.println("onTestStart Current Test Case :- " + CoreConstants.CURRENT_TESTCASE);
		/*
		 * if (CoreConstants.PRODUCTION) { testcase.updateTestcaseStartTime(CoreConstants.XENON_SERVER_WS,
		 * CoreConstants.BUILD_ID, CoreConstants.CURRENT_TESTCASE_ID, CoreConstants.DIRECTORY_NAME); }
		 */

	}

	@Override
	public void onFinish(ITestContext arg0) {

		logger.info("============================On Finish===========================");
		logger.info("All test cases execution finished.Now, clean up in progress");
		Date millis = arg0.getEndDate();

		/*
		 * if (cleanup()) { logger.info("Browser instance closed successfully."); } else {
		 * logger.info("Not quitting browser due to configurations setting."); }
		 */

	}

	/*
	 * private void getTestCaseID(String testMethod) { Set<String> keys = CoreConstants.EXECUTION_TESTCASE.keySet(); for
	 * (String key : keys) { List<String> testMethods = CoreConstants.EXECUTION_TESTCASE.get(key); if
	 * (testMethods.contains(testMethod)) { CoreConstants.CURRENT_TESTCASE_ID = key; break; } } }
	 */

	private void setStepCounter() {
		Set<String> keys = CoreConstants.STEP_COUNTER.keySet();

		/*
		 * if (keys.contains(CoreConstants.CURRENT_TESTCASE_ID)) { CURRENT_STEP_COUNTER =
		 * CoreConstants.STEP_COUNTER.get(CoreConstants.CURRENT_TESTCASE_ID); } else { CURRENT_STEP_COUNTER = 0; }
		 */
	}

	/**
	 * @author dhiraj.bendale
	 * @param tr
	 * @param testCaseStatus
	 */
	private void setResult(ITestResult tr, String status) {

		HashMap<Integer, HashMap<String, String>> testData = new HashMap<Integer, HashMap<String, String>>();
		testCaseData = new HashMap<String, HashMap<Integer, HashMap<String, String>>>();

		// if AssertionError then append message to step description
		if (status.equalsIgnoreCase("FAIL")) {
			if (ExceptionUtils.hasCause(tr.getThrowable(), AssertionError.class)) {
				System.out.println("AssertionError - considering user defined error message");
				report.logs("Step is Failed. " + tr.getThrowable().getMessage(), status);
			} else {
				report.logs("Step is Failed. " + tr.getThrowable().getMessage(), status);

			}
		}

	}

}
