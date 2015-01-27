package it.obiectivo.ecoss.service;

import java.util.List;

import it.obiectivo.ecoss.domain.Credenzialiutente;
import it.obiectivo.ecoss.domain.Distributore;
import it.obiectivo.ecoss.domain.Profilo;
import it.obiectivo.ecoss.domain.Utente;

public interface CredenzialiutenteService {
	
	public List<Credenzialiutente> getAll();
	public List<Credenzialiutente> getByPrimoAccesso(Boolean primoAccesso);
	public Credenzialiutente get(Integer id);
	public Credenzialiutente getByUserId(String userId);
	//public Boolean add(Credenzialiutente credenziali);
	public Credenzialiutente makeCredenziali(String userId, Profilo ruolo);
	//public Credenzialiutente makeCredenziali(String userId, Profilo ruolo, Distributore distributore);
	//public Credenzialiutente makeCredenziali(String userId, Profilo ruolo, Utente utente );
	public Boolean delete(Integer id);
	public Boolean edit(Credenzialiutente credenziali);	
}