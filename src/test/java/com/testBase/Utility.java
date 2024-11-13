/*
Utility.java 2014
 * 
 * Copyright � 2014, Jade Global, Inc. All rights reserved.
 * 
 * This software is the confidential and proprietary information of Jade Global, Inc.
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you entered into with Jade Global,
 * Inc.
 * 
 */

package com.testBase;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.constant.CoreConstants;
/*import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;*/

/**
 * @author dhiraj.bendale
 * 
 */
public class Utility {

	private Logger logger = LogManager.getLogger(Utility.class);

	// #############################
	public static HashMap<Integer, HashMap<String, String>> stepData = new HashMap<Integer, HashMap<String, String>>();

	/**
	 * @return date
	 * 
	 *         return current date time having format date month year hour min
	 *         sec.
	 */

	public String getCurrentTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	/**
	 * @return
	 */
	public String getCurrentDate(String dateformat) {
		DateFormat dateFormat = new SimpleDateFormat(dateformat);
		Date date = new Date();
		return dateFormat.format(date);
	}

	/**
	 * @return
	 */
	public String getCurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat("ddMMMyyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}

	/**
	 * @param temp
	 * @param key
	 * @return
	 */
	public boolean listContains(Set<String> temp, String key) {
		for (String value : temp) {
			if (value.equalsIgnoreCase(key)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param filepath
	 * @param values
	 */
	public void modifyXML(String filepath, List<String> values) {
		logger.info("Start updating XML...");
		// try {
		// DocumentBuilderFactory docFactory =
		// DocumentBuilderFactory.newInstance();
		// DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		// Document doc = docBuilder.parse(filepath);
		//
		// NodeList nodes = doc.getElementsByTagName("include");
		// if (nodes.getLength() > 0) {
		// for (int i = 0; i < nodes.getLength(); i++) {
		// NamedNodeMap attr = nodes.item(i).getAttributes();
		// Node nodeAttr = attr.getNamedItem("name");
		// String nodeValue = nodeAttr.getNodeValue().trim();
		// if (FindinList(values, nodeValue)) {
		// nodeAttr.setNodeValue(nodeValue);
		// doc.re(nodes.item(i), "include");
		// } else {
		// doc.renameNode(nodes.item(i), null, "exclude");
		// nodeAttr.setNodeValue(nodeValue);
		// i--;
		// }
		// }
		// }
		// nodes = doc.getElementsByTagName("exclude");
		// if (nodes.getLength() > 0) {
		// for (int i = 0; i < nodes.getLength(); i++) {
		// NamedNodeMap attr = nodes.item(i).getAttributes();
		// Node nodeAttr = attr.getNamedItem("name");
		// String nodeValue = nodeAttr.getNodeValue().trim();
		// if (FindinList(values, nodeValue)) {
		// doc.renameNode(nodes.item(i), null, "include");
		// nodeAttr.setNodeValue(nodeValue);
		// i--;
		// } else {
		// nodeAttr.setNodeValue(nodeValue);
		// }
		// }
		// }
		// TransformerFactory transformerFactory =
		// TransformerFactory.newInstance();
		// Transformer transformer = transformerFactory.newTransformer();
		// DOMSource source = new DOMSource(doc);
		// StreamResult result = new StreamResult(new File(filepath));
		// transformer.transform(source, result);
		// logger.info("XML is updated...");
		// } catch (Exception e) {
		// logger.info("Problem in Updating XML file.");
		// logger.info(e.toString());
		// }
	}

	/**
	 * @param list
	 * @param value
	 * @return
	 */
	public boolean FindinList(List<String> list, String value) {
		for (String val : list) {
			if (val.equalsIgnoreCase(value)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param arrayList
	 * @return
	 */
	public List<String> ConvertArrayTOList(String[] arrayList) {
		List<String> list = new ArrayList<String>();
		for (String value : arrayList) {
			list.add(value);
		}
		return list;
	}

	/**
	 * @param caseLists
	 * @return
	 */
	public String ConvertListToString(List<String> caseLists) {
		StringBuilder test = new StringBuilder();

		String sep = ",";
		test.append(caseLists.get(0));
		for (int i = 1; i < caseLists.size(); i++) {
			test.append(sep).append(caseLists.get(i));
		}
		return test.toString();
	}

	/**
	 * @param strArr
	 * @return
	 */
	public ArrayList<String> convertStringArrayToArraylist(String[] strArr) {
		ArrayList<String> stringList = new ArrayList<String>();
		for (String s : strArr) {
			stringList.add(s);
		}
		return stringList;
	}

	// Generate random number.
	public String randomNumberInString(int len) {
		String number = "";
		Random random = new Random();
		for (int i = 0; i < len; i++) {
			number = number + (random.nextInt(10));
		}
		return number;
	}

	/*
	 * Change date format
	 * 
	 * @Date , OldFormat ,newdateFormat
	 */
	public String changeDateFormat(String dateStr, String oldDateFormat, String newDateFormat) {
		logger.info("Convert date format from " + oldDateFormat + " to " + newDateFormat);
		try {
			DateFormat srcDf = new SimpleDateFormat(oldDateFormat);
			Date date = srcDf.parse(dateStr);
			DateFormat destDf = new SimpleDateFormat(newDateFormat);
			dateStr = destDf.format(date);
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}
		return dateStr;
	}

	/**
	 * @param directoryName
	 */
	public void checkFolder(String directoryName) {
		logger.info("Checking directory.." + directoryName);
		File theDir = new File(directoryName);
		if (theDir.exists()) {
			theDir.delete();
		}
		logger.info("Creating new directory: " + directoryName);
		theDir.mkdir();
	}

	/**
	 * @param directoryName
	 */
	public void createFolder(String directoryName) {
		logger.info("Checking directory.." + directoryName);
		File theDir = new File(directoryName);
		if (theDir.exists()) {
			theDir.delete();
		}
		logger.info("Creating new directory: " + directoryName);
		theDir.mkdir();

	}

	public void copyFolder(String srcFolderPath, String destFolderPath) {
		File srcFolder = new File(srcFolderPath);
		File destFolder = new File(destFolderPath);

		// make sure source exists
		if (!srcFolder.exists()) {

			System.out.println("Directory does not exist.");
			// just exit
			System.exit(0);

		} else {

			try {
				copyFolder(srcFolder, destFolder);
			} catch (Exception e) {
				e.printStackTrace();
				// error, just exit
			}
		}

		System.out.println("Done");
	}

	public void copyFolder(File src, File dest) throws IOException {

		if (src.isDirectory()) {

			// if directory not exists, create it
			if (!dest.exists()) {
				dest.mkdir();
				System.out.println("Directory copied from " + src + "  to " + dest);
			}

			// list all the directory contents
			String files[] = src.list();

			for (String file : files) {
				// construct the src and dest file structure
				File srcFile = new File(src, file);
				File destFile = new File(dest, file);
				// recursive copy
				copyFolder(srcFile, destFile);
			}

		} else {
			// if file, then copy it
			// Use bytes stream to support all file types
			InputStream in = new FileInputStream(src);
			OutputStream out = new FileOutputStream(dest);

			byte[] buffer = new byte[1024];

			int length;
			// copy the file content in bytes
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}

			in.close();
			out.close();
			logger.info("File copied from " + src + " to " + dest);
		}
	}

	public void getTestCase(String testCases) {
		if (testCases.contains(",")) {
			String[] testCasesExec = testCases.split(",");
			CoreConstants.TEST_EXEC = Arrays.asList(testCasesExec);
		} else {
			CoreConstants.TEST_EXEC.add(testCases);
		}
	}

	public void getDatasheetTestCase(String testCases) {
		if (testCases.contains(",")) {
			String[] testCasesExec = testCases.split(",");
			CoreConstants.DATASHEET_TEST_EXEC = Arrays.asList(testCasesExec);
		} else {
			CoreConstants.DATASHEET_TEST_EXEC.add(testCases);
		}
	}

	public void verifyPropertyValue(HashMap<String, String> config) {
		Set<String> keys = config.keySet();
		for (String key : keys) {
			Assert.assertTrue(!config.get(key).isEmpty(), "Value of " + key + " should not empty.");
		}

	}

	public static String updateBuildStatus(String url, int buildId, String buildStamp) {
		String result = "FAILED";
		try {

//		//	Client client = Client.create();
//
//		//	WebResource webResource = client
//					.resource(url + "/sendmailonexeccomplete?buildId=" + buildId + "&buildStamp=" + buildStamp);
//
//		////	ClientResponse response = webResource.accept("application/json").type("application/json")
//					.post(ClientResponse.class);
//
//			if (response.getStatus() != 200) {
//				result = "FAILED";
//
//			}
//			result = response.getEntity(String.class);
//			result = "OK";

		} catch (Exception e) {
			result = "FAILED";
			e.printStackTrace();
		}
		return result;
	}
	
	public static void expWait(WebElement ele)
	{
		WebDriverWait wait = new WebDriverWait(CoreConstants.driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	
		
	}
}
