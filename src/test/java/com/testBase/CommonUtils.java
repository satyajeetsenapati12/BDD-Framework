package com.testBase;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.constant.CoreConstants;

import action.core.ActionPerformer;



public class CommonUtils extends MainExecutionClass{

	
	ActionPerformer action = new ActionPerformer(CoreConstants.driver);
	

	public void closeAccountSubTabs(String AccountName) {
		timer.pause(15);
		try {
			String SubTab = "//div[@data-aura-class='navexConsoleTabContainer']//div[@class='close slds-col--bump-left slds-p-left--none slds-p-right--none ']";
			List<WebElement> tabs = CoreConstants.driver.findElements(By.xpath(SubTab));
		
			if (tabs.size() != 0) {
				for (int i = 0; i <= tabs.size() - 1; i++) {
					tabs.get(i).click();
					timer.pause(2);
					//report.logs("Clicked on close button of sub-tab", "", "PASS", "closeAccountSubTabs", //screenShotType.BROWSER, //"1");
					CoreConstants.report.logs("Clicked on close button of sub-tab", "PASS");
				}
			}
		}
			catch(Exception e) {
				
			}
		}
	public void scrolltoElementandCheck(String elementToScrollVerifyFieldName, By elementToBeVisible) throws Exception {

		boolean elementFound = false;

		for (int i = 0; i < 7; i++) 
			
		{

			JavascriptExecutor js = ((JavascriptExecutor) CoreConstants.driver);
			js.executeScript("window.scrollTo(0, " + String.valueOf((i + 1) * 500) + ");");
			System.out.println("In for : " + i);

			try 
			{

				timer.pause(2);
				if ((CoreConstants.driver.findElement(elementToBeVisible)).isDisplayed()) 
				{

					System.out.println("In If :");
					elementFound = true;
					break;
				}

			}

			catch (Exception ex) 
			{
				ex.printStackTrace();
			} 
			finally 
			{

			}


		}

		if (elementFound == false) {
			throw new Exception(elementToScrollVerifyFieldName+" Element Not Found in the page even after scrolling");
		}

	}
	
	public void scrolltoElementandCheckBottom(String elementToScrollVerifyFieldName, By elementToBeVisible) throws Exception {

		boolean elementFound = false;

		for (int i = 0; i < 5; i++) 
			
		{

			JavascriptExecutor js = ((JavascriptExecutor) CoreConstants.driver);
			js.executeScript("window.scrollTo(0, " + String.valueOf((i + 1) * 900) + ");");
			System.out.println("In for : " + i);

			try 
			{

				timer.pause(2);
				if ((CoreConstants.driver.findElement(elementToBeVisible)).isDisplayed()) 
				{

					System.out.println("In If :");
					elementFound = true;
					break;
				}

			}

			catch (Exception ex) 
			{
				ex.printStackTrace();
			} 
			finally 
			{

			}


		}

		if (elementFound == false) {
			throw new Exception(elementToScrollVerifyFieldName+" Element Not Found in the page even after scrolling");
		}

	}
	
	public void scrolltoElementandCheckSmall(String elementToScrollVerifyFieldName, By elementToBeVisible) throws Exception {

		boolean elementFound = false;

		for (int i = 0; i < 7; i++) 
			
		{

			JavascriptExecutor js = ((JavascriptExecutor) CoreConstants.driver);
			js.executeScript("window.scrollTo(0, " + String.valueOf((i + 1) * 300) + ");");
			System.out.println("In for : " + i);

			try 
			{

				timer.pause(2);
				if ((CoreConstants.driver.findElement(elementToBeVisible)).isDisplayed()) 
				{

					System.out.println("In If :");
					elementFound = true;
					break;
				}

			}

			catch (Exception ex) 
			{
				ex.printStackTrace();
			} 
			finally 
			{

			}


		}
	}
		
		public void scrolltoElementandCheckSmallShadow(String elementToScrollVerifyFieldName, WebElement elementToBeVisible) throws Exception {

			boolean elementFound = false;

			for (int i = 0; i < 7; i++) 
				
			{

				JavascriptExecutor js = ((JavascriptExecutor) CoreConstants.driver);
				js.executeScript("window.scrollTo(0, " + String.valueOf((i + 1) * 300) + ");");
				System.out.println("In for : " + i);

				try 
				{

					timer.pause(2);
					if ((elementToBeVisible).isDisplayed()) 
					{

						System.out.println("In If :");
						elementFound = true;
						break;
					}

				}

				catch (Exception ex) 
				{
					ex.printStackTrace();
				} 
				finally 
				{

				}


			}

		if (elementFound == false) {
			throw new Exception(elementToScrollVerifyFieldName+" Element Not Found in the page even after scrolling");
		}

	}
	public void scrollDown() throws Exception {
		timer.pause(2);
		JavascriptExecutor js = ((JavascriptExecutor) CoreConstants.driver);
		js.executeScript("window.scrollTo(0, " + String.valueOf((1) * 300) + ");");		
	}
	
	public void closeWorkSpaceTabs()
	{
		try {

			String worSpaceTab = "//div[@aria-label='Workspace tabs for Eats Lightning']//button[contains(@title,'Close')]";

			List<WebElement> tabs = CoreConstants.driver.findElements(By.xpath(worSpaceTab));

			if(tabs.size()!=0)
			{
				for(int i=0;i<=tabs.size()-1;i++)
				{										
					tabs.get(i).click();
					timer.pause(2);

				}
			}

		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		} 

	}

	public By getXpathFromString(String xPath, Object... args) {

		return By.xpath(String.format(xPath, args));

	}

	public static String getValueByKey(String fileName, String parentFieldname, String childFieldname)
			throws FileNotFoundException, IOException {
		String condition1 = "";

		/*
		 * try { //Object obj = parser.parse(new FileReader("./src/main/resources/JSON"
		 * + fileName + ".json")); //JSONObject jsonObject = (JSONObject) obj;
		 * 
		 * //JSONObject enrollmentResponseObject = (JSONObject)
		 * jsonObject.get(parentFieldname); //condition1 = (String)
		 * enrollmentResponseObject.get(childFieldname); } catch (Exception e) {
		 * System.out.println(e); }
		 */

		return condition1;
	}
	
	
	public void scrolltoElementByString(String elementToScrollVerifyFieldName, String xpath) throws Exception {

		boolean elementFound = false;

		for (int i = 0; i < 15; i++) 
		{

			JavascriptExecutor js = ((JavascriptExecutor) CoreConstants.driver);
			js.executeScript("window.scrollTo(0, " + String.valueOf((i + 1) * 200) + ");");
			System.out.println("In for : " + i);

			try 
			{

				timer.pause(2);
				if ((CoreConstants.driver.findElement(By.xpath(xpath)).isDisplayed()))
				{

					System.out.println("In If :");
					elementFound = true;
					break;
				}

			}

			catch (Exception ex) 
			{
				ex.printStackTrace();
			} 
			finally 
			{

			}


		}

		if (elementFound == false) {
			throw new Exception("Element Not Found in the page even after scrolling");
		}

	}

//-------------------------------------------------------------------
	
    
	 public int RandomNumber(int min, int max) {
	      
	        // Generate a random number between min and max
	        Random r = new Random();
	        int randomNumber = r.nextInt((max - min) + 1) + min;

	   //     System.out.println("Random number: " + randomNumber);
			return randomNumber;
	    }
    

}	
