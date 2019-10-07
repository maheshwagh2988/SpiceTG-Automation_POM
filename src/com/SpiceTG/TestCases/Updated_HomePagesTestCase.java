package com.SpiceTG.TestCases;

import org.testng.annotations.Test;
import org.testng.Assert;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import com.SpiceTG.Utility.ExcelWriteUtility;
import com.SpiceTG.Utility.SpiceTG_GlobalVariables;
import com.SpiceTG.Utility.Updated_Excel_RW_Utility;
import com.SpiceTG.pages.SpiceTG_homePage;


public class Updated_HomePagesTestCase extends SpiceTG_GlobalVariables {
	
	@Test
	public void Test1() {

		System.out.println(dr.getTitle());
		String PageTitle;
		dr.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		Assert.assertTrue(dr.getTitle().contains("Spice: Admin"),"User not able to login with Valid Crendiential");
		PageTitle=dr.getTitle();
		System.out.println("SpiceTG Page Title has been Verified Sucessfully!"+PageTitle);
						
	}
	@Test
	public void Test2() throws InterruptedException {
		
		SpiceTG_homePage Shp=PageFactory.initElements(dr, SpiceTG_homePage.class);
		dr.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		Assert.assertEquals(Shp.Verify_SpiceTG_Logo.getAttribute("src"),"https://spicetg.azurewebsites.net/assets/img/cmy_logo.png","Actual img is not expected");
		String LogoVerification=Shp.Verify_SpiceTG_Logo.getText();
		System.out.println(LogoVerification);
		System.out.println("SpiceTG_Logo Verified Sucessfully!" + LogoVerification);
		
		
	}
	@Test
	public void Test3() {
		
		SpiceTG_homePage Shp=PageFactory.initElements(dr, SpiceTG_homePage.class);
		dr.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		
		try {
			timeout();
			Shp.Enter_User_Name_Email_id.sendKeys("prasadn@leotechnosoft.net");
			//dr.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			//Here we can call timeout method from SpiceTG_GlobalVariables class it has to wait 50 second 
			timeout();
			Shp.Enter_User_Password.sendKeys("leo_123");
			dr.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Shp.ClickOn_Login.click();
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		
						
	}
	/*
	@Test(dataProvider="SpiceTGuserpwd")
	public void Test4(String uname, String pwd) {
		
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
							
	} */
	

	@Test()
	public void Test5() throws Exception  {
		
		SpiceTG_homePage Shp=PageFactory.initElements(dr, SpiceTG_homePage.class);
		Shp.ForgotPassword.click();
		
		Updated_Excel_RW_Utility reader=new Updated_Excel_RW_Utility();
		
		String FistName=reader.getCellData("Sheet1", "Test_Case_Name", 1);
		System.out.println(FistName);
		
		//reader.getdata("Sheet1", 1, 1);
		reader.getRowCount("Sheet1");
				
			
		dr.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		try {
			Thread.sleep(2000);
			dr.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		//	Shp.ForgotEmailtextbox.sendKeys(uname);
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
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}
	@DataProvider(name="SpiceTGuserpwd")
	public Object[][]passdata(){
		
		ExcelWriteUtility exwrite= new ExcelWriteUtility();
		int rows=exwrite.getRowcount("LoginData");
		
		Object[][] DataProv=new Object[rows][2];
		for (int i=0;i<rows;i++){
			DataProv[i][0]=exwrite.getdata(0, i, 0);
			DataProv[i][1]=exwrite.getdata(0, i, 1);
			}
		return DataProv;
		
	}
	
	

}
