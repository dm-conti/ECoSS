package it.obiectivo.ecoss.controller;

import it.obiectivo.ecoss.domain.CheckDate;
import it.obiectivo.ecoss.domain.Gruppolicenzecommerciali;
import it.obiectivo.ecoss.domain.Istanzautentecpe;
import it.obiectivo.ecoss.domain.Licenzacpe;
import it.obiectivo.ecoss.domain.Licenzautente;
import it.obiectivo.ecoss.domain.Notifiche;
import it.obiectivo.ecoss.domain.Servizilicenzacpe;
import it.obiectivo.ecoss.service.CheckDateService;
import it.obiectivo.ecoss.service.IstanzautentecpeService;
import it.obiectivo.ecoss.service.LicenzacpeService;
import it.obiectivo.ecoss.service.LicenzautenteService;
import it.obiectivo.ecoss.service.NotificheService;
import it.obiectivo.ecoss.service.ServizilicenzacpeService;
import it.obiectivo.ecoss.service.TabellastatiService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Period;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class MainController {

	protected static Logger logger = Logger.getLogger("controller");
	
	@Resource(name="istanzautentecpeService") /* */
	private IstanzautentecpeService istUteCpeService;
	
	@Resource(name="licenzautenteService")
	private LicenzautenteService licenzautenteService;
	
	@Resource(name="licenzacpeService")
	private LicenzacpeService licenzacpeService;
	
	@Resource(name="servizilicenzacpeService")
	private ServizilicenzacpeService serviziService;
	
	@Resource(name="tabellastatiService")
	private TabellastatiService statoService;
	
	@Resource(name="notificheService")
	private NotificheService notificheService;
	
	@RequestMapping(value = "index", method = {RequestMethod.GET, RequestMethod.POST})
    public String getListPost(Model model, HttpSession session, HttpServletRequest request) {
    	logger.debug("MainController: Wellcome to Index");
    	String username = request.getRemoteUser();
    	DateTime timeServer = new DateTime();
    	DateTime expiredDate, fineServizio;
    	
    	if(username == null)
    		return "redirect:/login";
    	
    	SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
    	
    	/** 
    	 * CHECK LIST_1 - CONTROLLI SEMPLICI:
    	 * 1) Controllo STATO delle Licenzautente
    	 * 2) Controllo STATO delle Licenzacpe
    	 * 3) Controllo STATO delle Istanzautentecpe
    	 * 4) Controllo STATO dei Servizilicenzacpe
    	 */
    	
    	String prefix = "LIU";
    	List<Licenzautente> licenzeUtenti = licenzautenteService.getByStato(prefix+"0");
    	/* CONTROLLO LA SCADENZA DELLE Licenzeutente */
    	//checkState(licenzeUtenti, timeServer, prefix, "LicenzaUtente");
    	
    	prefix = "LIC";
    	List<Licenzacpe> licenzeCpe = licenzacpeService.getByStato(prefix+"0");
    	/* CONTROLLO LA SCADENZA DELLE Licenzecpe */
    	//checkState(licenzeCpe, timeServer, prefix, "LicenzaCPE");
    	
    	prefix = "IUC";
    	List<Istanzautentecpe> contratti = istUteCpeService.getByStato(prefix+"0");
    	/* CONTROLLO LA SCADENZA DEI Contratti */
    	//checkState(contratti, timeServer, prefix, "IstanzaUtenteCPE");
    	
    	prefix = "SLC";
    	List<Servizilicenzacpe> serviziCpe = serviziService.getByStato(prefix+"0");
    	/* CONTROLLO LA SCADENZA DEI Servizi ASSOCIATI AL Cpe*/
    	//checkState(serviziCpe, timeServer, prefix, "ServiziLicenzaCPE");
    	
    	/** 
    	 * CHECK LIST_2 - CONTROLLI INCROCIATI:
    	 * 1) Confronto SCADENZA Licenzautente VS Contratto
    	 * 2) Confronto SCADENZA Licenzautente VS Servizilicenzacpe
    	 * 3) Controllo STATO delle Istanzautentecpe
    	 * 4) Controllo STATO dei Servizilicenzacpe
    	 */
    	
    	
    	/*CHECK LIST*/
    	
    	//TODO: Controllo contatti in scadenza
    	
    	//TODO: Controllo scadenza Licenzautente
    	
    	//TODO: Controllo scadenza Servizilicenzacpe
    	
    	//TODO: Controllo dei Servizilicenzacpe in scadenza prima della data di scadenza della Licenzacommerciale
    	/*
    	 * 1)Recupero tutti i Contratti Attivi
    	 * 2)Per ogni Contratto:
    	 * 	2a)Recupero la Licenzautente
    	 *  2b)Dalla Licenzautente recupero la Licenzacommerciale
    	 *  2c)Dalla Licenzacommerciale recupero la lista dei Gruppolicenzecommerciali
    	 *  2d)Dalla 
    	 * */
    	
    	List<Licenzautente> vocilicenzaChecked = new ArrayList<Licenzautente>();
    	List<Istanzautentecpe> newcontratti = istUteCpeService.getByStato("Istanza_Attiva");
    	Iterator<Istanzautentecpe> itrIstUteCpe = newcontratti.iterator();
    	
    	/*CONTROLLO TUTTA LA LISTA DI CONTRATTI ATTIVI*/
    	while(itrIstUteCpe.hasNext()){
    		Licenzautente tmpLic = itrIstUteCpe.next().getLicenzautente();
    		expiredDate = new DateTime(tmpLic.getDataCessazione().getTime());
    		
    		/* SE E' GIA' STATA CONTROLLATA ALLORA E' PRESENTE IN vocilicenzaChecked
    		 * QUINDI SALTO IL CICLO*/
    		if(vocilicenzaChecked.contains(tmpLic)){
    			continue;
    		}
    		
    		/* PER OGNI LICENZA RECUPERO IL Gruppolicenzecommerciali
    		 * CHE CONTIENE TUTTI I Servizi FORNITI ALL'Utente*/
    		List<Gruppolicenzecommerciali> gruppi = tmpLic.getLicenzecommerciali().getGruppolicenzecommercialiList();
    		Iterator<Gruppolicenzecommerciali> itrGruppi = gruppi.iterator();
    		
    		/* CONTROLLO LA LISTA DI Servizi PER VERIFICARE SE QUALC'UNO DI ESSI
    		 * HA UNA DATA DI SCADENZA PRECEDENTE LA DATA DI SCADENZA DELLA Licenza*/
    		while(itrGruppi.hasNext()){
    			
    			List<Servizilicenzacpe> servizi = itrGruppi.next().getVocelicenza().getServizilicenzacpeList();
    			Iterator<Servizilicenzacpe> itrServizi = servizi.iterator();
    			while(itrServizi.hasNext()){
    				Servizilicenzacpe tmpServizio = itrServizi.next();
    				
    				fineServizio = new DateTime(tmpServizio.getDataCessazione().getTime());
    				
    				Period expiredLine = new Period(fineServizio, expiredDate);
    				int days = expiredLine.getDays();
    				logger.debug("Giorni del periodo "+days);
    				if(days-30 > 0){
    					//ALLORA DEVO SETTARE LO STATO AD In_Scadenza
    				}
    				if(days-30 < 0){
    					
    					//ALLORA DEVO SETTARE LO STATO A Scaduto
    				}
    			}
    		}
    	}
    	
    	// Caricamento numero dei contratti
		//List<Istanzautentecpe> contratti = istUteCpeService.getAll();
	    //Iterator<Istanzautentecpe> itrContratti = contratti.iterator();
	    	
	    List<Istanzautentecpe> rowsAttivi = istUteCpeService.getByStato("Istanza_Attiva");
	    List<Istanzautentecpe> rowsSospesi = istUteCpeService.getByStato("Istanza_Sospesa");
	    List<Istanzautentecpe> rowsChiusi = istUteCpeService.getByStato("Istanza_Chiusa");
	    		
		/*List<Istanzautentecpe> rowsAttivi = new ArrayList<Istanzautentecpe>();
	    List<Istanzautentecpe> rowsSospesi = new ArrayList<Istanzautentecpe>();
	    List<Istanzautentecpe> rowsChiusi = new ArrayList<Istanzautentecpe>();
	    	
	    while(itrContratti.hasNext()){
	    	Istanzautentecpe tmpContratto = itrContratti.next();
	    	Tabellastati stato = tmpContratto.getTabellastati();
	    	if(stato.getStato().equalsIgnoreCase("Istanza_Attiva")){
	    		rowsAttivi.add(tmpContratto);
	    		continue ;
	    	}
	    	if(stato.getStato().equalsIgnoreCase("Istanza_Sospesa")){
	    		rowsSospesi.add(tmpContratto);
	    		continue ;
	    	}
	    	if(stato.getStato().equalsIgnoreCase("Istanza_Chiusa")){
	    		rowsChiusi.add(tmpContratto);
	    		continue ;
	    	}
	    }*/
	    	
	    Integer nAttivi = rowsAttivi.size();
	    Integer nSospesi = rowsSospesi.size();
	    Integer nChiusi = rowsChiusi.size();
	    	
	    session.setAttribute("nAttivi", nAttivi);
	    session.setAttribute("nSospesi", nSospesi);
	    session.setAttribute("nChiusi", nChiusi);
	    	
	    return "/index";
	}
	
	/* VISUALIZZO LA LISTA DI Gestori */
	@RequestMapping(value = "gestore/index", method = RequestMethod.GET)
    public String getListaGestori(HttpServletRequest request) {
    	logger.debug("MainController: Richiesta visualizzazione lista Gestori");
    	String username = request.getRemoteUser();
    	if(username == null)
    		return "redirect:/login";
    	else
    		return "redirect:/gestore/Listed";
	}
	
	/* VISUALIZZO LA LISTA DI Licenze Commerciali */
	@RequestMapping(value = "licenzecommerciali/index", method = RequestMethod.GET)
    public String getLicenzeCommerciali(HttpServletRequest request) {
    	logger.debug("MainController: Richiesta visualizzazione lista Licenze Commerciali");
    	String username = request.getRemoteUser();
    	if(username == null)
    		return "redirect:/login";
    	else
    		return "redirect:/licenzecommerciali/Listed";
	}
	
	/* VISUALIZZO LA LISTA DI Licenze Commerciali */
	@RequestMapping(value = "utente/testLocalizzation", method = RequestMethod.GET)
    public String getTestLocalizzation(HttpServletRequest request) {
    	logger.debug("MainController: Richiesta visualizzazione lista Licenze Commerciali");
    	String username = request.getRemoteUser();
    	if(username == null)
    		return "redirect:/login";
    	else
    		return "redirect:/utente/test_i18n";
	}
	
	/* VISUALIZZO LA LISTA DI Subgestori */
	@RequestMapping(value = "distributore/index", method = RequestMethod.GET)
    public String getListaDistributori(HttpServletRequest request) {
    	logger.debug("MainController: Richiesta visualizzazione lista Distributori");
    	String username = request.getRemoteUser();
    	if(username == null)
    		return "redirect:/login";
    	else
    		// This will resolve to /WEB-INF/jsp/users.jsp page
    		return "redirect:/distributore/Listed";
	}
	
	/* VISUALIZZO LA LISTA DI Utenti */
	@RequestMapping(value = "utente/index", method = RequestMethod.GET)
    public String getListaUtenti(HttpServletRequest request) {
    	logger.debug("MainController: Richiesta visualizzazione lista Utenti");
    	String username = request.getRemoteUser();
    	if(username == null)
    		return "redirect:/login";
    	else
    		// This will resolve to /WEB-INF/jsp/users.jsp page
    		return "redirect:/utente/Listed";
	}
	
	/* VISUALIZZO LA LISTA DI Cpe */
	@RequestMapping(value = "cpe/index", method = RequestMethod.GET)
    public String getListaCpe(HttpServletRequest request) {
    	logger.debug("MainController: Richiesta visualizzazione lista Cpe");
    	String username = request.getRemoteUser();
    	if(username == null)
    		return "redirect:/login";
    	else
    		// This will resolve to /WEB-INF/jsp/users.jsp page
    		return "redirect:/cpe/Listed";
	}
	
	/* VISUALIZZO LA LISTA DELLE Licenze Utente */
	@RequestMapping(value = "licenzautente/index", method = RequestMethod.GET)
    public String getLicenzeUtenti(HttpServletRequest request) {
    	logger.debug("MainController: Richiesta visualizzazione lista Licenze Utenti");
    	String username = request.getRemoteUser();
    	if(username == null)
    		return "redirect:/login";
    	else
    		// This will resolve to /WEB-INF/jsp/users.jsp page
    		return "redirect:/licenzautente/Listed";
	}
	
	/* VISUALIZZO LA LISTA DI Contratti */
	@RequestMapping(value = "istanzautentecpe/index", method = RequestMethod.GET)
    public String getListaIstanzautentecpe(HttpServletRequest request) {
    	logger.debug("MainController: Richiesta visualizzazione lista Cpe");
    	String username = request.getRemoteUser();
    	if(username == null)
    		return "redirect:/login";
    	else
    	// This will resolve to /WEB-INF/jsp/users.jsp page
    	return "redirect:/istanzautentecpe/Listed";
	}
	
	/** 
	 * CONTROLLI
	 */
	public void checkState(List<? extends CheckDate> lista, DateTime timeServer, String prefix, String nomeTabella, CheckDateService checkDateService){
		/*CONTROLLO LA SCADENZA DEGLI OGGETTI CON LA DATA ATTUALE*/
    	CheckDate tmpObj;
    	Iterator<? extends CheckDate> itrObj = lista.iterator();
    	
    	while(itrObj.hasNext()){
    		tmpObj = itrObj.next();
    		
    		DateTime tmpDate = new DateTime(tmpObj.getDataCessazione().getTime());
    		Days days = Days.daysBetween(timeServer, tmpDate);
    		
    		//TODO: Questo caso non dovrebbe verificarsi. 
    		if(days.getDays() < 0){
    			/* SETTO LO STATO - DOVE prefix+2 = Chiuso\a */
    			tmpObj.setTabellastati(statoService.getByName(prefix+"2"));
    			checkDateService.edit(tmpObj);
    		}
    		
    		if(days.getDays() > 0 && days.getDays() <= 30){
    			/**
    			 * TODO: Creo avviso - Licenzainscadenza.
    			 * */
    			//NTS = close to expire;
    			String codNotifica = "NTS";
    			int idTabella = tmpObj.getId();
    			Notifiche notifica = new Notifiche(idTabella, nomeTabella, codNotifica);
    			notificheService.add(notifica);
    				
    			/*
    			 * 1) CREO CODICE NOTIFICA
    			 * 2) REGISTRO LA NOTIFICA NEL DB
    			 * */
    		}
    		
    		
    		
    		/*logger.debug(simpleDate.format(tmpDate.getMillis())+" - "+simpleDate.format(timeServer.getMillis()));
			logger.debug("Per una differenza di "+days.getDays()+" giorni");*/
    	}
	}
	
	
	public void checkLicenzaUtente(List<Licenzautente> licenzeUtenti, DateTime timeServer){
		/*CONTROLLO Licenzautente CON DATA ATTUALE*/
    	Licenzautente tmpLicUte;
    	Iterator<Licenzautente> itrLicUte = licenzeUtenti.iterator();
    	while(itrLicUte.hasNext()){
    		tmpLicUte = itrLicUte.next();
    		DateTime tmpDate = new DateTime(tmpLicUte.getDataCessazione().getTime());
    		Days days = Days.daysBetween(timeServer, tmpDate);
    		
    		//TODO: Questo caso non dovrebbe verificarsi. 
    		if(days.getDays() < 0){
    			/* SETTO LO STATO */
    			tmpLicUte.setTabellastati(statoService.getByName("LIU2"));
    		}
    		
    		if(days.getDays() > 0 && days.getDays() <= 30){
    			/* SETTO LO STATO */
    			/**
    			 * TODO: Creo avviso - Licenzainscadenza.
    			 * */
    		}
    		
    		/*logger.debug(simpleDate.format(tmpDate.getMillis())+" - "+simpleDate.format(timeServer.getMillis()));
			logger.debug("Per una differenza di "+days.getDays()+" giorni");*/
    	}
	}
	
	public void checkLicenzaCpe(List<Licenzacpe> licenzeCpe, DateTime timeServer){
		/*CONTROLLO Licenzacpe CON DATA ATTUALE*/
    	Licenzacpe tmpLicCpe;
    	Iterator<Licenzacpe> itrLicCpe = licenzeCpe.iterator();
    	while(itrLicCpe.hasNext()){
    		tmpLicCpe = itrLicCpe.next();
    		DateTime tmpDate = new DateTime(tmpLicCpe.getDataCessazione().getTime());
    		Days days = Days.daysBetween(timeServer, tmpDate);
    		
    		//TODO: Questo caso non dovrebbe verificarsi. 
    		if(days.getDays() < 0){
    			/* SETTO LO STATO */
    			tmpLicCpe.setTabellastati(statoService.getByName("LicenzaCpe_Scaduta"));
    			
    		}
    		
    		if(days.getDays() > 0 && days.getDays() <= 30){
    			/* SETTO LO STATO */
    			/**
    			 * TODO: Creo avviso - Licenzainscadenza.
    			 * */
    		}
    		
    		/*logger.debug(simpleDate.format(tmpDate.getMillis())+" - "+simpleDate.format(timeServer.getMillis()));
			logger.debug("Per una differenza di "+days.getDays()+" giorni");*/
    	}
	}
}