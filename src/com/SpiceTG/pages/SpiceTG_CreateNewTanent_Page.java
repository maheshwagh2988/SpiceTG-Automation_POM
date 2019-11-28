package com.SpiceTG.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SpiceTG_CreateNewTanent_Page {
	@FindBy(xpath="//a[@ng-click='CreateTenant(createnew)']")
	WebElement CreatNewTanent;
	
	@FindBy(id="subTenant")
	WebElement EnterTanentName;
	
	@FindBy(id="tenant-member-id")
	WebElement EnterGS1_MemberID;
	
	@FindBy(id="tenant-web")
	WebElement EnterTenant_Website_Url;
	
	@FindBy(id="tenant-account-id")
	WebElement EnterAccount_ID;
	
	@FindBy(id="owner-yes")
	WebElement Document_OwnerYES;
	
	@FindBy(id="owner-no")
	WebElement Document_OwnerNO;
	//---------------------------------------------
	
	@FindBy(xpath="//div[@class='collapseBox-head']//h3[text()='Business Details']")
	WebElement ClickonBusinessDetails;
	
	@FindBy(name="tenant-address-country")
	WebElement Select_country;
	
	@FindBy(name="tenant-address-city")
	WebElement Select_State;
	
	@FindBy(id="tenant-address-zipcode")
	WebElement EnterZIPCode;
	
	@FindBy(id="tenant-address-01")
	WebElement EnterAddress;
	//---------------------------------------------
		
	@FindBy(xpath="//div[@class='collapseBox-head']//h3[text()='Technical Details ']")
	WebElement ClickonTechnicalDetails;
	
	@FindBy(id="admin-name")
	WebElement Super_Admin_Name;
	
	@FindBy(id="admin-name")
	WebElement Super_admin_id;
	
	@FindBy(id="admin-password")
	WebElement Super_admin_password;
		
	@FindBy(id="confirm-password")
	WebElement Super_Confirm_admin_password;
	
	@FindBy(id="isa-Qualifier")
	WebElement Default_ISA_ID_Qualifier;
	
	@FindBy(id="isa-id")
	WebElement Default_ISA_ID_;
	
	@FindBy(id="gsapp-code")
	WebElement Default_GS_App_Code;
	
	@FindBy(id="message-threshold")
	WebElement Message_threshold;
	
	@FindBy(id="timeformat-no")
	WebElement TimeFormat12;
	
	@FindBy(id="timeformat-yes")
	WebElement TimeFormat24;
	
	@FindBy(id="status-yes")
	WebElement status_yes;
	
	@FindBy(id="status-no")
	WebElement status_no;
	//---------------------------------------------
		
	@FindBy(xpath="//div[@class='collapseBox-head']//h3[text()='Contact Details']")
	WebElement ClickonContactDetails;
	
	@FindBy(xpath="//select[@class='form-control ng-pristine ng-valid ng-empty ng-touched']")
	WebElement SelectMr;
	
	@FindBy(id="tenant-contact-name-01")
	WebElement EnterContact_Name;
	
	@FindBy(id="tenant-contact-email-01__1")
	WebElement Enter_contact_emailID;
	
	@FindBy(id="tenant-contact-office-01")
	WebElement EnterContact_Office;
	
	@FindBy(id="tenant-contact-mobile-01")
	WebElement EnterContact_Mobile;
	
	@FindBy(id="tenant-contact-timezone-01")
	WebElement Select_Time_Zone;
	
	@FindBy(id="tenant-contact-type-01")
	WebElement Select_Contact_Type;
	//---------------------------------------------
	
	@FindBy(id="Tenantcreate")
	WebElement ClickOnTenantcreate;
		
	//Click On Ok Alert and Print message "Business profile created successfully."
	@FindBy(id="Tenantsave")
	WebElement ClickOnSave;
	
	//Edit something and Click On Save Alert and Print message "Business profile updated successfully."
		
	@FindBy(id="NextConn")
	WebElement ClickOnNEXT;
	
	@FindBy(id="BusinessprofCon1")
	WebElement ClickOnConnection_Profiles;
	
	@FindBy(id="conn-protocal")
	WebElement Connection_ProfilesHeaderText;
	
	@FindBy(id="connection-profile-01_")
	WebElement EnterConnectionProfile;
	
	@FindBy(xpath="//select[@class='form-control ng-pristine ng-untouched ng-empty ng-invalid ng-invalid-required']")
	WebElement Select_Protocol;
	
	@FindBy(xpath="//button[@class='btn btn-style ml-1']")
	WebElement CONNECTION_PROFILE_ClickOnSave;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
