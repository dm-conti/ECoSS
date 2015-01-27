package it.obiectivo.ecoss.dao;

import it.obiectivo.ecoss.domain.Credenzialiutente;

import java.util.List;

public interface CredenzialiutenteDAO {
	public List<Credenzialiutente> getAll();
	public List<Credenzialiutente> getByPrimoAccesso(Boolean primoAccesso);
	public Credenzialiutente get(Integer id);
	public Credenzialiutente getByUserId(String nomeLicenza);
	public void add(Credenzialiutente credenzialiutente);
	public void delete(Integer id);
	public void edit(Credenzialiutente credenzialiutente);
}