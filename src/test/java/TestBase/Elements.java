package TestBase;





	import java.time.Duration;
	import java.util.concurrent.TimeUnit;

	import org.apache.logging.log4j.LogManager;
	import org.apache.logging.log4j.Logger;
	import org.openqa.selenium.By;
	import org.openqa.selenium.ElementNotInteractableException;
	import org.openqa.selenium.NoSuchElementException;
	import org.openqa.selenium.StaleElementReferenceException;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.FluentWait;
	import org.openqa.selenium.support.ui.Wait;
	import org.openqa.selenium.support.ui.WebDriverWait;

	import com.constant.CoreConstants;
	import com.xenon.report.Report;

	import action.core.ActionPerformer;

	public class Elements {

		private static Logger logger = LogManager.getLogger(Elements.class);

		private String value = "";

		private boolean flag = false;

		@SuppressWarnings("unused")
		private String status = "";

		private static Elements instance = null;

		public Report report;

		protected Elements() {

		} // Exists only to defeat instantiation.

		protected static Elements getInstance() {
			if (instance == null)
				instance = new Elements();
			return instance;
		}

		public String getAttribute(By elementLocator, String attributeType) {
			return getAttributeMethod(elementLocator, attributeType);
		}

		private String getAttributeMethod(By elementLocator, String attributeType) {

			value = CoreConstants.driver.findElement(elementLocator).getAttribute(attributeType);
			if (value.isEmpty()) {
				logger.info("No Value is found for element locator - " + elementLocator);
			}
			return value;
		}

		public String getText(By elementLocator) {
			return getTextMethod(elementLocator);
		}

		private String getTextMethod(By elementLocator) {

			value = CoreConstants.driver.findElement(elementLocator).getText();
			if (value.isEmpty()) {
				logger.info("No Text Value is found for element locator - " + elementLocator);
			}
			return value;

		}

		public String getTagName(By elementLocator) {
			return getTagNameMethod(elementLocator);
		}

		private String getTagNameMethod(By elementLocator) {

			value = CoreConstants.driver.findElement(elementLocator).getTagName();
			if (value.isEmpty()) {
				logger.info("No Tag Name is found for element locator - " + elementLocator);
			}
			return value;
		}

		public boolean visibilityOfElementLocated(By elementLocator) {
			return visibilityOfElementLocatedMethod(elementLocator);
		}

		private boolean visibilityOfElementLocatedMethod(By elementLocator) {

			try {
				CoreConstants.driver.manage().timeouts().implicitlyWait(CoreConstants.DEFAULTTIME, TimeUnit.SECONDS);
				//WebDriverWait wait = new WebDriverWait(CoreConstants.driver, CoreConstants.DEFAULTTIME);
				//wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
				return true;
			} catch (Exception e) {
				logger.error("Element is not found -" + e.getMessage());
				return false;
			}
		}

		public boolean elementToBeClickable(By elementLocator) {
			return elementToBeClickableMethod(elementLocator);
		}

		private boolean elementToBeClickableMethod(By elementLocator) {

			try {
				CoreConstants.driver.manage().timeouts().implicitlyWait(CoreConstants.DEFAULTTIME, TimeUnit.SECONDS);
				WebDriverWait wait = new WebDriverWait(CoreConstants.driver,Duration.ofSeconds(10));
				wait.until(ExpectedConditions.elementToBeClickable(elementLocator));
				return true;
			} catch (Exception e) {
				logger.error("Element is not found -" + e.getMessage());
				return false;
			}
		}

		public boolean elementToBeSelected(By elementLocator) {
			return elementToBeSelectedMethod(elementLocator);
		}

		private boolean elementToBeSelectedMethod(By elementLocator) {

			try {
				CoreConstants.driver.manage().timeouts().implicitlyWait(CoreConstants.DEFAULTTIME, TimeUnit.SECONDS);
				WebDriverWait wait = new WebDriverWait(CoreConstants.driver,Duration.ofSeconds(10));
				return wait.until(ExpectedConditions.elementToBeSelected(elementLocator));
			} catch (Exception e) {
				logger.error("Element is not found -" + e.getMessage());
				return false;
			}
		}

		public boolean invisibilityOfElementLocated(By elementLocator) {
			return invisibilityOfElementLocatedMethod(elementLocator);
		}

		private boolean invisibilityOfElementLocatedMethod(By elementLocator) {

			try {
				CoreConstants.driver.manage().timeouts().implicitlyWait(CoreConstants.DEFAULTTIME, TimeUnit.SECONDS);
				WebDriverWait wait = new WebDriverWait(CoreConstants.driver,Duration.ofSeconds(10));
				return wait.until(ExpectedConditions.invisibilityOfElementLocated(elementLocator));
			} catch (Exception e) {
				logger.error("Element is not found -" + e.getMessage());
				return false;
			}
		}

		public boolean textToBePresentInElementValue(By elementLocator, String value) {
			return textToBePresentInElementValueMethod(elementLocator, value);
		}

		private boolean textToBePresentInElementValueMethod(By elementLocator, String value) {

			try {
				CoreConstants.driver.manage().timeouts().implicitlyWait(CoreConstants.DEFAULTTIME, TimeUnit.SECONDS);
				WebDriverWait wait = new WebDriverWait(CoreConstants.driver,Duration.ofSeconds(10));
				return wait.until(ExpectedConditions.textToBePresentInElementValue(elementLocator, value));
			} catch (Exception e) {
				logger.error("Element is not found -" + e.getMessage());
				return false;
			}
		}

		public boolean isDisplayed(By elementLocator) {
			return isDisplayedMethod(elementLocator);
		}

		private boolean isDisplayedMethod(By elementLocator) {
			flag = false;
			if (visibilityOfElementLocated(elementLocator)) {
				flag = CoreConstants.driver.findElement(elementLocator).isDisplayed();
				if (flag) {
					logger.info("Element locator is displayed.");
					flag = true;
				} else {
					logger.info("Element locator is not displayed." + elementLocator);
					flag = false;
				}
				status = "PASS";
			} else {
				status = "FAIL";
				flag = false;
			}
			return flag;
		}

		public boolean isEnabled(By elementLocator) {
			return isEnabledMethod(elementLocator);
		}

		private boolean isEnabledMethod(By elementLocator) {
			flag = false;
			if (visibilityOfElementLocated(elementLocator)) {
				flag = CoreConstants.driver.findElement(elementLocator).isEnabled();
				if (flag) {
					logger.info("Element locator is Enabled.");
					flag = true;
				} else {
					logger.info("Element locator is not Enabled." + elementLocator);
					flag = false;
				}
				status = "PASS";
			} else {
				status = "FAIL";
				flag = false;

			}
			return flag;
		}

		public boolean isSelected(By elementLocator) {
			return isSelectedMethod(elementLocator);
		}

		private boolean isSelectedMethod(By elementLocator) {
			flag = false;
			if (visibilityOfElementLocated(elementLocator)) {
				flag = CoreConstants.driver.findElement(elementLocator).isSelected();
				if (flag) {
					logger.info("Element locator is Selected.");
					flag = true;
				} else {
					logger.info("Element locator is not Selected" + elementLocator);
					flag = false;
				}
				status = "PASS";
			} else {
				status = "FAIL";
				flag = false;
			}
			return false;
		}

		public void click(String fieldName, By elementLocator) {

			clickMethod(elementLocator);
			// report.logs("Clicked on '" + fieldName + "'", "Click", "Pass", elementLocator.toString(),
			// screenShotType.BROWSER);
			CoreConstants.report.logs("Clicked on '" + fieldName + "'", "PASS");
		}

		public void fluentWaitForElementAndclick(String fieldName, By elementLocator) {

			fluentWaitForElementMethod(elementLocator);
			clickMethod(elementLocator);
			// report.logs("Clicked on '" + fieldName + "'", "Click", "Pass", elementLocator.toString(),
			// screenShotType.BROWSER);
			CoreConstants.report.logs("Clicked on '" + fieldName + "'", "PASS");
		}

		public void click(By elementLocator) {

			clickMethod(elementLocator);
		}

		private void clickMethod(By elementLocator) {

			ActionPerformer action = new ActionPerformer(CoreConstants.driver);

			try {

				action.click(elementLocator);
				logger.info("Clicked on element " + elementLocator);

			} catch (Exception ex) {

				logger.info(ex.getMessage());
				ex.printStackTrace();

			}

		}

		public void fluentWaitAndwebDriverClick(By elementLocator) {
			fluentWaitForElementMethod(elementLocator);
			CoreConstants.driver.findElement(elementLocator).click();
		}

		public WebElement fluentWaitForElement(final By elementLocator) {
			return fluentWaitForElementMethod(elementLocator);
		}

		private WebElement fluentWaitForElementMethod(final By elementLocator) {

			Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(CoreConstants.driver)
			        .withTimeout(Duration.ofSeconds(30))
			        .pollingEvery(Duration.ofSeconds(5))
			        .ignoring(NoSuchElementException.class);


			return fluentWait.until(driver -> driver.findElement(elementLocator));
		}

	}

