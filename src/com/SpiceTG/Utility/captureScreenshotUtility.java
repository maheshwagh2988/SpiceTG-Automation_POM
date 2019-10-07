package com.SpiceTG.Utility;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;

public class captureScreenshotUtility extends SpiceTG_GlobalVariables {
	
	public static String captureScreenshot(String screenshotName){
		try 
		{
		TakesScreenshot ts=(TakesScreenshot)dr;

		File source=ts.getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(source, new File("./Screenshots/"+screenshotName+".png"));

		System.out.println("Screenshot taken");
		} 
		catch (Exception e)
		{

		System.out.println("Exception while taking screenshot "+e.getMessage());
		}
		return screenshotName; 
		
	}
	public void getResult(ITestResult result) throws Exception
    {
        if (result.getStatus() == ITestResult.FAILURE)
        {

           // captureScreenshot(result.getMethod().getMethodName());
        	captureScreenshotUtility.captureScreenshot(result.getName());
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
