package com.SpiceTG.Utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.support.PageFactory;

import com.SpiceTG.pages.SpiceTG_homePage;

public class AlertHandel extends SpiceTG_GlobalVariables{
	
	public boolean isAlertPresent(){
		
		 boolean presentFlag = false;

		    try {

		        // Check the presence of alert
		       Alert confirmationAlert = dr.switchTo().alert();
		       String alertText = confirmationAlert.getText();
		       System.out.println("Alert text is " + alertText);
		        // Alert present; set the flag
		       presentFlag = true;
		        // if present consume the alert
		       ScreenshotUtility.FullScreenCapture("Alert! InValid UserName");
		       confirmationAlert.accept();
		       System.out.println("Invalid UserName or Password Enter by User");
		       ScreenshotUtility.captureScreenshot("Alert !UserName or Pwd is Invalid on Date time");
		       System.out.println("Test Case is Failed");
		       oDriver.close();

		    } catch (NoAlertPresentException e) {
		        // Alert not present
		    	presentFlag = false;
		        System.out.println(e.getMessage());
		    }
		    return presentFlag;
	
}
	
}
