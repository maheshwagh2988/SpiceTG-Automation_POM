package com.SpiceTG.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.SpiceTG.Utility.SpiceTG_GlobalVariables;

public class SpiceTG_LoginPage extends SpiceTG_GlobalVariables{
	@FindBy(xpath="//img[@src='/assets/img/cmy_logo.png']")////div[@class='logoCenter']
	public WebElement Verify_SpiceTG_Logo;
	
	@FindBy(id="admin-email")
	public WebElement Enter_User_Name_Email_id;
	
	@FindBy(id="admin-password")
	public WebElement Enter_User_Password;
		
	@FindBy(xpath="//button[@ng-click='spicelogin(email_txt,password_txt)']")
	public WebElement ClickOn_Login;
	
	//ForgotPassword
	
	@FindBy(xpath="//a[@class='form-link ']")
	public WebElement ForgotPassword;
	
	@FindBy(id="forgot-email")
	public WebElement ForgotEmailtextbox;
	
	@FindBy(xpath="//div[@class='modal-header']//h5[@class='modal-title']")
	public WebElement ForgotPwdText;
		
	@FindBy(xpath="//div[@class='modal-header']//h5[@class='modal-title text-primary'and text()='Success!']")
	public WebElement SucessMessage;
	
	@FindBy(xpath="//div[@class='pt-2 pb-2']")
	public WebElement SucessMessageDetailsMessage;
	
	@FindBy(xpath="//button[@class='btn btn-primary'and text()='Submit']")
	public WebElement ForgotPasswordSubmiteButton;
	
	
	@FindBy(xpath="//button[@class='btn btn-primary'and text()='Done']")
	public WebElement ForgotPasswordDoneButton;
	
	@FindBy(xpath="//button[@class='btn btn-link' and text()='Cancel']")
	public WebElement CancelButton;
	
	//Page Object Of SpiceTGHomePage 
	
	@FindBy(xpath="//div[@class='pageHead clearfix ng-scope']") //div[@class='pageHead clearfix ng-scope']//h2[text()='Tenant Management ']
	public WebElement LandingDashboard;
	
	@FindBy(xpath="//a[@class='dropdown-toggle profileToggle ng-scope']")////a[@class='dropdown-toggle profileToggle ng-scope' and @aria-expanded='true'] OR ////div[@class='btn-group']
	public WebElement LogoutDrpoDownArrow;////a[@class='dropdown-toggle profileToggle ng-scope']
	
	@FindBy(xpath="//a[@class='dropdown-toggle profileToggle ng-scope']")
	public WebElement CheckDropDownEnable;
			
	@FindBy(xpath="//div[@class='dropdown-menu dropdown-menu-right show']//a[text()='Logout']")
	public WebElement LogOutApplicaion;
		
	public SpiceTG_LoginPage(){
		PageFactory.initElements(dr, this); //OR this
	}
	
	//Actions:
	public String validateSpiceTGHomePageTitle(){
		return dr.getTitle();
	}
	public boolean validateSpiceTGLogo(){
	
		return Verify_SpiceTG_Logo.isDisplayed();
	}
	//Will redirect to HomePage of Spice TG Application 
	public SpiceTGHomePage validateUserNamePWD(String Uname,String PWD){
		
		Enter_User_Name_Email_id.sendKeys(Uname);
		dr.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		Enter_User_Password.sendKeys(PWD);
		dr.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		ClickOn_Login.click();
		return new SpiceTGHomePage();
	}
	



}
