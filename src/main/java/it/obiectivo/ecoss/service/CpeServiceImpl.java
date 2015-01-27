package it.obiectivo.ecoss.service;

import it.obiectivo.ecoss.dao.CpeDAO;
import it.obiectivo.ecoss.domain.Cpe;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service("cpeService")
@Transactional
public class CpeServiceImpl implements CpeService{

	protected static Logger logger = Logger.getLogger("service");
	
	@Autowired
    private CpeDAO cpeDAO;
	
	//Ritorna una lista di Cpe
	public List<Cpe> getAll() {
		logger.debug("--- Service Layer ---");
		return cpeDAO.getAll();
	}
	
	//Ritrova un singolo Cpe
	public Cpe get( Integer id ) {
		logger.debug("--- Service Layer ---");
		return cpeDAO.get(id);
	}
	
	//Aggiungi un nuovo utente
	public Boolean add(Cpe cpe) {
		logger.debug("--- Service Layer ---");
		
		try {
			cpeDAO.add(cpe);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//Elimina un Cpe
	public Boolean delete(Integer id) {
		logger.debug("--- Service Layer ---");
		try {
			cpeDAO.delete(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	//Modifica di un Cpe
	public Boolean edit(Cpe cpe) {
		logger.debug("--- Service Layer ---");
		try {
			cpeDAO.edit(cpe);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}