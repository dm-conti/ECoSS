package it.obiectivo.ecoss.controller;

import it.obiectivo.ecoss.domain.Credenzialiutente;
import it.obiectivo.ecoss.domain.Distributore;
import it.obiectivo.ecoss.domain.Gestore;
import it.obiectivo.ecoss.domain.Profilo;
import it.obiectivo.ecoss.domain.Utente;
import it.obiectivo.ecoss.service.AccessService;
import it.obiectivo.ecoss.service.CredenzialiutenteService;
import it.obiectivo.ecoss.service.DistributoreService;
import it.obiectivo.ecoss.service.GestoreService;
import it.obiectivo.ecoss.service.JavaMailService;
import it.obiectivo.ecoss.service.ProfiloService;
import it.obiectivo.ecoss.service.UtenteService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import it.obiectivo.ecoss.json.CustomGenericResponse;
import it.obiectivo.ecoss.json.CustomObjectResponse;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/utente")
public class UtenteController {
	protected static Logger logger = Logger.getLogger("controller");
	
	@Resource(name="utenteService")
	private UtenteService utenteService;
	
	@Resource(name="gestoreService")
	private GestoreService gestoreService;
	
	@Resource(name="distributoreService")
	private DistributoreService distributoreService;
	
	@Resource(name="accessService")
	private AccessService accessService;
	
	@Resource(name="credenzialiutenteService")
	private CredenzialiutenteService credenzialiService;
	
	@Resource(name="profiloService")
	private ProfiloService profiloService;
	
	@Resource(name="javaMailService")
	private JavaMailService jMailService;
	
	
	//INVOCO LA JSP New - Inserimento di un nuovo utente - 
    @RequestMapping(value = "/New", method = RequestMethod.GET)
    public ModelAndView getAdd(HttpServletRequest request, HttpSession session) {
    	logger.debug("Visualizzazione della pagina add");
    	String username = request.getRemoteUser();
    	ModelAndView modelAndView;
    	if(username == null){
    		modelAndView = new ModelAndView("redirect:/login");
    		return modelAndView;
    	}
    	else{	
    		
	        /* RECUPERO LE CREDENZIALI ASSECONDA DELLE QUALI VADO A 
			* VALORIZZARE I CAMPI DI RICERCA PER I Gestori e\o Subgestori*/
			Credenzialiutente credUte = credenzialiService.getByUserId(request.getRemoteUser());
			
			modelAndView = new ModelAndView("utente/New");
			Utente utente = new Utente();
			
			/* SE IL RUOLO DELL'Utente E' Admin, DEVO INVIARE ALLA View LA LISTA DEI Gestori
			* E LA LISTA DEI Subgestori*/
			if(credUte.getProfilo().getProfDescrizione().equalsIgnoreCase("Admin")){
				/*PREPARO LE DUE LISTE*/
				List<Gestore> listaGestori = gestoreService.getAll();
				List<Distributore> listaDistributori = distributoreService.getAll();
					
				modelAndView.addObject("listaGestori", listaGestori);
				modelAndView.addObject("listaDistributori", listaDistributori);
				session.setAttribute("listaGestori", listaGestori);
				session.setAttribute("listaDistributori", listaDistributori);
			}
			/* ALTRIMENTI SE L'Utente HA RUOLO DI Gestore, DEVO INVIARE ALLA View SOLO
			* LA LISTA DEI Distributori*/
			else if(credUte.getProfilo().getProfDescrizione().equalsIgnoreCase("Gestore")){
				/*PRIMA RECUPERO LA RAGIONE SOCIALE DEL GESTORE INVIO ALLA View PER VALORIZZARE 
				* IL RISPETTIVO CAMPO DI INPUT */
				List<Gestore> listaGestori = new ArrayList<Gestore>();
				listaGestori.add(credUte.getGestore());
				
				List<Distributore> listaDistributori = distributoreService.getAll();
					
				modelAndView.addObject("listaGestori", listaGestori);
				modelAndView.addObject("listaDistributori", listaDistributori);
				session.setAttribute("listaGestori", listaGestori);
				session.setAttribute("listaDistributori", listaDistributori);
			}
			else if(credUte.getProfilo().getProfDescrizione().equalsIgnoreCase("Subgestore")){ 
				/*	QUANDO L'Utente HA RUOLO DI Distributore NON DEVO VALORIZZARE LE LISTE 
				*  MA DIRETTAMENTE I CAMPI DELLE Ragioni Sociali*/
				List<Gestore> listaGestori = new ArrayList<Gestore>();
				List<Distributore> listaDistributori = new ArrayList<Distributore>();
				
				Distributore subGestore = credUte.getDistributore();
				listaGestori.add(subGestore.getGestore());
				listaDistributori.add(subGestore);
				
				modelAndView.addObject("listaGestori", listaGestori);
				modelAndView.addObject("listaDistributori", listaDistributori);
				session.setAttribute("listaGestori", listaGestori);
				session.setAttribute("listaDistributori", listaDistributori);
			} 
	        modelAndView.addObject("utenteAttribute", utente);
	        return modelAndView;
    	}
    }
    
