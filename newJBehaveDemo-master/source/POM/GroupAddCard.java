package com.cavisson.pagemodel;

import com.automation.cavisson.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
public class GroupAddCard extends PageModel{
	@FindBy(GroupAddCard.groupNameTextBox.using="GroupAddCard.groupNameTextBox.value")
	WebElement groupNameTextBox;
  	@FindBy(GroupAddCard.scriptTypeRadioButton.using="GroupAddCard.scriptTypeRadioButton.value")	
	WebElement scriptTypeRadioButton;
	@FindBy(GroupAddCard.userProfileDropDown.using="GroupAddCard.userProfileDropDown.value")
	WebElement userProfileDropDown;
	String internetUserProfileXpath = "GroupAddCard.internetUserProfileXpath.value";
	@FindBy(GroupAddCard.scriptProjectSelector.using="GroupAddCard.scriptProjectSelector.value")
	WebElement scriptProjectSelector;
	String scriptProjectNameXpath = "GroupAddCard.scriptProjectNameXpath.value";
	@FindBy(GroupAddCard.scriptSubProjectSelector.using="GroupAddCard.scriptSubProjectSelector.value")
	WebElement scriptSubProjectSelector;
	String scriptSubProjectNameXpath = "GroupAddCard.scriptSubProjectNameXpath.value";
	@FindBy(GroupAddCard.scriptDropDownList.using="GroupAddCard.scriptDropDownList.value")
	WebElement scriptDropDownList ;
	//@FindBy(GroupAddCard.scriptSearchBox.using="GroupAddCard.scriptSearchBox.value")
	//WebElement scriptSearchBox;
	String scriptSearchBox = "GroupAddCard.scriptSearchBox.value";
	String scriptNameSelectXpath = "GroupAddCard.scriptNameSelectXpath.value";
	@FindBy(GroupAddCard.groupAddOkBtn.using="GroupAddCard.groupAddOkBtn.value")
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

