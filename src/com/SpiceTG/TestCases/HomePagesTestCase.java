package com.SpiceTG.TestCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.seleniumhq.jetty9.util.thread.ShutdownThread;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.SpiceTG.Utility.SpiceTG_GlobalVariables;
import com.SpiceTG.pages.SpiceTG_homePage;


public class HomePagesTestCase extends SpiceTG_GlobalVariables {
	
	@Test
	public void Test1() {

		System.out.println(dr.getTitle());
		String PageTitle;
		dr.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		Assert.assertTrue(dr.getTitle().contains("Spice: Admin"));
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
		Shp.Enter_Email_id.sendKeys("prasadn@leotechnosoft.net");
		dr.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		Shp.Enter_Password.sendKeys("leo_12345");
		dr.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		Shp.ClickOn_Login.click();
						
	}
	@Test
	public void Test4() {
		
		SpiceTG_homePage Shp=PageFactory.initElements(dr, SpiceTG_homePage.class);
		Shp.ForgotPassword.click();
		dr.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		try {
			Thread.sleep(2000);
			dr.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			Shp.ForgotEmailtextbox.sendKeys("prasadn@leotechnosoft.net");
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
	@Test
	public void Test5() {
		
		
						
	}
	
	

}
