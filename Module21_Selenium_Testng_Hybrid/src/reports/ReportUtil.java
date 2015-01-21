package reports;



import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;

import testscript.DriverScript;
import util.TestUtil;
public class ReportUtil {
	public static int scriptNumber=1;
	public static String indexResultFilename;
	public static String currentDir;
	public static String currentSuiteName;
	public static int tcid;
	//public static String currentSuitePath;
	
	public static double passNumber;
	public static double failNumber;
	public static boolean newTest=true;
	public static ArrayList<String> description=new ArrayList<String>();;
	public static ArrayList<String> keyword=new ArrayList<String>();;
	public static ArrayList<String> teststatus=new ArrayList<String>();;
	public static ArrayList<String> screenShotPath=new ArrayList<String>();;

	

	
    public static void startSuite(ArrayList myList,String suite,ArrayList tpList,String norepeat,String name){

	    FileWriter fstream =null;
		BufferedWriter out =null;
		//currentSuiteName = suiteName.replaceAll(" ", "_");
		tcid=1;
	    try{
	    	// build the suite folder
	    //	currentSuitePath = currentDir; //+"//"+suiteName.replaceAll(" ","_");
	    //	currentSuiteDir = suiteName.replaceAll(" ","_");
	    //	File f = new File(currentSuitePath);
		//	f.mkdirs();
	    	
	    
	    	
	    	
	  	
	      if(suite == "suite")
	      {
	    	  
	    	  indexResultFilename = DriverScript.FILE_NAME;
	    	  File f = new File(indexResultFilename);
  	        BufferedWriter out1 = new BufferedWriter(new FileWriter(f));
	    	  out1.write("<html>");
	  		out1.write("<head>");
	  		out1.write("<title>");
	  		out1.write("Suite");
	  		out1.write("</title>");
	  		out1.write("</head>");
	  		out1.write("<body>");
	  		
	  		 out1.write("<h4 align=center><FONT COLOR=660066 FACE=AriaL SIZE=6><b><u> Automation Test Results</u></b></h4>\n");
		     out1.write("<table  border=1 cellspacing=1 cellpadding=1 >\n");
		     out1.write("<tr>\n");

		     out1.write("<h4> <FONT COLOR=660000 FACE=Arial SIZE=4.5> <u>Test Details :</u></h4>\n");
		           out1.write("<td width=150 align=left bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2.75><b>Run Date</b></td>\n");
		           out1.write("<td width=150 align=left><FONT COLOR=#153E7E FACE=Arial SIZE=2.75><b>"+DriverScript.RUN_DATE+"</b></td>\n");
		     out1.write("</tr>\n");
		     out1.write("<tr>\n");
		           
		           out1.write("<td width=150 align=left bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2.75><b>Run StartTime</b></td>\n");

		           out1.write("<td width=150 align=left><FONT COLOR=#153E7E FACE=Arial SIZE=2.75><b>"+DriverScript.START_TIME+"</b></td>\n");
		     out1.write("</tr>\n");
		     out1.write("<tr>\n");
		    // out.newLine();   
		           out1.write("<td width=150 align= left  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2.75><b>Run EndTime</b></td>\n");
		           out1.write("<td width=150 align= left ><FONT COLOR=#153E7E FACE= Arial  SIZE=2.75><b>"+DriverScript.END_TIME+"</b></td>\n");
		     out1.write("</tr>\n");
		     out1.write("<tr>\n");
		   //  out.newLine();
		           
		           out1.write("<td width=150 align= left  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2.75><b>Environment</b></td>\n");
		           out1.write("<td width=150 align= left ><FONT COLOR=#153E7E FACE= Arial  SIZE=2.75><b>"+DriverScript.ENVIRONMENT+"</b></td>\n");
		     out1.write("</tr>\n");
		     out1.write("<tr>\n");
		           
		           out1.write("<td width=150 align= left  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2.75><b>Release</b></td>\n");
		           out1.write("<td width=150 align= left ><FONT COLOR=#153E7E FACE= Arial  SIZE=2.75><b>"+DriverScript.RELEASE+"</b></td>\n");
		     out1.write("</tr>\n");

		     out1.write("</table>\n");
	  		
	  		
	  		
	  		
	  		
	  		
	    	  out1.write("<table  border=1 cellspacing=1    cellpadding=1 width=100%>");
	    	  out1.write("<tr>\n");
	    	  out1.write("\n");
	    	  out1.write("<h4 align=center><FONT COLOR=#308850 FACE=AriaL SIZE=6><b><u>Scenarios</u></b></h4>\n");
	    	  out1.write("<td width=10%  align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>TPID</b></td>\n");


	    	  out1.write("<td width=40% align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Description</b></td>\n");
	          out1.write("<td width=10% align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Status</b></td>\n");
	          out1.write("<td width=40% align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Start Time</b></td>\n");
	          out1.write("<td width=10% align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>End Time</b></td>\n");
	          out1.write("</tr>\n");
	          for(int i=0;i<myList.size();i++ )
	          {
	        	  ArrayList temp = (ArrayList) myList.get(i);
	        	  out1.write("<tr>\n");  
	        	  out1.write("<td width=10%  align= center  bgcolor=#B0F0F0><FONT COLOR=000080 FACE= Arial  SIZE=2><b>"+"<a href=file:///C:/keyword_driven_rep/Suite_" + temp.get(0)+ ".htm"+ " target=_blank> "+ temp.get(0) +"</a></b></td>\n");

		          out1.write("<td width=40% align= center  bgcolor=#B0F0F0><FONT COLOR=#000080 FACE= Arial  SIZE=2><b>" + temp.get(1) + "</b></td>\n");
		          out1.write("<td width=10% align= center  bgcolor=#B0F0F0><FONT COLOR=#000080 FACE= Arial  SIZE=2><b>" + temp.get(2) + "</b></td>\n");
		          out1.write("<td width=40% align= center  bgcolor=#B0F0F0><FONT COLOR=#000080 FACE= Arial  SIZE=2><b>" + temp.get(3) + "</b></td>\n");
		          out1.write("<td width=10% align= center  bgcolor=#B0F0F0><FONT COLOR=#000080 FACE= Arial  SIZE=2><b>" + temp.get(4) + "</b></td>\n");
		       
	        	 
	        	  
	        	  out1.write("</tr>\n");
	          }
	         
	    	  
	    	  
	          out1.write("</table>");
	          out1.write("</body>");
		      out1.write("</html>");
		      out1.close();
	      }
	      if(suite == "tp")
	    	  
	      {
	    	  for(int x=0;x<tpList.size();x++ )
	          {
	    		  ArrayList temps = (ArrayList) tpList.get(x);
	    		//  File f = new File("C:\\C:\keyword_driven_repkeyword_driven_rep\\"+temps.get(x)+ ".html");
	    		  File f = new File("C:\\keyword_driven_rep\\Suite_"+name+".htm");
	    	        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
	    	        bw.write("<html>");
	    	        bw.write("<head>");
	    	        bw.write("<title>");
	    	        bw.write("Suite");
	    	        bw.write("</title>");
	    	        bw.write("</head>");
	    	        bw.write("<body>");
	    	        bw.write("<table  border=1 cellspacing=1    cellpadding=1 width=100%>");
	    	        bw.write("<tr>\n");
	    	        bw.write("<td width=10%  align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>TCID</b></td>\n");

	    	        bw.write("<td width=40% align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Description</b></td>\n");
	    	        bw.write("<td width=10% align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>Status</b></td>\n");
	    	        bw.write("</tr>\n");
	          for(int i=0;i<myList.size();i++ )
	          {
	        	  ArrayList temp = (ArrayList) myList.get(i);
	        	  bw.write("<tr>\n");  
	        	  bw.write("<td width=10%  align= center  bgcolor=#B0F0F0><FONT COLOR=#000080 FACE= Arial  SIZE=2><b>"+"<a href=file:///C:/keyword_driven_rep/Suite_" + temp.get(0)+ ".htm"+ " target=_blank> "+ temp.get(0) +"</a></b></td>\n");

	        	  bw.write("<td width=40% align= center  bgcolor=#B0F0F0><FONT COLOR=#000080 FACE= Arial  SIZE=2><b>" + temp.get(1) + "</b></td>\n");
	        	  bw.write("<td width=10% align= center  bgcolor=#B0F0F0><FONT COLOR=#000080 FACE= Arial  SIZE=2><b>" + temp.get(2) + "</b></td>\n");
		       
	        	 
	        	  
	        	  bw.write("</tr>\n");
	          }
	         
	    	  
	    	  
	          bw.write("</table>");
	          bw.write("</body>");
	          bw.write("</html>");
	          bw.close();
	          }
	    	
	      }
      if(suite == "tc")
	    	  
	      {
	    	  for(int x=0;x<tpList.size();x++ )
	          {
	    		  ArrayList temps = (ArrayList) tpList.get(x);
	    		//  File f = new File("C:\\C:\keyword_driven_repkeyword_driven_rep\\"+temps.get(x)+ ".html");
	    		  File f = new File("C:\\keyword_driven_rep\\Suite_"+norepeat+".htm");
	    	        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
	    	        bw.write("<html>");
	    	        bw.write("<head>");
	    	        bw.write("<title>");
	    	        bw.write("Suite");
	    	        bw.write("</title>");
	    	        bw.write("</head>");
	    	        bw.write("<body>");
	    	        bw.write("<table  border=1 cellspacing=1    cellpadding=1 width=100%>");
	    	        bw.write("<tr>\n");
	    	        bw.write("<td width=10%  align= center  bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>TSID</b></td>\n");

	    	        bw.write("<td width=40% align= center  bgcolor=#153E7E><FONT COLOR=#000080 FACE= Arial  SIZE=2><b>Description</b></td>\n");
	    	        bw.write("<td width=10% align= center  bgcolor=#153E7E><FONT COLOR=#000080 FACE= Arial  SIZE=2><b>Keyword</b></td>\n");
	    	        bw.write("<td width=10% align= center  bgcolor=#153E7E><FONT COLOR=#000080 FACE= Arial  SIZE=2><b>Status</b></td>\n");
	    	        bw.write("<td width=10% align= center  bgcolor=#153E7E><FONT COLOR=#000080 FACE= Arial  SIZE=2><b>Screenshot</b></td>\n");
	    	        bw.write("</tr>\n");
	          for(int i=0;i<myList.size();i++ )
	          {
	        	  ArrayList temp = (ArrayList) myList.get(i);
	        	  bw.write("<tr>\n");  
	        	  bw.write("<td width=10%  align= center  bgcolor=#B0F0F0><FONT COLOR=#000080 FACE= Arial  SIZE=2><b>"+ temp.get(0) +"</b></td>\n");

	        	  bw.write("<td width=40% align= center  bgcolor=#B0F0F0><FONT COLOR=#000080 FACE= Arial  SIZE=2><b>" + temp.get(1) + "</b></td>\n");
	        	  bw.write("<td width=10% align= center  bgcolor=#B0F0F0><FONT COLOR=#000080 FACE= Arial  SIZE=2><b>" + temp.get(2) + "</b></td>\n");
	        	  bw.write("<td width=10% align= center  bgcolor=#B0F0F0><FONT COLOR=#000080 FACE= Arial  SIZE=2><b>" + temp.get(3) + "</b></td>\n");
	        	if(temp.size() > 4)
	        	{
	        	  bw.write("<td width=10% align= center  bgcolor=#B0F0F0><FONT COLOR=#E0E0E0 FACE= Arial  SIZE=2><b>"+"<a href=file://"+System.getProperty("user.dir")+"/src/customizedReport/" + temp.get(4)+ " target=_blank> "+ "ScreenShot" +"</a> "+"</b></td>\n");
	        	}
	        	 
	        	  
	        	  bw.write("</tr>\n");
	          }
	         
	    	  
	    	  
	          bw.write("</table>");
	          bw.write("</body>");
	          bw.write("</html>");
	          bw.close();
	          }
	    	
	      }
	   
       
	    }catch(Exception e){
		      System.err.println("Error: " + e.getMessage());
	    }finally{
	    	
		    fstream=null;
		    out=null;
	    }

    }
    
