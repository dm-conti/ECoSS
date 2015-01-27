package it.obiectivo.ecoss.controller;

import it.obiectivo.ecoss.dao.ServizioDAO;
import it.obiectivo.ecoss.domain.Cpe;
import it.obiectivo.ecoss.domain.Credenzialiutente;
import it.obiectivo.ecoss.domain.Distributore;
import it.obiectivo.ecoss.domain.Gestore;
import it.obiectivo.ecoss.domain.Licenzacpe;
import it.obiectivo.ecoss.domain.Servizilicenzacpe;
import it.obiectivo.ecoss.domain.Tabellastati;
import it.obiectivo.ecoss.service.CpeService;
import it.obiectivo.ecoss.service.CredenzialiutenteService;
import it.obiectivo.ecoss.service.GestoreService;
import it.obiectivo.ecoss.service.TabellastatiService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import it.obiectivo.ecoss.json.CustomLicenzacpeResponse;
import it.obiectivo.ecoss.json.CustomObjectResponse;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/cpe")
public class CpeController {
	protected static Logger logger = Logger.getLogger("controller");
	
	@Resource(name="cpeService")
	private CpeService cpeService;
	
	@Resource(name="gestoreService")
	private GestoreService gestoreService;
	
	@Resource(name="credenzialiutenteService")
	private CredenzialiutenteService credenzialiService;
	
	@Resource(name="tabellastatiService")
	private TabellastatiService statoService;
	
	//RESTITUISCO una lista di Utenti senza Contratto
	@RequestMapping(value = "/Listed", method = RequestMethod.GET)
	public ModelAndView getAll( HttpServletRequest request, HttpSession session) throws Exception{
		ModelAndView modelAndView = new ModelAndView("cpe/Listed");
        List<Cpe> listaCpe = cpeService.getAll();
        
        String jsonText = new ObjectMapper().writeValueAsString(listaCpe);
        JSONArray json = (JSONArray) new JSONParser().parse(jsonText);
        	
        modelAndView.addObject("listaCpe", json);
        	
        return modelAndView;
	}
	
	//INVOCO LA JSP New - Inserimento di un nuovo Cpe - 
    @RequestMapping(value = "/New", method = RequestMethod.GET)
    public ModelAndView getAdd(HttpServletRequest request, HttpSession session) {
    	logger.debug("Visualizzazione della pagina cpe/New");
    	String username = request.getRemoteUser();
    	ModelAndView modelAndView;
    	
    	if(username == null){
    		modelAndView = new ModelAndView("redirect:/login");
    		return modelAndView;
    	}
    	else{
    		try{
    			/* RECUPERO LE CREDENZIALI ASSECONDA DELLE QUALI VADO A 
    			* VALORIZZARE I CAMPI DI RICERCA PER I Gestori e\o Subgestori*/
    			Credenzialiutente credUte = credenzialiService.getByUserId(request.getRemoteUser());
        		
        		if(credUte.getProfilo().getProfDescrizione().equalsIgnoreCase("Utente")){
        			new Exception("Blocked an attempt to run critical function by an unauthorized user");
        		}
        		
        		modelAndView = new ModelAndView("cpe/New");
    			
    			/* SE IL RUOLO DELL'Utente E' Admin, DEVO INVIARE ALLA View LA LISTA DEI Gestori */
    			if(credUte.getProfilo().getProfDescrizione().equalsIgnoreCase("Admin")){
    				/*PREPARO LE DUE LISTE*/
    				List<Gestore> listaGestori = gestoreService.getAll();
    				modelAndView.addObject("listaGestori", listaGestori);
    				session.setAttribute("listaGestori", listaGestori);
    			}
    			
    			/* ALTRIMENTI SE L'Utente HA RUOLO DI Gestore, DEVO INVIARE ALLA View SOLO */
    			else if(credUte.getProfilo().getProfDescrizione().equalsIgnoreCase("Gestore")){
    				/*PRIMA RECUPERO LA RAGIONE SOCIALE DEL GESTORE INVIO ALLA View PER VALORIZZARE 
    				* IL RISPETTIVO CAMPO DI INPUT */
    				List<Gestore> listaGestori = new ArrayList<Gestore>();
    				listaGestori.add(credUte.getGestore());
    					
    				modelAndView.addObject("listaGestori", listaGestori);
    				session.setAttribute("listaGestori", listaGestori);
    			}
    			else if(credUte.getProfilo().getProfDescrizione().equalsIgnoreCase("Subgestore")){ 
    				/*	QUANDO L'Utente HA RUOLO DI Distributore NON DEVO VALORIZZARE LE LISTE 
    				*  MA DIRETTAMENTE I CAMPI DELLE Ragioni Sociali*/
    				List<Gestore> listaGestori = new ArrayList<Gestore>();
    				
    				Distributore subGestore = credUte.getDistributore();
    				listaGestori.add(subGestore.getGestore());
    				modelAndView.addObject("listaGestori", listaGestori);
    				session.setAttribute("listaGestori", listaGestori);
    			}
    			
    			modelAndView.addObject("cpeAttribute", new Cpe());
    	        return modelAndView;
    			
    		}catch (Exception e) {
				// TODO: handle exception
    			modelAndView = new ModelAndView("cpe/errorPage");
    			modelAndView.addObject("errorMessage", "Messaggio di errore personalizzato: creare delle Custom Exception oppure gestire i casi");
    			return modelAndView;
			}
    	}
    }
    
