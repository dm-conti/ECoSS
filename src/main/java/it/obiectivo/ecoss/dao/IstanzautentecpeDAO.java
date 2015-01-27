package it.obiectivo.ecoss.dao;

import it.obiectivo.ecoss.domain.Istanzautentecpe;

import java.util.List;

public interface IstanzautentecpeDAO {
	
	public List<Istanzautentecpe> getAll();
	public List<Istanzautentecpe> getByStato(String stato);
	public Istanzautentecpe get(Integer id);
	public void add(Istanzautentecpe istanzautentecpe);
	public void delete(Integer id);
	public void edit(Istanzautentecpe istanzautentecpe);
}