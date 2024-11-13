package com.testRunner;

import org.junit.runner.RunWith;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.cucumber.java.Before;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/Features", glue= {"com.stepDefinations"},
					monochrome=true, plugin={"pretty","html:target/cucumber-reports/cucumber-pretty.html",
							                          "rerun:target/cucumber-reports/rerun.txt",
							                          "json:target/JsonReports/report.json",
							                          "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
							                          "timeline:test-output-thread/"
							          				
							
}
				)
					
	
public class TestRunner {
	
	}

 