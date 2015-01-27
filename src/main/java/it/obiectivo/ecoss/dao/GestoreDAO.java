package it.obiectivo.ecoss.dao;

import java.util.List;
import it.obiectivo.ecoss.domain.Gestore;

public interface GestoreDAO {
	public List<Gestore> getAll();
	public Gestore get(Integer id);
	public Gestore getByRagSociale(String ragioneSociale);
	public void add(Gestore gestore);
	public void delete(Integer id);
	public void edit(Gestore gestore);
}