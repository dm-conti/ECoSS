package it.obiectivo.ecoss.dao;

import it.obiectivo.ecoss.domain.Tabellastati;

import java.util.List;

public interface TabellastatiDAO {
	public List<Tabellastati> getAll();
	public Tabellastati get(Integer id);
	public Tabellastati getByName(String stato);
	public List<Tabellastati> getByNomeTabella(String nomeTabella);
	public void add(Tabellastati stato);
	public void delete(Integer id);
	public void edit(Tabellastati stato);
}