package it.obiectivo.ecoss.dao;

import it.obiectivo.ecoss.domain.Distributore;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository("distributoreDAO")
public class DistributoreDAOImpl implements DistributoreDAO{
protected static Logger logger = Logger.getLogger("distributoreDAO");
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	//Ritorna una lista di Distributori
	@SuppressWarnings("unchecked")
	public List<Distributore> getAll() {
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Creo la query usando il linguaggio HQL (Hibernate Query Language)
		Query query = session.createQuery("FROM  Distributore");
			
		//Restituisco la lista di Distributori
		return  query.list();
	}
		
	//Ritrova una singola istanza di Distributore dall'id
	public Distributore get( Integer id ) {
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Recupero una ricorrenza di Distributore
		Distributore distributore = (Distributore) session.get(Distributore.class, id);
		
		//Restituisco l'istanza trovata
		return distributore;
	}
	
	//Ritrova una ricorrenza di Gestore dalla ragione sociale
	public Distributore getByRagSociale(String ragioneSociale){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("Distributore.findByDistRagSociale").setString("distRagSociale", ragioneSociale);
		
		Distributore distributore = (Distributore) query.setMaxResults(1).uniqueResult();
		
		return distributore;
	}
		
	//Aggiungi una nuova ricorrenza di Distributore
	public void add(Distributore distributore) {
		logger.debug("Aggiungo un Distributore");
			
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
		Date dataCreazione = distributore.getDistDataCreazione();
		if(dataCreazione == null){
			distributore.setDistDataCreazione(new Date());
		}
			
		//Salvo il Distributore
		session.save(distributore);
	}
		
	//Elimino una ricorrenza di Distributore
	public void delete(Integer id) {
		logger.debug("Elimina una ricorrenza di Distributore");
		
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		//Recupero la ricorrenza da eliminare
		Distributore distributore = (Distributore) session.get(Distributore.class, id);
			
		//Elimino la ricorrenza
		session.delete(distributore);
		session.flush();
	}
		
	//Modifica di un Gestore
	public void edit(Distributore distributore) {
		logger.debug("Modifica Distributore");
			
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Ritrova il Distributore da modificare
		Distributore exDistributore = (Distributore) session.get(Distributore.class, distributore.getIdDistributore());
			
		//Modifico gli attributi
		exDistributore.setDistCap(distributore.getDistCap());
		exDistributore.setDistCF(distributore.getDistCF());
		exDistributore.setDistCitta(distributore.getDistCitta());
		exDistributore.setDistDataCessazione(distributore.getDistDataCessazione());
		exDistributore.setDistDataCreazione(distributore.getDistDataCreazione());
		exDistributore.setDistDescrizione(distributore.getDistDescrizione());
		exDistributore.setDistEmail(distributore.getDistEmail());
		exDistributore.setDistEmail2(distributore.getDistEmail2());
		exDistributore.setDistFax(distributore.getDistFax());
		exDistributore.setDistIndirizzo(distributore.getDistIndirizzo());
		exDistributore.setDistPIVA(distributore.getDistPIVA());
		exDistributore.setDistRagSociale(distributore.getDistRagSociale());
		exDistributore.setDistTel1(distributore.getDistTel1());
		exDistributore.setDistTel2(distributore.getDistTel2());
		exDistributore.setCredenzialiutente(distributore.getCredenzialiutente());
		exDistributore.setGestore(distributore.getGestore());
		exDistributore.setUtenteList(distributore.getUtenteList());
			
		//Salvo le modifiche
		session.save(exDistributore);
		session.flush();
	}	
}