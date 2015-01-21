package util;

import java.io.File;
import java.util.Properties;

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

public class testMail {
	
public static void main(String[] args)throws Exception 
	{
	String arg=null;
	String msg=null;
	String to=null;
	
	
	Workbook workbook=Workbook.getWorkbook(new File("C:\\Users\\annavik.QUALITREEINC\\Desktop\\data nad keyword driven\\Module21_Selenium_Testng_Hybrid\\src\\util\\ZipAndMail.xls"));
	final Sheet sheet = workbook.getSheet(0); 		
	Cell e=sheet.getCell(4,2);
	final String EmailIdOfSender=e.getContents();
	Cell f=sheet.getCell(5,2);
	final String PasswordOfSender=f.getContents();
	String fileAttachment =arg;
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
//	messageBodyPart = new MimeBodyPart();
//	DataSource source = new FileDataSource(fileAttachment);
//	messageBodyPart.setDataHandler(new DataHandler(source));
//	messageBodyPart.setFileName(fileAttachment);
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
	//return false;
	}
	//return true;
	}
	}
