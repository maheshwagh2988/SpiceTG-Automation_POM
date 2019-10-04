package com.SpiceTG.TestCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.seleniumhq.jetty9.util.thread.ShutdownThread;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import com.SpiceTG.Utility.AlertHandel;
import com.SpiceTG.Utility.ExcelRWUtility;
import com.SpiceTG.Utility.ExcelWriteUtility;
import com.SpiceTG.Utility.Excel_ReadWrite_Utility;
import com.SpiceTG.Utility.SpiceTG_GlobalVariables;
import com.SpiceTG.pages.SpiceTG_homePage;


public class CopyOfHomePagesTestCase extends SpiceTG_GlobalVariables {
	
	@Test //To Verify Page Title
	public void Test1() {

		System.out.println(dr.getTitle());
		String PageTitle;
		dr.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		Assert.assertTrue(dr.getTitle().contains("Spice: Admin"),"User not able to login with Valid Crendiential");
		PageTitle=dr.getTitle();
		System.out.println("SpiceTG Page Title has been Verified Sucessfully!"+PageTitle);
						
	}
	@Test  //To Verify Application Logo
	public void Test2() throws InterruptedException {
		
		SpiceTG_homePage Shp=PageFactory.initElements(dr, SpiceTG_homePage.class);
		dr.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		Assert.assertEquals(Shp.Verify_SpiceTG_Logo.getAttribute("src"),"https://spicetg.azurewebsites.net/assets/img/cmy_logo.png","Actual img is not expected");
		String LogoVerification=Shp.Verify_SpiceTG_Logo.getText();
		System.out.println(LogoVerification);
		System.out.println("SpiceTG_Logo Verified Sucessfully!" + LogoVerification);
		
		
	}
	@Test //To Login with Valid user and after Login Print Default dashBoard Title
	public void Test3() {
		
		SpiceTG_homePage Shp=PageFactory.initElements(dr, SpiceTG_homePage.class);
		dr.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		
		try {
			timeout();  //neel.sharma@spicetg.com
			Shp.Enter_User_Name_Email_id.sendKeys("prasadn@leotechnosoft.net");//prasadn@leotechnosoft.net
			//Here we can call timeout method from SpiceTG_GlobalVariables class it has to wait 50 second 
			timeout();
			Shp.Enter_User_Password.sendKeys("leo_123");
			dr.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Shp.ClickOn_Login.click();
			timeout();
			String LandingPageDashboard=Shp.LandingDashboard.getText();
			System.out.println("Landing Page Dashboard Name is :"+LandingPageDashboard);
							//OR
			//Assert.assertEquals(Shp.LandingDashboard.getText(),"Tenant Management");
			timeout();
			Assert.assertTrue(Shp.LandingDashboard.getText().contains(LandingPageDashboard), "Landing Page Dashboard Title does not match");
			dr.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Shp.LogoutDrpoDownArrow.click();
			dr.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Shp.LogOutApplicaion.click();
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		} 
	}
	
