package it.obiectivo.ecoss.service;

import java.util.List;

import it.obiectivo.ecoss.domain.Cpe;

public interface CpeService {
	
	public List<Cpe> getAll();
	public Cpe get(Integer id);
	public Boolean add(Cpe cpe);
	public Boolean delete(Integer id);
	public Boolean edit(Cpe cpe);	
}