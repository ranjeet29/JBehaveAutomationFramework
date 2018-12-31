package com.automation.cavisson;

import org.openqa.selenium.TimeoutException;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;
import org.openqa.selenium.TakesScreenshot;
import java.io.*;
import java.util.Properties;
import org.openqa.selenium.support.ui.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
public class CavissonDriver
{
	private String url;
        private String imageDir ;
        private String logDir ;
        private String suiteName ;
	private Logger log = Logger.getLogger(CavissonDriver.class.getName());
	private WebDriver driver;
	private WebDriverWait wait;
        public CavissonDriver(WebDriver driver,String etcDir,String imgDir,String logDir , String suiteName )throws IOException
	{
		this.driver=driver;
		Properties p=new Properties();
        	String proprtiesFile = etcDir+"/log4j-test.properties";
        	this.imageDir=imgDir;
        	this.logDir=logDir;
		this.suiteName = suiteName;
        	/*if (new File(etcDir+"/log4j-test.properties").exists()){
	       		p.load(new FileInputStream(proprtiesFile));
			Iterator itr = p.keySet().iterator();
			String str = "";
			while(itr.hasNext()){
				str= (String)itr.next();
				System.out.println("The test 4j property for " + str +  " is " + p.getProperty(str));
			}
			//PropertyConfigurator.configure(p);
			PropertyConfigurator.configure(getProperties(logDir,suiteName));
        	}
        	else{
            		log.warn("properties file not found hence continueing with out logging");
        	}*/
		PropertyConfigurator.configure(getProperties(logDir,suiteName));
	}
        public CavissonDriver(WebDriver driver,String etcDir,String imgDir,String logDir ,String suiteName,String testRunId)throws IOException{
		this(driver , etcDir ,imgDir+"/"+testRunId , logDir+"/"+testRunId,suiteName);		
	}
        public WebDriver getBaseDriver(){
		return driver;
	}
	public WebElement findElement(By by)
	{
		return driver.findElement(by);
	}
        public int sendKeys(By by,String str,String testcase){
		return sendKeys(driver.findElement(by) , str , testcase);
	}
        public int sendKeys(WebElement element , String str,String testcase)
	{
		try{
			waitForLoad(driver);
			element.clear();
			element.sendKeys(str);
			waitForLoad(driver);
			captureScreenShot(driver);
			//System.out.println("inside try");
			log.info(String.format("Keys sent to the TextBox for case : \"%s\" : Screenshot captured ",testcase));

		}
		catch(NoSuchElementException e)
		{
		 log.error(String.format("Element not found : \"%s\" : [Exception] :\"%s\" ",testcase,e.getMessage()));return 1;
		}
		catch(ElementNotVisibleException e)
		{
		 log.error(String.format("Element not Visible : \"%s\" : [Exception] :\"%s\" ",testcase,e.getMessage()));	return 2;
		}
		catch(NotFoundException e)
		{
		 log.error(String.format("Element not Found : \"%s\" : [Exception] : \"%s\" ",testcase,e.getMessage()));	return 3;
		}
		catch(Exception e){
                log.error(String.format("Mescelleneous Exception : \"%s\" : [Exception] : \"%s\" ",testcase,e.getMessage()));    return 3;
		}
		return 0;
	}
        public int select(By by,String str,String testcase){
		return select(driver.findElement(by) , str , testcase);
	}
        public int select(WebElement element , String str , String testcase){
		try{
			waitForLoad(driver);
			new Select(element).selectByVisibleText(str);
			waitForLoad(driver);
			captureScreenShot(driver);
			log.info(String.format("Element selected for case : \"%s\" : Screenshot captured ",testcase));

		}
		catch(NoSuchElementException e)
		{
		 log.error(String.format("Element not found : \"%s\" : [Exception] :\"%s\" ",testcase,e.getMessage()));return 1;
		}
		catch(ElementNotVisibleException e)
		{
		 log.error(String.format("Element not Visible : \"%s\" : [Exception] :\"%s\" ",testcase,e.getMessage()));	return 2;
		}
		catch(NotFoundException e)
		{
		 log.error(String.format("Element not Found : \"%s\" : [Exception] : \"%s\" ",testcase,e.getMessage()));	return 3;
		}
		catch(Exception e){
                log.error(String.format("Mescelleneous Exception : \"%s\" : [Exception] : \"%s\" ",testcase,e.getMessage()));    return 3;
		}
		return 0;
		}        
	public boolean assertByValue(By by,String str,String testcase){
		return assertByValue(driver.findElement(by) , str , testcase);
	}
	public boolean assertByValue(WebElement element , String str , String testcase){
		boolean assertResult = false;
		try{
			waitForLoad(driver);
			assertResult = element.getText().contains(str);
                        System.out.println("Inside assert "+element.getText());
			waitForLoad(driver);
			captureScreenShot(driver);
			//System.out.println("inside try");
			log.info(String.format("Assertion done for case : \"%s\" : Screenshot captured  Assert result = \"%s\"",testcase,String.valueOf(assertResult)));
			 
		}
		catch(NoSuchElementException e)
		{
		 log.error(String.format("Element not found : \"%s\" : [Exception] :\"%s\" ",testcase,e.getMessage()));return false;
		}
		catch(ElementNotVisibleException e)
		{
		 log.error(String.format("Element not Visible : \"%s\" : [Exception] :\"%s\" ",testcase,e.getMessage()));	return false;
		}
		catch(NotFoundException e)
		{
		 log.error(String.format("Element not Found : \"%s\" : [Exception] : \"%s\" ",testcase,e.getMessage()));	return false;
		}
		catch(Exception e){
                log.error(String.format("Mescelleneous Exception : \"%s\" : [Exception] : \"%s\" ",testcase,e.getMessage()));e.printStackTrace();    return false;
		}
		return assertResult;
		}


