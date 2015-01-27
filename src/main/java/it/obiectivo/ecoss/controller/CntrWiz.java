package it.obiectivo.ecoss.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.obiectivo.ecoss.domain.Cpe;
import it.obiectivo.ecoss.domain.Credenzialiutente;
import it.obiectivo.ecoss.domain.Distributore;
import it.obiectivo.ecoss.domain.Gestore;
import it.obiectivo.ecoss.domain.Istanzautentecpe;
import it.obiectivo.ecoss.domain.Licenzautente;
import it.obiectivo.ecoss.domain.Licenzecommerciali;
import it.obiectivo.ecoss.domain.Profilo;
import it.obiectivo.ecoss.domain.Utente;
import it.obiectivo.ecoss.service.CpeService;
import it.obiectivo.ecoss.service.CredenzialiutenteService;
import it.obiectivo.ecoss.service.DistributoreService;
import it.obiectivo.ecoss.service.GestoreService;
import it.obiectivo.ecoss.service.IstanzautentecpeService;
import it.obiectivo.ecoss.service.JavaMailService;
import it.obiectivo.ecoss.service.LicenzecommercialiService;
import it.obiectivo.ecoss.service.ProfiloService;
import it.obiectivo.ecoss.service.UtenteService;

import javax.annotation.Resource;
import javax.mail.AuthenticationFailedException;
import javax.mail.NoSuchProviderException;
import javax.mail.SendFailedException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.util.WebUtils;

@Controller
@RequestMapping("/istanzautentecpe")
@SessionAttributes("newContratto")
public class CntrWiz {
	protected static Logger logger = Logger.getLogger("controller");
	
	@Resource(name="credenzialiutenteService")
	private CredenzialiutenteService credUteService;

	@Resource(name="istanzautentecpeService")
	private IstanzautentecpeService istUteCpeService;
	
	@Resource(name="utenteService")
	private UtenteService utenteService;
	
	@Resource(name="gestoreService")
	private GestoreService gestoreService;
	
	@Resource(name="distributoreService")
	private DistributoreService distributoreService;
	
	@Resource(name="cpeService")
	private CpeService cpeService;
	
	@Resource(name="licenzecommercialiService")
	private LicenzecommercialiService licCommService;
	
	@Resource(name="profiloService")
	private ProfiloService profiloService;
	
	@Resource(name="credenzialiutenteService")
	private CredenzialiutenteService credenzialiService;
	
	@Resource(name="javaMailService")
	private JavaMailService jmService;

	@RequestMapping(value = "/stepOne", method = RequestMethod.GET)
	public String setupForm(Model model, HttpServletRequest request) {
		logger.debug("ContrattoFWizardController: stepOne");
		String username = request.getRemoteUser();
    	if(username == null)
    		return "redirect:/login";
    	else{
    		Istanzautentecpe newContratto = new Istanzautentecpe();
    		model.addAttribute("newContratto", newContratto);
    		return "istanzautentecpe/stepOne";
    	}
	}
	
