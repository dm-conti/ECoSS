package it.obiectivo.ecoss.dao;

import it.obiectivo.ecoss.domain.Istanzautentecpe;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository("istanzautentecpeDAO")
public class IstanzautentecpeDAOImpl implements IstanzautentecpeDAO{
protected static Logger logger = Logger.getLogger("service");
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	//Ritorna una lista di Istanzautentecpe
	@SuppressWarnings("unchecked")
	public List<Istanzautentecpe> getAll() {
		logger.debug("IstanzautentecpeDAO - Ricerca delle Istanzautentecpe");
		
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Creo la query usando il linguaggio HQL (Hibernate Query Language)
		Query query = session.createQuery("FROM  Istanzautentecpe");
			
		//Restituisco la lista di Istanzautentecpe
		return  query.list();
	}
	
	//Ritorna una lista di Istanzautentecpe sulla base del valore assunto dallo stato
	@SuppressWarnings("unchecked")
	public List<Istanzautentecpe> getByStato(String stato) {
		
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Creo la query usando il linguaggio HQL (Hibernate Query Language)
		Query query = session.getNamedQuery("Istanzautentecpe.findByStato").setParameter("stato", stato);
			
		//Restituisco la lista di Credenzialiutente
		return  query.list();
	}
		
	//Ritrova una singola istanzautentecpe
	public Istanzautentecpe get( Integer id ) {
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Recupero l'istanzautentecpe
		Istanzautentecpe istanzautentecpe = (Istanzautentecpe) session.get(Istanzautentecpe.class, id);
		
		//Restituisco il cpe trovato
		return istanzautentecpe;
	}
		
	//Aggiungi una nuova Istanzautentecpe
	public void add(Istanzautentecpe istanzautentecpe) {
		logger.debug("Aggiungo una nuova Istanzautentecpe");
			
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
		Date dataInizio = istanzautentecpe.getDataAttivazione(); 
		Date dataFine = istanzautentecpe.getDataCessazione();
		if(dataInizio == null){
			dataInizio = new Date();
			istanzautentecpe.setDataAttivazione(dataInizio);
			dataFine = dataInizio;
			dataFine.setTime(120000);
			istanzautentecpe.setDataCessazione(dataFine);
		}else if(dataFine == null){
			dataFine = dataInizio;
			dataFine.setTime(120000);
			istanzautentecpe.setDataCessazione(dataFine);
		}	
		//Salvo l'istanzautentecpe
		session.save(istanzautentecpe);
		session.flush();
	}
		
	//Elimina una Istanzautentecpe
	public void delete(Integer id) {
		logger.debug("Eliminazione di una Istanzautentecpe");
		
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		//Recupero l'Istanzautentecpe
		Istanzautentecpe istanzautentecpe = (Istanzautentecpe) session.get(Istanzautentecpe.class, id);
			
		//Elimino l'Istanzautentecpe trovata
		session.delete(istanzautentecpe);
		session.flush();
	}
		
	//Modifica di una Istanzautentecpe
	public void edit(Istanzautentecpe istanzautentecpe) {
		logger.debug("Modifica di una Istanzautentecpe");
			
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Ritrova l'Istanzautentecpe da modificare
		Istanzautentecpe existingIstUteCpe = (Istanzautentecpe) session.get(Istanzautentecpe.class, istanzautentecpe.getId());
			
		//Modifico gli attributi
		existingIstUteCpe.setCpe(istanzautentecpe.getCpe());
		
		//DATI CHE DOVREBBERO ESSERE GENERATI IN AUTOMATICO
		//DAL SISTEMA QUANTO AGGIUNGIAMO UNA NUOVA Istanzautentecpe
		existingIstUteCpe.setDataAttivazione(istanzautentecpe.getDataAttivazione());
		//DAL SISTEMA, CALCOLATO IN AUTOMATICO ASSEGNANDO UN LIMITE TEMPORALE
		existingIstUteCpe.setDataCessazione(istanzautentecpe.getDataCessazione());
		//DETERMINATO DAL SISTEMA SULLA BASE DELLA DISCORDANZA TRA DATA INIZIO E FINE
		existingIstUteCpe.setTabellastati(istanzautentecpe.getTabellastati());
		
		existingIstUteCpe.setNumeroContratto(istanzautentecpe.getNumeroContratto());
		existingIstUteCpe.setLicenzautente(istanzautentecpe.getLicenzautente());
		existingIstUteCpe.setUtente(istanzautentecpe.getUtente());
		
		//Salvo le modifiche
		session.save(existingIstUteCpe);
		session.flush();
	}
}