		public int get(String url,String testcase)
                {
                try{
                        waitForLoad(driver);
                        driver.get(url);
                        waitForLoad(driver);
                        captureScreenShot(driver);
                        //System.out.println("inside try");
                        log.info(String.format("navigation to page \"%s\" done for : \"%s\" : Screenshot captured ",url,testcase));

                }
                catch(Exception e){
                log.error(String.format("Mescelleneous Exception : \"%s\" : [Exception] : \"%s\" ",testcase,e.getMessage()));    return 3;
                }
                return 0;
                }

	public int click(By by,String testcase){
		return click(driver.findElement(by) , testcase);
	}
    	public int click(WebElement element , String testcase)
		{
                try{
			waitForLoad(driver,element);
                        element.click(); 
			waitForLoad(driver);
			captureScreenShot(driver);
                        //System.out.println("inside try2");
			log.info(String.format("Succesfully Clicked : \"%s\" : Screenshot captured ",testcase));

                }
        catch(NoSuchElementException e)
        {
         log.error(String.format("Element not found : \"%s\" : [Exception] :\"%s\" ",testcase,e.getMessage()));return 1;
        }
        catch(ElementNotVisibleException e)
        {
         log.error(String.format("Element not Visible : \"%s\" : [Exception] :\"%s\" ",testcase,e.getMessage()));   return 2;
        }
        catch(NotFoundException e)
        {
         log.error(String.format("Element not Found : \"%s\" : [Exception] : \"%s\" ",testcase,e.getMessage()));    return 3;
        }
        catch(UnhandledAlertException te){
            driver.switchTo().alert().accept();
            waitForLoad(driver);
        }
        catch(Exception e){
        log.error(String.format("Mescelleneous Exception : \"%s\" : [Exception] : \"%s\" ",testcase,e.getMessage()));    return 4;
	}
		return 0;
        }
        public int submit(By by,String testcase){
		return submit(driver.findElement(by) , testcase);
	}
        public int submit(WebElement element , String testcase)
        {
	    url=null;
            try{        			
    			url=driver.getCurrentUrl();
    			System.out.println("Initial wait started");
                	waitForLoad(driver);
    			System.out.println("Initial wait is over");
                	element.click();
    			System.out.println("going to capture screenshot");
    			captureScreenShot(driver);
    			System.out.println("Completed capture screenshot");
    			System.out.println("Secondary wait started");
                	wait = new WebDriverWait(driver, 60);
                	//wait.until(ExpectedConditions.stalenessOf(element));
                	wait.until(new ExpectedCondition<Boolean>(){
			    @Override
                            public Boolean apply(WebDriver driver){
                                try{
                                    return !element.isEnabled();
                                }catch(NoSuchElementException e){return true;}
                                catch(Exception e){return false;}
                            }

                        });
    			System.out.println("Secondary wait Completed");
    			captureScreenShot(driver);
			log.info(String.format("Succesfully Submitted for case: \"%s\" : Screenshot captured ",testcase));
            }
            catch(NoSuchElementException e)
            {
             log.error(String.format("Element not found : \"%s\" : [Exception] :\"%s\" ",testcase,e.getMessage()));return 1;
            }
            catch(ElementNotVisibleException e)
            {
             log.error(String.format("Element not Visible : \"%s\" : [Exception] :\"%s\" ",testcase,e.getMessage()));   return 2;
            }
            catch(NotFoundException e)
            {
             log.error(String.format("Element not Found : \"%s\" : [Exception] : \"%s\" ",testcase,e.getMessage()));    return 3;
            }
            catch(UnhandledAlertException te){
                driver.switchTo().alert().accept();
                wait.until(ExpectedConditions.stalenessOf(element));
                captureScreenShot(driver);
                log.info(String.format("Succesfully Submitted for case: \"%s\" : Screenshot captured ",testcase));
            }
            catch(TimeoutException te){
    			try{
                        	wait = new WebDriverWait(driver, 30);
		        	System.out.println("inside last wait");
                        	wait.until(new ExpectedCondition<Boolean>(){
                        		public Boolean apply(WebDriver driver){
                        			return (!url.equals(driver.getCurrentUrl()));}
    				});
				System.out.println("inside last wait done");
				waitForLoad(driver);
				System.out.println("going to capture screenshot");
    				captureScreenShot(driver);
				System.out.println("going to capture screenshot done");
    			}catch(Exception e){e.printStackTrace();System.out.println("inside try3333333333");}   
            }
            catch(Exception te){
            	te.printStackTrace();
            	log.error(String.format("Mesceleneous Exception : \"%s\" : [Exception] : \"%s\" ",testcase,te.getMessage()));    return 4;
            }
            return 0;
        }

