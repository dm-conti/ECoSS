package it.obiectivo.ecoss.controller;

import it.obiectivo.ecoss.domain.Credenzialiutente;
import it.obiectivo.ecoss.service.CredenzialiutenteService;

import javax.annotation.Resource;
import org.apache.log4j.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/credenzialiutente")
public class CredenzialiutenteController {
	protected static Logger logger = Logger.getLogger("controller");
	
	@Resource(name="credenzialiutenteService")
	private CredenzialiutenteService credenzialiService;
	
	/* ------------------------------------------------------------ *
     * ---------------- INIZIO RICHIESTE AJAX --------------------- *
     * ------------------------------------------------------------ */
	/**/
    
    @RequestMapping(value = "/userIdIsExisted", method = RequestMethod.GET)
    public @ResponseBody  Boolean userIdIsExited(@RequestParam("userId") String userId) {
        	logger.debug("CredenzialiutenteController: - ");
        	
        	if(credenzialiService.getByUserId(userId) instanceof Credenzialiutente)
        		return false;
        	return true;
    }
}