package it.obiectivo.ecoss.dao;

import it.obiectivo.ecoss.domain.Tabellastati;

import java.util.List;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository("tabellastatiDAO")
public class TabellastatiDAOImpl implements TabellastatiDAO{
protected static Logger logger = Logger.getLogger("service");
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	//Ritorna una lista di Tabellastati
	@SuppressWarnings("unchecked")
	public List<Tabellastati> getAll() {
		logger.debug("Layer DAO: Tabellastati getAll");
		
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Creo la query usando il linguaggio HQL (Hibernate Query Language)
		Query query = session.createQuery("FROM  Tabellastati");
			
		//Restituisco la lista di Tabellastati
		return  query.list();
	}
		
	//Ritrova una singola Tabellastati
	public Tabellastati get( Integer id ) {
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Recupero la Tabellastati
		Tabellastati stato = (Tabellastati) session.get(Tabellastati.class, id);
		
		//Restituisco la Tabellastati trovata
		return stato;
	}
	
	//Ritrova un singolo stato dalla descrizione
	public Tabellastati getByName(String stato){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("Tabellastati.findByStato").setString("stato", stato);
		
		Tabellastati exStato = (Tabellastati) query.setMaxResults(1).uniqueResult();
		
		return exStato;
	}
	
	//Ritrova un singolo stato dalla descrizione
	@SuppressWarnings("unchecked")
	public List<Tabellastati> getByNomeTabella(String nomeTabella){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("Tabellastati.findByTabella").setString("tabella", nomeTabella);
				
		return query.list();
	}
		
	//Aggiungi una nuova Tabellastati
	public void add(Tabellastati stato) {
		logger.debug("Aggiungo un nuovo stato in Tabellastati");
			
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Salvo lo stato
		session.save(stato);
	}
		
	//Elimino uno stato della Tabellastati
	public void delete(Integer id) {
		logger.debug("Eliminazione di uno stato della Tabellastati");
		
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		//Recupero lo stato
		Tabellastati stato = (Tabellastati) session.get(Tabellastati.class, id);
			
		//Elimino lo stato trovato
		session.delete(stato);
		session.flush();
	}
		
	//Modifica di uno stato in Tabellastati
	public void edit(Tabellastati stato) {
		logger.debug("Modifico uno stato in Tabellastati");
			
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Ritrovo l.o stato da modificare
		Tabellastati existingStato = (Tabellastati) session.get(Tabellastati.class, stato.getIdstato());
			
		//Modifico gli attributi
		existingStato.setCpeList(stato.getCpeList());
		existingStato.setIstanzautentecpeList(stato.getIstanzautentecpeList());
		existingStato.setLicenzacpeList(stato.getLicenzacpeList());
		existingStato.setLicenzautenteList(stato.getLicenzautenteList());
		existingStato.setServizilicenzacpeList(stato.getServizilicenzacpeList());
		existingStato.setStato(stato.getStato());
			
		//Salvo le modifiche
		session.save(existingStato);
		session.flush();
	}
}