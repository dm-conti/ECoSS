package it.obiectivo.ecoss.service;

import it.obiectivo.ecoss.domain.Credenzialiutente;
import it.obiectivo.ecoss.domain.Istanzautentecpe;

import java.util.List;

public interface IstanzautentecpeService {
	
	public List<Istanzautentecpe> getAll();
	public List<Istanzautentecpe> getByStato(String stato);
	public Istanzautentecpe get(Integer id);
	public Boolean add(Istanzautentecpe istanzautentecpe);
	public Boolean add(Istanzautentecpe istanzautentecpe, Credenzialiutente credenziali);
	public Boolean delete(Integer id);
	public Boolean edit(Istanzautentecpe istanzautentecpe);
}