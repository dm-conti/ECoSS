package it.obiectivo.ecoss.service;

import it.obiectivo.ecoss.dao.DistributoreDAO;
import it.obiectivo.ecoss.domain.Distributore;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("distributoreService")
@Transactional
public class DistributoreServiceImpl implements DistributoreService{
protected static Logger logger = Logger.getLogger("gestoreService");
	
	@Autowired
    private DistributoreDAO distributoreDAO;
	
	//Ritorna una lista di Distributori
	public List<Distributore> getAll() {
		logger.debug("--- Service Layer ---");
		return  distributoreDAO.getAll();
	}
	
	//Ritrova una singola ricorrenza di Distributore
	public Distributore get( Integer id ) {
		logger.debug("--- Service Layer ---");
		return distributoreDAO.get(id);
	}
	
	//Ritrova una singola istanza di Distributore dalla ragione sociale
	public Distributore getByRagSociale( String ragioneSociale){
		return distributoreDAO.getByRagSociale(ragioneSociale);
	}
	
	//Aggiungi un nuovo Distributore
	public Boolean add(Distributore distributore) {
		logger.debug("--- Service Layer ---");
		try {
			distributoreDAO.add(distributore);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	//Elimina una istanza di Distributore
	public Boolean delete(Integer id) {
		logger.debug("--- Service Layer ---");
		try {
			distributoreDAO.delete(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	//Modifica un istanza di Distributore
	public Boolean edit(Distributore distributore) {
		logger.debug("--- Service Layer ---");
		try {
			distributoreDAO.edit(distributore);
			return true;
		} catch (Exception e) {
			return false;
		}
	}		
}