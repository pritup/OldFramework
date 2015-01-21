package testscript;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Properties;
import org.apache.log4j.Logger;
//testng 1
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
//
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import testscript.mail;
import testscript.QuitApplication;
import reports.ReportUtil;
import util.TestUtil;
import util.SendMail;
import datatable.Xls_Reader;

public class DriverScript {
	
	public static Properties CONFIG;
	public static Properties OR;
	public static Properties APPTEXT;
	public static Xls_Reader controller;
	public static Xls_Reader testData;

	public static String library;
	public static String currentTest;
	public static String keyword;
	public static WebDriver wbdv=null;
	public static EventFiringWebDriver driver=null;
	public static String object;
	public static String currentTSID;
	public static String stepDescription;
	public static String proceedOnFail;
	public static String testStatus;
	public static String tpidData;
	public static String END_TIME;
	public static String START_TIME;
	public static String RUN_DATE;
	public static String ENVIRONMENT;
	public static String RELEASE;
	public static String FILE_NAME;
	// temp
	public static String data_column_name;
	public static int  testRepeat;
	public static Logger APPICATION_LOGS = Logger.getLogger("devpinoyLogger");
	public static int  norepeat=1;
	public static int  runstatus;
	
	
	
	@BeforeSuite
	/*public static void startTesting(){
		ReportUtil.startTesting("C://keyword_driven_rep//index.html", 
                TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"), 
                "Prod",
                "1.1.3");
		
	}*/
	
	@BeforeClass
	public void initialize() throws IOException{
		// load the property fIles
		// load the config prop
		CONFIG = new Properties();
		FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"\\src\\config\\config.properties");
		CONFIG.load(fs);
		
