package it.obiectivo.ecoss.service;

import java.util.List;
import it.obiectivo.ecoss.domain.Utente;

public interface UtenteService {
	
	public List<Utente> getAll();
	public Utente get(Integer id);
	public List<Utente> getUteWithContract();
	public List<Utente> getUteWithoutContract();
	public Boolean add(Utente utente);
	public Boolean delete(Integer id);
	public Boolean edit(Utente utente);	
}