    /* SALVO l'Utente CREATO */
    @RequestMapping(value = "/Add", method = RequestMethod.POST)
    public ModelAndView add( @Valid @ModelAttribute("utenteAttribute") Utente utente, BindingResult result, 
    		@RequestParam("userId") String userId, ModelAndView modelAndView, HttpSession session ) {
    	
    	logger.debug("DistributoreController: Salvataggio dell'utente "+userId);
    	
    	//TODO: Eliminare il codice commentato
    	/*
    	modelAndView.addObject("usernameEsistente", false);
    	List<Utente> listaUtente = utenteService.getAll();
    	for(int i=0; i<listaUtente.size(); i++){
    		Utente tmpUtente = listaUtente.get(i);
    		String tmpUserId = "";
    		if(tmpUtente.getCredenzialiutente() != null)
    			tmpUserId = tmpUtente.getCredenzialiutente().getUserId();
    		if(userId.equals(tmpUserId)){
    			modelAndView = new ModelAndView("utente/New");
    			modelAndView.addObject("usernameEsistente", true);
    			modelAndView.addObject("listaGestori", session.getAttribute("listaGestori"));
    			modelAndView.addObject("listaDistributori", session.getAttribute("listaDistributori"));
    				
    			return modelAndView;
    		}
    	}
    	*/
    	
    	/*CONTROLLE LA PRESENZA DI ERRORI DI VALIDAZIONE LATO Server*/
		if(result.hasErrors()){
			modelAndView = new ModelAndView("utente/New");
			modelAndView.addObject("utenteAttribute", utente);
        	return modelAndView;
		}
		
        /* CREO LE CREDENZIALI DI ACCESSO: SONO TEMPORANEE PERCHE' MANCA 
         * ANCORA UN DATO FONDAMENTALE, Utente */
		Profilo ruolo = profiloService.getByProfDescrizione("Utente");
		Credenzialiutente tmpCredenziali = credenzialiService.makeCredenziali(userId, ruolo);
		
		/* SE ENTRAMBE I CONTROLLI SONO True ALLORA INVIO L'Email DI CONFERMA.
        * ALTRIMENTI:*/
        /**
        * @todo Resta aperta la necessita di gestire eeventuali exception che si 
        * possono verificare quando memorizziamo l'utente. 
        */
        Boolean isAccessTrue = accessService.add(utente, tmpCredenziali);
		
        if(isAccessTrue){
        	modelAndView = new ModelAndView("utente/riepilogoUtente");
        	modelAndView.addObject("userId", userId);
        	modelAndView.addObject("utente", utente);
        	String subject = "Conferma registrazione Ecoss";
        	String body = "<html><body><h1>Registrazione Ecoss avvenuta correttamente!</h1>" +
        	"<br><br>" +
        	"<b>Credenziali d'accesso per il ruolo di:"+
        	tmpCredenziali.getProfilo().getProfDescrizione()+"</b>" +
        	"<br>" +
        	"<b>UserId:</b>" +
        	tmpCredenziali.getUserId() +
        	"<b>Password:</b>" +
        	tmpCredenziali.getPassword() +
        	"<br><br>" +
        	"Si consiglia di cambiare la password dopo aver effettuato il primo accesso</body></html>";
        	
        	try{
        		jMailService.sendMail("ecoss.javamail@gmail.com", utente.getUteEmail(), subject, body);
        	}
        	catch (Exception e) {
        		e.printStackTrace();
			}
        	return modelAndView;
        }
        modelAndView = new ModelAndView("utente/erroreUtente");
        return modelAndView;
    }
    
/* // Controllo se l'username esiste già
	modelAndView.addObject("usernameEsistente", false);
	List<Utente> listaUtente = utenteService.getAll();
	for(int i=0; i<listaUtente.size(); i++){
		Utente tmpUtente = listaUtente.get(i);
		String tmpUserId = tmpUtente.getCredenzialiutente().getUserId();
		if(userId.equals(tmpUserId)){
			modelAndView = new ModelAndView("utente/New");
			modelAndView.addObject("usernameEsistente", true);
			modelAndView.addObject("listaGestori", session.getAttribute("listaGestori"));
			modelAndView.addObject("listaDistributori", session.getAttribute("listaDistributori"));
				
			return modelAndView;
		}*/
    
    
	/*@RequestMapping(value = "/Add", method = RequestMethod.POST)
    public ModelAndView add( @Valid @ModelAttribute("utenteAttribute") Utente utente, 
    		BindingResult result,@RequestParam("userId") String userId, 
        	HttpServletRequest request, ModelAndView modelAndView, HttpSession session ) throws Exception{
		
		logger.debug("UtenteController: Salvataggio di un Utente");
			
		// Controllo se l'username esiste già
		modelAndView.addObject("usernameEsistente", false);
		List<Utente> listaUtente = utenteService.getAll();
		for(int i=0; i<listaUtente.size(); i++){
			Utente tmpUtente = listaUtente.get(i);
			String tmpUserId = tmpUtente.getCredenzialiutente().getUserId();
			if(userId.equals(tmpUserId)){
				modelAndView = new ModelAndView("utente/New");
				modelAndView.addObject("usernameEsistente", true);
				modelAndView.addObject("listaGestori", session.getAttribute("listaGestori"));
				modelAndView.addObject("listaDistributori", session.getAttribute("listaDistributori"));
					
				return modelAndView;
			}
		}
			
		CONTROLLE LA PRESENZA DI ERRORI DI VALIDAZIONE LATO Server
		if(result.hasErrors()){
			modelAndView = new ModelAndView("utente/New");
			modelAndView.addObject("utenteAttribute", utente);
		    return modelAndView;
		}
				
		 CREO LE CREDENZIALI DI ACCESSO: SONO TEMPORANEE PERCHE' MANCA 
	    * ANCORA UN DATO FONDAMENTALE, Utente 
		Profilo ruolo = profiloService.getByProfDescrizione("Utente");
		Credenzialiutente tmpCredenziali = credenzialiService.makeCredenziali(userId, ruolo);
			
	    Boolean isAccessTrue = accessService.add(utente, tmpCredenziali);
			
	    if(isAccessTrue){
	    	modelAndView = new ModelAndView("utente/riepilogoUtente");
	        modelAndView.addObject("utente", utente);
	        String subject = "Conferma registrazione Ecoss";
	        String body = "<html><body><h1>Registrazione Ecoss avvenuta correttamente!</h1>" +
	        "<br><br>" +
	        "<b>Credenziali d'accesso per il ruolo di:"+
	        tmpCredenziali.getProfilo().getProfDescrizione()+"</b>" +
	        "<br>" +
	        "<b>UserId:</b>" +
	        tmpCredenziali.getUserId() +
	        "<b>Password:</b>" +
	        tmpCredenziali.getPassword() +
	        "<br><br>" +
	        "Si consiglia di cambiare la password dopo aver effettuato il primo accesso</body></html>";
	        
	        try{
	        	jMailService.sendMail("ecoss.javamail@gmail.com", utente.getUteEmail(), subject, body);
	        }
	        catch (Exception e) {
	        	e.printStackTrace();
			}
	        return modelAndView;
	    }
	    modelAndView = new ModelAndView("utente/erroreUtente");
	    return modelAndView;
	}
	*/
    
