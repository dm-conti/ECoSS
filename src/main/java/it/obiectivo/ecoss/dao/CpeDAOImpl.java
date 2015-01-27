package it.obiectivo.ecoss.dao;

import it.obiectivo.ecoss.domain.Cpe;

import java.util.List;

import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository("cpeDAO")
public class CpeDAOImpl implements CpeDAO{
	
	protected static Logger logger = Logger.getLogger("service");
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	//Ritorna una lista di Cpe
	@SuppressWarnings("unchecked")
	public List<Cpe> getAll() {
		logger.debug("CpeDAO - Ricerca dei Cpe");
		
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Creo la query usando il linguaggio HQL (Hibernate Query Language)
		Query query = session.createQuery("FROM  Cpe");
			
		//Restituisco la lista di Cpe
		return  query.list();
	}
		
	//Ritrova un singolo cpe
	public Cpe get( Integer id ) {
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Recupero il cpe
		Cpe cpe = (Cpe) session.get(Cpe.class, id);
		
		//Restituisco il cpe trovato
		return cpe;
	}
		
	//Aggiungi un nuovo Cpe
	public void add(Cpe cpe) {
		logger.debug("Aggiungo un nuovo Cpe");
			
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Salvo l'utente
		session.save(cpe);
	}
		
	//Elimina un Cpe
	public void delete(Integer id) {
		logger.debug("Eliminazione di un Cpe");
		
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		//Recupero il Cpe
		Cpe cpe = (Cpe) session.get(Cpe.class, id);
			
		//Elimino il Cpe trovato
		session.delete(cpe);
		session.flush();
	}
		
	//Modifica di un Cpe
	public void edit(Cpe cpe) {
		logger.debug("Modifica un Cpe");
			
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Ritrova il Cpe da modificare
		Cpe existingCpe = (Cpe) session.get(Cpe.class, cpe.getIdCPE());
			
		//Modifico gli attributi
		existingCpe.setCpeNome(cpe.getCpeNome());
		existingCpe.setCpeModello(cpe.getCpeModello());
		existingCpe.setCpeVersCore(cpe.getCpeVersCore());
		existingCpe.setCpeVersCoreProg(cpe.getCpeVersCoreProg());
		existingCpe.setCpeIpAddress(cpe.getCpeIpAddress());
		
		//NON DOVREBBE ESSERE MODIFICABILE
		existingCpe.setCpeMacAddress(cpe.getCpeMacAddress());
		
		existingCpe.setCpeManagementKey(cpe.getCpeManagementKey());
		existingCpe.setCpeDataAttivazione(cpe.getCpeDataAttivazione());
		existingCpe.setTabellastati(cpe.getTabellastati());
			
		//Salvo le modifiche
		session.save(existingCpe);
		session.flush();
	}
}