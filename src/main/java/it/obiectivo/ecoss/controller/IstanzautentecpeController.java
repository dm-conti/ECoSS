package it.obiectivo.ecoss.controller;

import it.obiectivo.ecoss.domain.Istanzautentecpe;
import it.obiectivo.ecoss.json.CustomContrattoResponse;
import it.obiectivo.ecoss.json.CustomGenericResponse;
import it.obiectivo.ecoss.service.IstanzautentecpeService;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/istanzautentecpe")
public class IstanzautentecpeController {
	protected static Logger logger = Logger.getLogger("IstanzautentecpeController");
	
	@Resource(name="istanzautentecpeService")
	private IstanzautentecpeService istUteCpeService;
	
	@RequestMapping(value = "/Listed", method = RequestMethod.GET)
    public ModelAndView getListed(Model model) throws JsonGenerationException, JsonMappingException, IOException, ParseException {
    	logger.debug("Ricevuta richiesta per la pagina Listed");
    	
    	ModelAndView modelAndView = new ModelAndView("istanzautentecpe/Listed");
    	
    	List<Istanzautentecpe> listUteCpe = istUteCpeService.getAll();
    	
    	String jsonText = new ObjectMapper().writeValueAsString(listUteCpe);
        JSONArray json = (JSONArray) new JSONParser().parse(jsonText);
    	
        modelAndView.addObject("listaIstUteCpe", json);
    
    	return modelAndView;
	}
	
	@RequestMapping(value = "/Details", method = RequestMethod.GET)
	public ModelAndView getEdit(@RequestParam(value="idIstanzaUtenteCPE", required=true) Integer idContratto) throws Exception{
		ModelAndView modelAndView = new ModelAndView("gestore/detailsPage");
	    Istanzautentecpe contratto = istUteCpeService.get(idContratto);
	    	
	    modelAndView.addObject("contratto", contratto);
	    	
	    return modelAndView;
	}
	
	@RequestMapping(value = "/New", method = RequestMethod.GET)
    public String sendPageNew(Model model) {
    	logger.debug("Ricevuta richiesta per la pagina New");
    
    	return "istanzautentecpe/New";
	}
	
	/* RICHIESTE Ajax */
	
	@RequestMapping(value = "/Listed", method = RequestMethod.POST)
    public @ResponseBody  CustomContrattoResponse getAll(HttpServletRequest request, HttpSession session) {
        	logger.debug("UtenteController: - Ricevuta richiesta Listed Utenti");
        	
        	CustomContrattoResponse response = new CustomContrattoResponse();

        	List<Istanzautentecpe> contratti = istUteCpeService.getAll();
        	response.setRows(contratti);
        	
        	/**
        	 * @todo
        	 * Portare la logiga del caricamento delle liste discriminate 
        	 * per stato all'interno della classe CustomContrattoResponse
        	 */
        	// Assegna il numero totale di record trovati. Tale valore e' usato per il paging
        	response.setRecords( String.valueOf(response.getRows().size()) );
        	response.setPage( "1" );
        	response.setTotal( "10" );
        	
        	logger.debug("Prima del return: response = "+response.toString());
        	return response;
    	}
	
	@RequestMapping(value = "/getAttivi", method = RequestMethod.GET)
    public String getAttivi(Model model) {
    	logger.debug("Ricevuta richiesta per la pagina Listed");
    	return "istanzautentecpe/getAttivi";
	}
	
	@RequestMapping(value = "/getAttivi", method = RequestMethod.POST)
    public @ResponseBody  CustomContrattoResponse getAttivi(HttpServletRequest request) {
        	logger.debug("IstanzautentecpeController: - Ricevuta richiesta getAttivi");
        	
        	CustomContrattoResponse response = new CustomContrattoResponse();
        	
        	/* RICERCA SULLA BASE DEL VALORE ASSUNTO DALLO STATO: 
        	 * Istanza_Attiva, Istanza_Sospesa, Istanza_Chiusa*/
        	
        	List<Istanzautentecpe> contratti = istUteCpeService.getByStato("Istanza_Attiva");
        	response.setRows(contratti);
        	
        	// Assegna il numero totale di record trovati. Tale valore e' usato per il paging
        	response.setRecords( String.valueOf(response.getRows().size()) );
        	response.setPage( "1" );
        	response.setTotal( "10" );
        	
        	logger.debug("Prima del return: response = "+response.toString());
        	return response;
    }
	
	@RequestMapping(value = "/getSospesi", method = RequestMethod.GET)
    public String getSospesi(Model model) {
    	logger.debug("Ricevuta richiesta per la pagina Listed");
    	return "istanzautentecpe/getSospesi";
	}
	
	@RequestMapping(value = "/getSospesi", method = RequestMethod.POST)
    public @ResponseBody  CustomContrattoResponse getSospesi(HttpServletRequest request) {
        	logger.debug("IstanzautentecpeController: - Ricevuta richiesta getSospesi");
        	
        	CustomContrattoResponse response = new CustomContrattoResponse();
        	
        	/* RICERCA SULLA BASE DEL VALORE ASSUNTO DALLO STATO: 
        	 * Istanza_Attiva, Istanza_Sospesa, Istanza_Chiusa*/
        	
        	List<Istanzautentecpe> contratti = istUteCpeService.getByStato("Istanza_Sospesa");
        	response.setRows(contratti);
        	
        	// Assegna il numero totale di record trovati. Tale valore e' usato per il paging
        	response.setRecords( String.valueOf(response.getRows().size()) );
        	response.setPage( "1" );
        	response.setTotal( "10" );
        	
        	logger.debug("Prima del return: response = "+response.toString());
        	return response;
    }
	
	@RequestMapping(value = "/getChiusi", method = RequestMethod.GET)
    public String getChiusi(Model model) {
    	logger.debug("Ricevuta richiesta per la pagina Listed");    
    	return "istanzautentecpe/getChiusi";
	}
	
	@RequestMapping(value = "/getChiusi", method = RequestMethod.POST)
    public @ResponseBody  CustomContrattoResponse getChiusi(HttpServletRequest request) {
        	logger.debug("IstanzautentecpeController: - Ricevuta richiesta getChiusi");
        	
        	CustomContrattoResponse response = new CustomContrattoResponse();
        	
        	/* RICERCA SULLA BASE DEL VALORE ASSUNTO DALLO STATO: 
        	 * Istanza_Attiva, Istanza_Sospesa, Istanza_Chiusa*/
        	
        	List<Istanzautentecpe> contratti = istUteCpeService.getByStato("Istanza_Chiusa");
        	response.setRows(contratti);
        	
        	// Assegna il numero totale di record trovati. Tale valore e' usato per il paging
        	response.setRecords( String.valueOf(response.getRows().size()) );
        	response.setPage( "1" );
        	response.setTotal( "10" );
        	
        	logger.debug("Prima del return: response = "+response.toString());
        	return response;
    }
	
	
	@RequestMapping(value = "/ajaxDelete", method = RequestMethod.POST)
    public @ResponseBody CustomGenericResponse ajaxDelete(
    		@RequestParam("arrayOfIds") String ids, HttpServletRequest request ){
		
		String username = request.getRemoteUser();
		if(username != null){
	    	logger.debug("Ricevuta richiesta ajaxDelete");
	    	
	    	List<String> listIds = Arrays.asList(ids.split(","));
	    	Iterator<String> itr = listIds.iterator();
	    	
	    	Boolean success = false;
	    	while(itr.hasNext()){
	    		success = istUteCpeService.delete(Integer.parseInt(itr.next()));
	    	}
	    	
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
}