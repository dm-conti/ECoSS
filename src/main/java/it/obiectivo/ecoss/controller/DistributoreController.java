package it.obiectivo.ecoss.controller;

import it.obiectivo.ecoss.domain.Credenzialiutente;
import it.obiectivo.ecoss.domain.Distributore;
import it.obiectivo.ecoss.domain.Gestore;
import it.obiectivo.ecoss.domain.Profilo;
import it.obiectivo.ecoss.service.AccessService;
import it.obiectivo.ecoss.service.CredenzialiutenteService;
import it.obiectivo.ecoss.service.DistributoreService;
import it.obiectivo.ecoss.service.GestoreService;
import it.obiectivo.ecoss.service.JavaMailService;
import it.obiectivo.ecoss.service.ProfiloService;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
@RequestMapping("/distributore")
public class DistributoreController {
	protected static Logger logger = Logger.getLogger("Distributorecontroller");
	
	@Resource(name="credenzialiutenteService")
	private CredenzialiutenteService credUteService;
		
	@Resource(name="gestoreService")
	private GestoreService gestoreService;
	
	@Resource(name="distributoreService")
	private DistributoreService distributoreService;
	
	@Resource(name="credenzialiutenteService")
	private CredenzialiutenteService credenzialiService;
	
	@Resource(name="profiloService")
	private ProfiloService profiloService;
	
	@Resource(name="accessService")
	private AccessService accessService;
	
	@Resource(name="javaMailService")
	private JavaMailService jMailService;
	
	@RequestMapping(value = "/Listed", method = RequestMethod.GET)
    public ModelAndView getListed(Model model) throws JsonGenerationException, JsonMappingException, IOException, ParseException {
    	logger.debug("Ricevuta richiesta per la pagina Listed");
    	
    	ModelAndView modelAndView = new ModelAndView("distributore/Listed");
    	
    	List<Distributore> listaDistributori = distributoreService.getAll();
    	
    	String jsonText = new ObjectMapper().writeValueAsString(listaDistributori);
        JSONArray json = (JSONArray) new JSONParser().parse(jsonText);
    	
        modelAndView.addObject("listaDistributori", json);
    
    	return modelAndView;
	}
	
	@RequestMapping(value = "/Details", method = RequestMethod.GET)
    public ModelAndView getEdit(@RequestParam(value="idDistributore", required=true) Integer idDistributore) throws Exception{
    	ModelAndView modelAndView = new ModelAndView("distributore/detailsPage");
    	Distributore distributore = distributoreService.get(idDistributore);
    	
    	modelAndView.addObject("distributore", distributore);
    	
    	return modelAndView;
    }
	
	//INVOCO LA JSP New - Inserimento di un nuovo Distributore - 
    @RequestMapping(value = "/New", method = RequestMethod.GET)
    public String getAdd(HttpServletRequest request, Model model) {
    	
    	logger.debug("Visualizzazione della pagina distributore/New");
    	String username = request.getRemoteUser();
    	if(username == null)
    		return "redirect:/login";
    	else{	
	        /* RECUPERO LE CREDENZIALI ASSECONDA DELLE QUALI VADO A 
			* VALORIZZARE I CAMPI DI RICERCA PER I Gestori e\o Subgestori*/
			Credenzialiutente credUte = credUteService.getByUserId(request.getRemoteUser());
			/* SE IL RUOLO DELL'Utente E' Admin, DEVO INVIARE ALLA View LA LISTA DEI Gestori
			* E LA LISTA DEI Subgestori*/
			if(credUte.getProfilo().getProfDescrizione().equalsIgnoreCase("Admin")){
				/*PREPARO LE DUE LISTE*/
				List<Gestore> listaGestori = gestoreService.getAll();
				model.addAttribute("listaGestori", listaGestori);
			}
			/* ALTRIMENTI SE L'Utente HA RUOLO DI Gestore, DEVO INVIARE ALLA View SOLO
			* LA LISTA DEI Distributori*/
			else if(credUte.getProfilo().getProfDescrizione().equalsIgnoreCase("Gestore")){
				/*PRIMA RECUPERO LA RAGIONE SOCIALE DEL GESTORE INVIO ALLA View PER VALORIZZARE 
				* IL RISPETTIVO CAMPO DI INPUT */
				model.addAttribute("gestRagSociale", request.getRemoteUser());
			} 
	        model.addAttribute("distributoreAttribute", new Distributore());
	        return "distributore/New";
    	}
    }
    
