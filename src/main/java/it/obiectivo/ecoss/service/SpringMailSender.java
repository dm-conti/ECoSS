package it.obiectivo.ecoss.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service("sprMailSender")
public class SpringMailSender
{
        private JavaMailSender  mailSender;
        private MimeMessage        message;
        private String         fromAddress;
        private String         toAddress;

        public SpringMailSender(){
        }

        public void sendMail(String messageBody, String subject)
        {
                try
                {
                        message = mailSender.createMimeMessage();
                        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
                        helper.setFrom(fromAddress);
                        helper.setSubject(subject);
                        helper.setTo(toAddress);
                        helper.setText(messageBody);
                }
                catch (MessagingException me)
                {
                        me.printStackTrace();
                }

                try
                {
                        mailSender.send(message);
                }
                catch (MailAuthenticationException ex)
                {
                        ex.printStackTrace();
                }
                catch (MailException me)
                {
                        me.printStackTrace();
                }
        }

        public JavaMailSender getMailSender()
        {
                return mailSender;
        }

        public void setMailSender(JavaMailSender mailSender)
        {
                this.mailSender = mailSender;
        }

        public String getFromAddress()
        {
                return fromAddress;
        }

        public void setFromAddress(String fromAddress)
        {
                this.fromAddress = fromAddress;
        }

        public String getToAddress()
        {
                return toAddress;
        }

        public void setToAddress(String toAddress)
        {
                this.toAddress = toAddress;
        }
}