	@Test(dataProvider="SpiceTG_UserName_and_Password")
	public void Test4(String UserName, String Password) {
		
		System.out.println(UserName+" | "+Password);
		SpiceTG_homePage Shp=PageFactory.initElements(dr, SpiceTG_homePage.class);
		dr.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		
		try {
			Thread.sleep(2000);
			Shp.Enter_User_Name_Email_id.sendKeys(UserName);
			
			timeout();
			Shp.Enter_User_Password.sendKeys(Password);
			dr.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Shp.ClickOn_Login.click();
			Thread.sleep(5000);
			AlertHandel ak=new AlertHandel();
			boolean flag=ak.isAlertPresent();
		   	if(!flag)
	        	{
					String LandingPageDashboard=Shp.LandingDashboard.getText();
					System.out.println("Landing Page Dashboard Name is :"+LandingPageDashboard);
					timeout();
					Assert.assertTrue(Shp.LandingDashboard.getText().contains(LandingPageDashboard), "Landing Page Dashboard Title does not match");
					System.out.println("Home page title after login is" + LandingPageDashboard );
					timeout();
					System.out.println("User has been Login with valid Details and the userName is: "+UserName);
		        	System.out.println("Test Case is PASSED");
					dr.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
					Shp.LogoutDrpoDownArrow.click();
					dr.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
					Shp.LogOutApplicaion.click();
	        	}	
	    		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
				
	}
	@DataProvider(name="SpiceTG_UserName_and_Password")
	public Object[][]Userpassdata() throws Exception{
		
		ExcelWriteUtility Readwrite=new ExcelWriteUtility();
		Readwrite.Openfile("D:\\Automation\\Automation_Project\\SpiceTG-Automation\\src\\com\\SpiceTG\\TestDataFiles\\UserNameAndPassword.xlsx");
		int rows=Readwrite.getRowcount("Sheet1");
		System.out.println("Total no of rows in the given Excel file is: "+rows);
		
		int column=Readwrite.getColumnCount("Sheet1");
		System.out.println("Total no of Column in the given Excel file is:"+column);
		Object[][] UserPass=new Object[rows][2];
		//i=1 as our 1 row use for header so my loop start with row 1 
		for (int i=1;i<rows;i++)
		{	
			
			//Index Start with 0 so i-1 to get 1st row
			UserPass[i-1][0]=Readwrite.getdata(0, i, 0);
			UserPass[i-1][1]=Readwrite.getdata(0, i, 1);
			
		}
		return UserPass;
	}
	
	@Test(dataProvider="SpiceTGuserpwd")
	public void Test5(String uname, String pwd) throws Exception  {
		SpiceTG_homePage Shp=PageFactory.initElements(dr, SpiceTG_homePage.class);
		Shp.ForgotPassword.click();
					
		dr.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		try {
			Thread.sleep(2000);
			dr.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
						
			Shp.ForgotEmailtextbox.sendKeys(uname);
			dr.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
						
			String Forgottxt=Shp.ForgotPwdText.getText();
			System.out.println("Forgot txt is  :"+Forgottxt);
			
			dr.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Shp.ForgotPasswordSubmiteButton.click();
			
			String Sucesstxt=Shp.SucessMessage.getText();
			System.out.println("On Sucess page text is:"+Sucesstxt);
										
			String SucessDetailstxt=Shp.SucessMessageDetailsMessage.getText();
			System.out.println("On Sucess page Details text is:"+SucessDetailstxt);
			
			Shp.ForgotPasswordDoneButton.click();	
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@DataProvider(name="SpiceTGuserpwd")
	public Object[][]passdata() throws Exception{
		
		ExcelWriteUtility Readwrite=new ExcelWriteUtility();
		Readwrite.Openfile("D:\\Automation\\Automation_Project\\SpiceTG-Automation\\src\\com\\SpiceTG\\TestDataFiles\\testNData.xlsx");
		//Readwrite.readData();
		int rows=Readwrite.getRowcount("Sheet1");
		System.out.println("Total rows"+rows);
//		ExcelWriteUtility exwrite= new ExcelWriteUtility();
//		int rows=exwrite.getRowcount("Sheet1");
//		
		Object[][] DataProv=new Object[rows][2];
		for (int i=0;i<rows;i++){
			DataProv[i][0]=Readwrite.getdata(0, i, 0);
			//DataProv[i][0]=Readwrite.getdata(0, i, 0);
			//DataProv[i][1]=Readwrite.getdata(0, i, 2);
			//DataProv[i][0]=Readwrite.getNewdata("Sheet1", "Username", 0);
		}
		return DataProv;
		
	}
	//For ForgotPwd pass from excel file
	@Test(dataProvider="SpiceTGForgotPwd")
	public void Test6(String username) throws Exception  {
		SpiceTG_homePage Shp=PageFactory.initElements(dr, SpiceTG_homePage.class);
		Shp.ForgotPassword.click();
					
		dr.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		try {
			Thread.sleep(2000);
			dr.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
						
			Shp.ForgotEmailtextbox.sendKeys(username);
			String Forgottxt=Shp.ForgotPwdText.getText();
			System.out.println("Forgot txt is  :"+Forgottxt);
			dr.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Shp.ForgotPasswordSubmiteButton.click();
			String Sucesstxt=Shp.SucessMessage.getText();
			System.out.println("On Sucess page text is:"+Sucesstxt);
			String SucessDetailstxt=Shp.SucessMessageDetailsMessage.getText();
			System.out.println("On Sucess page Details text is:"+SucessDetailstxt);
			Shp.ForgotPasswordDoneButton.click();	
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
	//DataProvdier with Single Dimesntion Array
	@DataProvider(name="SpiceTGForgotPwd")
	public Object[][]ForgotPwddata() throws Exception{
		ExcelWriteUtility Readwrite=new ExcelWriteUtility();
		Readwrite.Openfile("D:\\Automation\\Automation_Project\\SpiceTG-Automation\\src\\com\\SpiceTG\\TestDataFiles\\ForgotPwdUserName.xlsx");
		int rows=Readwrite.getRowcount("Sheet1");
		System.out.println("Total No of Row is : "+rows);
		Object[][] DataProv=new Object[rows][1];
		for (int i=1;i<rows;i++){
			DataProv[i][0]=Readwrite.getdata(0, i, 0);
		}
		return DataProv;
	}
	// Write Data using DataProvdier with Single Dimesntion Arrary
	@Test
	public void Test7() throws Exception{
		ExcelWriteUtility Readwrite=new ExcelWriteUtility();
		Readwrite.Openfile("D:\\Automation\\Automation_Project\\SpiceTG-Automation\\src\\com\\SpiceTG\\TestDataFiles\\ForgotPwdUserName.xlsx");
		Readwrite.WriteNewData("Sheet1", 3, 2, "Pass");
		Readwrite.WriteNewData("Sheet1", 3, 3, "Fail");
		Readwrite.WriteNewData("Sheet1", 3, 4, "fail");
	}
	
	
	
	

}
