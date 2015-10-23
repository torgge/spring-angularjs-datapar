package py.com.datapar.testes;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import org.apache.commons.mail.EmailException;

public class TesteEmail2 {

	public static void main(String[] args) throws EmailException, MessagingException  {

/*		Email email = new SimpleEmail();
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("integraldominio@gmail.com", "www010203..."));
		email.setSSL(true);
		email.setFrom("integraldominio@gmail.com","Lyndon Tavares");
		email.setSubject("TestMail");
		email.setMsg("This is a test mail ... :-)");
		email.addTo("integraldominio@gmail.com");
		email.send();
*/		
		
		
		String host = "smtp.gmail.com";
		String username = "integraldominio@gmail.com";
		String password = "www010203...";
		Properties props = new Properties();
		props.put("mail.smtps.auth", "true");
		Session session = Session.getInstance(props);
		
		MimeMessage msg = new MimeMessage(session);
		
		Transport t = session.getTransport("smtps");
		
		try {
			
		t.connect(host, username, password);
		
		t.sendMessage(msg, msg.getAllRecipients());
		
		} finally {
			
		t.close();
		
		}		
		

		
		/*
		 
    	  // Create the attachment
		  EmailAttachment attachment = new EmailAttachment();
		  attachment.setPath("mypictures/john.jpg");
		  attachment.setDisposition(EmailAttachment.ATTACHMENT);
		  attachment.setDescription("Picture of John");
		  attachment.setName("John");

		  // Create the email message
		  MultiPartEmail email = new MultiPartEmail();
		  email.setHostName("mail.myserver.com");
		  email.addTo("jdoe@somewhere.org", "John Doe");
		  email.setFrom("me@apache.org", "Me");
		  email.setSubject("The picture");
		  email.setMsg("Here is the picture you wanted");

		  // add the attachment
		  email.attach(attachment);

		  // send the email
		  email.send();

		  
		  
		  
		  
		  // Create the email message
		  HtmlEmail email = new HtmlEmail();
		  email.setHostName("mail.myserver.com");
		  email.addTo("jdoe@somewhere.org", "John Doe");
		  email.setFrom("me@apache.org", "Me");
		  email.setSubject("Test email with inline image");
		  
		  // embed the image and get the content id
		  URL url = new URL("http://www.apache.org/images/asf_logo_wide.gif");
		  String cid = email.embed(url, "Apache logo");
		  
		  // set the html message
		  email.setHtmlMsg("<html>The apache logo - <img src=\"cid:"+cid+"\"></html>");
		
		  // set the alternative message
		  email.setTextMsg("Your email client does not support HTML messages");
		
		  // send the email
		  email.send();
		  
		  */
		
		
		
	}

}
