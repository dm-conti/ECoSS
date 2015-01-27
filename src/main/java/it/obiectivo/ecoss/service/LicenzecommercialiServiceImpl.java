package it.obiectivo.ecoss.service;

import it.obiectivo.ecoss.dao.LicenzecommercialiDAO;
import it.obiectivo.ecoss.domain.Licenzecommerciali;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("licenzecommercialiService")
@Transactional
public class LicenzecommercialiServiceImpl implements LicenzecommercialiService{
	
	protected static Logger logger = Logger.getLogger("service");
	
	@Autowired
    private LicenzecommercialiDAO licenzecommercialiDAO;
	
	//Ritorna una lista di Licenze Commerciali
	public List<Licenzecommerciali> getAll() {
		logger.debug("--- Service Layer ---");
		return  licenzecommercialiDAO.getAll();
	}
	
	//Ritrova una singola Licenza Commerciale
	public Licenzecommerciali get( Integer id ) {
		logger.debug("--- Service Layer ---");
		return licenzecommercialiDAO.get(id);
	}
	
	//Aggiungi una nuova licenza
	public void add(Licenzecommerciali licenzecommerciali) {
		logger.debug("--- Service Layer ---");
		licenzecommercialiDAO.add(licenzecommerciali);
	}
	
	//Elimina una Licenza Commerciale
	public void delete(Integer id) {
		logger.debug("--- Service Layer ---");
		licenzecommercialiDAO.delete(id);
	}
	
	//Modifica da Utente
	public void edit(Licenzecommerciali licenzecommerciali) {
		logger.debug("--- Service Layer ---");
		licenzecommercialiDAO.edit(licenzecommerciali);
	}
}