package it.obiectivo.ecoss.dao;

import it.obiectivo.ecoss.domain.Cpe;

import java.util.List;

public interface CpeDAO {
	
	public List<Cpe> getAll();
	public Cpe get(Integer id);
	public void add(Cpe cpe);
	public void delete(Integer id);
	public void edit(Cpe cpe);
}