    public static void endSuite(){
    	 FileWriter fstream =null;
 		BufferedWriter out =null;
 		
 	    try{
 	    fstream = new FileWriter(indexResultFilename,true);
 	  	out = new BufferedWriter(fstream);
        out.write("</table>\n");
        out.close();
 	    }catch(Exception e){
		      System.err.println("Error: " + e.getMessage());
	    }finally{
	    	
		    fstream=null;
		    out=null;
	    }

    }
	
	public static void addTestCase(String testCaseName,String testCaseStartTime,String testCaseEndTime,String status){
		newTest=true;
		FileWriter fstream=null;
		BufferedWriter out=null;
		
		
		try {
			newTest=true;
			// build the keywords page
		   if(status.equalsIgnoreCase("Skipped") || status.equalsIgnoreCase("Skip")){
			   
		   }else{
				File f = new File(currentDir+"//"+currentSuiteName+"_TC"+tcid+"_"+testCaseName.replaceAll(" ", "_")+".html");
				f.createNewFile();
				fstream = new FileWriter(currentDir+"//"+currentSuiteName+"_TC"+tcid+"_"+testCaseName.replaceAll(" ", "_")+".html");
				out = new BufferedWriter(fstream);
				out.write("<html>");
				out.write("<head>");
				out.write("<title>");
				out.write(testCaseName + " Detailed Reports");
				out.write("</title>");
				out.write("</head>");
				out.write("<body>");
			
			
			out.write("<h4> <FONT COLOR=660000 FACE=Arial SIZE=4.5> Detailed Report :</h4>");
		 	 out.write("<table  border=1 cellspacing=1    cellpadding=1 width=100%>");
		 	 out.write("<tr> ");
		        out.write("<td align=center width=10%  align=center bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2><b>Step/Row#</b></td>");
		        out.write("<td align=center width=50% align=center bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2><b>Description</b></td>");
		        out.write("<td align=center width=10% align=center bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2><b>Keyword</b></td>");
		        out.write("<td align=center width=15% align=center bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2><b>Result</b></td>");
		 		out.write("<td align=center width=15% align=center bgcolor=#153E7E><FONT COLOR=#E0E0E0 FACE=Arial SIZE=2><b>Screen Shot</b></td>");
		 	 out.write("</tr>");
		 	 if(description!=null){
		 		 for(int i=0;i<description.size();i++){
		 			 out.write("<tr> ");

		 			 out.write("<td align=center width=10%><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>TS"+(i+1)+"</b></td>");
		 			 out.write("<td align=center width=50%><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>"+description.get(i)+"</b></td>");
		 			 out.write("<td align=center width=10%><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>"+keyword.get(i)+"</b></td>");
		 			if(teststatus.get(i).startsWith("Pass"))
		 			     out.write("<td width=20% align= center  bgcolor=#BCE954><FONT COLOR=#153E7E FACE=Arial SIZE=2><b>"+teststatus.get(i)+"</b></td>\n");
		 			else if(teststatus.get(i).startsWith("Fail"))
		 			  	 out.write("<td width=20% align= center  bgcolor=Red><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>"+teststatus.get(i)+"</b></td>\n");
		 			     
		 			// out.write("<td align=center width=20%><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>"+teststatus.get(i)+"</b></td>");
		 			 if(screenShotPath.get(i) != null)
		 			 out.write("<td align=center width=20%><FONT COLOR=#153E7E FACE=Arial SIZE=1><b><a href="+screenShotPath.get(i)+" target=_blank>Screen Shot</a></b></td>");
		 			 else
		 				out.write("<td align=center width=20%><FONT COLOR=#153E7E FACE=Arial SIZE=1><b>&nbsp;</b></td>");	 
		 			 out.write("</tr>");
		 	 }
		 	 }
			
		 	 
		 	 
		 	 out.close();
			
			
		   }
			
			fstream = new FileWriter(indexResultFilename,true);
			out = new BufferedWriter(fstream);
			
			 fstream = new FileWriter(indexResultFilename,true);
			 out = new BufferedWriter(fstream);
			// out.newLine();
			
			 out.write("<tr>\n");
			 //System.out.println(currentSuitePath);
		     out.write("<td width=10% align= center ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>"+scriptNumber+"</b></td>\n");
		     if(status.equalsIgnoreCase("Skipped") || status.equalsIgnoreCase("Skip"))
		    	 out.write("<td width=40% align= center ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>"+testCaseName+"</b></td>\n");
		     else
		    	 out.write("<td width=40% align= center ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b><a href=file:///"+currentDir+"//"+currentSuiteName+"_TC"+tcid+"_"+testCaseName.replaceAll(" ", "_")+".html>"+testCaseName+"</a></b></td>\n");

		     tcid++;
		     if(status.startsWith("Pass"))
		     out.write("<td width=10% align= center  bgcolor=#BCE954><FONT COLOR=#153E7E FACE=Arial SIZE=2><b>"+status+"</b></td>\n");
		     else if(status.startsWith("Fail"))
		    	 out.write("<td width=10% align= center  bgcolor=Red><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>"+status+"</b></td>\n");
		     else if(status.equalsIgnoreCase("Skipped") || status.equalsIgnoreCase("Skip"))
			     out.write("<td width=10% align= center  bgcolor=yellow><FONT COLOR=#FFD800 FACE=Arial SIZE=2><b>"+status+"</b></td>\n");
		     
		     out.write("<td width=20% align= center ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>"+testCaseStartTime+"</b></td>\n");
		     out.write("<td width=20% align= center ><FONT COLOR=#153E7E FACE= Arial  SIZE=2><b>"+testCaseEndTime+"</b></td>\n");

		     out.write("</tr>\n");
		     
		     scriptNumber++;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}finally{
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		description= new ArrayList<String>();
		keyword= new ArrayList<String>();
		teststatus= new ArrayList<String>();
		screenShotPath = new ArrayList<String>();
		newTest=false;
	}
	
	
	
	
	public static void addKeyword(String desc,String key,String stat,String path){
		
		
			
		
		description.add(desc);
		keyword.add(key);
		teststatus.add(stat);
		screenShotPath.add(path);
		
}

	public static void updateEndTime(String endTime) {
		StringBuffer buf = new StringBuffer();
		try{
		    // Open the file that is the first 
		    // command line parameter
		    FileInputStream fstream = new FileInputStream(indexResultFilename);
		    // Get the object of DataInputStream
		    DataInputStream in = new DataInputStream(fstream);
		        BufferedReader br = new BufferedReader(new InputStreamReader(in));
		    String strLine;
		    
		    
		    
		    //Read File Line By Line
		    
		    while ((strLine = br.readLine()) != null)   {
		    	
		    
		    	
		     if(strLine.indexOf("END_TIME") !=-1){
		    	 strLine=strLine.replace("END_TIME", endTime);
		     }
		     buf.append(strLine);
		     
		     
		    }
		  //Close the input stream
		    in.close();
		    System.out.println(buf);
		    FileOutputStream fos=new FileOutputStream(indexResultFilename);
			 DataOutputStream   output = new DataOutputStream (fos);	 
	    	 output.writeBytes(buf.toString());
	    	 fos.close();
		    
		    }catch (Exception e){//Catch exception if any
		      System.err.println("Error: " + e.getMessage());
		    }
	
		
		
		
		
		
		
		
	}
	
	
	

	
	

}
