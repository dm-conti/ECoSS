package it.obiectivo.ecoss.service;

import it.obiectivo.ecoss.dao.VocelicenzaDAO;
import it.obiectivo.ecoss.domain.Vocelicenza;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("vocelicenzaService")
@Transactional
public class VocelicenzaServiceImpl implements VocelicenzaService{
	protected static Logger logger = Logger.getLogger("service");
	
	@Autowired
    private VocelicenzaDAO voceLicenzaDAO;
	
	//Ritorna una lista di Vocelicenza
	public List<Vocelicenza> getAll() {
		return  voceLicenzaDAO.getAll();
	}
	
	//Ritrova una singola Vocelicenza dall'id
	public Vocelicenza get( Integer id ) {
		return voceLicenzaDAO.get(id);
	}
	
	//Ritrova una singola Vocelicenza dal nome
	public Vocelicenza getByName( String licenseName ){
		return voceLicenzaDAO.getByName(licenseName);
	}
	
	//Aggiungi una nuova Vocelicenza
	public void add(Vocelicenza vocelicenza) {
		voceLicenzaDAO.add(vocelicenza);
	}
	
	//Elimina una Vocelicenza
	public void delete(Integer id) {
		voceLicenzaDAO.delete(id);
	}
	
	//Modifica una Vocelicenza
	public void edit(Vocelicenza vocelicenza) {
		voceLicenzaDAO.edit(vocelicenza);
	}
}