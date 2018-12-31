package com.cavisson.pagemodel;
import com.automation.cavisson.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
public class Home extends PageModel{
	@FindBy(xpath="//div[contains(@class,'ui-splitbutton')]/button[contains(@class,'ui-button-text-icon-left')]/span[contains(@class,'ui-button-text')]")
	WebElement loginUserNameLabel;
	@FindBy(xpath="//md-card[contains(@class,'totalScenarioCard')]")
	WebElement totalScenariosCard; 
        public Home(CavissonDriver driver,String testCaseName){
		super(driver);
		setTestCaseName(testCaseName);
	}
        public boolean validateUserLogin(String userName){
		return getDriver().assertByValue(loginUserNameLabel , userName , getTestCaseName());
	}	
        public void openTotalScenariosUI(){
	        getDriver().click(totalScenariosCard , getTestCaseName());	
        }
}
