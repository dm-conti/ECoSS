package it.obiectivo.ecoss.service;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service("javaMailService")
public class JavaMailService implements MessageListener{
	
	@Autowired
	private SimpleMailMessage alertMailMessage;
	
	@Autowired
	private JavaMailSender jMailSender;
	
	public void onMessage(Message message){
		
	}
	
	public void sendMail(String from, String to, String subject, String body ) throws Exception{
		MimeMessage message = jMailSender.createMimeMessage();
		message.addHeader("Return-Receipt-To", "ecoss.javamail.gmail.com");
		
		/*Il flag true e' usato per indicare la necessita' di un messaggio multipart*/
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(body, true);
		
		//helper.addAttachment("CoolImage.jpg", file);
		
		 try {
			 jMailSender.send(message);
		 }
		 catch(MailException exception){
			 //log the message and go on…
			 System.err.println(exception.getMessage());
		 }	
	}
	
	public void sendMail(String nContratto, String from, String to, String subject, String body ) throws Exception{
		
		MimeMessage message = jMailSender.createMimeMessage();
		message.addHeader("Return-Receipt-To", "ecoss.javamail.gmail.com");
		message.addHeader("nContratto", nContratto);
		
		/*Il flag true e' usato per indicare la necessita' di un messaggio multipart*/
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(body, true);
		
		//helper.addAttachment("CoolImage.jpg", file);
		
		 try {
			 jMailSender.send(message);
		 }
		 catch(MailException exception){
			 //log the message and go on…
			 System.err.println(exception.getMessage());
		 }	
	}
	
	public void sendAlertMail(String alert) {
		
		SimpleMailMessage mailMessage = new SimpleMailMessage(alertMailMessage);
		mailMessage.setText(alert);
		jMailSender.send(mailMessage);
	}
}