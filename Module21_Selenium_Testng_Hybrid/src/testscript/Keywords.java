package testscript;
//http://www.vogella.de/articles/JavaRegularExpressions/ar01s05.html
import static org.junit.Assert.assertEquals;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;
// testng
import org.testng.Assert;
import org.testng.Reporter;

import datatable.Xls_Reader;
public class Keywords extends DriverScript{
	
	
	// navigate
	public static String navigate(){
		APPICATION_LOGS.debug("Executing Navigate");
		if(wbdv == null){
			if(CONFIG.getProperty("testBrowser").equals("Firefox")){
				wbdv = new FirefoxDriver();
				driver = new EventFiringWebDriver(wbdv);
				driver.manage().window().maximize();
		//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			}
			//else
		}
		driver.navigate().to(CONFIG.getProperty(object));
		return "Pass";
		
	}
public static String Wait(){
		System.out.println("wait started ");
		try {
		APPICATION_LOGS.debug("Executing WaitTime");
		System.out.println(Integer.parseInt(CONFIG.getProperty("waitTime")));
		Thread.sleep(Integer.parseInt(CONFIG.getProperty("waitTime")));
		}catch(Throwable t){
			// report error
			APPICATION_LOGS.debug("Error while executin wait -"+ object + t.getMessage());
			return "Fail - Couldnt wait";
		}
		//driver.navigate().to(CONFIG.getProperty(object));
		return "Pass";
}
public static String switchframe1()
{
	APPICATION_LOGS.debug("Executing switchframe1");
	try
	{
		driver.switchTo().defaultContent();
	}
	catch(Throwable t)
	{
		APPICATION_LOGS.debug("Error while switching to other frame -"+ object +t.getMessage());
		return "Fail - Link Not Found";
	}
	return "Pass";
}
public static String switchframe()
{
	APPICATION_LOGS.debug("Executing switchframe");
	try
	{
//		if (!(APPTEXT.getProperty(object).equals("")))
//		{
//		driver.switchTo().frame("rightMenu");
		driver.switchTo().frame(APPTEXT.getProperty(object));
	
//		else 
//		{
//			driver.switchTo().defaultContent();
//
//		}
		
	}
	catch(Throwable t)
	{
		APPICATION_LOGS.debug("Error while switching to other frame -"+ object +t.getMessage());
		return "Fail - Link Not Found";
	}
	return "Pass";
}

public static String selectdropdown()
{
	APPICATION_LOGS.debug("Executing selectdropdown");
	// extract the test data
    System.out.println("current test sheet name"+DriverScript.tpidData);
	System.out.println("Data column name of Test data sheet:" +data_column_name);
	System.out.println("testrepeat: "+testRepeat);
	String data =testData.getCellData(DriverScript.tpidData, data_column_name, testRepeat);
	
    System.out.println("Senk key data: "+data);
	try
	{
	new Select(driver.findElement(By.xpath(OR.getProperty(object)))).selectByVisibleText(data);
	}
	catch(Throwable t)
	{
		APPICATION_LOGS.debug("Error while selecting DropDown -"+ object +t.getMessage());
		return "Fail - Link Not Found";
	}
	return "Pass";
}
	public static String clickLink()
	{
		APPICATION_LOGS.debug("Executing clickLink");
		try{
	//    Thread.sleep(3000);
		driver.findElement(By.xpath(OR.getProperty(object))).click();
		}catch(Throwable t){
			// report error
			APPICATION_LOGS.debug("Error while clicking on link -"+ object + t.getMessage());
			return "Fail - Link Not Found";
		}	
		
		return "Pass";
	}
	
	public static String verifyText()
	{
		APPICATION_LOGS.debug("Executing verifyText");
		String expected=APPTEXT.getProperty(object);
	    String actual=driver.findElement(By.xpath(OR.getProperty(object))).getText();
		APPICATION_LOGS.debug(expected);
		APPICATION_LOGS.debug(actual);
		try{
			Assert.assertEquals(actual.trim() , expected.trim());
		}catch(Throwable t){
			// error
			APPICATION_LOGS.debug("Error in text - "+object);
			APPICATION_LOGS.debug("Actual - "+actual);
			APPICATION_LOGS.debug("Expected -"+ expected);
			return "Fail -"+ t.getMessage(); 
			
		}
		
		return "Pass";
		
	}
	
	public static String input()
	{	
		APPICATION_LOGS.debug("Executing input Keyword");
		// extract the test data
	    System.out.println("current test sheet name"+DriverScript.tpidData);
		System.out.println("Data column name of Test data sheet:" +data_column_name);
		System.out.println("testrepeat: "+testRepeat);
		String data =testData.getCellData(DriverScript.tpidData, data_column_name, testRepeat);
		
	    System.out.println("Senk key data: "+data);
		
		try{
			System.out.println("OR object :"+OR.getProperty(object));
			
			driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(data);
			}catch(Throwable t){
				// report error
				APPICATION_LOGS.debug("Error while wrinting into input -"+ object + t.getMessage());
				return "Fail - "+t.getMessage();
			}
			
			return "Pass";
	}
	
	
	public static String clickButton()
	{
		APPICATION_LOGS.debug("Executing clickButton Keyword");		
		try{
			//System.out.println(OR.getProperty(object));
			driver.findElement(By.xpath(OR.getProperty(object))).click();
			}catch(Throwable t){
				// report error
				APPICATION_LOGS.debug("Error while clicking on Button -"+ object + t.getMessage());
				return "Fail - "+t.getMessage();
			}
			
			return "Pass";
	}
	
	public static String select(){
		APPICATION_LOGS.debug("Executing select Keyword");
		// extract the test data
		String data =testData.getCellData(currentTest, data_column_name , testRepeat);
		
		
		
		try{
			driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(data);
			}catch(Throwable t){
				// report error
				APPICATION_LOGS.debug("Error while Selecting from droplist -"+ object + t.getMessage());
				return "Fail - "+t.getMessage();
			}
			
			return "Pass";
	}
	
	public static String clickCheckBox()
	{
     APPICATION_LOGS.debug("Executing clickCheckBox Keyword");
		
		try{
			Thread.sleep(3000);
			driver.findElement(By.xpath(OR.getProperty(object))).click();
			}catch(Throwable t){
				// report error
				APPICATION_LOGS.debug("Error while clicking on checkbox -"+ object + t.getMessage());
				return "Fail - "+t.getMessage();
			}
			
			return "Pass";
	}
	public static String GetTitle(){
		  APPICATION_LOGS.debug("Executing GetTitle Keyword");
		  Reporter.log("Executing GetTitle Keyword");
		try{
		   driver.getTitle();
		   System.out.println(driver.getTitle());
		 
		   }catch(Throwable t){	
			   //report error
			   APPICATION_LOGS.debug("Error while clicking on checkbox -"+ object + t.getMessage());
			   Reporter.log("Error while clicking on checkbox -"+ object + t.getMessage());
				return "Fail - "+t.getMessage();   
		   }
		return "Pass";
	}
	public static String Wait1() throws NumberFormatException, InterruptedException
	{
	     APPICATION_LOGS.debug("Executing wait Keyword");
	  // extract the test data
			String data =testData.getCellData(DriverScript.tpidData, data_column_name , testRepeat);
			
	     Thread.sleep(Long.parseLong(data));
	     return "Pass";
	}
	  public static String CloseApplication()
	  {
		  APPICATION_LOGS.debug("Executing CloseApplication Keyword");
	
		try{
		  driver.close();
		  }catch(Throwable t){	
			  APPICATION_LOGS.debug("Error while closing application  -"+ object + t.getMessage());	  
				return "Fail - "+t.getMessage();  
	  }	
	
		return "Pass";
}
	public static String ClearEditField(){
		 APPICATION_LOGS.debug("Executing ClearEditField Keyword");	
			try{
				driver.findElement(By.xpath(OR.getProperty(object))).clear();
			 }catch(Throwable t){	
				  APPICATION_LOGS.debug("Error while clearing Edit field -"+ object + t.getMessage());
				  return "Fail - "+t.getMessage();  
			}
			 return "Pass";	
	}
	public static String VerifyLinkText()
	{
		APPICATION_LOGS.debug("Executing VerifyLinkText Keyword");	
		try{
			driver.findElement(By.linkText(APPTEXT.getProperty(object))).click();
		 }catch(Throwable t){	
	    APPICATION_LOGS.debug("Error while clicking link -"+ object + t.getMessage());
	    return "Fail - "+t.getMessage();  
		}
		return "Pass";	
	}
	public static String VerifyLinkTextDeleted()
	{
		APPICATION_LOGS.debug("Executing VerifyLinkTextDeleted Keyword");
		try{
			driver.findElement(By.linkText(OR.getProperty(object)));
	 }catch(Throwable t){	
		//    APPICATION_LOGS.debug("Error while Verifying Link Text Deleted -"+ object + t.getMessage());
		    return "Pass";  
			}
		return "Fail";	
	}
	public static String StoreTextAndVerify(){
		String a2 = driver.findElement(By.xpath("//td/div/table/tbody/tr/td")).getText();
		String expected=APPTEXT.getProperty(object);
		if(a2.equals(expected)){
			APPICATION_LOGS.debug("Executing VerifyProductEdited AppSpecificKeyword");
	    	return "Pass";
		}
	    else

	APPICATION_LOGS.debug("Executing clickButton AppSpecificKeyword");
	return "Fail - Link Not Found";
	}
		
	public static String SwitchToNewWindow() {
		System.out.println("executing switch window");
	Set<String> windows = driver.getWindowHandles();
	
    for (String window : windows) {
    	driver.switchTo().window(window); 
    	
  //	driver.manage().window().maximize();
        String windowTitle =APPTEXT.getProperty(object);
        System.out.println(windowTitle);
        System.out.println(driver.getTitle());
        if (driver.getTitle().contains(windowTitle)) {
     	//driver.switchTo().window(window); 
        
        	System.out.println("annavi");
        	
        	driver.manage().window().maximize();
        	
        	System.out.println("window maximized");
        }
           }
           return "Pass";    
                 
}
	public static String SwitchToParentWindow() {
		driver.switchTo().window("");
		return "Pass";
		
	}
	
	public static String VerifyElementPresent()
	{
		try{
		String text=APPTEXT.getProperty(object);
		driver.getPageSource().contains(text);
		 }catch(Throwable t){	
			    APPICATION_LOGS.debug("Error while Executing VerifyElementPresent -"+ object + t.getMessage());
			    return "Fail - "+t.getMessage();  
				}
				return "Pass";	
}
	public static String VerifyElementNotPresent()
	{
		
		String text=APPTEXT.getProperty(object);
		if(driver.getPageSource().contains(text))
		{
			  return "Fail";
		}
		else{
				
		return "Pass";	

	}
		}
	}