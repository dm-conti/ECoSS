package it.obiectivo.ecoss.service;

import it.obiectivo.ecoss.dao.GestoreDAO;
import it.obiectivo.ecoss.domain.Gestore;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("gestoreService")
@Transactional
public class GestoreServiceImpl implements GestoreService {
	protected static Logger logger = Logger.getLogger("gestoreService");
	
	@Autowired
    private GestoreDAO gestoreDAO;
	
	//Ritorna una lista di Gestori
	public List<Gestore> getAll() {
		logger.debug("--- Service Layer ---");
		return  gestoreDAO.getAll();
	}
	
	//Ritrova una singola ricorrenza di Gestore
	public Gestore get( Integer id ) {
		logger.debug("--- Service Layer ---");
		return gestoreDAO.get(id);
	}
	
	//Ritrova una singola istanza di Gestore dalla ragione sociale
	public Gestore getByRagSociale( String ragioneSociale){
		return gestoreDAO.getByRagSociale(ragioneSociale);
	}
	
	//Aggiungi un nuovo Gestore
	public Boolean add(Gestore gestore) {
		logger.debug("--- Service Layer ---");

		try {
			gestoreDAO.add(gestore);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//Elimina una istanza di Gestore
	public Boolean delete(Integer id) {
		logger.debug("--- Service Layer ---");
		try {
			gestoreDAO.delete(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	//Modifica un istanza di Gestore
	public Boolean edit(Gestore gestore) {
		logger.debug("--- Service Layer ---");
		try {
			gestoreDAO.edit(gestore);
			return true;
		} catch (Exception e) {
			return false;
		}
	}		
}