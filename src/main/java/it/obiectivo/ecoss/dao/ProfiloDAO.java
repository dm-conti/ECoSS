package it.obiectivo.ecoss.dao;

import it.obiectivo.ecoss.domain.Profilo;

import java.util.List;

public interface ProfiloDAO {
	public List<Profilo> getAll();
	public Profilo get(Integer id);
	public Profilo getByProfDescrizione(String descrizione);
	public void add(Profilo profilo);
	public void delete(Integer id);
	public void edit(Profilo profilo);

}