		// LOAD OR
		OR = new Properties();
		fs = new FileInputStream(System.getProperty("user.dir")+"\\src\\config\\OR.properties");
		OR.load(fs);
		// app text prop load
		APPTEXT = new Properties();
		fs = new FileInputStream(System.getProperty("user.dir")+"\\src\\config\\app_text.properties");
		APPTEXT.load(fs);
		// intialize datatable
		controller= new Xls_Reader(System.getProperty("user.dir")+"\\src\\config\\Controller.xlsx");
		testData  =  new Xls_Reader(System.getProperty("user.dir")+"\\src\\config\\TestData.xlsx");
		
	}
	
	@Test
	public void testApp() {
		
		String startTime=null; 
		//String END_TIME=null;
		
    	//ReportUtil.startSuite("Suite");
    	ArrayList myList = new ArrayList();
    	ArrayList tplist = new ArrayList();
    	START_TIME=TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa");
		for(int Suite=2 ; Suite<=controller.getRowCount("Suite");Suite++)
		{

			RUN_DATE = TestUtil.now("dd.MMMMM.yyyy").toString();
			ENVIRONMENT = "Prod";
			RELEASE="1.1.3";
			FILE_NAME=System.getProperty("user.dir")+"/src/customizedReport/index.html";
			String suitestarted = TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa");
			
			currentTest = controller.getCellData("Suite", "TPID", Suite);
			// initilize start time of test
			String tpidDescription = controller.getCellData("Suite", "Description", Suite).toString();	
			String tpidstatus = controller.getCellData("Suite", "Runmode", Suite).toString();	
			ArrayList tpidTohtml = new ArrayList();
			tpidTohtml.add(currentTest);
			tpidTohtml.add(tpidDescription);
			tpidTohtml.add("Pass");
			tpidTohtml.add(suitestarted);
			myList.add(tpidTohtml);
			if(controller.getCellData("Suite", "Runmode", Suite).equals("Y")){
			
		
		for(int tpid=2 ; tpid<=controller.getRowCount(currentTest);tpid++)
		{
			tpidData = controller.getCellData(currentTest, "TCID", tpid).toString();
			String tpidsDescription = controller.getCellData(currentTest, "Description", tpid).toString();	
			String tpidsstatus = controller.getCellData(currentTest, "Runmode", tpid).toString();	
			ArrayList tpidsTohtml = new ArrayList();
			tpidsTohtml.add(tpidData);
			tpidsTohtml.add(tpidsDescription);
			tpidsTohtml.add("Pass");
			tplist.add(tpidsTohtml);
			ArrayList tclist = new ArrayList();
			// initilize start time of test
			if(controller.getCellData(currentTest, "Runmode", tpid).equals("Y")){
			
				// execute the keywords
				// loop again - rows in test data
				
					
				//for( testRepeat=2; testRepeat<=totalSets;testRepeat++){	
			
				APPICATION_LOGS.debug("Executing the test "+ currentTest);
				// implement keyword . Reflection API
			//	System.out.println(controller.getRowCount(currentTest));
				int totalSets=testData.getRowCount(tpidData); // holds total rows in test data sheet. IF sheet does not exist then 2 by default
				if(totalSets<=1){
					totalSets=2; // run atleast once
				}
			
				testRepeat=2;
			for( testRepeat=2; testRepeat<=totalSets;testRepeat++){	
			
				for(int tcid=2;tcid<=controller.getRowCount(tpidData);tcid++)
				{
					ArrayList tplisttohtml = new ArrayList();
					// values from xls
					library=controller.getCellData(tpidData, "Library", tcid);
					keyword=controller.getCellData(tpidData, "Keyword", tcid);
					object=controller.getCellData(tpidData, "Object", tcid);
					currentTSID=controller.getCellData(tpidData, "TSID", tcid);
					stepDescription=controller.getCellData(tpidData, "Decription", tcid);
					proceedOnFail=controller.getCellData(tpidData, "ProceedOnFail", tcid);
					data_column_name=controller.getCellData(tpidData, "Data_Column_Name", tcid);
					APPICATION_LOGS.debug(keyword);
					String id= controller.getCellData(tpidData, "TSID", tcid);
					tplisttohtml.add(id);
					tplisttohtml.add(stepDescription);
					tplisttohtml.add(keyword);
					
					
					
					try
					{
					
						 /* execute methods from SpecialPriceSetting.java class */ 
					     /*  if (library.equals("SpecialPriceSetting"))
					       {
					    	   System.out.println(keyword);
					    		System.out.println("launching special price");
				    	Method method=SpecialPriceSetting.class.getMethod(keyword);
				    	
						String result = (String)method.invoke(method);
						System.out.println("method executed ");
						tplisttohtml.add(result);
						tclist.add(tplisttohtml);
					       System.out.println("method executed");
					       }
					       
					       /* execute methods from actualpriceCheck.java class */ 
					     /*  if (library.equals("actualpriceCheck"))
					       {
					    	   System.out.println(keyword);
					    		System.out.println("launching special price");
				    	Method method=actualpriceCheck.class.getMethod(keyword);
				    	
						String result = (String)method.invoke(method);
						System.out.println("method executed ");
						tplisttohtml.add(result);
						tclist.add(tplisttohtml);
					       System.out.println("method executed");
					       }
					       
					    /* execute methods from specialpriceCheck.java class */   
					    /*   if (library.equals("specialpriceCheck"))
					       {
					    	   System.out.println(keyword);
					    		System.out.println("launching special price");
				    	Method method=specialpriceCheck.class.getMethod(keyword);
				    	String result = (String)method.invoke(method);
						System.out.println("method executed ");
						tplisttohtml.add(result);
						tclist.add(tplisttohtml);
					       System.out.println("method executed");
					       }
					       
					       if (library.equals("BuyButtonSetting"))
					       {
					    	   System.out.println(keyword);
					    		System.out.println("launching special price");
				    	Method method=BuyButtonSetting.class.getMethod(keyword);
				    	String result = (String)method.invoke(method);
						System.out.println("method executed ");
						tplisttohtml.add(result);
						tclist.add(tplisttohtml);
					       System.out.println("method executed");
					       }
					       
					       if (library.equals("BuybtnVerification"))
					       {
					    	   System.out.println(keyword);
					    		System.out.println("launching special price");
				    	Method method=BuybuttonVerification.class.getMethod(keyword);
				    	String result = (String)method.invoke(method);
						System.out.println("method executed ");
						tplisttohtml.add(result);
						tclist.add(tplisttohtml);
					       System.out.println("method executed");
					       }
						    /* execute methods from CustomSettings.java class */   

				    /*   if (keyword.equals("CustomSettings")) {
				    	   System.out.println(keyword);
				    		
			    	Method method=CustomReader_General_ALL.class.getMethod(keyword);
					String result = (String)method.invoke(method);
					tplisttohtml.add(result);
					tclist.add(tplisttohtml);
				       }
				       if (keyword.equals("teardown")) {
				    	   System.out.println(keyword);
				    		
			    	Method method=QuitApplication.class.getMethod(keyword);
					String result = (String)method.invoke(method);
					tplisttohtml.add(result);
					tclist.add(tplisttohtml);
				       }
				       if (keyword.equals("DefaultSetting")) {
				    	   System.out.println(keyword);
				    	   
				    Method method=Default_General_Settings_ALL.class.getMethod(keyword);
					String result = (String)method.invoke(method);
					tplisttohtml.add(result);
					tclist.add(tplisttohtml);
				       }
				       if (keyword.equals("zipandmail")) {
				    	   System.out.println(keyword);
				    	   
				    Method method=mail.class.getMethod(keyword);
					String result = (String)method.invoke(method);
					tplisttohtml.add(result);
					tclist.add(tplisttohtml);
				       }
				       
				       
				       if (library.equals("Tax")) {
				    	   System.out.println(keyword);
				    	   
				    Method method=Tax.class.getMethod(keyword);
					String result = (String)method.invoke(method);
					tplisttohtml.add(result);
					tclist.add(tplisttohtml);
				       }
				       */
				       
						Method method= Keywords.class.getMethod(keyword);
				       
						String result = (String)method.invoke(method);
						tplisttohtml.add(result);
						APPICATION_LOGS.debug("***Result of execution -- "+result);
						if(result.startsWith("Fail")){
							testStatus=result;
							
							for(int k=0;k<tplist.size();k++)
							{
								ArrayList temps = (ArrayList) tplist.get(k);
								ArrayList t= new ArrayList();
							if(temps.get(0).toString() == tpidData)
							{
								String at0=temps.get(0).toString();
								String at1=temps.get(1).toString();
								temps.set(2, "Fail");
								t.add(at0);
								t.add(at1);
								t.add("Fail");
								tplist.set(k, t);
							}
							}
							for(int l=0;l<myList.size();l++)
							{
								ArrayList temps = (ArrayList) myList.get(l);
								ArrayList t= new ArrayList();
								if(temps.get(0).toString() == currentTest)
								{
									String at0=temps.get(0).toString();
									String at1=temps.get(1).toString();
									temps.set(2, "Fail");
									t.add(at0);
									t.add(at1);
									t.add("Fail");
									t.add(suitestarted);
									myList.set(l, t);
								}
							}
							
							// take screenshot - only on error
					        String fileName="Suite1_TC"+tcid+"_TS"+"_"+keyword+testRepeat+".jpg";
							TestUtil.takeScreenShot(CONFIG.getProperty("screenshotPath")+fileName);
						//ReportUtil.addKeyword(stepDescription, keyword, result, fileName);
                            tplisttohtml.add(fileName);
                            
							if(proceedOnFail.equalsIgnoreCase("N")){
								break;
							}
						
						}
						else
						{
							
							// comment if screen shot not required for pass
							//  String fileName="Suite1_TC"+tcid+"_TS"+"_"+keyword+testRepeat+".jpg";
							//	TestUtil.takeScreenShot(CONFIG.getProperty("screenshotPath")+fileName);
							//	 tplisttohtml.add(fileName);
						}
						
						tclist.add(tplisttohtml);
						norepeat++;
						//ReportUtil.startSuite(tclist,"tc",tplist);
					}
				
				
					// take screenshot - every keyword
				

					
					
					catch(Throwable t){ 
 						APPICATION_LOGS.debug("Error came");
 						System.out.println(t.getMessage());
					}
					
					
				}// keywords
				// report pass or fail
				if(testStatus == null){
					testStatus="Pass";
				}
				
				
				APPICATION_LOGS.debug("***********************************"+currentTest+" --- " +testStatus);
				//ReportUtil.addTestCase(currentTest, 
							//			startTime, 
							//			TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"),
							//			testStatus );
				// test data

				
			}
			}
			
		else
		{
				APPICATION_LOGS.debug("Skipping the test "+ currentTest);
				testStatus="Skip";
				for(int k=0;k<tplist.size();k++)
				{
				ArrayList temps = (ArrayList) tplist.get(k);
				ArrayList t= new ArrayList();
				if(temps.get(0).toString() == tpidData)
				{
					String at0=temps.get(0).toString();
					String at1=temps.get(1).toString();
					temps.set(2, "Skip");
					t.add(at0);
					t.add(at1);
					t.add("Skip");
					t.add(suitestarted);
					tplist.set(k, t);
				}
				}
				// report skipped
				APPICATION_LOGS.debug("***********************************"+currentTest+" --- " +testStatus);
				//ReportUtil.addTestCase(currentTest, 
									//	TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"), 
									//	TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"),
										//testStatus );
				
			}
			ReportUtil.startSuite(tclist,"tc",tplist,tpidData,"");
			testStatus=null;
			ReportUtil.startSuite(tplist,"tp",myList,"1",currentTest)
			;
		}
	
		tplist.clear();
		//ReportUtil.endSuite();
	}
			else{
				APPICATION_LOGS.debug("Skipping the test "+ currentTest);
				testStatus="Skip";
				for(int l=0;l<myList.size();l++)
				{
					ArrayList temps = (ArrayList) myList.get(l);
					ArrayList t= new ArrayList();
					if(temps.get(0).toString() == currentTest)
					{
						String at0=temps.get(0).toString();
						String at1=temps.get(1).toString();
						temps.set(2, "Skip");
						t.add(at0);
						t.add(at1);
						t.add("Skip");
						myList.set(l, t);
					}
				}
				// report skipped
				APPICATION_LOGS.debug("***********************************"+currentTest+" --- " +testStatus);
			//	ReportUtil.addTestCase(currentTest, 
							//			TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"), 
							//			TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"),
							//			testStatus );
				
			}
			
			testStatus=null;
			String suiteendtime = TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa");
			for(int l=0;l<myList.size();l++)
			{
				ArrayList temps = (ArrayList) myList.get(l);
				ArrayList t= new ArrayList();
				if(temps.get(0).toString() == currentTest)
				{
					String at0=temps.get(0).toString();
					String at1=temps.get(1).toString();
					String at2=temps.get(2).toString();
				//	temps.set(2, "Fail");
					t.add(at0);
					t.add(at1);
					t.add(at2);
					t.add(suitestarted);
					t.add(suiteendtime);
					myList.set(l, t);
				}
			}
			
		}
		END_TIME = TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa");
		ReportUtil.startSuite(myList,"suite",myList,"1","");
		
		//Zipping the folder and sending mail code starts
/////////////////////////////////////////////////////////////////////////////////////////////////		
//		TestUtil.zip("C:\\keyword_driven_rep");
//		   String[] to={"annavik@qualitreeinc.com"};
//
//           String[] cc={};
//           String[] bcc={};
//		   SendMail.sendMail("ksrikanth2k9@gmail.com",
//		            "994451451454",
//		            "smtp.gmail.com",
//		            "465",
//		            "true",
//		            "true",
//		            true,
//		            "javax.net.ssl.SSLSocketFactory",
//		            "false",
//		           to,
//		            cc,
//		           bcc,
//		            "Automation test Reports",
//		            "Please find the reports attached.\n\n Regards\nWebMaster",
//		        	System.getProperty("user.dir")+"\\Reports.zip",
//		        	"Reports.zip");
		   
//////////////////////////////////////////////////////////////////////////////////////////////////
	//SendMail.sendMail(userName, passWord, host, port, starttls, auth, debug, socketFactoryClass, fallback, to, cc, bcc, subject, text, attachmentPath, attachmentName)SendMail();
	//	ReportUtil.startSuite(tclist,"tp",tplist);
		}
	
	@AfterSuite
	public static void endScript(){
		
	//	ReportUtil.updateEndTime(TestUtil.now("dd.MMMMM.yyyy hh.mm.ss aaa"));
		
			}
	

}
