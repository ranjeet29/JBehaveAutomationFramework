package com.cavisson.pagemodel;
import com.automation.cavisson.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
public class PageModel{
	private CavissonDriver CavDriver;
	private String testCaseName;
	public PageModel(CavissonDriver driver){
		CavDriver = driver;
		PageFactory.initElements(driver.getBaseDriver(),this);
	}
	public void setDriver(CavissonDriver driver){
		this.CavDriver = driver;
	}
	public CavissonDriver getDriver(){
		return this.CavDriver;
	}
	public void setTestCaseName(String testCaseName){
		this.testCaseName = testCaseName;
	}
	public String getTestCaseName(){
		return this.testCaseName;
	}
}
