package it.obiectivo.ecoss.dao;

import it.obiectivo.ecoss.domain.Gruppolicenzecommerciali;

import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository("gruppoLicenzeCommercialiDAO")
public class GruppoLicenzeCommercialiDAOImpl implements GruppoLicenzeCommercialiDAO{
protected static Logger logger = Logger.getLogger("service");
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	//Ritorna una lista di Gruppolicenzecommerciali
	@SuppressWarnings("unchecked")
	public List<Gruppolicenzecommerciali> getAll() {
		logger.debug("--- DAO Layer ---");
		logger.debug("Ricerca di tutti i Gruppi Licenze Commerciali");
		
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Creo la query usando il linguaggio HQL (Hibernate Query Language)
		Query query = session.createQuery("FROM  Gruppolicenzecommerciali");
		
		logger.debug("Query appena effettuata FROM Gruppolicenzecommerciali");
			
		//Restituisco la lista di Gruppilicenzecommerciali
		return  query.list();
	}
		
	//Ritrova un singolo GruppoLicenzeCommerciali
	public Gruppolicenzecommerciali get( Integer id ) {
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Recupero il Gruppolicenzecommerciali
		Gruppolicenzecommerciali Gruppolicenzecommerciali = (Gruppolicenzecommerciali) session.get(Gruppolicenzecommerciali.class, id);
		
		//Restituisco il Gruppolicenzecommerciali trovato
		return Gruppolicenzecommerciali;
	}
	
	/*
	@SuppressWarnings("unchecked")
	public List<Gruppolicenzecommerciali> getByLicenzeCommerciali(Licenzecommerciali licenzeCommerciali){
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Recupero il Gruppolicenzecommerciali
		Query query = session.createQuery("FROM Gruppolicenzecommerciali g where g.licenzecommerciali = :licenzeCommerciali"); 
		List<Gruppolicenzecommerciali> listaGruppoLicCommerciali =  query.list();
		
		//Restituisco il Gruppolicenzecommerciali trovato
		return listaGruppoLicCommerciali;
	}
	*/
	
	//Aggiungi un nuovo Gruppolicenzecommerciali
	public void add(Gruppolicenzecommerciali Gruppolicenzecommerciali) {
		logger.debug("Aggiungo un nuovo Gruppolicenzecommerciali");
			
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Salvo il Gruppolicenzecommerciali
		session.save(Gruppolicenzecommerciali);
	}
		
	//Elimina un Gruppolicenzecommerciali
	public void delete(Integer id) {
		logger.debug("Eliminazione di un Gruppolicenzecommerciali");
		
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		//Recupero l'Gruppolicenzecommerciali
		Gruppolicenzecommerciali Gruppolicenzecommerciali = (Gruppolicenzecommerciali) session.get(Gruppolicenzecommerciali.class, id);
			
		//Elimino il Gruppolicenzecommerciali trovato
		session.delete(Gruppolicenzecommerciali);
		session.flush();
	}
		
	//Modifica da Gruppolicenzecommerciali
	public void edit(Gruppolicenzecommerciali Gruppolicenzecommerciali) {
		logger.debug("Modifica di un Gruppolicenzecommerciali");
			
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Ritrova l'Gruppolicenzecommerciali
		Gruppolicenzecommerciali existingGruppolicenzecommerciali = (Gruppolicenzecommerciali) session.get(Gruppolicenzecommerciali.class, Gruppolicenzecommerciali.getIdGruppiLicenzeCommerciali());
			
		//Modifico gli attributi
		existingGruppolicenzecommerciali.setLicenzecommerciali(Gruppolicenzecommerciali.getLicenzecommerciali());
		existingGruppolicenzecommerciali.setVocelicenza(Gruppolicenzecommerciali.getVocelicenza());
		
		//Salvo le modifiche
		session.save(existingGruppolicenzecommerciali);
		session.flush();
	}
}