    /* SALVO il Distributore CREATO */
    @RequestMapping(value = "/Add", method = RequestMethod.POST)
    public ModelAndView add( @Valid @ModelAttribute("distributoreAttribute") Distributore distributore, 
    		BindingResult result,@RequestParam("userId") String userId,
        	HttpServletRequest request, ModelAndView modelAndView ) throws Exception{
    	
    	logger.debug("DistributoreController: Salvataggio del Distributore");
    	
    	/*CONTROLLE LA PRESENZA DI ERRORI DI VALIDAZIONE LATO Server*/
		if(result.hasErrors()){
			modelAndView = new ModelAndView("distributore/New");
			modelAndView.addObject("distributoreAttribute", distributore);
        	return modelAndView;
		}
        	
        /* CREO LE CREDENZIALI DI ACCESSO: SONO TEMPORANEE PERCHE' MANCA 
         * ANCORA UN DATO FONDAMENTALE, Distributore*/
		Profilo ruolo = profiloService.getByProfDescrizione("Subgestore");
		Credenzialiutente tmpCredenziali = credenzialiService.makeCredenziali(userId, ruolo);
		
		/* SE ENTRAMBE I CONTROLLI SONO True ALLORA INVIO L'Email DI CONFERMA.
        * ALTRIMENTI:*/
        /**
        * @todo Resta aperta la necessita di gestire eeventuali exception che si 
        * possono verificare quando memorizziamo l'utente. 
        */
        Boolean isAccessTrue = accessService.add(distributore, tmpCredenziali);
		
        if(isAccessTrue){
        	modelAndView = new ModelAndView("distributore/riepilogoDistributore");
        	modelAndView.addObject("distributore", distributore);
        	String subject = "Conferma registrazione Ecoss";
        	String body = "<html><body><h1>Registrazione Ecoss avvenuta correttamente!</h1>" +
        	"<br><br>" +
        	"<b>Credenziali d'accesso per il ruolo di:"+
        	tmpCredenziali.getProfilo().getProfDescrizione()+"</b>" +
        	"<br>" +
        	"<b>UserId:</b>" +
        	tmpCredenziali.getUserId() +
        	"<br>" +
        	"<b>Password:</b>" +
        	tmpCredenziali.getPassword() +
        	"<br><br>" +
        	"Si consiglia di cambiare la password dopo aver effettuato il primo accesso</body></html>";
        	
        	try{
        		jMailService.sendMail("ecoss.javamail@gmail.com", distributore.getDistEmail(), subject, body);
        	}
        	catch (Exception e) {
        		e.printStackTrace();
			}
        	return modelAndView;
        }
        modelAndView = new ModelAndView("distributore/erroreDistributore");
        return modelAndView;
    }
    
    /* ------------------------------------------------------------ *
    * ---------------- INIZIO RICHIESTE AJAX --------------------- *
    * ------------------------------------------------------------ */
    
    /** 
     * @todo:
     * POSSIBILI ECCEZZIONI LANCIATE (DA GESTIRE):
     * JsonGenerationException
     * JsonMappingException
     * IOException*/
    @RequestMapping(value = "/ajaxGetUsers", method = RequestMethod.POST)
    public @ResponseBody  JSONArray getListaUtenti(@RequestParam("idDistributore") String idDistributore) throws Exception{
    	logger.debug("DistributoreController: - getListaUtenti");

        Distributore distributore = distributoreService.get(Integer.parseInt(idDistributore));
        
        String jsonText = new ObjectMapper().writeValueAsString(distributore.getUtenteList());
        JSONArray json = (JSONArray) new JSONParser().parse(jsonText);
        
        return json;
    }
        
    @RequestMapping(value = "/Listed", method = RequestMethod.POST)
    public @ResponseBody  CustomObjectResponse getAll() {
    	logger.debug("DistributoreController: - Ricevuta richiesta Listed Distributori");

        List<Distributore> distributori = distributoreService.getAll();
        
        CustomObjectResponse response = new CustomObjectResponse();
        response.setRows((List<Distributore>) distributori);

        // Assegna il numro totale di record trovati. Tale valore e' usato per il paging
        response.setRecords( String.valueOf(distributori.size()) );
            	
        response.setPage( "1" );
        response.setTotal( "10" );
            	
        logger.debug("Prima del return: response = "+response.toString());
        return response;
    }
        
        @RequestMapping(value = "/ajaxEdit", method = RequestMethod.POST)
        public @ResponseBody CustomGenericResponse ajaxEdit( @ModelAttribute("distributoreAttribute") Distributore distributore, Model model, HttpServletRequest request){
        	
        	String username = request.getRemoteUser();
    		if(username != null){
	        	logger.debug("DistributoreController: - Ricevuta richiesta di modifica Distributore");
	        	
	        	/* Call service to edit */
	        	Boolean success = distributoreService.edit(distributore);
	        	
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
    		else {
        		// A failure. Return a custom response as well
        		CustomGenericResponse response = new CustomGenericResponse();
    			response.setSuccess(false);
    			response.setMessage("Sessione scaduta!");
        		return response;
        	}
    	}
        
        @RequestMapping(value = "/ajaxDelete", method = RequestMethod.POST)
        public @ResponseBody CustomGenericResponse ajaxDelete(
        		@RequestParam("arrayOfIds") String ids, HttpServletRequest request ){
        	
        	String username = request.getRemoteUser();
    		if(username != null){
	        	logger.debug("DistributoreController: - Ricevuta richiesta di cancellazione Distributore");
	        	
	        	List<String> listIds = Arrays.asList(ids.split(","));
	        	Iterator<String> itr = listIds.iterator();
	        	
	        	try{
	        		// ELIMINO LE CREDENZIALI ASSOCIATE
	            	while(itr.hasNext()){
	            		/* RECUPERO IL Distributore */
	            		Distributore tmpDistributore = distributoreService.get(Integer.parseInt(itr.next()));
	            		/* ELIMINO L'Accesso (Distributore + Credenzialiutente) */
	            		accessService.delete(tmpDistributore);
	            	}
	        		CustomGenericResponse response = new CustomGenericResponse();
	        		response.setSuccess(true);
	        		response.setMessage("Action successful!");
	        		// Success. Return a custom response
	        		return response;
	        	}
	        	catch (Exception e) {
	        		// A failure. Return a custom response as well
	           	 	CustomGenericResponse response = new CustomGenericResponse();
	           	 	response.setSuccess(false);
	           	 	response.setMessage("Action failure!");
	           	 	return response;
				}
    		}
    		else {
        		// A failure. Return a custom response as well
        		CustomGenericResponse response = new CustomGenericResponse();
    			response.setSuccess(false);
    			response.setMessage("Sessione scaduta!");
        		return response;
        	}
    		
     }
}