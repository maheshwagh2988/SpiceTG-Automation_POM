package com.SpiceTG.TestCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.SpiceTG.Utility.SpiceTG_GlobalVariables;
import com.SpiceTG.pages.SpiceTG_homePage;


public class HomePagesTestCase extends SpiceTG_GlobalVariables {
	
	@Test
	public void Test1() {

		System.out.println(dr.getTitle());
		String PageTitle;
		Assert.assertTrue(dr.getTitle().contains("Spice: Admin"));
		PageTitle=dr.getTitle();
		System.out.println("SpiceTG Page Title has been Verified Sucessfully!"+PageTitle);
						
	}
	@Test
	public void Test2() throws InterruptedException {
		
		SpiceTG_homePage Shp=PageFactory.initElements(dr, SpiceTG_homePage.class);
		dr.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		Assert.assertEquals(Shp.Verify_SpiceTG_Logo.getAttribute("src"),"https://spicetg.azurewebsites.net/assets/img/cmy_logo.png","Actual img is not expected");
		String LogoVerification=Shp.Verify_SpiceTG_Logo.getText();
		System.out.println(LogoVerification);
		System.out.println("SpiceTG_Logo Verified Sucessfully!" + LogoVerification);
		
		
	}
	@Test
	public void Test3() {
		
		SpiceTG_homePage Shp=PageFactory.initElements(dr, SpiceTG_homePage.class);
		Shp.Enter_Email_id.sendKeys("neel.sharma@spicetg.com");
		Shp.Enter_Password.sendKeys("leo_12345");
		Shp.ClickOn_Login.click();
						
	}
	

}
