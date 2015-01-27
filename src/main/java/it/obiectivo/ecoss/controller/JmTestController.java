package it.obiectivo.ecoss.controller;

import it.obiectivo.ecoss.service.JavaMailService;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/javamail")
public class JmTestController {
	protected static Logger logger = Logger.getLogger("javaMail Controller");
	
	@Resource(name="javaMailService")
	private JavaMailService jMailService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
    public String showSendPage(Model model) {
    	logger.debug("MainController: Wellcome to Send Mail Index");
    	return "/javamail/send";
	}
	
	@RequestMapping(value = "/send", method = RequestMethod.GET)
    public String sendEmail(Model model) throws Exception {
    	logger.debug("Sto inviando l'email");
    	
        String messageBody = "<html><body><h1>Titolo con TAG H1</h1>" +
        					 "<br /><br /></body></html>" +
        					 "Senza TAG";
        String subject = "Test invio email con HTML";
        jMailService.sendMail("domenico_comnti@alice.it", "ecoss.javamail@gmail.com", subject, messageBody);
    	
    	return "/javamail/sended";
	}
}