	@RequestMapping(value = "stepOne", method = RequestMethod.POST) 
	public String sbForm(
			HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute ("newContratto") Istanzautentecpe newContratto, 
			BindingResult result, SessionStatus status,
			@RequestParam(value="_page") int currentPage,
			HttpSession session, Model model) {
		
		String choice;
		Integer idUtente;
		Integer idCPE;
		Integer idLicenzeCommerciali;
		if(newContratto == null)
			logger.debug("ERRORE -");
		
		Map<Integer, String> pageForms = new HashMap<Integer, String>();
		pageForms.put(0, "/istanzautentecpe/stepOne");
		pageForms.put(1, "/istanzautentecpe/stepTwo");
		pageForms.put(2, "/istanzautentecpe/stepThree");
		pageForms.put(3, "/istanzautentecpe/stepFour");
		pageForms.put(4, "/istanzautentecpe/summary");
		
		String username = request.getRemoteUser();
    	if(username == null)
    		return "redirect:/login";
		
		if(request.getParameter("_cancel") != null){
			return (String) pageForms.get(currentPage);
		}
		else if(request.getParameter("_finish") != null){
			
			/* LA STESSA DATA VERRA' SETTATA SIA PER IL TERMINE DEL Contratto CHE 
			 * PER IL TERMINE DELLA Licenzautente*/
			//try{
				Integer id = newContratto.getUtente().getIdUtente();
				/* IL CONTRATTO HA GIA' AL SUO INTERNO L'OGGETTO
				 * Utente QUINDI POSSO RENDERE PERSISTENTE IL Contratto */
				if(id != null){
					Utente tmpUtente = utenteService.get(id);
					newContratto.setUtente(tmpUtente);
					istUteCpeService.add(newContratto);
				}
				/* L'Utente e' stato selezionato e non e' disponibile 
				 * all'interno dell'oggetto newContratto */
				else{
					/* CREO LE CREDENZIALI DI ACCESSO: SONO TEMPORANEE PERCHE' MANCA 
				     * ANCORA UN DATO FONDAMENTALE, Utente */
					Profilo ruolo = profiloService.getByProfDescrizione("Utente");
					String userId = newContratto.getUtente().getCredenzialiutente().getUserId();
					//String userId = (String) session.getAttribute("userId");
					Credenzialiutente tmpCredenziali = credenzialiService.makeCredenziali(userId, ruolo);
					istUteCpeService.add(newContratto, tmpCredenziali);
				}
				String from = "ecoss.javamail@gmail.com";
				String to = newContratto.getUtente().getUteEmail();
				String nContratto = newContratto.getNumeroContratto();
				String subject = "Conferma registrazione contratto n."+nContratto;
				String body = makeBodyMessage(newContratto);
				try {
					jmService.sendMail(nContratto, from, to, subject, body);
				} 
				catch (NoSuchProviderException nspEx) {
					nspEx.printStackTrace();
					return "redirect:/istanzautentecpe/emailErrata";
				}
				catch (SendFailedException sfEx) {
					sfEx.printStackTrace();
					return "redirect:/istanzautentecpe/nonInviata";
				}
				catch (AuthenticationFailedException e) {
					// TODO Gestire l'eccezione
					e.printStackTrace();
				}
				catch (Exception e) {
					// TODO Gestire l'eccezione
					e.printStackTrace();
				}
				return "redirect:/istanzautentecpe/Listed";
			//}
			
			/*
			catch (Exception e) {
				e.printStackTrace();
				return "redirect:/istanzautentecpe/erroreContratto";
			}
			*/
		}else{
			int targetPage = WebUtils.getTargetPage(request, "_target", currentPage);
			
			/*SE LA PAGINA E' stepOne*/
			if(targetPage == 1){
				if(request.getParameter("choice")!=null){
					choice = (String) request.getParameter("choice"); 
					session.setAttribute("choice", choice);
				}
				else
					choice = (String) session.getAttribute("choice");
				
				if(choice.equalsIgnoreCase("Nuovo")){
					/* PREPARO L'OGGETTO CHE SARA PASSOTO ALLA View*/
					Utente newUtente = new Utente();
					
					/* RECUPERO LE CREDENZIALI ASSECONDA DELLE QUALI VADO A 
					 * VALORIZZARE I CAMPI DI RICERCA PER I Gestori e\o Subgestori*/
					Credenzialiutente credUte = credUteService.getByUserId(request.getRemoteUser());
					
					/* SE IL RUOLO DELL'Utente E' Admin, DEVO INVIARE ALLA View LA LISTA DEI Gestori
					 * E LA LISTA DEI Subgestori*/
					if(credUte.getProfilo().getProfDescrizione().equalsIgnoreCase("Admin")){
						/*PREPARO LE DUE LISTE*/
						List<Gestore> listaGestori = gestoreService.getAll();
						List<Distributore> listaDistributori = distributoreService.getAll();
						
						//model.addAttribute("listaUtenti", utenteService.getAll());
						model.addAttribute("listaGestori", listaGestori);
						model.addAttribute("listaDistributori", listaDistributori);
					}
					/* ALTRIMENTI SE L'Utente HA RUOLO DI Gestore, DEVO INVIARE ALLA View SOLO
					 * LA LISTA DEI Distributori*/
					else if(credUte.getProfilo().getProfDescrizione().equalsIgnoreCase("Gestore")){
						/*PRIMA RECUPERO LA RAGIONE SOCIALE DEL GESTORE INVIO ALLA View PER VALORIZZARE 
						* IL RISPETTIVO CAMPO DI INPUT */
						List<Gestore> listaGestori = new ArrayList<Gestore>();
						Gestore gestore = credUte.getGestore(); 
						listaGestori.add(gestore);
						
						List<Distributore> listaDistributori = distributoreService.getAll();
						
						//model.addAttribute("listaUtenti", gestore.getUtenteList());
						model.addAttribute("listaGestori", listaGestori);
						model.addAttribute("listaDistributori", listaDistributori);
					}
					else if(credUte.getProfilo().getProfDescrizione().equalsIgnoreCase("Subgestore")){ 
						/*	QUANDO L'Utente HA RUOLO DI Distributore NON DEVO VALORIZZARE LE LISTE 
						*  MA DIRETTAMENTE I CAMPI DELLE Ragioni Sociali*/
						List<Gestore> listaGestori = new ArrayList<Gestore>();
						List<Distributore> listaDistributori = new ArrayList<Distributore>();
						
						Distributore subGestore = credUte.getDistributore();
						listaGestori.add(subGestore.getGestore());
						listaDistributori.add(subGestore);
						
						//model.addAttribute("listaUtenti", subGestore.getUtenteList());
						model.addAttribute("listaGestori", listaGestori);
						model.addAttribute("listaDistributori", listaDistributori);
					} 
					model.addAttribute("newUtente", newUtente);
					model.addAttribute("utente", choice);
				} else if(choice.equalsIgnoreCase("Seleziona")){
					Credenzialiutente credUte = credUteService.getByUserId(request.getRemoteUser());
					
					if(credUte.getProfilo().getProfDescrizione().equalsIgnoreCase("Admin")){
						//TODO: Gestire le eccezioni
						String jsonText;
						try {
							jsonText = new ObjectMapper().writeValueAsString(utenteService.getAll());
							JSONArray json = (JSONArray) new JSONParser().parse(jsonText);
					        model.addAttribute("listaUtenti", json);
						} catch (JsonGenerationException e) {
							// TODO Gestire l'eccezione
							e.printStackTrace();
						} catch (JsonMappingException e) {
							// TODO Gestire l'eccezione
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Gestire l'eccezione
							e.printStackTrace();
						} catch (ParseException e) {
							// TODO Gestire l'ecceezione
							e.printStackTrace();
						}
					}
					else if(credUte.getProfilo().getProfDescrizione().equalsIgnoreCase("Gestore")){
						Gestore gestore = credUte.getGestore();
						String jsonText;
						try {
							jsonText = new ObjectMapper().writeValueAsString(gestore.getUtenteList());
							JSONArray json;
							json = (JSONArray) new JSONParser().parse(jsonText);
					        model.addAttribute("listaUtenti", json);
						} catch (JsonGenerationException e) {
							// TODO Gestire l'eccezione
							e.printStackTrace();
						} catch (JsonMappingException e) {
							// TODO Gestire l'eccezione
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Gestire l'eccezione
							e.printStackTrace();
						}catch (ParseException e) {
							// TODO Gestire l'eccezione
							e.printStackTrace();
						}
					}
					else if(credUte.getProfilo().getProfDescrizione().equalsIgnoreCase("Subgestore")){
						Distributore subGestore = credUte.getDistributore();
						String jsonText;
						try {
							jsonText = new ObjectMapper().writeValueAsString(subGestore.getUtenteList());
							JSONArray json;
							try {
								json = (JSONArray) new JSONParser().parse(jsonText);
								model.addAttribute("listaUtenti", json);
							} catch (ParseException e) {
								// TODO Gestire l'eccezione
								e.printStackTrace();
							}
						
						} catch (JsonGenerationException e1) {
							// TODO Gestire l'eccezione
							e1.printStackTrace();
						} catch (JsonMappingException e1) {
							// TODO Gestire l'eccezione
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Gestire l'eccezione
							e1.printStackTrace();
						}
					}	
				}
				request.setAttribute("utente", choice);
			}
			
			/*SE LA PAGINA E' stepTwo*/
			if(targetPage == 2){
				Credenzialiutente credUte = credUteService.getByUserId(request.getRemoteUser());
				
				if(credUte.getProfilo().getProfDescrizione().equalsIgnoreCase("Admin")){
					//TODO: Gestire le eccezioni
					String jsonText;
					try {
						jsonText = new ObjectMapper().writeValueAsString(cpeService.getAll());
						JSONArray json = (JSONArray) new JSONParser().parse(jsonText);
				        model.addAttribute("listaVsg", json);
					} catch (JsonGenerationException e) {
						// TODO Gestire l'eccezione
						e.printStackTrace();
					} catch (JsonMappingException e) {
						// TODO Gestire l'eccezione
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Gestire l'eccezione
						e.printStackTrace();
					} catch (ParseException e) {
						// TODO Gestire l'eccezione
						e.printStackTrace();
					}
				}
				else if(credUte.getProfilo().getProfDescrizione().equalsIgnoreCase("Gestore")){
					Gestore gestore = credUte.getGestore();
					String jsonText;
					try {
						jsonText = new ObjectMapper().writeValueAsString(gestore.getCpeList());
						JSONArray json = (JSONArray) new JSONParser().parse(jsonText);
				        model.addAttribute("listaVsg", json);
					} catch (JsonGenerationException e) {
						// TODO Gestire l'eccezione
						e.printStackTrace();
					} catch (JsonMappingException e) {
						// TODO Gestire l'eccezione
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Gestire l'eccezione
						e.printStackTrace();
					} catch (ParseException e) {
						// TODO Gestire l'eccezione
						e.printStackTrace();
					}
				}
				//TODO: Creare un chiave esterna tra Cpe e Distributore.
				/*
				else if(credUte.getProfilo().getProfDescrizione().equalsIgnoreCase("Subgestore")){
					Distributore subGestore = credUte.getDistributore();
					String jsonText = new ObjectMapper().writeValueAsString(subGestore.getCpeList());
			        JSONArray json = (JSONArray) new JSONParser().parse(jsonText);
			        model.addAttribute("listaVsg", json);
				}
				*/
				String txtIdUtente = request.getParameter("_idUtente");
				if(txtIdUtente!=null){
					if(txtIdUtente.equalsIgnoreCase("Nuovo")){
						
						session.setAttribute("_idUtente", 0);
					}else{
						idUtente = Integer.parseInt(txtIdUtente);
						Utente tmpUtente = utenteService.get(idUtente);
						newContratto.setUtente(tmpUtente);
						session.setAttribute("_idUtente", idUtente);
						Credenzialiutente credenziali = tmpUtente.getCredenzialiutente();
						if(credenziali == null){
							session.setAttribute("userId", "NONE" );
						}else{
							session.setAttribute("userId", credenziali.getUserId());
						}
							
					}
				}
				else
					idUtente = (Integer) session.getAttribute("_idUtente");
			}
			
			/*SE LA PAGINA E' stepThree*/
			if(targetPage == 3){
				if(request.getParameter("_idVSG")!=null){
					idCPE = Integer.parseInt(request.getParameter("_idVSG"));
					session.setAttribute("_idVSG", idCPE);
				}
				else
					idCPE = (Integer)  session.getAttribute("_idVSG");
				Cpe cpe = cpeService.get(idCPE);
				newContratto.setCpe(cpe);
			}
			
			/*SE LA PAGINA E' stepFour*/
			if(targetPage == 4){
				if(request.getParameter("_idLicenzeCommerciali")!=null){
					idLicenzeCommerciali = Integer.parseInt(request.getParameter("_idLicenzeCommerciali"));
					session.setAttribute("_idLicenzeCommerciali", idLicenzeCommerciali);
					//request.setAttribute("licenzecommerciali", licCommService.getAll());
				}
				else
					idLicenzeCommerciali = (Integer) session.getAttribute("_idLicenzeCommerciali");
					Licenzecommerciali licComm = licCommService.get(idLicenzeCommerciali);
					
					/* OTTENUTA LA Licenzacommerciale POSSO CREARE UNA NUOVA Licenzautente */
					Licenzautente licenza = new Licenzautente();
					licenza.setLicenzecommerciali(licComm);
					newContratto.setLicenzautente(licenza);
					request.setAttribute("userId", session.getAttribute("userId"));
			}
			
			if(targetPage < currentPage){
				return (String) pageForms.get(targetPage); 
				
			}
			session.setAttribute("newContratto", newContratto);
			return (String) pageForms.get(targetPage);
			
		}
	}
	
