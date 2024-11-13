package com.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

//import com.operation.Zephyr_API_Operations;
import com.util.ExcelUtils;


public class TestListner implements ITestListener{
	ExcelUtils wrtexc=new ExcelUtils();
	//String TestDataExcel = "F:/UberMergeReport/MergeTestReport/JiraExcel/JiraTicket.xlsx";
	String ZephyrExcel = "src/main/resources/Excel/ZephyrID.xlsx";
	String ZephyrDataExcel = "src/main/resources/Excel/ZephyrData.xlsx";

	//Zephyr_API_Operations zep = new Zephyr_API_Operations();

	int rowcount=0;
	
    StopWatch stopWatch = new StopWatch();

    
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		  stopWatch.reset();
			try {
				//zep.TestCycleCheck();
				////System.out.println("Test Cycle Created");
			} catch (Exception e) {
				////System.out.println("Test Cycle is not created");
			}
			stopWatch.start();
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		stopWatch.stop();
	      ////System.out.println("Elapsed Time in minutes: "+ stopWatch.getTime(TimeUnit.MILLISECONDS));
	      Long time = stopWatch.getTime(TimeUnit.MILLISECONDS);
	      String executiontime=String.valueOf(time);
	    		  
	      ExcelUtils ZephyrIDData = null;
		try {
			ZephyrIDData = new ExcelUtils(ZephyrExcel, "Zephyr");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String ID = null;
		String TCName = null;
		String CurrentTC = result.getName();
		int size=0;
		try {
			size = rowSize(ZephyrExcel, "Zephyr");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(int i=0; i<=size;i++)
		{
//			TCName = ZephyrIDData.getCellValue("TestCase", i);
			if(ZephyrIDData.getCellValue("TestCase", i).equalsIgnoreCase(CurrentTC))
			{
				ID = ZephyrIDData.getCellValue("ID", i);
				break;
			}
			

		}
		////System.out.println("Current Test Case "+CurrentTC);
		////System.out.println(ID);
		try {
		//	zep.TestCase_Status_Update("Pass",ID, "Passed", executiontime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		////System.out.println("================The name of the testcase passed is :"+result.getName());
		
	}
	@Override
	public void onTestFailure(ITestResult result) {
	      ////System.out.println("Elapsed Time in minutes: "+ stopWatch.getTime(TimeUnit.MILLISECONDS));
	      Long time = stopWatch.getTime(TimeUnit.MILLISECONDS);
	      String executiontime=String.valueOf(time);
	      stopWatch.reset();
	      
	      
		String data=result.getTestClass().toString();
		String data1=data.substring(22,data.length()-1);
		
//		DataSheet AccountData = null;
//		try {
//			AccountData = new DataSheet(TestDataExcel, "Summary");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		try {
//			rowcount=wrtexc.checkAvailableRow(TestDataExcel, "Summary");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	
//				
//					wrtexc.writeDataToExcel(rowcount, 0, TestDataExcel,"Summary",data1);
//					wrtexc.writeDataToExcel(rowcount, 1, TestDataExcel,"Summary",result.getName());
//				wrtexc.writeDataToExcel(rowcount, 2, TestDataExcel,"Summary",result.getThrowable().toString());
//		
		///////////  zephyr code axy  -----------------------------------
				
		ExcelUtils ZephyrIDData = null;
				try {
					ZephyrIDData = new ExcelUtils(ZephyrExcel, "Zephyr");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String ID = null;
				String CurrentTC = result.getName();

				int size=0;
				try {
					size = rowSize(ZephyrExcel, "Zephyr");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for(int i=0; i<=size;i++)
				{
//					TCName = ZephyrIDData.getCellValue("TestCase", i);
					if(ZephyrIDData.getCellValue("TestCase", i).equalsIgnoreCase(CurrentTC))
					{
						ID = ZephyrIDData.getCellValue("ID", i);
						break;
					}
					

				}
				////System.out.println("Current Test Case "+CurrentTC);
				////System.out.println(ID);
				
				try {
					String error = result.getThrowable().toString();
					String execp="";

					if(error.length()>1000)
					{
						execp =  result.getThrowable().toString().substring(0,1000);	
					}
					else
					{
						execp =  result.getThrowable().toString();
					}
//					System.out.println("original "+execp+"-----------end");
					
//					execp = execp.replaceAll("\n", "").replace("\r", "");

//					execp = execp.replaceAll("[^a-z:.()/A-Z0-9\\s\\[]]", "").replaceAll("\n", "").replace("\r", "");

					execp = execp.replaceAll("[^a-z:.()/A-Z0-9\\s\\[]]", "").replace('\\', '/').replace('"', '\'').replaceAll("\n", "").replace("\r", "");

//					System.out.println("trim "+execp+"-----------end");

//					zep.TestCase_Status_Update("Fail", ID, "Test Case Failed : ", executiontime); //back up update
					//zep.TestCase_Status_Update("Fail", ID, "Test Case Failed : "+execp, executiontime);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	public int rowSize(String filePath, String sheetName) throws IOException
	{
		Workbook workbook = null;
		FileInputStream fis = null;
		fis = new FileInputStream(filePath);
		workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheet(sheetName);

		int size=0;
		size = sheet.getLastRowNum();
		
		return size;
	}
	
		

	@Override
	public void onTestSkipped(ITestResult result) {
		///////////  zephyr code axy  -----------------------------------
		
	      ////System.out.println("Elapsed Time in minutes: "+ stopWatch.getTime(TimeUnit.MILLISECONDS));
	      Long time = stopWatch.getTime(TimeUnit.MILLISECONDS);
	      String executiontime=String.valueOf(time);
	      stopWatch.reset();
	      
	      ExcelUtils ZephyrIDData = null;
	      ExcelUtils ZepData = null;

		try {
			ZephyrIDData = new ExcelUtils(ZephyrExcel, "Zephyr");
			ZepData = new ExcelUtils(ZephyrDataExcel, "Zephyr");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String ID = null;
		String CurrentTC = result.getName();
		String oldTC = ZepData.getCellValue("PrevTC", 0);


		int size=0;
		try {
			size = rowSize(ZephyrExcel, "Zephyr");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(int i=0; i<=size;i++)
		{
//			TCName = ZephyrIDData.getCellValue("TestCase", i);
			if(ZephyrIDData.getCellValue("TestCase", i).equalsIgnoreCase(CurrentTC))
			{
				ID = ZephyrIDData.getCellValue("ID", i);
				break;
			}
			

		}
		//System.out.println("Current Test Case "+CurrentTC);
//		System.out.println("Axy----------------------------------------------------current "+CurrentTC);
//		System.out.println("Axy----------------------------------------------------old "+oldTC);
		String execp="";

		if(CurrentTC.equalsIgnoreCase(oldTC))
		{
			//System.out.println("Axy----------------------------------------------------failed");
			try {
				//System.out.println("axy-------"+result.getThrowable().toString());
				String error = result.getThrowable().toString();
//				System.out.println(error.length());
				if(error.length()>1000)
				{
					execp =  result.getThrowable().toString().substring(0,1000);	
				}
				else
				{
					execp =  result.getThrowable().toString();
				}
//				System.out.println("original "+execp+"-----------end");
				execp = execp.replaceAll("[^a-z:.()/A-Z0-9\\s\\[]]", "").replace('\\', '/').replace('"', '\'').replaceAll("\n", "").replace("\r", "");

//				System.out.println("trim "+execp+"-----------end");

				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
			//	zep.TestCase_Status_Update("Fail", ID, "Test case Skipped (Not executed)", executiontime);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		/*else
		{
			//System.out.println("Axy----------------------------------------------------Skipped");
			try {
				String error = result.getThrowable().toString();
//				System.out.println(error.length());
				if(error.length()>1000)
				{
					execp =  result.getThrowable().toString().substring(0,1000);	
				}
				else
				{
					execp =  result.getThrowable().toString();
				}
//				System.out.println("original "+execp+"-----------end");
				execp = execp.replaceAll("[^a-z:.()/A-Z0-9\\s\\[]]", "").replace('\\', '/').replace('"', '\'').replaceAll("\n", "").replace("\r", "");

//				System.out.println("trim "+execp+"-----------end");
				
//				zep.TestCase_Status_Update("Not Executed", ID, "Test Case Skipped: ", executiontime); // temp commented this code
//				zep.TestCase_Status_Update("Fail", ID, "Test Case Skipped: "+execp, executiontime);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		wrtexc.writeDataToExcel(1, 5, ZephyrDataExcel, "Zephyr", CurrentTC); // execution name
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	//@Override
	/*
	 * public void onStart(ITestContext context) { // TODO Auto-generated method
	 * stub try { // System.out.println("Token::"+zep.getAccessToken()+"::"); //
	 * wrtexc.writeDataToExcel(1, 4, ZephyrDataExcel, "Zephyr",
	 * zep.getAccessToken()); // execution name } catch (IOException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 */
	  
	//}

/*	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
*/
}
