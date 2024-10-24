package StepDefinations;

import java.util.concurrent.TimeUnit;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.constant.CoreConstants;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SalesforceLogin {

	WebDriver driver =null;
	
	@Given("open the browser")
	public void open_the_browser() {
		WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
	    driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	}

	@When("enter the url")
	public void enter_the_url() {
	    driver.get("https://jadeglobal-11b-dev-ed.develop.my.salesforce.com/");
	}

	@And("enter username and password")
	public void enter_username_and_password() throws InterruptedException {
	  driver.findElement(By.xpath("//input[@id='username']")).sendKeys("Satyajeet12@jadeglobal.com");
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Satyajeet@1234");
	}
	@Then("click on login button")
	public void click_on_login_button() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='Login']")).click();	
		CoreConstants.report.logs("Taken", "PASS");
	}
	@Then("navigated to homepage successfully")
		public void navigated_to_homepage_successfully() throws InterruptedException {
			Thread.sleep(2000);
			String title = driver.getTitle();
			System.out.println(title);
		}
	}
	