    /* SALVO il Cpe CREATO */
    @RequestMapping(value = "/Add", method = RequestMethod.POST)
    public ModelAndView add( @Valid @ModelAttribute("cpeAttribute") Cpe cpe, 
    		BindingResult result, HttpServletRequest request, ModelAndView modelAndView ){
    	
    	logger.debug("CpeController: Salvataggio del Cpe");
        	
		if(result.hasErrors()){
			modelAndView = new ModelAndView("cpe/erroreCpe");
        	modelAndView.addObject("cpeAttribute", cpe);
        	return modelAndView;
		}
        
		else{
			Gestore gestore = gestoreService.getByRagSociale(cpe.getGestore().getGestRagSociale());
			cpe.setGestore(gestore);
			
			Tabellastati stato = statoService.getByName("CPE0");
			cpe.setTabellastati(stato);
			
			cpeService.add(cpe);
			
        	modelAndView = new ModelAndView("cpe/riepilogoCpe");
        	modelAndView.addObject("cpe", cpe);
        	return modelAndView;
        }
    }
	
	/* ------------------------------------------------------------ *
     * ---------------- INIZIO RICHIESTE AJAX --------------------- *
     * ------------------------------------------------------------ */
	/**/
    
    @RequestMapping(value = "/Listed", method = RequestMethod.POST)
    public @ResponseBody  CustomObjectResponse getAll() {
        	logger.debug("CpeController: - Ricevuta richiesta Listed Cpe");

        	List<Cpe> cpes = cpeService.getAll();
        	
        	CustomObjectResponse response = new CustomObjectResponse();
        	response.setRows(cpes);

        	// Assegna il numro totale di record trovati. Tale valore e' usato per il paging
        	response.setRecords( String.valueOf(cpes.size()) );
        	
        	// Since our service is just a simple service for teaching purposes
        	// We didn't really do any paging. But normally your DAOs or your persistence layer should support this
        	// Assign a dummy page
        	response.setPage( "1" );
        	
        	// Same. Assign a dummy total pages
        	response.setTotal( "10" );
        	
        	// Return the response
        	// Spring will automatically convert our CustomUserResponse as JSON object. 
        	// This is triggered by the @ResponseBody annotation. 
        	// It knows this because the JqGrid has set the headers to accept JSON format when it made a request
        	// Spring by default uses Jackson to convert the object to JSON
        	
        	logger.debug("Prima del return: response = "+response.toString());
        	return response;
    	}
    
	@RequestMapping(value = "/getDetail", method = RequestMethod.POST)
    public @ResponseBody  CustomLicenzacpeResponse getListOfVocelicenza( @RequestParam("ids") String id ) {
		logger.debug("CpeController: - getListOfVocelicenza");
		Cpe existingCpe = cpeService.get(Integer.parseInt(id));
		
		/*RECUPERO L'UNICA Licenzacpe ASSOCIATA AL Cpe*/
		List<Licenzacpe> licenze = existingCpe.getLicenzacpeList();
		Licenzacpe licCpe = licenze.get(licenze.size()-1);
		
		//Setto il Nome della Licenza associata al CPE
		CustomLicenzacpeResponse response = new CustomLicenzacpeResponse(licCpe.getLicCpeNome());
		
		List <Servizilicenzacpe> listaServizi = licCpe.getServizilicenzacpeList();
		
		//Creo l'Entita' Virtuale
		Iterator<Servizilicenzacpe> itr = listaServizi.iterator();
		ArrayList<ServizioDAO> arrayServizi = new ArrayList<ServizioDAO>();
		
		while(itr.hasNext()){
			ServizioDAO tmpServizio = new ServizioDAO(itr.next());
			arrayServizi.add(tmpServizio);
		}
		
		response.setRows(arrayServizi);
        
        // Assegna il numro totale di record trovati. Tale valore e' usato per il paging
        response.setRecords( String.valueOf(licenze.size()) );
        
        response.setPage( "1" );
        
        // Same. Assign a dummy total pages
        response.setTotal( "10" );
        
        logger.debug("Prima del return: response = "+response.toString());
        return response;
    }
}