package TestBase;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.constant.CoreConstants;

import page.Loginpage;


public class BaseTest {

	public static WebDriver driver;
	public static Properties prop;
	public static ExtentSparkReporter sparkreporter;
	public static ExtentReports extendreports;
	public static ExtentTest extendtest;
	public Loginpage login;

	public BaseTest() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/resources/configuration/credentials.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@BeforeSuite
	public void suiteSetup() {
		File directory = new File(System.getProperty("user.dir") + "/reports");
        if (directory.exists() && directory.isDirectory()) {
            deleteDirectoryContents(directory);
        }
	}
	
	@BeforeTest
	public void setup() {
		driver = initialization();
		login = new Loginpage();
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
	private static void deleteDirectoryContents(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirectoryContents(file);
                }
                file.delete();
            }
        }
    }

	public static WebDriver initialization() {
		String browserName = prop.getProperty("browser");
		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "D:/IT/exe/chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if (browserName.equalsIgnoreCase("firefox")) {
			//System.setProperty("webdriver.chrome.driver", "D:/IT/exe/chromedriver.exe");
			driver = new FirefoxDriver();
          
        }
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		driver.get(prop.getProperty("url"));
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}

	public String getScreenShot(String testCaseName, WebDriver driver2) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) CoreConstants.driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String targetPath = System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
		File file = new File(targetPath);
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
	}
	public void setUp() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream("src/main/resources/configuration/parellel.properties");
		prop.load(fis);

		String browser = prop.getProperty("browser");
		String hubUrl = prop.getProperty("hubUrl");
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

}
