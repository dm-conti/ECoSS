package it.obiectivo.ecoss.service;

import it.obiectivo.ecoss.dao.CredenzialiutenteDAO;
import it.obiectivo.ecoss.domain.Credenzialiutente;
import it.obiectivo.ecoss.domain.Profilo;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("credenzialiutenteService")
@Transactional
public class CredenzialiutenteServiceImpl implements CredenzialiutenteService{
	
	protected static Logger logger = Logger.getLogger("service");
	
	@Autowired
    private CredenzialiutenteDAO credenzialiDAO;
	
	//Ritorna una lista di Credenzialiutente
	public List<Credenzialiutente> getAll() {
		logger.debug("--- Service Layer ---");
		return  credenzialiDAO.getAll();
	}
	
	//Ritrova una lista di Credenzialiutente dal valore assunto da primoAccesso
	public List<Credenzialiutente> getByPrimoAccesso( Boolean primoAccesso ){
		return credenzialiDAO.getByPrimoAccesso(primoAccesso);
	}
	
	//Ritrova una singola ricorrenza di Credenzialiutente
	public Credenzialiutente get( Integer id ) {
		logger.debug("--- Service Layer ---");
		return credenzialiDAO.get(id);
	}
	
	//Ritrova una singola istanza di Credenzialiutente dall'userId
	public Credenzialiutente getByUserId( String userId ){
		return credenzialiDAO.getByUserId(userId);
	}
	
	/*Restituisce le credenziali FITTIZIE (xke' non completamente valorizzate) di un nuovo Gestore, SubGestore o Utente (in base al profilo) */
	public Credenzialiutente makeCredenziali(String userId, Profilo ruolo) {
		logger.debug("CredenzialiutenteService: makeCredenziali(String userId, Profilo ruolo)");
		Credenzialiutente tmpCredenziali = new Credenzialiutente();
		tmpCredenziali.setUserId(userId);
		tmpCredenziali.setPassword(Integer.toString((int)(Math.random() * 1000000) + 1000000));
		tmpCredenziali.setProfilo(ruolo);
		
		return tmpCredenziali;
	}
	
	/*//Restituisce le credenziali di un nuovo Distributore
	public Credenzialiutente makeCredenziali(String userId, Profilo ruolo, Distributore distributore) {
		logger.debug("CredenzialiutenteService: makeCredenziali(String userId, Profilo ruolo, Distributore distributore)");
		Credenzialiutente tmpCredenziali = new Credenzialiutente();
		tmpCredenziali.setUserId(userId);
		tmpCredenziali.setDataInserimento(new Date());
		tmpCredenziali.setPassword(Integer.toString((int)(Math.random() * 1000000) + 1000000));
		tmpCredenziali.setProfilo(ruolo);
		
		return tmpCredenziali;
	}*/
	
	//Aggiungi un nuovo utente
	public Boolean add(Credenzialiutente credenziali) {
		logger.debug("CredenzialiutenteService: add(Credenzialiutente credenziali)");
		try {
			credenzialiDAO.add(credenziali);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//Elimina una istanza di Credenzialiutente
	public Boolean delete(Integer id) {
		logger.debug("--- Service Layer ---");
		try {
			credenzialiDAO.delete(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	//Modifica un istanza di Credenzialiutente
	public Boolean edit(Credenzialiutente credenziali) {
		logger.debug("--- Service Layer ---");
		try {
			credenzialiDAO.edit(credenziali);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}