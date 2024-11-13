package com.testBase;


import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtendReporterNG {

	public static ExtentReports getReportObject() {
		 String reportPath = System.getProperty("user.dir") + "/reports/index.html";
	        
	        // Ensure the reports directory exists
	        File reportDir = new File(System.getProperty("user.dir") + "/reports");
	        if (!reportDir.exists()) {
	            reportDir.mkdir();
	        }
		//String reprortPath = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
		reporter.config().setReportName("Automation Result");
		reporter.config().setDocumentTitle("Test Result");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Test Env", "Recvue QE");
		return extent;
	}
}