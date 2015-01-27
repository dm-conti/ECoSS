package it.obiectivo.ecoss.dao;

import it.obiectivo.ecoss.domain.Utente;

import java.util.List;
import java.util.Date;

import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("utenteDAO")
public class UtenteDAOImpl implements UtenteDAO{
	
	protected static Logger logger = Logger.getLogger("service");
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	//Ritorna una lista di Utenti
	@SuppressWarnings("unchecked")
	public List<Utente> getAll() {
		logger.debug("--- DAO Layer ---");
		logger.debug("Ricerca di tutti gli Utenti");
		
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Creo la query usando il linguaggio HQL (Hibernate Query Language)
		Query query = session.createQuery("FROM  Utente");
		
		logger.debug("Query appena effettuata FROM Utente");
			
		//Restituisco la lista di utenti
		return  query.list();
	}
		
	//Ritrova una singola persona
	public Utente get( Integer id ) {
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Recupero l'utente
		Utente utente = (Utente) session.get(Utente.class, id);
		
		//Restituisco l'utente trovato
		return utente;
	}
		
	//Aggiungi un nuovo utente
	public void add(Utente utente) {
		logger.debug("Aggiungo un nuovo Utente");
			
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
		utente.setUteDataCreazione(new Date());
			
		//Salvo l'utente
		session.save(utente);
	}
		
	//Elimina un Utente
	public void delete(Integer id) {
		logger.debug("Eliminazione di un Utente");
		
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		//Recupero l'utente
		Utente utente = (Utente) session.get(Utente.class, id);
			
		//Elimino l'utente trovato
		session.delete(utente);
		session.flush();
	}
		
	//Modifica da Utente
	public void edit(Utente utente) {
		logger.debug("Modifica un Utente");
			
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Ritrova l'utente
		Utente existingUtente = (Utente) session.get(Utente.class, utente.getIdUtente());
			
		//Modifico gli attributi
		existingUtente.setUteNome(utente.getUteNome());
		existingUtente.setUteCognome(utente.getUteCognome());
		existingUtente.setUteRagSociale(utente.getUteRagSociale());
		existingUtente.setUteDescrizione(utente.getUteDescrizione());
		existingUtente.setUteIndirizzo(utente.getUteIndirizzo());
		existingUtente.setUteCitta(utente.getUteCitta());
		existingUtente.setUteCap(utente.getUteCap());
		existingUtente.setUteTel1(utente.getUteTel1());
		existingUtente.setUteTel2(utente.getUteTel2());
		existingUtente.setUteFax(utente.getUteFax());
		existingUtente.setUteEmail(utente.getUteEmail());
		existingUtente.setUteCF(utente.getUteCF());
		existingUtente.setUtePIVA(utente.getUtePIVA());
		existingUtente.setUteEmail2(utente.getUteEmail2());
		//xistingUtente.setGestore(utente.getGestore());
			
		//Salvo le modifiche
		session.save(existingUtente);
	}	
}