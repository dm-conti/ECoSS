package it.obiectivo.ecoss.dao;

import it.obiectivo.ecoss.domain.Notifiche;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository("notificheDAO")
public class NotificheDAOImpl implements NotificheDAO{
protected static Logger logger = Logger.getLogger("service");
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Notifiche> getAll(){
		logger.debug("NotificheDAO - getAll");
		
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Creo la query usando il linguaggio HQL (Hibernate Query Language)
		Query query = session.createQuery("FROM  Notifiche");
			
		//Restituisco la lista di Cpe
		return  query.list();
	}
	
	public Notifiche get(int id){
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Recupero il cpe
		Notifiche notifica = (Notifiche) session.get(Notifiche.class, id );
		
		//Restituisco il cpe trovato
		return notifica;
	}
	
	public Notifiche getRow(int idTabella, String nomeTabella, String codNotifica){
		
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.getNamedQuery("Notifiche.findRow");
		query.setString("idTabella", Integer.toString(idTabella));
		query.setString("nomeTabella", nomeTabella);
		query.setString("codNotifica", codNotifica);
		
		Notifiche notifica = (Notifiche) query.setMaxResults(1).uniqueResult();
		
		return notifica;
	}
	
	public void add(Notifiche notifica){
		logger.debug("Aggiungo una notifica");
		
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Salvo l'utente
		session.save(notifica);
	}
	
	public void delete(int id){
		logger.debug("NotificheDAO - Eliminazione di una Notifica");
		
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		//Recupero la Notifica
		Notifiche notifica = (Notifiche) session.get(Notifiche.class, id);
			
		//Elimino il Cpe trovato
		session.delete(notifica);
		session.flush();
	}
	
	public void edit(Notifiche notifica){
		
	}
}