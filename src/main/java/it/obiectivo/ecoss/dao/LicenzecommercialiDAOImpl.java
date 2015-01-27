package it.obiectivo.ecoss.dao;


import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.apache.log4j.Logger;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import it.obiectivo.ecoss.domain.Licenzecommerciali;

@Repository("licenzecommercialiDAO")
public class LicenzecommercialiDAOImpl implements LicenzecommercialiDAO{
protected static Logger logger = Logger.getLogger("service");

	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	//Ritorna una lista di Licenze Commerciali
	@SuppressWarnings("unchecked")
	public List<Licenzecommerciali> getAll() {
		logger.debug("--- DAO Layer ---");
		logger.debug("Ricerca delle Licenze Commerciali");
		
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Creo la query usando il linguaggio HQL (Hibernate Query Language)
		Query query = session.createQuery("FROM  Licenzecommerciali");
		
		logger.debug("Query appena effettuata FROM Licenzecommerciali");
			
		//Restituisco la lista di Licenze Commerciali
		return  query.list();
	}
		
	//Ritrova una singola Licenza Commerciale
	public Licenzecommerciali get( Integer id ) {
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Recupero la Licenza Commerciale
		Licenzecommerciali licenzecommerciali = (Licenzecommerciali) session.get(Licenzecommerciali.class, id);
		
		//Restituisco la licenza trovata
		return licenzecommerciali;
	}
		
	//Aggiungi una nuova Licenza Commerciale
	public void add(Licenzecommerciali licenzecommerciali) {
		logger.debug("Aggiungo una nuova Licenza Commerciale");
			
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
					
		//Salvo la licenza
		session.save(licenzecommerciali);
	}
		
	//Elimina una Licenza Commerciale
	public void delete(Integer id) {
		logger.debug("Eliminazione di una Licenza Commerciale");
		
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		//Recupero la licenza
		Licenzecommerciali licenzecommerciali = (Licenzecommerciali) session.get(Licenzecommerciali.class, id);
			
		//Elimino la licenza
		session.delete(licenzecommerciali);
		session.flush();
	}
		
	//Modifica di una Licenza Commerciale
	public void edit(Licenzecommerciali licenzecommerciali) {
		logger.debug("Modifica di una Licenza Commerciale");
			
		//Recupero la sessione da Hibernate
		Session session = sessionFactory.getCurrentSession();
			
		//Ritrova la licenza
		Licenzecommerciali exLicenza = (Licenzecommerciali) session.get(Licenzecommerciali.class, licenzecommerciali.getIdLicenzeCommerciali());
			
		//Modifico gli attributi
		exLicenza.setLicenzautenteList(licenzecommerciali.getLicenzautenteList());
		exLicenza.setLicomNomeLicenza(licenzecommerciali.getLicomNomeLicenza());
		exLicenza.setGruppolicenzecommercialiList(licenzecommerciali.getGruppolicenzecommercialiList());
			
		//Salvo le modifiche
		session.save(exLicenza);
		session.flush();
	}
}