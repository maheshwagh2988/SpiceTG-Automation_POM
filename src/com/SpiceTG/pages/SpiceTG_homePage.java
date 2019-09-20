package com.SpiceTG.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SpiceTG_homePage {
	@FindBy(xpath="//img[@src='/assets/img/cmy_logo.png']")
	public WebElement Verify_SpiceTG_Logo;
	
	@FindBy(id="admin-email")
	public WebElement Enter_Email_id;
	
	@FindBy(id="admin-password")
	public WebElement Enter_Password;
	
	@FindBy(xpath="//button[@ng-click='spicelogin(email_txt,password_txt)']")
	public WebElement ClickOn_Login;
	
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
	


}