	 @RequestMapping(value = "/Details", method = RequestMethod.GET)
	    public ModelAndView getEdit(@RequestParam(value="idUtente", required=true) Integer idUtente) {
		 ModelAndView modelAndView = new ModelAndView("utente/detailsPage");
		 Utente utente = utenteService.get(idUtente);
	     modelAndView.addObject("utente", utente);
	     return modelAndView;
	 }
	    
	//RESTITUISCO una lista di Utenti senza Contratto
	@RequestMapping(value = "/Listed", method = RequestMethod.GET)
	public ModelAndView getAll( HttpServletRequest request, HttpSession session) throws Exception{
		ModelAndView modelAndView = new ModelAndView("utente/Listed");
        List<Utente> listaUtenti = utenteService.getAll();
        	
        String jsonText = new ObjectMapper().writeValueAsString(listaUtenti);
        JSONArray json = (JSONArray) new JSONParser().parse(jsonText);
        Iterator<Utente> iterator = listaUtenti.iterator();
        int i = 0;
        while(iterator.hasNext()){
        	Utente uteTmp = iterator.next();
        	JSONObject tmpObj = (JSONObject) json.get(i);
        	if(uteTmp.getIstanzautentecpeList().size() != 0){
        		int size = uteTmp.getIstanzautentecpeList().size();
        		tmpObj.put("contratti", size);
        		i=++i;
        		continue;
        	}
        	tmpObj.put("contratti", 0);
        	i=++i;
        }
        modelAndView.addObject("listaUtenti", json);
        	
        return modelAndView;
	}
	
