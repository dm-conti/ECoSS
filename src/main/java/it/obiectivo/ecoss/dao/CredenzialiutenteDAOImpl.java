package it.obiectivo.ecoss.dao;

import it.obiectivo.ecoss.domain.Credenzialiutente;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository("credenzialiutenteDAO")
public class CredenzialiutenteDAOImpl implements CredenzialiutenteDAO{
protected static Logger logger = Logger.getLogger("credenzialiutenteDAO");
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	//Ritorna una lista di Credenzialiutente
	@SuppressWarnings("unchecked")
	public List<Credenzialiutente> getAll() {
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Creo la query usando il linguaggio HQL (Hibernate Query Language)
		Query query = session.createQuery("FROM  Credenzialiutente");
			
		//Restituisco la lista di Credenzialiutente
		return  query.list();
	}
	
	//Ritorna una lista di Credenzialiutente sulla base del valore assunto da primoAccesso
	@SuppressWarnings("unchecked")
	public List<Credenzialiutente> getByPrimoAccesso(Boolean primoAccesso) {
		
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Creo la query usando il linguaggio HQL (Hibernate Query Language)
		Query query = session.createQuery("Credenzialiutente.findByPrimoAccesso").setString("primoAccesso", primoAccesso.toString());
			
		//Restituisco la lista di Credenzialiutente
		return  query.list();
	}
		
	//Ritrova una singola istanza di Credenzialiutente dall'id
	public Credenzialiutente get( Integer id ) {
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Recupero una ricorrenza di Credenzialiutente
		Credenzialiutente credenziali = (Credenzialiutente) session.get(Credenzialiutente.class, id);
		
		//Restituisco le Credenzialiutente trovate
		return credenziali;
	}
	
	//Ritrova una ricorrenza di Credenzialiutente dall'userId
	public Credenzialiutente getByUserId(String userid){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("Credenzialiutente.findByUserId").setString("userId", userid);
		
		Credenzialiutente credenziali = (Credenzialiutente) query.setMaxResults(1).uniqueResult();
		
		return credenziali;
	}
		
	//Aggiungi una nuova ricorrenza di Credenzialiutente
	public void add(Credenzialiutente credenziali) {
		logger.debug("Aggiungo Credenzialiutente");
		credenziali.setDataInserimento(new Date());	
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
		Date dataInserimento = credenziali.getDataInserimento();
		if(dataInserimento == null){
			credenziali.setDataInserimento(new Date());
		}
		//Salvo le Credenzialiutente
		session.save(credenziali);
		session.flush();
	}
		
	//Elimina una ricorrenza di Credenzialiutente
	public void delete(Integer id) {
		logger.debug("Elimina una ricorrenza di Credenzialiutente");
		
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		//Recupero la ricorrenza da eliminare
		Credenzialiutente credenziali = (Credenzialiutente) session.get(Credenzialiutente.class, id);
			
		//Elimino la ricorrenza di Credenzialiutente
		session.delete(credenziali);
		session.flush();
	}
		
	//Modifica Credenzialiutente
	public void edit(Credenzialiutente credenziali) {
		logger.debug("Modifica Credenzialiutente");
			
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Ritrova le Credenzialiutente
		Credenzialiutente exCredenziali = (Credenzialiutente) session.get(Credenzialiutente.class, credenziali.getIdCredenzialiUtente());
			
		//Modifico gli attributi
		exCredenziali.setUserId(credenziali.getUserId());
		exCredenziali.setPassword(credenziali.getPassword());
		exCredenziali.setDataInserimento(credenziali.getDataInserimento());
		exCredenziali.setProfilo(credenziali.getProfilo());
		exCredenziali.setPrimoAccesso(credenziali.getPrimoAccesso());
		
		
			
		//Salvo le modifiche
		session.save(exCredenziali);
		session.flush();
	}	
}