package it.obiectivo.ecoss.service;

import it.obiectivo.ecoss.dao.UtenteDAO;
import it.obiectivo.ecoss.domain.Utente;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service("utenteService")
@Transactional
public class UtenteServiceImpl implements UtenteService{

	protected static Logger logger = Logger.getLogger("service");
	
	@Autowired
    private UtenteDAO utenteDAO;
	
	//Ritorna una lista di Utenti
	public List<Utente> getAll() {
		logger.debug("--- Service Layer ---");
		return  utenteDAO.getAll();
	}
	
	//Ritrova una singola persona
	public Utente get( Integer id ) {
		logger.debug("--- Service Layer ---");
		return utenteDAO.get(id);
	}
	
	/**
	 * @todo: La ricerca dovrebbe eessere fatta a livello di DB
	 * altrimenti si rischia di appesantire troppo l'applicazione
	 * con un consumo eccessi di memoria. 
	 */
	//Ritrova una lista di Utenti con Contratto
	public List<Utente> getUteWithContract() {
		logger.debug("UtenteService: getUteWithContract");
		List<Utente> utenti = utenteDAO.getAll();
		List<Utente> withContract = new ArrayList<Utente>();
		Iterator<Utente> iterator = utenti.iterator();
		Utente tmpUtente = new Utente();
		
		while(iterator.hasNext()){
			tmpUtente = iterator.next();
			if(tmpUtente.getIstanzautentecpeList().size() == 0){
				withContract.add(tmpUtente);
			}
		}
		
		return withContract;
	}
	
	//Ritrova una lista di Utenti senza Contratto
	public List<Utente> getUteWithoutContract() {
		logger.debug("UtenteService: getUteWithContract");
		List<Utente> utenti = utenteDAO.getAll();
		List<Utente> withoutContract = new ArrayList<Utente>();
		Iterator<Utente> iterator = utenti.iterator();
		Utente tmpUtente = new Utente();
		
		while(iterator.hasNext()){
			tmpUtente = iterator.next();
			if(tmpUtente.getIstanzautentecpeList().size() == 0){
				withoutContract.add(tmpUtente);
			}
		}
		
		return withoutContract;
	}
	
	//Aggiungi un nuovo utente
	public Boolean add(Utente utente) {
		logger.debug("--- Service Layer ---");
		try {
			utenteDAO.add(utente);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	//Elimina un Utente
	public Boolean delete(Integer id) {
		logger.debug("--- Service Layer ---");
		try {
			utenteDAO.delete(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	//Modifica da Utente
	public Boolean edit(Utente utente) {
		logger.debug("--- Service Layer ---");
		try {
			utenteDAO.edit(utente);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}