	//RESTITUISCO una lista di Utenti senza Contratto
	@RequestMapping(value = "/test_i18n", method = RequestMethod.GET)
	public ModelAndView getTestI18N( HttpServletRequest request, HttpSession session) throws Exception{
		ModelAndView modelAndView = new ModelAndView("utente/test_i18n");
        List<Utente> listaUtenti = utenteService.getAll();
        	
        String jsonText = new ObjectMapper().writeValueAsString(listaUtenti);
        JSONArray json = (JSONArray) new JSONParser().parse(jsonText);
        modelAndView.addObject("listaUtenti", json);
        	
        return modelAndView;
	}
		
	//RESTITUISCO una lista di Utenti con Contratto
	@RequestMapping(value = "/withContract", method = RequestMethod.GET)
    public ModelAndView getWithContract( HttpServletRequest request, HttpSession session) throws Exception{
		logger.debug("Visualizzazione della pagina add");
			
        ModelAndView modelAndView = new ModelAndView("utente/Listed");
        List<Utente> listaUtenti = utenteService.getUteWithContract();
        	
        String jsonText = new ObjectMapper().writeValueAsString(listaUtenti);
        JSONArray json = (JSONArray) new JSONParser().parse(jsonText);
        	
        modelAndView.addObject("listaUtenti", json);
        
        return modelAndView;
	}
		
		//RESTITUISCO una lista di Utenti senza Contratto
		@RequestMapping(value = "/withoutContract", method = RequestMethod.GET)
        public ModelAndView getWithoContract( HttpServletRequest request, HttpSession session) throws Exception{
			logger.debug("Visualizzazione della pagina add");
			
        	ModelAndView modelAndView = new ModelAndView("utente/Listed");
        	List<Utente> listaUtenti = utenteService.getUteWithoutContract();
        	
        	String jsonText = new ObjectMapper().writeValueAsString(listaUtenti);
        	JSONArray json = (JSONArray) new JSONParser().parse(jsonText);
        	
        	modelAndView.addObject("listaUtenti", json);
        	
        	return modelAndView;
        }
        		
        /* ------------------------------------------------------------ *
         * ---------------- INIZIO RICHIESTE AJAX --------------------- *
         * ------------------------------------------------------------ */
        
        @RequestMapping(value = "/ajaxListed", method = RequestMethod.POST)
        public @ResponseBody  CustomObjectResponse getAll() {
            	logger.debug("UtenteController: - Ricevuta richiesta Listed Utenti");

            	List<Utente> utenti = utenteService.getAll();
            	
            	CustomObjectResponse response = new CustomObjectResponse();
            	response.setRows(utenti);

            	// Assegna il numro totale di record trovati. Tale valore e' usato per il paging
            	response.setRecords( String.valueOf(utenti.size()) );
            	
            	response.setPage( "1" );
            	response.setTotal( "10" );
            	
            	logger.debug("Prima del return: response = "+response.toString());
            	return response;
        }
        
