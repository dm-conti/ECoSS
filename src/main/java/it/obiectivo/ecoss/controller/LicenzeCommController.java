package it.obiectivo.ecoss.controller;

import it.obiectivo.ecoss.dao.ServizioDAO;
import it.obiectivo.ecoss.domain.Cpe;
import it.obiectivo.ecoss.domain.Gruppolicenzecommerciali;
import it.obiectivo.ecoss.domain.Licenzacpe;
import it.obiectivo.ecoss.domain.Licenzautente;
import it.obiectivo.ecoss.domain.Licenzecommerciali;
import it.obiectivo.ecoss.domain.Servizilicenzacpe;
import it.obiectivo.ecoss.domain.Vocelicenza;
import it.obiectivo.ecoss.json.CustomLicenzacpeResponse;
import it.obiectivo.ecoss.json.CustomObjectResponse;
import it.obiectivo.ecoss.service.GruppoLicenzeCommercialiService;
import it.obiectivo.ecoss.service.LicenzecommercialiService;
import it.obiectivo.ecoss.service.VocelicenzaService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes
@RequestMapping("/licenzecommerciali")
public class LicenzeCommController {
protected static Logger logger = Logger.getLogger("controller");
	
	@Resource(name="licenzecommercialiService")
	private LicenzecommercialiService licenzeCommercialiService;
	
	@Resource(name="gruppoLicenzeCommercialiService")
	private GruppoLicenzeCommercialiService gruppoLicenzeCommercialiService;
	
	@Resource(name="vocelicenzaService")
	private VocelicenzaService voceLicenzaService;
    
	/*INVOCO LA JSP Listed - Lista di tutte le Licenze Commerciali - */
	@RequestMapping(value = "/Listed", method = {RequestMethod.GET, RequestMethod.POST})
    public String getListaLicCommerciali(Model model) {
    	logger.debug("Ricevuta richiesta per risolvere /licenzecommerciali/Listed");
    	
    	List<Licenzecommerciali> licenzecommerciali = licenzeCommercialiService.getAll();
    	model.addAttribute("listaLicCommerciali", licenzecommerciali);
    
    	return "licenzecommerciali/Listed";
	}
    
	/*INVOCO LA JSP New - Inserimento di un nuovo utente - */
	@RequestMapping(value = "/New", method = RequestMethod.GET)
    public String getAdd(Model model) {
    	logger.debug("Form: Nuova Licenza Commerciale");
    	List<Vocelicenza> listaServizi = voceLicenzaService.getAll();
    	
    	model.addAttribute("listaServizi", listaServizi);
    	model.addAttribute("licCommerciale", new Licenzecommerciali());
    	
    	return "licenzecommerciali/New";
	}
	
	@RequestMapping(value = "/New", method = RequestMethod.POST)
    public String add(@ModelAttribute("licCommerciale") Licenzecommerciali newLicenza, @RequestParam(value="servicesSelected", required=true) String stringServices) {
		
		/*licenzeCommercialiService.add(newLicenza);*/
    	List<Vocelicenza> listVocelicenza = null;
		
    	//Recupero la lista Vocilicenze
    	listVocelicenza = getListOfVocelicenza(stringServices);
    	
    	//Recupero la lista Gruppolicenzecommerciali
    	List<Gruppolicenzecommerciali> listaGruppLicComm = getListOfGruppLicComm(newLicenza, listVocelicenza);
    	
    	//Ora posso completare l'oggetto newLicenzeCommerciali
    	newLicenza.setGruppolicenzecommercialiList(listaGruppLicComm);
    	
    	
    	//Rendo Persistente l'oggetto
    	licenzeCommercialiService.add(newLicenza);
    	
    	//Rendo Persistente gli oggetto Gruppolicenzecommerciali
    	Iterator<Gruppolicenzecommerciali> itr = listaGruppLicComm.iterator();
    	while(itr.hasNext()){
    		gruppoLicenzeCommercialiService.add(itr.next());
    	}
    	logger.debug("---END--- : /NEW .POST");
    	return "redirect:/licenzecommerciali/Listed";
	}
	
	/* ------------------------------------------------------------ *
     * ---------------- INIZIO RICHIESTE AJAX --------------------- *
     * ------------------------------------------------------------ */
	/**/
	