	/**
	 * Il metodo crea un utente e delle credenziali.
	 * @param session
	 * @param newUtente
	 * @param userId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/makeTmpUser", method = RequestMethod.POST)
    public @ResponseBody String makeTmpUser(HttpSession session, @ModelAttribute ("newUtente") Utente newUtente, @RequestParam("userId") String userId ,Model model) {
    	
    	logger.debug("Aggiungi un nuovo Utente: Richiesta AJAX");
    	
    	// Controllo se l'username esiste già
    	Credenzialiutente credenziali = credenzialiService.getByUserId(userId);
		if(credenziali != null){
			return "Username già esistente, inserire un altro Username";
		}
    	
    	/*List<Utente> listaUtente = utenteService.getAll();
		for(int i=0; i<listaUtente.size(); i++){
			Utente tmpUtente = listaUtente.get(i);
			String tmpUserId = tmpUtente.getCredenzialiutente().getUserId();
			if(userId.equals(tmpUserId)){
				modelAndView = new ModelAndView("utente/New");
				modelAndView.addObject("usernameEsistente", true);
				modelAndView.addObject("listaGestori", session.getAttribute("listaGestori"));
				modelAndView.addObject("listaDistributori", session.getAttribute("listaDistributori"));
				
				return "Username già esistente, inserire un altro Username";
			}
		}*/
    	
