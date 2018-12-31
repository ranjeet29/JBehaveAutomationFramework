package com.cavisson.pagemodel;
import com.automation.cavisson.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
public class Home extends PageModel{
	@FindBy(Home.loginUserNameLabel.using="Home.loginUserNameLabel.value")
	WebElement loginUserNameLabel;
	@FindBy(Home.totalScenariosCard.using="Home.totalScenariosCard.value")
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
