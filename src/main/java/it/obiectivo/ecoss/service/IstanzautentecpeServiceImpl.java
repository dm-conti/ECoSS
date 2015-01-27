package it.obiectivo.ecoss.service;

import it.obiectivo.ecoss.dao.IstanzautentecpeDAO;
import it.obiectivo.ecoss.dao.TabellastatiDAO;
import it.obiectivo.ecoss.domain.Credenzialiutente;
import it.obiectivo.ecoss.domain.Istanzautentecpe;
import it.obiectivo.ecoss.domain.Licenzautente;
import it.obiectivo.ecoss.domain.Tabellastati;
import it.obiectivo.ecoss.domain.Utente;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("istanzautentecpeService")
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
public class IstanzautentecpeServiceImpl implements IstanzautentecpeService{
protected static Logger logger = Logger.getLogger("service");
	
	@Autowired
    private IstanzautentecpeDAO istUteCpeDAO;
	
	@Autowired
    private TabellastatiDAO tabellastatiDAO;
	
	//Ritorna una lista di Istanzautentecpe
	public List<Istanzautentecpe> getAll() {
		logger.debug("--- Service Layer ---");
		return istUteCpeDAO.getAll();
	}
	
	//Ritorna una lista di Istanzautentecpe discriminate per stato
	public List<Istanzautentecpe> getByStato(String stato) {
		logger.debug("--- Service Layer ---");
		return istUteCpeDAO.getByStato(stato);
	}
	
	//Ritrova una singola Istanzautentecpe
	public Istanzautentecpe get( Integer id ) {
		logger.debug("--- Service Layer ---");
		return istUteCpeDAO.get(id);
	}
	
	//Aggiungi una nuova Istanzautentecpe
	public Boolean add(Istanzautentecpe istanzautentecpe) {
		logger.debug("IstanzautenteService: invocato metodo add(contratto) ");
		
		Licenzautente licUte = istanzautentecpe.getLicenzautente();
		Credenzialiutente credenziali = istanzautentecpe.getUtente().getCredenzialiutente();
		Date dataTermine = istanzautentecpe.getDataCessazione();
		Date dataInserimento = new Date();
		
		licUte.setDataAttivazione(dataInserimento);
		licUte.setDataCessazione(dataTermine);
		licUte.setLicUteDescrizione("Licenza_"+credenziali.getUserId());
		licUte.setTabellastati(tabellastatiDAO.getByName("LIU0"));
		
		istanzautentecpe.setLicenzautente(licUte);
		istanzautentecpe.setDataAttivazione(dataInserimento);
				
		//ASSEGNO UNO STATO ALL'ISTANZA
		Tabellastati stato = tabellastatiDAO.getByName("IUC0");
		istanzautentecpe.setTabellastati(stato);
		
		//ASSEGNO UN numeroContratto FITTIZIO
		istanzautentecpe.setNumeroContratto("NUMBER" + (Integer.toString((int)(Math.random() * 100000000) + 100000000)));
		
		try {
			istUteCpeDAO.add(istanzautentecpe);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//Aggiungi una nuova Istanzautentecpe (Nuovo Utente)
	public Boolean add(Istanzautentecpe istanzautentecpe, Credenzialiutente credenziali) {
		logger.debug("IstanzautenteService: invocato metodo add(contratto, credenziali) ");
		
		Utente utente = istanzautentecpe.getUtente();
		Licenzautente licUte = istanzautentecpe.getLicenzautente();
		Date dataTermine = istanzautentecpe.getDataCessazione();
		Date dataInserimento = new Date();
		
		credenziali.setDataInserimento(dataInserimento);
		credenziali.setPrimoAccesso(true);
		credenziali.setUtente(utente);
		
		utente.setUteDataCreazione(dataInserimento);
		utente.setUteDataCreazione(dataTermine);
		utente.setCredenzialiutente(credenziali);
		
		licUte.setDataAttivazione(dataInserimento);
		licUte.setDataCessazione(dataTermine);
		licUte.setLicUteDescrizione("Licenza_"+credenziali.getUserId());
		licUte.setTabellastati(tabellastatiDAO.getByName("LIU0"));
		
		istanzautentecpe.setUtente(utente);
		istanzautentecpe.setLicenzautente(licUte);
		istanzautentecpe.setDataAttivazione(dataInserimento);
				
		//ASSEGNO UNO STATO ALL'ISTANZA
		Tabellastati stato = tabellastatiDAO.getByName("IUC0");
		istanzautentecpe.setTabellastati(stato);
		
		//ASSEGNO UN numeroContratto FITTIZIO
		istanzautentecpe.setNumeroContratto("NUMBER" + (Integer.toString((int)(Math.random() * 100000000) + 100000000)));
		
		try {
			istUteCpeDAO.add(istanzautentecpe);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//Elimina una Istanzautentecpe
	public Boolean delete(Integer id) {
		logger.debug("--- Service Layer ---");
		try {
			istUteCpeDAO.delete(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	//Modifica una Istanzautentecpe
	public Boolean edit(Istanzautentecpe istanzautentecpe) {
		logger.debug("--- Service Layer ---");
		try {
			istUteCpeDAO.edit(istanzautentecpe);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}