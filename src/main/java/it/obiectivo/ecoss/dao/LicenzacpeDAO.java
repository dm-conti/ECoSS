package it.obiectivo.ecoss.dao;

import it.obiectivo.ecoss.domain.Licenzacpe;

import java.util.List;

public interface LicenzacpeDAO {
	
	public List<Licenzacpe> getAll();
	public List<Licenzacpe> getByStato(String stato);
	public Licenzacpe get(Integer id);
	public void add(Licenzacpe licenzacpe);
	public void delete(Integer id);
	public void edit(Licenzacpe licenzacpe);
}
