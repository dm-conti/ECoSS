package it.obiectivo.ecoss.dao;

import it.obiectivo.ecoss.domain.Profilo;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository("profiloDAO")
public class ProfiloDAOImpl implements ProfiloDAO{
	protected static Logger logger = Logger.getLogger("profiloDAO");
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	//Ritorna una lista di Profili
	@SuppressWarnings("unchecked")
	public List<Profilo> getAll() {
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Creo la query usando il linguaggio HQL (Hibernate Query Language)
		Query query = session.createQuery("FROM  Vocelicenza");
			
		//Restituisco la lista di Profili
		return  query.list();
	}
		
	//Ritrova un Profilo dall'id
	public Profilo get( Integer id ) {
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Recupero il Profilo
		Profilo profilo = (Profilo) session.get(Profilo.class, id);
		
		//Restituisco il Profilo trovato
		return profilo;
	}
	
	//Ritrova un Profilo dalla descrizione
	public Profilo getByProfDescrizione(String descrizione){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("Profilo.findByProfDescrizione").setString("profDescrizione", descrizione);
		
		Profilo profilo = (Profilo) query.setMaxResults(1).uniqueResult();
		
		return profilo;
	}
		
	//Aggiungi un nuovo Profilo
	public void add(Profilo profilo) {
		logger.debug("Aggiungo un nuovo Profilo");
			
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Salvo il Profilo
		session.save(profilo);
	}
		
	//Elimino un Profilo
	public void delete(Integer id) {
		logger.debug("Elimino un Profilo");
		
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		//Recupero il Profilo
		Profilo profilo = (Profilo) session.get(Profilo.class, id);
			
		//Elimino il Profilo trovato
		session.delete(profilo);
		session.flush();
	}
		
	//Modifico il Profilo
	public void edit(Profilo profilo) {
		logger.debug("Modifico un Profilo");
			
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Ritrova il Profilo da modificare
		Profilo exProfilo = (Profilo) session.get(Profilo.class, profilo.getIdProfilo());
			
		//Modifico gli attributi
		exProfilo.setCredenzialiutenteList(profilo.getCredenzialiutenteList());
		exProfilo.setFunzraggruppateList(profilo.getFunzraggruppateList());
		exProfilo.setProfDescrizione(profilo.getProfDescrizione());
		
		//Salvo le modifiche
		session.save(exProfilo);
		session.flush();
	}	
}