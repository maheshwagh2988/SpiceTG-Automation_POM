package com.SpiceTG.Utility;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;

import com.SpiceTG.pages.SpiceTG_homePage;

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
//This method to take date and time to take screen shot	
	public static String timestamp() {
	    return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
	}

	//Take Screenshot of WebElement
	public void WebElementLocationSS(String SSName){
	try {
		SpiceTG_homePage Shp=PageFactory.initElements(dr, SpiceTG_homePage.class);
		Point point = Shp.Enter_User_Password.getLocation();
		File screen = ((TakesScreenshot) dr).getScreenshotAs(OutputType.FILE);
		int xcordinate = point.getX();
		int ycordinate = point.getY();
		int imageWidth = Shp.Enter_User_Password.getSize().getWidth();
		int imageHeight = Shp.Enter_User_Password.getSize().getHeight();
		BufferedImage img = ImageIO.read(screen);
		BufferedImage destination = img.getSubimage(xcordinate, ycordinate, imageWidth, imageHeight);
		ImageIO.write(destination, "png", screen);
		FileUtils.copyFile(screen, new File("./Screenshots/"+SSName+ timestamp()+".png"));
		
	} catch (Exception e) {
		System.out.println(e.getMessage());
		
	} 
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
