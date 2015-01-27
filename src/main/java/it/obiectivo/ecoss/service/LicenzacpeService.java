package it.obiectivo.ecoss.service;

import java.util.List;

import it.obiectivo.ecoss.domain.Licenzacpe;

public interface LicenzacpeService {
	
	public List<Licenzacpe> getAll();
	public List<Licenzacpe> getByStato(String stato);
	public Licenzacpe get(Integer id);
	public Boolean add(Licenzacpe licenzacpe);
	public Boolean delete(Integer id);
	public Boolean edit(Licenzacpe licenzacpe);	
}