	@RequestMapping(value = "/Listed", method = RequestMethod.POST)
    public @ResponseBody  CustomObjectResponse getAll() {
        	logger.debug("LicenzautenteController: - Ricevuta richiesta Listed Licenzautente");

        	List<Licenzecommerciali> licenze = licenzeCommercialiService.getAll();
        	
        	CustomObjectResponse response = new CustomObjectResponse();
        	response.setRows(licenze);

        	// Assegna il numro totale di record trovati. Tale valore e' usato per il paging
        	response.setRecords( String.valueOf(licenze.size()) );
        	
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
	
	
	@RequestMapping(value = "/getDetails", method = RequestMethod.POST)
    public @ResponseBody  CustomObjectResponse getDetails( @RequestParam("ids") String id ) {
		logger.debug("LicenzeCommController: - getDetails");
		Integer idLicenzaComm = Integer.parseInt(id);
		//RECUPER LA Licenzacommerciale
		Licenzecommerciali licenza = licenzeCommercialiService.get(idLicenzaComm);
		List<Gruppolicenzecommerciali> listaServizi = licenza.getGruppolicenzecommercialiList();
		
		CustomObjectResponse response = new CustomObjectResponse();
		
		response.setRows(listaServizi);
        
        // Assegna il numro totale di record trovati. Tale valore e' usato per il paging
        response.setRecords( String.valueOf(listaServizi.size()) );
        
        response.setPage( "1" );
        
        // Same. Assign a dummy total pages
        response.setTotal( "10" );
        
        logger.debug("Prima del return: response = "+response.toString());
        return response;
    }
	
	@RequestMapping(value = "/Delete", method = RequestMethod.POST)
	    public @ResponseBody String delete(@RequestParam(value="arrayId", required=true) String arrayId,  Model model) {
			logger.debug("Ricevuta richiesta per eliminazione utenti");
			
			String[] ids = arrayId.split(",");
			Licenzecommerciali tmpLicComm = null;
			List<Gruppolicenzecommerciali> tmpGruppLic = null;
			/*RICEVUTO L'ARRAY DI ID, CANCELLO GLI UTENTI DAL DB*/
			for(int i = 0; i < ids.length ;i++){
				int idLicComm = Integer.parseInt(ids[i]);
				tmpLicComm = licenzeCommercialiService.get(idLicComm);
				tmpGruppLic = tmpLicComm.getGruppolicenzecommercialiList(); 
				Iterator<Gruppolicenzecommerciali> itr = tmpGruppLic.iterator();
				while(itr.hasNext()){
					gruppoLicenzeCommercialiService.delete(itr.next().getIdGruppiLicenzeCommerciali());
				}
				licenzeCommercialiService.delete(idLicComm);
			}
			logger.debug("Licenzecommerciali eliminate");
			return "redirect:/licenzecommerciali/Listed";
	}
	 
	 /*INVOCO LA JSP Edit*/
	 @RequestMapping(value = "/Edit", method = RequestMethod.GET)
	 public String getEdit(@RequestParam(value="idLicenzeCommerciali", required=true) Integer idLicComm,  Model model) {
	    	logger.debug("Ricevuto richiesta: /Edit per Licenzecommerciali");
	    	
	    	Licenzecommerciali licComm = licenzeCommercialiService.get(idLicComm);
	    	List<Gruppolicenzecommerciali> tmpGruppLic = licComm.getGruppolicenzecommercialiList();
	    	Iterator<Gruppolicenzecommerciali> itrGruppLic = tmpGruppLic.iterator();
	    	
	    	ArrayList<String> alreadyAdded = new ArrayList<String>();
	    	while(itrGruppLic.hasNext()){
	    		String tmpVoice = itrGruppLic.next().getVocelicenza().getVoceLicenza(); 
	    		alreadyAdded.add(tmpVoice);
	    	}// -alreadyAdded- Lista dei servizi del pacchetto
	    	
	    	List<Vocelicenza> allVoiceLic = voceLicenzaService.getAll();
	    	Iterator<Vocelicenza> itrAllVoice = allVoiceLic.iterator();
	    	
	    	ArrayList<String> otherServices = new ArrayList<String>();
	    	while(itrAllVoice.hasNext()){
	    		String tmpVoice = itrAllVoice.next().getVoceLicenza();
	    		if(!alreadyAdded.contains(tmpVoice)){
	    			otherServices.add(tmpVoice);
	    		}
	    	}
	    	
	    	model.addAttribute("licCommerciale", licComm );
	    	model.addAttribute("listServices", otherServices );
	    	model.addAttribute("servicesAdded", alreadyAdded );
	    	
	    	
	    	return "licenzecommerciali/Edit";
		}
	    
	    /*ESEGUO LA MODIFICA DELL'UTENTE E RITORNO ALLA LISTA UTENTI*/
	    @RequestMapping(value = "/Edit", method = RequestMethod.POST)
	    public String saveEdit(	@ModelAttribute("licCommerciale") Licenzecommerciali licComm , 
	    						@RequestParam(value="servicesSelected", required=true) List<String> servicesSelected,
	    						Model model) {
	    	
	    	logger.debug("Ricevuta richiesta di modifica per una Licenzacommerciale");
	    	Licenzecommerciali licCommMod = licenzeCommercialiService.get(licComm.getIdLicenzeCommerciali());
	    	List<Gruppolicenzecommerciali> listaGrupp = licCommMod.getGruppolicenzecommercialiList();
	    	ArrayList<String> serviceSelected = new ArrayList<String>();
	    	
	    	for(int index = 0; index < listaGrupp.size(); index++){
	    		Gruppolicenzecommerciali tmpGrupp = listaGrupp.get(index); 
	    		String tmpVoice = tmpGrupp.getVocelicenza().getVoceLicenza();
	    		
	    		/*
	    		* SE L'ELENCO DEI SERVIZI SELEZIONATI NON CONTIENE
	    		* LA VOCE DI LICENZA GIA' PRESENTE, VUOL DIRE CHE 
	    		* DALL'ELENCO E' STATA ELIMINATA LA VOCE
	    		*/
	    		if(!servicesSelected.contains(tmpVoice)){
	    			listaGrupp.remove(index);
	    			gruppoLicenzeCommercialiService.delete(tmpGrupp.getIdGruppiLicenzeCommerciali());
	    			continue;
	    		}
	    		serviceSelected.add(tmpVoice);
	    	}
	    	
	    	for(int index = 0; index < servicesSelected.size(); index++){
	    		String tmpVoice = servicesSelected.get(index);
	    		
	    		/*
	    		 * SE L'ELENCO DEI SERVIZI GIA' ESISTENTI
	    		 * NON CONTIENE LA VOCE SELEZIONATA ALLORA
	    		 * DOBBIAMO AGGIUNGERLA
	    		 */
	    		if(!serviceSelected.contains(tmpVoice)){
	    			Gruppolicenzecommerciali newGrupp = new Gruppolicenzecommerciali();
	    			
	    			newGrupp.setLicenzecommerciali(licCommMod);
	    			Vocelicenza voceLicenza = voceLicenzaService.getByName(tmpVoice);
	    			newGrupp.setVocelicenza(voceLicenza);
	    			gruppoLicenzeCommercialiService.add(newGrupp);
	    			newGrupp = gruppoLicenzeCommercialiService.get(newGrupp.getIdGruppiLicenzeCommerciali());
	    			listaGrupp.add(newGrupp);
	    		}
	    	}
	    	
	    	
	    	licComm.setGruppolicenzecommercialiList(listaGrupp);
	    	licenzeCommercialiService.edit(licComm);
	    	
	    	logger.debug("Modifica Licenzacommerciale Terminata");
	    	return "redirect:/licenzecommerciali/Listed";
		}
		
		
	    /*CLASSI DI SERVIZIO*/
		private List<Gruppolicenzecommerciali> getListOfGruppLicComm(Licenzecommerciali newLicComm, List<Vocelicenza> listVocLic){
		
		//Ora abbiamo le coppie {newLicComm, newVoceLic}
    	ArrayList<Gruppolicenzecommerciali> listaGruppoLic = new ArrayList<Gruppolicenzecommerciali>();
    	Iterator<Vocelicenza> itr = listVocLic.iterator();
    	while(itr.hasNext()){
    		//Creo un nuovo Gruppolicenzecommerciali
        	Gruppolicenzecommerciali newGruppoLicComm = new Gruppolicenzecommerciali();
        	newGruppoLicComm.setLicenzecommerciali(newLicComm);
        	newGruppoLicComm.setVocelicenza(itr.next());
        	
        	listaGruppoLic.add(newGruppoLicComm);
        	//Salvo il Gruppolicenzecommerciali appena creato
        	//gruppoLicenzeCommercialiService.add(newGruppoLicComm);
    	}
    	return listaGruppoLic;
	}
	
	private List<Vocelicenza> getListOfVocelicenza(String stringServices){
		
		//Recupero i nomi dei servizi selezionati
    	ArrayList<String> servicesSelected = new ArrayList<String>();
		StringTokenizer tokens = new StringTokenizer(stringServices,",");
		while(tokens.hasMoreTokens()){
			servicesSelected.add((String) tokens.nextElement());
		}
		
    	ArrayList<Vocelicenza> listVocelicenza = new ArrayList<Vocelicenza>();
    	Iterator<String> itrServSelected = servicesSelected.iterator();
    	
    	//Creo una Lista con tutti gli oggetti Vocelicenza trovati
    	while(itrServSelected.hasNext()){
    		//Recupero la Vocelicenza
    		Vocelicenza newVoceLic = voceLicenzaService.getByName(itrServSelected.next());
    		//Aggiungo la Vocelicenza in un Array che mi servira' per il salvataggio di newLicenzeCommerciali
    		listVocelicenza.add(newVoceLic);
    	}
    	return listVocelicenza;
	}
}