    	/* DEVO VALORIZZARE L'ISTANZA DI Utente AGGIUNGENGO 
    	 * LE SEGUENTI INFORMAZIONI: 
    	 * Credenzialutente
    	 * Gestore (dato obbligatorio)
    	 * Distributore (potrebbe esserci o meno) */
    	Credenzialiutente tmpCredenziali = new Credenzialiutente();
    	tmpCredenziali.setUserId(userId);
    	
    	/* 	RECUPERO LA Ragione sociale CON LA QUALE OTTENGO L'ISTANZA CORRISPONDENTE
    		DAL DB.DB */
    	String gestRagSociale = newUtente.getGestore().getGestRagSociale();
    	newUtente.setGestore(gestoreService.getByRagSociale(gestRagSociale));
    	
    	/*	SE LA Ragione sociale DEL Distributore E' STATA VALORIZZATA, DEVO RECUPERARE ANCHE 
    	 * 	L'OGGETTO Distributore ED ASSEGNARLO ALL'Utente */
    	String distRagSociale = newUtente.getDistributore().getDistRagSociale();
    	if(!distRagSociale.equals(null)){
    		newUtente.setDistributore(distributoreService.getByRagSociale(distRagSociale));
    	}
    	
    	newUtente.setCredenzialiutente(tmpCredenziali);
    	
