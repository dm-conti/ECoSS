package it.obiectivo.ecoss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("mailService")
public class MailService {
	
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private SimpleMailMessage alertMailMessage;
	
	@Autowired
	private JavaMailSender jMailSender;
	
	public void sendMail(String from, String to, String subject, String body) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		 
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		
		 try {
			 mailSender.send(message);
		 }
		 catch(MailException exception){
			 //log the message and go on…
			 System.err.println(exception.getMessage());
		 }	
	}
	
	public void sendAlertMail(String alert) {
		
		SimpleMailMessage mailMessage = new SimpleMailMessage(alertMailMessage);
		mailMessage.setText(alert);
		mailSender.send(mailMessage);
		
	}
	
	
 
	/*public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
		this.simpleMailMessage = simpleMailMessage;
	}
 
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
 
	public void sendMail(String dear, String content) {
 
	   MimeMessage message = mailSender.createMimeMessage();
 
	   try{
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
 
		helper.setFrom(simpleMailMessage.getFrom());
		helper.setTo(simpleMailMessage.getTo());
		helper.setSubject(simpleMailMessage.getSubject());
		helper.setText(String.format(
			simpleMailMessage.getText(), dear, content));
 
		FileSystemResource file = new FileSystemResource("C:\\log.txt");
		helper.addAttachment(file.getFilename(), file);
 
	     }catch (MessagingException e) {
		throw new MailParseException(e);
	     }
	     mailSender.send(message);
         }
	*/
}
