package com.testBase;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.TestNG;

import com.util.Timer;



public class MainExecutionClass {

	public Timer timer=Timer.getInstance();
	public Elements element=Elements.getInstance();
	public final static int DefaultTimeOut = 60;

    public static void main(String[] args) throws Exception {
       
    	
    	
        
        //xenon.excute("xml/testng.xml");    
        /*
         * ----------------------------------------
         */
	
        if(args.length == 0) {
            System.out.println("No arguments found");
            //System.exit(0);
			initiateData("xml/testng.xml");
            
          
        }
        else
        {
            System.out.println("Arguments :- " + Arrays.toString(args));
            // Xenon xenon = Xenon.getXenonInstance();
            //initiateData();
            //xenon.excute("xml/testng.xml");

 

            String suiteName = args[0];
            //xenon.excute("xml/" + suiteName + "-testng.xml");
			initiateData("xml/" + suiteName + "-testng.xml");



            File srcFile=new File("./test-output/emailable-report.html");
            String destFolderName="UberReports";
            
          /*  File destFile=new File("C:/Program Files (x86)/Jenkins/workspace/"+destFolderName+"/emailable-report.html");
            copyFile(srcFile,destFile);
           
            File newName = new File("C:/Program Files (x86)/Jenkins/workspace/"+destFolderName+"/"+suiteName+".html");
            */
//        if(!suiteName.equalsIgnoreCase("New")) {
        	
        
            File destFile=new File("C:/Windows/System32/config/systemprofile/AppData/Local/Jenkins/.jenkins/workspace/"+destFolderName+"/emailable-report.html");
           copyFile(srcFile,destFile);
           
            File newName = new File("C:/Windows/System32/config/systemprofile/AppData/Local/Jenkins/.jenkins/workspace/"+destFolderName+"/"+suiteName+".html");
            

			/*
			 * File destFile=new
			 * File("./test-output/"+destFolderName+"/emailable-report.html");
			 * copyFile(srcFile,destFile);
			 * 
			 * File newName = new
			 * File("./test-output/"+destFolderName+"/"+suiteName+".html");
			 */   
            Thread.sleep(2000);
            
            if (destFile.renameTo(newName)) 
            {
                System.out.println("Renamed successfully");       
            }
            else {
                System.out.println("Error");   
            }

        }

         //        driver.quit();
    }

 

    private static void copyFile(File source, File dest){
        try{
            FileUtils.copyFile(source, dest);
        }catch (Exception e){

 

        }
    }
    private static void initiateData(String testNgXML) {
    	

		File file = new File("src/main/resources/" + testNgXML);
		String absolutePath = file.getAbsolutePath();
		final TestNG testng = new TestNG();
		DotTestListener dotoTest = new DotTestListener();
		List<String> suitesList = new ArrayList<String>();
		//AnnotationTransformer annotation = new AnnotationTransformer();
		suitesList.add(absolutePath);
		testng.setTestSuites(suitesList);
		testng.addListener(dotoTest);
		
		
		System.out.println("B======================================");
	
			testng.run();
    	
       // Constants.ApplicationURL = configProperties.get("ApplicationUrl");
        //    Constants.InfobloxURL = configProperties.get("InfobloxURL");
        //Constants.SFDCUrl=configProperties.get("ApplicationUrl");
    }

 

 

}