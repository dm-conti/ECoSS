package it.obiectivo.ecoss.service;

import java.util.Date;

import it.obiectivo.ecoss.dao.CredenzialiutenteDAO;
import it.obiectivo.ecoss.dao.DistributoreDAO;
import it.obiectivo.ecoss.dao.GestoreDAO;
import it.obiectivo.ecoss.dao.UtenteDAO;
import it.obiectivo.ecoss.domain.Credenzialiutente;
import it.obiectivo.ecoss.domain.Distributore;
import it.obiectivo.ecoss.domain.Gestore;
import it.obiectivo.ecoss.domain.Utente;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("accessService")
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
public class AccessService {
protected static Logger logger = Logger.getLogger("gestoreService");
	
	@Autowired
    private GestoreDAO gestoreDAO;
	
	@Autowired
    private UtenteDAO utenteDAO;
	
	@Autowired
    private DistributoreDAO distributoreDAO;
	
	@Autowired
    private CredenzialiutenteDAO credenzialiutenteDAO;
	
	
	/* ------------------------------------------ *
	 * METODI PER L'Aggiunta DEGLI Accessi    *
	 * ------------------------------------------ */
	
	
	//Aggiungo un nuovo Accesso (Gestore + Credenzialiutente)
	/*PARAMETRI DI INPUT: Entity, String, String*/
	
	/**
	 * @todo
	 * Il metodo attualmente è reso ATOMICO attraverso l'uso di istruzioni 
	 * esplicite come: begin(), commit() e rollback(). Lo stesso dovrebbe 
	 * integrare le stesse funzionalità in automatico grazie all'utilizzo
	 * dell'annotazione: @Transactional o delle sue varianti 
	 * es. @Transactional( propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = true)
	 */
	public Boolean add(Gestore gestore, Credenzialiutente credenziali) throws Exception {
		logger.debug("MakeAccessService: - creazione di un nuovo accesso x Gestore");
		
		Date dataInserimento = new Date();
		gestore.setGestDataCreazione(dataInserimento);
		
		credenziali.setGestore(gestore);
		credenziali.setDataInserimento(dataInserimento);
		credenzialiutenteDAO.add(credenziali);
		return true;
	}
	
	//Aggiungo un nuovo Accesso (Distributore + Credenzialiutente)
	public Boolean add(Distributore distributore, Credenzialiutente credenziali) {
		logger.debug("MakeAccessService: - creazione di un nuovo accesso x Distributore");
		
		Gestore gestore = gestoreDAO.getByRagSociale(distributore.getGestore().getGestRagSociale());
		distributore.setGestore(gestore);
		
		
		Date dataInserimento = new Date();
		distributore.setDistDataCreazione(dataInserimento);
		
		credenziali.setDistributore(distributore);
		credenziali.setDataInserimento(dataInserimento);
		credenzialiutenteDAO.add(credenziali);
		return true;
	}
	
	/** @todo
	 * Dovrei trovare un sistema per poter ritornare, in caso di errore, il valore booleano. 
	 * In particolare almeno per adesso, dalle ricerche effettuate, sembrerebbe che per poter
	 * far funzionare correttamente il meccanismo delle transizioni, non si può usare il
	 * try\catch statemen.
	 * **/
	//Aggiungo un nuovo Accesso (Utente + Credenzialiutente)
	public Boolean add(Utente utente, Credenzialiutente credenziali) {
		logger.debug("MakeAccessService: - creazione di un nuovo accesso per l'utente "+credenziali.getUserId());
		
		Gestore gestore = gestoreDAO.getByRagSociale(utente.getGestore().getGestRagSociale());
		utente.setGestore(gestore);
		
		String distRagSociale = utente.getDistributore().getDistRagSociale();
		if(!distRagSociale.equals(null)){
			Distributore distributore = distributoreDAO.getByRagSociale(utente.getDistributore().getDistRagSociale());
			utente.setDistributore(distributore);
		}
		
		Date dataInserimento = new Date();
		utente.setUteDataCreazione(dataInserimento);
		
		/*Relazione Bidirezione OneToOne*/
		credenziali.setUtente(utente);
		utente.setCredenzialiutente(credenziali);
		
		credenziali.setDataInserimento(dataInserimento);
		credenziali.setPrimoAccesso(true);
		credenzialiutenteDAO.add(credenziali);
		return true;
	}
	
	/* ------------------------------------------ *
	 * METODI PER L'Eliminazione DEGLI Accessi    *
	 * ------------------------------------------ */
	
	//Elimina un accesso x Gestore
	public Boolean delete(Gestore gestore) {
		logger.debug("MakeAccessService: - eliminazione di un accesso x Gestore");
		gestoreDAO.delete(gestore.getIdGestore());
		return true;
	}
	
	//Elimina un accesso x Distributore
	public Boolean delete(Distributore distributore) {
		logger.debug("MakeAccessService: - eliminazione di un accesso x Distributore");
		distributoreDAO.delete(distributore.getIdDistributore());
		return true;
	}
	
	// Elimina un accesso x Utente
	public Boolean delete(Utente utente) {
		logger.debug("MakeAccessService: - eliminazione di un accesso x Utente");
		utenteDAO.delete(utente.getIdUtente());
		return true;
	}
}