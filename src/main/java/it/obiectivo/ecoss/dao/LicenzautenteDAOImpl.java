package it.obiectivo.ecoss.dao;

import it.obiectivo.ecoss.domain.Licenzautente;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository("licenzautenteDAO")
public class LicenzautenteDAOImpl implements LicenzautenteDAO{
protected static Logger logger = Logger.getLogger("service");
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	//Ritorna una lista di Licenzautentepe
	@SuppressWarnings("unchecked")
	public List<Licenzautente> getAll() {
		logger.debug("LicenzautenteDAO - Ricerca di Licenzautente");
		
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Creo la query usando il linguaggio HQL (Hibernate Query Language)
		Query query = session.createQuery("FROM  Licenzautente");
			
		//Restituisco la lista di Licenzautente
		return  query.list();
	}
	
	//Ritorna una lista di Istanzautentecpe sulla base del valore assunto dallo stato
	@SuppressWarnings("unchecked")
	public List<Licenzautente> getByStato(String stato) {
		
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Creo la query usando il linguaggio HQL (Hibernate Query Language)
		Query query = session.getNamedQuery("Licenzautente.findByStato").setParameter("stato", stato);
			
		//Restituisco la lista di Credenzialiutente
		return  query.list();
	}
		
	//Ritrova una singola Licenzautente
	public Licenzautente get( Integer id ) {
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Recupero la singola Licenzautente
		Licenzautente licenzautente = (Licenzautente) session.get(Licenzautente.class, id);
		
		//Restituisco il cpe trovato
		return licenzautente;
	}
		
	//Aggiungi una nuova Licenzautente
	public void add(Licenzautente licenzautente) {
		logger.debug("Aggiungo una nuova Licenzautente");
			
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Salvo l'utente
		session.save(licenzautente);
	}
		
	//Elimina una Licenzautente
	public void delete(Integer id) {
		logger.debug("Eliminazione di una Licenzautente");
		
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		//Recupero la Licenzautente
		Licenzautente licenzautente = (Licenzautente) session.get(Licenzautente.class, id);
			
		//Elimino la Licenzautente trovata
		session.delete(licenzautente);
		session.flush();
	}
		
	//Modifica di una Licenzautente
	public void edit(Licenzautente licenzautente) {
		logger.debug("Modifica di una Licenzautente");
			
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Ritrova la Licenzautente da modificare
		Licenzautente existingLicUte = (Licenzautente) session.get(Licenzautente.class, licenzautente.getId());
			
		//Modifico gli attributi
		existingLicUte.setIstanzautentecpeList(licenzautente.getIstanzautentecpeList());
		existingLicUte.setLicenzecommerciali(licenzautente.getLicenzecommerciali());
		existingLicUte.setDataAttivazione(licenzautente.getDataAttivazione());
		existingLicUte.setDataCessazione(licenzautente.getDataCessazione());
		existingLicUte.setLicUteDescrizione(licenzautente.getLicUteDescrizione());
		existingLicUte.setTabellastati(licenzautente.getTabellastati());
			
		//Salvo le modifiche
		session.save(existingLicUte);
		session.flush();
	}

}
