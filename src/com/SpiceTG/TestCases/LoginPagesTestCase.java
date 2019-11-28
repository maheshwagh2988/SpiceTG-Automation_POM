package com.SpiceTG.TestCases;

import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import com.SpiceTG.Utility.SpiceTG_GlobalVariables;
import com.SpiceTG.pages.SpiceTGHomePage;
import com.SpiceTG.pages.SpiceTG_LoginPage;

public class LoginPagesTestCase extends SpiceTG_GlobalVariables {
	SpiceTG_LoginPage LoginPage = new SpiceTG_LoginPage();
	SpiceTGHomePage HomePage=new SpiceTGHomePage();
	
	@Test(priority=1)
	public void SpicePageTitleTest() {
		
		String title=LoginPage.validateSpiceTGHomePageTitle();
		Assert.assertTrue(dr.getTitle().contains("Spice: Admin"),"User not able to login with Valid Crendiential");
		System.out.println("SpiceTG Page Title is: " + title);
		System.out.println("Test1");
			
	}
	@Test(priority=2)
	public void SpiceLogoTest() throws InterruptedException {
			
			dr.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			boolean Spicelogo=LoginPage.validateSpiceTGLogo();
			Assert.assertTrue(Spicelogo);
			System.out.println("SpiceTG_Logo Verified Sucessfully!"+Spicelogo);
			System.out.println("Test2");
			
	}
	@Test(priority=3)
	public void SpiceValidLoginTest() {
		
		dr.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		HomePage=LoginPage.validateUserNamePWD("prasadn@leotechnosoft.net", "leo_12345");
								
	}
	@Test(priority=4, dataProvider="SpiceTGuserpwd")
	public void Test4(String uname, String pwd) {
		
		SpiceTG_LoginPage Shp=PageFactory.initElements(dr, SpiceTG_LoginPage.class);
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
	public Object[][]passdata(){
		Object[][] DataProv=new Object[1][2];
		DataProv[0][0]="prasadn@leotechnosoft.net";
		DataProv[0][1]="leo_123";
		return DataProv;
		
	}
	
	

}
