package it.obiectivo.ecoss.service;

import java.util.List;
import it.obiectivo.ecoss.domain.Gestore;

public interface GestoreService {
	public List<Gestore> getAll();
	public Gestore get(Integer id);
	public Gestore getByRagSociale(String ragioneSociale);
	public Boolean add(Gestore gestore);
	public Boolean delete(Integer id);
	public Boolean edit(Gestore gestore);
}