package com.testBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class TestBase {
	protected WebDriver driver;
	private Properties props;

	@BeforeClass
	public void setUp() throws IOException {
		props = new Properties();
		FileInputStream fis = new FileInputStream("src/main/resources/configuration/parellel.properties");
		props.load(fis);

		String browser = props.getProperty("browser");
		String hubUrl = props.getProperty("hubUrl");
		System.out.println("browser"+browser);
		System.out.println("hubUrl"+hubUrl);
		try {
			if (browser.equalsIgnoreCase("chrome")) {
				ChromeOptions options = new ChromeOptions();
				driver = new RemoteWebDriver(new URL(hubUrl), options);
			} else if (browser.equalsIgnoreCase("firefox")) {
				FirefoxOptions options = new FirefoxOptions();
				driver = new RemoteWebDriver(new URL(hubUrl), options);
			}
			else
			{
				throw new IllegalArgumentException("Unsupported browser: " + browser);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 throw new RuntimeException("Invalid hub URL", e);
		}
		catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create WebDriver session", e);
        }
	}

	@AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
