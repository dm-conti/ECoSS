package it.obiectivo.ecoss.service;

import it.obiectivo.ecoss.domain.Tabellastati;

import java.util.List;

public interface TabellastatiService {
	public List<Tabellastati> getAll();
	public Tabellastati get(Integer id);
	public Tabellastati getByName(String stato);
	public List<Tabellastati> getByNomeTabella(String nomeTabella);
	public Boolean add(Tabellastati stato );
	public Boolean delete(Integer id);
	public Boolean edit(Tabellastati stato);
}