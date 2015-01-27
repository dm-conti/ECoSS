package it.obiectivo.ecoss.dao;

import it.obiectivo.ecoss.domain.Licenzautente;

import java.util.List;

public interface LicenzautenteDAO {
	public List<Licenzautente> getAll();
	public List<Licenzautente> getByStato(String stato);
	public Licenzautente get(Integer id);
	public void add(Licenzautente licenzautente);
	public void delete(Integer id);
	public void edit(Licenzautente licenzautente);

}
