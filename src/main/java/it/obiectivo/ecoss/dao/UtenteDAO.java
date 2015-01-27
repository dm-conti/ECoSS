package it.obiectivo.ecoss.dao;

import it.obiectivo.ecoss.domain.Utente;

import java.util.List;

public interface UtenteDAO {
	
	public List<Utente> getAll();
	public Utente get(Integer id);
	public void add(Utente utente);
	public void delete(Integer id);
	public void edit(Utente utente);
}