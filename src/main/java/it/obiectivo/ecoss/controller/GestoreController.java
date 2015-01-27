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
@RequestMapping("/gestore")
public class GestoreController {
	protected static Logger logger = Logger.getLogger("GestoreController");
		
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
    	
    	ModelAndView modelAndView = new ModelAndView("gestore/Listed");
    	
    	List<Gestore> listaGestori = gestoreService.getAll();
    	
    	String jsonText = new ObjectMapper().writeValueAsString(listaGestori);
        JSONArray json = (JSONArray) new JSONParser().parse(jsonText);
    	
        modelAndView.addObject("listaGestori", json);
    
    	return modelAndView;
	}
	
	@RequestMapping(value = "/Details", method = RequestMethod.GET)
	public ModelAndView getEdit(@RequestParam(value="idGestore", required=true) Integer idGestore) throws Exception{
		ModelAndView modelAndView = new ModelAndView("gestore/detailsPage");
	    Gestore gestore = gestoreService.get(idGestore);
	    	
	    modelAndView.addObject("gestore", gestore);
	    	
	    return modelAndView;
	}
	
	
	//INVOCO LA JSP New - Inserimento di un nuovo Gestore - 
    @RequestMapping(value = "/New", method = RequestMethod.GET)
    public String getAdd(HttpServletRequest request, Model model) {
    	
    	logger.debug("Visualizzazione della pagina gestore/New");
    	String username = request.getRemoteUser();
    	if(username == null)
    		return "redirect:/login";
    	else{	
	        /* SOLO L'Admin PUO' ACCEDERE ALLA CREAZIONE DI UN Gestore 
	         * PER QUESTO MOTIVO L'if SEGUENTE E' SOLO UN CONTROLLO CHE
	         * IN CONDIZIONI NORMALI DOVREBBE ESSERE SEMBRE SUPERATO */
	    	
	    	/* RECUPERO LE CREDENZIALI ASSECONDA DELLE QUALI VADO A 
			* VALORIZZARE I CAMPI DI RICERCA PER I Gestori e\o Subgestori*/
			Credenzialiutente credUte = credenzialiService.getByUserId(request.getRemoteUser());
			/* SE IL RUOLO DELL'Utente E' Admin, DEVO INVIARE ALLA View LA LISTA DEI Gestori
			* E LA LISTA DEI Subgestori*/
			if(credUte.getProfilo().getProfDescrizione().equalsIgnoreCase("Admin")){
				/*PREPARO LA LISTA*/
				List<Distributore> listaDistributori = distributoreService.getAll();
				model.addAttribute("listaDistributori", listaDistributori);
			}
			/*AGGIUNGERE PAGINA NON HAI I PERMESSI PER ESEGUIRE QUESTA FUNZIONE*/
			
			 
	        model.addAttribute("gestoreAttribute", new Gestore());
	        return "gestore/New";
    	}
    }
    
    /* SALVO il Gestore CREATO */
    @RequestMapping(value = "/Add", method = RequestMethod.POST)
    public ModelAndView add( @Valid @ModelAttribute("gestoreAttribute") Gestore gestore, 
    		BindingResult result,@RequestParam("userId") String userId,
        	HttpServletRequest request, ModelAndView modelAndView ) throws Exception{
    	
    	logger.debug("GestoreController: Salvataggio del Gestore");
        
    	/*CONTROLLE LA PRESENZA DI ERRORI DI VALIDAZIONE LATO Server*/
		if(result.hasErrors()){
			modelAndView = new ModelAndView("gestore/New");
        	modelAndView.addObject("gestoreAttribute", gestore);
        	return modelAndView;
		}
        	
        /* CREO LE CREDENZIALI DI ACCESSO: SONO TEMPORANEE PERCHE' MANCA 
         * ANCORA UN DATO FONDAMENTALE, Gestore*/
		Profilo ruolo = profiloService.getByProfDescrizione("Gestore");
		Credenzialiutente tmpCredenziali = credenzialiService.makeCredenziali(userId, ruolo);
        	        	
		/* SE ENTRAMBE I CONTROLLI SONO True ALLORA INVIO L'Email DI CONFERMA.
        * ALTRIMENTI:*/
        /**
        * @todo Resta aperta la necessita di gestire eeventuali exception che si 
        * possono verificare quando memorizziamo l'utente. 
        */
        Boolean isAccessTrue = accessService.add(gestore, tmpCredenziali);
		
        if(isAccessTrue){
        	modelAndView = new ModelAndView("gestore/riepilogoGestore");
        	modelAndView.addObject("gestore", gestore);
        	modelAndView.addObject("userId", userId);
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
        		jMailService.sendMail("ecoss.javamail@gmail.com", gestore.getGestEmail(), subject, body);
        	}
        	catch (Exception e) {
        		e.printStackTrace();
			}
        	return modelAndView;
        }
        modelAndView = new ModelAndView("gestore/erroreGestore");
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
    @RequestMapping(value = "/ajaxGetProviders", method = RequestMethod.POST)
    public @ResponseBody  JSONArray getListaDistributori(@RequestParam("idGestore") String idGestore ) throws Exception{
    	logger.debug("GestoreController: - getListaDistributori");

        Gestore gestore = gestoreService.get(Integer.parseInt(idGestore));
        
        String jsonText = new ObjectMapper().writeValueAsString(gestore.getDistributoreList());
        JSONArray json = (JSONArray) new JSONParser().parse(jsonText);
        
        return json;
    }
    
    @RequestMapping(value = "/ajaxGetUsers", method = RequestMethod.POST)
    public @ResponseBody  JSONArray getListaUtenti(@RequestParam("idGestore") String idGestore ) throws Exception{
    	logger.debug("GestoreController: - getListaUtenti");

        Gestore gestore = gestoreService.get(Integer.parseInt(idGestore));
        
        String jsonText = new ObjectMapper().writeValueAsString(gestore.getUtenteList());
        JSONArray json = (JSONArray) new JSONParser().parse(jsonText);
        
        return json;
    }
        
    @RequestMapping(value = "/Listed", method = RequestMethod.POST)
    public @ResponseBody  CustomObjectResponse getAll() {
    	logger.debug("GestoreController: - Ricevuta richiesta Listed Gestori");

        List<Gestore> gestori = gestoreService.getAll();
            	
        CustomObjectResponse response = new CustomObjectResponse();
        response.setRows((List<Gestore>) gestori);

        // Assegna il numro totale di record trovati. Tale valore e' usato per il paging
           	response.setRecords( String.valueOf(gestori.size()) );
            	
        response.setPage( "1" );
        response.setTotal( "10" );
            	
        logger.debug("Prima del return: response = "+response.toString());
        return response;
    }

        
    @RequestMapping(value = "/ajaxEdit", method = RequestMethod.POST)
    public @ResponseBody CustomGenericResponse ajaxEdit( @ModelAttribute("gestoreAttribute") Gestore gestore, Model model, HttpServletRequest request){
        	
    	String username = request.getRemoteUser();
        if(username != null){
        	logger.debug("GestoreController: - Ricevuta richiesta di modifica Gestore");
	        	
	        /* Call service to edit */
	        Boolean success = gestoreService.edit(gestore);
	        	
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
    		logger.debug("GestoreController: - Ricevuta richiesta di cancellazione Gestore");
	        	
	        List<String> listIds = Arrays.asList(ids.split(","));
	        Iterator<String> idsIterator = listIds.iterator();
	        	
	        try{
	        	// ELIMINO LE CREDENZIALI ASSOCIATE 
	            while(idsIterator.hasNext()){
	            	/* RECUPERO IL Gestore */
	            	Gestore tmpGestore = gestoreService.get(Integer.parseInt(idsIterator.next()));
	            	/* ELIMINO L'Accesso (Gestore + Credenzialiutente) */
	            	accessService.delete(tmpGestore);
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
    	else{
    		// A failure. Return a custom response as well
           	CustomGenericResponse response = new CustomGenericResponse();
           	response.setSuccess(false);
           	response.setMessage("Sessione scaduta!");
           	return response;
    	}
     }
}