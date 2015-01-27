package it.obiectivo.ecoss.service;

import java.util.List;

import it.obiectivo.ecoss.dao.ProfiloDAO;
import it.obiectivo.ecoss.domain.Profilo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("profiloService")
@Transactional
public class ProfiloServiceImpl implements ProfiloService{
protected static Logger logger = Logger.getLogger("service");
	
	@Autowired
    private ProfiloDAO profiloDAO;
	
	//Ritorna una lista di Profili
	public List<Profilo> getAll() {
		return  profiloDAO.getAll();
	}
	
	//Ritrova un singolo Profilo dall'id
	public Profilo get( Integer id ) {
		return profiloDAO.get(id);
	}
	
	//Ritrova un singolo Profilo dalla descrizione
	public Profilo getByProfDescrizione( String descrizione ){
		return profiloDAO.getByProfDescrizione(descrizione);
	}
	
	//Aggiungi un unovo Prrofilo
	public Boolean add(Profilo profilo) {
		try {
			profiloDAO.add(profilo);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	//Elimina un Profilo
	public Boolean delete(Integer id) {
		try{
			profiloDAO.delete(id);
			return true;
		}
		catch(Exception e){
			return false;
		}
		
	}
	
	//Modifica un Profilo
	public Boolean edit(Profilo profilo) {
		try{
			profiloDAO.edit(profilo);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
}