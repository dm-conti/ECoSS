package it.obiectivo.ecoss.service;

import java.util.List;
import it.obiectivo.ecoss.domain.Distributore;

public interface DistributoreService {
	public List<Distributore> getAll();
	public Distributore get(Integer id);
	public Distributore getByRagSociale(String ragioneSociale);
	public Boolean add(Distributore distributore);
	public Boolean delete(Integer id);
	public Boolean edit(Distributore distributore);
}