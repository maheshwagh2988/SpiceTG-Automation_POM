package com.SpiceTG.Utility;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;

public class AlertHandel extends SpiceTG_GlobalVariables{
	
	public boolean isAlertPresent(){
		
		 boolean presentFlag = false;

		    try {

		        // Check the presence of alert
		        Alert alert = dr.switchTo().alert();
		        // Alert present; set the flag
		        presentFlag = true;
		        // if present consume the alert
		        alert.accept();
		        //( Now, click on ok or cancel button )

		    } catch (NoAlertPresentException ex) {
		        // Alert not present
		        ex.printStackTrace();
		    }

		    return presentFlag;
		

}
	
}
