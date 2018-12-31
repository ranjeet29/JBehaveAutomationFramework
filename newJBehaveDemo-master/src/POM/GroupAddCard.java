package com.cavisson.pagemodel;

import com.automation.cavisson.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
public class GroupAddCard extends PageModel{
	@FindBy(name="groupName")
	WebElement groupNameTextBox;
  	@FindBy(xpath="//p-radiobutton[@value='Script']")	
	WebElement scriptTypeRadioButton;
	@FindBy(xpath="//div[text()='User Profile ']/parent::div/descendant::p-dropdown")
	WebElement userProfileDropDown;
	String internetUserProfileXpath = "//div[contains(@class,'ui-dropdown-items-wrapper')]/child::ul//span[text()='%s']";
	@FindBy(xpath="//div[text()='Project ']/parent::div/descendant::p-dropdown")
	WebElement scriptProjectSelector;
	String scriptProjectNameXpath = "//div[contains(@class,'ui-dropdown-items-wrapper')]/child::ul//span[text()='%s']";
	@FindBy(xpath="//div[text()='Sub Project ']/parent::div/descendant::p-dropdown")
	WebElement scriptSubProjectSelector;
	String scriptSubProjectNameXpath = "//div[contains(@class,'ui-dropdown-items-wrapper')]/child::ul//span[text()='%s']";
	@FindBy(xpath="//div[text()='Script ']/parent::div/descendant::p-dropdown")
	WebElement scriptDropDownList ;
	//@FindBy(xpath="//div[contains(@class,'ui-dropdown-items-wrapper')]/child::ul/ancestor::div[contains(@class,'ui-dropdown-panel')]/child::div[contains(@class,'ui-dropdown-filter-container')]/child::input")
	//WebElement scriptSearchBox;
	String scriptSearchBox = "//div[contains(@class,'ui-dropdown-items-wrapper')]/child::ul/ancestor::div[contains(@class,'ui-dropdown-panel')]/child::div[contains(@class,'ui-dropdown-filter-container')]/child::input";
	String scriptNameSelectXpath = "//div[contains(@class,'ui-dropdown-items-wrapper')]/child::ul//span[text()='%s']";
	@FindBy(xpath="//p-dialog[contains(@class,'addEditGroupDialog')]/div/descendant::button/span[text()='OK']")
	WebElement groupAddOkBtn;
	private String projectName;
	private String subProjectName;
	private String scriptName;
	private String groupName;
	private String internetProfileName = "Internet";
	public GroupAddCard(CavissonDriver driver,String testCaseName){
		super(driver);
		setTestCaseName(testCaseName);
	}	
        public void setProjectName(String projectName){
		this.projectName = projectName;
	} 
	public String getProjectName(){
		return this.projectName;
	}
	public void setSubProjectName(String subProjectName){
		this.subProjectName = subProjectName;	
	}
	public String getSubProjectName(){
		return this.subProjectName;
	}
	public void setScriptName(String scriptName){
		this.scriptName = scriptName;
	}	
	public String getScriptName(){
		return this.scriptName;
	}
	public void setGroupName(String groupName){
		this.groupName = groupName;
	}
	public String getGroupName(){
		return this.groupName;
	}
	public void executeAddGroup(){
		CavissonDriver driver = getDriver();
		driver.sendKeys(groupNameTextBox , groupName , getTestCaseName());
		driver.click(scriptTypeRadioButton , getTestCaseName());	
		driver.click(userProfileDropDown , getTestCaseName());
		driver.click(By.xpath(String.format(internetUserProfileXpath,internetProfileName)), getTestCaseName());
		driver.click(scriptProjectSelector, getTestCaseName());
		driver.click(By.xpath(String.format(scriptProjectNameXpath,projectName)),getTestCaseName());
		driver.click(scriptSubProjectSelector, getTestCaseName());
		driver.click(By.xpath(String.format(scriptSubProjectNameXpath,subProjectName)), getTestCaseName());
		driver.click(scriptDropDownList,getTestCaseName());
		//driver.sendKeys(scriptSearchBox,scriptName,getTestCaseName());
		driver.sendKeys(By.xpath(scriptSearchBox),scriptName,getTestCaseName());
		driver.click(By.xpath(String.format(scriptNameSelectXpath,scriptName)), getTestCaseName());
		driver.click(groupAddOkBtn,getTestCaseName());
	}
}

