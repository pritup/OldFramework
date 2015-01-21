package testscript;

import org.testng.annotations.Test;

public class QuitApplication extends DriverScript
{
 @Test
 public static String teardown()throws Exception
 {
	 driver.quit();
	 return "Pass";
 }
	
}

