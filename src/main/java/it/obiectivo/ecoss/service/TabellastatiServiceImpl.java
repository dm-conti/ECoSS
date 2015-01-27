package it.obiectivo.ecoss.service;

import it.obiectivo.ecoss.dao.TabellastatiDAO;
import it.obiectivo.ecoss.domain.Tabellastati;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("tabellastatiService")
@Transactional
public class TabellastatiServiceImpl implements TabellastatiService{
protected static Logger logger = Logger.getLogger("service");
	
	@Autowired
    private TabellastatiDAO tabellastatiDAO;
	
	//Ritorna una lista di stati da Tabellastati
	public List<Tabellastati> getAll() {
		logger.debug("--- Service Layer ---");
		return tabellastatiDAO.getAll();
	}
	
	//Ritrova un singolo Stato di Tabellastati
	public Tabellastati get( Integer id ) {
		logger.debug("--- Service Layer ---");
		return tabellastatiDAO.get(id);
	}
	
	//Ritrova una singola Vocelicenza dal nome
	public Tabellastati getByName( String stato ){
		return tabellastatiDAO.getByName(stato);
	}
	
	/* Ritrova una lista di stati dal nome della 
	 * Tabella a cui lo stato puo' essere applicato */
	public List<Tabellastati> getByNomeTabella( String nomeTabella ){
		return tabellastatiDAO.getByNomeTabella(nomeTabella);
	}
	
	//Aggiungi un nuovo stato a Tabellastati
	public Boolean add(Tabellastati stato) {
		logger.debug("--- Service Layer ---");
		
		try {
			tabellastatiDAO.add(stato);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	//Elimina uno stato da Tabellastati
	public Boolean delete(Integer id) {
		logger.debug("--- Service Layer ---");
		try {
			tabellastatiDAO.delete(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	//Modifica di uno stato in Tabellastati
	public Boolean edit(Tabellastati stato) {
		logger.debug("--- Service Layer ---");
		try {
			tabellastatiDAO.edit(stato);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}