	public void captureScreenShot(WebDriver Idriver){
            File src = null;
            try{
		 try{
                 	switchWindow(driver);
		 }catch(Exception e){}
       		 src =(File)((TakesScreenshot)Idriver).getScreenshotAs(OutputType.FILE);
            }catch(UnhandledAlertException ae){
                driver.switchTo().alert().accept();
            }
	    catch(Exception e){
		
		log.error(String.format("Mescelleneous Exception  : [Exception] : \"%s\" ",e.getMessage()));	
	    }
     		try {
				
			if(src!=null){
     		 	FileUtils.copyFile(src, new File(imageDir+"/Img_" + suiteName + "_" + System.currentTimeMillis()+".png"));}
		     }
      		catch (IOException e)
     		{
       			System.out.println(e.getMessage());
      		}
     	}
       public void switchWindow(WebDriver driver) throws IOException
	{
        	String parentWindow = driver.getWindowHandle();
        	new WebDriverWait(driver,1).until(ExpectedConditions.numberOfWindowsToBe(2));
    		for (String winHandle : driver.getWindowHandles()) 
        	{
    			if (!parentWindow.equalsIgnoreCase(winHandle))
        		driver.switchTo().window(winHandle);
    		}
	}		
       private void waitForLoad(WebDriver driver) 
 		{
 	 	ExpectedCondition pageLoads = new ExpectedCondition<Boolean>(){
        	public Boolean apply(WebDriver driver){
                return (Boolean)((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
        	}
        	};
	        wait = new WebDriverWait(driver, 30);
		wait.until(pageLoads);
		}
       private void waitForLoad(WebDriver driver,WebElement element) 
 		{
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
 	 	ExpectedCondition pageLoads = new ExpectedCondition<Boolean>(){
        	public Boolean apply(WebDriver driver){
                return (Boolean)((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
        	}
        	};
	        wait = new WebDriverWait(driver, 30);
		wait.until(pageLoads);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		}
        public void waitFor(int interval){
       		try{
			Thread.sleep(interval*1000);
		}catch(Exception e){System.out.println("Thread is interrupted");}
       }
       public void quit(){
            try{
                driver.quit();
            }catch(Exception e){
                log.error(String.format("Driver Quit Exception : [Exception] : \"%s\" ",e.getMessage()));
            }    
       }
       public void log(String message)
       {
           log.info(message);
	
       }
       private Properties getProperties(String logDir , String suiteName){
           Properties p = new Properties();
	   p.put("log",logDir); 
	   p.put("log4j.rootLogger","INFO,FILE"); 
	   p.put("log4j.appender.FILE","org.apache.log4j.FileAppender"); 
	   p.put("org.apache.log4j.FileAppender","true"); 
	   p.put("log4j.appender.FILE.File","${log}/log_chrome_"+suiteName+".html"); 
	   p.put("log4j.logger.org.apache","off"); 
	   p.put("log4j.logger.org.hybernate","off"); 
	   p.put("log4j.logger.org.springframework","off"); 
	   p.put("log4j.appender.FILE.layout","HtmlCustomLayout"); 
	   p.put("log4j.appender.FILE.layout.Title","HTML Layout Example"); 
	   p.put("log4j.appender.FILE.layout.LocationInfo","false");
	   return p; 
       } 

}
