package testscript;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

public class mail extends DriverScript
{
@Test
	public static String zipandmail()throws Exception
	{
	try 
	{
		Workbook workbook=Workbook.getWorkbook(new File("D:\\Orange_FRM\\Frame_work_latest\\Module21_Selenium_Testng_Hybrid\\src\\testscript\\ZipAndMail.xls"));
		Sheet sheet = workbook.getSheet(0); 
		Cell a=sheet.getCell(0,2);
		String ZippedFolderPath=a.getContents();
		System.out.println(ZippedFolderPath);
		//name of zip file to create
		String outFilename = ZippedFolderPath;
		
		//create ZipOutputStream object
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outFilename));
		
		//path to the folder to be zipped
		Cell b=sheet.getCell(1,2);
		String PathOfFolderToBeZipped=b.getContents();
		System.out.println(PathOfFolderToBeZipped);
		File zipFolder = new File(PathOfFolderToBeZipped);
		
		//get path prefix so that the zip file does not contain the whole path
		// eg. if folder to be zipped is /home/lalit/test
		// the zip file when opened will have test folder and not home/lalit/test folder
		int len = zipFolder.getAbsolutePath().lastIndexOf(File.separator);
		String baseName = zipFolder.getAbsolutePath().substring(0,len+1);
		
		addFolderToZip(zipFolder, out, baseName);
		System.out.println("Folder Zipped");
		File file = new File("D:/Report2.zip");
        long filesize = file.length();
        long filesizeInKB = filesize / 1024;
        System.out.println("Size of File is: " 
        + filesizeInKB + " KB");
		out.close();
		Cell c=sheet.getCell(2,2);
		String PathOfFolderToAttcheInMail=c.getContents();
		Cell d=sheet.getCell(3,2);
		String ReceipientsMailId=d.getContents();
		sendmailAttachment(PathOfFolderToAttcheInMail,"Mail With Attachment",ReceipientsMailId);
		return "Pass";
	} 
	catch (FileNotFoundException e) 
	{
		e.printStackTrace();
		return "Fail";
	} 
	catch (IOException e) 
	{
		e.printStackTrace();
		return "Fail";
	}
}

private static void addFolderToZip(File folder, ZipOutputStream zip, String baseName) throws IOException {
	File[] files = folder.listFiles();
	for (File file : files) 
	{
		if (file.isDirectory()) 
		{
			addFolderToZip(file, zip, baseName);
		} 
		else 
		{
			String name = file.getAbsolutePath().substring(baseName.length());
			ZipEntry zipEntry = new ZipEntry(name);
			zip.putNextEntry(zipEntry);
			IOUtils.copy(new FileInputStream(file), zip);
			zip.closeEntry();
		}
	}
}

public static Boolean sendmailAttachment(String args,String msg, String to)throws Exception 
{
Workbook workbook=Workbook.getWorkbook(new File("C:\\Documents and Settings\\pritup\\Desktop\\ZipAndMail.xls"));
final Sheet sheet = workbook.getSheet(0); 		
Cell e=sheet.getCell(4,2);
final String EmailIdOfSender=e.getContents();
Cell f=sheet.getCell(5,2);
final String PasswordOfSender=f.getContents();
String fileAttachment =args;
//Get system properties
//Properties props = System.getProperties();
Properties props = new Properties();
//Setup mail server
props.put("mail.smtp.host", "smtp.gmail.com");
props.put("mail.smtp.socketFactory.port", "465");
props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
props.put("mail.smtp.auth", "true");
props.put("mail.smtp.port", "465");

//Get session
Session session = Session.getDefaultInstance(props,
  new javax.mail.Authenticator() {
    protected PasswordAuthentication getPasswordAuthentication() 
   {
	   
    return new PasswordAuthentication(EmailIdOfSender,PasswordOfSender);
	  
}
   }
);
try 
{
//Define message
Message message = new MimeMessage(session);
Cell g=sheet.getCell(6,2);
String EmailId=g.getContents();
message.setFrom(new InternetAddress(EmailId));
message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
Cell h=sheet.getCell(7,2);
String MESSAGE=h.getContents();

message.setSubject(MESSAGE);
message.setText(msg);

//create the message part
MimeBodyPart messageBodyPart =new MimeBodyPart();

//fill message
Cell i=sheet.getCell(8,2);
String BODYMESSAGE=i.getContents();
messageBodyPart.setText(BODYMESSAGE);

Multipart multipart = new MimeMultipart();
multipart.addBodyPart(messageBodyPart);

//Part two is attachment
messageBodyPart = new MimeBodyPart();
DataSource source = new FileDataSource(fileAttachment);
messageBodyPart.setDataHandler(new DataHandler(source));
messageBodyPart.setFileName(fileAttachment);
multipart.addBodyPart(messageBodyPart);

//Put parts in message
message.setContent(multipart);

//Send the message
Transport.send( message );
System.out.println("Message sent");

} 

catch (MessagingException ex) 
{
ex.printStackTrace();
System.out.println("Exception in Send Mail");
return false;
}
return true;
}
	
}
