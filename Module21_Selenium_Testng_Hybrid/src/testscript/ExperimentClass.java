package testscript;

import java.awt.event.ActionEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class ExperimentClass 
{
	private static StringBuffer verificationErrors = new StringBuffer();
	@Test
	public static void TestSample()throws Exception
	{
    WebDriver driver=new FirefoxDriver();
    String baseUrl="http://demo.orangehrmlive.com/";
	driver.get(baseUrl + "symfony/web/index.php/auth/login");
	driver.findElement(By.id("btnLogin")).click();	
		
//	driver.switchTo().frame("relative=up");
//	driver.switchTo().frame("relative=top");
//	driver.switchTo().defaultContent();
	driver.findElement(By.xpath("//li[@id='pim']/a/span")).click();
	driver.switchTo().frame("rightMenu");
//	driver.findElement(By.xpath("//div/div/input")).click();
//	driver.switchTo().frame("rightMenu");
//	driver.switchTo().frame(driver.findElement(By.id("2")));
//	driver.switchTo().defaultContent();
//	driver.switchTo().Window(winHandle);
//	driver.switchTo().frame("frameA");
	
	driver.findElement(By.xpath("//tr[4]/td[3]/a")).click();
	driver.findElement(By.xpath("//li[6]/a/span")).click();
	driver.findElement(By.xpath("//div[6]/input")).click();
	new Select(driver.findElement(By.xpath("//select"))).selectByVisibleText("Floor Supervisor");
	new Select(driver.findElement(By.xpath("//select[2]"))).selectByVisibleText("Full Time Permanant");
	new Select(driver.findElement(By.xpath("//select[4]"))).selectByVisibleText("Sales");
	driver.findElement(By.xpath("//div[6]/input")).click();
	
}

	private static String assertEquals(String string, String text)
	{
		return text;
		// TODO Auto-generated method stub
		
	}
	
}
