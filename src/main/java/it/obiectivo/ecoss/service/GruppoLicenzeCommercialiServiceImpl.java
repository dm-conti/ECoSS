package it.obiectivo.ecoss.service;

import it.obiectivo.ecoss.dao.GruppoLicenzeCommercialiDAO;
import it.obiectivo.ecoss.domain.Gruppolicenzecommerciali;
import it.obiectivo.ecoss.domain.Licenzecommerciali;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("gruppoLicenzeCommercialiService")
@Transactional
public class GruppoLicenzeCommercialiServiceImpl implements GruppoLicenzeCommercialiService{
protected static Logger logger = Logger.getLogger("service");
	
	@Autowired
    private GruppoLicenzeCommercialiDAO gruppoLicenzeCommercialiDAO;
	
	//Ritorna una lista di Gruppolicenzecommerciali
	public List<Gruppolicenzecommerciali> getAll() {
		logger.debug("--- Service Layer ---");
		return  gruppoLicenzeCommercialiDAO.getAll();
	}
	
	//Ritrova un singolo Gruppolicenzecommerciali
	public Gruppolicenzecommerciali get( Integer id ) {
		logger.debug("--- Service Layer ---");
		return gruppoLicenzeCommercialiDAO.get(id);
	}
	
	/*
	//Ritrova un singolo Gruppolicenzecommerciali
	public List<Gruppolicenzecommerciali> getByLicenzecommerciali( Licenzecommerciali licenzeCommerciali){
		logger.debug("--- Service Layer: getByLicenzeCommerciali method ---");
		return gruppoLicenzeCommercialiDAO.getByLicenzeCommerciali(licenzeCommerciali);
	}
	*/
	
	//Aggiungi un nuovo Gruppolicenzecommerciali
	public void add(Gruppolicenzecommerciali gruppolicenzecommerciali) {
		logger.debug("--- Service Layer ---");
		gruppoLicenzeCommercialiDAO.add(gruppolicenzecommerciali);
	}
	
	//Elimina un Gruppolicenzecommerciali
	public void delete(Integer id) {
		logger.debug("--- Service Layer ---");
		gruppoLicenzeCommercialiDAO.delete(id);
	}
	
	//Modifica di un Gruppolicenzecommerciali
	public void edit(Gruppolicenzecommerciali gruppolicenzecommerciali) {
		logger.debug("--- Service Layer ---");
		gruppoLicenzeCommercialiDAO.edit(gruppolicenzecommerciali);
	}
}