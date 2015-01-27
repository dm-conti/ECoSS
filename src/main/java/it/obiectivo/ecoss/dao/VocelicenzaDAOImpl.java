package it.obiectivo.ecoss.dao;

import it.obiectivo.ecoss.domain.Vocelicenza;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository("vocelicenzaDAO")
public class VocelicenzaDAOImpl implements VocelicenzaDAO{
protected static Logger logger = Logger.getLogger("service");
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	//Ritorna una lista di Vocelicenza
	@SuppressWarnings("unchecked")
	public List<Vocelicenza> getAll() {
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Creo la query usando il linguaggio HQL (Hibernate Query Language)
		Query query = session.createQuery("FROM  Vocelicenza");
			
		//Restituisco la lista di Vocelicenza
		return  query.list();
	}
		
	//Ritrova una singola Vocelicenza dall'id
	public Vocelicenza get( Integer id ) {
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Recupero la Vocelicenza
		Vocelicenza vocelicenza = (Vocelicenza) session.get(Vocelicenza.class, id);
		
		//Restituisco la Vocelicenza trovata
		return vocelicenza;
	}
	
	//Ritrova una singola Vocelicenza dal nome
	public Vocelicenza getByName(String nomeLicenza){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("Vocelicenza.findByName").setString("nomeLicenza", nomeLicenza);
		
		Vocelicenza voiceResult = (Vocelicenza) query.setMaxResults(1).uniqueResult();
		
		return voiceResult;
	}
		
	//Aggiungi una nuova Vocelicenza
	public void add(Vocelicenza vocelicenza) {
		logger.debug("Aggiungo una nuova Vocelicenza");
			
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Salvo l'Vocelicenza
		session.save(vocelicenza);
	}
		
	//Elimina un Vocelicenza
	public void delete(Integer id) {
		logger.debug("Elimina una Vocelicenza");
		
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		//Recupero la Vocelicenza
		Vocelicenza vocelicenza = (Vocelicenza) session.get(Vocelicenza.class, id);
			
		//Elimino la Vocelicenza trovata
		session.delete(vocelicenza);
		session.flush();
	}
		
	//Modifica da Vocelicenza
	public void edit(Vocelicenza vocelicenza) {
		logger.debug("Modifica un Vocelicenza");
			
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Ritrova l'Vocelicenza
		Vocelicenza existingVocelicenza = (Vocelicenza) session.get(Vocelicenza.class, vocelicenza.getIdVoceLicenza());
			
		//Modifico gli attributi
		existingVocelicenza.setGruppolicenzecommercialiList(vocelicenza.getGruppolicenzecommercialiList());
		existingVocelicenza.setServizilicenzacpeList(vocelicenza.getServizilicenzacpeList());
		existingVocelicenza.setVoceLicenza(vocelicenza.getVoceLicenza());
			
		//Salvo le modifiche
		session.save(existingVocelicenza);
		session.flush();
	}	
}