package it.obiectivo.ecoss.service;

import it.obiectivo.ecoss.dao.LicenzautenteDAO;
import it.obiectivo.ecoss.domain.CheckDate;
import it.obiectivo.ecoss.domain.Licenzautente;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("licenzautenteService")
@Transactional
public class LicenzautenteServiceImpl extends CheckDateService implements LicenzautenteService{
	protected static Logger logger = Logger.getLogger("service");
	
	@Autowired
    private LicenzautenteDAO licenzautenteDAO;
	
	//Ritorna una lista di Licenzautente
	public List<Licenzautente> getAll() {
		logger.debug("--- Service Layer ---");
		return licenzautenteDAO.getAll();
	}
	
	//Ritorna una lista di Istanzautentecpe discriminate per stato
	public List<Licenzautente> getByStato(String stato) {
		logger.debug("--- Service Layer ---");
		return licenzautenteDAO.getByStato(stato);
	}
	
	//Ritrova una singola Licenzautente
	public Licenzautente get( Integer id ) {
		logger.debug("--- Service Layer ---");
		return licenzautenteDAO.get(id);
	}
	
	//Aggiungi una nuova Licenzautente
	public Boolean add(Licenzautente licenzautente) {
		logger.debug("--- Service Layer ---");
		
		try {
			licenzautenteDAO.add(licenzautente);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	//Elimina una Licenzautente
	public Boolean delete(Integer id) {
		logger.debug("--- Service Layer ---");
		try {
			licenzautenteDAO.delete(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public Boolean edit(CheckDate obj){
		return edit(obj);
	}
	
	//Modifica di una Licenzautente
	public Boolean edit(Licenzautente licenzautente) {
		logger.debug("--- Service Layer ---");
		try {
			licenzautenteDAO.edit(licenzautente);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}