package com.SpiceTG.Utility;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;

public class ScreenshotUtility extends SpiceTG_GlobalVariables {
	
	public static String captureScreenshot(String screenshotName){
		try 
		{
		TakesScreenshot ts=(TakesScreenshot)dr;
				
		File source=ts.getScreenshotAs(OutputType.FILE);
		
		String Screenshottime =  new SimpleDateFormat("yyyyMMddhhmmss'.txt'").format(new Date());

		FileUtils.copyFile(source, new File("./Screenshots/"+screenshotName+ timestamp()+".png"));
							//or
		//FileUtils.copyFile(source, new File("./Screenshots/"+screenshotName+".png"+Screenshottime));

		System.out.println("Screenshot taken");
		} 
		catch (Exception e)
		{

		System.out.println("Exception while taking screenshot "+e.getMessage());
		}
		return screenshotName; 
		
	}
	
	public static String timestamp() {
	    return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
	}
	
	
	public void getResult(ITestResult result) throws Exception
    {
        if (result.getStatus() == ITestResult.FAILURE)
        {

           // captureScreenshot(result.getMethod().getMethodName());
        	ScreenshotUtility.captureScreenshot(result.getName());
        	//String screenShotPath =	  
            captureScreenshot(result.getName()+" Test case FAILED due to below issues:" );
             
        	//captureScreenshot(result.getTestName());

        }
        else if(result.getStatus() == ITestResult.SUCCESS)
        {
            captureScreenshot(result.getName()+" Test Case PASSED");
        }
        else
        {
        	captureScreenshot(result.getName()+" Test Case SKIPPED");
            
        }

    }
	


}
