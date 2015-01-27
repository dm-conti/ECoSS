package it.obiectivo.ecoss.service;

import it.obiectivo.ecoss.domain.Notifiche;
import java.util.List;

public interface NotificheService {
	public List<Notifiche> getAll();
	public Notifiche get(Integer id);
	public Notifiche getRow(int idTabella, String nomeTabella, String codNotifica);
	public void add(Notifiche notifiche);
	public void delete(Integer id);
	public void edit(Notifiche notifiche);
}