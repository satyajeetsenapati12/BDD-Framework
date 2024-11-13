package com.testBase;


import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.constant.CoreConstants;




/**
 * 
 * @author dhiraj.bendale
 *
 */
public class ScreenShot {

	private Logger logger = LogManager.getLogger(ScreenShot.class);
	
	private static ScreenShot instance = null;
	
	
	
	public ScreenShot() {

		

	} // Exists only to defeat instantiation.


	

	/**
	 * 
	 * @param dirLocation
	 * @param fileName
	 * @return
	 */

	public String saveBrowserScreenShot(String dirLocation, String fileName) {
		String screenshotPath = "";
		try {
			File dir = new File(dirLocation);
			if (!dir.exists()) {
				if (dir.mkdirs()) {
					logger.info("Screenshot Directory path created successfully.");
				}
			}
			CoreConstants.driver.manage().window().maximize();
			Thread.sleep(1000);
			screenshotPath = dirLocation + "/" + fileName + ".png";
			System.out.println("saveBrowserScreenShot screenshotPath :- " + screenshotPath);
			File pngFile = new File(screenshotPath);
			File screenshot = ((TakesScreenshot) CoreConstants.driver).getScreenshotAs(OutputType.FILE);
			logger.info("Screen shot saved at : " + screenshotPath);
			FileUtils.copyFile(screenshot, pngFile);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return screenshotPath;
	}

	

}
