package it.obiectivo.ecoss.dao;

import it.obiectivo.ecoss.domain.Gestore;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository("gestoreDAO")
public class GestoreDAOImpl implements GestoreDAO{
protected static Logger logger = Logger.getLogger("gestoreDAO");
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	//Ritorna una lista di Gestori
	@SuppressWarnings("unchecked")
	public List<Gestore> getAll() {
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Creo la query usando il linguaggio HQL (Hibernate Query Language)
		Query query = session.createQuery("FROM  Gestore");
			
		//Restituisco la lista di Gestori
		return  query.list();
	}
		
	//Ritrova una singola istanza di Gestore dall'id
	public Gestore get( Integer id ) {
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Recupero una ricorrenza di Gestore
		Gestore gestore = (Gestore) session.get(Gestore.class, id);
		
		//Restituisco l'istanza trovata
		return gestore;
	}
	
	//Ritrova una ricorrenza di Gestore dalla ragione sociale
	public Gestore getByRagSociale(String ragioneSociale){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("Gestore.findByGestRagSociale").setString("gestRagSociale", ragioneSociale);
		
		Gestore gestore = (Gestore) query.setMaxResults(1).uniqueResult();
		
		return gestore;
	}
		
	//Aggiungi una nuova ricorrenza di Gestore
	public void add(Gestore gestore) {
		logger.debug("Aggiungo un Gestore");
			
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		//Assegno la data di creazione
		gestore.setGestDataCreazione(new Date());
			
		//Salvo il Gestore
		session.save(gestore);
		session.flush();
	}
		
	//Elimino una ricorrenza di Gestore
	public void delete(Integer id) {
		logger.debug("Elimina una ricorrenza di Gestore");
		
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		//Recupero la ricorrenza da eliminare
		Gestore gestore = (Gestore) session.get(Gestore.class, id);
			
		//Elimino la ricorrenza
		session.delete(gestore);
		session.flush();
	}
		
	//Modifica di un Gestore
	public void edit(Gestore gestore) {
		logger.debug("Modifica Gestore");
			
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Ritrova il Gestore da modificare
		Gestore exGestore = (Gestore) session.get(Gestore.class, gestore.getIdGestore());
			
		//Modifico gli attributi
		exGestore.setGestCap(gestore.getGestCap());
		exGestore.setGestCF(gestore.getGestCF());
		exGestore.setGestCitta(gestore.getGestCitta());
		exGestore.setGestDataCessazione(gestore.getGestDataCessazione());
		//exGestore.setGestDataCreazione(gestore.getGestDataCreazione());
		exGestore.setGestDescrizione(gestore.getGestDescrizione());
		exGestore.setGestEmail(gestore.getGestEmail());
		exGestore.setGestEmail2(gestore.getGestEmail2());
		exGestore.setGestFax(gestore.getGestFax());
		exGestore.setGestIndirizzo(gestore.getGestIndirizzo());
		exGestore.setGestLivello(gestore.getGestLivello());
		exGestore.setGestPIVA(gestore.getGestPIVA());
		exGestore.setGestRagSociale(gestore.getGestRagSociale());
		exGestore.setGestTel1(gestore.getGestTel1());
		exGestore.setGestTel2(gestore.getGestTel2());
		exGestore.setCpeList(gestore.getCpeList());
			
		//Salvo le modifiche
		session.save(exGestore);
		session.flush();
	}	

}
