package it.obiectivo.ecoss.service;

import it.obiectivo.ecoss.domain.Licenzautente;

import java.util.List;

public interface LicenzautenteService {
	public List<Licenzautente> getAll();
	public List<Licenzautente> getByStato(String stato);
	public Licenzautente get(Integer id);
	public Boolean add(Licenzautente licenzautente);
	public Boolean delete(Integer id);
	public Boolean edit(Licenzautente licenzautente);

}
