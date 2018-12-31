package com.cavisson.pagemodel;

import com.automation.cavisson.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
public class TotalScenariosPage extends PageModel{
	@FindBy(xpath="//button[@label='Create']")
	WebElement createScenario;
	private String projectName;
	private String subProjectName;
	private String ScenarioName;
	public TotalScenariosPage (CavissonDriver driver,String testCaseName){
		super(driver);
		setTestCaseName(testCaseName);
	}

	public void executeCreateScenario(){
		getDriver().click( createScenario , getTestCaseName());		
	}
	
}
