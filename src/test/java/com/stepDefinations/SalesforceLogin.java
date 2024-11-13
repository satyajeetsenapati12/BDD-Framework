package com.stepDefinations;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.constant.CoreConstants;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SalesforceLogin {
	
WebDriver driver;

	@Given("Open Browser")
	public void open_browser() {
	   WebDriverManager.chromedriver().setup();
	   WebDriver driver=new ChromeDriver();
	   CoreConstants.driver.manage().window().maximize();
	   CoreConstants.driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	}

	@Then("enter the URL")
	public void enter_the_url() {
	    CoreConstants.driver.get("https://jadeglobal-11b-dev-ed.develop.my.salesforce.com");
	}

	@Then("^enter (.*) and (.*)$")
	public void enter_username_and_password (String username,String password) throws InterruptedException {
	   CoreConstants.driver.findElement(By.xpath("//input[@id='username']")).sendKeys(username);
	   CoreConstants.driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
	   Thread.sleep(2000);
	   CoreConstants.report.logs("Login Page", "PASS");
	   CoreConstants.driver.findElement(By.xpath("//input[@id='Login']")).click();
	}

	@Then("validate user logged in successfully")
	public void validate_user_logged_in_successfully() {
	    String title = CoreConstants.driver.getTitle();
	    System.out.println(title);
	    CoreConstants.report.logs("Home Page", "PASS");
	    Assert.fail("Test Failed");
	    CoreConstants.report.logs("SS taken", "Failed");
	}
}