        @RequestMapping(value = "/ajaxEdit", method = RequestMethod.POST)
        public @ResponseBody CustomGenericResponse ajaxEdit( @ModelAttribute("utenteAttribute") Utente utente, Model model, HttpServletRequest request){
        	
        	String username = request.getRemoteUser();
        	if(username != null){
	        	logger.debug("Ricevuta richiesta di modifica Utente");
	        	
	        	/* Call service to edit */
	        	Boolean success = utenteService.edit(utente);
	        	
	        	// Check if successful
	        	if ( success == true ) {
	        		// Success. Return a custom response
	        		CustomGenericResponse response = new CustomGenericResponse();
	    			response.setSuccess(true);
	    			response.setMessage("Action successful!");
	        		return response;
	        		
	        	} else {
	        		// A failure. Return a custom response as well
	        		CustomGenericResponse response = new CustomGenericResponse();
	    			response.setSuccess(false);
	    			response.setMessage("Action failure!");
	        		return response;
	        	}
        	}
        	else{
	        	// A failure. Return a custom response as well
	    		CustomGenericResponse response = new CustomGenericResponse();
				response.setSuccess(false);
				response.setMessage("Sessione scaduta!");
	    		return response;
        	}
    	}
        
        @RequestMapping(value = "/ajaxEmail", method = RequestMethod.POST)
        public @ResponseBody CustomGenericResponse ajaxEmail(@RequestParam("idUtenteEmail") String idUtenteEmail ){
        	
        	logger.debug("Ricevuta richiesta invio email con password");
        	        	
        	Utente tmpUtente = utenteService.get(Integer.parseInt(idUtenteEmail));
        	
        	Credenzialiutente tmpCredenziali = tmpUtente.getCredenzialiutente();
        	
        	String subject = "Conferma registrazione Ecoss";
    		String body = "Registrazione Ecoss avvenuta correttamente!" +
    				"<br><br>" +
    				"<b>Credenziali d'accesso</b>" +
    				"<br>" +
    				"<b>UserId:</b>" +
    				tmpCredenziali.getUserId() +
    				"<br><b>Password:</b>" +
    				tmpCredenziali.getPassword() +
    				"<br><br>" +
    				"Si consiglia di cambiare la password dopo aver effettuato il primo accesso";
    		
    		CustomGenericResponse response = new CustomGenericResponse();
    		
    		try {
				jMailService.sendMail("ecoss.javamail@gmail.com", tmpUtente.getUteEmail(), subject, body);
				// Success. Return a custom response
	    		response.setSuccess(true);
	    		response.setMessage("Email inviata!");
	    		return response;
			} catch (Exception e) {
				e.printStackTrace();
				// Success. Return a custom response
	    		response.setSuccess(false);
	    		response.setMessage("Errore invio email!");
	    		return response;
			}
        }
        
        @RequestMapping(value = "/ajaxDelete", method = RequestMethod.POST)
        public @ResponseBody CustomGenericResponse ajaxDelete(
        		@RequestParam("arrayOfIds") String ids, HttpServletRequest request ){
        	
        	String username = request.getRemoteUser();
    		if(username != null){
	        	logger.debug("Ricevuta richiesta ajaxDelete");
	        	
	        	List<String> listIds = Arrays.asList(ids.split(","));
	        	Iterator<String> itr = listIds.iterator();
	        	
	        	try{
	        		// ELIMINO LE CREDENZIALI ASSOCIATE
	            	while(itr.hasNext()){
	            		/* RECUPERO L'Utente */
	            		Utente tmpUtente = utenteService.get(Integer.parseInt(itr.next()));
	            		/* ELIMINO L'Accesso (Utente + Credenzialiutente) */
	            		accessService.delete(tmpUtente);
	            	}
	            	CustomGenericResponse response = new CustomGenericResponse();
	        		response.setSuccess(true);
	        		response.setMessage("Action successful!");
	        		// Success. Return a custom response
	        		return response;
	        	}catch (Exception e) {
	        		// A failure. Return a custom response as well
	        		CustomGenericResponse response = new CustomGenericResponse();
	           	 	response.setSuccess(false);
	           	 	response.setMessage("Action failure!");
	           	 	return response;
	        	}
    		}
    		else{
    			// A failure. Return a custom response as well
        		CustomGenericResponse response = new CustomGenericResponse();
           	 	response.setSuccess(false);
           	 	response.setMessage("Sessione scaduta!");
           	 	return response;
    		}
     }   
}