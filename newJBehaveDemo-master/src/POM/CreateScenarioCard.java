package com.cavisson.pagemodel;

import com.automation.cavisson.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
public class CreateScenarioCard extends PageModel{
	@FindBy(xpath="//div[text()='Project ']/parent::div/descendant::p-dropdown[contains(@class,'filterDrop')]")
	WebElement projectSelector;
	@FindBy(xpath="//div[text()='SubProject ']/parent::div/descendant::p-dropdown[contains(@class,'filterDrop')]")
	WebElement subProjectSelector;
	@FindBy(xpath="//footer/descendant::button[@label='Next']")
	WebElement createScenarioNextBtn;
	@FindBy(xpath="//div[text()='Scenario Name ']/parent::div/descendant::input")
        WebElement scenarioNameTextBox;
	private String projectName;
	private String subProjectName;
	private String scenarioName;
	public CreateScenarioCard(CavissonDriver driver,String testCaseName){
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
	public void setScenarioName(String scenarioName){
		this.scenarioName = scenarioName;
	}
	public String getScenarioName(){
		return this.scenarioName;
	}
	public void executeCreateScenario(){
		CavissonDriver driver = getDriver();
		driver.click(projectSelector , getTestCaseName());
		driver.click(By.xpath("//div[text()='Project ']/parent::div/descendant::p-dropdown[contains(@class,'filterDrop')]//ul/li/span[text()='"+ projectName +"']"), getTestCaseName());
		driver.click(subProjectSelector,getTestCaseName());
		driver.click(By.xpath("//div[text()='SubProject ']/parent::div/descendant::p-dropdown[contains(@class,'filterDrop')]//ul//span[text()='"+ subProjectName +"']"),getTestCaseName());
                driver.sendKeys(scenarioNameTextBox , scenarioName ,getTestCaseName());
		driver.submit(createScenarioNextBtn , getTestCaseName());
	}
	
}
