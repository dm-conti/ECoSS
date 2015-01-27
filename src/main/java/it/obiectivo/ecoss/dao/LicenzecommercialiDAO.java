package it.obiectivo.ecoss.dao;

import it.obiectivo.ecoss.domain.Licenzecommerciali;

import java.util.List;

public interface LicenzecommercialiDAO {
	public List<Licenzecommerciali> getAll();
	public Licenzecommerciali get(Integer id);
	public void add(Licenzecommerciali licenzecommerciali);
	public void delete(Integer id);
	public void edit(Licenzecommerciali licenzecommerciali);
}