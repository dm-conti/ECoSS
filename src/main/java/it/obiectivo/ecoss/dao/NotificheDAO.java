package it.obiectivo.ecoss.dao;

import it.obiectivo.ecoss.domain.Notifiche;

import java.util.List;

public interface NotificheDAO {
	
	public List<Notifiche> getAll();
	public Notifiche get(int id);
	public Notifiche getRow(int idTabella, String nomeTabella, String codNotifica);
	public void add(Notifiche notifica);
	public void delete(int id);
	public void edit(Notifiche notifica);
}