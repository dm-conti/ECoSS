package it.obiectivo.ecoss.service;

import it.obiectivo.ecoss.dao.ServizilicenzacpeDAO;
import it.obiectivo.ecoss.domain.Servizilicenzacpe;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("servizilicenzacpeService")
@Transactional
public class ServizilicenzacpeServiceImpl implements ServizilicenzacpeService{
protected static Logger logger = Logger.getLogger("Service Layer: servizilicenzacpeService");
	
	@Autowired
    private ServizilicenzacpeDAO servizilicenzacpeDAO;
	
	//Ritorna una lista di Servizilicenzacpe
	public List<Servizilicenzacpe> getAll() {
		logger.debug("--- Service Layer ---");
		return servizilicenzacpeDAO.getAll();
	}
	
	//Ritorna una lista di Istanzautentecpe discriminate per stato
	public List<Servizilicenzacpe> getByStato(String stato) {
		logger.debug("--- Service Layer ---");
		return servizilicenzacpeDAO.getByStato(stato);
	}
	
	//Ritrova un singolo Servizilicenzacpe
	public Servizilicenzacpe get( Integer id ) {
		logger.debug("--- Service Layer ---");
		return servizilicenzacpeDAO.get(id);
	}
	
	//Ritrova Servizilicenzacpe tramite l'id
	public Servizilicenzacpe getByIdServiziLicenzaCPE(Integer idServiziLicenzaCPE){
		logger.debug("--- Service Layer ---");
		return servizilicenzacpeDAO.getByIdServiziLicenzaCPE(idServiziLicenzaCPE);
	}
	
	//Aggiungi un nuovo Servizilicenzacpe
	public Boolean add(Servizilicenzacpe servizio) {
		logger.debug("--- Service Layer ---");
		
		try {
			servizilicenzacpeDAO.add(servizio);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	//Elimina un Servizilicenzacpe
	public Boolean delete(Integer id) {
		logger.debug("--- Service Layer ---");
		try {
			servizilicenzacpeDAO.delete(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	//Modifica di un Servizilicenzacpe
	public Boolean edit(Servizilicenzacpe servizio) {
		logger.debug("--- Service Layer ---");
		try {
			servizilicenzacpeDAO.edit(servizio);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}