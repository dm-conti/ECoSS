package it.obiectivo.ecoss.service;

import it.obiectivo.ecoss.domain.Gruppolicenzecommerciali;
import it.obiectivo.ecoss.domain.Licenzecommerciali;

import java.util.List;

public interface GruppoLicenzeCommercialiService {
	public List<Gruppolicenzecommerciali> getAll();
	public Gruppolicenzecommerciali get(Integer id);
	//public List<Gruppolicenzecommerciali> getByLicenzecommerciali(Licenzecommerciali licenzeCommerciali);
	public void add(Gruppolicenzecommerciali Gruppolicenzecommerciali);
	public void delete(Integer id);
	public void edit(Gruppolicenzecommerciali Gruppolicenzecommerciali);
}
