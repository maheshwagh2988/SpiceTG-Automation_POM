package com.SpiceTG.Utility;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
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
		
		BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		ImageIO.write(image, "png", new File("./ScreenshotsFullWebPage/"+screenshotName+ timestamp()+".png"));
		String Screenshottime =  new SimpleDateFormat("yyyyMMddhhmmss'.txt'").format(new Date());

		FileUtils.copyFile(source, new File("./Screenshots/"+screenshotName+ timestamp()+".png"));
							//or
		//FileUtils.copyFile(source, new File("./Screenshots/"+screenshotName+".png"+Screenshottime));

		System.out.println("Screenshot taken for Failed Test Case");
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
//This method to use to take 	FullScreenCaptureExample
	public static void FullScreenCapture (String FullSS)  {
		try {
			// create object to robt class
			Robot robot = new Robot();
			// create rectangle for full screenshot
			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			// capture screen of the desktop
			BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
			// save the screenshot to local system
			ImageIO.write(screenFullImage, "jpg", new File("./ScreenshotsFullWebPage/"+FullSS+ timestamp()+".jpg"));
			//System.out.println("Full Desktop screenshot saved!");
		} catch (Exception e) {
		
			System.out.println(e.getMessage());
		} 
	}

//Take Screenshot of WebElement
/*
	
	public static String WebElementLocationSS(String SSName){
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
	return SSName; 
}
*/	
	
	
	
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