    	Istanzautentecpe newContratto = (Istanzautentecpe) session.getAttribute("newContratto");
    	newContratto.setUtente(newUtente);
    	
    	/*SALVO L'OGGETTO newContratto VALORIZZATO CON IL NUOVO Utente*/
    	session.setAttribute("newContratto", newContratto);
    	session.setAttribute("userId", userId);
    	logger.debug("Nuovo Utente aggiunto all'oggetto Contratto: Richiesta AJAX");
    	return "Nuovo";
	}
	
	private String makeBodyMessage(Istanzautentecpe contratto){
		Credenzialiutente credenziali = contratto.getUtente().getCredenzialiutente();
		String body = "Registrazione Ecoss avvenuta correttamente!" +
		"<br><br>" +
		"<p>Il nuovo conratto e' stato attivato in data: "+contratto.getDataAttivazione()+"</p>"+
		"</ br>ed ha temine il: "+contratto.getDataCessazione()+"</ br>"+
		"</ br>di seguito sono riportate le credenziali di accesso per usufruire dei servizi" +
		"appena acquistati:</ br>" +
		"<br>" +
		"<b>UserId:</b>" +
		credenziali.getUserId()+
		"<br><b>Password:</b>" +
		credenziali.getPassword() +
		"<br><br>" +
		"Si consiglia di cambiare la password dopo aver effettuato il primo accesso";
		return body;
	}
}