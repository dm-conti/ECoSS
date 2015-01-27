package it.obiectivo.ecoss.service;

import it.obiectivo.ecoss.dao.NotificheDAO;
import it.obiectivo.ecoss.domain.Notifiche;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("notificheService")
@Transactional
public class NotificheServiceImpl implements NotificheService{
	protected static Logger logger = Logger.getLogger("service");
	
	@Autowired
    private NotificheDAO notificheDAO;
	
	//Ritorna una lista di Notifiche
	public List<Notifiche> getAll(){
		return notificheDAO.getAll();
	}
	
	//Ritrova una Notifica dall'id
	public Notifiche get( Integer id ) {
		return notificheDAO.get(id);
	}
	
	//Ritrova una singola Notifica dalla terna (idTabella, nomeTabella, codNotifica)
	public Notifiche getRow( int idTabella, String nomeTabella, String codNotifica ){
		return notificheDAO.getRow(idTabella, nomeTabella, codNotifica);
	}
	
	//Aggiungi una nuova Notifica
	public void add(Notifiche notifica) {
		notificheDAO.add(notifica);
	}
	
	//Elimina una Notifica
	public void delete(Integer id) {
		notificheDAO.delete(id);
	}
	
	//Modifica una Notifica
	public void edit(Notifiche notifica) {
		notificheDAO.edit(notifica);
	}
}