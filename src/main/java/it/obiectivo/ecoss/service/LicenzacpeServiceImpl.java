package it.obiectivo.ecoss.service;

import it.obiectivo.ecoss.dao.LicenzacpeDAO;
import it.obiectivo.ecoss.domain.Licenzacpe;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service("licenzacpeService")
@Transactional
public class LicenzacpeServiceImpl implements LicenzacpeService{

	protected static Logger logger = Logger.getLogger("service");
	
	@Autowired
    private LicenzacpeDAO licenzacpeDAO;
	
	//Ritorna una lista di Licenzacpe
	public List<Licenzacpe> getAll() {
		logger.debug("--- Service Layer ---");
		return licenzacpeDAO.getAll();
	}
	
	//Ritorna una lista di Istanzautentecpe discriminate per stato
	public List<Licenzacpe> getByStato(String stato) {
		logger.debug("--- Service Layer ---");
		return licenzacpeDAO.getByStato(stato);
	}
	
	//Ritrova una singola Licenzacpe
	public Licenzacpe get( Integer id ) {
		logger.debug("--- Service Layer ---");
		return licenzacpeDAO.get(id);
	}
	
	//Aggiungi una nuova Licenzacpe
	public Boolean add(Licenzacpe licenzacpe) {
		logger.debug("--- Service Layer ---");
		
		try {
			licenzacpeDAO.add(licenzacpe);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	//Elimina una Licenzacpe
	public Boolean delete(Integer id) {
		logger.debug("--- Service Layer ---");
		try {
			licenzacpeDAO.delete(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	//Modifica di una LicenzaCpe
	public Boolean edit(Licenzacpe licenzacpe) {
		logger.debug("--- Service Layer ---");
		try {
			licenzacpeDAO.edit(licenzacpe);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}