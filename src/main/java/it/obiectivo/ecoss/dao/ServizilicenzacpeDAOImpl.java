package it.obiectivo.ecoss.dao;

import it.obiectivo.ecoss.domain.Servizilicenzacpe;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository("servizilicenzacpeDAO")
public class ServizilicenzacpeDAOImpl implements ServizilicenzacpeDAO{
protected static Logger logger = Logger.getLogger("servizilicenzacpeDAO");
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	//Ritorna una lista di Servizilicenzacpe
	@SuppressWarnings("unchecked")
	public List<Servizilicenzacpe> getAll() {
		logger.debug("ServizilicenzacpeDAO - Ricerca dei Servizilicenzacpe");
		
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Creo la query usando il linguaggio HQL (Hibernate Query Language)
		Query query = session.createQuery("FROM  Servizilicenzacpe");
			
		//Restituisco la lista di Servizilicenzacpe
		return  query.list();
	}
	
	//Ritorna una lista di Istanzautentecpe sulla base del valore assunto dallo stato
	@SuppressWarnings("unchecked")
	public List<Servizilicenzacpe> getByStato(String stato) {
		
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Creo la query usando il linguaggio HQL (Hibernate Query Language)
		Query query = session.getNamedQuery("Servizilicenzacpe.findByStato").setParameter("stato", stato);
			
		//Restituisco la lista di Credenzialiutente
		return  query.list();
	}
		
	//Ritrova un singolo Servizilicenzacpe
	public Servizilicenzacpe get( Integer id ) {
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Recupero il Servizilicenzacpe
		Servizilicenzacpe servizio = (Servizilicenzacpe) session.get(Servizilicenzacpe.class, id);
		
		//Restituisco il Servizilicenzacpe trovato
		return servizio;
	}
	
	//Ritrova Servizilicenzacpe tramite l'id di
	public Servizilicenzacpe getByIdServiziLicenzaCPE(Integer idServiziLicenzaCPE){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("Servizilicenzacpe.findByIdServiziLicenzaCPE").setInteger("idServiziLicenzaCPE", idServiziLicenzaCPE);
		
		Servizilicenzacpe servizi = (Servizilicenzacpe) query.setMaxResults(1).uniqueResult();
		
		return servizi;
	}
		
	//Aggiungi un nuovo Servizilicenzacpe
	public void add(Servizilicenzacpe servizio) {
		logger.debug("Aggiungo un nuovo Servizilicenzacpe");
			
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Salvo l'utente
		session.save(servizio);
	}
		
	//Elimina un Servizilicenzacpe
	public void delete(Integer id) {
		logger.debug("Eliminazione di un Servizilicenzacpe");
		
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		//Recupero il Servizilicenzacpe
		Servizilicenzacpe servizio = (Servizilicenzacpe) session.get(Servizilicenzacpe.class, id);
			
		//Elimino il Servizilicenzacpe trovato
		session.delete(servizio);
		session.flush();
	}
		
	//Modifica di un Servizilicenzacpe
	public void edit(Servizilicenzacpe servizio) {
		logger.debug("Modifica un Servizilicenzacpe");
			
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Ritrova il Servizilicenzacpe da modificare
		Servizilicenzacpe existingServizio = (Servizilicenzacpe) session.get(Servizilicenzacpe.class, servizio.getId());
			
		//Modifico gli attributi
		existingServizio.setLicenzacpe(servizio.getLicenzacpe());
		existingServizio.setDataAttivazione(servizio.getDataAttivazione());
		existingServizio.setDataCessazione(servizio.getDataCessazione());
		existingServizio.setTabellastati(servizio.getTabellastati());
		existingServizio.setVocelicenza(servizio.getVocelicenza());
		
		//Salvo le modifiche
		session.save(existingServizio);
		session.flush();
	}
}