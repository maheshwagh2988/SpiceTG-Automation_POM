package com.SpiceTG.Utility;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;



public class SpiceTG_GlobalVariables {
	// Initilaze the Static Variables 
	
	public static WebDriver dr = null;
	public static WebElement oElement = null; 
	public static Select sel;
	public static String LoginURL = "https://spicetg.azurewebsites.net/";
	public static EventFiringWebDriver oDriver = null;
	//public static String testDataExcelFileName  = "testdata.xlsx";
	//public static String wait = null;
	public static String Filepahts  = "D:\\Automation\\Automation_Project\\SpiceTG-Automation\\src\\com\\SpiceTG\\TestDataFiles\\Testdata_1.xlsx";
	public static long PAGE_LOAD_TIMEOUT=30; //Time out function return long so take long variable
	public static long IMPLICIT_WAIT=40;
		
	@BeforeMethod
	public static void beforeClass() throws Exception {
		initialize();
		timeout();
		
		//Set Test Data Excel and Sheet
		//System.out.println("******Setup Test Level Data***********");
		//ExcelUtil.setExcelFileSheet("LoginData");
	}
	@AfterMethod
	public static void afterClass() throws Exception {
		quit();
		//captureScreenshotUtility.captureScreenshot("captureScreenshot");
		
	}
	
	
	/* Here we can set Different Browser like IE,chromeBrower,FF etc.
	 * Window will Maximize
	 * Default Browser waiting time
	 *  Browser will quilt after every test cases
	 * */
	
	public static void initialize() throws Exception{
		if(oDriver==null){
			
		//System.setProperty("webdriver.ie.driver", "D:/Automation/Automation_Project/SpiceTG-Automation/Jar Files/IEDriverServer.exe");
		//dr = new InternetExplorerDriver();
		
		System.setProperty("webdriver.chrome.driver", "D:/Automation/Automation_Project/SpiceTG-Automation/Jar Files/chromedriver_win32_76/chromedriver.exe");
		dr = new ChromeDriver();
		dr.manage().window().maximize();
		dr.get(LoginURL);
		dr.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		dr.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
 		

		if (dr != null) {
			oDriver = new EventFiringWebDriver(dr);
			oDriver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
					
		  }
		}
		
		
	}
	
	protected static void quit() throws Exception {
		if (oDriver != null) {
			oDriver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
		//	oDriver.quit();
			oDriver.close();
			

			oDriver = null;
		}
		if (dr != null) {
			
			dr.close();
			//dr.quit();
			dr = null;
		}
		
		//LoginURL = null;
	}
	
	public static void timeout() throws Exception {
		WebDriverWait wait = new WebDriverWait(dr,50);
		
	}

}
