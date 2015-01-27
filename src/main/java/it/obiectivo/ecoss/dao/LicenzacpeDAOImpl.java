package it.obiectivo.ecoss.dao;

import it.obiectivo.ecoss.domain.Licenzacpe;

import java.util.List;

import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository("licenzacpeDAO")
public class LicenzacpeDAOImpl implements LicenzacpeDAO{
	
	protected static Logger logger = Logger.getLogger("service");
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	//Ritorna una lista di Licenzacpe
	@SuppressWarnings("unchecked")
	public List<Licenzacpe> getAll() {
		logger.debug("LicenzacpeDAO - Ricerca delle Licenzecpe");
		
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Creo la query usando il linguaggio HQL (Hibernate Query Language)
		Query query = session.createQuery("FROM  Licenzacpe");
			
		//Restituisco la lista di Cpe
		return  query.list();
	}
	
	//Ritorna una lista di Istanzautentecpe sulla base del valore assunto dallo stato
	@SuppressWarnings("unchecked")
	public List<Licenzacpe> getByStato(String stato) {
		
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Creo la query usando il linguaggio HQL (Hibernate Query Language)
		Query query = session.getNamedQuery("Licenzacpe.findByStato").setParameter("stato", stato);
			
		//Restituisco la lista di Credenzialiutente
		return  query.list();
	}
		
	//Ritrova una singola Licenzacpe
	public Licenzacpe get( Integer id ) {
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Recupero la Licenzacpe
		Licenzacpe licenzacpe = (Licenzacpe) session.get(Licenzacpe.class, id);
		
		//Restituisco la Licenzacpe
		return licenzacpe;
	}
		
	//Aggiungi un nuovo Licenzacpe
	public void add(Licenzacpe licenzacpe) {
		logger.debug("Aggiungo una nuova Licenzacpe");
			
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Salvo la Licenzacpe
		session.save(licenzacpe);
	}
		
	//Elimina un Cpe
	public void delete(Integer id) {
		logger.debug("Eliminazione di un Licenzacpe");
		
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		//Recupero la Licenzacpe
		Licenzacpe licenzacpe = (Licenzacpe) session.get(Licenzacpe.class, id);
			
		//Elimino la Licenzacpe
		session.delete(licenzacpe);
		session.flush();
	}
		
	//Modifica di una Licenzacpe
	public void edit(Licenzacpe licenzacpe) {
		logger.debug("Modifica di una Licenzacpe");
			
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Ritrova la Licenzacpe da modificare
		Licenzacpe existingLicCpe = (Licenzacpe) session.get(Licenzacpe.class, licenzacpe.getId());
			
		//Modifico gli attributi
		existingLicCpe.setDataAttivazione(licenzacpe.getDataAttivazione());
		existingLicCpe.setDataCessazione(licenzacpe.getDataCessazione());
		existingLicCpe.setLicCpeNome(licenzacpe.getLicCpeNome());
		existingLicCpe.setTabellastati(licenzacpe.getTabellastati());
		existingLicCpe.setCpe(licenzacpe.getCpe());
			
		//Salvo le modifiche
		session.save(existingLicCpe);
		session.flush();
	}
}
