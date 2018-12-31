package com.cavisson.pagemodel;

import com.automation.cavisson.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
public class ScenarioUiPage extends PageModel{

	@FindBy(xpath="//button[@title='Add Group']")
	WebElement addGroupButton;
	@FindBy(xpath="//md-icon[contains(@class,'produi-icon-scenario')]")
	WebElement openScenariosBtn;
        public ScenarioUiPage(CavissonDriver driver,String testCaseName){
                super(driver);
                setTestCaseName(testCaseName);
        }

        public void clickOnAddGroupButton(){
		getDriver().click(addGroupButton,getTestCaseName());
	}
	public void openScenarios(){
		getDriver().click(openScenariosBtn,getTestCaseName());
	}	
}

