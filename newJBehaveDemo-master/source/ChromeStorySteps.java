package com.cavisson.biswajit;

import com.cavisson.pagemodel.*;
import com.automation.cavisson.*;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
//import org.jbehave.core.annotations.And;
//import org.jbehave.core.junit.Assert;
import java.util.*;
import java.text.SimpleDateFormat;
import java.io.File;
import java.io.FileInputStream;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.*;




public class ChromeStorySteps {
    private String chromeExecutablePath = "";
    private String chromeBinaryPath = "";
    private boolean headlessFlag = false;
    private String baseDir;
    private String etcDir;
    private String imageDir;
    private String logDir;
    private String suiteName = "";
    private String testRunId = "";
    private CavissonDriver driver ;
    private Login loginModel ;
    private Home homeModel ;
    private ScenarioUiPage scenarioUiPage;
    private TotalScenariosPage totalScenariosPage;
    private CreateScenarioCard createScenarioCard;
    private GroupAddCard groupAddCard;
    @Given("a chrometest with testcaseName $testsuitename")
    public void givenStartChromeTest(@Named("testsuitename") String testsuitename) {  
        System.out.println("test case name = "+testsuitename);
        this.suiteName = testsuitename ;
        setUp(); 
	driver = instantiateDriver();
        try{
          //  Thread.sleep(2000);
        }catch(Exception e){}
    }
    @Given("open Netstorm ProductUI for $netstormBoxUI")
    public void openURL(@Named("netstormBoxUI") String netstormBoxUI){
        driver.get( netstormBoxUI , suiteName);
    }
    @When("login with username $username and password $password for $casename")
    public void login(@Named("username") String userName , @Named("password") String passowrd , @Named("casename") String caseName){
        //System.out.println("user name = " + userName + " , password = "+passowrd);
        loginModel = new Login(driver,caseName);
        loginModel.setUserName(userName);
        loginModel.setUserPassword(passowrd);
        loginModel.executeLogin();
         
    }
    @Then("home page login name should be $username for casename $casename")
    public void homeCheckLogin(@Named("username") String userName, @Named("casename") String caseName){
        homeModel = new Home(driver,caseName);
        homeModel.validateUserLogin(userName);        
    }
    @Given("create a scenario with name $scenarioname project $project subproject $subproject casename $casename")
    public void createScenario(@Named("scenarioname") String scenarioName,@Named("project") String project,@Named("subproject") String subproject,@Named("casename") String casename){
        homeModel.openTotalScenariosUI();
	totalScenariosPage = new TotalScenariosPage(driver,casename); 
	totalScenariosPage.executeCreateScenario();
	createScenarioCard = new CreateScenarioCard(driver,casename);
	createScenarioCard.setProjectName(project);
	createScenarioCard.setSubProjectName(subproject);
createScenarioCard.setScenarioName(scenarioName);
	createScenarioCard.executeCreateScenario();
    }
    @Given("add group with groupname $groupname , script $scriptname , project $project , subproject $subproject , casename $casename")
    public void createGroup(@Named("groupname")String groupname ,@Named("scriptname")String scriptname , @Named("project") String project , @Named("subproject") String subproject , @Named("casename")String casename){
        scenarioUiPage = new ScenarioUiPage(driver,casename);
        scenarioUiPage.clickOnAddGroupButton();
        groupAddCard = new GroupAddCard(driver,casename);
        groupAddCard.setProjectName(project);
        groupAddCard.setSubProjectName(subproject);
        groupAddCard.setScriptName(scriptname);
        groupAddCard.setGroupName(groupname);
        groupAddCard.executeAddGroup();
    }
    @Then("quit chrometest")
    public void quitTest(){
        driver.quit();
    }
    private void setUp(){
        Properties p = new Properties();
	try{
            if (new File("./etc/chromeTest.properties").exists()){
                p.load(new FileInputStream(new File("./etc/chromeTest.properties")));
                Iterator itr = p.keySet().iterator();
                String str = "";
                while(itr.hasNext()){
                    str= (String)itr.next();
                    System.out.println("The test 4j property for " + str +  " is " + p.getProperty(str));
                }
                chromeBinaryPath = p.getProperty("executableBinary");
                baseDir = p.getProperty("baseDir");
                etcDir = baseDir + "/etc"; 
                logDir = baseDir + "/logs"; 
                imageDir = baseDir + "/images";
                chromeExecutablePath = etcDir + "/" + p.getProperty("driverPath"); 
                testRunId = generateTestRunId();
	        makeTestRunDir();
            }
        }catch(Exception e){
            e.printStackTrace();
	}

    }
    private void makeTestRunDir(){
        File logFile = new File(logDir+"/"+testRunId);
        if (!logFile.exists()){
                try{
                        logFile.mkdirs();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
        }
        File imageFile = new File(imageDir+"/"+testRunId);
        if (!imageFile.exists()){
                try{
                        imageFile.mkdirs();
                }
                catch(Exception e){
		        e.printStackTrace();
                }
        }
    } 
    private String generateTestRunId(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyy_hhmmss");
        String strDate= formatter.format(date);
        return strDate;
    }
    
    private CavissonDriver instantiateDriver(){
        System.setProperty("webdriver.chrome.driver", chromeExecutablePath);
        System.setProperty("webdriver.chrome.logfile", baseDir+"/logs/chrome_debug.log");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability("acceptInsecureCerts", true);
        ChromeOptions options = new ChromeOptions();
        options.setBinary(chromeBinaryPath);
        if(headlessFlag){
            options.addArguments("--headless");
            options.addArguments("--disable-gpu");
            options.addArguments("--disable-logging");
            System.out.println("this is not executed for headless mode ");
        }
        options.addArguments("window-size=1368x720");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("disable-geolocation");
        options.addArguments("ignore-certificate-errors");
        System.out.println("***********************************"+options+"***********************");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        WebDriver driver = new ChromeDriver(capabilities);
        CavissonDriver cav = null;
        try{
                cav = new CavissonDriver(driver,etcDir,imageDir,logDir,suiteName,testRunId);
        }catch(Exception e){e.printStackTrace();}
        try{
        //Thread.sleep(10000);
        }catch(Exception e){e.printStackTrace();}
        return cav;
    } 
}
