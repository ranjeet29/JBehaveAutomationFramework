package com.cavisson.pagemodel;

import com.automation.cavisson.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
public class Login extends PageModel{
	@FindBy(Login.userNameTextBox.using="Login.userNameTextBox.value")
	WebElement userNameTextBox;
	@FindBy(Login.userPasswordTextBox.using="Login.userPasswordTextBox.value")
	WebElement userPasswordTextBox;
	@FindBy(Login.loginButton.using="Login.loginButton.value")
	WebElement loginButton;

	private String userName;
	private String userPassword;
	public Login(CavissonDriver driver,String testCaseName){
		super(driver);
		setTestCaseName(testCaseName);
	}
	public void setUserName(String userName){
		this.userName = userName;
	}
	public String getUserName(){
		return this.userName;
	}
	public void setUserPassword(String userPassword){
		this.userPassword = userPassword;
	}
	public String getUserPassword(){
		return this.userPassword;
	}
	public void executeLogin(){
		CavissonDriver driver = getDriver();
		String testCaseName = getTestCaseName();
        	driver.sendKeys( userNameTextBox , userName , testCaseName );			
        	driver.sendKeys( userPasswordTextBox , userPassword , testCaseName );		
		driver.submit( loginButton , testCaseName );	
	}

}
